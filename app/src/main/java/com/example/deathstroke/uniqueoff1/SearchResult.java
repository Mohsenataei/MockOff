package com.example.deathstroke.uniqueoff1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

public class SearchResult extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView mrecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        searchView = findViewById(R.id.result_searchView);

        CharSequence query = getIntent().getStringExtra("query");
        searchView.setQueryHint(query);
    }
}
