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
import android.text.Html;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    final static String TAG = "jelal";

    Button share_us, sign_up, sign_in, followed_centers, terms_of_service, Contact_us, edit, exit, bookmarks, coopreq, testbtn,mapbutton;
    Spinner cities;
    TextView hotoffs,sample_test,appname;
    ViewPager viewPager;
    TabLayout tabLayout;
    int images[] = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};
    SliderAdapter sliderAdapter;
    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    ImageView bookmark1, bookmark2;
    Boolean book1_flag ,book2_flag;
    //    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    switch (item.getItemId()) {
//
//                    }
//                    return false;
//                }
//            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        // supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        book1_flag = book2_flag = false;
        //final LinearLayout tv = findViewById(R.id.contnet);
        Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        main = findViewById(R.id.mainall3);
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        main.setOnClickListener(view -> {
            startActivity(new Intent(this,Map.class));
            Toast.makeText(this, "on main click", Toast.LENGTH_SHORT).show();
        });
        if(drawerLayout == null){
            Toast.makeText(this, "drawerLayout is null", Toast.LENGTH_SHORT).show();
        }
        else {
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            drawerLayout.setDrawerElevation(0);
            //drawerLayout.closeDrawers();
            ImageButton drawebtn = findViewById(R.id.drawebtn);
            if(drawebtn == null){
                Toast.makeText(MainActivity.this, "drawebtn is null!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                drawebtn.setOnClickListener(view -> {
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    drawerLayout.openDrawer(navigationView);
                });
            }
        }
        hotoffs = findViewById(R.id.hottest_offs_txtvw);
        if (hotoffs == null) {
            Log.d(TAG, "onCreate: hott of is null");
        } else
        {
            hotoffs.setTypeface(hintFont);
            hotoffs.setOnClickListener(v->{
                startActivity(new Intent(this,Shop.class));
            });
        }
        testbtn = findViewById(R.id.generateqrcode);
        testbtn.setOnClickListener(v->{
            startActivity(new Intent(this,QRCode.class));
        });

        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.header, null);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        String text = "<strike><font color=\'#757575\'>Some text</font></strike>";

        sample_test = findViewById(R.id.card_description);
        sample_test.setText(Html.fromHtml(text));

        View headerLayout = navigationView.getHeaderView(0);

        appname = headerLayout.findViewById(R.id.header_app_name);
        appname.setTypeface(hintFont);
        //appname.setVisibility(View.VISIBLE);
        sign_up = headerLayout.findViewById(R.id.header_sign_up);
        sign_up.setTypeface(hintFont);
       // sign_up.setVisibility(View.INVISIBLE);


        share_us = headerLayout.findViewById(R.id.share_us);
        share_us.setOnClickListener(v->{
            startActivity(new Intent(this,CoopRquest.class));
        });

        sign_in = headerLayout.findViewById(R.id.header_sign_in);
       // sign_in.setVisibility(View.INVISIBLE);

        followed_centers = headerLayout.findViewById(R.id.followed_centers);
        followed_centers.setTypeface(hintFont);
        followed_centers.setOnClickListener(v->{
            startActivity(new Intent(this,FAQ.class));
        });

        terms_of_service = headerLayout.findViewById(R.id.terms);
        terms_of_service.setTypeface(hintFont);

        Contact_us = headerLayout.findViewById(R.id.contact_us);
        Contact_us.setTypeface(hintFont);
        Contact_us.setOnClickListener(v -> {
            Toast.makeText(this, "on contact us click", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, contact_us.class));
        });
        bookmarks = headerLayout.findViewById(R.id.bookmark_centers);
        bookmarks.setTypeface(hintFont);

        bookmark1 = findViewById(R.id.bookmark1);
        bookmark1.setOnClickListener(v->{
            if (!book1_flag){
                doBookMark(bookmark1);
                book1_flag = true;
            }else {
                undoBookMark(bookmark1);
                book1_flag = false;
            }
        });

        bookmark2 = findViewById(R.id.bookmark2);
        bookmark2.setOnClickListener(v->{
            if (!book2_flag){
                doBookMark(bookmark2);
                book2_flag = true;
            }else {
                undoBookMark(bookmark2);
                book2_flag = false;
            }
        });



        bookmark2 = findViewById(R.id.bookmark2);

        edit = headerLayout.findViewById(R.id.edit);
        edit.setTypeface(hintFont);

        exit = headerLayout.findViewById(R.id.exit);
        exit.setTypeface(hintFont);

        share_us.setTypeface(hintFont);
//        share_us.setOnClickListener(v -> {
//            Intent moveToSignIn = new Intent(this, SingInActivity.class);
//            Drawable img = getApplicationContext().getDrawable(R.drawable.ic_arrow_left);
//            share_us.setCompoundDrawables(img, null, null, null);
//            startActivity(moveToSignIn);
//        });
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

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities,R.layout.spinner_text_view);
            adapter.setDropDownViewResource(R.layout.spinner_text_view);
            try {

                cities.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("in navigation :", "onCreate: " + e);
            }
        }
        tabLayout = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(40, 0, 40, 0);
        viewPager.setPageMargin(20);
        sliderAdapter = new SliderAdapter(this, images);
        viewPager.setAdapter(sliderAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private void doBookMark(ImageView imageView) {
        imageView.setImageResource(R.drawable.ic_bookmark_onclick);
    }

    private void undoBookMark(ImageView imageView){
        imageView.setImageResource(R.drawable.ic_bookmark);
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
    public void onDrawerOpened(View arg0) {
        //write your code
    }

    @Override
    public void onDrawerClosed(View arg0) {
        //write your code
    }



    @Override
    public void onDrawerStateChanged(int arg0) {
        //write your code
    }
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        float slideX = drawerView.getWidth() * slideOffset;
        main.setTranslationX(-slideX);
    }
}
