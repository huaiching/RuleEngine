package com.mli.exapmle.service.datacalc;

import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.contract.DataCalcContract;
import org.springframework.stereotype.Service;

@Service
public class AgeCalcService implements DataCalcContract {

    @Override
    public void calculate(CalculationDto dto) {
        // 模擬計算
        dto.setAge(25);
    }
}