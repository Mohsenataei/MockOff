package com.example.deathstroke.uniqueoff1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MySpinnerHandler extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.header);
            spinner = findViewById(R.id.cities_spinner);
            ArrayAdapter<CharSequence> myAdapter = new ArrayAdapter<>(MySpinnerHandler.this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cities));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.e("asad", "my spinner handler onCreate, e: " + e );
            Toast.makeText(this, "bug in spinner handler, e: " + e, Toast.LENGTH_SHORT);
        }
    }
}
