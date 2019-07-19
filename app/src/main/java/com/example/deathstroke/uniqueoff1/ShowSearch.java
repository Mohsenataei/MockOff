package com.example.deathstroke.uniqueoff1;

import Service.RetrofitClient;
import Service.SaveSharedPreference;
import adapters.RegPostAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import bottomsheetdialoges.SearchFilterBottomSheet;
import entities.Post;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ShowSearch extends AppCompatActivity implements DrawerLayout.DrawerListener, SearchFilterBottomSheet.BottomSheetListener {
    private ImageButton search_filter;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;
    Spinner cities;
    ViewPager viewPager;
    TabLayout tabLayout;
    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    NavigationView navigationView;
    private static final String TAG = "ShowSearch";
    private RecyclerView mRecyclerView;
    RegPostAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Post> postList = new ArrayList<>();

    androidx.appcompat.widget.SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search);

        searchView = findViewById(R.id.showsearchView);
        search_filter = findViewById(R.id.search_filter_imgbtn);
        search_filter.setOnClickListener(view -> {
            SearchFilterBottomSheet searchFilterBottomSheet = new SearchFilterBottomSheet();
            searchFilterBottomSheet.show(getSupportFragmentManager(),"");
        });

        mRecyclerView = findViewById(R.id.show_search_recyclerview);

        String query = getntentExtra();
        getSerarchResult(query);
        
        searchView.setQueryHint("جستجو");
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSerarchResult(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                getSerarchResult(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });


    }

    private void ShowSearch(){
        
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private String getntentExtra(){
        String extra = "";
        if (getIntent().hasExtra("query")){
            return getIntent().getStringExtra("query");
        }
        return null;
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

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

    @Override
    public void onLayoutClicked(String cat_id) {
        //Toast.makeText(this, cat_id, Toast.LENGTH_SHORT).show();
        String city = SaveSharedPreference.getCity(this);
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getcategoryPosts("2","همدان");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse: response is not successful ");

                }
                if(response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: ");

                    if(postList.isEmpty())
                        postList.clear();

                    postList = response.body();

                    layoutManager = new LinearLayoutManager(ShowSearch.this);
                    adapter = new RegPostAdapter(postList,ShowSearch.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse: something went wrong");
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void getSerarchResult(String query){
        String city = SaveSharedPreference.getCity(this);
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getcategoryPosts("3","همدان");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse: response is not successful ");

                }
                if(response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: ");

                    if(postList.isEmpty())
                        postList.clear();

                    postList = response.body();

                    layoutManager = new LinearLayoutManager(ShowSearch.this);
                    adapter = new RegPostAdapter(postList,ShowSearch.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse: something went wrong");
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
