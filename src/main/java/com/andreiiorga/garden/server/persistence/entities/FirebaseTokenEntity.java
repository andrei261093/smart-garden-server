package com.andreiiorga.garden.server.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "firebase_token")
public class FirebaseTokenEntity {

    @Id
    @Column(name = "[token]")
    String token;

    @Column(name = "[user]")
    int idUser;

    public FirebaseTokenEntity(String token, int idUser) {
        this.token = token;
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
