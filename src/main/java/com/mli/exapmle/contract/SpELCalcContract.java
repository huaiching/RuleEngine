package com.mli.exapmle.contract;

import com.mli.exapmle.dto.CalculationDto;

import java.util.Map;

// 抽象類作為標記，無方法
public interface SpELCalcContract {

    /**
     * 子類需實作 calculate 方法
     */
    Map<String, Object> calculate();
}