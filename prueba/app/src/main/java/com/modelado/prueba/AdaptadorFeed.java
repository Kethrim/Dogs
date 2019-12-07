
package com.modelado.prueba;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * El adaptador le pasa los datos al recycler view, pone las imágenes, etc
 */
public class AdaptadorFeed extends RecyclerView.Adapter<viewHolderFeed> {
    private ArrayList<Perro> perros;
    private String llaveUsuario;

    /**
     * Crea un adaptador para el feed.
     *
     * @param perros- lista de perros que se mostrará en el feed.
     */
    public AdaptadorFeed(ArrayList<Perro> perros, String llaveUsuario) {
        this.perros = perros;
        this.llaveUsuario = llaveUsuario;
    }

    @NonNull
    @Override
    //Se crea la vista de cada card item (tarjeta) usando el viewHolderFeed
    public viewHolderFeed onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

        return new viewHolderFeed(vista, perros, llaveUsuario);
    }

    @SuppressLint("SetTextI18n")
    @Override
    //Se asignan los valores de cada card item (tarjeta)
    public void onBindViewHolder(@NonNull final viewHolderFeed holder, int position) {
        holder.getNombrePerro().setText(perros.get(position).getNombrePerro());

        holder.getImagenPerro().setImageBitmap(
                perros.get(position).descargaImg());

        holder.getNumMeGusta().setText(perros.get(position).getNumMeGusta() + " me gusta");

        holder.getIdPerro().setText(String.valueOf(perros.get(position).getIdPerro()));

    }

    @Override
    //Número de datos que cargará el feed, en este caso será el número de elementos de la lista.
    public int getItemCount() {
        return perros.size();
    }

}
