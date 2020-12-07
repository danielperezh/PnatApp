package com.example.squaluss.ui.perfil;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.squaluss.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class RegistroFragment extends Fragment implements View.OnClickListener {

    ImageView foto;
    EditText nombre, correo, nacionalidad, contrasena;
    Button agregar;
    private Bitmap bitmap;
    private int request_code = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registro, container, false);


        foto = (ImageView) view.findViewById(R.id.Foto_registro);
        nombre = (EditText) view.findViewById(R.id.nombreRegistro);
        correo = (EditText) view.findViewById(R.id.correoRegistro);
        nacionalidad = (EditText) view.findViewById(R.id.nacionalidadRegistro);
        contrasena = (EditText) view.findViewById(R.id.contraseñaRegistro);
        agregar = (Button) view.findViewById(R.id.btn_acceder);

        agregar.setOnClickListener(this);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                //verificacion de la version de plataforma
                if(Build.VERSION.SDK_INT < 19){
                    //android 4.3  y anteriores
                    i = new Intent();
                    i.setAction(Intent.ACTION_GET_CONTENT);
                }else {
                    //android 4.4 y superior
                    i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                }
                i.setType("image/*");
                startActivityForResult(i, request_code);
            }
        });


        return view;
    }

    @Override
    public void onClick(final View v) {
        final String name= nombre.getText().toString();
        final String correi= correo.getText().toString();
        final String nacionalid= nacionalidad.getText().toString();
        final String sImagePhoto = convertirImgString(bitmap);
        final String contrasenaa= contrasena.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success= jsonObject.getBoolean("success");

                    if (success){
                        // Crear fragmento de tu clase
                        Fragment fragment = new RegistroFragment();
                        // Obtener el administrador de fragmentos a través de la actividad
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        // Definir una transacción
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        // Remplazar el contenido principal por el fragmento
                        fragmentTransaction.replace(R.id.nav_inicio, fragment);
                        fragmentTransaction.addToBackStack(null);
                        // Cambiar
                        fragmentTransaction.commit();

                    }else {
                        AlertDialog.Builder builder= new AlertDialog.Builder(v.getContext()); //puede que no srive el contexto de getcontext
                        builder.setMessage("Error registro").setNegativeButton("Retry", null).create().show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        RegistroRequest registroRequest= new RegistroRequest(name,  correi,  nacionalid,  contrasenaa, sImagePhoto,  respoListener);
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(registroRequest);
    }
    private String convertirImgString(Bitmap bitmap) {

        String imagenString;
        ByteArrayOutputStream array=new ByteArrayOutputStream();
        if(bitmap!=null){
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
            byte[] imagenByte=array.toByteArray();
            imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);
        }else{
            imagenString = "no imagen"; //se enviara este string en caso de no haber imagen
        }

        return imagenString;
    }

    private boolean validar() {
        boolean valid = true;

        String sPassword = contrasena.getText().toString();
        String sEmail = correo.getText().toString();


        if (sEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            correo.setError("Dirección de correo electrónico no válida");
            valid = false;
        } else {
            correo.setError(null);
        }

        if (sPassword.isEmpty() || contrasena.length() < 4 || contrasena.length() > 10) {
            contrasena.setError("Ingrese entre 4 a 10 caracteres alfanuméricos");
            valid = false;
        } else {
            contrasena.setError(null);
        }

        return valid;
    }

}
