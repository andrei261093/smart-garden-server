package com.andreiiorga.garden.server.beans;

public class HeartbeatMessage {
    int idDevice;

    public HeartbeatMessage(int idDevice) {
        this.idDevice = idDevice;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }
}

