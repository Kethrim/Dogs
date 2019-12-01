package com.modelado.prueba;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Estructura de un perro para mostrar en el feed.
 */
public class Perro implements Serializable {
    private String nombrePerro;
    private String imagenPerro;
    private int idPerro;
    private int numMeGusta;
    private ArrayList<String> comentarios;


    /**
     * Constructor
     *
     * @param imagen-  url de la imágen del pero.
     * @param nombre-  nombre del perro.
     * @param megusta- número de me gusta que tiene el perro.
     * @param idPerro- id creado en la base de datos del perro.
     */
    public Perro(String imagen, String nombre, int megusta, int idPerro) {
        this.imagenPerro = imagen;
        this.nombrePerro = nombre;
        this.idPerro = idPerro;
        this.numMeGusta = megusta;
        this.comentarios = new ArrayList<>();
    }

    /**
     * Define el número de me gustas del perro.
     *
     * @param numMeGusta- número de me gustas.
     */

    public void setNumMeGusta(int numMeGusta) {
        this.numMeGusta = numMeGusta;
    }


    public void setComentarios(ArrayList<String> comentarios) {

        this.comentarios = comentarios;
    }

    public ArrayList getListaComentarios(){
        return (ArrayList) this.comentarios;
    }

//    public LinkedList<String>

    /**
     * Obtiene el nombre del perro.
     *
     * @return nombre del perro.
     */
    public String getNombrePerro() {
        return nombrePerro;
    }

    /**
     * Obtiene la imagen del perro.
     *
     * @return url de la imagen del perro.
     */
    public String getImagenPerro() {
        return imagenPerro;
    }

    /**
     * Obtiene el id del perro.
     *
     * @return id del perro.
     */
    public int getIdPerro() {
        return idPerro;
    }

    /**
     * Obtiene el número de me gustas del perro.
     *
     * @return número de me gustas del perro.
     */
    public int getNumMeGusta() {
        return numMeGusta;
    }

    /**
     * Descarga una imagen a partir de una url y es usada para el metodo onBindViewHolder con el fin
     * de que cada imagen sea diferente
     * > El metodo puede fallar si no se cuenta con conexion a internet
     *
     * @return Un Bitmap que contiene toda la información de la imagen
     */

    public Bitmap descargaImg() {
        PeticionAsynk a = new PeticionAsynk();
        Bitmap salida = null;
        try {
            salida = a.execute(imagenPerro).get();
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


