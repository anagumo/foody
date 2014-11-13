package com.foody.devf.foody;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class refriActivity extends Activity{

    private Button buscar;
    private Button agregar;
    private ProgressDialog buscando;
    private EditText ingrediente;
    private ListView lista;
    private ArrayList<String> ingredientes; // Arreglo de tipo String que contendrá los ingredientes.
    //volley
    private String urlJson = "https://agile-refuge-1884.herokuapp.com/formulas?format=json&ingredients=1";
    private static String TAG = refriActivity.class.getSimpleName();

    // temporary string to show the parsed response
    String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refri);

        ingrediente = (EditText) findViewById(R.id.ingrediente);
        lista = (ListView) findViewById(R.id.lista);
        agregar = (Button) findViewById(R.id.agregar);
        buscar = (Button) findViewById(R.id.search);

        //Focus
        ingredientes = new ArrayList<String>();
        //Adapter
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredientes);
        lista.setAdapter(listAdapter); //Al listview "lista", se le suministra los datos "listAdapter" a través de "setAdapter"

        //escuchar al botón agregar
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredient = ingrediente.getText().toString();

                if (ingredient.equals(null)) {
                    Toast personalizado = Toast.makeText(getBaseContext(), "Escribe algo", Toast.LENGTH_SHORT);
                     personalizado.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);

                     personalizado.show();
                }
                else{
                    ingredientes.add(ingrediente.getText().toString()); //Al arreglo Ingredientes agregamos el dato obtenido del editText
                    ingrediente.setText(""); //Imprime el dato obtenido

                    //Es como refresh o algo así
                    ArrayAdapter<String> listAdapter = (ArrayAdapter) lista.getAdapter();
                    listAdapter.notifyDataSetChanged();
                    Log.i("Boton: ", "Boton apretado"); //Para pruebas internas de código

                    Toast dos = Toast.makeText(getBaseContext(), "Agregado!", Toast.LENGTH_SHORT);
                    dos.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
                    dos.show();
                }
            }
        });

        //escuchar al botón buscar
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // making json array request
                makeJsonArrayRequest();
            }
        });

    }

    private void makeJsonArrayRequest() {

        RequestQueue queue = ClassController.getInstance(getApplicationContext()).getRequestQueue();

        buscando = ProgressDialog.show(this, "", "Buscando receta...");

        JsonArrayRequest req = new JsonArrayRequest(urlJson, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    //Log.d(TAG, response.toString());

                try {
                    // Parsing json array response
                    // loop through each json object
                    jsonResponse = "";
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject receta = (JSONObject) response.get(i);

                        String name = receta.getString("nombre");
                        String time = receta.getString("tiempo");
                        String type = receta.getString("tipo");
                        String description = receta.getString("descripcion");

                        // Todos estos datos se pueden guardar en un Bundle, hacerlo así es mala práctica

                        jsonResponse += "Receta: " + name + "\n\n";
                        jsonResponse += "Tiempo: " + time + "\n\n";
                        jsonResponse += "Tipo: " + type + "\n\n";
                        jsonResponse += "Pasos: " + description + "\n\n";
                    }

                    Intent intent = new Intent(getBaseContext(), activityReceta.class);
                    intent.putExtra("sentJson", jsonResponse);
                    startActivity(intent);


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Errorr: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }

            buscando.hide();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d("error", "Error: " + error.getMessage());
            Toast.makeText(getApplicationContext(),
                    error.getMessage(), Toast.LENGTH_SHORT).show();
            buscando.hide();
        }
    });

        queue.add(req);
    }

    private void showpDialog() {
        if (!buscando.isShowing())
            buscando.show();
    }

    private void hidepDialog() {
        if (buscando.isShowing())
            buscando.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.refri, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
