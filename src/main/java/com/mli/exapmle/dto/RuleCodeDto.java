package com.mli.exapmle.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class RuleCodeDto {
    @Schema(description = "訊息代碼")
    private String ruleCode;
    @Schema(description = "訊息說明")
    private String ruleDesc;
    @Schema(description = "等級")
    private String level;

    public RuleCodeDto() {
    }

    public RuleCodeDto(String ruleCode, String ruleDesc, String level) {
        this.ruleCode = ruleCode;
        this.ruleDesc = ruleDesc;
        this.level = level;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
