package com.example.pantapp.api;


import com.example.pantapp.Model.Usuario;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    ////////////////////////////////////////////
    /***********   USUARIOS  ******************/
    ////////////////////////////////////////////
    @FormUrlEncoded
    @POST("login")
    Call<Usuario> login(
            @Field("usuario") String usuario,
            @Field("password") String password
    );

    ////////////////////////////////////////////
    ////////////   CLIENTES  ///////////////////
    ////////////////////////////////////////////
    /*
    @GET("clientes")
    Call<List<Registro>> clientes();*/

    @FormUrlEncoded
    @POST("Registro")
    Call<String> Registros(
            @Field("nombre") String nombre,
            @Field("nacionalidad") String nacionalidad,
            @Field("contraseña") String contraseña,
            @Field("correo") String correo,
            @Field("foto") String foto
    );

    @FormUrlEncoded
    @POST("Inmercion")
    Call<String> inmercion(
            @Field("longitud") String longitud,
            @Field("latitud") String latitud,
            @Field("profundidad") String profundidad,
            @Field("temperatura") String temperatura,
            @Field("duracion") String duracion,
            @Field("observaciones") String observaciones
    );

    @FormUrlEncoded
    @POST("Avistamiento")
    Call<String> avistamiento(
            @Field("tamaño") String longitud,
            @Field("n_individuos") String latitud,
            @Field("distancia") String profundidad,
            @Field("foto") String temperatura

    );

    @DELETE("clientes/{idCliente}")
    Call<String> eliminarCliente(
            @Path("idCliente") int idCliente
    );

    @FormUrlEncoded
    @PUT("clientes/{idCliente}")
    Call<String> modificarCliente(
            @Field("idCliente") int idCliente,
            @Field("nombre") String nombre,
            @Field("dirección") String direccion,
            @Field("telefono") String telefono,
            @Field("correo") String correo,
            @Field("puntos") int puntos,
            @Field("estado") int estado,
            @Field("comentarios") String comentarios
    );

}
