package com.example.pantapp.ui.inmercionn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pantapp.R;
import com.example.pantapp.api.Api;
import com.example.pantapp.api.RetrofitClient;
import com.example.pantapp.ui.avistamientos.avistamientoFragment;
import com.example.pantapp.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inmercionFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner lugar, lugarEsp, visibilidad;
    private Button avistamiento, guardar;
    private EditText et_logitud,et_latitud,et_duracion,et_temperatura,et_profundidad,et_obervaciones;
    private TextView horat,fechat;
    private AlertDialog dialog;

    Api api;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inmercion_fragment, container, false);

        lugar = (Spinner) view.findViewById(R.id.spLugar);
        lugarEsp = (Spinner) view.findViewById(R.id.spLugarEspecifico);
        visibilidad = (Spinner) view.findViewById(R.id.spVisibilidad2);
        avistamiento = (Button) view.findViewById(R.id.new_avistamiento);
        et_logitud = (EditText) view.findViewById(R.id.cuadroLongi);
        et_latitud = (EditText)  view.findViewById(R.id.cuadroLati);
        et_temperatura = (EditText) view.findViewById(R.id.cuadroTemperatura);
        et_duracion = (EditText) view.findViewById(R.id.cuadroDuracion);
        et_profundidad = (EditText) view.findViewById(R.id.cuadroProfundidad);
        guardar = (Button) view.findViewById(R.id.btn_guardar_inmercion);
        et_obervaciones = (EditText) view.findViewById(R.id.observaciones);

        Date date=new Date();
        fechat=view.findViewById(R.id.txtFecha);
        SimpleDateFormat fecha=new SimpleDateFormat("d , MMMM 'del', yy");
        String sFecha=fecha.format(date);
        fechat.setText(sFecha);

        //hora
        horat=view.findViewById(R.id.txtHora);
        SimpleDateFormat hora=new SimpleDateFormat("h:mm a");
        String sHora=hora.format(date);
        horat.setText(sHora);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.visibilidad,R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        visibilidad.setAdapter(adapter);

        ArrayAdapter<CharSequence> luga= ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.Lugares,R.layout.spinner);
        luga.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lugar.setAdapter(luga);
        lugar.setOnItemSelectedListener(this);
        //lugar.setSelection(0, true); View v = lugar.getSelectedView(); ((TextView)v).setTextColor(sim);

        LocationManager mgr = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                et_logitud.setText(""+location.getLongitude());
                et_latitud.setText(""+location.getLatitude());
            }
        };

        avistamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avistamientoFragment fragment= new avistamientoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,fragment);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarBoton();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int[] numeros ={R.array.lugares_gorgo, R.array.lugar_malpe, R.array.lugar_nuqui,R.array.lugar_Bahia,R.array.lugar_capur,R.array.lugar_isla,R.array.lugar_santa,R.array.lugar_cartagena,R.array.lugar_Bernardo,R.array.lugar_andres,R.array.lugar_providencia};
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), numeros[position], R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lugarEsp.setAdapter(adapter);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean Varlidar(){
        boolean valid = true;

        String sLongi = et_logitud.getText().toString();
        String sLati = et_latitud.getText().toString();
        String sDura = et_duracion.getText().toString();
        String sTem = et_temperatura.getText().toString();
        String sProf = et_profundidad.getText().toString();


        if (sLongi.isEmpty() || sLongi.length() < 1) {
            et_logitud.setError("Introduzca una Longitud");
            valid = false;
        } else {
            et_logitud.setError(null);
        }
        if (sLati.isEmpty() || sLati.length() < 1) {
            et_latitud.setError("Introduzca una Latitud");
            valid = false;
        } else {
            et_latitud.setError(null);
        }
        if (sDura.isEmpty() || sDura.length() < 1) {
            et_duracion.setError("Introduzca una Duracion de la inmersion");
            valid = false;
        } else {
            et_duracion.setError(null);
        }
        if (sTem.isEmpty() || sTem.length() < 1) {
            et_temperatura.setError("Introduzca una Temperatura");
            valid = false;
        } else {
            et_temperatura.setError(null);
        }
        if (sProf.isEmpty() || sProf.length() < 1) {
            et_profundidad.setError("Introduzca la Profundidad Maxima");
            valid = false;
        } else {
            et_profundidad.setError(null);
        }
        return valid;

    }

    private void validarBoton(){
        if (!Varlidar()) return;;

        String Longitud = et_logitud.getText().toString().trim();
        String Latitud = et_latitud.getText().toString().trim();
        String Duracion = et_duracion.getText().toString().trim();
        String Temperatura = et_temperatura.getText().toString().trim();
        String Profundidad = et_profundidad.getText().toString().trim();
        String Observaciones = et_obervaciones.getText().toString().trim();

        api = RetrofitClient.getClient().create(Api.class);
        Call<String> call = api.inmercion(Longitud,Latitud,Duracion,Temperatura,Profundidad,Observaciones);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String resp = response.body();
                if (resp == "ok"){
                    Toast.makeText(getContext(),"Inmersion Guardada", Toast.LENGTH_LONG);//mirar rl otro proyecto
                } else {
                    Toast.makeText(getContext(),"La inmersion no se guardo", Toast.LENGTH_LONG);
                }
                dialog.dismiss();
                HomeFragment fragment= new HomeFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        Toast toast = Toast.makeText(getContext(), "Inmersion Guardada", Toast.LENGTH_SHORT);
        toast.show();
        HomeFragment fragment= new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
    }
}