package com.mli.exapmle.service.rule;

import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.contract.RuleContract;
import com.mli.exapmle.dto.RuleCodeDto;
import com.mli.exapmle.service.helpers.RuleHitSetService;
import com.mli.exapmle.vo.RuleHitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class P002Service implements RuleContract {
    @Autowired
    private RuleHitSetService ruleHitSetService;

    @Override
    public List<RuleHitVo> evaluate(CalculationDto dto, List<String> ruleTable, List<RuleCodeDto> ruleCodeList) {
        List<RuleHitVo> hits = new ArrayList<>();
        if (dto.getIncome() > 50000) {
            RuleHitVo ruleHitVo = ruleHitSetService.ruleHitType1("P002", ruleCodeList);

            hits.add(ruleHitVo);
        }
        return hits;
    }
}
