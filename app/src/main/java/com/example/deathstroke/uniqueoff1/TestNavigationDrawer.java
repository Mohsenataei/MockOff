package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class TestNavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button share_us,sign_up,sign_in;
    Spinner cities;
    TextView hotoffs;
    ViewPager viewPager;
    TabLayout tabLayout;
    int images[] = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3};
    SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_navigation_drawer);
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Log.e("esmal agha", "onCreate: TestNavigation Drawer" );
        //final LinearLayout tv = findViewById(R.id.contnet);
        Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        final ConstraintLayout main = findViewById(R.id.mainall);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
               // getActionBar().setTitle("is it working ?");
                float slideX = drawerView.getWidth() * slideOffset;
                main.setTranslationX(-slideX);
            }
        };
        hotoffs = findViewById(R.id.hottest_offs_txtvw);
        hotoffs.setTypeface(hintFont);

        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.header,null);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);

        sign_up = headerLayout.findViewById(R.id.header_sign_in);
        sign_up.setTypeface(hintFont);

        share_us = headerLayout.findViewById(R.id.share_us);
        sign_in = headerLayout.findViewById(R.id.header_sign_up);
        share_us.setTypeface(hintFont);
        //share_us.setText("Clicked");
        share_us.setOnClickListener(v->{
            Intent moveToSignIn = new Intent(this,SingInActivity.class);
            startActivity(moveToSignIn);
        });
//        sign_up.setOnClickListener(v->{
//            startActivity(new Intent(this,SignUpActivity.class));
//        });
        sign_in.setTypeface(hintFont);
        sign_in.setOnClickListener(v->{
            startActivity(new Intent(this,SingInActivity.class));
        });
        cities = findViewById(R.id.cities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        try {
            cities.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("in navigation :", "onCreate: "+ e );
        }


        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }catch (NullPointerException e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.border_color));
        navigationView.setNavigationItemSelectedListener(this);
        tabLayout = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(this,images);
        viewPager.setAdapter(sliderAdapter);
        tabLayout.setupWithViewPager(viewPager,true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
