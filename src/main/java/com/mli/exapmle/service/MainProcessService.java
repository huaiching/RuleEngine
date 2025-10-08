package com.mli.exapmle.service;


import com.mli.exapmle.contract.SpELCalcContract;
import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.dto.InputDto;
import com.mli.exapmle.dto.RuleCodeDto;
import com.mli.exapmle.dto.RuleTableDto;
import com.mli.exapmle.service.rule.SpELCheckService;
import com.mli.exapmle.vo.OutputVo;
import com.mli.exapmle.vo.RuleHitVo;
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
import java.util.concurrent.CopyOnWriteArrayList;

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
        BeanUtils.copyProperties(inputDto, dto);

        // 讀取規則表
        List<RuleTableDto> ruleTableList = ruleTableService.getRuleTable();
        List<RuleCodeDto> ruleCodeList = ruleTableService.getRuleCodeToChineseMap();

        /*** 數據計算 ***/

        // 數據計算: 非同步
        List<CompletableFuture<Void>> calcFutures = new ArrayList<>();
        for (DataCalcContract dataCalcContract : dataCalcContractList) {
            calcFutures.add(CompletableFuture.runAsync(() -> dataCalcContract.calculate(dto)));
        }
        CompletableFuture.allOf(calcFutures.toArray(new CompletableFuture[0])).join();

        /*** 程式規則檢核 ***/

        // 規則判斷: 非同步 處理
        List<CompletableFuture<List<RuleHitVo>>> ruleFutures = new ArrayList<>();
        for (RuleContract ruleContract : ruleContractList) {
            ruleFutures.add(CompletableFuture.supplyAsync(() -> ruleContract.evaluate(dto, ruleCodeList)));
        }
        CompletableFuture.allOf(ruleFutures.toArray(new CompletableFuture[0])).join();

        // 規則判斷: 收集所有命中規則
        List<RuleHitVo> allHitCodes = new ArrayList<>();
        for (CompletableFuture<List<RuleHitVo>> future : ruleFutures) {
            allHitCodes.addAll(future.join());
        }
        outputVo.setRuleHitVoList(allHitCodes);

        /*** spEL 規則檢核 ***/

        // spEL 數據設定: 非同步 處理
        List<CompletableFuture<Map<String, Object>>> spELCalcFutures = new ArrayList<>();
        for (SpELCalcContract spELCalcContract : spELCalcContractList) {
            spELCalcFutures.add(CompletableFuture.supplyAsync(() -> spELCalcContract.calculate()));
        }
        CompletableFuture.allOf(spELCalcFutures.toArray(new CompletableFuture[0])).join();

        // spEL 數據設定: 收集所有命中規則
        Map<String, Object> dataMap = new HashMap<>();
        for (CompletableFuture<Map<String, Object>> future : spELCalcFutures) {
            dataMap.putAll(future.join());
        }


        // spEL 規則判斷
        List<String> spELRuleList = new CopyOnWriteArrayList<>();
        ruleTableList.parallelStream().forEach(ruleTable -> {
            List<String> result = spELCheckService.checkRule(ruleTable, dataMap);
            spELRuleList.addAll(result);
        });
        outputVo.setSpELRuleList(spELRuleList);


        long end = System.currentTimeMillis();
        double diff = (double) (end - start) / 1000;
        System.out.println("執行時間: " + diff + " 秒");


        return outputVo;
    }
}