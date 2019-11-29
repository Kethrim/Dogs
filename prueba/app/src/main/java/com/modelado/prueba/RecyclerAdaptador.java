
package com.modelado.prueba;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class RecyclerAdaptador extends RecyclerView.Adapter<viewHolder> {
    private ImageView imageImg;
    LinkedList<Perro> perros;

    public RecyclerAdaptador(LinkedList<Perro> perros) {
        this.perros = perros;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new viewHolder(vista, perros);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.nombrePerro.setText(perros.get(position).nombrePerro);

        holder.imagenPerro.setImageBitmap(
                descargaImg( // da un Bitmap con la url
                        holder.perros.get(position).imagenPerro // da la url de la imagen del perro
                ));

        holder.numMeGusta.setText(Integer.toString(perros.get(position).numMeGusta) + " me gusta");
    }

    @Override
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
    private Bitmap descargaImg(String url) {
        PeticionAsynk a = new PeticionAsynk();
        Bitmap salida = null;
        try {
            salida = a.execute(url).get();
        } catch (Exception e) {
            System.out.println("Fallo al descargar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
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
            URL imageUrl = null;

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
