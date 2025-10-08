package com.mli.exapmle.service.rule;

import com.mli.exapmle.dto.RuleTableDto;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SpELCheckService {
    public List<String> checkRule(RuleTableDto ruleTableDto, Map<String, Object> dataMap) {
        List<String> hitList = new ArrayList<>();

        // 開始檢核
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        dataMap.forEach(context::setVariable);

        String rule = ruleTableDto.getRuleCode();
        String check = rule + " ? true : false";
        Boolean result = parser.parseExpression(
                        check)
                .getValue(context, Boolean.class);

        if (Boolean.TRUE.equals(result)) {
            hitList.add(ruleTableDto.getRuleItem());
        }

        return hitList;
    }
}
