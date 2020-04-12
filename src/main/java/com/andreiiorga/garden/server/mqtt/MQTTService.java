package com.andreiiorga.garden.server.mqtt;

import com.andreiiorga.garden.server.beans.HeartbeatMessage;
import com.andreiiorga.garden.server.beans.IncomingDeviceMessage;
import com.andreiiorga.garden.server.firebase.Firebase;
import com.andreiiorga.garden.server.persistence.entities.HeartbeatEntity;
import com.andreiiorga.garden.server.persistence.entities.PinEntity;
import com.andreiiorga.garden.server.persistence.repositories.FirebaseTokensRepository;
import com.andreiiorga.garden.server.persistence.repositories.PinRepository;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.StringReader;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Component
public class MQTTService implements MqttCallback {
    private final int qos = 1;
    private MqttClient client;
    private Gson gson;
    private String clientId;
    private String receiveFromDevicesTopic;
    private String brokerURL;
    private String heartbeatTopic;

    @Autowired
    PinRepository pinRepository;

    @Autowired
    FirebaseTokensRepository firebaseTokensRepository;

    @Autowired
    EntityManager em;

    @Autowired
    Firebase firebase;

    @Autowired
    public MQTTService(@Value("${mqtt.broker-url}") String brokerURL, @Value("${mqtt.client-id}") String clientId, @Value("${mqtt.receive-from-devices}") String receiveFromDevicesTopic, @Value("${mqtt.heartbeat}") String heartbeatTopic) throws MqttException {
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);

        this.receiveFromDevicesTopic = receiveFromDevicesTopic;
        this.heartbeatTopic = heartbeatTopic;

        this.client = new MqttClient(brokerURL, clientId, new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect(conOpt);

        gson = new Gson();

        this.subscribe(receiveFromDevicesTopic);
        this.subscribe(heartbeatTopic);
    }

    public void subscribe(String topic) throws MqttException {
        this.client.subscribe(topic, qos);
    }

    private String[] getAuth(URI uri) {
        String a = uri.getAuthority();
        String[] first = a.split("@");
        return first[0].split(":");
    }

    public void sendMessageRetained(String topic, String payload) throws MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        message.setRetained(true);
        this.client.publish(topic, message);
    }

    public void sendMessage(String topic, String payload) throws MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        this.client.publish(topic, message);
    }

    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        cause.printStackTrace();
        //System.exit(1);
    }


    public void deliveryComplete(IMqttDeliveryToken token) {
    }

    @Transactional
    public void messageArrived(String topic, MqttMessage message){
        try {
            Gson gson = new Gson();
            if(topic.equals(receiveFromDevicesTopic)){

                IncomingDeviceMessage incomingDeviceMessage = gson.fromJson(new String(message.getPayload()), IncomingDeviceMessage.class);

                PinEntity pinEntity = pinRepository.findByDeviceCustomIdAndNumberOnDevice("/" + incomingDeviceMessage.getDeviceId(), incomingDeviceMessage.getRelayIndex());
                System.out.println("Mesaj de la pin-ul cu id: " + pinEntity.getId());
                System.out.println(new String(message.getPayload()));
                pinEntity.setState(incomingDeviceMessage.getState());
                pinEntity.setLast_change(incomingDeviceMessage.getNow());
                pinEntity.setExpiration_time(incomingDeviceMessage.getRunForMinutes());

                pinRepository.save(pinEntity);

                if(!incomingDeviceMessage.isState()){
                    List<String> tokens = firebaseTokensRepository.getTokensByUser(pinEntity.getDevice().getUser());
                    for(String token: tokens){
                        firebase.sendNotification("Smart-Garden", "Releul " + pinEntity.getDescription() + " s-a oprit!", token);
                    }

                }
            }else if(topic.equals(heartbeatTopic)){
                HeartbeatMessage heartbeatMessage = gson.fromJson(new String(message.getPayload()), HeartbeatMessage.class);
                em.merge(new HeartbeatEntity(heartbeatMessage.getIdDevice(), new Date()));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
