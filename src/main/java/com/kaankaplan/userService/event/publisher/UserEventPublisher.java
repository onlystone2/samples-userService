package com.kaankaplan.userService.event.publisher;

import com.kaankaplan.userService.common.dtos.UserCommentCreatedEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserEventPublisher {
    @Value("${amqp.exchange.user_events}")
    private String userEventsExchange;
    @Value("${amqp.routingKey.user_created_events}")
    private String userPlacedRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void publishCommentCreatedEvent(UserCommentCreatedEventDto userCommentCreatedEventDto) {
        log.info(">>>>>>>>>>>>>>>>>>>>>> User Comment Created Event Publishing : " +userCommentCreatedEventDto);
        rabbitTemplate.convertAndSend(userEventsExchange, userPlacedRoutingKey, userCommentCreatedEventDto);
    }
}
