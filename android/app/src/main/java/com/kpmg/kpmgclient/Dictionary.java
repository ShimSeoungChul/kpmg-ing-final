package com.kpmg.kpmgclient;

public class Dictionary {

    private String date;
    private String type;
    private String point;

    public Dictionary(String date, String type, String point) {
        this.date = date;
        this.type = type;
        this.point = point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}