package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class ListaComentarios extends AppCompatActivity {


    TextView vistaDeListaDeComentariosDePerros,
            postea,
            nombre,
            id,
            meGusta;
    ImageView imagen;
    LinkedList<String> listaDeComentariosDePerro;
    EditText nuevoComentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);
        Bundle extras = getIntent().getExtras();

        String idPerro  = "",
                nombrePerro = "",
                meGustas = "";
        Bitmap bitmap = null;

        if (extras != null){
            idPerro = extras.getString("idPerrito");
            nombrePerro = extras.getString("nombrePerrito");
            meGustas = extras.getString("meGustasPerrito");

//            bitmap = extras.getParcelable("bitmapImagenPerrito");
        }

                            System.out.println("\t\t\tBITMAAAAAAAAP "+bitmap);

        vistaDeListaDeComentariosDePerros = findViewById(R.id.listaComentarios);
        nuevoComentario= findViewById(R.id.nuevoComentario);

        postea = findViewById(R.id.postea);
        nombre = findViewById(R.id.nombrePerroDetalles);
        id = findViewById(R.id.idPerroDetalles);
        meGusta = findViewById(R.id.meGustaDetalles);

        nombre.setText("Nombre: "+nombrePerro);
        meGusta.setText(meGustas);
        id.setText("Id: "+idPerro);
//        imagen.setImageBitmap(bitmap);

        obtenComentarios(Integer.parseInt(idPerro));



        vistaDeListaDeComentariosDePerros.setText(idPerro);
        listaDeComentariosDePerro = new LinkedList<>();
//        listaDeComentariosDePerro.add(nuevoComent);
//        String comentariosAnteriores = vistaDeListaDeComentariosDePerros.getText().toString();
//        vistaDeListaDeComentariosDePerros.setText(comentariosAnteriores+listaDeComentariosDePerro.toString());

    }

    private void obtenComentarios(int idPerro){
//        ApiCall api = new ApiCall();


    }
}
