package com.modelado.prueba;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiCall {

    /**
     * Clase ApiCall para ser un puente entre la api de la página web http://modelado2020-1.tk y una a-
     * plicación para Android.
     * <p>
     * Esta api esta libre de excepciones, sin embargo es impresindible contar con conección a internet
     * para mandar a llamar a todos y cada uno de los métodos.
     *
     * @author Brayan Martinez Santana Brayan
     * @version 1.0
     */
    private Gson gson = new Gson(); // Sirve para pasar strings a objetos del tipo Json

    /**
     * Metodo que le agrega un comentario a un perro
     * Acepta saltos de linea
     *
     * @param key        String que representa la key del usuario
     * @param dog_id     String del perro al que se le hará el comentario
     * @param comentario String Es el comentario mismo, funciona incluso con saltos de línea.
     */
    public void comentar(String key, String dog_id, String comentario) {
        String url = "http://modelado2020-1.tk/comentarios.php?key=" + key + "&dog_id=" + dog_id;

        apiLlamado(url, "POST", comentario);
    }

    /**
     * Obtiene todos los detalles de un perro en específico
     * Cada índice representa cosas distintas
     * > En el índice 0 representa el dog_id (este debe coincidir con el dog_id pasado como parámetro
     * pero siempre es el mismo.
     * > En el índice 1 representa el nombre del perro
     * > En el índice 2 representa la imagen del perro en un url de internet
     * > En el índice 3 representa el número de likes del perro
     *
     * @param key    String que representa la key del usuario
     * @param dog_id String (único) del perro del que se obtendran los detalles
     * @return String[] De  longitud 4 con los indices previamente mencionados
     */
    public String[] perroDetalles(String key, String dog_id) {
        String url = "http://modelado2020-1.tk/detalles.php?key=" + key + "&dog_id=" + dog_id;

        JsonObject jsonObj = gson.fromJson(apiLlamado(url,"GET",""), JsonObject.class);
        JsonObject perro_info = jsonObj.get("perro").getAsJsonObject();
        return new String[]{perro_info.get("dog_id").getAsString(),
                perro_info.get("nombre").getAsString(),
                perro_info.get("imagen").getAsString(),
                perro_info.get("no_likes").getAsString(),
        };
    }

    /**
     * Obtiene todos los comentarios de un perro específico
     * Es recomentable usar un ForEach para recorrer el arreglo que regresa porque si un usuario no
     * tiene comentarios regresa un arreglo de longitud 0, o de cualquier otra longitud, dependiendo
     * de la cantidad de comenatios del perrito.
     *
     * @param key    String que representa la key del usuario
     * @param dog_id String (único) del perro del que se obtendran los detalles
     * @return String[] De  longitud variada, donde cada elemento es un String que representa el
     * comentario
     */
    public String[] perroComentarios(String key, String dog_id) {
        String url = "http://modelado2020-1.tk/detalles.php?key=" + key + "&dog_id=" + dog_id;

        JsonArray perro_coments = gson.fromJson(apiLlamado(url,"GET",""), JsonObject.class).get("comentarios").getAsJsonArray();
        String[] comentarios = new String[perro_coments.size()];

        for (int i = 0; i < comentarios.length; i++) {
            comentarios[i] = perro_coments.get(i).getAsJsonObject().get("text").getAsString();
        }
        return comentarios;
    }

    /**
     * Método que da 'Me gusta' a un perro en específico de parte del usuario con el que esta re-
     * lacionada la key
     *
     * @param key    String que representa la key del usuario
     * @param dog_id String (único) del perro al que se le dará like
     */
    public void likes(String key, String dog_id) {
        String url = "http://modelado2020-1.tk/likes.php?key=" + key + "&dog_id=" + dog_id;
        apiLlamado(url,"GET","");
    }

    /**
     * Obtiene la información de 2000 mil perros
     * Donde
     * > String[i][0] es un url de la imagene del i-ésimo perro
     * > String[i][1] el nombre del i-ésimo perro
     * > String[i][2] el número de likes del i-ésimo perro
     * > String[i][3] el id del i-ésimo perro
     *
     * @param key La llave del usuario, de modo que solo los usuarios registrados pueden acceder a
     *            este metodo
     * @return String[][] de dimención 2000x4 donde el primer indice representa el i-ésimo perro, y
     * cada perro tiene 4 atributos, mencionados arriba.
     */
    public String[][] feed(String key) {
        String url = "http://modelado2020-1.tk/feed.php?key=" + key;
        JsonArray perros_json = this.gson.fromJson(apiLlamado(url,"GET",""), JsonArray.class);
        String[][] dos_mil_perritos = new String[2000][4];

        for (int i = 0; i < 2000; i++) {
            JsonObject perro_json = perros_json.get(i).getAsJsonObject();
            dos_mil_perritos[i][0] = perro_json.get("imagen").getAsString();
            dos_mil_perritos[i][1] = perro_json.get("nombre").getAsString();
            dos_mil_perritos[i][2] = perro_json.get("no_likes").getAsString();
            dos_mil_perritos[i][3] = perro_json.get("perro_id").getAsString();
        }

        return dos_mil_perritos;
    }

    /**
     * Sirve para hacer el login (iniciar sesión) de la api
     *
     * @param usr      El nombre del usario, es sensible a las mayusculas por lo que el usaurio
     *                 "ABC" es distinto al usaurio "abc"
     * @param password La contraseña del usuario
     * @return String que representa la llave del usuario necesaria para mandar a llamar otros méto-
     * dos
     */
    public String login(String usr, String password) {
        String url = "http://modelado2020-1.tk/login.php?user=" + usr + "&pass=" + password;
        JsonObject jsonObject = gson.fromJson(apiLlamado(url,"GET",""), JsonObject.class);
        if (jsonObject.get("status").getAsString().equals("ok"))
            return jsonObject.get("Message").getAsString(); // esto regresa el key
        return "ERROR:usr or password incorrect";
    }

    /**
     * Metodo para hacer el SingUp (Registro) en la api
     *
     * @param usr      El nombre del usuario, es sensible a las mayusculas por lo que el usaurio
     *                 "ABC" es distinto al usaurio "abc"
     * @param password La contraseña del usuario
     * @return boolean true si usuario pudo registrase con éxito, false en otro caso
     */
    public boolean singUp(String usr, String password) {
        String url = "http://modelado2020-1.tk/signup.php?user=" + usr + "&pass=" + password;
        JsonObject jsonObject = gson.fromJson(apiLlamado(url,"GET", ""), JsonObject.class);
        return jsonObject.get("status").getAsString().equals("ok");
    }

    /**
     * Sirve para hacer un llamado a la api
     *
     * @param url String con los parametros GET
     * @return String que lo que haya sido responiddo por la pagina web.
     */
    private String apiLlamado(String url, String metodo, String comentario ) {
        PeticionAsynk a = new PeticionAsynk();
        String salida = "";
        try {
            salida = a.execute(url, metodo, comentario).get();
        } catch (Exception e) {
            System.out.println("fallo apiLlamado" + e.getMessage());
        }
        return salida;
    }

    private class PeticionAsynk extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            StringBuffer response = new StringBuffer();
            if (strings[1].equals("POST")) {
                try {

                    URL link = new URL(url);
                    String postData = "text=" + URLEncoder.encode( strings[2], "UTF-8");
                    byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

                    HttpURLConnection conn = (HttpURLConnection) link.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(postDataBytes);
                    conn.getInputStream();

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                try {
                    URL urlForGetRequest = new URL(url);
                    String readLine = null;
                    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
                    conection.setRequestMethod("GET");
                    int responseCode = conection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(conection.getInputStream()));
                        while ((readLine = in.readLine()) != null) {
                            response.append(readLine);
                        }
                        in.close();
                    } else {
                        System.out.println("Fallo detalles");
                    }
                } catch (Exception e) {
                    System.out.println("DoInBack de PeticionAsynkF error" + e.getMessage());
                    //e.printStackTrace();
                }
            }
            return response.toString();
        }
    }


    /**
     * Descarga una imagen a partir de una url y es usada principalmente para el metodo onBindViewHolder con el fin
     * de que cada imagen sea diferente
     * > El metodo puede fallar si no se cuenta con conexion a internet
     *
     * @param url String De la url de la imagen, puede servir cualquier imagen en linea empezando por
     *            "https://ruta_de_da_imagen"
     * @return Un Bitmap que contiene toda la información de la imagen
     */
    public Bitmap descargaImg(String url) {
        PeticionAsynkr a = new PeticionAsynkr();
        Bitmap salida = null;
        try {
            salida = a.execute(url).get();
        } catch (Exception e) {
            System.out.println("Fallo al descargar la imagen: " + e.getMessage());
            e.printStackTrace();
        }

        return salida;
    }

    /**
     * Clarse PeticionAsynk
     * Esta clase representa una tarea de que realizará de forma asincrona en el programa
     * Solo es usada una vez en descargImg
     */
    private class PeticionAsynkr extends AsyncTask<String, Void, Bitmap> {

        /**
         * Este metodo se hace por detras y solo manda a descarar a la imagen por medio de una url y
         * Obtiene un Bitmap de ella
         *
         * @param strings Url de la imagen empezando con el protocolo "https//"
         * @return Bitmap de la imagen a la cual esta ligada la url en internet
         */
        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageHttpAddress = strings[0];
            Bitmap imagen = null;
            URL imageUrl;

            try {
                imageUrl = new URL(imageHttpAddress);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                imagen = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return imagen;
        }
    }

}
