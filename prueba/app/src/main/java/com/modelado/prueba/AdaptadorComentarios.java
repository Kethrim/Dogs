package com.modelado.prueba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorComentarios extends RecyclerView.Adapter<AdaptadorComentarios.viewHolderComentarios> {
    private Context contexto;
    private LayoutInflater mInflater;
    private ArrayList<String> comentarios;

    /**
     * Crea un adaptador para mostrar todos los comentarios
     * @param comentarios
     */
    public AdaptadorComentarios( ArrayList<String> comentarios) {
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public viewHolderComentarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comentario_item, parent, false);
        return new viewHolderComentarios(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderComentarios holder, int position) {
        String dato = this.comentarios.get(position);
        holder.comentario.setText(dato);
    }

    @Override
    public int getItemCount() {
        return this.comentarios.size();
    }

    /**
     * Indica la información que se mostrará en cada comentario.
     */
    public class viewHolderComentarios extends RecyclerView.ViewHolder{
        TextView comentario;

        public viewHolderComentarios(@NonNull View itemView) {
            super(itemView);
            comentario = itemView.findViewById(R.id.comentario);
        }
    }
}
