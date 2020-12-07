package com.example.pantapp.Model;

public class especies {

    private int id;
    private String Nombre;
    private String Descripcion;
    private String TamañoMax;

    public especies() {
    }

    public especies(int id, String nombre, String descripcion, String tamañoMax) {
        this.id = id;
        Nombre = nombre;
        Descripcion = descripcion;
        TamañoMax = tamañoMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTamañoMax() {
        return TamañoMax;
    }

    public void setTamañoMax(String tamañoMax) {
        TamañoMax = tamañoMax;
    }
}
