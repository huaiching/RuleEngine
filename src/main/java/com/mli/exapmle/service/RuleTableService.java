package com.mli.exapmle.service;

import com.mli.exapmle.dto.RuleCodeDto;
import com.mli.exapmle.dto.RuleTableDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RuleTableService {

    /**
     * 取得規則表
     * @return
     */
    public List<RuleTableDto> getRuleTable() {
        List<RuleTableDto> ruleTableDtoList = new ArrayList<>();
        ruleTableDtoList.add(new RuleTableDto("R001","#age > 18 and #income > 50000", "1"));
        ruleTableDtoList.add(new RuleTableDto("R002","#planClasCode matches '0.*'", "1"));
        ruleTableDtoList.add(new RuleTableDto("R003","(#age < 18 or #age > 30) and #income > 50000", "2"));
        return ruleTableDtoList;
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