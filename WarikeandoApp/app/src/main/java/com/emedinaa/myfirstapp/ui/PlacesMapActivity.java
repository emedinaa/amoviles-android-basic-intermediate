package com.emedinaa.myfirstapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.emedinaa.myfirstapp.R;
import com.emedinaa.myfirstapp.model.entity.Warike;
import com.emedinaa.myfirstapp.model.storage.WarikeRepository;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacesMapActivity extends BaseActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener{

    @Nullable
    private List<Warike> places;
    @NotNull
    private Map<Marker,Warike> warikeHashMap;
    @NotNull
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_map);
        enabledBack();
        setUpGoogleMaps();
        warikeHashMap= new HashMap<>();
    }

    private void setUpGoogleMaps(){
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap=map;
        // We will provide our own zoom controls.
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrievePlaces();
    }

    private void renderPlaces(){
        warikeHashMap= new HashMap<>();
        Marker marker= null;
        for (Warike warike:places) {
            marker= buildMarker(warike);
            warikeHashMap.put(marker,warike);
        }
    }

    private Marker buildMarker(Warike warike){
        double tmpLat= warike.getLat();
        double tmpLng= warike.getLng();
        String tmpTile= warike.getName();
        Marker tmp=   googleMap.addMarker(new MarkerOptions().
                position(new LatLng(tmpLat, tmpLng)).title(tmpTile));
        return  tmp;
    }

    //provider
    private void retrievePlaces(){
        warikeRepository.getAllPlaces(new WarikeRepository.QueryCallback() {
            @Override
            public void onSuccess(List<Warike> mPlaces) {
                places= mPlaces;
                renderPlaces();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        //Warike warike= warikeHashMap.get(marker);
        //goToWarikeDetails(warike);
        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Warike warike= warikeHashMap.get(marker);
        goToWarikeDetails(warike);
    }

    private void goToWarikeDetails(Warike warike){
        Bundle bundle= new Bundle();
        bundle.putSerializable("WARIKE",warike);

        next(PlaceDetailsActivity.class,bundle,false);
    }
}

