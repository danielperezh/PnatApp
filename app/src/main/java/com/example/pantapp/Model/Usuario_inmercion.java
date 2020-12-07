package com.example.pantapp.Model;

import java.io.Serializable;

public class Usuario_inmercion implements Serializable {
    private String lugar;
    private String fecha;
    private String avistamientos; //cambiar a int
    private int imagenId;


    public Usuario_inmercion(String lugar, String fecha, String avistamientos) {
        this.lugar = lugar;
        this.fecha = fecha;
        this.avistamientos = avistamientos;
        this.imagenId = imagenId;
    }

    public Usuario_inmercion() {
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAvistamientos() {
        return avistamientos;
    }

    public void setAvistamientos(String avistamientos) {
        this.avistamientos = avistamientos;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
