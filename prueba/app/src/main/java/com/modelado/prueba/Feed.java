package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

public class Feed extends AppCompatActivity {

    private ApiCall api = new ApiCall(this);
    private ArrayList<Perro> perros;
    ArrayList<Integer> listaDeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        //Cargamos el feed y lo llenamos mediante la api.
        llenaFeed();

        RecyclerView contenedor = findViewById(R.id.contenedor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contenedor.setAdapter(new AdaptadorFeed(perros));
        contenedor.setLayoutManager(layoutManager);

    }

    /** Abre una nueva actividad para los comentarios*/
    public void comentar(View view){

//        Intent intent = new Intent(this, ListaComentarios.class);
////        intent.putExtra("ids", listaDeId);
//        startActivity(intent);
    }

    /** Llena el feed mediante la api */
    private void llenaFeed(){
        perros = new ArrayList<>();
        listaDeId = new ArrayList<>();

        String[][] dos_mil_perros = api.feed("961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506");
        for (int i = 0; i < 2000; i++) {
            Perro perro = new Perro(dos_mil_perros[i][0],
                    dos_mil_perros[i][1],
                    Integer.parseInt(dos_mil_perros[i][2]),
                    Integer.parseInt(dos_mil_perros[i][3]));
            listaDeId.add(Integer.parseInt(dos_mil_perros[i][3]));
            perros.add(perro);
        }


    }



    /* El adaptador le pasa los datos al recycler view, pone las imágenes, etc */
}
