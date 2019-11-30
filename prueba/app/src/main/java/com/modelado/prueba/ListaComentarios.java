package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class ListaComentarios extends AppCompatActivity {


    TextView vistaDeListaDeComentariosDePerros,
            postea,
            nombre,
            id,
            meGusta;

    LinkedList<String> listaDeComentariosDePerro;
    EditText nuevoComentario;
    String llaveUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);
        Bundle extras = getIntent().getExtras();

        //Obtenemos los datos del perro a partir de lo que ya teníamos en el feed.
        String idPerro = findViewById(R.id.idPerro).toString(),
                nombrePerro = findViewById(R.id.nombrePerro).toString(),
                meGustas = findViewById(R.id.numMeGusta).toString();

        //Accedemos a la llave del usuario, que será necesaria para comentar
        if (extras != null) {
            llaveUsuario = extras.getString("llaveUsuario");
        }



        vistaDeListaDeComentariosDePerros = findViewById(R.id.listaComentarios);
        nuevoComentario = findViewById(R.id.nuevoComentario);

        postea = findViewById(R.id.postea);
        nombre = findViewById(R.id.nombrePerroDetalles);
        id = findViewById(R.id.idPerroDetalles);
        meGusta = findViewById(R.id.meGustaDetalles);

        //Le damos formato al layout que usa ésta actividad
        //falta la imagen
        nombre.setText("Nombre: " + nombrePerro);
        meGusta.setText(meGustas);
        id.setText("Id: " + idPerro);

//        obtenComentarios(Integer.parseInt(idPerro));
//
//
//        vistaDeListaDeComentariosDePerros.setText(idPerro);
//        listaDeComentariosDePerro = new LinkedList<>();

    }

    private void obtenComentarios(int idPerro) {
        ApiCall api = new ApiCall();
//        api.perroComentarios();

    }
}
