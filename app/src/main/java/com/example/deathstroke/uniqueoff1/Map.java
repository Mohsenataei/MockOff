package com.example.deathstroke.uniqueoff1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.DelayedMapListener;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import Service.CustomTypefaceSpan;
import Service.RetrofitClient;
import Service.SaveSharedPreference;
import Service.SetTypefaces;
import butterknife.Bind;
import butterknife.ButterKnife;
import entities.NearestShops;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Map extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, DrawerLayout.DrawerListener {

    private static final int MULTIPLE_PERMISSION_REQUEST_CODE = 4;
    private  MapView mapView ;
//    private MyLocationOverlay myLocationoverlay;
//    private MapController mapController;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    protected DrawerLayout drawerLayout;
    protected ConstraintLayout main;
    private Button signup,signin, followed_centers, bookmarks,terms_off_service, frequently_asked_questions,contactus,share_with_friends,exit,edit;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    Typeface yekanFont;
    private TextView appname;
    @Bind(R.id.drawebtn)
    ImageButton drawerbtn;
    @Bind(R.id.back_button)
    ImageButton backbtn;

    List<NearestShops> nearestShopsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        main = findViewById(R.id.map_page);
        ButterKnife.bind(this);
        yekanFont = Typeface.createFromAsset(getAssets(), "fonts/B Yekan+.ttf");
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerElevation(0);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(this);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        openDrawwer();
        backbtn.setOnClickListener(view -> {
            finish();
        });
        checkPermissionsState();
//
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
//        initializemap();
//        myLocationoverlay = new MyLocationOverlay(this, map);
//        myLocationoverlay.disableMyLocation(); // not on by default
//        myLocationoverlay.disableCompass();
//        myLocationoverlay.disableFollowLocation();
//        myLocationoverlay.setDrawAccuracyEnabled(true);
//        map = findViewById(R.id.main_map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        IMapController mapController = mapView.getController();
        mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(34.796830, 48.514820);
        mapController.setCenter(startPoint);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        TextView tv = findViewById(R.id.just_appbar_tv);
        tv.setTypeface(yekanFont);
        tv.setText("تخفیف های اطراف من");
        //View headerLayout = navigationView.getHeaderView(0);

        View header_items = navigationView.getHeaderView(0);

        initilizeheaderbuttons(header_items);
        setHeaderitems();
        handleNavDrawerItemClick();

        SetTypefaces.setButtonTypefaces(yekanFont, signup, signin, followed_centers, bookmarks, terms_off_service, frequently_asked_questions, contactus, share_with_friends, exit, edit);

        bottomNavigationView = findViewById(R.id.navigation);

        CustomTypefaceSpan typefaceSpan = new CustomTypefaceSpan("", yekanFont);
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem mMenuitem = bottomNavigationView.getMenu().getItem(i);
            SpannableStringBuilder spannableTitle = new SpannableStringBuilder(mMenuitem.getTitle());
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length(), 0);
            mMenuitem.setTitle(spannableTitle);
            if (i == 2) {
                mMenuitem.setChecked(true);
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(Map.this, MainActivity.class));
                    break;
                case R.id.navigation_nearest_off:
                    break;
                case R.id.navigation_my_codes:
                    startActivity(new Intent(Map.this, MyCodes.class));
                    break;
                case R.id.classification:
                    startActivity(new Intent(Map.this, Classification.class));
                    break;
            }
            return false;
        });
        getnearDiscount();


        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        Drawable mMarker = this.getResources().getDrawable(R.drawable.marker_default);


        GeoPoint point;
        if (nearestShopsList.size() > 0) {
            for (int i = 0; i < 5; i++) {
                double lat, lan;
                lat = 34.796830 + i;
                        //Double.parseDouble(nearestShopsList.get(i).getLatitude());
                lan = 48.514820 + i;
                        //Double.parseDouble(nearestShopsList.get(i).getLongitude());
                items.add(new OverlayItem("title", "some description", new GeoPoint(lat, lan)));
            }
        }

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        //do something
                        Toast.makeText(Map.this, "click on map", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Map.this,Shop.class));
                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                },Map.this);
        mOverlay.setFocusItemsOnTap(true);
        mapView.getOverlays().add(mOverlay);

        Marker startmarker = new Marker(mapView);
        startmarker.setIcon(getResources().getDrawable(R.drawable.shop_map_marker));
        mapView.getOverlays().add(startmarker);

    }

    private void openDrawwer() {
        drawerbtn.setOnClickListener(view->{
            drawerLayout.openDrawer(navigationView);
        });
    }

    private void checkPermissionsState() {
        int internetPermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);

        int networkStatePermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE);

        int writeExternalStoragePermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int coarseLocationPermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        int fineLocationPermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        int wifiStatePermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_WIFI_STATE);

        if (internetPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                networkStatePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                writeExternalStoragePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                coarseLocationPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                fineLocationPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                wifiStatePermissionCheck == PackageManager.PERMISSION_GRANTED) {

            setupMap();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_WIFI_STATE},
                    MULTIPLE_PERMISSION_REQUEST_CODE);
        }
    }

    private void setupMap() {

        mapView = (MapView) findViewById(R.id.main_map);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        //setContentView(mapView); //displaying the MapView

        mapView.getController().setZoom(15); //set initial zoom-level, depends on your need
        //mapView.getController().setCenter(ONCATIVO);
        //mapView.setUseDataConnection(false); //keeps the mapView from loading online tiles using network connection.
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);

        MyLocationNewOverlay oMapLocationOverlay = new MyLocationNewOverlay(mapView);
        mapView.getOverlays().add(oMapLocationOverlay);
        oMapLocationOverlay.enableFollowLocation();
        oMapLocationOverlay.enableMyLocation();
        oMapLocationOverlay.enableFollowLocation();
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        CompassOverlay compassOverlay = new CompassOverlay(this, mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        mapView.setMapListener(new DelayedMapListener(new MapListener() {
            public boolean onZoom(final ZoomEvent e) {
                MapView mapView = (MapView) findViewById(R.id.main_map);

                String latitudeStr = "" + mapView.getMapCenter().getLatitude();
                String longitudeStr = "" + mapView.getMapCenter().getLongitude();

                String latitudeFormattedStr = latitudeStr.substring(0, Math.min(latitudeStr.length(), 7));
                String longitudeFormattedStr = longitudeStr.substring(0, Math.min(longitudeStr.length(), 7));

                Log.i("zoom", "" + mapView.getMapCenter().getLatitude() + ", " + mapView.getMapCenter().getLongitude());
                TextView latLongTv = (TextView) findViewById(R.id.textView);
//                latLongTv.setText("" + latitudeFormattedStr + ", " + longitudeFormattedStr);
                return true;
            }

            public boolean onScroll(final ScrollEvent e) {
                MapView mapView = (MapView) findViewById(R.id.main_map);

                String latitudeStr = "" + mapView.getMapCenter().getLatitude();
                String longitudeStr = "" + mapView.getMapCenter().getLongitude();

                String latitudeFormattedStr = latitudeStr.substring(0, Math.min(latitudeStr.length(), 7));
                String longitudeFormattedStr = longitudeStr.substring(0, Math.min(longitudeStr.length(), 7));

                Log.i("scroll", "" + mapView.getMapCenter().getLatitude() + ", " + mapView.getMapCenter().getLongitude());
                TextView latLongTv = (TextView) findViewById(R.id.textView);
//                latLongTv.setText("" + latitudeFormattedStr + ", " + longitudeFormattedStr);
                return true;
            }
        }, 1000));

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
            startActivity(new Intent(Map.this,SignUpActivity.class));
        });
        signin.setOnClickListener(view->{
            startActivity(new Intent(Map.this,SingInActivity.class));
        });

        bookmarks.setOnClickListener(view -> {
            startActivity(new Intent(Map.this,BookMarkedPosts.class));
        });

        followed_centers.setOnClickListener(view -> {
            startActivity(new Intent(Map.this,FollowedShops.class));
            //drawerLayout.closeDrawer(navigationView);
        });

        frequently_asked_questions.setOnClickListener(view -> {
            startActivity(new Intent(Map.this,FAQ.class));
            //drawerLayout.closeDrawer(navigationView);
        });

//        terms_off_service.setOnClickListener(view->{
//            startActivity(new Intent(MyCodes.this,.class));
//        });

        contactus.setOnClickListener(view->{
            startActivity(new Intent(Map.this,contact_us.class));
        });

        share_with_friends.setOnClickListener(view -> {
            // startActivity(new Intent(MainActivity.this,BookMarkedPosts.class));
            Toast.makeText(this, "yet to be published", Toast.LENGTH_SHORT).show();
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
        if(SaveSharedPreference.getAPITOKEN(Map.this).length() > 0  ){
            signin.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.INVISIBLE);
            appname.setVisibility(View.VISIBLE);
            appname.setText(R.string.title_activity_test_navigation_drawer);
            appname.setTypeface(yekanFont);
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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

    private void getnearDiscount(){
        Call<List<NearestShops>> call = RetrofitClient.getmInstance().getApi().getNearestDiscounts("34.797732","48.514845");
        call.enqueue(new Callback<List<NearestShops>>() {
            @Override
            public void onResponse(Call<List<NearestShops>> call, Response<List<NearestShops>> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(Map.this, "response is not empty", Toast.LENGTH_SHORT).show();
                    if(!nearestShopsList.isEmpty()){
                        nearestShopsList.clear();
                    }

                    nearestShopsList = response.body();
                }else {
                    Toast.makeText(Map.this, "Response is empty", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<NearestShops>> call, Throwable t) {

            }
        });

    }
}
