package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedList;

public class Feed extends AppCompatActivity {

    private ApiCall api = new ApiCall();
    private ArrayList<Perro> perros;
    private String llaveUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        //Obtenemos la llave del usuario
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            if (extras.getString("keyUsuario") != null) {
                llaveUsuario = extras.getString("keyUsuario");
            } else {
                System.out.println("\t\t\tLa llave del usuario es nula, hubo error al pasarla");
            }


        // Cargamos el feed si y solo si es la primera vez
//        if (extras != null)

        llenaFeed();

        //Creamos la vista e interacción con el adaptador
        RecyclerView contenedor = findViewById(R.id.contenedor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        if (extras != null)
        contenedor.setAdapter(new AdaptadorFeed(perros, llaveUsuario));
        contenedor.setLayoutManager(layoutManager);


    }

    /**
     * Llena el feed mediante la api
     */
    public ArrayList<Perro> llenaFeed() {
        perros = new ArrayList<>();

        String[][] dos_mil_perros = api.feed(llaveUsuario);
        // Cambiar por 2000 perros
        for (int i = 0; i < 100; i++) {
            Perro perro = new Perro(dos_mil_perros[i][0],
                    dos_mil_perros[i][1],
                    Integer.parseInt(dos_mil_perros[i][2]),
                    Integer.parseInt(dos_mil_perros[i][3]));

            api.comentar(llaveUsuario, dos_mil_perros[i][3], "Hola perrito" + i);
            perros.add(perro);
        }
        return perros;

    }
}
