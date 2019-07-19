package com.spring.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by bonismo@hotmail.com on 2019/6/14 4:39 PM
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api")
@EnableBinding(SinkSender.class)
public class Provider {

    private final SinkSender sinkSender;

    public Provider(SinkSender sinkSender) {
        this.sinkSender = sinkSender;
    }

    @GetMapping("/provider")
    public User provider() {
        User user = new User();
        user.setName("Bonismo");
        user.setMarry(false);
        sinkSender.output().send(MessageBuilder.withPayload(user).build());
        return user;
    }
}
