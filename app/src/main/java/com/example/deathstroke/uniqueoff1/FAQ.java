package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Service.CustomTypefaceSpan;
import Service.SaveSharedPreference;
import Service.SetTypefaces;
import bottomsheetdialoges.ConfirmExitbottomSheet;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class FAQ extends AppCompatActivity implements DrawerLayout.DrawerListener {

    BottomNavigationView bottomNavigationView;
    private TextView t1,t2,t3,t4,t5,t6;
    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    protected NavigationView navigationView;
    TextView appbar_tv,appname;
    Typeface yekanfont;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;
    ImageButton drawer,backbtn;
    private Spinner cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        t1 = findViewById(R.id.textview1);
        t2 = findViewById(R.id.textview2);
        t3 = findViewById(R.id.textview3);
        t4 = findViewById(R.id.textview4);
        t5 = findViewById(R.id.textview5);
        drawer = findViewById(R.id.drawebtn);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0);
        main = findViewById(R.id.mainall);
        appbar_tv = findViewById(R.id.just_appbar_tv);
        appbar_tv.setText("سوالات متداول");
        appbar_tv.setTypeface(yekanfont);
        backbtn = findViewById(R.id.back_button);

        backbtn.setOnClickListener(view->{
            finish();
        });

        if (main == null) {
            Toast.makeText(this, "main u looking for is null", Toast.LENGTH_SHORT).show();
        }

        drawer.setOnClickListener(v->{
            Toast.makeText(this, "clicked on drawer button ", Toast.LENGTH_SHORT).show();
            drawerLayout.openDrawer(navigationView);
        });

        Typeface yekanFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        //setTexttypeface(yekanFont,t1,t2,t3,t4,t5);

        View header_items = navigationView.getHeaderView(0);

        initilizeheaderbuttons(header_items);

        setHeaderitems();
        handleNavDrawerItemClick();
        SetTypefaces.setButtonTypefaces(yekanFont,signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit);


        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.getMenu().setGroupCheckable(0,false,true);
//        Menu menu = bottomNavigationView.getMenu();
//        MenuItem menuItem = menu.getItem(0);
//        menuItem.setChecked(true);
        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", yekanFont);
        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
            mMenuitem.setChecked(false);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home :
                    startActivity(new Intent(FAQ.this,MainActivity.class));
                    break;
                case R.id.navigation_nearest_off :
                    startActivity(new Intent(FAQ.this,Map.class));
                    break;
                case R.id.navigation_my_codes :
                    startActivity(new Intent(FAQ.this,MyCodes.class));
                    break;
                case R.id.classification:
                    startActivity(new Intent(FAQ.this,Classification.class));
                    break;
            }
            return false;
        });

        cities = header_items.findViewById(R.id.cities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities, R.layout.spinner_text_view_1);
        adapter.setDropDownViewResource(R.layout.spinner_text_view);
        cities.setAdapter(adapter);

        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = parent.getItemAtPosition(position).toString();
                SaveSharedPreference.setCity(FAQ.this,city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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
            startActivity(new Intent(FAQ.this,SignUpActivity.class));
        });
        signin.setOnClickListener(view->{
            startActivity(new Intent(FAQ.this,SingInActivity.class));
        });

        bookmarks.setOnClickListener(view -> {
            startActivity(new Intent(FAQ.this,BookMarkedPosts.class));
        });

        followed_centers.setOnClickListener(view -> {
            startActivity(new Intent(FAQ.this,FollowedShops.class));
            //drawerLayout.closeDrawer(navigationView);
        });

        frequently_asked_questions.setOnClickListener(view -> {
            //startActivity(new Intent(FAQ.this,FAQ.class));
            drawerLayout.closeDrawer(navigationView);
        });

//        terms_off_service.setOnClickListener(view->{
//            startActivity(new Intent(MyCodes.this,.class));
//        });

        contactus.setOnClickListener(view->{
            startActivity(new Intent(FAQ.this,contact_us.class));
        });

        share_with_friends.setOnClickListener(view -> {
            //startActivity(new Intent(MyCodes.this,BookMarkedPosts.class));
            Toast.makeText(this, "پیشنهاد به دوستان", Toast.LENGTH_SHORT).show();
        });

        exit.setOnClickListener(view ->{
            //finish();
            //System.exit(0);
            ConfirmExitbottomSheet confirmExitbottomSheet = new ConfirmExitbottomSheet();
            confirmExitbottomSheet.show(getSupportFragmentManager(),"ConfirmExit");
        });

        edit.setOnClickListener(view->{
            //Toast.makeText(this, "this part is yet to be complete", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,EditProfie.class));
        });


    }
    private void setHeaderitems() {
        if(SaveSharedPreference.getAPITOKEN(FAQ.this).length() > 0  ){
            signin.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            appname.setVisibility(View.VISIBLE);
            appname.setText(R.string.title_activity_test_navigation_drawer);
            appname.setTypeface(yekanfont);
        }
    }
    private void setTexttypeface(Typeface tf, TextView... textViews){

        for (TextView tv: textViews) {
            tv.setTypeface(tf);
        }

    }

    private void setButtonTypeface(Typeface tf, Button... buttons){
        for (Button btn: buttons){
            btn.setTypeface(tf);
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
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
