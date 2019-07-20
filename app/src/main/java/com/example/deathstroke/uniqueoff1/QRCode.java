package com.example.deathstroke.uniqueoff1;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import Service.SaveSharedPreference;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import Service.SetTypefaces;

public class QRCode extends AppCompatActivity implements DrawerLayout.DrawerListener{

    private TextView tag_name,tag_count,tag_status,tag,tag_code,qrcode_tv,qrcode_code;
    private ImageButton drawerbtn,backbtn;
    private ImageView qrcode;
    private Button save_to_gallery, alriht;
    Button share_us, sign_up, sign_in, followed_centers, terms_of_service, Contact_us, edit, exit, bookmarks, coopreq, testbtn,mapbutton;
    private Spinner cities;
    private Typeface myFont;

    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    Context context;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String TAG = "QRCode";
    private final int REQUEST_PERMISSION = 0xf0;

    private Bitmap qrImage;
    private QRCode self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.qrcode_page);

        Typeface yekanfont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        myFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");

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
        qrcode_code = findViewById(R.id.qrcode_num);
        backbtn = findViewById(R.id.back_button);

        backbtn.setOnClickListener(view -> {
            finish();
        });

        SetTypefaces.SetTextviewTypefaces(yekanfont, tag_name, tag_count, tag_status, tag, tag_code, qrcode_tv);
        drawerbtn.setOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
        });

        save_to_gallery = findViewById(R.id.save_in_gallery);


        alriht = findViewById(R.id.alright);

        SetTypefaces.setButtonTypefaces(yekanfont, save_to_gallery, alriht);

        qrcode = findViewById(R.id.qrcode);

        // change spinner font :


            // handle navigation item selection :

            View headerLayout = navigationView.getHeaderView(0);
          getIntentExres();
          //generateImage();
        save_to_gallery.setOnClickListener(view -> {
            saveToGallery();
            //saveImage(qrcode);
            finish();

        });


        View  header_items = navigationView.getHeaderView(0);
        cities = header_items.findViewById(R.id.cities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities, R.layout.spinner_text_view_1);
        adapter.setDropDownViewResource(R.layout.spinner_text_view);
        cities.setAdapter(adapter);

        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = parent.getItemAtPosition(position).toString();
                SaveSharedPreference.setCity(QRCode.this,city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        }


    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }



    private void generateqrcode(String sample){
        qrcode_code.setText(sample);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(sample,BarcodeFormat.QR_CODE,1000,1000);
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


        File file = new File(storageLoc, "Mocatag " + new Date().toString() + ".jpg");

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
    private void getIntentExres() {
        Log.d(TAG, "getIntentExres: ");
        Toast.makeText(context, "til here working fine", Toast.LENGTH_SHORT).show();
//        if (getIntent().hasExtra("qr_code")){
//            Toast.makeText(context, "qr code send by intent", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "qr code was not send by intent", Toast.LENGTH_SHORT).show();
//        }
//        if (getIntent().hasExtra("ticket_status")){
//            Toast.makeText(context, "ticket status send by intent", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(context, "ticket status was not send by intent", Toast.LENGTH_SHORT).show();
//        }
        if(getIntent().hasExtra("qr_code") && getIntent().hasExtra("ticket_status") && getIntent().hasExtra("count")){
            Log.d(TAG, "getIntentExres: has extra ");
            String qrcode = getIntent().getStringExtra("qr_code");
            int code = Integer.parseInt(qrcode);
            String type = getIntent().getStringExtra("ticket_status");
            int status = Integer.parseInt(type);
            String count = getIntent().getStringExtra("count");
            tag_count.setText(count);

            Toast.makeText(this, "qrcode :" +qrcode, Toast.LENGTH_SHORT).show();
            generateqrcode(qrcode);

        }
    }
    private void setVlues(String qrcode,int status){
        qrcode_code.setText(qrcode);

    }

    private void generateQrCodeImage(){
        if(getIntent().hasExtra("qr_code")){
            final String qrcode = getIntent().getStringExtra("qr_code");
            if(!qrcode.trim().isEmpty()){
                //new Thread()
            }
        }
    }
    private void generateImage(){
        final String text = getIntent().getStringExtra("qr_code");
        if(text.trim().isEmpty()){
            alert("Ketik dulu data yang ingin dibuat QR Code");
            return;
        }

        //endEditing();
        //showLoadingVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int size = qrcode.getMeasuredWidth();
                if( size > 1){
                    Log.e(TAG, "size is set manually");
                    size = 260;
                }

                Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
                hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                hintMap.put(EncodeHintType.MARGIN, 1);
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                try {
                    BitMatrix byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size,
                            size, hintMap);
                    int height = byteMatrix.getHeight();
                    int width = byteMatrix.getWidth();
                    qrImage = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++){
                        for (int y = 0; y < height; y++){
                            qrImage.setPixel(x, y, byteMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showImage(qrImage);
//                            self.showLoadingVisible(false);
//                            self.snackbar("QRCode telah dibuat");
                        }
                    });
                } catch (WriterException e) {
                    e.printStackTrace();
                    alert(e.getMessage());
                }
            }
        }).start();
    }
    private void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(self)
                .setTitle("QRCode Generator")
                .setMessage(message)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dlg.show();
    }
    private void showImage(Bitmap bitmap) {
        if (bitmap == null) {
            qrcode.setImageResource(android.R.color.transparent);
            qrImage = null;
            //txtSaveHint.setVisibility(View.GONE);
        } else {
            qrcode.setImageBitmap(bitmap);
            //txtSaveHint.setVisibility(View.VISIBLE);
        }
    }

    private void saveImage(ImageView img){
        if (img != null){

            img.buildDrawingCache();

            Bitmap qrcode = img.getDrawingCache();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
                return;
            }




            String fname = "Mocatag-" + Calendar.getInstance().getTimeInMillis();
            boolean success = true;
            try {
                String result = MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        qrcode,
                        fname,
                        "QRCode Image"
                );
                if (result == null) {
                    success = false;
                } else {
                    Log.e(TAG, result);
                }
            } catch (Exception e) {
                e.printStackTrace();
                success = false;
            }





        }

    }

}
