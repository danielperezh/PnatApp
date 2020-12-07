package com.example.pantapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantapp.Model.Usuario_inmercion;
import com.example.pantapp.R;

import java.util.ArrayList;

public class adapterInmercion extends RecyclerView.Adapter<adapterInmercion.ViewHolder> implements View.OnClickListener {

    ArrayList<Usuario_inmercion> model;
    LayoutInflater inflater;

    private View.OnClickListener listener;


    public adapterInmercion(Context context, ArrayList<Usuario_inmercion> model) {
        this.inflater= LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.lista_inmerciones, parent, false);
       setOnClickListener(this);
       return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String lugar = model.get(position).getLugar();
        String avistamientos = model.get(position).getAvistamientos();//CAMBIAR A INT
        String fecha = model.get(position).getFecha();

        holder.lugar.setText(lugar);
        holder.fecha.setText(fecha);
        holder.numero_avistamiento.setText(avistamientos);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View v) {

        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lugar,fecha,numero_avistamiento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lugar= itemView.findViewById(R.id.lugar);
            fecha= itemView.findViewById(R.id.fecha);
            numero_avistamiento= itemView.findViewById(R.id.n_avistamiento);
        }
    }
}

