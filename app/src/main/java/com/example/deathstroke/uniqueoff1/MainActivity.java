package com.example.deathstroke.uniqueoff1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import TestNavigationDrawer.*;



public class MainActivity extends BaseActivity {

    boolean isFragmentLoaded;
    Fragment menuFragment;
    TextView title;
    ImageView menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("onCreate", "onCreate: " );
        super.onCreate(savedInstanceState);
        initAddlayout(R.layout.activity_main);
        Log.e("onCreate", "onCreate: " );
        title = findViewById(R.id.title_top);
        menuButton = findViewById(R.id.menu_icon);
        title.setText("Menu Activity");


        menuButton.setOnClickListener((View)-> {

                try {
                    if (!isFragmentLoaded) {
                        loadFragment();
                        //title.setText("Profile");
                    } else {
                        if (menuFragment != null) {
                            if (menuFragment.isAdded()) {
                                hideFragment();
                            }
                        }
                    }
                }catch (Exception e){
                    Log.e("Menu", "onCreate: menu on click, e: " + e);
                }
        });
    }

    public void hideFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up);
        fragmentTransaction.remove(menuFragment);
        fragmentTransaction.commit();
        menuButton.setImageResource(R.drawable.ic_menu);
        isFragmentLoaded = false;
        title.setText("Main Activity");
    }
    public void loadFragment(){
        FragmentManager fm = getSupportFragmentManager();
        menuFragment = fm.findFragmentById(R.id.container);
        menuButton.setImageResource(R.drawable.ic_up_arrow);
        if(menuFragment == null){
            menuFragment = new MenuFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up);
            fragmentTransaction.add(R.id.container,menuFragment);
            fragmentTransaction.commit();
        }

        isFragmentLoaded = true;
    }
}





















/*public class MainActivity extends AppCompatActivity {

    Spinner nav_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        final LinearLayout content = (LinearLayout) findViewById(R.id.content);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        nav_spinner = findViewById(R.id.drawer_spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.cities));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // nav_spinner.setAdapter(myAdapter);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                content.setTranslationX(slideX);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

//        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//      //  FragmentManager fragmentManager = getFragmentManager();
//        int id = item.getItemId();
//
//        if (id == R.id.termsofservice) {
//            // Handle the camera action
//        } else if (id == R.id.contactus) {
//
//        } else if (id == R.id.share) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
*/