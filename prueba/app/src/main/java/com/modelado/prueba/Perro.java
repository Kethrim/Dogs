package com.modelado.prueba;
import java.util.LinkedList;

public class Perro {
    String nombrePerro;
    int imagenPerro;
    int idPerro;
    int numMeGusta;
    LinkedList<String> comentarios;


    public Perro(int imagen, String nombre, int megusta, int idPerro) {
        this.imagenPerro = imagen;
        this.nombrePerro = nombre;
        this.idPerro = idPerro;
        this.numMeGusta = megusta;
        this.comentarios = new LinkedList<>();
    }

}


