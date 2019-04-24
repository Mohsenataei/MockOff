package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import Service.SetTypefaces;

public class QRCode extends AppCompatActivity implements DrawerLayout.DrawerListener{

    private TextView tag_name,tag_count,tag_status,tag,tag_code,qrcode_tv;
    private ImageButton drawerbtn,backbtn;
    private ImageView qrcode;
    private Button save_to_gallery, alriht;

    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface yekanfont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        setContentView(R.layout.qrcode);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(this);
        drawerLayout.setDrawerElevation(0);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        context = getApplicationContext();
        NavigationView navigationView = findViewById(R.id.nav_view);
        main = findViewById(R.id.qrcode_page);
        drawerbtn = findViewById(R.id.drawebtn);
        tag_name = findViewById(R.id.qrcode_tag);
        tag_count = findViewById(R.id.ticked_count);
        tag_status = findViewById(R.id.qrcode_status);
        tag = findViewById(R.id.tag);
        tag_code = findViewById(R.id.qrcode_num);
        qrcode_tv = findViewById(R.id.just_appbar_tv);
        qrcode_tv.setText("کدهای من");
        backbtn = findViewById(R.id.back_button);

        backbtn.setOnClickListener(view -> {
            finish();
        });

        SetTypefaces.SetTextviewTypefaces(yekanfont,tag_name,tag_count,tag_status,tag,tag_code,qrcode_tv);
        drawerbtn.setOnClickListener(view->{
            drawerLayout.openDrawer(navigationView);
        });

        save_to_gallery = findViewById(R.id.save_in_gallery);

        save_to_gallery.setOnClickListener(view -> {
            saveToGallery();
            finish();

        });
        alriht = findViewById(R.id.alright);

        SetTypefaces.setButtonTypefaces(yekanfont,save_to_gallery, alriht);

        qrcode = findViewById(R.id.qrcode);
        generateqrcode();
    }

    private void generateqrcode(){
        String sample = "thisissampleforgenerationagainandagain";
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(sample,BarcodeFormat.QR_CODE,500,500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    private void saveToGallery(){

       //String s = MediaStore.Images.Media.insertImage(getContentResolver(),qrcode,"sample","sampleImage");
//        BitmapDrawable draw = (BitmapDrawable) qrcode.getDrawable();
//        Bitmap bitmap = draw.getBitmap();
//
//        FileOutputStream outStream = null;
//        File sdCard = Environment.getExternalStorageDirectory();
//        File dir = new File(sdCard.getAbsolutePath() + "/YourFolderName");
//        dir.mkdirs();
//        String fileName = String.format("%d.jpg", System.currentTimeMillis());
//        File outFile = new File(dir, fileName);
//        try {
//            outStream = new FileOutputStream(outFile);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
//        if (outStream != null) {
//            try {
//                outStream.flush();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        try {
//            outStream.close();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        intent.setData(Uri.fromFile(File));
//        sendBroadcast(intent);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43

        qrcode.buildDrawingCache();

        Bitmap bmp = qrcode.getDrawingCache();
        File storageLoc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //context.getExternalFilesDir(null);


        File file = new File(storageLoc, "sample" + new Date().toString() + ".jpg");

        try{
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            scanFile(context, Uri.fromFile(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);

    }
    @Override
    public void onDrawerOpened(View arg0) {
        //write your code
    }

    @Override
    public void onDrawerClosed(View arg0) {
        //write your code
    }



    @Override
    public void onDrawerStateChanged(int arg0) {
        //write your code
    }
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        float slideX = drawerView.getWidth() * slideOffset;
        main.setTranslationX(-slideX);
    }
}
