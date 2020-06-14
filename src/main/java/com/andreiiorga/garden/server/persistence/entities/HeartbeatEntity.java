package com.andreiiorga.garden.server.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "heartbeat")
public class HeartbeatEntity {

    @Id
    @Column(name = "[device]")
    int idDevice;

    @Column(name = "[lastHeartbeat]")
    Date lastHeartbeat;

    public HeartbeatEntity() {
    }

    public HeartbeatEntity(int idDevice, Date lastHeartbeat) {
        this.idDevice = idDevice;
        this.lastHeartbeat = lastHeartbeat;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public Date getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(Date lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }
}
