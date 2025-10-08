package com.mli.exapmle.service.rule;

import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.contract.RuleContract;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class P002Service implements RuleContract {

    @Override
    public List<String> evaluate(CalculationDto dto) {
        List<String> hits = new ArrayList<>();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (dto.getIncome() > 50000) {
            hits.add("P002");
        }
        return hits;
    }
}
