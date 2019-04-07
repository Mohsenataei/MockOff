package com.example.deathstroke.uniqueoff1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class contact_us extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected ConstraintLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        LayoutInflater factory = getLayoutInflater();
        View view = factory.inflate(R.layout.main_drawer, null);

        drawerLayout = view.findViewById(R.id.drawer_layout);
        if (drawerLayout == null)
        {
            Toast.makeText(this, "Drawer is null dump ass !!!", Toast.LENGTH_SHORT).show();
        }else {
        Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        main = findViewById(R.id.mainall);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close) {
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
