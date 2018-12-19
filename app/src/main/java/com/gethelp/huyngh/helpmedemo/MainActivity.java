package com.gethelp.huyngh.helpmedemo;


import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.MenuItem;
import android.view.Window;

import java.security.cert.Certificate;


public class MainActivity extends BaseActivity {
    private GoogleMap mGoogleMap;

    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Thiết lập Controls
        navigationView = (NavigationView) findViewById(R.id.mnuNavigation);
        drawerLayout=(DrawerLayout) findViewById(R.id.activity_main);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        //
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()){
                    case R.id.mit_edit_profile:
                        menuItem.setChecked(true);
                        intent = new Intent(MainActivity.this,EditProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.mit_notification:
                        break;
                    case R.id.mit_languages:
                        break;
                    case R.id.mit_location:
                        break;
                    case R.id.mit_payment:
                        break;
                    case R.id.mit_log_out:
                        intent = new Intent(MainActivity.this, LoginActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        menuItem.setChecked(true);
                        break;
                    case R.id.mit_about:
                        break;
                }

                return true;
            }
        });
        //
        checkFineLocationPermission();
        checkCoarseLocationPermission();
        initView();
    }
  
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
  
    private boolean checkCoarseLocationPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkFineLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        }
        else {
            return true;
        }
    }

    private void whereAmI(GoogleMap googleMappper) {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        @SuppressLint("MissingPermission") Location lastLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (lastLocation != null)
        {
            LatLng latLng=new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            googleMappper.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()))      // Sets the center of the map to location user
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera toeast
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            googleMappper.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            //Thêm MarketOption cho Map:
            MarkerOptions option=new MarkerOptions();
            option.title("You Here !!!");
            option.snippet("");
            option.position(latLng);
            Marker currentMarker= mGoogleMap.addMarker(option);
            currentMarker.showInfoWindow();
        }
    }

    private void initView() {
        showDialogLoading();
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frmMap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                mGoogleMap = googleMap;
                mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        dismissDialog();
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
                        mGoogleMap.setMyLocationEnabled(true);
                        whereAmI(mGoogleMap);
                    }
                });
            }
        });
    }
}
