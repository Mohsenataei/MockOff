package com.example.deathstroke.uniqueoff1;

import android.content.Context;

import Service.SaveSharedPreference;
import adapters.SliderAdapter;
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
import com.google.android.material.tabs.TabLayout;
import com.j256.ormlite.stmt.query.In;
import com.shawnlin.numberpicker.NumberPicker;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class PostPage extends AppCompatActivity implements DrawerLayout.DrawerListener {

    private static final String TAG = "PostPage";
    private TextView postName,soldCount,post_title,price_textView,show_date,use_date;
    private ImageButton backbtn,drawer;
    protected DrawerLayout drawerLayout;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;
    private ConstraintLayout main;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    ViewPager mviewPager;
    TabLayout tabLayout;
    SliderAdapter sliderAdapter;
    private NumberPicker mNumberPicker;
    private static String[] persianNumbers = new String[]{ "۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹" };
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

        mviewPager = findViewById(R.id.postViewPager);

        drawer.setOnClickListener(view->{
            drawerLayout.openDrawer(navigationView);
        });
        View view = navigationView.getHeaderView(0);
        initilizeheaderbuttons(view);
        handleNavDrawerItemClick();




        postName = findViewById(R.id.post_name_post_page);
        soldCount = findViewById(R.id.bought_ticket_count);
        post_title = findViewById(R.id.post_title_tv);
        price_textView = findViewById(R.id.post_price_tv);
        price_textView.setText(getPrice());
        use_date = findViewById(R.id.use_date);
        show_date = findViewById(R.id.show_date);
        tabLayout = findViewById(R.id.indicator);
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

        final int[] post_count = new int[1];
        mNumberPicker = findViewById(R.id.post_number_picker);
        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Toast.makeText(PostPage.this, "selected "+newVal, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onValueChange: " + newVal);
                post_count[0] = newVal;
                setPriceTextView(toPersianNumber(getPrice()),newVal);
            }
        });

        String price = getPrice();
        setPriceTextView(price,post_count[0]);
    }

    void setPriceTextView(String price, int count){
        Log.d(TAG, "setPriceTextView: "+price +"// " + count);
        String discount = getIntent().getStringExtra("discount");
        String tmp = String.valueOf(Integer.parseInt(price)-(Integer.parseInt(price) * Integer.parseInt(discount) /100)) + getString(R.string.toman);
        String totalval = String.valueOf(Integer.parseInt(price)*count);
        price_textView.setText(totalval);

    }
    private String getPrice(){
        if (getIntent().hasExtra("price")){
            return getIntent().getStringExtra("price");
        }
        return null;
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
        if(getIntent().hasExtra("post_title") && getIntent().hasExtra("quantity") && getIntent().hasExtra("img_urls")){

            Log.d(TAG, "getExtrainfo: intent extras found ");
            Toast.makeText(this, "intent extras found", Toast.LENGTH_SHORT).show();
            String postname = getIntent().getStringExtra("post_title");
            String count = getIntent().getStringExtra("quantity");
            String shop_name = getIntent().getStringExtra("shop_name");
            String price = getIntent().getStringExtra("price");
            String show_date = getIntent().getStringExtra("e_date_show");
            String use_date = getIntent().getStringExtra("e_date_use");
            String post_id = getIntent().getStringExtra("post_id");
            String[] img_urls = getIntent().getStringArrayExtra("img_urls");
            if (img_urls.length == 1){
                tabLayout.setVisibility(View.GONE);
            }
            sliderAdapter = new SliderAdapter(PostPage.this,img_urls);
            mviewPager.setAdapter(sliderAdapter);
            tabLayout.setupWithViewPager(mviewPager,true);

            Log.d(TAG, "getExtrainfo: post title is : "+postname + " and count is : " +count );
            setTextviews(postname,count,shop_name,show_date,use_date);
        }
    }
    private void setTextviews(String post_name,String count, String shop_name, String showdate, String usedate){
        postName.setText(post_name);
        soldCount.setText(count);
        post_title.setText(shop_name);
        show_date.setText(showdate);
        use_date.setText(usedate);
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
//    private String formatPrice(String price){
//        String tmp;
//        int colon_index=0;
//        for (int i=price.length()-1;i>=0;i--){
//            colon_index++;
//            tmp.
//        }
//    }



    public static String toPersianNumber(String text) {
        if (text.length() == 0) {
            return "";
        }
        String out = "";
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if ('0' <= c && c <= '9') {
                int number = Integer.parseInt(String.valueOf(c));
                out += persianNumbers[number];
            } else if (c == '٫') {
                out += '،';
            } else {
                out += c;
            }
        }
        return out;
    }

}
