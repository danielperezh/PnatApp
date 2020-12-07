package com.example.pantapp.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantapp.Adaptadores.adapterInmercion;
import com.example.pantapp.Model.Usuario_inmercion;
import com.example.pantapp.R;
import com.example.pantapp.iComunicaFragments;
import com.example.pantapp.ui.avistamientos.avistamientoFragment;
import com.example.pantapp.ui.inmercionn.inmercionFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    com.example.pantapp.Adaptadores.adapterInmercion AdapterInmercion;
    RecyclerView recyclerViewinmerciones;
    ArrayList<Usuario_inmercion> listainmerciones;
    FloatingActionButton btn_flotante;


    //referencias  para comunicar fragments
    Activity activity;
    iComunicaFragments interfasComunica;;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewinmerciones = view.findViewById(R.id.listaInmerciones);
        listainmerciones =new ArrayList<Usuario_inmercion>();
        cargarlista();
        mostarDato();

        btn_flotante = (FloatingActionButton)view.findViewById(R.id.btn_agregar_inmercion);
        btn_flotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inmercionFragment fragment= new inmercionFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
    public void cargarlista(){
        listainmerciones.add(new Usuario_inmercion("Santa Marta","21/11/2020","3"));
        listainmerciones.add(new Usuario_inmercion("Malpelo","25/11/2020","1"));
        listainmerciones.add(new Usuario_inmercion("Providencia","28/11/2020","2"));
        listainmerciones.add(new Usuario_inmercion("Gorgona","10/11/2020","5"));
        listainmerciones.add(new Usuario_inmercion("Malpelo","02/12/2020","0"));

    }

    public  void  mostarDato(){
        recyclerViewinmerciones.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterInmercion= new adapterInmercion(getContext(), listainmerciones);
        recyclerViewinmerciones.setAdapter(AdapterInmercion);

        AdapterInmercion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfasComunica.enviarPersona(listainmerciones.get(recyclerViewinmerciones.getChildAdapterPosition(v)));// toca enviar toda la info no solo una parte
            }
        });


    }
}