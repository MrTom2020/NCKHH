package com.example.nckh.model;

public class info_about_aqi_pm
{
    public int img;
    public String info;

    public info_about_aqi_pm(int img, String info) {
        this.img = img;
        this.info = info;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
