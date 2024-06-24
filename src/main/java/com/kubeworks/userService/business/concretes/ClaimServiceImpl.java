package com.kubeworks.userService.business.concretes;

import com.kubeworks.userService.business.abstracts.ClaimService;
import com.kubeworks.userService.common.dtos.UserCommentCreatedEventDto;
import com.kubeworks.userService.common.dtos.UserCommentEventDto;
import com.kubeworks.userService.dao.ClaimDao;
import com.kubeworks.userService.dao.UserDao;
import com.kubeworks.userService.entity.Claim;
import com.kubeworks.userService.entity.User;
import com.kubeworks.userService.entity.dto.UserCommentRequestDto;
import com.kubeworks.userService.event.publisher.UserEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimDao claimDao;
    private final UserDao userDao;
    private final UserEventPublisher commentEventPublisher;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Claim getClaimByClaimName(String claimName) {
        return claimDao.getClaimByClaimName(claimName);
    }

    public void createUserComment(UserCommentRequestDto userCommentRequestDto) {

        Claim claim = this.getClaimByClaimName("CUSTOMER");
        //커맨트 유저 생성
        User user = User.builder()
                .email(userCommentRequestDto.getEmail())
                .password(passwordEncoder.encode(userCommentRequestDto.getPassword()))
                .fullName(userCommentRequestDto.getCustomerName())
                .claim(claim)
                .build();
        userDao.insert(user);

        //커멘트 발행 이벤트
        UserCommentCreatedEventDto userCommentCreatedEventDto = new UserCommentCreatedEventDto();
        userCommentCreatedEventDto.setUserId(user.getUserId());
        userCommentCreatedEventDto.setCommentText(userCommentRequestDto.getCommentText());
        userCommentCreatedEventDto.setCommentBy(userCommentRequestDto.getCommentBy());
        userCommentCreatedEventDto.setCommentByUserId(user.getUserId());
        userCommentCreatedEventDto.setMovieId(userCommentRequestDto.getMovieId());
        commentEventPublisher.publishCommentCreatedEvent(userCommentCreatedEventDto);
    }
    public void cancelUserComment(UserCommentEventDto userCommentEventDto){
//        User user = userDao.findUserByUserId(userCommentEventDto.getUserId());
        userDao.deleteById(userCommentEventDto.getUserId());
        log.info("@@@@@@@@@@@@@@@@@@@@ Rollback completed. Deleted User : " +userCommentEventDto);

    }
}
