package com.example.android_u3_tema3_mapplataform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class RutaEntreDosPuntos extends AppCompatActivity implements OnMapReadyCallback {
  GoogleMap gMap; Polyline polyline = null;
  List<LatLng> latLngList = new ArrayList<>(); List<Marker> markerList = new ArrayList<>();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ruta_entre_dos_puntos);
    SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);
  }
  @Override
  public void onMapReady(GoogleMap googleMap) {
    gMap = googleMap;
    LatLng Tacna = new LatLng(-18.011737, -70.253529);
    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Tacna,15));
    gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
      @Override
      public void onMapClick(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        Marker marker = gMap.addMarker(markerOptions);
        latLngList.add(latLng); markerList.add(marker);
      }
    });
  }
  public void dibujar(View view) {
    if (polyline != null) polyline.remove();
    PolylineOptions polylineOptions = new PolylineOptions()
        .addAll(latLngList).clickable(true);
    polyline = gMap.addPolyline(polylineOptions);
  }
  public void limpiar(View view) {
    if (polyline != null) polyline.remove();
    for (Marker marker : markerList) marker.remove();
    latLngList.clear(); markerList.clear();
  }
}
