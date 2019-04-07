package com.example.deathstroke.uniqueoff1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class contact_us extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected ConstraintLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        drawerLayout = findViewById(R.id.drawer_layout);
        main = findViewById(R.id.mainall);
        main.setOnClickListener(view -> Toast.makeText(this, "on main click", Toast.LENGTH_SHORT).show());
        if(drawerLayout == null){
            Toast.makeText(this, "drawerLayout is null", Toast.LENGTH_SHORT).show();
        }
        else {
            Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            drawerLayout.closeDrawers();
            Button drawebtn = findViewById(R.id.drawebtn);
            if(drawebtn == null){
                Toast.makeText(contact_us.this, "drawebtn is null!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(contact_us.this, "drawebtn is not null", Toast.LENGTH_SHORT).show();
                drawebtn.setOnClickListener(view -> {
                    View drawerView = findViewById(R.id.nav_view);
                    Toast.makeText(contact_us.this, "opening drawer", Toast.LENGTH_SHORT).show();
                    drawerLayout.openDrawer(drawerView);
//                    float slideX = drawerView.getWidth() * 100;
//                    main.setTranslationX(-slideX);
                });
            }
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    super.onDrawerSlide(drawerView, slideOffset);
                    // getActionBar().setTitle("is it working ?");
                    Toast.makeText(contact_us.this, "why main content is not moving ?", Toast.LENGTH_SHORT).show();
                    float slideX = drawerView.getWidth() * slideOffset;
                    main.setTranslationX(-slideX);
                }
            };
        }
    }
}
