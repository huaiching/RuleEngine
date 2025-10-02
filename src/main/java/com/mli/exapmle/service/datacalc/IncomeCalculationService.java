package com.mli.exapmle.service.datacalc;

import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.contract.DataCalculationService;
import org.springframework.stereotype.Service;

@Service
public class IncomeCalculationService implements DataCalculationService {

    @Override
    public void calculate(CalculationDto dto) {
        // 模擬計算
        dto.setIncome(10000.0);
    }
}