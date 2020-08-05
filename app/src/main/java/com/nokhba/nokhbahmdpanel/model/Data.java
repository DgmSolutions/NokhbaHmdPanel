package com.nokhba.nokhbahmdpanel.model;

public class Data {
    private String  title,body,phone,date;

    public Data(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Data(String title, String body, String phone,String date) {
        this.title= title;
        this.body = body;
        this.phone = phone;
        this.date=date;
    }


    public Data() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
