package com.example.biblioteca.Model;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String morada;
    private String contacto;

    //construtor
    public User(int id, String username, String password, String email, String morada, String contacto) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.morada = morada;
        this.contacto = contacto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }


}
