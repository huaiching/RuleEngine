package com.mli.exapmle.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RuleTableService {

    public List<String> getRuleTable() {
        // 模擬寫死規則表 (包含 RULE003 條件)
        return Arrays.asList("age > 18", "income > 50000", "age < 30");
    }

    public Map<String, String> getRuleCodeToChineseMap() {
        Map<String, String> map = new HashMap<>();
        map.put("P001", "年齡超過18");
        map.put("P002", "收入超過50000");
        map.put("P003", "年齡小於30");
        return map;
    }
}