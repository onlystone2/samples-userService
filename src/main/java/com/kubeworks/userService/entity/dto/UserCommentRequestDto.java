package com.kubeworks.userService.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCommentRequestDto {
    private String customerName;
    private String email;
    private String phone;
    private String password;

    private String commentText;
    private String commentBy;
    private String commentByUserId;
    private int movieId;
}
