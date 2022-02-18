package com.example.biblioteca.Model;

public class Book {

    private int id;
    private String title, author, theme, editor, isbn, image, pages;

    public Book(int id, String title, String author, String theme, String editor, String isbn, String image, String pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.theme = theme;
        this.editor = editor;
        this.isbn = isbn;
        this.image = image;
        this.pages = pages;
    }

    public Book() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
