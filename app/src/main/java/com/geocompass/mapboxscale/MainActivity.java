package com.geocompass.mapboxscale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.UiSettings;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private MapboxMap mMapboxMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_key));
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
    }


    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        mMapboxMap = mapboxMap;

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(39.9421242969957, 116.305169200667))
                .zoom(3)//放大尺度 从0开始，0即最大比例尺，最大未知，17左右即为街道层级
                .build();//创建CameraPosition对象
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 500);

        UiSettings uiSettings = mapboxMap.getUiSettings();
        uiSettings.setCompassEnabled(false);//隐藏指南针
        uiSettings.setLogoEnabled(false);//隐藏logo
        uiSettings.setRotateGesturesEnabled(false);//设置是否可以旋转地图
        uiSettings.setAttributionEnabled(false);//设置是否显示那个提示按钮

        /**
         * 控制比例尺
         * */
        mMapboxMap.setOnCameraChangeListener(new MapboxMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                MapScaleView mapScaleView = findViewById(R.id.view_scale_map);
                mapScaleView.refreshScaleView(mMapboxMap);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
