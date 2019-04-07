package com.example.deathstroke.uniqueoff1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Shop extends AppCompatActivity {

    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        b1= findViewById(R.id.shop_info_button);
        b2= findViewById(R.id.shop_attr_button);
        b3= findViewById(R.id.shop_map_button);
        b4= findViewById(R.id.shop_off_button);

        b1.setOnClickListener(view->{
            shop_info_fragment info_fragment = new shop_info_fragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frg_holder,info_fragment);
            ft.commit();

        });
        b2.setOnClickListener(view ->{
            shop_map_fragment map_fragment = new shop_map_fragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frg_holder,map_fragment);
            ft.commit();
        });
    }

}
