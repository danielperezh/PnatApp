package com.example.pantapp.ui.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pantapp.MainActivity;
import com.example.pantapp.Model.UserParcelable;
import com.example.pantapp.R;
import com.example.pantapp.ui.perfil.RegistroFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends AppCompatActivity {

    EditText et_correo;
    EditText et_contrasena;
    private ProgressDialog progreso;
    private RequestQueue requestQueue;
    StringRequest stringRequest;

    Button Registro;
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_loginn);

        et_contrasena=(EditText)findViewById(R.id.et_contraseña);
        et_correo=(EditText)findViewById(R.id.et_correo);
        requestQueue= Volley.newRequestQueue(this);

        Registro=(Button) findViewById(R.id.registro);
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), RegistroFragment.class);
                startActivityForResult(intent, 0);
            }
        });

        Login=(Button)findViewById(R.id.btn_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
    }
    private void iniciar() {

        if (!validar()) return;

        progreso = new ProgressDialog(this);
        progreso.setMessage("Iniciando...");
        progreso.show();

        Intent intent = new Intent(LoginFragment.this, MainActivity.class);
        startActivity(intent);
        finish();

        /*
        String url = "http://192.168.1.73:8086/Login/Login.php";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UserParcelable userParcelable = new UserParcelable();;
                Log.i("RESPUESTA JSON: ",""+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.names().get(0).equals("success")){
                        et_correo.setText("");
                        et_contrasena.setText("");
                        userParcelable.setId(jsonObject.getJSONArray("usuario").getJSONObject(0).getInt("id_usuario"));
                        userParcelable.setEmail(jsonObject.getJSONArray("usuario").getJSONObject(0).getString("correo"));
                        userParcelable.setNombre(jsonObject.getJSONArray("usuario").getJSONObject(0).getString("nombre_usuario"));
                        userParcelable.setImage(jsonObject.getJSONArray("usuario").getJSONObject(0).getString("url_foto"));

                        Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                        progreso.dismiss();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("DATA_USER",userParcelable);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                        Log.i("RESPUESTA JSON: ",""+jsonObject.getString("error"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progreso.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                progreso.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {//para enviar los datos mediante POST
                String sEmail = et_correo.getText().toString();
                String sPassword =  et_contrasena.getText().toString();

                Map<String,String> parametros = new HashMap<>();
                parametros.put("email",sEmail);
                parametros.put("password",sPassword);
                //estos parametros son enviados a nuestro web service

                return parametros;
            }
        };

        requestQueue.add(stringRequest);*/
    }

    private boolean validar() {
        boolean valid = true;

        String sEmail = et_correo.getText().toString();
        String sPassword = et_contrasena.getText().toString();

        if (sEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            et_correo.setError("Introduzca una dirección de correo electrónico válida");
            valid = false;
        } else {
            et_correo.setError(null);
        }

        if (sPassword.isEmpty() || et_contrasena.length() < 4 || et_contrasena.length() > 10) {
            et_contrasena.setError("Entre 4 y 10 caracteres alfanuméricos");
            valid = false;
        } else {
            et_contrasena.setError(null);
        }

        return valid;
    }

}