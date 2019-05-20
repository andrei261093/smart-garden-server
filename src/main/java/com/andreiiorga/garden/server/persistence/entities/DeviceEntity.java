package com.andreiiorga.garden.server.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device")
public class DeviceEntity {

    @Id
    @Column(name = "[id]")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @OneToMany(mappedBy="device", fetch = FetchType.LAZY)
    private List<PinEntity> pins;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private UserEntity user;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "topic")
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PinEntity> getPins() {
        return pins;
    }

    public void setPins(List<PinEntity> pins) {
        this.pins = pins;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
