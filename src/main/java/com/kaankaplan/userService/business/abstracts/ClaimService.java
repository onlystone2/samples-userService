package com.kaankaplan.userService.business.abstracts;

import com.kaankaplan.userService.common.dtos.UserCommentEventDto;
import com.kaankaplan.userService.entity.Claim;
import com.kaankaplan.userService.entity.dto.UserCommentRequestDto;

public interface ClaimService {

    Claim getClaimByClaimName(String claimName);
    void createUserComment(UserCommentRequestDto userCommentRequestDto);
    void cancelUserComment(UserCommentEventDto userCommentEventDto);
}
