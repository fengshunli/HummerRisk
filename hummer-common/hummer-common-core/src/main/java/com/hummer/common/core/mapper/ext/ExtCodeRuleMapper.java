package com.hummer.common.core.mapper.ext;

import com.hummer.common.core.domain.request.code.CodeRuleRequest;
import com.hummer.common.core.dto.CodeRuleDTO;

import java.util.List;

public interface ExtCodeRuleMapper {

    List<CodeRuleDTO> ruleList(CodeRuleRequest request);

}
