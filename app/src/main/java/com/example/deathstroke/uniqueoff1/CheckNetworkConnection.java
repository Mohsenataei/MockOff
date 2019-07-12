package com.example.deathstroke.uniqueoff1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;

public class CheckNetworkConnection extends AppCompatActivity {

    private Button retrybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_network_connection);

        retrybtn = findViewById(R.id.check_connection_btn);

        retrybtn.setOnClickListener(view->{
            if(isNetworkConnected())
                if(!getIntentFlag().isEmpty()) {
                    switch (getIntentFlag()) {
                        case "MainActivity":
                            startActivity(new Intent(CheckNetworkConnection.this, MainActivity.class));
                            break;
                        case "Map" :
                            startActivity(new Intent(CheckNetworkConnection.this, Map.class));
                            break;
                        case "Mycodes" :
                            startActivity(new Intent(CheckNetworkConnection.this, MyCodes.class));
                            break;
                        case "Classification" :
                            startActivity(new Intent(CheckNetworkConnection.this, Classification.class));
                            break;
                            default:
                                break;
                    }
                }
            });

    }
    private String getIntentFlag(){
        if (getIntent().hasExtra("flag")){
            return getIntent().getStringExtra("flag");
        }
        return null;
    }
    private boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            return true;
        else return false;
    }
}
