package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

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

        //Cargamos el feed y lo llenamos mediante la api.
        llenaFeed();


        //Creamos la vista e interacción con el adaptador
        RecyclerView contenedor = findViewById(R.id.contenedor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        AdaptadorFeed adapta = new AdaptadorFeed(perros, llaveUsuario);
        contenedor.setAdapter(adapta);
        contenedor.setLayoutManager(layoutManager);


    }

    /**
     * Llena el feed mediante la api
     */
    public ArrayList<Perro> llenaFeed() {
        perros = new ArrayList<>();
        System.out.println("\t\t\t\tEN EL FEEED LA LLAVE " + llaveUsuario);

        String[][] dos_mil_perros = api.feed(llaveUsuario);
        for (int i = 0; i < 5; i++) {
            Perro perro = new Perro(dos_mil_perros[i][0],
                    dos_mil_perros[i][1],
                    Integer.parseInt(dos_mil_perros[i][2]),
                    Integer.parseInt(dos_mil_perros[i][3]));


            api.comentar(llaveUsuario, dos_mil_perros[i][3], "Hola perrito" + i);

            ArrayList<String> listaComentPerrito = new ArrayList<>();
            listaComentPerrito.add("Hola perrito " + i);
            perro.setComentarios(listaComentPerrito);
            perros.add(perro);
        }
        return perros;

    }
}
