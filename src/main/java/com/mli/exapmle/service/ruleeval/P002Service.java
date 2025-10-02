package com.mli.exapmle.service.ruleeval;

import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.contract.RuleEvaluationService;
import com.mli.exapmle.vo.RuleHitVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class P002Service implements RuleEvaluationService {

    @Override
    public List<RuleHitVo> evaluate(CalculationDto dto, List<String> ruleTable, Map<String, String> ruleMap) {
        List<RuleHitVo> hits = new ArrayList<>();
        if (dto.getIncome() > 50000) {
            RuleHitVo ruleHitVo = new RuleHitVo();
            ruleHitVo.setCode("P002");
            ruleHitVo.setDesc(ruleMap.get("P002"));
            hits.add(ruleHitVo);
        }
        return hits;
    }
}
