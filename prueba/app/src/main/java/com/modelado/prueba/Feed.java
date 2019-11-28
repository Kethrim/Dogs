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

import com.modelado.apicall.ApiCall;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;

public class Feed extends AppCompatActivity {

    private LinkedList<Perro> perros;

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

    /* Abre una nueva actividad para los comentarios*/
    public void comentar(View view){
        Intent intent = new Intent(this, comentarios.class);
        startActivity(intent);
    }

    /* Llena el feed mediante la api */
    public void llenaFeed(){
        perros = new LinkedList<>();
        perros.add(new Perro(R.drawable.uno,"Athan_Ervin",5, 76281));
        perros.add(new Perro(R.drawable.dos,"Alfonse_Barrett", 10, 47042));
        perros.add(new Perro(R.drawable.tres, "Zackery_Maverick", 6,41630));
        perros.add(new Perro(R.drawable.cuatro, "Max Wilbur", 79, 85653));
        perros.add(new Perro(R.drawable.cinco, "asdfghjklñqwertyuiopzxcvbnm", 95,36133));
    }

    /* El adaptador le pasa los datos al recycler view, pone las imágenes, etc */



}
