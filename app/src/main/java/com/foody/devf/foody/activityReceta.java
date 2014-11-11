package com.foody.devf.foody;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ariana on 07/11/2014.
 */
public class activityReceta extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);

    }

    public void empezarApp(View view ){
        Intent intent = new Intent(getBaseContext(),refriActivity.class);
        startActivity(intent);
    }
}
