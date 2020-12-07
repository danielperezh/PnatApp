package com.example.squaluss.ui.perfil;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {



    private static final String REGUISTRO_REQUEST_URL="http://192.168.1.73/Login/Register.php";
    private Map<String, String> params;
    public RegistroRequest(String nombre, String correo, String nacionalidad, String contrasena,String foto,  Response.Listener<String> listener){
        super(Method.POST, REGUISTRO_REQUEST_URL,listener, null);
        params= new HashMap<>();
        params.put("nombre", nombre);
        params.put("correo", correo);
        params.put("nacionalidad", nacionalidad);;
        params.put("foto",foto);
        params.put("contraseña", contrasena);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
