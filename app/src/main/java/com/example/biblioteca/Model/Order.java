package com.example.biblioteca.Model;

public class Order {

    private String id;
    private String description, name, email, book, date, deadline = "2 meses";

    public Order(String id, String book, String description, String name, String email, String date, String deadline) {
        this.id = id;
        this.book = book;
        this.description = description;
        this.name = name;
        this.email = email;
        this.date = date;
        this.deadline = deadline;
    }

    public Order() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
