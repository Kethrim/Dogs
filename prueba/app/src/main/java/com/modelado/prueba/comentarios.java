package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class comentarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        Toast.makeText(this, "Preparando comentarios...", Toast.LENGTH_SHORT).show();
    }
}
