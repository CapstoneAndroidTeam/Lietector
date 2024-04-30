package com.example.capstone.QnA;

public class QnAPost {
    private String title;
    private String content;
    private Integer writer;
    public QnAPost (String title, String content, Integer writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }
    public  String getContent() {
        return  content;
    }
    public Integer getWriter() {
        return writer;
    }
}
