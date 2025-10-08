package com.mli.exapmle.contract;


import com.mli.exapmle.dto.CalculationDto;

import java.util.List;

// 抽象類作為標記，無方法
public interface RuleContract {

    /**
     * 子類需實作 evaluate 方法，返回命中規則代碼 List
     * @param dto   數據資料
     * @return
     */
    List<String> evaluate(CalculationDto dto);
}