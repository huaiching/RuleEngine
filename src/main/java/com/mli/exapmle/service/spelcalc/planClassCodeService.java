package com.mli.exapmle.service.spelcalc;

import com.mli.exapmle.contract.SpELCalcContract;
import com.mli.exapmle.dto.CalculationDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class planClassCodeService implements SpELCalcContract {
    /**
     * 子類需實作 calculate 方法
     */
    @Override
    public Map<String, Object> calculate(CalculationDto dto) {
        Map<String, Object> result = new HashMap<>();
        result.put("planClasCode", dto.getInput().getPlanClassCode());
        return result;
    }
}
