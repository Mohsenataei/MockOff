package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import Service.CustomTypefaceSpan;
import Service.SaveSharedPreference;
import Service.SetTypefaces;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class Classification extends AppCompatActivity  implements DrawerLayout.DrawerListener{

    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    BottomNavigationView bottomNavigationView;
    ImageButton drawer,backbtn;
    NavigationView navigationView;
    TextView appbar_tv,appname;
    Typeface yekanfont;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;

    private LinearLayout amusmentLayout, servicesLayout, restaurantLayout, clothesLayout, cafeLayout
            , healthLayout, educationLayout, makeupsLayout, culturalLayout;
    private TextView amusmenttextView, servicestextView, restauranttextView, clothestextView, cafetextView
            , healthtextView, educationtextView, makeupstextView, culturaltextView;
    private boolean[] layoutflags = new boolean[9];
    private boolean[] textViewflags = new boolean[9];

    private static final int AMUSMENT = 0;
    private static final int SERVICE = 1;
    private static final int RESTAURANT = 2;
    private static final int CLOTHES = 3;
    private static final int HEALTH = 4;
    private static final int CAFE = 5;
    private static final int EDUCATION = 6;
    private static final int MAKEUP = 7;
    private static final int CULTURAL = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classification);
//        ButterKnife.bind(this);
        yekanfont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        bottomNavigationView = findViewById(R.id.navigation);
        main = findViewById(R.id.classification_page);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerElevation(0);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(this);
        drawerLayout.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawebtn);

        InitializeLinearLayouts();
        InitialaizeTextViews();

        InitializeLayoutFlags();
        InitializeTextViewFlags();

        //gridLayoutItemOnclick();

        amusmentLayout.setOnClickListener(view->{
            OnClickAmusmentLayout();
            StartShowClassificationActivity("تفریحی");
        });

        servicesLayout.setOnClickListener(view->{
            OnClickServicesLayout();
            StartShowClassificationActivity("خدمات");
        });

        restaurantLayout.setOnClickListener(view->{
            OnClickRestaurantLayout();
            StartShowClassificationActivity("رستوران");
        });

        clothesLayout.setOnClickListener(view ->{
            OnClickClothesLayout();
            StartShowClassificationActivity("پوشاک");
        });

        healthLayout.setOnClickListener(view->{
            OnClickHealthtLayout();
            StartShowClassificationActivity("سلامت");
        });

        cafeLayout.setOnClickListener(view->{
            OnClickCafeLayout();
            StartShowClassificationActivity("کافی شاپ");
        });

        educationLayout.setOnClickListener(view->{
            OnClickEducationLayout();
            StartShowClassificationActivity("آموزش");
        });

        makeupsLayout.setOnClickListener(view->{
            OnClickMakeUpsLayout();
            StartShowClassificationActivity("آرایشی و بهداشتی");
        });

        culturalLayout.setOnClickListener(view->{
            OnClickCulturalLayout();
            StartShowClassificationActivity("فرهنگی");
        });




        drawer.setOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
        });

        appbar_tv = findViewById(R.id.just_appbar_tv);
        appbar_tv.setText("دسته بندی");
        appbar_tv.setTypeface(yekanfont);

        backbtn = findViewById(R.id.back_button);

        backbtn.setOnClickListener(view->{
            finish();
        });
//        amusment = findViewById(R.id.class_amusment);
//        amusment.setOnClickListener(view ->{
//            Toast.makeText(this, "working yay", Toast.LENGTH_SHORT).show();
//        });
        View header_items = navigationView.getHeaderView(0);

        initilizeheaderbuttons(header_items);

        setHeaderitems();
        handleNavDrawerItemClick();
        SetTypefaces.setButtonTypefaces(
                yekanfont,signup,signin,followed_centers,
                bookmarks,terms_off_service, frequently_asked_questions,
                contactus,share_with_friends,exit,edit
        );



        bottomNavigationView = findViewById(R.id.navigation);

        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", yekanfont);
        for (int i=0;i<bottomNavigationView.getMenu().size();i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
            if (i==0) {
                mMenuitem.setChecked(true);
            }
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home :
                    startActivity(new Intent(Classification.this,MainActivity.class));
                    break;
                case R.id.navigation_nearest_off :
                    startActivity(new Intent(Classification.this,Map.class));
                    break;
                case R.id.navigation_my_codes :
                    startActivity(new Intent(Classification.this,MyCodes.class));
                    break;
                case R.id.classification:
                    break;
            }
            return false;
        });
}

    private void StartShowClassificationActivity(String field) {
        Intent intent = new Intent(this,ShowClassification.class);
        intent.putExtra("field",field);
        startActivity(intent);
    }

    private void InitializeLayoutFlags() {
        for (int i =0; i<layoutflags.length; i++){
            layoutflags[i] = false;
        }
    }
    private void InitializeTextViewFlags() {
        for (int i =0; i<textViewflags.length; i++){
            textViewflags[i] = false;
        }
    }

    private void InitializeLinearLayouts() {
        amusmentLayout = findViewById(R.id.amusment_layout);
        servicesLayout = findViewById(R.id.services_layout);
        restaurantLayout = findViewById(R.id.restaurant_layout);
        clothesLayout = findViewById(R.id.clothes_layout);
        healthLayout = findViewById(R.id.health_layout);
        cafeLayout = findViewById(R.id.cafe_layout);
        educationLayout = findViewById(R.id.education_layout);
        makeupsLayout = findViewById(R.id.makeUps_layout);
        culturalLayout = findViewById(R.id.cultural_layout);
    }

    private void InitialaizeTextViews(){
        amusmenttextView = findViewById(R.id.amusment_textView);
        servicestextView = findViewById(R.id.services_textView);
        restauranttextView = findViewById(R.id.restaurant_textView);
        clothestextView = findViewById(R.id.clothes_textView);
        healthtextView = findViewById(R.id.health_textView);
        cafetextView = findViewById(R.id.cafe_textView);
        educationtextView = findViewById(R.id.education_textView);
        makeupstextView = findViewById(R.id.makeUps_textView);
        culturaltextView = findViewById(R.id.cultural_textView);
    }

    private void initilizeheaderbuttons(View header_items) {
        ButterKnife.bind(header_items);
        signup = header_items.findViewById(R.id.header_sign_up);
        signin = header_items.findViewById(R.id.header_sign_in);
        bookmarks = header_items.findViewById(R.id.bookmark_centers);
        followed_centers = header_items.findViewById(R.id.followed_centers);
        frequently_asked_questions = header_items.findViewById(R.id.header_faq);
        terms_off_service = header_items.findViewById(R.id.terms);
        contactus = header_items.findViewById(R.id.contact_us);
        share_with_friends = header_items.findViewById(R.id.share_us);
        exit = header_items.findViewById(R.id.exit);
        edit = header_items.findViewById(R.id.edit);
        appname = header_items.findViewById(R.id.header_app_name);
    }
    private void handleNavDrawerItemClick(){
        signup.setOnClickListener(view->{
            startActivity(new Intent(Classification.this,SignUpActivity.class));
        });
        signin.setOnClickListener(view->{
            startActivity(new Intent(Classification.this,SingInActivity.class));
        });

        bookmarks.setOnClickListener(view -> {
            startActivity(new Intent(Classification.this,BookMarkedPosts.class));
        });

        followed_centers.setOnClickListener(view -> {
            startActivity(new Intent(Classification.this,FollowedShops.class));
            //drawerLayout.closeDrawer(navigationView);
        });

        frequently_asked_questions.setOnClickListener(view -> {
            startActivity(new Intent(Classification.this,FAQ.class));
        });

//        terms_off_service.setOnClickListener(view->{
//            startActivity(new Intent(MyCodes.this,.class));
//        });

        contactus.setOnClickListener(view->{
            startActivity(new Intent(Classification.this,contact_us.class));
        });

        share_with_friends.setOnClickListener(view -> {
            //startActivity(new Intent(MyCodes.this,BookMarkedPosts.class));
            Toast.makeText(this, "پیشنهاد به دوستان", Toast.LENGTH_SHORT).show();
        });

        exit.setOnClickListener(view ->{
            //finish();
            System.exit(0);
        });

        edit.setOnClickListener(view->{
            Toast.makeText(this, "this part is yet to be complete", Toast.LENGTH_SHORT).show();
        });


    }
    private void setHeaderitems() {
        if(SaveSharedPreference.getAPITOKEN(Classification.this).length() > 0  ){
            signin.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            appname.setVisibility(View.VISIBLE);
            appname.setText(R.string.title_activity_test_navigation_drawer);
            appname.setTypeface(yekanfont);
        }
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
    public void onDrawerSlide(View drawerView, float slideOffset) {
        float slideX = drawerView.getWidth() * slideOffset;
        main.setTranslationX(-slideX);
    }

    @Override
    public void onDrawerStateChanged(int arg0) {
        //write your code
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private void OnClickAmusmentLayout(){
        if (!layoutflags[AMUSMENT]){
            ChangeLayoutBackgroundToBlue(amusmentLayout);
            ChangeTextViewDrawableTop(amusmenttextView,getResources().getDrawable(R.drawable.ic_swimming_pool_solid_onclick),textViewflags[AMUSMENT]);
            layoutflags[AMUSMENT]=true;
            textViewflags[AMUSMENT]=true;
        }else {
            ChangeLayoutBackgroundToDefault(amusmentLayout);
            ChangeTextViewDrawableTop(amusmenttextView,getResources().getDrawable(R.drawable.ic_swimming_pool_solid),textViewflags[AMUSMENT]);
            layoutflags[AMUSMENT]=false;
            textViewflags[AMUSMENT]=false;
        }

    }
    private void OnClickServicesLayout(){
        if(!layoutflags[SERVICE]){
            ChangeLayoutBackgroundToBlue(servicesLayout);
            ChangeTextViewDrawableTop(servicestextView,getResources().getDrawable(R.drawable.ic_wrench_solid_onclick),textViewflags[SERVICE]);
            layoutflags[SERVICE]=true;
            textViewflags[SERVICE]=true;
        }else{
            ChangeLayoutBackgroundToDefault(servicesLayout);
            ChangeTextViewDrawableTop(servicestextView,getResources().getDrawable(R.drawable.ic_wrench_solid),textViewflags[SERVICE]);
            layoutflags[SERVICE]=false;
            textViewflags[SERVICE]=false;
        }

    }
    private void OnClickRestaurantLayout(){

        if(!layoutflags[RESTAURANT]){
            ChangeLayoutBackgroundToBlue(restaurantLayout);
            ChangeTextViewDrawableTop(restauranttextView,getResources().getDrawable(R.drawable.ic_concierge_bell_solid_onclick),textViewflags[RESTAURANT]);
            layoutflags[RESTAURANT]=true;
            textViewflags[RESTAURANT]=true;
        }else{
            ChangeLayoutBackgroundToDefault(restaurantLayout);
            ChangeTextViewDrawableTop(restauranttextView,getResources().getDrawable(R.drawable.ic_concierge_bell_solid),textViewflags[RESTAURANT]);
            layoutflags[RESTAURANT]=false;
            textViewflags[RESTAURANT]=false;
        }

    }
    private void OnClickClothesLayout(){
        if(!layoutflags[CLOTHES]){
            ChangeLayoutBackgroundToBlue(clothesLayout);
            ChangeTextViewDrawableTop(clothestextView,getResources().getDrawable(R.drawable.ic_tshirt_solid_onclick),textViewflags[CLOTHES]);
            layoutflags[CLOTHES]=true;
            textViewflags[CLOTHES]=true;
        }else{
            ChangeLayoutBackgroundToDefault(clothesLayout);
            ChangeTextViewDrawableTop(clothestextView,getResources().getDrawable(R.drawable.ic_tshirt_solid),textViewflags[CLOTHES]);
            layoutflags[CLOTHES]=false;
            textViewflags[CLOTHES]=false;
        }
    }
    private void OnClickCafeLayout(){
        if(!layoutflags[CAFE]){
            ChangeLayoutBackgroundToBlue(cafeLayout);
            ChangeTextViewDrawableTop(cafetextView,getResources().getDrawable(R.drawable.ic_coffee_solid_onclick),textViewflags[CAFE]);
            layoutflags[CAFE]=true;
            textViewflags[CAFE]=true;
        }else{
            ChangeLayoutBackgroundToDefault(cafeLayout);
            ChangeTextViewDrawableTop(cafetextView,getResources().getDrawable(R.drawable.ic_coffee_solid),textViewflags[CAFE]);
            layoutflags[CAFE]=false;
            textViewflags[CAFE]=false;
        }
    }
    private void OnClickHealthtLayout(){
        if(!layoutflags[HEALTH]){
            ChangeLayoutBackgroundToBlue(healthLayout);
            ChangeTextViewDrawableTop(healthtextView,getResources().getDrawable(R.drawable.ic_heartbeat_onclick),textViewflags[HEALTH]);
            layoutflags[HEALTH]=true;
            textViewflags[HEALTH]=true;
        }else{
            ChangeLayoutBackgroundToDefault(healthLayout);
            ChangeTextViewDrawableTop(healthtextView,getResources().getDrawable(R.drawable.ic_heartbeat),textViewflags[HEALTH]);
            layoutflags[HEALTH]=false;
            textViewflags[HEALTH]=false;
        }
    }
    private void OnClickMakeUpsLayout(){
        if(!layoutflags[MAKEUP]){
            ChangeLayoutBackgroundToBlue(makeupsLayout);
            ChangeTextViewDrawableTop(makeupstextView,getResources().getDrawable(R.drawable.ic_paint_brush_onclick),textViewflags[MAKEUP]);
            layoutflags[MAKEUP]=true;
            textViewflags[MAKEUP]=true;
        }else{
            ChangeLayoutBackgroundToDefault(makeupsLayout);
            ChangeTextViewDrawableTop(makeupstextView,getResources().getDrawable(R.drawable.ic_paint_brush),textViewflags[MAKEUP]);
            layoutflags[MAKEUP]=false;
            textViewflags[MAKEUP]=false;
        }
    }
    private void OnClickCulturalLayout(){
        if(!layoutflags[CULTURAL]){
            ChangeLayoutBackgroundToBlue(culturalLayout);
            ChangeTextViewDrawableTop(culturaltextView,getResources().getDrawable(R.drawable.ic_theater_masks_onclick),textViewflags[CULTURAL]);
            layoutflags[CULTURAL]=true;
            textViewflags[CULTURAL]=true;
        }else{
            ChangeLayoutBackgroundToDefault(culturalLayout);
            ChangeTextViewDrawableTop(culturaltextView,getResources().getDrawable(R.drawable.ic_theater_masks),textViewflags[CULTURAL]);
            layoutflags[CULTURAL]=false;
            textViewflags[CULTURAL]=false;
        }
    }
    private void OnClickEducationLayout(){
        if(!layoutflags[EDUCATION]){
            ChangeLayoutBackgroundToBlue(educationLayout);
            ChangeTextViewDrawableTop(educationtextView,getResources().getDrawable(R.drawable.ic_education_onclick),textViewflags[EDUCATION]);
            layoutflags[EDUCATION]=true;
            textViewflags[EDUCATION]=true;
        }else{
            ChangeLayoutBackgroundToDefault(educationLayout);
            ChangeTextViewDrawableTop(educationtextView,getResources().getDrawable(R.drawable.ic_education),textViewflags[EDUCATION]);
            layoutflags[EDUCATION]=false;
            textViewflags[EDUCATION]=false;
        }
    }





//    private void gridLayoutItemOnclick(){
//        amusmentLayout.setOnClickListener(v->{
//            if (!layoutflags[AMUSMENT]){
//                layoutflags[AMUSMENT] = true;
//                OnClickAmusmentLayout();
//            }else{
//                layoutflags[AMUSMENT]=false;
//                UndoOnClickAmusmentLayout();
//            }
//
//        });
//    }

    private void ChangeLayoutBackgroundToBlue(LinearLayout layout){
        layout.setBackground(getResources().getDrawable(R.drawable.grid_item_border_onclick));
    }

    private void ChangeLayoutBackgroundToDefault(LinearLayout layout){
        layout.setBackground(getResources().getDrawable(R.drawable.grid_items_border));
    }

    private void ChangeTextViewDrawableTop(TextView tv, Drawable drawableTop,boolean flag){
        //tv.setCompoundDrawables(null,drawableTop,null,null);
        tv.setCompoundDrawablesWithIntrinsicBounds(null,drawableTop,null,null);
        if (flag){
            tv.setTextColor(getResources().getColor(R.color.coop_item_color));

        }else{
            tv.setTextColor(getResources().getColor(R.color.white));
        }

    }

    private void ChangeTextViewDrawableTopToDefault(TextView tv, Drawable defaultdrawable){
        tv.setCompoundDrawables(null,defaultdrawable,null,null);

    }
}
