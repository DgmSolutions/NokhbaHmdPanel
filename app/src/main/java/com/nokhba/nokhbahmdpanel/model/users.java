package com.nokhba.nokhbahmdpanel.model;

public class users {
    private String user,mdp,Token;
    private int isActive;

    public users() {
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
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
