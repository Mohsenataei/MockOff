package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Service.CustomTypefaceSpan;
import Service.SetTypefaces;

public class CoopRquest extends AppCompatActivity implements DrawerLayout.DrawerListener {

    BottomNavigationView bottomNavigationView;
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
        drawerLayout.setDrawerElevation(0);
        drawerLayout.addDrawerListener(this);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
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
        bottomNavigationView = findViewById(R.id.navigation);
//        Menu menu = bottomNavigationView.getMenu();
//        MenuItem menuItem = menu.getItem(0);
//        menuItem.setChecked(true);
        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", yekan_font);
        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home :
                    startActivity(new Intent(CoopRquest.this,MainActivity.class));
                    break;
                case R.id.navigation_nearest_off :
                    startActivity(new Intent(CoopRquest.this,Map.class));
                    break;
                case R.id.navigation_my_codes :
                    startActivity(new Intent(CoopRquest.this,MyCodes.class));
                    break;
                case R.id.classification:
                    startActivity(new Intent(CoopRquest.this,Classification.class));
                    break;
            }
            return false;
        });

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
