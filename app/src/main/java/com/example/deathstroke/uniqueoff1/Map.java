package com.example.deathstroke.uniqueoff1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class Map extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int MULTIPLE_PERMISSION_REQUEST_CODE = 4;
    private  MapView mapView ;
//    private MyLocationOverlay myLocationoverlay;
//    private MapController mapController;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
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
//        map.setMultiTouchControls(true);
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
}
