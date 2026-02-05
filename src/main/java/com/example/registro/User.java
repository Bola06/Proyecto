package com.example.registro;

public class User {
    int id;
    String nombreUsuario;
    String correo;
    String contrasena;

    public User() {

    }

    public User(String nombre,String contrasena){
        super();

        this.nombreUsuario = nombre;

        this.contrasena = contrasena;
    }

    public User(int id,String nombre,String correo,String contrasena){
        super();
        this.id = id;
        this.nombreUsuario = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombreUsuario;
    }

    public void setNombre(String nombre) {
        this.nombreUsuario = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;

    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}