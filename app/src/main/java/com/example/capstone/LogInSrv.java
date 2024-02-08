package com.example.capstone;


public class LogInSrv {
    private String code;
    private String msg;

    public LogInSrv(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}

