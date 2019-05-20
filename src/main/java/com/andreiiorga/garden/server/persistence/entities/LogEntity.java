package com.andreiiorga.garden.server.persistence.entities;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log")
public class LogEntity {

    @Id
    @Column(name = "[id]")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private PinEntity pinEntity;

    @Column(name = "[newState]")
    private boolean newState;

    @Column(name = "[sysDate]")
    private Date sysDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PinEntity getPinEntity() {
        return pinEntity;
    }

    public void setPinEntity(PinEntity pinEntity) {
        this.pinEntity = pinEntity;
    }

    public boolean isNewState() {
        return newState;
    }

    public void setNewState(boolean newState) {
        this.newState = newState;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }
}
