package com.chaoweifish.companyweb.exception;
/*
* 权限验证失败的自定义异常
* */
public class AuthenticationFail extends RuntimeException {
    private String code;
    private String msg;
    public AuthenticationFail(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
