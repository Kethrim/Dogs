package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText editTextUsuario;
    private EditText editTextContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextUsuario = findViewById(R.id.RegistroUsuario);
        editTextContrasena = findViewById(R.id.RegistroContrasena);
    }

    public void validarDatos(View view) {
        String usuario = editTextUsuario.getText().toString(),
                contrasena = editTextContrasena.getText().toString();

        if (usuario.isEmpty() || contrasena.length() == 0) {
            Toast.makeText(this, "Completa el ingreso de datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
            //Verificar los datos
            ApiCall apiCall = new ApiCall();
            if (apiCall.singUp(usuario,contrasena)){
                Toast.makeText(this, "Te registraste exitosamente. Inicia sesión.", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Hubo un error :C", Toast.LENGTH_SHORT).show();
        }
    }


}
