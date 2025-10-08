package com.mli.exapmle.controller;

import com.mli.exapmle.dto.InputDto;
import com.mli.exapmle.service.MainProcessService;
import com.mli.exapmle.vo.OutputVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RuleEngineController {
    @Autowired
    private MainProcessService mainProcessService;

    @PostMapping("/api/rule-engine")
    public OutputVo processRuleEngine(@RequestBody InputDto inputDto) {
        return mainProcessService.executeProcess(inputDto);
    }
}
