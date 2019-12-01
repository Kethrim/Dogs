package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class Feed extends AppCompatActivity {

    private ApiCall api = new ApiCall();
    private ArrayList<Perro> perros;
    ArrayList<Integer> listaDeId;
    private String nombreUsuario, contrasenaUsuario, llaveUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            if (extras.getString("nombreUsuario") != null) {
                nombreUsuario = extras.getString("nombreUsuario");
                contrasenaUsuario = extras.getString("contraUsuario");
                llaveUsuario = api.login(nombreUsuario, contrasenaUsuario);
            }


        // Cargamos el feed si y solo si es la primera vez
        if (extras != null)
            llenaFeed();

        //Creamos la vista e interacción con el adaptador
        RecyclerView contenedor = findViewById(R.id.contenedor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //Lo nuevo

        if (extras != null)
//            AdaptadorFeed adapta = new AdaptadorFeed(perros, llaveUsuario);
            contenedor.setAdapter(new AdaptadorFeed(perros, llaveUsuario));

        //Lo del Brayan
        /*if (extras != null) {
            AdaptadorFeed adapta = new AdaptadorFeed(perros);
            contenedor.setAdapter(adapta);
        }*/
        contenedor.setLayoutManager(layoutManager);


    }

//
//    public void darMeGusta(View view){
//        TextView idPerrito = findViewById(R.id.idPerro);
//        String idPerro = idPerrito.getText().toString();
//
//    }

    /**
     * Llena el feed mediante la api
     */
    public ArrayList<Perro> llenaFeed() {
        perros = new ArrayList<>();
        listaDeId = new ArrayList<>();


        String[][] dos_mil_perros = api.feed(llaveUsuario);
        for (int i = 0; i < 2000; i++) {
            Perro perro = new Perro(dos_mil_perros[i][0],
                    dos_mil_perros[i][1],
                    Integer.parseInt(dos_mil_perros[i][2]),
                    Integer.parseInt(dos_mil_perros[i][3]));


            //api.comentar(llaveUsuario, dos_mil_perros[i][3], "Hola perrito" + i);

            LinkedList<String> listaComentPerrito = new LinkedList<>();
            perro.setComentarios(listaComentPerrito);
            perros.add(perro);
        }
        return perros;

    }
}
