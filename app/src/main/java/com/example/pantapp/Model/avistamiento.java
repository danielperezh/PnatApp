package com.example.pantapp.Model;

public class avistamiento {

    private int id;
    private String Foto;
    private String Descripcion;
    //falta el id de especie
    private String Visibilidad;
    private float Profundidad;
    private String ComportamientoIni;
    private String ComportamientoFin;

    public avistamiento(int id, String foto, String descripcion, String visibilidad, float profundidad, String comportamientoIni, String comportamientoFin) {
        this.id = id;
        Foto = foto;
        Descripcion = descripcion;
        Visibilidad = visibilidad;
        Profundidad = profundidad;
        ComportamientoIni = comportamientoIni;
        ComportamientoFin = comportamientoFin;
    }

    public avistamiento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getVisibilidad() {
        return Visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        Visibilidad = visibilidad;
    }

    public float getProfundidad() {
        return Profundidad;
    }

    public void setProfundidad(float profundidad) {
        Profundidad = profundidad;
    }

    public String getComportamientoIni() {
        return ComportamientoIni;
    }

    public void setComportamientoIni(String comportamientoIni) {
        ComportamientoIni = comportamientoIni;
    }

    public String getComportamientoFin() {
        return ComportamientoFin;
    }

    public void setComportamientoFin(String comportamientoFin) {
        ComportamientoFin = comportamientoFin;
    }
}
