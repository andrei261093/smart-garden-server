package com.andreiiorga.garden.server.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "[id]")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "[firstName]")
    private String firstName;

    @Column(name = "[lastName]")
    private String lastName;

    @Column(name = "[address]")
    private String address;

    @Column(name = "[description]")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
