package com.mli.exapmle.contract;

import com.mli.exapmle.dto.CalculationDto;

import java.util.Map;

// 抽象類作為標記，無方法
public interface SpELCalcContract {

    /**
     * 子類需實作 calculate 方法
     * @param dto 數據資料，於方法中 直接 set 更新資料數據
     */
    Map<String, Object> calculate(CalculationDto dto);
}