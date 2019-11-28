package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;

public class Feed extends AppCompatActivity {

    private ApiCall api = new ApiCall(this);
    private RecyclerView mRv;
    private LinkedList<perro> perros;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        llenaFeed();

        RecyclerView contenedor = findViewById(R.id.contenedor);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contenedor.setAdapter(new RecyclerAdaptador(perros));
        contenedor.setLayoutManager(layoutManager);

    }

    public void comentar(View view){
        Intent intent = new Intent(this, comentarios.class);
        startActivity(intent);
    }

    public void llenaFeed(){
        perros = new LinkedList<>();

        String[][] dos_mil_perros = api.feed("961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506");
        for (int i = 0; i < 2000; i++) {
            perros.add(new perro(R.drawable.uno,dos_mil_perros[i][1],Integer.parseInt(dos_mil_perros[i][2]), Integer.parseInt(dos_mil_perros[i][3])));
        }

    }

    /* El adaptador le pasa los datos al recycler view, pone las imágenes, etc */



}
