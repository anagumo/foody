package com.foody.devf.foody;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ariana on 07/11/2014.
 */
public class activityReceta extends Activity {

    private TextView receta;
    String dataJson = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            dataJson = extras.getString("sendJson");
        }

        if (dataJson != null) {
            receta = (TextView) findViewById(R.id.recipe);
            receta.setText(dataJson);
        }
    }

    public void empezarApp(View view ){
        Intent intent = new Intent(getBaseContext(),refriActivity.class);
        startActivity(intent);
    }
}
