package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import Service.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingInActivity extends AppCompatActivity {

    private static final String TAG = "mohsen" ;
    EditText email_field;
    EditText password_field;
    ImageButton sign_in_back_btn;

    Button google_btn;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        Toast.makeText(this, "what the hell is going on ?", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate: is it even working ?");

        Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");

        email_field = findViewById(R.id.sign_in_email);
        email_field.setTypeface(hintFont);
        password_field = findViewById(R.id.sign_in_password);
        password_field.setTypeface(hintFont);
        google_btn = findViewById(R.id.google_sign_up);
        google_btn.setTypeface(hintFont);
        sign_in_back_btn = findViewById(R.id.sign_in_back_btn);
        sign_in_back_btn.setOnClickListener((view)->{
            finish();
        });
        button = findViewById(R.id.sign_in_btn);
        button.setTypeface(hintFont);

        button.setOnClickListener((view) -> {

            userlogin();
        });

    }
    private void gotoHomePage(){
        Intent gotohome = new Intent(this,MainActivity.class);
        startActivity(gotohome);
    }

    private void userlogin (){
      String email = email_field.getText().toString();
      String passWord = password_field.getText().toString();

      //form validation goes here :

//        HashMap<String, String> header = new HashMap<>();
//        header.put("content-type","application/json");
        Call<String> call = RetrofitClient.getmInstance().getApi().userLogin(email,passWord);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG,"onResponse, Server Response :" + response);
                String s = response.body();
                try {
                    Log.d(TAG,"onResponse API TOKEN IS :" + s);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

                if (s != null){
                Toast.makeText(SingInActivity.this,"welcome" + s,Toast.LENGTH_SHORT).show();
                    gotoHomePage();
                }else{
                    Toast.makeText(SingInActivity.this,"pls enter correct email or password ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(SingInActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        

    }
}
