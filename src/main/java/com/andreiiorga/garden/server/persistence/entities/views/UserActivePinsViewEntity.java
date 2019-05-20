package com.andreiiorga.garden.server.persistence.entities.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Immutable
@Table(name = "user_active_pins")
public class UserActivePinsViewEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    @Column(name = "description")
    String description;

    @Column(name = "expiration_time")
    int expirationTime;

    @Column(name = "last_change")
    Date last_change;

    @Column(name = "number_on_device")
    int numberOnDevice;

    @Column(name = "state")
    boolean state;

    @Column(name = "device")
    int device;

    @Column(name = "end_date")
    Date endDate;

    @Column(name = "device_id")
    int deviceId;

    @Column(name = "user_id")
    int userId;

    @Column(name = "device_description")
    String deviceDescription;

    @Column(name = "device_name")
    String deviceName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getLast_change() {
        return last_change;
    }

    public void setLast_change(Date last_change) {
        this.last_change = last_change;
    }

    public int getNumberOnDevice() {
        return numberOnDevice;
    }

    public void setNumberOnDevice(int numberOnDevice) {
        this.numberOnDevice = numberOnDevice;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}

