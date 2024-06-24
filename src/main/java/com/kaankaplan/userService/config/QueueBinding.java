package com.kaankaplan.userService.config;

import lombok.Data;

@Data
public class QueueBinding {
    private String queue;
    private String exchange;
    private String routingKey;
}
