package com.kubeworks.userService.business.abstracts;

import com.kubeworks.userService.common.dtos.UserCommentEventDto;
import com.kubeworks.userService.entity.Claim;
import com.kubeworks.userService.entity.dto.UserCommentRequestDto;

public interface ClaimService {

    Claim getClaimByClaimName(String claimName);
    void createUserComment(UserCommentRequestDto userCommentRequestDto);
    void cancelUserComment(UserCommentEventDto userCommentEventDto);
}
