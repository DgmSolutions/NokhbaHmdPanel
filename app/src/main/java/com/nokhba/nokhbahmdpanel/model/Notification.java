package com.nokhba.nokhbahmdpanel.model;

public class Notification {
    private String to;
    private Data data;

    public Notification(String token, Data data) {
        this.to = token;
        this.data = data;
    }

    public String getToken() {
        return to;
    }

    public void setToken(String token) {
        this.to = token;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
