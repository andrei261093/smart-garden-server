package com.andreiiorga.garden.server.endpoints;

import com.andreiiorga.garden.server.beans.DeviceCommand;
import com.andreiiorga.garden.server.mqtt.MQTTService;
import com.andreiiorga.garden.server.persistence.entities.DeviceEntity;
import com.andreiiorga.garden.server.persistence.entities.FirebaseTokenEntity;
import com.andreiiorga.garden.server.persistence.entities.PinEntity;
import com.andreiiorga.garden.server.persistence.entities.UserEntity;
import com.andreiiorga.garden.server.persistence.repositories.DeviceRepository;
import com.andreiiorga.garden.server.persistence.repositories.PinRepository;
import com.andreiiorga.garden.server.persistence.repositories.UserRepository;
import com.andreiiorga.garden.server.persistence.repositories.ViewsRepository;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Component
@Path("/garden/api")
public class RestController {

    @Autowired
    PinRepository pinRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ViewsRepository viewsRepository;

    @Autowired
    MQTTService mqttService;

    @Autowired
    EntityManager em;

    @POST
    @Path("/switch")
    @Transactional
    public Response switchOn(DeviceCommand deviceCommand) {
        PinEntity pinEntity = pinRepository.findById(deviceCommand.getPinId());
        DeviceEntity deviceEntity = pinEntity.getDevice();
        Gson gson = new Gson();

        try {
            mqttService.sendMessage("smart-outlet" + deviceEntity.getTopic(), gson.toJson(deviceCommand.getCommand()));
            return Response.status(200).build();
        } catch (MqttException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/register/token")
    @Transactional
    public Response registerToken(FirebaseTokenEntity tokenBean) {
        System.out.println("Registering token");
        try{
            deviceRepository.registerToken(tokenBean);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
        return Response.status(200).build();
    }

    @GET
    @Path("/users")
    @Produces("application/json")
    public Response getUsers() {
        return Response.ok(userRepository.findAll()).build();
    }

    @GET
    @Path("/user/{id}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") String id) {
        return Response.ok(userRepository.findById(Integer.parseInt(id))).build();
    }

    @GET
    @Path("/user/{id}/devices/active-pins")
    @Produces("application/json")
    public Response getActivePins(@PathParam("id") String id) {
        return Response.ok(viewsRepository.getActivePinsForUserId(1)).build();
    }

    @GET
    @Path("/user/{userId}/devices")
    @Produces("application/json")
    public Response getDevices(@PathParam("userId") String userId) {
        return Response.ok(deviceRepository.findByUser(Integer.parseInt(userId))).build();
    }

    @GET
    @Path("/device/{deviceId}")
    @Produces("application/json")
    public Response getDevice(@PathParam("deviceId") String deviceId) {
        return Response.ok(deviceRepository.findById(Integer.parseInt(deviceId))).build();
    }

    @GET
    @Path("/device/{deviceId}/pins")
    @Produces("application/json")
    public Response getDevicePins(@PathParam("deviceId") String deviceId) {
        return Response.ok(pinRepository.findByDevice(Integer.parseInt(deviceId))).build();
    }

    @GET
    @Path("/seed")
    @Transactional
    public Response seed() {

        UserEntity userEntity = new UserEntity();
        userEntity.setAddress("Str 1933, nr 2, Rosiori de Vede, Teleorman");
        userEntity.setFirstName("Cristi");
        userEntity.setLastName("Iorga");

        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setTopic("/001");
        deviceEntity.setUser(userEntity);
        deviceEntity.setName("RaspberryPi ZeroW");

        PinEntity pinEntity1 = new PinEntity();
        pinEntity1.setDevice(deviceEntity);
        pinEntity1.setExpiration_time(0);
        pinEntity1.setState(false);
        pinEntity1.setNumberOnDevice(1);
        pinEntity1.setDescription("Gradina");

        PinEntity pinEntity2 = new PinEntity();
        pinEntity2.setDevice(deviceEntity);
        pinEntity2.setExpiration_time(0);
        pinEntity2.setState(false);
        pinEntity2.setNumberOnDevice(2);
        pinEntity2.setDescription("Terasa");

        PinEntity pinEntity3 = new PinEntity();
        pinEntity3.setDevice(deviceEntity);
        pinEntity3.setExpiration_time(0);
        pinEntity3.setState(false);
        pinEntity3.setNumberOnDevice(3);
        pinEntity3.setDescription("Lumina intrare");

        PinEntity pinEntity4 = new PinEntity();
        pinEntity4.setDevice(deviceEntity);
        pinEntity4.setExpiration_time(0);
        pinEntity4.setState(false);
        pinEntity4.setNumberOnDevice(4);
        pinEntity4.setDescription("Lumina Magazie");

        em.persist(userEntity);
        em.persist(deviceEntity);
        em.persist(pinEntity1);
        em.persist(pinEntity2);
        em.persist(pinEntity3);
        em.persist(pinEntity4);

        return Response.status(200).build();
    }
}
