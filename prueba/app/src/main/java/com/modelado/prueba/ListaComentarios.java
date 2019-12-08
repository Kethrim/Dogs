package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    //Para la lista de los comntarios
    private RecyclerView recyclerView;
    private AdaptadorComentarios adaptadorComentarios;
    private ArrayList<String> comentarios;

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

        //Definimos la vista para la lista de comentarios.
        recyclerView = findViewById(R.id.recyler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        comentarios = new ArrayList<>();
//        List<String> listaDeComentarios = Arrays.asList(api.perroComentarios(llaveUsuario, idPerro));
//        comentarios.addAll(listaDeComentarios);

        comentarios.add("Hola perrito");
        comentarios.add("jjbdkcjbsdk");
        comentarios.add("Jejjdbk");
        comentarios.add("Chulis bonis");
        comentarios.add("El Mike");
        adaptadorComentarios = new AdaptadorComentarios(this, comentarios);
        recyclerView.setAdapter(adaptadorComentarios);


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
//        System.out.println("El tamaño de la lista que crereamos (ANTES) es: "+api.perroComentarios(llaveUsuario, idPerro).length);
//        System.out.println("Añadimos "+nuevoComentario.getText().toString());
//        System.out.println("El tamaño de la lista que creamos (DESPUÉS)es: "+api.perroComentarios(llaveUsuario, idPerro).length);
        System.out.println(Arrays.toString(api.perroComentarios(llaveUsuario, idPerro)));

        nuevoComentario.setText("");


        comentarios.clear();
        List<String> listaDeComentarios = Arrays.asList(api.perroComentarios(llaveUsuario, idPerro));

        for( String s: listaDeComentarios)
            System.out.println("Lista: "+s);

        comentarios.addAll(listaDeComentarios);
        System.out.println("El tamaño de la lista es: "+comentarios.size());
        adaptadorComentarios.notifyDataSetChanged();

    }

}
