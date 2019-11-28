package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText usuario;
    private EditText contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = findViewById(R.id.user);
        contrasena = findViewById(R.id.password);
    }

    public void validarDatos(View view){
        String user = usuario.getText().toString(), password = contrasena.getText().toString();

        if (user.isEmpty() || password.length()==0){
            Toast.makeText(this, "Completa el ingreso de datos", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
            //Verificar los datos
        }
    }



}
