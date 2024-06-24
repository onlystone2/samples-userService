package com.kubeworks.userService.business.abstracts;

import com.kubeworks.userService.entity.dto.UserAuthenticationResponseDto;
import com.kubeworks.userService.entity.dto.UserLoginRequestDto;

public interface AuthService {

    UserAuthenticationResponseDto login(UserLoginRequestDto userLoginRequestDto);

}
