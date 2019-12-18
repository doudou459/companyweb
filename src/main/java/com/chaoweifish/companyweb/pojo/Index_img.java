package com.chaoweifish.companyweb.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Index_img {
    @Override
    public String toString() {
        return "{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    private long ID;
    private String title;
    private String url;
    @JsonProperty("ID")
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
