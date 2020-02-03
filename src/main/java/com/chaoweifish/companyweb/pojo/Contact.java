package com.chaoweifish.companyweb.pojo;

public class Contact {
    private String id;
    private String detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
