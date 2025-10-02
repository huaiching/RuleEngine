package com.mli.exapmle.dto;

public class CalculationDto {
    private Integer age;    // 示例欄位: 年齡
    private Double income;  // 示例欄位: 收入
    private String clientId; // 示範欄位: 申請人


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}