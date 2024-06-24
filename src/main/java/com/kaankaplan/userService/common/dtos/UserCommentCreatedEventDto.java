package com.kaankaplan.userService.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentCreatedEventDto {
    private String userId;
    private String commentText;
    private String commentBy;
    private String commentByUserId;
    private int movieId;
}