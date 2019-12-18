package com.chaoweifish.companyweb.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Carousel_img {
    @Override
    public String toString() {
        return "{" +
                "ID=" + ID +
                ", showType='" + showType + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    private long ID;
    private String showType;
    private String title;
    private String url;
    @JsonProperty("ID")
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
