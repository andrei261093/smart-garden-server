package com.andreiiorga.garden.server.persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pin")
public class PinEntity {

    @Id
    @Column(name = "[id]")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device")
    private DeviceEntity device;

    @Column(name = "[state]")
    private boolean state;

    @Column(name = "[description]")
    private String description;

    @Column(name = "[last_change]")
    private Date last_change;

    @Column(name = "[expiration_time]")
    private int expiration_time;

    @Column(name = "[number_on_device]")
    private int numberOnDevice;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOnDevice() {
        return numberOnDevice;
    }

    public void setNumberOnDevice(int numberOnDevice) {
        this.numberOnDevice = numberOnDevice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getLast_change() {
        return last_change;
    }

    public void setLast_change(Date last_change) {
        this.last_change = last_change;
    }

    public int getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(int expiration_time) {
        this.expiration_time = expiration_time;
    }
}
