package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import Service.RetrofitClient;
import adapters.HotPostsAdapter;
import adapters.LazyLoadPost;
import adapters.LoadMoreRecyclerViewAdapter;
import adapters.RegPostAdapter;
import adapters.SliderAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import Service.CustomTypefaceSpan;
import Service.SaveSharedPreference;
import Service.SetTypefaces;
import bottomsheetdialoges.ConfirmExitbottomSheet;
import butterknife.ButterKnife;
import entities.HeaderPics;
import entities.Post;
import interfaces.LoadMore;
import io.github.inflationx.viewpump.*;
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
    //int images[] = {R.drawable.slider3,R.drawable.slider1, R.drawable.slider2, R.drawable.slider3,R.drawable.slider1};
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
    private ImageButton search_filter;
    private LinearLayout filter_line;
    private boolean filter_flag = false;
    private boolean line_flag = false;

    Button b12;
    // lazy loading variables

    private int step = 0;
    private int limit = 0;
    private int currentPosition = 0;
    List<Post> currentPosts = new ArrayList<>();
    List<Post> newPosts = new ArrayList<>();
    private LazyLoadPost adapter;

    // lazy loading 2
    private List<Post> lazyposts = new ArrayList<>();
    private int skip = 0;
    private LoadMoreRecyclerViewAdapter loadMoreRecyclerViewAdapter;


    //private boolean isLoading = false;


    //pagination shits
    private ProgressBar progressBar;
    private RecyclerView pagination_recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int skipp = 0;
    private RegPostAdapter paginationAdapter;

    private boolean isLoading = true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previousTotal=0;
    private int viewTreshold =10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        if (!isNetworkConnected()){
            Intent intent = new Intent(MainActivity.this,CheckNetworkConnection.class);
            intent.putExtra("flag","MainActivity");
            startActivity(intent);
        }

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

        search_filter = findViewById(R.id.search_filter_imgbtn);
        filter_line = findViewById(R.id.search_filter_line);

        search_filter.setOnClickListener(v->{
//            if(!filter_flag){
//                filter_flag = true;
//                search_filter.setBackground(getDrawable(R.drawable.ic_filter_icon));
//                search_filter.setImageResource(R.drawable.ic_filter_onclick_icon);
//            }else{
//                filter_flag = false;
//                search_filter.setBackground(getDrawable(R.drawable.search_filter_background));
//                search_filter.setImageResource(R.drawable.ic_filter_icon);
//            }
            startActivity(new Intent(this,ShowSearch.class));
        });

        b12 = findViewById(R.id.go_to_post);
        b12.setVisibility(View.GONE);
        b12.setOnClickListener(view->{
            startActivity(new Intent(this,PostPage.class));
        });

        filter_line.setOnClickListener(v->{
            if (!line_flag){
                line_flag = true;
                filter_line.setBackgroundColor(getResources().getColor(R.color.backarrowcolor));
            }else{
                line_flag = false;
                filter_line.setBackgroundColor(getResources().getColor(R.color.white));
            }
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
        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                String city = parent.getItemAtPosition(pos).toString();
                SaveSharedPreference.setCity(MainActivity.this,city);
                //Toast.makeText(MainActivity.this, "you selected " + city +" as city " , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
       // tabLayout = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);
//        viewPager.setClipToPadding(false);
//        viewPager.setPadding(40, 0, 40, 0);
//        viewPager.setPageMargin(20);
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



        //regpostrecycler = findViewById(R.id.req_home_post_recycler);
        hotPostsRecyclerView = findViewById(R.id.hot_posts_recyclerView);
        getHotPosts();

        //getRegularPosts();


//        LazyLoadPosts(skip);
//
//        if(lazyposts.isEmpty()){
//            Log.d("lazyload", "onCreate: lazypost is empty");
//        }

//        regpostrecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
//        loadMoreRecyclerViewAdapter = new LoadMoreRecyclerViewAdapter(MainActivity.this,lazyposts,regpostrecycler);

//        /loadMoreRecyclerViewAdapter.setOnLoadMoreListener(new LoadMoreRecyclerViewAdapter.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                Log.d("lazyload", "onLoadMore: loading more posts");
//                if(lazyposts.size() <= 40){
//                    lazyposts.add(null);
//                    loadMoreRecyclerViewAdapter.notifyItemInserted(lazyposts.size()-1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            lazyposts.remove(lazyposts.size()-1);
//                            loadMoreRecyclerViewAdapter.notifyItemRemoved(lazyposts.size());
//                            // get more posts :
//                            skip++;
//                            lazyposts = LazyLoadPosts(skip);
//                            loadMoreRecyclerViewAdapter.notifyDataSetChanged();
//                            loadMoreRecyclerViewAdapter.setLoaded();
//                        }
//                    },5000);
//                }else {
//                    Toast.makeText(MainActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });






        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //getSearchedPosts(query);
                Intent intent = new Intent(MainActivity.this,ShowSearch.class);
                intent.putExtra("query",query);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "you have entered: "+query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                regPostAdapter.getFilter().filter(newText);
                return false;
            }
        });


        // pagination do shits
        progressBar = findViewById(R.id.home_progressbar);
        pagination_recyclerView = findViewById(R.id.req_home_post_recycler);
        linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        pagination_recyclerView.setLayoutManager(linearLayoutManager);

        progressBar.setVisibility(View.VISIBLE);
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getHomeRegPosts(
                SaveSharedPreference.getCity(this),skipp
        );

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Post> postList = response.body();
                    paginationAdapter = new RegPostAdapter(postList,MainActivity.this);
                    pagination_recyclerView.setAdapter(paginationAdapter);
                    progressBar.setVisibility(View.GONE);
                    Log.d("pagination", "onResponse: First call for pagination adapter");
                    Toast.makeText(MainActivity.this, "First call for pagination adapter", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

        pagination_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                if(dy>0){
                    if(isLoading){
                        if(totalItemCount>previousTotal){
                            isLoading=false;
                            previousTotal = totalItemCount;
                        }

                    }

                    if(!isLoading && (totalItemCount-visibleItemCount)<= (pastVisibleItems+viewTreshold)){
                        skipp++;
                        performPagination();
                        isLoading=true;
                    }
                }
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
            ConfirmExitbottomSheet confirmExitbottomSheet = new ConfirmExitbottomSheet();
            confirmExitbottomSheet.show(getSupportFragmentManager(),"ConfirmExit");
        });

        edit.setOnClickListener(view->{
            //Toast.makeText(this, "this part is yet to be complete", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this,EditProfie.class));
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
                    sliderAdapter = new SliderAdapter(MainActivity.this, url,headerPics);
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
        Log.d(TAG, "getSearchedPosts: you have entered :" + query);
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    private void getNewPosts(){
        isLoading = true;
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getHomeRegPosts(SaveSharedPreference.getCity(this),limit*step);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: in get new posts: so far so good");

                    if (!newPosts.isEmpty()){
                        newPosts.clear();
                    }
                    step+=1;
                    newPosts = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void recyclerviewInitializer(){
        Log.d(TAG, "recycler view Initializer: ");
        currentPosts.addAll(newPosts);
        regPostAdapter = new RegPostAdapter(currentPosts,this);
        if (currentPosition < currentPosts.size() && currentPosts.size() >0){
            regpostrecycler.scrollToPosition(currentPosition);
        }

    }

    private void setOnscrollListeners() {
        regpostrecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    private void lazyLoading(){
        getPosts(0);
        adapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {

                newPosts.add(null);
            }
        });



    }

    private void getPosts(int skip){

        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getHomeRegPosts(SaveSharedPreference.getCity(this),skip);

        List<Post> mynewposts;
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: in getpost so far so good");
                    newPosts = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }
    private boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        return true;
        else return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  Toast.makeText(this, "MainActivity Resumed.", Toast.LENGTH_SHORT).show();
        if (!isNetworkConnected()){
            Intent intent = new Intent(MainActivity.this,CheckNetworkConnection.class);
            intent.putExtra("flag","MainActivity");
            startActivity(intent);
        }
    }

    private List<Post> LazyLoadPosts(int skip){

        List<Post> postList;
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getHomeRegPosts(SaveSharedPreference.getCity(this),2);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d("lazyload", "onResponse: lazy load was successful");
                    if(response.body() == null){
                        Log.d("lazyload", "onResponse: response body is empty.");
                    }
                    lazyposts = response.body();
                    regpostrecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
                    loadMoreRecyclerViewAdapter = new LoadMoreRecyclerViewAdapter(MainActivity.this,response.body(),regpostrecycler);


                }else {
                    Log.d("lazyload", "onResponse: lazy post, something went wrong");
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

        postList = lazyposts;
        return postList;

    }

    private void performPagination(){

        progressBar.setVisibility(View.VISIBLE);
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getHomeRegPosts(
                SaveSharedPreference.getCity(this),skipp
        );

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Post> new_posts = response.body();
                    paginationAdapter.addPosts(new_posts);
                    Log.d("pagination", "onResponse:" + skipp + "st call for pagination adapter");
                    Toast.makeText(MainActivity.this, "onResponse:" + skipp + "st call for pagination adapter", Toast.LENGTH_SHORT).show();
                }
             progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }

}
