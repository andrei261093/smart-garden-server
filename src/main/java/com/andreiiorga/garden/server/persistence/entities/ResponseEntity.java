package com.andreiiorga.garden.server.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "response")
public class ResponseEntity {

    @Id
    @Column(name = "[id]")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "[uuid]")
    private String uuid;

    @Column(name = "[commandJson]")
    private String commandJson;

    public ResponseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCommandJson() {
        return commandJson;
    }

    public void setCommandJson(String commandJson) {
        this.commandJson = commandJson;
    }
}
