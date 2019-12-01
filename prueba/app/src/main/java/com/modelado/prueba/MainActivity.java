package com.modelado.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena;
    private String llaveUsuario, user, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Accdemos a los editText para poder entrar al feed
        usuario = findViewById(R.id.user);
        contrasena = findViewById(R.id.password);

        if (usuario != null && contrasena != null) {
            user = usuario.getText().toString();
            password = contrasena.getText().toString();
        }


        //Cuando regresas para los comentarios, es necesario que se lean éstos editText
    }

    /**
     * Método que usa el botón entrar
     */
    public void entrar(View view) {
        user = usuario.getText().toString();
        password = contrasena.getText().toString();
        if (user.isEmpty() || password.length() == 0) {
            Toast.makeText(this, "Ingresa los datos correspondientes", Toast.LENGTH_SHORT).show();
        } else {
            //Verificar los datos
            ApiCall api = new ApiCall();
            String key = api.login(user, password);
            if (key.equals("ERROR:usr or password incorrect")) {
                Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
            } else {
                llaveUsuario = key;
            }
            //Si se puede acceder a la llave entonces creamos la actividad Feed
            if (llaveUsuario != null) {
                Intent intent = new Intent(this, Feed.class);
                intent.putExtra("nombreUsuario", user);
                intent.putExtra("contraUsuario", password);
                startActivity(intent);
            }

        }

    }

    /**
     * Método que usa el botón registrarse
     */
    public void registrarse(View view) {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

}
