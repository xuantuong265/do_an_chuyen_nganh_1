package com.example.yo7a.healthwatcher.Models;

import java.util.List;

public class SubItemHistory{
    private String id;
    private String idUser;
    private String number;
    private String type;
    private String time;

    public SubItemHistory(String id, String idUser, String number, String type, String time) {
        this.id = id;
        this.idUser = idUser;
        this.number = number;
        this.type = type;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
