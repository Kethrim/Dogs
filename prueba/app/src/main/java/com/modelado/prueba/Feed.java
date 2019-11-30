package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;

public class Feed extends AppCompatActivity {

    private ApiCall api = new ApiCall();
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

    /** Llena el feed mediante la api */
    public ArrayList<Perro> llenaFeed(){
        perros = new ArrayList<>();
        listaDeId = new ArrayList<>();

        LinkedList<String> listaComentPerrito = new LinkedList<>();


        String[][] dos_mil_perros = api.feed("961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506");
        for (int i = 0; i < 5; i++) {
            Perro perro = new Perro(dos_mil_perros[i][0],
                    dos_mil_perros[i][1], i+3,   //aumente me gustas para probar
                    Integer.parseInt(dos_mil_perros[i][3]));
            listaDeId.add(Integer.parseInt(dos_mil_perros[i][3]));
            listaComentPerrito.add("Hola perrito_"+i);
            perro.setComentarios(listaComentPerrito); //subir ésta lista a la api
            perros.add(perro);
        }
        return perros;

    }
}
