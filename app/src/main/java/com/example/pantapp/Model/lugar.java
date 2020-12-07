package com.example.pantapp.Model;

public class lugar {

    private int id;
    private String tipo_lugar;
    private String Nombre;

    public lugar(int id, String tipo_lugar, String nombre) {
        this.id = id;
        this.tipo_lugar = tipo_lugar;
        Nombre = nombre;
    }

    public lugar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_lugar() {
        return tipo_lugar;
    }

    public void setTipo_lugar(String tipo_lugar) {
        this.tipo_lugar = tipo_lugar;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
