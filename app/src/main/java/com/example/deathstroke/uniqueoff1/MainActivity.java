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
import android.text.SpannableStringBuilder;
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
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import Service.CustomTypefaceSpan;
import Service.SaveSharedPreference;
import Service.SetTypefaces;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    final static String TAG = "jelal";

    TextView appname;
    Typeface yekanfont;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;
    Spinner cities;
    ViewPager viewPager;
    TabLayout tabLayout;
    int images[] = {R.drawable.slider3,R.drawable.slider1, R.drawable.slider2, R.drawable.slider3,R.drawable.slider1};
    SliderAdapter sliderAdapter;
    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    ImageButton drawebtn;
    NavigationView navigationView;
    ImageView bookmark1, bookmark2;
    Boolean book1_flag ,book2_flag;
    BottomNavigationView bottomNavigationView;
    android.support.v7.widget.SearchView searchView;
    Button qrcode,shop;
    int mCurrentPosition;
    int lastPageIndex = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        searchView = findViewById(R.id.searchView);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        book1_flag = book2_flag = false;
        Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        main = findViewById(R.id.mainall3);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0);

        drawebtn = findViewById(R.id.drawebtn);
        drawebtn.setOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
        });


        qrcode = findViewById(R.id.generateqrcode);
        qrcode.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,RetrofitTest.class));
        });
        shop = findViewById(R.id.shop_button);
        shop.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,Shop.class));
        });

        View header_items = navigationView.getHeaderView(0);

        initilizeheaderbuttons(header_items);
        setHeaderitems();
        handleNavDrawerItemClick();

        SetTypefaces.setButtonTypefaces(hintFont,signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit);

        cities = header_items.findViewById(R.id.cities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities, R.layout.spinner_text_view_1);
        adapter.setDropDownViewResource(R.layout.spinner_text_view);
        cities.setAdapter(adapter);
        cities.setAdapter(adapter);
        tabLayout = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(40, 0, 40, 0);
        viewPager.setPageMargin(20);
        sliderAdapter = new SliderAdapter(this, images);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setCurrentItem(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(mCurrentPosition==0) viewPager.setCurrentItem(lastPageIndex-1,false);
                if(mCurrentPosition==lastPageIndex) viewPager.setCurrentItem(1,false);

            }
        });

        tabLayout.setupWithViewPager(viewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);


        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", hintFont);

        bottomNavigationView = findViewById(R.id.navigation);

        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
            if (i==0) {
                mMenuitem.setChecked(true);
            }
        }



        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home :
                    break;
                case R.id.navigation_nearest_off :
                    startActivity(new Intent(MainActivity.this,Map.class));
                    break;
                case R.id.navigation_my_codes :
                    startActivity(new Intent(MainActivity.this,MyCodes.class));
                    break;
                case R.id.classification:
                    startActivity(new Intent(MainActivity.this,Classification.class));
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
            startActivity(new Intent(MainActivity.this,SignUpActivity.class));
        });
        signin.setOnClickListener(view->{
            startActivity(new Intent(MainActivity.this,SingInActivity.class));
        });

        bookmarks.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,BookMarkedPosts.class));
        });

        followed_centers.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,FollowedShops.class));
            //drawerLayout.closeDrawer(navigationView);
        });

        frequently_asked_questions.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,FAQ.class));
            //drawerLayout.closeDrawer(navigationView);
        });

//        terms_off_service.setOnClickListener(view->{
//            startActivity(new Intent(MyCodes.this,.class));
//        });

        contactus.setOnClickListener(view->{
            startActivity(new Intent(MainActivity.this,contact_us.class));
        });

        share_with_friends.setOnClickListener(view -> {
           // startActivity(new Intent(MainActivity.this,BookMarkedPosts.class));
            Toast.makeText(this, "yet to be published", Toast.LENGTH_SHORT).show();
        });

        exit.setOnClickListener(view ->{
            //finish();
            SaveSharedPreference.removeAPITOKEN(MainActivity.this);
            System.exit(0);
        });

        edit.setOnClickListener(view->{
            Toast.makeText(this, "this part is yet to be complete", Toast.LENGTH_SHORT).show();
        });


    }
    private void setHeaderitems() {
        if(SaveSharedPreference.getAPITOKEN(MainActivity.this).length() > 0  ){
            signin.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            appname.setVisibility(View.VISIBLE);
            appname.setText(R.string.title_activity_test_navigation_drawer);
            appname.setTypeface(yekanfont);
        }
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
    private void search(){
    }

    private void handlebottomnavigation(){

    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < images.length -1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
