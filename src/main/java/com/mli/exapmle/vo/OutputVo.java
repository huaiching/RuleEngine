package com.mli.exapmle.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "規則檢核結果")
public class OutputVo {
    @Schema(description = "程式碼檢核結果")
    private List<RuleHitVo> ruleHitVoList;
    @Schema(description = "spEL檢核結果")
    private List<String> spELRuleList;

    public List<RuleHitVo> getRuleHitVoList() {
        return ruleHitVoList;
    }

    public void setRuleHitVoList(List<RuleHitVo> ruleHitVoList) {
        this.ruleHitVoList = ruleHitVoList;
    }

    public List<String> getSpELRuleList() {
        return spELRuleList;
    }

    public void setSpELRuleList(List<String> spELRuleList) {
        this.spELRuleList = spELRuleList;
    }
}
