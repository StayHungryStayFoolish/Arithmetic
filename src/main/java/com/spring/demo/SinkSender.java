package com.spring.demo;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @author Created by bonismo@hotmail.com on 2019/6/14 4:30 PM
 * @Description:
 * @Version: 1.0
 */
@Component
public interface SinkSender {

    String OUTPUT = "input";

    @Output(SinkSender.OUTPUT)
    MessageChannel output();

}
