package com.modelado.prueba;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;

/** Indica qué información se mostrará en el card item para el feed.*/
public class viewHolderFeed extends RecyclerView.ViewHolder  implements View.OnClickListener{
    private Button meGusta;
    private ImageView imagenPerro;
    private TextView nombrePerro;
    private TextView numMeGusta;
    private ArrayList<Perro> perros;

    /**Crea un viewHolderFeed
     * @param itemView-vista.
     * @param datos- lista de datos de perros que se mostrará.*/
    public viewHolderFeed(@NonNull View itemView, ArrayList<Perro> datos) {
        super(itemView);
        meGusta = itemView.findViewById(R.id.meGusta);
        imagenPerro = itemView.findViewById(R.id.imagenPerro);
        nombrePerro = itemView.findViewById(R.id.nombrePerro);
        numMeGusta = itemView.findViewById(R.id.numMeGusta);
        perros = datos;
        meGusta.setOnClickListener(this); //al dar click se aumenta un me gusta.
    }

    /**Obtiene la imagen de un perro.
     * @return ImageView donde se coloca la imagen. */
    public ImageView getImagenPerro() {
        return imagenPerro;
    }

    /**Obtiene el nombre de un perro.
     * @return TextView dónde se pone el nombre.*/
    public TextView getNombrePerro() {
        return nombrePerro;
    }

    /**Obtiene el número de me gustas del perro.
     * @return TextView en el formato "x me gusta", con x el número de me gustas.*/
    public TextView getNumMeGusta() {
        return numMeGusta;
    }

    /**Obtiene la lista de Perros que se mostrará en el feed.
     * @return lista de los perros.*/
    public ArrayList<Perro> getPerros() {
        return perros;
    }

    @Override
    public void onClick(View v) {
        Perro objeto = perros.get(getAdapterPosition());
        //Cambiar para hacer la petición de agregar el me gusta, pedir el número de me gustas.
        objeto.setNumMeGusta(objeto.getNumMeGusta()+1);
        numMeGusta.setText(objeto.getNumMeGusta()+ " me gusta");
//        meGusta.setEnabled(false);
    }
}
