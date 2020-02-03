package com.chaoweifish.companyweb.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mydemo {
    @JsonProperty("ID")
    private long ID;
    private String title;
    private String pictureUrl;
    private String detail;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
