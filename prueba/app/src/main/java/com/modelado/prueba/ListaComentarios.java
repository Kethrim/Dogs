package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class ListaComentarios extends AppCompatActivity {


    TextView vistaDeListaDeComentariosDePerros,
            postea,
            nombrePerro,
            idPerro,
            meGustasPerro;

    LinkedList<String> listaDeComentariosDePerro;
    EditText nuevoComentario;
    String llaveUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);
        Bundle extras = getIntent().getExtras();

        //Obtenemos los datos del perro a partir de lo que ya teníamos en el feed.

        TextView textView = findViewById(R.id.idPerro),
                textView1 = findViewById(R.id.nombrePerro),
                textView2 = findViewById(R.id.numMeGusta);

        String id = textView.getText().toString(),
                nombre = textView1.getText().toString(),
                meGusta = textView2.getText().toString();

        //Accedemos a la llave del usuario, que será necesaria para comentar
        if (extras != null) {
            llaveUsuario = extras.getString("llaveUsuario");
        }



        vistaDeListaDeComentariosDePerros = findViewById(R.id.listaComentarios);
        nuevoComentario = findViewById(R.id.nuevoComentario);

        postea = findViewById(R.id.postea);
        nombrePerro = findViewById(R.id.nombrePerroDetalles);
        idPerro = findViewById(R.id.idPerroDetalles);
        meGustasPerro = findViewById(R.id.meGustaDetalles);

        //Le damos formato al layout que usa ésta actividad
        //falta la imagen
        nombrePerro.setText("Nombre: " + nombre);
        meGustasPerro.setText(meGusta);
        idPerro.setText("Id: " + id);


    }

    private void obtenComentarios(int idPerro) {
        ApiCall api = new ApiCall();
//        api.perroComentarios();

    }
}
