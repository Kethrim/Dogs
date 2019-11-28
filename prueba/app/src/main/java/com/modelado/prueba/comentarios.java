package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class comentarios extends AppCompatActivity {

    private RecyclerView coments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        coments = findViewById(R.id.comentarios);
        coments.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
        coments.setLayoutManager(linearLayoutManager);

        
        Toast.makeText(this, "Preparando comentarios...", Toast.LENGTH_SHORT).show();
    }
}
