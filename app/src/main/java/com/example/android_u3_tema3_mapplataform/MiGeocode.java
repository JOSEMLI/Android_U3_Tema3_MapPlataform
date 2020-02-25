package com.example.android_u3_tema3_mapplataform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MiGeocode extends AppCompatActivity implements OnMapReadyCallback {
  GoogleMap gMap;
  Marker marker; EditText midireccion;String nro="", address="";
  JsonObjectRequest jsonObjectRequest; RequestQueue request;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mi_geocode);
    midireccion=findViewById(R.id.midireccion);
    SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);
    request= Volley.newRequestQueue(getApplicationContext());
  }
  @Override
  public void onMapReady(GoogleMap googleMap) {
    gMap = googleMap;
    LatLng Tacna = new LatLng(-18.011737, -70.253529);
    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Tacna,15));
    gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
      @Override
      public void onMapClick(LatLng latLng) {
        if (marker != null) marker.remove();
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        marker = gMap.addMarker(markerOptions);
      }
    });
  }
  public void geocode(View view) {
    if (marker != null) {
      Double latitude = marker.getPosition().latitude;
      Double longitude = marker.getPosition().longitude;
      Log.i("ubicacionmarker","lat:"+latitude+" lon:"+longitude);
    }
  }
}
