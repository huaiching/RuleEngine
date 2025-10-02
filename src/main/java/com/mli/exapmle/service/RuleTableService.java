package com.mli.exapmle.service;

import com.mli.exapmle.dto.RuleCodeDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RuleTableService {

    /**
     * 取得規則表
     * @return
     */
    public List<String> getRuleTable() {
        // 模擬寫死規則表 (包含 RULE003 條件)
        return Arrays.asList("age > 18", "income > 50000", "age < 30");
    }

    /**
     * 取得 訊息代碼表
     * @return
     */
    public List<RuleCodeDto> getRuleCodeToChineseMap() {
        List<RuleCodeDto> ruleCodeDtoList = new ArrayList<>();
        ruleCodeDtoList.add(new RuleCodeDto("P001","年齡超過18","1"));
        ruleCodeDtoList.add(new RuleCodeDto("P002","收入超過50000","1"));
        ruleCodeDtoList.add(new RuleCodeDto("P003","年齡小於30","1"));
        return ruleCodeDtoList;
    }
}