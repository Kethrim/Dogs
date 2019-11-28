package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.user);
        contrasena = findViewById(R.id.password);

    }

    //Método que usa el botón entrar
    public void entrar(View view){
        String user = usuario.getText().toString(), password = contrasena.getText().toString();

        if (user.isEmpty() || password.length()==0){
            Toast.makeText(this, "Ingresa los datos correspondientes", Toast.LENGTH_SHORT).show();
        } else{
            //Verificar los datos

            Intent intent = new Intent(this, Feed.class);
            startActivity(intent);
        }

    }

    //Método que usa el botón registrarse
    public void registrarse(View view){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

}
