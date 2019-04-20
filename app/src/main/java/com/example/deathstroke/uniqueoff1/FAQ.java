package com.example.deathstroke.uniqueoff1;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FAQ extends AppCompatActivity {

    private TextView t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        t1 = findViewById(R.id.textview1);
        t2 = findViewById(R.id.textview2);
        t3 = findViewById(R.id.textview3);
        t4 = findViewById(R.id.textview4);
        t5 = findViewById(R.id.textview5);
        Typeface yekanFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        setTexttypeface(yekanFont,t1,t2,t3,t4,t5);
    }
    private void setTexttypeface(Typeface tf, TextView... textViews){

        for (TextView tv: textViews) {
            tv.setTypeface(tf);
        }

    }
}
