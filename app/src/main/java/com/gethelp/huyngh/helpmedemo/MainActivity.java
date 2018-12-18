package com.gethelp.huyngh.helpmedemo;

import android.app.ActionBar;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.Window;

import java.security.cert.Certificate;


public class MainActivity extends BaseActivity {
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        showDialogLoading();
        SupportMapFragment supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frmMap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap=googleMap;
                mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        dismissDialog();
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
                    }
                });
                    LatLng frmLastlocation = new LatLng(21.0166458, 105.7841248);
                    mGoogleMap.addMarker(new MarkerOptions().position(frmLastlocation).title("I'm Here"));
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(frmLastlocation, 18));
            }
        });
    }
}
