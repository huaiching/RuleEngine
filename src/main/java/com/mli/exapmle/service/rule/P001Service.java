package com.mli.exapmle.service.rule;

import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.contract.RuleContract;
import com.mli.exapmle.service.helpers.AgeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class P001Service implements RuleContract {
    @Autowired
    private AgeRuleService ageRuleService;

    @Override
    public List<String> evaluate(CalculationDto dto) {
        List<String> hits = new ArrayList<>();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (ageRuleService.ckeckAge18(dto)) {
            hits.add("P001");
        }
        return hits;
    }
}
