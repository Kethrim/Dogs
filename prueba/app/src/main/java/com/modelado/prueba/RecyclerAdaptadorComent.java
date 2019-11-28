package com.modelado.prueba;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdaptadorComent extends RecyclerView.Adapter<RecyclerAdaptadorComent.ListaComentarios>{
    int numComents;

    public RecyclerAdaptadorComent(int numComents) {
        this.numComents = numComents;
    }




    class ListaComentarios extends RecyclerView.ViewHolder{
        TextView comentario;

        public ListaComentarios(@NonNull View itemView) {
            super(itemView);
            comentario = itemView.findViewById(R.id.comentario);
        }

        void blind(int listaIndex){
            comentario.setText(String.valueOf(listaIndex));
        }

    }
}
