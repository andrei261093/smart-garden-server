package com.andreiiorga.garden.server.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${activemq.topic.send-to-device-prefix}")
    private String deviceTopicPrefix;

    public void sendCommandToDevice(String message, String topic) throws JmsException {
        jmsTemplate.convertAndSend(deviceTopicPrefix + topic, message);
    }
}

