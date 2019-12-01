package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class ListaComentarios extends AppCompatActivity {

    // Objetos que se muestran al usuario
    TextView vistaDeListaDeComentariosDePerros,
            nombrePerro,
            idPerro,
            meGustasPerro;
    ImageView imagenPerro;

    //Otras partes del layout
    TextView postea;
    EditText nuevoComentario;
    String llaveUsuario;

    //Datos del perrito
    String idPerrrito;
    String numMegustaPerrito;
    String nombrePerrito;
    ArrayList<String> listaDeComentariosDePerro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);

//        imagenPerro = findViewById(R.id.imagenPerroDetalles);
        vistaDeListaDeComentariosDePerros = findViewById(R.id.listaComentarios);
        nuevoComentario = findViewById(R.id.nuevoComentario);
        postea = findViewById(R.id.postea);

        nombrePerro = findViewById(R.id.nombrePerroDetalles);
        idPerro = findViewById(R.id.idPerroDetalles);
        meGustasPerro = findViewById(R.id.meGustaDetalles);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            llaveUsuario = extras.getString("llave");
            idPerrrito = extras.getString("idPerrito");
            nombrePerrito = extras.getString("nombrePerrito");
            numMegustaPerrito = extras.getString("numMeGusta");
//            listaDeComentariosDePerro = extras.getStringArrayList("listaApi");
//            if (getIntent().hasExtra("byteArray") && extras.getByteArray("byteArray")!= null) {
//                Bitmap b = BitmapFactory.decodeByteArray(
//                        extras.getByteArray("byteArray"), 0, extras.getByteArray("byteArray").length
//                );
//                imagenPerro.setImageBitmap(b);
//            }
        }

        //Le damos formato al layout que usa ésta actividad
        nombrePerro.setText("Nombre: " + nombrePerrito);
        meGustasPerro.setText(numMegustaPerrito);
        idPerro.setText("Id: " + idPerrrito);
        vistaDeListaDeComentariosDePerros.setText(idPerrrito);


    }
}
