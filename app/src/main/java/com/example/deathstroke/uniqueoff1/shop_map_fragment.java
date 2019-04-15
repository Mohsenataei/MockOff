package com.example.deathstroke.uniqueoff1;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;



public class shop_map_fragment extends Fragment implements LocationListener {


    private static final String TAG = "Mohsen";
    private MyLocationNewOverlay mLocationOverlay;
    private CompassOverlay mCompassOverlay;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
    protected ImageButton btCenterMap;
    protected ImageButton btFollowMe;
    private LocationManager lm;
    private Location currentLocation = null;
    MapView mapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutInflater = inflater.inflate(R.layout.fragment_shop_map_fragment, container, false);
        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        mapView = layoutInflater.findViewById(R.id.shop_map_location);
        return layoutInflater;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = this.getActivity();
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();

        this.mCompassOverlay = new CompassOverlay(context, new InternalCompassOrientationProvider(context),
                mapView);
        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(context),
                mapView);

        mScaleBarOverlay = new ScaleBarOverlay(mapView);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);

        mRotationGestureOverlay = new RotationGestureOverlay(mapView);
        mRotationGestureOverlay.setEnabled(true);

        mapView.getController().setZoom(15);
        mapView.setTilesScaledToDpi(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setFlingEnabled(true);
        mapView.getOverlays().add(this.mLocationOverlay);
        mapView.getOverlays().add(this.mCompassOverlay);
        mapView.getOverlays().add(this.mScaleBarOverlay);

        mLocationOverlay.enableMyLocation();
        mLocationOverlay.enableFollowLocation();
        mLocationOverlay.setOptionsMenuEnabled(true);
        mCompassOverlay.enableCompass();
//
//        btCenterMap = view.findViewById(R.id.ic_center_map);
//
//        btCenterMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "centerMap clicked ");
//                if (currentLocation != null) {
//                    GeoPoint myPosition = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
//                    mapView.getController().animateTo(myPosition);
//                }
//            }
//        });

//        btFollowMe = view.findViewById(R.id.ic_follow_me);
//
//        btFollowMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "btFollowMe clicked ");
//                if (!mLocationOverlay.isFollowLocationEnabled()) {
//                    mLocationOverlay.enableFollowLocation();
//                    btFollowMe.setImageResource(R.drawable.ic_follow_me_on);
//                } else {
//                    mLocationOverlay.disableFollowLocation();
//                    btFollowMe.setImageResource(R.drawable.ic_follow_me);
//                }
//            }
//        });

    }
    @Override
    public void onPause() {
        super.onPause();
        try{
            lm.removeUpdates(this);
        }catch (Exception ex){}

        mCompassOverlay.disableCompass();
        mLocationOverlay.disableFollowLocation();
        mLocationOverlay.disableMyLocation();
        mScaleBarOverlay.enableScaleBar();
    }
    @Override
    public void onResume(){
        super.onResume();
        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        try{
            //this fails on AVD 19s, even with the appcompat check, says no provided named gps is available
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0l,0f,this);
        }catch (Exception ex){}

        try{
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0l,0f,this);
        }catch (Exception ex){}

        mLocationOverlay.enableFollowLocation();
        mLocationOverlay.enableMyLocation();
        mScaleBarOverlay.disableScaleBar();
    }

//    @Override
//    public String getSampleTitle() {
//        return "Follow Me";
//    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        lm=null;
        currentLocation=null;

        mLocationOverlay=null;
        mCompassOverlay=null;
        mScaleBarOverlay=null;
        mRotationGestureOverlay=null;
        btCenterMap=null;
        btFollowMe=null;
    }
@Override
public void onLocationChanged(Location location) {
    currentLocation=location;
}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

}
    @Override
    public void onProviderDisabled(String provider) {

    }
}
