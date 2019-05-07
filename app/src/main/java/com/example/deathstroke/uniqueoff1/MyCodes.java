package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Service.CustomTypefaceSpan;
import Service.RetrofitClient;
import Service.SaveSharedPreference;
import Service.SetTypefaces;
import adapters.MycodeslistAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import entities.Code;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCodes extends AppCompatActivity implements DrawerLayout.DrawerListener{

   // @Bind(R.id.header_sign_up)
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;

    Typeface typeface;
    TextView appname;
    @Bind(R.id.just_appbar_tv)
    TextView appbar_tv;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.drawebtn)
    ImageButton drawer;

    @Bind(R.id.my_codes_main_page)
    ConstraintLayout main;

    @Bind(R.id.mycodes_list_view)
    RecyclerView mycodes_list;

    BottomNavigationView bottomNavigationView;


    private RecyclerView.LayoutManager layoutManager;
    private List<Code> codes = new ArrayList<>();
    private MycodeslistAdapter adapter;
    private String TAG = MyCodes.class.getSimpleName();
    public  String API_TOKEN ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_codes);
        ButterKnife.bind(this);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        drawerLayout.addDrawerListener(this);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0);
        navigationView.setElevation(0);
        ButterKnife.bind(this);
        Typeface yekanfont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        layoutManager = new LinearLayoutManager(MyCodes.this) ;
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
//                1,StaggeredGridLayoutManager.VERTICAL
//        );
        API_TOKEN = "YhPkXvBeABJaQDwDhDWNIdLCAtFjv4Az6HRHyjYElh8XY30EpVzBabfaccHq";//"YhPkXvBeABJaQDwDhDWNIdLCAtFjv4Az6HRHyjYElh8XY30EpVzBabfaccHq";
                //SaveSharedPreference.getAPITOKEN(this);
      //  mycodes_list.setLayoutManager(staggeredGridLayoutManager);
//
//        mycodes_list.setLayoutManager(layoutManager);
//        mycodes_list.setItemAnimator(new DefaultItemAnimator());

        appbar_tv.setText("کدهای من");
        appbar_tv.setTypeface(yekanfont);

        drawer.setOnClickListener(view->{
            drawerLayout.openDrawer(navigationView);
        });


        View header_items = navigationView.getHeaderView(0);

        initilizeheaderbuttons(header_items);

        setHeaderitems();
//        signin.setVisibility(View.INVISIBLE);
//        signup.setVisibility(View.INVISIBLE);
//        appname.setVisibility(View.VISIBLE);
//        appname.setText(R.string.title_activity_test_navigation_drawer);
//        appname.setTypeface(typeface);



        handleNavDrawerItemClick();

        SetTypefaces.setButtonTypefaces(yekanfont,signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit);
        bottomNavigationView = findViewById(R.id.navigation);
        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", typeface);
        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
            if (i==1) {
                mMenuitem.setChecked(true);
            }
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home :
                    startActivity(new Intent(MyCodes.this,MainActivity.class));
                    break;
                case R.id.navigation_nearest_off :
                    startActivity(new Intent(MyCodes.this,Map.class));
                    break;
                case R.id.navigation_my_codes :
                    break;
                case R.id.classification:
                    startActivity(new Intent(MyCodes.this,Classification.class));
                    break;
            }
            return false;
        });
        loadCodes();
    }

    private void setHeaderitems() {
        if(SaveSharedPreference.getAPITOKEN(MyCodes.this).length() > 0  ){
            signin.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            appname.setVisibility(View.VISIBLE);
            appname.setText(R.string.title_activity_test_navigation_drawer);
            appname.setTypeface(typeface);
        }
    }

    private void loadCodes () {

        Call<List<Code>> call = RetrofitClient.getmInstance().getApi().getmycodes(API_TOKEN);

        call.enqueue(new Callback<List<Code>>() {
            @Override
            public void onResponse(Call<List<Code>> call, Response<List<Code>> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(MyCodes.this, "connection is successful"+response.body().get(0).getPrice(), Toast.LENGTH_LONG).show();
                    //Log.d(TAG, "onResponse: "+response.body().get(0).getPrice());
                    if (codes.isEmpty())
                        codes.clear();

                    codes = response.body();
                    adapter = new MycodeslistAdapter(codes,MyCodes.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyCodes.this);
                    mycodes_list.setLayoutManager(layoutManager);
                    mycodes_list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(MyCodes.this, "connection is not successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Code>> call, Throwable t) {

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

    private void handleNavDrawerItemClick(){
        signup.setOnClickListener(view->{
            startActivity(new Intent(MyCodes.this,SignUpActivity.class));
        });
        signin.setOnClickListener(view->{
            startActivity(new Intent(MyCodes.this,SingInActivity.class));
        });

        bookmarks.setOnClickListener(view -> {
            startActivity(new Intent(MyCodes.this,BookMarkedPosts.class));
        });

        followed_centers.setOnClickListener(view -> {
            startActivity(new Intent(MyCodes.this,FollowedShops.class));
        });

        frequently_asked_questions.setOnClickListener(view -> {
            startActivity(new Intent(MyCodes.this,FAQ.class));
        });

//        terms_off_service.setOnClickListener(view->{
//            startActivity(new Intent(MyCodes.this,.class));
//        });

        contactus.setOnClickListener(view->{
            startActivity(new Intent(MyCodes.this,contact_us.class));
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

}
