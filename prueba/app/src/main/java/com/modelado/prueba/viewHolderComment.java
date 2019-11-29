package com.modelado.prueba;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class viewHolderComment extends RecyclerView.ViewHolder {
    TextView listaDeComentarios;
    List<List<String>> listaDeListasComentarios;
    List<Integer> listaId;


    public viewHolderComment(@NonNull View itemView, List<List<String>> listaDeListasComentarios, List<Integer> listaId) {
        super(itemView);
        listaDeComentarios = itemView.findViewById(R.id.listaComentarios);
        this.listaDeListasComentarios = listaDeListasComentarios;
        this.listaId = listaId;
    }
}
