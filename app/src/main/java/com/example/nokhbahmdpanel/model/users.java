package com.example.nokhbahmdpanel.model;

public class users {
    private String user,mdp;
    private int isActive;

    public users() {
    }

    public users(String user, String mdp, int isActive) {
        this.user = user;
        this.mdp = mdp;
        this.isActive = isActive;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
