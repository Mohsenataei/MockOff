package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "jelal";

    Button share_us, sign_up, sign_in, followed_centers, terms_of_service, Contact_us, edit, exit, bookmarks, coopreq, testbtn;
    Spinner cities;
    TextView hotoffs;
    ViewPager viewPager;
    TabLayout tabLayout;
    int images[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};
    SliderAdapter sliderAdapter;
    protected DrawerLayout drawerLayout;
    CardView cardView;

    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected ConstraintLayout main;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        // supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "is it happen for second time?", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.main_drawer);
        drawerLayout = findViewById(R.id.drawer_layout);
        //final LinearLayout tv = findViewById(R.id.contnet);
        Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        main = findViewById(R.id.mainall);
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                // getActionBar().setTitle("is it working ?");
                float slideX = drawerView.getWidth() * slideOffset;
                main.setTranslationX(-slideX);
            }
        };
        hotoffs = findViewById(R.id.hottest_offs_txtvw);
        if (hotoffs == null) {
            Log.d(TAG, "onCreate: hott of is null");
        } else hotoffs.setTypeface(hintFont);

        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.header, null);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);

        sign_up = headerLayout.findViewById(R.id.header_sign_up);
        sign_up.setTypeface(hintFont);


        share_us = headerLayout.findViewById(R.id.share_us);
        sign_in = headerLayout.findViewById(R.id.header_sign_in);

        followed_centers = headerLayout.findViewById(R.id.followed_centers);
        followed_centers.setTypeface(hintFont);

        terms_of_service = headerLayout.findViewById(R.id.terms);
        terms_of_service.setTypeface(hintFont);

        Contact_us = headerLayout.findViewById(R.id.contact_us);
        Contact_us.setTypeface(hintFont);
        Contact_us.setOnClickListener(v -> {
            startActivity(new Intent(this, contact_us.class));
        });
        bookmarks = headerLayout.findViewById(R.id.bookmark_centers);
        bookmarks.setTypeface(hintFont);

        edit = headerLayout.findViewById(R.id.edit);
        edit.setTypeface(hintFont);

        exit = headerLayout.findViewById(R.id.EXIT);
        exit.setTypeface(hintFont);
        coopreq = findViewById(R.id.gotoSHOP);

        try {
            coopreq.setOnClickListener(v -> {
                startActivity(new Intent(this, Shop.class));

            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("jelal", "onCreate: ", e);
        }

        share_us.setTypeface(hintFont);
        share_us.setOnClickListener(v -> {
            Intent moveToSignIn = new Intent(this, SingInActivity.class);
            Drawable img = getApplicationContext().getDrawable(R.drawable.ic_arrow_left);
            share_us.setCompoundDrawables(img, null, null, null);
            startActivity(moveToSignIn);
        });
        sign_up.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
        sign_in.setTypeface(hintFont);

        try {

            sign_in.setOnClickListener(v -> {
                startActivity(new Intent(getApplicationContext(), SingInActivity.class));
                Toast.makeText(this, "bug in sign in click", Toast.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        cities = headerLayout.findViewById(R.id.cities_spinner);
        //cities.setOnItemSelectedListener(getApplicationContext());
        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (cities == null) {
            Log.e("asad :", "spinner is null!!");
        } else {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.cities, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            try {

                cities.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("in navigation :", "onCreate: " + e);
            }
        }
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.border_color));
        // navigationView.setNavigationItemSelectedListener(this);
        tabLayout = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(40, 0, 40, 0);
        viewPager.setPageMargin(20);
        sliderAdapter = new SliderAdapter(this, images);
        viewPager.setAdapter(sliderAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
        cardView = findViewById(R.id.left_card_view);
        cardView.setOnClickListener(v->{
            Toast.makeText(this, "it's workin yay", Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public void onBackPressed() {
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
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

    private void HandleOnclickItem() {

    }


}
