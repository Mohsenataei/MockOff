package com.example.deathstroke.uniqueoff1;

import android.content.Context;

import Service.SaveSharedPreference;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class PostPage extends AppCompatActivity implements DrawerLayout.DrawerListener {

    private static final String TAG = "PostPage";
    private TextView postName,soldCount;
    private ImageButton backbtn,drawer;
    protected DrawerLayout drawerLayout;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;
    private ConstraintLayout main;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_page);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0);
        drawerLayout.addDrawerListener(this);
        navigationView = findViewById(R.id.nav_view);
        main = findViewById(R.id.postPage);
        drawer = findViewById(R.id.drawebtn);

        drawer.setOnClickListener(view->{
            drawerLayout.openDrawer(navigationView);
        });
        View view = navigationView.getHeaderView(0);
        initilizeheaderbuttons(view);
        handleNavDrawerItemClick();




        postName = findViewById(R.id.post_name_post_page);
        soldCount = findViewById(R.id.bought_ticket_count);
        getExtrainfo();
        bottomNavigationView = findViewById(R.id.navigation);

//        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
//            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
//            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
//            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
//            mMenuitem.setTitle(spannableTitle);
//            if (i==3) {
//                mMenuitem.setChecked(true);
//            }
//        }



        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home :
                    startActivity(new Intent(PostPage.this,MainActivity.class));
                    break;
                case R.id.navigation_nearest_off :
                    startActivity(new Intent(PostPage.this,Map.class));
                    break;
                case R.id.navigation_my_codes :
                    startActivity(new Intent(PostPage.this,MyCodes.class));
                    break;
                case R.id.classification:
                    startActivity(new Intent(PostPage.this,Classification.class));
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
        //appname = header_items.findViewById(R.id.header_app_name);
    }
    private void handleNavDrawerItemClick() {
        signup.setOnClickListener(view -> {
            startActivity(new Intent(PostPage.this, SignUpActivity.class));
        });
        signin.setOnClickListener(view -> {
            startActivity(new Intent(PostPage.this, SingInActivity.class));
        });

        bookmarks.setOnClickListener(view -> {
            startActivity(new Intent(PostPage.this, BookMarkedPosts.class));
        });

        followed_centers.setOnClickListener(view -> {
            startActivity(new Intent(PostPage.this, FollowedShops.class));
            //drawerLayout.closeDrawer(navigationView);
        });

        frequently_asked_questions.setOnClickListener(view -> {
            startActivity(new Intent(PostPage.this, FAQ.class));
            //drawerLayout.closeDrawer(navigationView);
        });

//        terms_off_service.setOnClickListener(view->{
//            startActivity(new Intent(MyCodes.this,.class));
//        });

        contactus.setOnClickListener(view -> {
            startActivity(new Intent(PostPage.this, contact_us.class));
        });

        share_with_friends.setOnClickListener(view -> {
            // startActivity(new Intent(MainActivity.this,BookMarkedPosts.class));
            Toast.makeText(this, "yet to be published", Toast.LENGTH_SHORT).show();
        });

        exit.setOnClickListener(view -> {
            //finish();
            SaveSharedPreference.removeAPITOKEN(PostPage.this);
            System.exit(0);
        });

        edit.setOnClickListener(view -> {
            Toast.makeText(this, "this part is yet to be complete", Toast.LENGTH_SHORT).show();
        });
    }


        private void getExtrainfo(){
        if(getIntent().hasExtra("post_title") && getIntent().hasExtra("quantity")){
            Log.d(TAG, "getExtrainfo: intent extras found ");
            String postname = getIntent().getStringExtra("post_title");
            String count = getIntent().getStringExtra("quantity");

            setTextviews(postname,count);
        }
    }
    private void setTextviews(String s1,String s2){
        postName.setText(s1);
        soldCount.setText(s2);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        float slideX = drawerView.getWidth() * slideOffset;
        main.setTranslationX(-slideX);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
