package com.example.nckh.model;

public class pm
{
    public String title;
    public String value_pm;
    public int img;
    public String description;
    public String description_pm;

    public pm(String title, String value_pm, int img, String description, String description_pm) {
        this.title = title;
        this.value_pm = value_pm;
        this.img = img;
        this.description = description;
        this.description_pm = description_pm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue_pm() {
        return value_pm;
    }

    public void setValue_pm(String value_pm) {
        this.value_pm = value_pm;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_pm() {
        return description_pm;
    }

    public void setDescription_pm(String description_pm) {
        this.description_pm = description_pm;
    }
}
