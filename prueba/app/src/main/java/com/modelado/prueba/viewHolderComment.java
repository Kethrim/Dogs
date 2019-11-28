package com.modelado.prueba;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolderComment extends RecyclerView.ViewHolder {
    TextView comentario;

    public viewHolderComment(@NonNull View itemView) {
        super(itemView);
        comentario = itemView.findViewById(R.id.comentario);
    }
}
