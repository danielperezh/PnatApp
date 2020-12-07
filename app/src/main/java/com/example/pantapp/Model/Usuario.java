package com.example.pantapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private int id;
    private String Nombre;
    private String Correo;
    private String TipoUsuario;
    private String Nacionalidad;
    private String Foto;
    private String Contraseña;

    public Usuario(int id, String nombre, String correo, String tipoUsuario, String nacionalidad, String foto, String contraseña) {
        this.id = id;
        Nombre = nombre;
        Correo = correo;
        TipoUsuario = tipoUsuario;
        Nacionalidad = nacionalidad;
        Foto = foto;
        Contraseña = contraseña;
    }

    public Usuario() {
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

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        TipoUsuario = tipoUsuario;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    protected Usuario(Parcel in) {
        id = in.readInt();
        Correo = in.readString();
        Contraseña = in.readString();
        Nombre = in.readString();
        Foto = in.readString();
        Nacionalidad = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(Correo);
        dest.writeString(Contraseña);
        dest.writeString(Nombre);
        dest.writeString(Foto);
        dest.writeString(Nacionalidad);
    }

    @SuppressWarnings("unused")
    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size ){
            return new Usuario[size];
        }
    };
}
