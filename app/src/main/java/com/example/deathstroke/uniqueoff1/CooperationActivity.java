package com.example.deathstroke.uniqueoff1;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Service.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CooperationActivity extends AppCompatActivity {
    private static final String TAG = "CooperationActivity" ;
    EditText cooperation_name_field;
    EditText cooperation_family_field;
    EditText cooperation_email_field;
    EditText cooperation_phone_field;
    EditText cooperation_center_field;
    EditText cooperation_description_field;
    TextView home;
    Button coop_send_btn;
    Button classification_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperation);
        Typeface solidfont = Typeface.createFromAsset(getAssets(),"fonts/Font-Awesome-5-Free-Solid-900.ttf");
        Typeface mainfont = Typeface.createFromAsset(getAssets(),"fonts/B Yekan+.ttf"); // works ? yes
        classification_button = findViewById(R.id.classification_btn);
        cooperation_name_field = findViewById(R.id.cooperation_name_field);
        cooperation_family_field = findViewById(R.id.cooperation_family_field);
        cooperation_email_field = findViewById(R.id.cooperation_email_field);
        cooperation_center_field = findViewById(R.id.cooperation_center_name_field);
        cooperation_phone_field = findViewById(R.id.cooperation_phone_field);
        cooperation_description_field = findViewById(R.id.cooperation_description_field);
        coop_send_btn = findViewById(R.id.cooperation_snd_btn);
        setFonts(mainfont,
                cooperation_name_field,
                cooperation_family_field,
                cooperation_email_field,
                cooperation_center_field,
                cooperation_phone_field,
                cooperation_description_field
        );
        classification_button.setOnClickListener((view)->{
            Toast.makeText(CooperationActivity.this,"is it working ?",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onCreate: sdsdsfssf");
            startActivity(new Intent(CooperationActivity.this,Pop.class));
        });

        coop_send_btn.setOnClickListener((view)-> {
            sendCoopReq();
        });
    }

    private void setFonts(Typeface tf,EditText... editTexts){

        for (EditText et: editTexts) {
            et.setTypeface(tf);
        }

//        for (int i=0;i<editTexts.length;i++)
//        {
//            editTexts[i].setTypeface(tf);
//        }
//        return;
    }

    private void sendCoopReq(){
        String name = cooperation_name_field.getText().toString();
        String family = cooperation_family_field.getText().toString();
        String email = cooperation_email_field.getText().toString();
        String center = cooperation_center_field.getText().toString();
        String phone = cooperation_phone_field.getText().toString();
        String desc = cooperation_description_field.getText().toString();


        // do some validation here :

        Call<ResponseBody> call = RetrofitClient.getmInstance().getApi().cooperationRequest(
                name,family,email,center,phone,desc
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response != null) {
                    Log.d(TAG, "onResponse: " + "your request will proceed .");
                }else{
                    Log.d(TAG, "onResponse: " + "bad request.");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
