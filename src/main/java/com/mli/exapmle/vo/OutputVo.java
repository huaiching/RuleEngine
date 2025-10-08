package com.mli.exapmle.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "規則檢核結果")
public class OutputVo {
    @Schema(description = "檢核結果")
    private List<String> ruleList;

    public List<String> getRuleList() {

        return ruleList;
    }

    public void setRuleList(List<String> ruleList) {
        this.ruleList = ruleList;
    }
}
