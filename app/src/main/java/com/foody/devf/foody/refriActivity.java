package com.foody.devf.foody;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
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


public class refriActivity extends FragmentActivity {

    Button buscar;
    Button agregar;
    ProgressDialog progressDialog;
    EditText ingrediente;
    ListView lista;
  //  String ingredient;
  //  TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refri);


       // FragmentManager fm = getSupportFragmentManager();


        ingrediente = (EditText) findViewById(R.id.ingrediente);
        lista = (ListView) findViewById(R.id.lista);
        agregar = (Button) findViewById(R.id.agregar);
        buscar = (Button) findViewById(R.id.search);
        //prueba = (TextView) findViewById(R.id.prueba);
        //if (fm.findFragmentById(android.R.id.content) == null) {
          //  SimpleListFragment list = new SimpleListFragment();
            //fm.beginTransaction().add(android.R.id.content, list).commit();
        //}
    }

  /*  public static class SimpleListFragment extends ListFragment
    {

        String ingredient;
        SimpleListFragment(String test) {
            ingredient = test;
        }

        String[] numbers_text = new String[] { "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine", "ten", "eleven",
                "twelve", "thirteen", "fourteen", "fifteen" };
        String[] numbers_digits = new String[] { "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "11", "12", "13", "14", "15" };

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            //Toast.makeText(getActivity(),  numbers_digits[(int), Toast.LENGTH_LONG).show();
            //new CustomToast(getActivity(), numbers_digits[(int) id]);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    inflater.getContext(), android.R.layout.simple_list_item_1,
                    numbers_digits);
            setListAdapter(adapter);
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        public void setString(String ingredient) {

            this.ingredient = ingredient;

        }

    }*/

    public void addIngrediente(View view) {
       /* agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredient = ingrediente.getText().toString();
                Log.i("Variable obtenida", ingredient);
                prueba.setText(ingredient);*/
                Toast.makeText(this, "Agregado!", Toast.LENGTH_SHORT).show();
         //   }
       // });
    }

    public void buscarReceta(View view) {
        progressDialog = ProgressDialog.show(this, "", "Buscando receta...");
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
