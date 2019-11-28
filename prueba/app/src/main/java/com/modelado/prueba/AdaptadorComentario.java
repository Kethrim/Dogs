package com.modelado.prueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class AdaptadorComentario extends RecyclerView.Adapter<viewHolderComment> {
    LinkedList<String> comentarios;

    public AdaptadorComentario(LinkedList<String> comentarios) {
        this.comentarios = comentarios;
    }

    @NonNull
    @Override


    public viewHolderComment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_coments, parent, false);
        return new viewHolderComment(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderComment holder, int position) {
        holder.comentario.setText(comentarios.get(position));
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
}
