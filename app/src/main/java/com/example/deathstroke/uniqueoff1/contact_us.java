package com.example.deathstroke.uniqueoff1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class contact_us extends AppCompatActivity implements  DrawerLayout.DrawerListener {
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected ConstraintLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        main = findViewById(R.id.mainall);

        main.setOnClickListener(view -> Toast.makeText(this, "on main click", Toast.LENGTH_SHORT).show());
        if(drawerLayout == null){
            Toast.makeText(this, "drawerLayout is null", Toast.LENGTH_SHORT).show();
        }
        else {
            Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            //drawerLayout.closeDrawers();
            Button drawebtn = findViewById(R.id.drawebtn);
            if(drawebtn == null){
                Toast.makeText(contact_us.this, "drawebtn is null!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                drawebtn.setOnClickListener(view -> {
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    drawerLayout.openDrawer(navigationView);
                });
            }
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
        Toast.makeText(contact_us.this, "why main content is not moving ?", Toast.LENGTH_SHORT).show();
        float slideX = drawerView.getWidth() * slideOffset;
        main.setTranslationX(-slideX);
    }

    @Override
    public void onDrawerStateChanged(int arg0) {
        //write your code
    }
}
