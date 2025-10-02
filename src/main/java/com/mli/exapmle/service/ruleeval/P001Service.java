package com.mli.exapmle.service.ruleeval;

import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.contract.RuleEvaluationService;
import com.mli.exapmle.service.helpers.AgeRuleService;
import com.mli.exapmle.vo.RuleHitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class P001Service implements RuleEvaluationService {
    @Autowired
    private AgeRuleService ageRuleService;

    @Override
    public List<RuleHitVo> evaluate(CalculationDto dto, List<String> ruleTable, Map<String, String> ruleMap) {
        List<RuleHitVo> hits = new ArrayList<>();
        // 模擬檢核 年齡 >= 18 出訊息
        if (ageRuleService.ckeckAge18(dto)) {
            String desc = dto.getClientId() + ", " + ruleMap.get("P001");

            RuleHitVo ruleHitVo = new RuleHitVo();
            ruleHitVo.setCode("P001");
            ruleHitVo.setDesc(desc);
            hits.add(ruleHitVo);
        }
        return hits;
    }
}
