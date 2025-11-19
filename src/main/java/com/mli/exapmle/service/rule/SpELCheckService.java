package com.mli.exapmle.service.rule;

import com.mli.exapmle.dto.RuleTableDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.*;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SpELCheckService {
    private static final Logger log = LoggerFactory.getLogger(SpELCheckService.class);

    // SpEL 表達式解析器全局單例: 避免 多次 new 浪費記憶體
    private static final ExpressionParser PARSER = new SpelExpressionParser();

    public List<String> checkRule(RuleTableDto ruleTableDto, Map<String, Object> dataMap) {
        List<String> hitList = new ArrayList<>();

        // 取得規則
        String ruleCode = ruleTableDto.getRuleCode().trim();
        if (StringUtils.isEmpty(ruleCode)) {
            return hitList; // 空規則直接返回，避免異常
        }

        // 設定變數容器
        EvaluationContext context = new StandardEvaluationContext();
        dataMap.forEach(context::setVariable);

        try {
            Boolean result = PARSER.parseExpression(ruleCode.trim())
                    .getValue(context, Boolean.class);

            if (Boolean.TRUE.equals(result)) {
                hitList.add(ruleTableDto.getRuleItem());
            }
        } catch (ParseException e) {
            // 語法錯誤（規則寫錯）
            log.error("【SpEL 語法錯誤】ruleItem={} ruleCode={} error={}",
                    ruleTableDto.getRuleItem(), ruleCode, e.getMessage());

        } catch (EvaluationException e) {
            // 執行期錯誤（變數 null、類型不符等）
            log.error("【SpEL 執行失敗】ruleItem={} ruleCode={} error={}",
                    ruleTableDto.getRuleItem(), ruleCode, e.getMessage());

        } catch (Exception e) {
            // 其他未知錯誤
            log.error("【SpEL 未知異常】ruleItem={} ruleCode={}",
                    ruleTableDto.getRuleItem(), ruleCode, e);
        }

        return hitList;
    }
}
