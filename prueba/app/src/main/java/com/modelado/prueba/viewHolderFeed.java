package com.modelado.prueba;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Indica qué información se mostrará en el card item para el feed.
 */
public class viewHolderFeed extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Button meGusta;
    private ImageView imagenPerro;
    private TextView idPerro;
    private TextView nombrePerro;
    private TextView numMeGusta;
    private TextView llaveUsuario;
    private ArrayList<Perro> perros;

    Context contexto;

    /**
     * Crea un viewHolderFeed
     *
     * @param itemView-vista.
     * @param datos-          lista de datos de perros que se mostrará.
     */
    public viewHolderFeed(@NonNull View itemView, ArrayList<Perro> datos, String llave) {
        super(itemView);
        meGusta = itemView.findViewById(R.id.meGusta);
        imagenPerro = itemView.findViewById(R.id.imagenPerro);
        nombrePerro = itemView.findViewById(R.id.nombrePerro);
        numMeGusta = itemView.findViewById(R.id.numMeGusta);
        llaveUsuario = itemView.findViewById(R.id.llaveUsuario);
        idPerro = itemView.findViewById(R.id.idPerro);
        llaveUsuario.setText(llave);

        perros = datos;
        contexto = itemView.getContext();

        imagenPerro.setOnClickListener(this);
        meGusta.setOnClickListener(this); //al dar click se aumenta un me gusta.
    }

    /**
     * Obtiene la imagen de un perro.
     *
     * @return ImageView donde se coloca la imagen.
     */
    public ImageView getImagenPerro() {
        return imagenPerro;
    }

    /**
     * Obtiene el nombre de un perro.
     *
     * @return TextView dónde se pone el nombre.
     */
    public TextView getNombrePerro() {
        return nombrePerro;
    }

    /**
     * Obtiene el número de me gustas del perro.
     *
     * @return TextView en el formato "x me gusta", con x el número de me gustas.
     */
    public TextView getNumMeGusta() {
        return numMeGusta;
    }

    /**
     * Obtiene la lista de Perros que se mostrará en el feed.
     *
     * @return lista de los perros.
     */
    public ArrayList<Perro> getPerros() {
        return perros;
    }

    /**
     * Obtiene el id del perro.
     *
     * @return id único del perro.
     */
    public TextView getIdPerro() {
        return idPerro;
    }


    @Override
    public void onClick(View v) {
        Perro objeto = perros.get(getAdapterPosition());
        ApiCall apiCall = new ApiCall();
        switch (v.getId()) {
            case R.id.meGusta:
                String key = llaveUsuario.getText().toString();
                apiCall.likes(key, objeto.getIdPerro() + "");
                int num = Integer.parseInt(
                        apiCall.perroDetalles(
                                key,
                                objeto.getIdPerro() + "")[3]);
                objeto.setNumMeGusta(num);
                this.numMeGusta.setText(num + " me gusta");
                break;
            case R.id.imagenPerro:
//                ArrayList<String> comentarios = apiCall.perroComentarios(llaveUsuario.getText().toString(), String.valueOf(objeto.getIdPerro()));
//                if (comentarios != null){
//                    ArrayList<String> listaDeComentarios = new ArrayList<>();
                    Intent intent = new Intent(contexto, ListaComentarios.class);
//                    if (comentarios.size()>0){
//                        intent.putStringArrayListExtra("listaApi",comentarios);
//                    }
                    intent.putExtra("idPerrito", idPerro.getText());
                    intent.putExtra("nombrePerrito", nombrePerro.getText().toString());
                    intent.putExtra("numMeGusta", numMeGusta.getText().toString());
                    intent.putExtra("llave", this.llaveUsuario.getText().toString());

//                    intent.putStringArrayListExtra("listaPerro",objeto.getListaComentarios());
                    // Pasaremos por bitmap en el intent
//                    Bitmap bitmap = objeto.descargaImg();
//                    ByteArrayOutputStream byteArrayInputStream= new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayInputStream);
//                    intent.putExtra("byteArray", byteArrayInputStream.toByteArray());
                    contexto.startActivity(intent);

                break;
        }
    }
}
