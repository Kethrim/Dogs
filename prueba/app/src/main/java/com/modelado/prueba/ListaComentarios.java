package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ListaComentarios extends AppCompatActivity {

    ApiCall api = new ApiCall();

    String llaveUsuario, idPerro; //Para poder usar la api

    //Detalles del perro
    ImageView imagenPerroDetalles;
    TextView nombrePerroDetalles;
    TextView idPerroDetalles;
    TextView meGustaDetalles;

    // Para poder agregar un comentario
    TextView postea;
    EditText nuevoComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detalles del perro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Obtenemos los datos para hacer un llamado a la api
        Intent intent = getIntent();
        llaveUsuario = intent.getStringExtra("llaveUsuario");
        idPerro = intent.getStringExtra("idPerro");

        imagenPerroDetalles = findViewById(R.id.imagenPerroDetalles);
        nombrePerroDetalles = findViewById(R.id.nombrePerroDetalles);
        idPerroDetalles = findViewById(R.id.idPerroDetalles);
        meGustaDetalles = findViewById(R.id.meGustaDetalles);

        String [] detallesPerro = api.perroDetalles(llaveUsuario,idPerro);
//        Llenamos los datos del perrito
        idPerroDetalles.setText("Id del perro: "+detallesPerro[0]);
        nombrePerroDetalles.setText("Nombre: "+ detallesPerro[1]);
        meGustaDetalles.setText(detallesPerro[3]+ " me gusta");
        imagenPerroDetalles.setImageBitmap(api.descargaImg(detallesPerro[2]));


        postea = findViewById(R.id.postea);
        nuevoComentario = findViewById(R.id.nuevoComentario);

        postea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nuevoComentario.getText().toString().length() == 0){
                    Toast.makeText(ListaComentarios.this, "No puedes subir un comentario vacío", Toast.LENGTH_SHORT).show();
                } else {
                    anadeComentario();
                }
            }
        });

    }

    /**
     * Añade un comentario a la api.
     */
    private void anadeComentario(){
        api.comentar(llaveUsuario, idPerro, nuevoComentario.getText().toString());
        nuevoComentario.setText("");
    }

}
