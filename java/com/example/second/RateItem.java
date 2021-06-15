package com.example.second;

public class RateItem {
    private int id;
    private String curname;
    private String currate;

    public RateItem(String text, String text1) {
    }

    public RateItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurname() {
        return curname;
    }
    public void setCurname(String curname){
        this.curname=curname;
    }

    public String getCurrate() {
        return currate;
    }
    public void setCurrate(String currate){
        this.currate=currate;
    }
}
