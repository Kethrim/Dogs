package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class comentarios extends AppCompatActivity {

    private RecyclerView coments;
    private TextView nuevoComentario;
    private ImageButton enviar;
    ArrayList<Integer> listaId;
    List<List<String>> listaDeListasDeComentarios;
    AdaptadorComentario adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        llenaComentarios();

        coments = findViewById(R.id.comentarios);
        nuevoComentario = findViewById(R.id.nuevoComentario);
        enviar = findViewById(R.id.enviarComentario);


        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coments.setLayoutManager(linearLayoutManager);

        adaptador = new AdaptadorComentario(listaId, listaDeListasDeComentarios);
        coments.setAdapter(adaptador);
    }

    public void llenaComentarios(){
        listaId = (ArrayList<Integer>) getIntent().getSerializableExtra("ids");
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
