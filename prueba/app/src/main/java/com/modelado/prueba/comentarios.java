package com.modelado.prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class comentarios extends AppCompatActivity {

    private RecyclerView coments;
    private TextView nuevoComentario;
    private ImageButton enviar;
    private LinkedList<String> comentarios =  new LinkedList<>();
    AdaptadorComentario adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        llenaComentarios();

        coments = findViewById(R.id.comentarios);
        nuevoComentario = findViewById(R.id.nuevoComentario);
        enviar = findViewById(R.id.enviarComentario);


        coments = findViewById(R.id.comentarios);
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coments.setLayoutManager(linearLayoutManager);

        adaptador = new AdaptadorComentario(comentarios);
        coments.setAdapter(adaptador);
    }

    public void llenaComentarios(){
        comentarios.add("Qué bonito");
        comentarios.add("Estás preciosoooo");
        comentarios.add("Chulo");

    }

    public void subirComentario(View view){
        actualiza();
    }

    public void actualiza(){
        String nuevoComent = nuevoComentario.getText().toString();
        if (nuevoComent.length() != 0)
                comentarios.add(nuevoComentario.getText().toString());
        adaptador.notifyItemInserted(comentarios.size());
        nuevoComentario.setText("");
    }


}
