package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BottomNavigation extends AppCompatActivity {

    private TextView mTextMessage;
    //go to google map page
    private void goToMap(){
        //startActivity(new Intent(this,mapPage.class));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Log.d("mohsen", "onNavigationItemSelected: worked ?");
                    Toast.makeText(BottomNavigation.this, "clicked on home icon", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),SingInActivity.class));
                    return true;
                case R.id.navigation_nearest_off:
                    mTextMessage.setText(R.string.title_nearest_offs);
                    return true;
                case R.id.navigation_my_codes:
                    mTextMessage.setText(R.string.title_my_codes);
                    return true;
                case R.id.classification :
                    mTextMessage.setText(R.string.title_classification);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_test_navigation_drawer);
        BottomNavigationView bnv = findViewById(R.id.navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
