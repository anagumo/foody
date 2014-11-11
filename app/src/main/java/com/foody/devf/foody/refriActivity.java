package com.foody.devf.foody;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class refriActivity extends Activity{

    private Button buscar;
    private Button agregar;
    private ProgressDialog buscando;
    private EditText ingrediente;
    private ListView lista;
    private ArrayList<String> ingredientes; // Arreglo de tipo String que contendrá los ingredientes.

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

                if (ingredient.equals("")) {
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

    }

    public void buscarReceta(View view) {
        buscando = ProgressDialog.show(this, "", "Buscando receta...");
        Intent intent = new Intent(this, activityReceta.class);
        startActivity(intent);
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
