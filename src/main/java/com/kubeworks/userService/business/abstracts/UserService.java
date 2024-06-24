package com.kubeworks.userService.business.abstracts;

import com.kubeworks.userService.entity.User;
import com.kubeworks.userService.entity.dto.UserRegisterRequestDto;

public interface UserService {

    Boolean isUserExist(String userId);

    void addUser(UserRegisterRequestDto userRegisterRequestDto);

    User getUserByEmail(String email);

    boolean isUserCustomer();

    boolean isUserAdmin();
}
