package com.chaoweifish.companyweb.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin {

    private  long ID;
    private String loginID;
    private  String loginKey;
    private long loginTime;
    private int wrongTime;
    private int role;
    private long lastLoginTime;

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    @JsonProperty("ID")
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public int getWrongTime() {
        return wrongTime;
    }

    public void setWrongTime(int wrongTime) {
        this.wrongTime = wrongTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "admin{" +
                "ID=" + ID +
                ", loginID='" + loginID + '\'' +
                ", loginKey='" + loginKey + '\'' +
                ", loginTime=" + loginTime +
                ", wrongTime=" + wrongTime +
                ", role=" + role +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}


