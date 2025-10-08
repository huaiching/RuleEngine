package com.mli.exapmle.service.rule;

import com.mli.exapmle.dto.CalculationDto;
import com.mli.exapmle.contract.RuleContract;
import com.mli.exapmle.dto.RuleCodeDto;
import com.mli.exapmle.service.helpers.AgeRuleService;
import com.mli.exapmle.service.helpers.RuleHitSetService;
import com.mli.exapmle.vo.RuleHitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class P003Service implements RuleContract {
    @Autowired
    private AgeRuleService ageRuleService;
    @Autowired
    private RuleHitSetService ruleHitSetService;

    @Override
    public List<RuleHitVo> evaluate(CalculationDto dto, List<RuleCodeDto> ruleCodeList) {
        List<RuleHitVo> hits = new ArrayList<>();
        // 模擬檢核 年齡 < 30 出訊息
        if (!ageRuleService.ckeckAge30(dto)) {
            RuleHitVo ruleHitVo = ruleHitSetService.ruleHitType2("P003", dto.getClientId(), ruleCodeList);

            hits.add(ruleHitVo);
        }
        return hits;
    }
}
