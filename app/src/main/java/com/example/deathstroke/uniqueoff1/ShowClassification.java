package com.example.deathstroke.uniqueoff1;

import Service.RetrofitClient;
import adapters.RegPostAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bottomsheetdialoges.MapPageBottomSheet;
import entities.Post;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ShowClassification extends AppCompatActivity {

    private static final String TAG = "ShowClassification";
    private Button mbutton;
    private List<Post> categoryPosts = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    RegPostAdapter regPostAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_classification);

        mbutton = findViewById(R.id.go_back_to_classification);
        recyclerView = findViewById(R.id.classification_show_recyclerView);
        MapPageBottomSheet mapPageBottomSheet = new MapPageBottomSheet();

        mbutton.setOnClickListener(view->{
        //    finish();
            mapPageBottomSheet.show(getSupportFragmentManager(),"mapbottomsheet");
        });

        String category = getIntentExtra();
        getCategoryPost(category);
    }

    private String getIntentExtra(){
        if (getIntent().hasExtra("field")){
            Log.d(TAG, "getIntentExtra: ");
            return getIntent().getStringExtra("field");
        }
        return null;
    }

    private void getCategoryPost(String category){
        Call<List<Post>> call = RetrofitClient.getmInstance().getApi().getcategoryPosts("همدان",category);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: connecction is successful");
                    if(categoryPosts.isEmpty())
                        categoryPosts.clear();

                    categoryPosts = response.body();

                    layoutManager = new LinearLayoutManager(ShowClassification.this);
                    regPostAdapter = new RegPostAdapter(categoryPosts,ShowClassification.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(regPostAdapter);
                    regPostAdapter.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse: response was not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
