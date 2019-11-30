package com.modelado.prueba;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Estructura de un perro para mostrar en el feed.
 */
public class Perro implements Serializable {
    private String nombrePerro;
    private String imagenPerro;
    private int idPerro;
    private int numMeGusta;
    private LinkedList<String> comentarios;


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
        this.comentarios = new LinkedList<>();
    }

    /**
     * Define el número de me gustas del perro.
     *
     * @param numMeGusta- número de me gustas.
     */

    public void setNumMeGusta(int numMeGusta) {
        this.numMeGusta = numMeGusta;
    }


    public void setComentarios(LinkedList<String> comentarios) {
        this.comentarios = comentarios;
    }

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
}


