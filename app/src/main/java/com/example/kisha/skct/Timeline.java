package com.example.kisha.skct;

/**
 * Created by kisha on 23-02-2017.
 */

class Timeline
{
    private String name, picUrl, profUrl;

    Timeline()
    {

    }
    Timeline(String name, String picUrl, String profUrl)
    {
        this.name = name;
        this.picUrl = picUrl;
        this.profUrl = profUrl;
    }

    public String getName() {
        return name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getProfUrl() {
        return profUrl;
    }
    public void setProfUrl(String profUrl) {
        this.profUrl = profUrl;
    }
}
