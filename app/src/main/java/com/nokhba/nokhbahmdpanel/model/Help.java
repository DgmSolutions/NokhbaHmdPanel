package com.nokhba.nokhbahmdpanel.model;

import java.io.Serializable;
import java.util.Map;

public class Help implements Serializable {
    private String nom,prenom,phone,covid_you,Fcovid,service,desc,date,token;
    private int numCovide;
    private Map<String,Double> localisation;

    public Help() {
    }

    public Help(String nom, String prenom, String phone, String covid_you, String fcovid, String service, String desc, String date, int numCovide, Map<String, Double> localisation,String to) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.covid_you = covid_you;
        Fcovid = fcovid;
        this.service = service;
        this.desc = desc;
        this.date = date;
        this.numCovide = numCovide;
        this.localisation = localisation;
        this.token=to;
    }
    //for recycaleView

    public Help(String nom, String prenom, String desc, String date) {
        this.nom = nom;
        this.prenom = prenom;
        this.desc = desc;
        this.date = date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCovid_you() {
        return covid_you;
    }

    public void setCovid_you(String covid_you) {
        this.covid_you = covid_you;
    }

    public String getFcovid() {
        return Fcovid;
    }

    public void setFcovid(String fcovid) {
        Fcovid = fcovid;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumCovide() {
        return numCovide;
    }

    public void setNumCovide(int numCovide) {
        this.numCovide = numCovide;
    }

    public Map<String, Double> getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Map<String, Double> localisation) {
        this.localisation = localisation;
    }
}
