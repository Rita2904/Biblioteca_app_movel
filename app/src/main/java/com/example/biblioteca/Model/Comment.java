package com.example.biblioteca.Model;

public class Comment {

    private String id;
    private String idpost, text, date, author;

    public Comment(String id, String idpost, String text, String date, String author) {
        this.id = id;
        this.idpost = idpost;
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public Comment() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
