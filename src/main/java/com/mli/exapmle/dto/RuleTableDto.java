package com.mli.exapmle.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "規則表")
public class RuleTableDto {
    @Schema(description = "規則代碼")
    private String ruleItem;
    @Schema(description = "檢核規則")
    private String ruleCode;
    @Schema(description = "等級")
    private String level;

    public RuleTableDto() {
    }

    public RuleTableDto(String ruleItem, String ruleCode, String level) {
        this.ruleItem = ruleItem;
        this.ruleCode = ruleCode;
        this.level = level;
    }

    public String getRuleItem() {
        return ruleItem;
    }

    public void setRuleItem(String ruleItem) {
        this.ruleItem = ruleItem;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
