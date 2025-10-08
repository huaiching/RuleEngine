package com.mli.exapmle.service.spelcalc;

import com.mli.exapmle.contract.SpELCalcContract;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class planClassCodeService implements SpELCalcContract {
    /**
     * 子類需實作 calculate 方法
     */
    @Override
    public Map<String, Object> calculate() {
        Map<String, Object> result = new HashMap<>();
        result.put("planClasCode", "0821");
        return result;
    }
}
