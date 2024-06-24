package com.kubeworks.userService.controller;

import com.kubeworks.userService.business.abstracts.ClaimService;
import com.kubeworks.userService.business.abstracts.UserService;
import com.kubeworks.userService.entity.dto.UserCommentRequestDto;
import com.kubeworks.userService.entity.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ClaimService claimService;

    @GetMapping("isExist/{userId}")
    public boolean isExists(@PathVariable String userId) {
        return userService.isUserExist(userId);
    }

    @PostMapping("add")
    public void addUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        userService.addUser(userRegisterRequestDto);
    }

    @GetMapping("isUserCustomer")
    public boolean isUserCustomer() {
        return userService.isUserCustomer();
    }
    @GetMapping("isUserAdmin")
    public boolean isUserAdmin() {
        return userService.isUserAdmin();
    }

    @PostMapping("comment")
    public void addUserComment(@RequestBody UserCommentRequestDto userCommentRequestDto) {
        claimService.createUserComment(userCommentRequestDto);
    }
}