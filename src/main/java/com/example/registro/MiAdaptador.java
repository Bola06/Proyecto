package com.example.registro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MyViewHolder> {

    ArrayList<Comentario> comentarios;

    public MiAdaptador(ArrayList<Comentario> comentarios){
        this.comentarios = comentarios;
    }


    @NonNull
    @Override
    public MiAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda,parent,false);

        MyViewHolder mvh = new MyViewHolder(elemento);
        return  mvh;

    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MyViewHolder holder, int position) {
        Comentario c = this.comentarios.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView comentario;

        public MyViewHolder(View viewElemento){
            super(viewElemento);
            this.comentario = viewElemento.findViewById(R.id.tex);
        }
        public TextView getComentario(){
            return comentario;
        }
    }


}
