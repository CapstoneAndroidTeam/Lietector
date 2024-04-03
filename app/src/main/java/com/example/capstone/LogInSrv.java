package com.example.capstone;


public class LogInSrv {
    private Character code;
    private Character msg;

    public LogInSrv(Character code, Character msg) {
        this.code = code;
        this.msg = msg;
    }

    public Character getCode() {
        return code;
    }


    public Character getMsg() {
        return msg;
    }

}

