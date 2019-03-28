package com.example.deathstroke.uniqueoff1;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;

public class Pop extends AppCompatActivity {

    private TextView edu_textview, serv_textview, res_textview,
                     cloth_textview, health_textview, cafe_textview,
                     amus_textview, make_textview, cult_textview;


    @Override
    protected void onCreate(Bundle SaveInstanseState) {
        super.onCreate(SaveInstanseState);
        setContentView(R.layout.popup_window);
        edu_textview = findViewById(R.id.coop_educational);
        serv_textview = findViewById(R.id.coop_services);
        res_textview = findViewById(R.id.coop_restaurant);
        cloth_textview = findViewById(R.id.coop_cloth);
        health_textview = findViewById(R.id.coop_health);
        cafe_textview = findViewById(R.id.coop_cafe);
        amus_textview = findViewById(R.id.coop_amusement);
        make_textview = findViewById(R.id.coop_makeup);
        cult_textview = findViewById(R.id.coop_culture);

        Typeface mainfont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf"); // works ? yes

      //  setFonts(mainfont,edu_textview,serv_textview,res_textview,cloth_textview,health_textview,cafe_textview,amus_textview,make_textview,cult_textview);
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, (int) (height * .7));
    }

    private void setFonts(Typeface tf, TextView... textViews) {

        for (TextView et : textViews) {
            et.setTypeface(tf);
        }

    }
}
