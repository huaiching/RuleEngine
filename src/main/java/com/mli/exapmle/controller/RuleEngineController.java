package com.mli.exapmle.controller;

import com.mli.exapmle.dto.InputDto;
import com.mli.exapmle.dto.ValidateSpELSerachDto;
import com.mli.exapmle.service.MainProcessService;
import com.mli.exapmle.vo.OutputVo;
import com.mli.exapmle.vo.ValidateSpELVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rule Controller", description = "規則引擎 API 接口")
@RestController
public class RuleEngineController {
    @Autowired
    private MainProcessService mainProcessService;

    @Operation(summary = "規則引擎 檢核", description = "規則引擎 檢核")
    @PostMapping("/api/rule-engine")
    public ResponseEntity<OutputVo> processRuleEngine(@RequestBody InputDto inputDto) {
        return ResponseEntity.ok(mainProcessService.executeProcess(inputDto));
    }

    @Operation(summary = "spEL 檢核文字驗證", description = "spEL 檢核文字驗證")
    @PostMapping("/api/validateSpEL")
    public ResponseEntity<ValidateSpELVo> validateSpEL(@RequestBody ValidateSpELSerachDto validateSpELSerachDto) {
        ExpressionParser parser = new SpelExpressionParser();
        ValidateSpELVo validateSpELVo = new ValidateSpELVo();
        try {
            parser.parseExpression(validateSpELSerachDto.getSpelExpr());
            validateSpELVo.setResult(Boolean.TRUE);
        } catch (ParseException e) {
            // SpEL 語法錯誤
            validateSpELVo.setResult(Boolean.FALSE);
        }
        return ResponseEntity.ok(validateSpELVo);
    }

}
