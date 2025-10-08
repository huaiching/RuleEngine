package com.mli.exapmle.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "檢核 spEL 結果")
public class ValidateSpELVo {
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
