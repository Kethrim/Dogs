package com.modelado.prueba;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Indica qué información se mostrará en el card item para el feed.
 */
public class viewHolderFeed extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Button BotonMeGusta;
    private ImageView imageViewImagenPerro;
    private TextView textViewIdPerro;
    private TextView textViewNombrePerro;
    private TextView textViewNumMeGusta;
    private TextView textViewLlaveUsuario;
    private ArrayList<Perro> perros;
    private Context contexto;

    /**
     * Crea un viewHolderFeed
     *
     * @param itemView-vista.
     * @param datos- lista de datos de perros que se mostrará.
     * @param llave- llave del usuario.
     */
    public viewHolderFeed(@NonNull View itemView, ArrayList<Perro> datos, String llave) {
        super(itemView);
        BotonMeGusta = itemView.findViewById(R.id.FeedMeGusta);
        imageViewImagenPerro = itemView.findViewById(R.id.FeedImagenPerro);
        textViewNombrePerro = itemView.findViewById(R.id.FeedNombrePerro);
        textViewNumMeGusta = itemView.findViewById(R.id.FeedNumMeGusta);
        textViewLlaveUsuario = itemView.findViewById(R.id.FeedLlaveUsuario);
        textViewIdPerro = itemView.findViewById(R.id.FeedIdPerro);
        textViewLlaveUsuario.setText(llave);

        perros = datos;
        contexto = itemView.getContext();

        imageViewImagenPerro.setOnClickListener(this);
        BotonMeGusta.setOnClickListener(this); //al dar click se aumenta un me gusta.
    }

    /**
     * Obtiene la imagen de un perro.
     *
     * @return ImageView donde se coloca la imagen.
     */
    public ImageView getImagenPerro() {
        return imageViewImagenPerro;
    }

    /**
     * Obtiene el nombre de un perro.
     *
     * @return TextView dónde se pone el nombre.
     */
    public TextView getNombrePerro() {
        return textViewNombrePerro;
    }

    /**
     * Obtiene el número de me gustas del perro.
     *
     * @return TextView en el formato "x me gusta", con x el número de me gustas.
     */
    public TextView getNumMeGusta() {
        return textViewNumMeGusta;
    }

    /**
     * Obtiene la lista de Perros que se mostrará en el feed.
     *
     * @return lista de los perros.
     */
    public ArrayList<Perro> getPerros() {
        return perros;
    }

    /**
     * Obtiene el id del perro.
     *
     * @return id único del perro.
     */
    public TextView getIdPerro() {
        return textViewIdPerro;
    }


    @Override
    public void onClick(View v) {
        Perro objeto = perros.get(getAdapterPosition());
        switch (v.getId()) {
            case R.id.FeedMeGusta:
                ApiCall apiCall = new ApiCall();
                String key = textViewLlaveUsuario.getText().toString();
                apiCall.likes(key, objeto.getIdPerro() + "");
                int num = Integer.parseInt(
                        apiCall.perroDetalles(
                                key,
                                objeto.getIdPerro() + "")[3]);
                System.out.println("La key es" + key);
                System.out.println("El num likes es" +num);
                objeto.setNumMeGusta(num);
                this.textViewNumMeGusta.setText(num + " me gusta");
                break;
            case R.id.FeedImagenPerro:
                /* Al dar click en la imagen se abre la actividad de lista de comentarios
                Pasamos como argumentos por la actividad:
                    - llave del usuario
                    - id del perro
                */
                Intent intent = new Intent(contexto, ListaComentarios.class);
                intent.putExtra("llaveUsuario", textViewLlaveUsuario.getText().toString() );
                intent.putExtra("idPerro", textViewIdPerro.getText().toString());

//                Bitmap bitmap = objeto.descargaImg();
//                ByteArrayOutputStream byteArrayInputStream= new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayInputStream);
//
//                intent.putExtra("byteArray", byteArrayInputStream.toByteArray());

                contexto.startActivity(intent);
                break;
        }
    }
}
