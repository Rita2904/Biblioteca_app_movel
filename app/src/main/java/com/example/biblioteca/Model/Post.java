package com.example.biblioteca.Model;

public class Post {

    private int comment;
    private String id, title, text, image, date, author;

    public Post(String id, int comment, String title, String text, String image, String date, String author) {
        this.id = id;
        this.comment = comment;
        this.title = title;
        this.text = text;
        this.image = image;
        this.date = date;
        this.author = author;
    }

    public Post() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
