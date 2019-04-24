package com.example.deathstroke.uniqueoff1;

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

import Service.SetTypefaces;

public class CoopRquest extends AppCompatActivity implements DrawerLayout.DrawerListener {

    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    TextView appbar_textview;
    ImageButton drawer_button;
    private NavigationView navigationView;
    Button share_us, sign_up, sign_in, followed_centers, terms_of_service, Contact_us, edit, exit, bookmarks, coopreq, testbtn,mapbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coop);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        main = findViewById(R.id.main_content);

        appbar_textview = findViewById(R.id.just_appbar_tv);
        appbar_textview.setText("درخواست همکاری");
        Typeface yekan_font = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        appbar_textview.setTypeface(yekan_font);

        drawer_button = findViewById(R.id.drawebtn);

        if (main == null) {
            Toast.makeText(this, "main u looking for is null", Toast.LENGTH_SHORT).show();
        }

        drawer_button.setOnClickListener(v->{
            Toast.makeText(this, "clicked on drawer button ", Toast.LENGTH_SHORT).show();
            drawerLayout.openDrawer(navigationView);
        });

        View headerLayout = navigationView.getHeaderView(0);
        sign_in = headerLayout.findViewById(R.id.header_sign_in);
        sign_up = headerLayout.findViewById(R.id.header_sign_up);
        bookmarks = headerLayout.findViewById(R.id.bookmark_centers);
        terms_of_service = headerLayout.findViewById(R.id.terms);
        share_us = headerLayout.findViewById(R.id.share_us);
        Contact_us = headerLayout.findViewById(R.id.contact_us);
        edit = headerLayout.findViewById(R.id.edit);
        exit = headerLayout.findViewById(R.id.exit);

        SetTypefaces.setButtonTypefaces(yekan_font,sign_in,sign_up,bookmarks,terms_of_service,share_us,Contact_us,edit,exit);

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
