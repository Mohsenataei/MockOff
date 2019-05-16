package com.example.deathstroke.uniqueoff1;

import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

public class ShowClassification extends AppCompatActivity {

    private Button mbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_classification);

        mbutton = findViewById(R.id.go_back_to_classification);
        mbutton.setOnClickListener(view->{
            finish();
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
