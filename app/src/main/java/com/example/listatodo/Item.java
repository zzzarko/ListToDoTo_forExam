package com.example.listatodo;

public class Item {

    private String naziv, date, time;
    private  boolean status;

    public String getNaziv() {
        return naziv;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
