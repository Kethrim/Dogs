package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListaComentarios extends AppCompatActivity {

    TextView listaDeComentarios, postea;
    EditText nuevoComentario;

    ArrayList<Integer> listaId;
    List<List<String>> listaDeListasDeComentarios;
    AdaptadorComentario adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);


        listaDeComentarios = findViewById(R.id.listaComentarios);
        postea = findViewById(R.id.post);

//        llenaComentarios();

//        coments = findViewById(R.id.comentarios);
//        nuevoComentario = findViewById(R.id.nuevoComentario);
//        enviar = findViewById(R.id.enviarComentario);


//        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        coments.setLayoutManager(linearLayoutManager);
//
//        adaptador = new AdaptadorComentario(listaId, listaDeListasDeComentarios);
//        coments.setAdapter(adaptador);
    }

    public void llenaComentarios(){
        listaId = (ArrayList<Integer>) getIntent().getSerializableExtra("ids");

        listaDeListasDeComentarios = new LinkedList<>();
        LinkedList<String> listaRelleno = new LinkedList<>();
        listaRelleno.add("Hola");
        listaDeListasDeComentarios.add(listaRelleno);


        listaRelleno.add("Bola");
        listaDeListasDeComentarios.add(listaRelleno);



        listaRelleno.add("Bonis");
        listaDeListasDeComentarios.add(listaRelleno);


        listaRelleno.add("Bolis bonis");
        listaDeListasDeComentarios.add(listaRelleno);

        listaRelleno.add("Borrego");
        listaDeListasDeComentarios.add(listaRelleno);


        //Llamar a la api y obtener la lista de comentarios


    }


    public void subirComentario(View view){
     //   actualiza();
    }

    /*
    public void actualiza(){
        String nuevoComent = nuevoComentario.getText().toString();
        if (nuevoComent.length() != 0)
                comentarios.add(nuevoComentario.getText().toString());
        adaptador.notifyItemInserted(comentarios.size());
        nuevoComentario.setText("");
    }*/


}
