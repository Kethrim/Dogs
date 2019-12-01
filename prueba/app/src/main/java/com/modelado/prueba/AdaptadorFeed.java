
package com.modelado.prueba;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * El adaptador le pasa los datos al recycler view, pone las imágenes, etc
 */
public class AdaptadorFeed extends RecyclerView.Adapter<viewHolderFeed>{
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

        return new viewHolderFeed(vista, perros,llaveUsuario);
    }

    @Override
    //Se asignan los valores de cada card item (tarjeta)
    public void onBindViewHolder(@NonNull final viewHolderFeed holder, int position) {
        holder.getNombrePerro().setText(perros.get(position).getNombrePerro());

        holder.getImagenPerro().setImageBitmap(
                perros.get(position).descargaImg());

        holder.getNumMeGusta().setText(perros.get(position).getNumMeGusta() + " me gusta");

        holder.getIdPerro().setText(String.valueOf(perros.get(position).getIdPerro()));

//        holder.getImagenPerro().setOnClickListener(this);


    }

    @Override
    //Número de datos que cargará el feed, en este caso será el número de elementos de la lista.
    public int getItemCount() {
        return perros.size();
    }

    /**
     * Descarga una imagen a partir de una url y es usada para el metodo onBindViewHolder con el fin
     * de que cada imagen sea diferente
     * > El metodo puede fallar si no se cuenta con conexion a internet
     *
     * @param url String De la url de la imagen, puede servir cualquier imagen en linea empezando por
     *            "https://ruta_de_da_imagen"
     * @return Un Bitmap que contiene toda la información de la imagen
     */
    public Bitmap descargaImg(String url) {
        PeticionAsynk a = new PeticionAsynk();
        Bitmap salida = null;
        try {
            salida = a.execute(url).get();
        } catch (Exception e) {
            System.out.println("Fallo al descargar la imagen: " + e.getMessage());
            e.printStackTrace();
        }

//        System.out.println("\t\t\t\tBITMAP DE LA IMAGEN"+salida);
        return salida;
    }

    /**
     * Clarse PeticionAsynk
     * Esta clase representa una tarea de que realizará de forma asincrona en el programa
     * Solo es usada una vez en descargImg
     */
    private class PeticionAsynk extends AsyncTask<String, Void, Bitmap> {

        /**
         * Este metodo se hace por detras y solo manda a descarar a la imagen por medio de una url y
         * Obtiene un Bitmap de ella
         *
         * @param strings Url de la imagen empezando con el protocolo "https//"
         * @return Bitmap de la imagen a la cual esta ligada la url en internet
         */
        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageHttpAddress = strings[0];
            Bitmap imagen = null;
            URL imageUrl;

            try {
                imageUrl = new URL(imageHttpAddress);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                imagen = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return imagen;
        }
    }
}
