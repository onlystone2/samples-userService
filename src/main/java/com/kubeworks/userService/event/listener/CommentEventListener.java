package com.kubeworks.userService.event.listener;

import com.kubeworks.userService.business.abstracts.ClaimService;
import com.kubeworks.userService.common.dtos.UserCommentEventDto;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Component
public class CommentEventListener {
    private final ClaimService claimService;

    @RabbitListener(queues = "${amqp.queues.comment_failed}", ackMode = "MANUAL")
    public void handleCommentFailedEvent(UserCommentEventDto userCommentEventDto, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag)
            throws IOException {
        log.info("^^^^^^^^^^^^^^^^^^ Comment Failed Event Listen : " +userCommentEventDto);
        try {
            claimService.cancelUserComment(userCommentEventDto);
            channel.basicAck(tag, false);
        } catch (RuntimeException | IOException ex) {
            ex.printStackTrace();
            channel.basicNack(tag, false, false);
        }
    }

    @RabbitListener(queues = "${amqp.queues.comment_created}", ackMode = "MANUAL")
    public void handleCommentCompletedEvent(UserCommentEventDto userCommentEventDto, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag)
            throws IOException {
        log.info("<<<<<<<<<<<<<<<<<< Comment Created Event Listen : " +userCommentEventDto);
        channel.basicAck(tag, false);
    }
}
