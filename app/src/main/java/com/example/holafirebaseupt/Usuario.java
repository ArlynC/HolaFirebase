package com.example.holafirebaseupt;

public class Usuario {
    private String id;
    private String usu;
    private String pass;

    public Usuario() {
    }

    public Usuario(String usu, String pass) {
        this.usu = usu;
        this.pass = pass;
    }

    public Usuario(String id, String usu, String pass) {
        this.id = id;
        this.usu = usu;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
