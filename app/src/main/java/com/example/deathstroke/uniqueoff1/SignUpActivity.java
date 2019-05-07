package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Service.RetrofitClient;
import Service.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG ="SignUpActivity" ;
    EditText username_field;
    EditText password_field;
    EditText re_password_field;
    EditText cell_phone_field;
    EditText email_field;
    TextView already_signed_up;
    TextView enter_textview;
    ImageButton sign_up_back_btn;
    Button button;
    Button google_sign_up;
    SharedPreferences user_Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Typeface hintFont = Typeface.createFromAsset(getAssets(),"fonts/B Yekan+.ttf"); // works ? yes
        username_field = findViewById(R.id.sign_up_username);
        username_field.setTypeface(hintFont);
        password_field = findViewById(R.id.password);
        password_field.setTypeface(hintFont);
        re_password_field = findViewById(R.id.re_password);
        re_password_field.setTypeface(hintFont);
        cell_phone_field = findViewById(R.id.sign_up_phone);
        cell_phone_field.setTypeface(hintFont);
        email_field = findViewById(R.id.sign_up_email);
        email_field.setTypeface(hintFont);
        already_signed_up = findViewById(R.id.already_signed_text_view);
        already_signed_up.setTypeface(hintFont);
        enter_textview = findViewById(R.id.enter);
        enter_textview.setTypeface(hintFont);
        button = findViewById(R.id.sign_up_btn);
        button.setTypeface(hintFont);
        sign_up_back_btn = findViewById(R.id.sign_up_back_btn);
        google_sign_up = findViewById(R.id.sign_up_wih_google);
        google_sign_up.setTypeface(hintFont);

        SharedPreferences user_info = getSharedPreferences("UserInfo",0);
        SharedPreferences.Editor editor = user_info.edit();
        editor.putString("Username",username_field.getText().toString());
        editor.putString("Password",password_field.getText().toString());
        editor.commit();

        Intent gotoHome;
        button.setOnClickListener((view)->{
            userSignUp();
            //Toast.makeText(this,"cell_phone :"+ cell_phone_field.getText().toString(),Toast.LENGTH_SHORT).show();
        });
        already_signed_up.setOnClickListener((view)->{

        });
        enter_textview.setOnClickListener((view)->{
            Intent moveToSignIn = new Intent(this,SingInActivity.class);
            startActivity(moveToSignIn);
        });
        sign_up_back_btn.setOnClickListener((view)->{
            finish();
        });


    }
    private void userSignUp(){
        String name = username_field.getText().toString();
        String password = password_field.getText().toString();
        String re_password = re_password_field.getText().toString();
        String cell_phone = cell_phone_field.getText().toString();
        String email = email_field.getText().toString();

        // form validation for user inputs :
        if (name.isEmpty()){
            username_field.setError("لطفا نام را وارد کنید");

            username_field.requestFocus();

            return;
        }
        if(cell_phone.isEmpty()){
            cell_phone_field.setError("لطفا شماره تلفن را وارد کنید");
            cell_phone_field.requestFocus();

            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_field.setError("لطفا یک ایمیل معتبر وارد کنید");
            email_field.requestFocus();
            return;
        }
        if (password.isEmpty()){
            password_field.setError("لطفا رمز ورود را وارد کنید");

            password_field.requestFocus();
            return;
        }
        if(!password.equals(re_password)){
            re_password_field.setError("رمز عبور یکسان نمی باشد");
            re_password_field.requestFocus();
            return;
        }
        if(password.length() < 8){
            password_field.setError("رمز عبور باید حداقل 8 کاراکتر و شامل حرف بزرگ باشد");
            password_field.requestFocus();
            return;
        }

//        HashMap<String, String> header = new HashMap<>();
//        header.put("content-type","application/json");
        Call<String> call = RetrofitClient
                .getmInstance()
                .getApi()
                .userSignUp(name,cell_phone,password,email);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG,"onResponse, Server Response :" + response);
                String s = response.body();


                if (response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "response successful", Toast.LENGTH_LONG).show();
                    SaveSharedPreference.setAPITOKEN(SignUpActivity.this, s);
                }
                else Toast.makeText(SignUpActivity.this, "connection was not successful", Toast.LENGTH_SHORT).show();
                try {
                        Log.d(TAG,"onResponse API TOKEN IS :" + s);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                Toast.makeText(SignUpActivity.this, "User SUCCESSFULLY ADDED WITH TOKEN :"+response,Toast.LENGTH_LONG).show();
                //gotoHome();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(SignUpActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void gotoHome (){
       Intent gotohome = new Intent(this,MainActivity.class);
       startActivity(gotohome);
    }

}
