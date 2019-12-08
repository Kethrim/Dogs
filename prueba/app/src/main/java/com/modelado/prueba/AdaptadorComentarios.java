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

    public AdaptadorComentarios(Context contexto, ArrayList<String> comentarios) {
        this.mInflater = LayoutInflater.from(contexto);
        this.comentarios = comentarios;
        System.out.println("En el adaptador la lista mide "+this.comentarios.size());
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderComentarios holder, int position) {
        holder.comentario.setText(this.comentarios.get(position));
    }

    @NonNull
    @Override
    public viewHolderComentarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.comentario_item, parent, false);
        return new viewHolderComentarios(view);

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
