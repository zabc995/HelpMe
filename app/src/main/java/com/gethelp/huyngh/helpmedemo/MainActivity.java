package com.gethelp.huyngh.helpmedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.Window;


public class MainActivity extends BaseActivity {
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                LatLng frmVietNam=new LatLng(21.0166458, 105.7841248);
                mGoogleMap.addMarker(new MarkerOptions().position(frmVietNam).title("Fregment VietNam"));
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(frmVietNam,18));
            }
        });
    }
}
