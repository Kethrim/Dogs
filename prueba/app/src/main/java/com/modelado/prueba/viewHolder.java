package com.modelado.prueba;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import static androidx.core.content.ContextCompat.startActivities;
import static androidx.core.content.ContextCompat.startActivity;

public class viewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
    Button meGusta;
    ImageView imagenPerro;
    TextView nombrePerro;
    TextView numMeGusta;
    LinkedList<perro> perros;

    public viewHolder(@NonNull View itemView, LinkedList<perro> datos) {
        super(itemView);
        meGusta = (Button) itemView.findViewById(R.id.meGusta);
        imagenPerro = (ImageView) itemView.findViewById(R.id.imagenPerro);
        nombrePerro = (TextView) itemView.findViewById(R.id.nombrePerro);
        numMeGusta = (TextView) itemView.findViewById(R.id.numMeGusta);
        perros = datos;
        meGusta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        perro objeto = perros.get(getAdapterPosition());
        objeto.numMeGusta+=1;
        numMeGusta.setText(objeto.numMeGusta+ " me gusta");

    }
}
