package com.example.guadalupe.json;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {
    private Context context;
    private static String url = "http://resources.260mb.net/plantas-v.json";


    static final String KEY_ID_PLANTA = "id_planta";
    static final String KEY_NOMBRE_PLANTA = "nombre_planta";
    static final String KEY_TIPO_PLANTA = "tipo_planta";
    static final String KEY_COLOR_PLANTA = "color_planta";
    static final String KEY_PRECIO = "precio";

    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();
    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private ListActivity activity;

        // private List<Message> messages;
        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        /** progress dialog to show user that the backup is processing. */

        /** application context. */
        private Context context;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.list_item,new String[] { KEY_ID_PLANTA, KEY_NOMBRE_PLANTA, KEY_TIPO_PLANTA, KEY_COLOR_PLANTA, KEY_PRECIO}, new int[] {
                    R.id.id_plant, R.id.nombre_p, R.id.tipo_p, R.id.color_p, R.id.prec});
            setListAdapter(adapter);

            // selecting single ListView item
            lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // getting values from selected ListItem
                    String tx_idplant = ((TextView) view.findViewById(R.id.id_plant)).getText().toString();
                    String tx_nombrePlant = ((TextView) view.findViewById(R.id.nombre_p)).getText().toString();
                    String txt_tipoPlant=((TextView) view.findViewById(R.id.tipo_p)).getText().toString();
                    String txt_colorPlant=((TextView) view.findViewById(R.id.color_p)).getText().toString();
                    String txt_precioPlan=((TextView) view.findViewById(R.id.prec)).getText().toString();





                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(), Vista_Invd.class);
                    in.putExtra(KEY_ID_PLANTA, tx_idplant);
                    in.putExtra(KEY_NOMBRE_PLANTA, tx_nombrePlant);
                    in.putExtra(KEY_TIPO_PLANTA, txt_tipoPlant);
                    in.putExtra(KEY_COLOR_PLANTA,txt_colorPlant);
                    in.putExtra(KEY_PRECIO, txt_precioPlan);

                    startActivity(in);

                }
            });

        }


        protected Boolean doInBackground(final String... args) {

            JsonParce jParser = new JsonParce();

            // getting JSON string from URL
            JSONArray json = jParser.getJSONFromUrl(url);

            for (int i = 0; i < json.length(); i++) {

                try {
                    JSONObject c = json.getJSONObject(i);
                    String c_id_plant = c.getString(KEY_ID_PLANTA);

                    String c_nomPla = c.getString(KEY_NOMBRE_PLANTA);
                    String c_tipoPla= c.getString(KEY_TIPO_PLANTA);
                    String c_colorPla = c.getString(KEY_COLOR_PLANTA);
                    String c_precio = c.getString(KEY_PRECIO);


                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(KEY_ID_PLANTA, c_id_plant);
                    map.put(KEY_NOMBRE_PLANTA, c_nomPla);
                    map.put(KEY_TIPO_PLANTA, c_tipoPla);
                    map.put(KEY_COLOR_PLANTA, c_colorPla);
                    map.put(KEY_PRECIO, c_precio);

                    jsonlist.add(map);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;

        }

    }
}
