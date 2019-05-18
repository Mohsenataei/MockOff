package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import Service.RetrofitClient;
import adapters.HotPostsAdapter;
import adapters.RegPostAdapter;
import adapters.SliderAdapter;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Service.CustomTypefaceSpan;
import Service.SaveSharedPreference;
import Service.SetTypefaces;
import bottomsheetdialoges.ConfirmExitbottomSheet;
import butterknife.ButterKnife;
import entities.HeaderPics;
import entities.Post;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener{

    private static final String TAG = "aghamohsen";
    TextView appname , hottest_offs_txtvw;
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
    List<String> imagesurl = new ArrayList<>();
    Boolean book1_flag ,book2_flag;
    BottomNavigationView bottomNavigationView;
    androidx.appcompat.widget.SearchView searchView;
    Button qrcode,shop;
    int mCurrentPosition;
    int lastPageIndex = 4;

    RecyclerView regpostrecycler,hotPostsRecyclerView;
    List<Post> regularPostList = new ArrayList<>();
    List<Post> hotPost = new ArrayList<>();
    List<HeaderPics> headerPics = new ArrayList<>();
    RegPostAdapter regPostAdapter;
    HotPostsAdapter mhotPostsAdapter;

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

        hottest_offs_txtvw = findViewById(R.id.hottest_offs_txtvw);
        hottest_offs_txtvw.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,Shop.class));
        });
        drawebtn = findViewById(R.id.drawebtn);
        drawebtn.setOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
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
       // tabLayout = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(40, 0, 40, 0);
        viewPager.setPageMargin(20);
        getHeaderAdPics();
        //Log.d(TAG, "onCreate: " + imagesurl[0]);

        viewPager.setAdapter(sliderAdapter);
        viewPager.setCurrentItem(1);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mCurrentPosition = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//                if(mCurrentPosition==0) viewPager.setCurrentItem(lastPageIndex-1,false);
//                if(mCurrentPosition==lastPageIndex) viewPager.setCurrentItem(1,false);
//
//            }
//        });

//        tabLayout.setupWithViewPager(viewPager, true);
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);


        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", hintFont);

        bottomNavigationView = findViewById(R.id.navigation);

        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
            if (i==3) {
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



        regpostrecycler = findViewById(R.id.req_home_post_recycler);
        hotPostsRecyclerView = findViewById(R.id.hot_posts_recyclerView);
        getHotPosts();

        getRegularPosts();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //getSearchedPosts(query);
                Toast.makeText(MainActivity.this, "you have entered: "+query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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
//            Animation anim_slide_up = AnimationUtils.loadAnimation(this,R.anim.slide_up);
//            LayoutInflater inflater = getLayoutInflater();
//            View v = inflater.inflate(R.layout.confirm_exit_layout,null,false);
//            v.startAnimation(anim_slide_up);
////            SaveSharedPreference.removeAPITOKEN(MainActivity.this);
////            System.exit(0);
            ConfirmExitbottomSheet confirmExitbottomSheet = new ConfirmExitbottomSheet();
            confirmExitbottomSheet.show(getSupportFragmentManager(),"ConfirmExit");
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

//    @Override
//    public void onItemClick(View view, int position) {
//        Post post = regularPostList.get(position);
//        Intent intent = new Intent(this,PostPage.class);
//        intent.putExtra("sale_count",String.valueOf(post.getQuantity()));
//        intent.putExtra("discount",String.valueOf(post.getDiscount()));
//        intent.putExtra("price",String.valueOf(post.getPrice()));
//        intent.putExtra("remain_date",post.getE_date_use());
//        intent.putExtra("more_days",post.getS_date_use());
//        String img_url = "img_url";
//        for (int i = 1 ; i<post.getPics().size();i++){
//            img_url = img_url+i;
//            intent.putExtra(img_url,post.getPics().size());
//        }
//        startActivity(intent);
//    }

//    private class SliderTimer extends TimerTask {
//
//        @Override
//        public void run() {
//            MainActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (viewPager.getCurrentItem() < images.length -1) {
//                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
//                    }else {
//                        viewPager.setCurrentItem(0);
//                    }
//                }
//            });
//        }
//    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private void getRegularPosts(){

        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getHomeRegPosts("همدان",0);

       // Toast.makeText(MainActivity.this, "is it even working ? line 346", Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //Toast.makeText(MainActivity.this, "is it even working ? line 350", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(MainActivity.this, "regular posts response is successful line 352"+response.body().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                    if (regularPostList.isEmpty()) {
                        regularPostList.clear();
                    }


                    //Toast.makeText(MainActivity.this, "even here ?", Toast.LENGTH_SHORT).show();
                    regularPostList = response.body();
                    regPostAdapter = new RegPostAdapter(regularPostList,MainActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    regpostrecycler.setLayoutManager(layoutManager);
                    regpostrecycler.setAdapter(regPostAdapter);
                }else {
                    Toast.makeText(MainActivity.this, "response is unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "is it even working ? line 369" + t, Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: failed " , t);
            }
        });
    }

    private void getHotPosts(){
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getHomeHotPosts("همدان");

         call.enqueue(new Callback<List<Post>>() {
             @Override
             public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                 if (response.isSuccessful() && response.body() != null){
                     Log.d(TAG, "onResponse: response is successful");
                     Toast.makeText(MainActivity.this, "Response is successful", Toast.LENGTH_SHORT).show();

                     if (hotPost.isEmpty())
                         hotPost.clear();


                     hotPost = response.body();

                     mhotPostsAdapter = new HotPostsAdapter(hotPost,MainActivity.this);
                     RecyclerView.LayoutManager layoutManager = new  LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
                     hotPostsRecyclerView.setLayoutManager(layoutManager);
                     hotPostsRecyclerView.setAdapter(mhotPostsAdapter);
                     mhotPostsAdapter.notifyDataSetChanged();

                 }

             }

             @Override
             public void onFailure(Call<List<Post>> call, Throwable t) {

             }
         });

    }

    private void getHeaderAdPics(){
        Call<List<HeaderPics>> call = RetrofitClient.getmInstance().getApi().getHomeHeaderPics("همدان");
        String[] urls = new String[10];

        call.enqueue(new Callback<List<HeaderPics>>() {
            @Override
            public void onResponse(Call<List<HeaderPics>> call, Response<List<HeaderPics>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: so for so good");
                    if(headerPics.isEmpty())
                        headerPics.clear();

                    headerPics = response.body();
                    for (int i=0;i<headerPics.size();i++){
                        imagesurl.add(i,headerPics.get(i).getThumblink());
                        Log.d(TAG, "onResponse: image urls are : " + imagesurl.get(i));
                    }
                    String[] url = new String[imagesurl.size()];
                    for (int i=0;i<imagesurl.size();i++){
                        url[i] = imagesurl.get(i);
                        Log.d(TAG, "onResponse: second for statement :"+url[i]);
                    }
                    sliderAdapter = new SliderAdapter(MainActivity.this, url);
                    viewPager.setAdapter(sliderAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<HeaderPics>> call, Throwable t) {
                Log.d(TAG, "onFailure: why failed? ",t);
            }
        });
    }

    private void getSearchedPosts(String query){
        String mquery = searchView.getQuery().toString();
        Call<List<Post>> searchedPots = RetrofitClient.getmInstance().getApi().getsearchedposts("","همدان",mquery);


        Toast.makeText(this, "you have entered : "+query, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "getSearchedPosts: you have enterd :" + query);
    }

}
