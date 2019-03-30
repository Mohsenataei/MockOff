package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class HandleSlider extends AppCompatActivity {
    Button signup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_menu);
        Log.e("in slider handler", "onCreate: is it working ? " );
        signup.setOnClickListener(view -> {
            startActivity(new Intent(this,SignUpActivity.class));
        });
    }
}
