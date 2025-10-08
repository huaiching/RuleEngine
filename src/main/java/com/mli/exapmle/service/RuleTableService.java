package com.mli.exapmle.service;

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
}