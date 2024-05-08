package com.example.capstone.Chatbot;

import com.google.gson.annotations.SerializedName;

public class Message {
   @SerializedName("answer_f5")
    String answer_f5;

   public String getAnswer () {return answer_f5;}
    private String message;
    private boolean sentByUser;

    public Message(String message, boolean sentByUser) {
        this.message = message;
        this.sentByUser = sentByUser;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSentByUser() {
        return sentByUser;
    }

}