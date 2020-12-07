package com.example.pantapp.ui.avistamientos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pantapp.R;
import com.example.pantapp.api.Api;
import com.example.pantapp.api.RetrofitClient;
import com.example.pantapp.ui.home.HomeFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class avistamientoFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private Spinner zona, especie,visibilidad, ubicacion,comportaIni, comportaFin,compotamiento;
    private Button guardar, inmercion;
    private EditText et_distancia,et_tamaño,et_individuos;
    private Bitmap bitmap;
    private int request_code = 1;
    private ImageView foto;
    private ProgressDialog progreso;
    Api api;
    private AlertDialog dialog;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avistamiento, container, false);

        zona = (Spinner) view.findViewById(R.id.spZona);
        especie = (Spinner) view.findViewById(R.id.spEspecie);
        visibilidad = (Spinner) view.findViewById(R.id.spVisibilidad);
        ubicacion = (Spinner) view.findViewById(R.id.spUbicacion);
        guardar = (Button) view.findViewById(R.id.guardar);
        inmercion = (Button) view.findViewById(R.id.new_avistamiento);
        comportaIni = (Spinner) view.findViewById(R.id.spComportaIni);
        comportaFin = (Spinner) view.findViewById(R.id.spComportaFin);
        compotamiento = (Spinner) view.findViewById(R.id.spReaccion);
        et_distancia = (EditText) view.findViewById(R.id.cuadroDistancia);
        et_tamaño = (EditText) view.findViewById(R.id.cuadroTamaño);
        et_individuos = (EditText) view.findViewById(R.id.cuadroIndividuos);
        foto = (ImageButton) view.findViewById(R.id.agregar_foto);



        ArrayAdapter<CharSequence> compI=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.comportamiento_inicial,R.layout.spinner);
        compI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comportaIni.setAdapter(compI);

        ArrayAdapter<CharSequence> compF=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.comportamiento_final,R.layout.spinner);
        compF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comportaFin.setAdapter(compF);

        ArrayAdapter<CharSequence> ubi=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.ubicacion,R.layout.spinner);
        ubi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ubicacion.setAdapter(ubi);

        ArrayAdapter<CharSequence> vi=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.visibilidad,R.layout.spinner);
        vi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        visibilidad.setAdapter(vi);

        ArrayAdapter<CharSequence> comp=ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.reaccion,R.layout.spinner);
        comp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        compotamiento.setAdapter(comp);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Zonas,R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zona.setAdapter(adapter);
        zona.setOnItemSelectedListener(this);

        inmercion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avistamientoFragment fragment= new avistamientoFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarBoton();
            }
        });

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


    private boolean Varlidar(){
        boolean valid = true;

        String sTama = et_tamaño.getText().toString();
        String sIndivi = et_individuos.getText().toString();
        String sDista = et_distancia.getText().toString();


        /*
        if (sTama.isEmpty() || sTama.length() < 1) {
            tamaño.setError("Introduzca ");
            valid = false;
        } else {
            tamaño.setError(null);
        }*/
        if (sIndivi.isEmpty() || sIndivi.length() < 1) {
            et_individuos.setError("Introduzca un numero de individuos");
            valid = false;
        } else {
            et_individuos.setError(null);
        }
        if (sDista.isEmpty() || sDista.length() < 1) {
            et_distancia.setError("Introduzca una distancia del avistamiento");
            valid = false;
        } else {
            et_distancia.setError(null);
        }
        return valid;
    }

    private void validarBoton(){
        if (!Varlidar()) return;;

        /*
        String Individuos = et_individuos.getText().toString().trim();
        String Distancia = et_distancia.getText().toString().trim();
        String Tamaño = et_tamaño.getText().toString().trim();//convertir a string los spinners
        String sImagePhoto = convertirImgString(bitmap).trim();


        api = RetrofitClient.getClient().create(Api.class);
        Call<String> call = api.avistamiento(Individuos, Distancia, Tamaño, sImagePhoto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String resp = response.body();
                if (resp == "ok"){
                    Toast.makeText(getContext(),"El Informe esta correctamente guardado", Toast.LENGTH_LONG);//mirar rl otro proyecto
                } else {
                    Toast.makeText(getContext(),"El registro no se guardo", Toast.LENGTH_LONG);
                }
                dialog.dismiss();
                HomeFragment fragment= new HomeFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });*/

        Toast toast = Toast.makeText(getContext(), "Informe Guardada", Toast.LENGTH_SHORT);
        toast.show();
        HomeFragment fragment= new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK && requestCode == request_code){
            foto.setImageURI(data.getData());

            try{
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getBaseContext().getContentResolver(),data.getData());
                foto.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int[] num={R.array.Especies_paciico,R.array.Especies_caribe};
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity().getBaseContext(),num[position],R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        especie.setAdapter(adapter);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}