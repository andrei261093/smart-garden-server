package com.andreiiorga.garden.server.firebase;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Component;

@Component
public class Firebase {

    public void sendNotification(String title, String body, String deviceToken){

        HttpResponse<JsonNode> response = Unirest.post("https://fcm.googleapis.com/fcm/send")
                .header("Content-Type", "application/json")
                .header("Authorization", "key=AAAA3O1M6EU:APA91bE-Af7qLiZH0igxI88qGHRanx_fyYWoK62uqqlqcwC_dEomAXneF5_IXmywNQM86ettQVhJwvgof9YnlPcf0m6wsPlOFdo0_z9jNazklTK8eFUOUy8_QYgG0g---c9wOpyetL4s")
                .body("{ \"data\": \n" +
                        "\t{\n" +
                        "\t\t \"title\": \"" + title + "\",\n" +
                        "\t\t \"body\": \"" + body + "\",\n" +
                        "\t },\n" +
                        "\t\"to\" : \"" + deviceToken + "\"\n" +
                        " }")
                .asJson();

    }
}
