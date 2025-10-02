package com.mli.exapmle.contract;


import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.dto.RuleCodeDto;
import com.mli.exapmle.vo.RuleHitVo;

import java.util.List;
import java.util.Map;

// 抽象類作為標記，無方法
public interface RuleContract {

    /**
     * 子類需實作 evaluate 方法，返回命中規則代碼 List
     * @param dto   數據資料
     * @param ruleTable 規則表
     * @param ruleCodeList 訊息代碼表
     * @return
     */
    List<RuleHitVo> evaluate(CalculationDto dto, List<String> ruleTable, List<RuleCodeDto> ruleCodeList);
}