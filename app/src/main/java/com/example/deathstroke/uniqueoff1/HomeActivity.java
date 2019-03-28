package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // Button Send = findViewById(R.id.SEND_BTN);
    }
    public void gotoSignupPage(View v){
        Intent signuppage = new Intent(this,SignUpActivity.class);
        startActivity(signuppage);
    }
    public void gotoSigninPage(View v){
        Intent signuppage = new Intent(this,SingInActivity.class);
        startActivity(signuppage);
    }
    public void googleSignUp(View v) {
        Intent googlesignupPage = new Intent(this,CooperationActivity.class);
        startActivity(googlesignupPage);

    }

    public void gotomain(View V){
        Intent mainpage = new Intent(this,TestNavigationDrawer.class);
        startActivity(mainpage);
    }

}
