package com.mli.exapmle.service;


import com.mli.exapmle.contract.SpELCalcContract;
import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.dto.InputDto;
import com.mli.exapmle.dto.RuleTableDto;
import com.mli.exapmle.service.rule.SpELCheckService;
import com.mli.exapmle.vo.OutputVo;
import com.mli.exapmle.contract.DataCalcContract;
import com.mli.exapmle.contract.RuleContract;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class MainProcessService {

    @Autowired
    private List<DataCalcContract> dataCalcContractList;  // 自動注入所有子類

    @Autowired
    private RuleTableService ruleTableService;

    @Autowired
    private List<RuleContract> ruleContractList;  // 自動注入所有子類

    @Autowired
    private SpELCheckService spELCheckService;

    @Autowired
    private List<SpELCalcContract> spELCalcContractList;  // 自動注入所有子類

    public OutputVo executeProcess(InputDto inputDto) {
        long start = System.currentTimeMillis();

        OutputVo outputVo = new OutputVo();
        CalculationDto dto = new CalculationDto();

        // 輸入資料複製
        dto.setInput(inputDto);

        // 讀取規則表
        List<RuleTableDto> ruleTableList = ruleTableService.getRuleTable();

        /*** 數據計算 ***/

        // 數據計算: 非同步
        List<CompletableFuture<Void>> calcFutures = new ArrayList<>();
        for (DataCalcContract dataCalcContract : dataCalcContractList) {
            calcFutures.add(CompletableFuture.runAsync(() -> dataCalcContract.calculate(dto)));
        }
        CompletableFuture.allOf(calcFutures.toArray(new CompletableFuture[0])).join();


        // spEL 數據設定: 非同步 處理
        List<CompletableFuture<Map<String, Object>>> spELCalcFutures = new ArrayList<>();
        for (SpELCalcContract spELCalcContract : spELCalcContractList) {
            spELCalcFutures.add(CompletableFuture.supplyAsync(() -> spELCalcContract.calculate(dto)));
        }
        CompletableFuture.allOf(spELCalcFutures.toArray(new CompletableFuture[0])).join();

        // spEL 數據設定: 收集所有命中規則
        Map<String, Object> dataMap = new HashMap<>();
        for (CompletableFuture<Map<String, Object>> future : spELCalcFutures) {
            dataMap.putAll(future.join());
        }

        /*** 規則檢核 ***/

        List<CompletableFuture<List<String>>> ruleFutures = new ArrayList<>();

        // 1. 程式規則檢核
        for (RuleContract ruleContract : ruleContractList) {
            ruleFutures.add(CompletableFuture.supplyAsync(() -> ruleContract.evaluate(dto)));
        }
        // 2. spEL 規則檢核
        ruleTableList.parallelStream().forEach(ruleTable -> {
            ruleFutures.add(CompletableFuture.supplyAsync(() -> spELCheckService.checkRule(ruleTable, dataMap)));
        });

        CompletableFuture.allOf(ruleFutures.toArray(new CompletableFuture[0])).join();

        // spEL 規則判斷
        List<String> ruleList = new ArrayList<>();
        for (CompletableFuture<List<String>> future : ruleFutures) {
            ruleList.addAll(future.join());
        }
        outputVo.setRuleList(ruleList);


        long end = System.currentTimeMillis();
        double diff = (double) (end - start) / 1000;
        System.out.println("執行時間: " + diff + " 秒");


        return outputVo;
    }
}