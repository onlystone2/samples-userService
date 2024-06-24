package com.kubeworks.userService.business.concretes;

import com.kubeworks.userService.business.abstracts.ClaimService;
import com.kubeworks.userService.business.abstracts.UserService;
import com.kubeworks.userService.dao.UserDao;
import com.kubeworks.userService.entity.Claim;
import com.kubeworks.userService.entity.User;
import com.kubeworks.userService.entity.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ClaimService claimService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean isUserExist(String userId) {

        User user = userDao.findUserByUserId(userId);

        if (user == null) {
            return false;
        }

        return true;
    }

    @Override
    public void addUser(UserRegisterRequestDto userRegisterRequestDto) {

        Claim claim = claimService.getClaimByClaimName("CUSTOMER");

        User user = User.builder()
                        .email(userRegisterRequestDto.getEmail())
                        .password(passwordEncoder.encode(userRegisterRequestDto.getPassword()))
                        .fullName(userRegisterRequestDto.getCustomerName())
                        .claim(claim)
                        .build();
        userDao.insert(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public boolean isUserCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().allMatch(
                a -> a.getAuthority().equals("ROLE_CUSTOMER")
        )) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_ADMIN")
        )) {
            return true;
        }
        return false;
    }
}
