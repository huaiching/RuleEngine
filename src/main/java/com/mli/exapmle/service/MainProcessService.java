package com.mli.exapmle.service;


import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.dto.InputDto;
import com.mli.exapmle.vo.RuleHitVo;
import com.mli.exapmle.contract.DataCalculationService;
import com.mli.exapmle.contract.RuleEvaluationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class MainProcessService {

    @Autowired
    private List<DataCalculationService> dataCalcServices;  // 自動注入所有子類

    @Autowired
    private RuleTableService ruleTableService;

    @Autowired
    private List<RuleEvaluationService> ruleEvalServices;  // 自動注入所有子類

    public List<RuleHitVo> executeProcess(InputDto inputDto) {
        CalculationDto dto = new CalculationDto();

        // 輸入資料複製
        BeanUtils.copyProperties(inputDto, dto);

        // 數據計算 非同步
        List<CompletableFuture<Void>> calcFutures = new ArrayList<>();
        for (DataCalculationService calcService : dataCalcServices) {
            calcFutures.add(CompletableFuture.runAsync(() -> calcService.calculate(dto)));
        }
        CompletableFuture.allOf(calcFutures.toArray(new CompletableFuture[0])).join();

        // 讀取規則表
        List<String> ruleTable = ruleTableService.getRuleTable();
        Map<String, String> ruleMap = ruleTableService.getRuleCodeToChineseMap();

        // 規則判斷 非同步
        List<CompletableFuture<List<RuleHitVo>>> evalFutures = new ArrayList<>();
        for (RuleEvaluationService evalService : ruleEvalServices) {
            evalFutures.add(CompletableFuture.supplyAsync(() -> evalService.evaluate(dto, ruleTable, ruleMap)));
        }
        CompletableFuture.allOf(evalFutures.toArray(new CompletableFuture[0])).join();

        // 收集所有命中規則
        List<RuleHitVo> allHitCodes = new ArrayList<>();
        for (CompletableFuture<List<RuleHitVo>> future : evalFutures) {
            allHitCodes.addAll(future.join());
        }

        return allHitCodes;
    }
}