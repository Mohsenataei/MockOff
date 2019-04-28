package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Service.CustomTypefaceSpan;
import Service.SaveSharedPreference;
import Service.SetTypefaces;
import butterknife.ButterKnife;

public class contact_us extends AppCompatActivity implements  DrawerLayout.DrawerListener {
    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;
    TextView appbar_tv,appname;
    Typeface yekanfont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0);

        yekanfont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        navigationView = findViewById(R.id.nav_view);
        main = findViewById(R.id.mainall);
        appbar_tv = findViewById(R.id.just_appbar_tv);
        appbar_tv.setText("ارتباط با ما");
        appbar_tv.setTypeface(yekanfont);
        ImageButton back = findViewById(R.id.back_button);

        back.setOnClickListener( v->{
            finish();
        });

        main.setOnClickListener(view -> Toast.makeText(this, "on main click", Toast.LENGTH_SHORT).show());
        if(drawerLayout == null){
            Toast.makeText(this, "drawerLayout is null", Toast.LENGTH_SHORT).show();
        }
        else {
            //drawerLayout.closeDrawers();
            ImageButton drawebtn = findViewById(R.id.drawebtn);
            if(drawebtn == null){
                Toast.makeText(contact_us.this, "drawebtn is null!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                drawebtn.setOnClickListener(view -> {

                    drawerLayout.openDrawer(navigationView);
                });
            }
        }
        View header_items = navigationView.getHeaderView(0);

        initilizeheaderbuttons(header_items);

        setHeaderitems();
        handleNavDrawerItemClick();
        SetTypefaces.setButtonTypefaces(yekanfont,signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit);

        bottomNavigationView = findViewById(R.id.navigation);
//        Menu menu = bottomNavigationView.getMenu();
//        MenuItem menuItem = menu.getItem(0);
//        menuItem.setChecked(true);
        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", yekanfont);
        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home :
                    startActivity(new Intent(contact_us.this,MainActivity.class));
                    break;
                case R.id.navigation_nearest_off :
                    startActivity(new Intent(contact_us.this,Map.class));
                    break;
                case R.id.navigation_my_codes :
                    startActivity(new Intent(contact_us.this,MyCodes.class));
                    break;
                case R.id.classification:
                    startActivity(new Intent(contact_us.this,Classification.class));
                    break;
            }
            return false;
        });
    }
    private void initilizeheaderbuttons(View header_items) {
        ButterKnife.bind(header_items);
        signup = header_items.findViewById(R.id.header_sign_up);
        signin = header_items.findViewById(R.id.header_sign_in);
        bookmarks = header_items.findViewById(R.id.bookmark_centers);
        followed_centers = header_items.findViewById(R.id.followed_centers);
        frequently_asked_questions = header_items.findViewById(R.id.header_faq);
        terms_off_service = header_items.findViewById(R.id.terms);
        contactus = header_items.findViewById(R.id.contact_us);
        share_with_friends = header_items.findViewById(R.id.share_us);
        exit = header_items.findViewById(R.id.exit);
        edit = header_items.findViewById(R.id.edit);
        appname = header_items.findViewById(R.id.header_app_name);
    }
    private void handleNavDrawerItemClick(){
        signup.setOnClickListener(view->{
            startActivity(new Intent(contact_us.this,SignUpActivity.class));
        });
        signin.setOnClickListener(view->{
            startActivity(new Intent(contact_us.this,SingInActivity.class));
        });

        bookmarks.setOnClickListener(view -> {
            startActivity(new Intent(contact_us.this,BookMarkedPosts.class));
        });

        followed_centers.setOnClickListener(view -> {
            startActivity(new Intent(contact_us.this,FollowedShops.class));
            //drawerLayout.closeDrawer(navigationView);
        });

        frequently_asked_questions.setOnClickListener(view -> {
            startActivity(new Intent(contact_us.this,FAQ.class));
        });

//        terms_off_service.setOnClickListener(view->{
//            startActivity(new Intent(MyCodes.this,.class));
//        });

        contactus.setOnClickListener(view->{
            //startActivity(new Intent(contact_us.this,contact_us.class));
            drawerLayout.closeDrawer(navigationView);
        });

        share_with_friends.setOnClickListener(view -> {
            //startActivity(new Intent(MyCodes.this,BookMarkedPosts.class));
            Toast.makeText(this, "پیشنهاد به دوستان", Toast.LENGTH_SHORT).show();
        });

        exit.setOnClickListener(view ->{
            //finish();
            System.exit(0);
        });

        edit.setOnClickListener(view->{
            Toast.makeText(this, "this part is yet to be complete", Toast.LENGTH_SHORT).show();
        });


    }
    private void setHeaderitems() {
        if(SaveSharedPreference.getAPITOKEN(contact_us.this).length() > 0  ){
            signin.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            appname.setVisibility(View.VISIBLE);
            appname.setText(R.string.title_activity_test_navigation_drawer);
            appname.setTypeface(yekanfont);
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
