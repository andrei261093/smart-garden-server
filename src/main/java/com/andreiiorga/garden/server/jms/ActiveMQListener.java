package com.andreiiorga.garden.server.jms;


import com.andreiiorga.garden.server.beans.IncomingDeviceMessage;
import com.andreiiorga.garden.server.persistence.entities.PinEntity;
import com.andreiiorga.garden.server.persistence.repositories.PinRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import org.springframework.messaging.Message;
import java.util.Date;

@Component
public class ActiveMQListener {

    @Autowired
    PinRepository pinRepository;

    @JmsListener(destination = "${activemq.topic.listen-from-devices}")
    public void logAction(final Message jsonMessage) {
        Gson gson = new Gson();
        IncomingDeviceMessage incomingDeviceMessage = gson.fromJson(jsonMessage.getPayload().toString(), IncomingDeviceMessage.class);

        PinEntity pinEntity = pinRepository.findByDeviceCustomIdAndNumberOnDevice("/" + incomingDeviceMessage.getDeviceId(), incomingDeviceMessage.getRelayIndex());

        pinEntity.setState(incomingDeviceMessage.getState());
        pinEntity.setLast_change(incomingDeviceMessage.getNow());
        pinEntity.setExpiration_time(incomingDeviceMessage.getRunForMinutes());

        pinRepository.save(pinEntity);
    }
}
