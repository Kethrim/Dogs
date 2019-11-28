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
        perros.add(new perro(R.drawable.uno,"Athan_Ervin",5, 76281));
        perros.add(new perro(R.drawable.dos,"Alfonse_Barrett", 10, 47042));
        perros.add(new perro(R.drawable.tres, "Zackery_Maverick", 6,41630));
        perros.add(new perro(R.drawable.cuatro, "Max Wilbur", 79, 85653));
        perros.add(new perro(R.drawable.cinco, "asdfghjklñqwertyuiopzxcvbnm", 95,36133));
    }

    /* El adaptador le pasa los datos al recycler view, pone las imágenes, etc */



}
