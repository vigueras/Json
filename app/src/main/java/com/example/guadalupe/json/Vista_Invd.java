package com.example.guadalupe.json;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Vista_Invd extends Activity {
    static final String KEY_ID_PLANTA = "id_planta";
    static final String KEY_NOMBRE_PLANTA= "nombre_planta";
    static final String KEY_TIPO_PLANTA = "tipo_planta";
    static final String KEY_COLOR_PLANTA = "color_planta";
    static final String KEY_PRECIO= "precio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_invd);

        Intent in = getIntent();

        // Get XML values from previous intent
        String id_plant = in.getStringExtra(KEY_ID_PLANTA);
        String nombre_plant = in.getStringExtra(KEY_NOMBRE_PLANTA);
        String tipo_plant= in.getStringExtra(KEY_TIPO_PLANTA);
        String color_plant= in.getStringExtra(KEY_COLOR_PLANTA);
        String precio_plant= in.getStringExtra(KEY_PRECIO);


        // Displaying all values on the screen
        TextView lm_idPlan = (TextView) findViewById(R.id.id_c_planta);
        TextView lm_nombPlan= (TextView) findViewById(R.id.c_nombre);
        TextView lm_tipoPlan = (TextView) findViewById(R.id.c_tipo);
        TextView lm_colorPlan = (TextView) findViewById(R.id.c_color);
        TextView lm_precio= (TextView) findViewById(R.id.c_precio);


        lm_idPlan.setText(id_plant);
        lm_nombPlan.setText(nombre_plant);
        lm_tipoPlan.setText(tipo_plant);
        lm_colorPlan.setText(color_plant);
        lm_precio.setText(precio_plant);


    }


}
