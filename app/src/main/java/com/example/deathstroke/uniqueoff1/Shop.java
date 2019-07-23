package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import Service.RetrofitClient;
import Service.SaveSharedPreference;
import adapters.SliderAdapter;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Service.CustomTypefaceSpan;
import bottomsheetdialoges.ConfirmExitbottomSheet;
import entities.Coordinate;
import entities.Detail;
import entities.Pics;
import entities.ShopInfo;
import entities.ShopShits;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shop extends AppCompatActivity implements DrawerLayout.DrawerListener {

    RelativeLayout b1,b3,b2;
    BottomNavigationView bottomNavigationView;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SliderAdapter sliderAdapter;
    boolean flag = false;
    boolean off_flag, map_flag, info_flag,notify_me_flag;
    private ImageView shop_location,shop_info,shop_offs;
    private TextView shop_location_text_view,shop_info_text_view,shop_offs_text_view,shop_name_text_view;
    private Button notify_me_button;
    private String[] images ;
    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    NavigationView navigationView;
    private static final String TAG = "Shop";
    private String shopname;
    TextView appname;
    private String shopid;
    private double lat;
    private double lon;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;
    private Spinner cities;

    private ImageButton bookmark_shop_image_button;
    private boolean shop_flag = false;

    //shop coordinate
    private Coordinate shop_coordinate;

    //shop info
    private ShopInfo shopInfo;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        drawerLayout.setDrawerElevation(0);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //drawerLayout.setDrawerElevation(0);
        main = findViewById(R.id.this_one);
        off_flag = map_flag = info_flag = notify_me_flag = false;
        shop_location = findViewById(R.id.shop_location_image_view);
        shop_location_text_view = findViewById(R.id.shop_loc_text);
        Typeface yekanFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        shop_location_text_view.setTypeface(yekanFont);
        shop_info_text_view = findViewById(R.id.shop_info_textView);
        shop_location_text_view.setTypeface(yekanFont);
        shop_info = findViewById(R.id.shop_info_image_view);
        shop_offs = findViewById(R.id.shop_offs_image_view);
        shop_offs_text_view = findViewById(R.id.shop_offs_textView);
        shop_offs_text_view.setTypeface(yekanFont);
        navigationView = findViewById(R.id.nav_view);
        shop_name_text_view = findViewById(R.id.shop_name);
//
//        ImageButton imageButton = findViewById(R.id.drawebtn);
//
//        imageButton.setOnClickListener(v->{
//            Toast.makeText(this, "clicked on drawer button ", Toast.LENGTH_SHORT).show();
//            NavigationView navigationView = findViewById(R.id.nav_view);
//            drawerLayout.openDrawer(navigationView);
//        });

        notify_me_button = findViewById(R.id.notify_me_button);
        notify_me_button.setTypeface(yekanFont);

        notify_me_button.setOnClickListener(v->{
            if (!flag) {
            doNotifyMeOnclick();
            flag = true;
            notify_me_flag = true;
            }else {
                undoNotifyMeOnClick();
                flag = false;
                notify_me_flag=false;
            }
        });

        //main.setOnClickListener(view -> Toast.makeText(this, "on main click", Toast.LENGTH_SHORT).show());
        if(drawerLayout == null){
            Toast.makeText(this, "drawerLayout is null", Toast.LENGTH_SHORT).show();
        }
        else {
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            //drawerLayout.closeDrawers();
            try {
                ImageButton drawebtn = findViewById(R.id.drawebtn);
                if(drawebtn == null){
                    Toast.makeText(Shop.this, "drawebtn is null!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    drawebtn.setOnClickListener(view -> {
                        Toast.makeText(this, "clicked on a button", Toast.LENGTH_SHORT).show();
                        drawerLayout.openDrawer(navigationView);
                    });
                }
            }catch (Exception e) {
                Log.e("in shop page", "onCreate: is it working ?",e );
                e.printStackTrace();
            }



        }

        getIntentExtras();

        getShopInfo();
        getShopCoordinate();

        View header_items = navigationView.getHeaderView(0);

        initilizeheaderbuttons(header_items);


        setHeaderitems();
        handleNavDrawerItemClick();
        tabLayout = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.shopViewPager);
//        setViewPager();
        b1= findViewById(R.id.shop_info_button);
        b2= findViewById(R.id.shop_offs_button);
        b3= findViewById(R.id.shop_map_button);

        b1.setOnClickListener(view->{
            if (!info_flag) {
                //b1.clearFocus();
                b2.clearFocus();
                b3.clearFocus();
                doInfoOnClick();
                undoMapOnclick();
                undoOffsOOnclick();
                info_flag = true;
                shop_info_fragment info_fragment = new shop_info_fragment();
                info_fragment.setShopid(shopid);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frg_holder, info_fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
            else {
                b1.clearFocus();
                b2.clearFocus();
                b3.clearFocus();
                undoInfoOnClick();
                FragmentManager fm = getSupportFragmentManager();
                for (int i=0;i < fm.getBackStackEntryCount();i++)
                    fm.popBackStack();
                info_flag = false;
            }
        });
        b2.setOnClickListener(view ->{
            if (!off_flag) {
                b1.clearFocus();
                b3.clearFocus();
                doOffsOnClick();
                undoMapOnclick();
                undoInfoOnClick();
                //b2.clearFocus();
                off_flag = true;
                shop_offs_fragment offs_fragment = new shop_offs_fragment();
                offs_fragment.setShopid(String.valueOf(shopid));
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frg_holder,offs_fragment);
                ft.addToBackStack(null);
                ft.commit();
            }else {
                undoOffsOOnclick();
                b1.clearFocus();
                b2.clearFocus();
                b3.clearFocus();
                FragmentManager fm = getSupportFragmentManager();
                for (int i=0;i < fm.getBackStackEntryCount();i++)
                    fm.popBackStack();
                off_flag = false;
            }
        });

        b3.setOnClickListener(view->{
            if (!map_flag) {
                doMapOnClick();
                undoInfoOnClick();
                undoOffsOOnclick();
                map_flag = true;
                shop_map_fragment map_fragment = new shop_map_fragment();
                map_fragment.setLat(lat);
                map_fragment.setLon(lon);
                map_fragment.setShop_coordinate(shop_coordinate);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frg_holder, map_fragment);
                ft.addToBackStack(null);
                ft.commit();
                //b3.clearFocus();
                b1.clearFocus();
                b2.clearFocus();
            } else {
                undoMapOnclick();
                FragmentManager fm = getSupportFragmentManager();
                for (int i=0;i < fm.getBackStackEntryCount();i++)
                    fm.popBackStack();
                map_flag = false;
                b1.clearFocus();
                b2.clearFocus();
                b3.clearFocus();
            }

        });
        bottomNavigationView = findViewById(R.id.navigation);
//        Menu menu = bottomNavigationView.getMenu();
//        MenuItem menuItem = menu.getItem(0);
//        menuItem.setChecked(true);
        bottomNavigationView.getMenu().setGroupCheckable(0,false,true);
        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", yekanFont);
        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home :
                    startActivity(new Intent(Shop.this,MainActivity.class));
                    break;
                case R.id.navigation_nearest_off :
                    startActivity(new Intent(Shop.this,Map.class));
                    break;
                case R.id.navigation_my_codes :
                    startActivity(new Intent(Shop.this,MyCodes.class));
                    break;
                case R.id.classification:
                    startActivity(new Intent(Shop.this,Classification.class));
                    break;
            }
            return false;
        });

        //justfottest();
        cities = header_items.findViewById(R.id.cities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities, R.layout.spinner_text_view_1);
        adapter.setDropDownViewResource(R.layout.spinner_text_view);
        cities.setAdapter(adapter);

        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = parent.getItemAtPosition(position).toString();
                SaveSharedPreference.setCity(Shop.this,city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //handle shop bookmark

        bookmark_shop_image_button = findViewById(R.id.shop_bookmark);
        bookmark_shop_image_button.setOnClickListener(view->{
            if(!shop_flag){
                shop_flag = true;
                subscribe();
                bookmark_shop_image_button.setImageResource(R.drawable.ic_shop_bookmark);
            }else {
                shop_flag = false;
                deleteSubscribe();
                bookmark_shop_image_button.setImageResource(R.drawable.ic_bookmark_shop);
            }
        });

    }

    private void subscribe(){
        Call<String> call = RetrofitClient.getmInstance().getApi().subscribeToShop(SaveSharedPreference.getAPITOKEN(this),Integer.parseInt(shopid));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d("subscribe", "onResponse: successfully subscribed to this shop");
                }else{
                    Log.d("subscribe", "onResponse: sth went wrong");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void deleteSubscribe(){
        Call<String> call = RetrofitClient.getmInstance().getApi().deleteSubscribeToShop(SaveSharedPreference.getAPITOKEN(this),Integer.parseInt(shopid));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d("subscribe", "onResponse: successfully removed subscribed to this shop");
                }else{
                    Log.d("subscribe", "onResponse: sth went wrong in remove");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    //get intent extras :
    private void getIntentExtras(){

        if (getIntent().hasExtra("shopname") && getIntent().hasExtra("shopid") &&
            getIntent().hasExtra("latitude") && getIntent().hasExtra("longitude")){
            Log.d(TAG, "getIntentExtras: found extras ");
            shopname = getIntent().getStringExtra("shopname");
            shop_name_text_view.setText(shopname);
            shopid = getIntent().getStringExtra("shopid");
            lat = Double.parseDouble(getIntent().getStringExtra("latitude"));
            lon = Double.parseDouble(getIntent().getStringExtra("longitude"));

        }
    }

    private void doNotifyMeOnclick() {
        notify_me_button.setBackground(getDrawable(R.drawable.notify_me_button_on_click));
        notify_me_button.setTextColor(Color.WHITE);
    }
    private void undoNotifyMeOnClick() {
        notify_me_button.setBackground(getDrawable(R.drawable.notify_me_border));
        notify_me_button.setTextColor(getResources().getColor(R.color.grin_beam_color));
    }

    private void setViewPager (ShopInfo shopInfo) {

        List<Pics> headerPics;

        if(shopInfo != null)
        {
            Log.d("shopViewPager", "setViewPager: ");
            headerPics = shopInfo.getPics();
            String[] headerUrl = new String[headerPics.size()];
            for(int i=0;i<headerPics.size();i++){
                headerUrl[i] = headerPics.get(i).getThumblink();
                Log.d("shopViewPager", "setViewPager: urls" + headerUrl[i]+"\n");
            }

            sliderAdapter = new SliderAdapter(this, headerUrl);
            viewPager.setAdapter(sliderAdapter);
            if(headerUrl.length == 1){
                tabLayout.setVisibility(View.GONE);
            }else {
                tabLayout.setupWithViewPager(viewPager, true);
            }
        }




    }

    private void undoMapOnclick(){

        b3.setBackground(getDrawable(R.drawable.shop_botton_style));
        shop_location.setBackgroundResource(R.drawable.ic_shop_map_marker_alt);
        shop_location_text_view.setTextColor(getResources().getColor(R.color.shop_icon_color));
    }

    private void doMapOnClick(){
        shop_location_text_view.setTextColor(Color.WHITE);
        shop_location.setBackgroundResource(R.drawable.ic_map_marker_alt_onclick);
        //b3.setBackgroundColor(getResources().getColor(R.color.shop_icon_color));
        b3.setBackground(getResources().getDrawable(R.drawable.shop_button_onclick_background));
    }

    private void doInfoOnClick() {
        shop_info_text_view.setTextColor(Color.WHITE);
        shop_info.setBackgroundResource(R.drawable.ic_info_circle_onclick);
        b1.setBackground(getResources().getDrawable(R.drawable.shop_button_onclick_background));
    }
    private void undoInfoOnClick(){
        shop_info_text_view.setTextColor(getResources().getColor(R.color.shop_icon_color));
        shop_info.setBackgroundResource(R.drawable.ic_info_circle);
        b1.setBackground(getDrawable(R.drawable.shop_botton_style));

    }
    private void doOffsOnClick() {
        shop_offs_text_view.setTextColor(Color.WHITE);
        shop_offs.setBackgroundResource(R.drawable.ic_percentage_onclick);
        b2.setBackground(getDrawable(R.drawable.shop_button_onclick_background));

    }
    private void undoOffsOOnclick() {
        shop_offs_text_view.setTextColor(getResources().getColor(R.color.shop_icon_color));
        shop_offs.setBackgroundResource(R.drawable.ic_percentage);
        b2.setBackground(getDrawable(R.drawable.shop_botton_style));
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


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    private void initilizeheaderbuttons(View header_items) {
        //ButterKnife.bind(header_items);
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
    private void setHeaderitems() {
        if(SaveSharedPreference.getAPITOKEN(Shop.this).length() > 0  ){
            signin.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            appname.setVisibility(View.VISIBLE);
            appname.setText(R.string.title_activity_test_navigation_drawer);
            //appname.setTypeface(yekanfont);
        }
    }

    private void handleNavDrawerItemClick(){
        signup.setOnClickListener(view->{
            startActivity(new Intent(Shop.this,SignUpActivity.class));
        });
        signin.setOnClickListener(view->{
            startActivity(new Intent(Shop.this,SingInActivity.class));
        });

        bookmarks.setOnClickListener(view -> {
            startActivity(new Intent(Shop.this,BookMarkedPosts.class));
        });

        followed_centers.setOnClickListener(view -> {
            startActivity(new Intent(Shop.this,FollowedShops.class));
            //drawerLayout.closeDrawer(navigationView);
        });

        frequently_asked_questions.setOnClickListener(view -> {
            startActivity(new Intent(Shop.this,FAQ.class));
        });

//        terms_off_service.setOnClickListener(view->{
//            startActivity(new Intent(MyCodes.this,.class));
//        });

        contactus.setOnClickListener(view->{
            startActivity(new Intent(Shop.this,contact_us.class));
            //drawerLayout.closeDrawer(navigationView);
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

    private void getShopCoordinate(){
        Call<Coordinate> call = RetrofitClient.getmInstance().getApi().getShopCoordinate(shopid);

        call.enqueue(new Callback<Coordinate>() {
            @Override
            public void onResponse(Call<Coordinate> call, Response<Coordinate> response) {
                if (response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: in get shop coordinate");
                    Log.d("subshop", "onResponse: is it empty?");
                    shop_coordinate = response.body();
                }
            }

            @Override
            public void onFailure(Call<Coordinate> call, Throwable t) {

            }
        });
    }

    private void getShopInfo(){
        Call<ShopInfo> call = RetrofitClient.getmInstance().getApi().getShopInfo(shopid);

        call.enqueue(new Callback<ShopInfo>() {
            @Override
            public void onResponse(Call<ShopInfo> call, Response<ShopInfo> response) {
                if (response.isSuccessful() && response.body() != null){
                    setViewPager(response.body());
                }
            }

            @Override
            public void onFailure(Call<ShopInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
