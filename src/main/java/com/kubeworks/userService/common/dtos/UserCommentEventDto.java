package com.kubeworks.userService.common.dtos;

import com.kubeworks.userService.common.enums.UserCommentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserCommentEventDto {
    private String userId;
    private String commentByUserId;
    private UserCommentStatus userCommentStatus;
    private Date instant = new Date();

    public UserCommentEventDto(String userId, String commentByUserId) {
        this.userId = userId;
        this.commentByUserId = commentByUserId;
    }
}
