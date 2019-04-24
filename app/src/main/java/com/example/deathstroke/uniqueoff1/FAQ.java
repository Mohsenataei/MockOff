package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FAQ extends AppCompatActivity implements DrawerLayout.DrawerListener {

    private TextView t1,t2,t3,t4,t5,t6;
    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    protected NavigationView navigationView;
    Button share_us, sign_up, sign_in, followed_centers, terms_of_service, Contact_us, edit, exit, bookmarks, coopreq, testbtn,mapbutton;
    ImageButton drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        t1 = findViewById(R.id.textview1);
        t2 = findViewById(R.id.textview2);
        t3 = findViewById(R.id.textview3);
        t4 = findViewById(R.id.textview4);
        t5 = findViewById(R.id.textview5);
        t6 = findViewById(R.id.just_appbar_tv);
        drawer = findViewById(R.id.drawebtn);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        main = findViewById(R.id.mainall);

        if (main == null) {
            Toast.makeText(this, "main u looking for is null", Toast.LENGTH_SHORT).show();
        }

        drawer.setOnClickListener(v->{
            Toast.makeText(this, "clicked on drawer button ", Toast.LENGTH_SHORT).show();
            navigationView = findViewById(R.id.nav_view);
            drawerLayout.openDrawer(navigationView);
        });

        t6.setText("سوالات متداول");
        Typeface yekanFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        setTexttypeface(yekanFont,t1,t2,t3,t4,t5,t6);
        View headerLayout = navigationView.getHeaderView(0);

        sign_in = headerLayout.findViewById(R.id.header_sign_in);
        sign_up = headerLayout.findViewById(R.id.header_sign_up);
        bookmarks = headerLayout.findViewById(R.id.bookmark_centers);
        terms_of_service = headerLayout.findViewById(R.id.terms);
        share_us = headerLayout.findViewById(R.id.share_us);
        Contact_us = headerLayout.findViewById(R.id.contact_us);
        edit = headerLayout.findViewById(R.id.edit);
        exit = headerLayout.findViewById(R.id.exit);
        setButtonTypeface(yekanFont,sign_in,sign_up,bookmarks,terms_of_service,share_us,Contact_us,edit,exit);



    }
    private void setTexttypeface(Typeface tf, TextView... textViews){

        for (TextView tv: textViews) {
            tv.setTypeface(tf);
        }

    }

    private void setButtonTypeface(Typeface tf, Button... buttons){
        for (Button btn: buttons){
            btn.setTypeface(tf);
        }
    }
    @Override
    public void onDrawerOpened(View arg0) {
        //write your code
    }

    @Override
    public void onDrawerClosed(View arg0) {
        //write your code
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        //write your code
        // getActionBar().setTitle("is it working ?");
        float slideX = drawerView.getWidth() * slideOffset;
        main.setTranslationX(-slideX);
    }

    @Override
    public void onDrawerStateChanged(int arg0) {
        //write your code
    }
}
