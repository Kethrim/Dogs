package com.modelado.prueba;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecyclerAdaptador extends RecyclerView.Adapter<viewHolder> {

    LinkedList<perro> perros;

    public RecyclerAdaptador(LinkedList<perro> perros) {
        this.perros = perros;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new viewHolder(vista, perros);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.nombrePerro.setText(perros.get(position).nombrePerro);
        holder.imagenPerro.setImageResource(perros.get(position).imagenPerro);
        holder.numMeGusta.setText(Integer.toString(perros.get(position).numMeGusta)+" me gusta");
    }

    @Override
    public int getItemCount() {
        return perros.size();
    }
}
