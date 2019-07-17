package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import Service.RetrofitClient;
import Service.SaveSharedPreference;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "mohsen" ;
    private static final int REQ_SIGN_IN = 8;
    EditText email_field;
    EditText password_field;
    ImageButton sign_in_back_btn,pass_toggle;

    //Button google_btn;
    Button button;

    boolean p1;

    // google sign in ui components
    GoogleApiClient gApiClient;
    SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        signInButton = findViewById(R.id.google_sign_in);
        //signInButton.setSize(SignInButton.SIZE_WIDE);
        Typeface hintFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");

        email_field = findViewById(R.id.sign_in_email);
        email_field.setTypeface(hintFont);
        password_field = findViewById(R.id.sign_in_password);
        password_field.setTypeface(hintFont);
        //google_btn = findViewById(R.id.google_sign_in);
       // google_btn.setTypeface(hintFont);
        sign_in_back_btn = findViewById(R.id.sign_in_back_btn);
        sign_in_back_btn.setOnClickListener((view)->{
            finish();
        });
        button = findViewById(R.id.sign_in_btn);
        button.setTypeface(hintFont);

        button.setOnClickListener((view) -> {

            userlogin();
        });


        pass_toggle = findViewById(R.id.password_toggle3);

        pass_toggle.setOnClickListener(view->{
            if(!p1){
                p1 = true;
                password_field.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                pass_toggle.setImageResource(R.drawable.ic_eye_clicked);
            }else{
                p1 = false;
                password_field.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                pass_toggle.setImageResource(R.drawable.ic_eye);
            }
        });




        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton.setOnClickListener(view->{
            SignIn();
        });


    }

    private void SignIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(gApiClient);
        startActivityForResult(signInIntent,REQ_SIGN_IN);
    }



    private void gotoHomePage(){
        Intent gotohome = new Intent(this,MainActivity.class);
        startActivity(gotohome);
    }

    private void userlogin (){
      String email = email_field.getText().toString();
      String passWord = password_field.getText().toString();
      //password_field.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
      //form validation goes here :

//        HashMap<String, String> header = new HashMap<>();
//        header.put("content-type","application/json");
        Call<String> call = RetrofitClient.getmInstance().getApi().userLogin(email,passWord);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG,"onResponse, Server Response :" + response);
                String s = response.body();
                SaveSharedPreference.setAPITOKEN(SingInActivity.this,s);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQ_SIGN_IN){
            Log.d(TAG, "onActivityResult: request code match " + requestCode);
            GoogleSignInResult gsResut = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            HandleSignInResult(gsResut);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void HandleSignInResult(GoogleSignInResult gsResut) {
        Log.d(TAG, "HandleSignInResult: " );
        //signInWithGoogle(gsResut.getSignInAccount());
        if(gsResut.isSuccess()){
            Log.d(TAG, "HandleSignInResult: gs result is successful" + gsResut.isSuccess());
            GoogleSignInAccount googleAccount = gsResut.getSignInAccount();
            signInWithGoogle(googleAccount);
        }else{
            signInWithGoogle(null);
        }
    }

    private void signInWithGoogle(GoogleSignInAccount account){
        if(account == null){
            Toast.makeText(this, "حساب کاربری گوگل پیدا نشد", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = account.getEmail();
        googleSignIn(email);
    }


    private void googleSignIn(String email){
        Call<String> call = RetrofitClient.getmInstance().getApi().googleSingIn(email);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: Sign in activity " + response.body());
                    String APITOKEN = response.body();
                    SaveSharedPreference.setAPITOKEN(SingInActivity.this,APITOKEN);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure: sign in activity ", t );
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed: connection failed" + connectionResult);

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
