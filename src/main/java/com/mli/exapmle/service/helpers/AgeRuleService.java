package com.mli.exapmle.service.helpers;

import com.mli.exapmle.dto.CalculationDto;
import org.springframework.stereotype.Service;

@Service
public class AgeRuleService {

    public boolean ckeckAge18(CalculationDto dto) {
        return dto.getAge() >= 18;
    }

    public boolean ckeckAge30(CalculationDto dto) {
        return dto.getAge() >= 30;
    }

}
