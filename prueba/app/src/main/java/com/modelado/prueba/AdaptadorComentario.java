package com.modelado.prueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

/* Creamos un adaptador para poder mostrar los comentarios.*/
public class AdaptadorComentario extends RecyclerView.Adapter<viewHolderComment> {
    List<Integer> listaId;
    List<List<String>> listaDeListasComentarios;


    public AdaptadorComentario(List<Integer> listaId, List<List<String>> listaDeListasComentarios) {
        this.listaId = listaId;
        this.listaDeListasComentarios = listaDeListasComentarios;
    }

    @NonNull
    @Override
    public viewHolderComment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_coments, parent, false);
        return new viewHolderComment(view, listaDeListasComentarios, listaId);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderComment holder, int position) {
        System.out.println(holder.toString());
        holder.listaDeComentarios.setText(listaId.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
