package com.mli.exapmle.service.helpers;

import com.mli.exapmle.dto.RuleCodeDto;
import com.mli.exapmle.vo.RuleHitVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleHitSetService {
    /**
     * @param ruleHitCode
     * @return
     */

    /**
     * 設定命中訊息: 類型 1 - 純文字
     * @param ruleHitCode 命中訊息
     * @param ruleCodeList 訊息代碼檔
     * @return RuleHitVo
     */
    public RuleHitVo ruleHitType1(String ruleHitCode, List<RuleCodeDto> ruleCodeList) {
        RuleCodeDto ruleCodeDto = ruleCodeList.stream()
                .filter(ruleCode -> ruleHitCode.equals(ruleCode.getRuleCode()))
                .findFirst().orElse(new RuleCodeDto());
        String desc = ruleCodeDto.getRuleDesc();

        RuleHitVo ruleHitVo = new RuleHitVo();
        ruleHitVo.setCode(ruleHitCode);
        ruleHitVo.setDesc(desc);
        ruleHitVo.setLevel(ruleCodeDto.getLevel());

        return ruleHitVo;
    }

    /**
     * 設定命中訊息: 類型 2 - ID + 純文字
     * @param ruleHitCode 命中訊息
     * @param clientId ID
     * @param ruleCodeList 訊息代碼檔
     * @return RuleHitVo
     */
    public RuleHitVo ruleHitType2(String ruleHitCode, String clientId, List<RuleCodeDto> ruleCodeList) {
        RuleCodeDto ruleCodeDto = ruleCodeList.stream()
                .filter(ruleCode -> ruleHitCode.equals(ruleCode.getRuleCode()))
                .findFirst().orElse(new RuleCodeDto());
        String desc = clientId + ", " + ruleCodeDto.getRuleDesc();

        RuleHitVo ruleHitVo = new RuleHitVo();
        ruleHitVo.setCode(ruleHitCode);
        ruleHitVo.setDesc(desc);
        ruleHitVo.setLevel(ruleCodeDto.getLevel());

        return ruleHitVo;
    }
}
