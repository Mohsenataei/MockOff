package com.example.deathstroke.uniqueoff1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MySpinnerHandler extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);
        spinner = findViewById(R.id.cities_spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(MySpinnerHandler.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.cities));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
    }
}
