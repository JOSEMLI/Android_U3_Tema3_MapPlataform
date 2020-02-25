package com.example.android_u3_tema3_mapplataform;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.google.maps.android.PolyUtil;

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

  public void dibujaRuta(View view) {
    Log.i("miposicionsize","size:"+latLngList.size());
    if(latLngList.size()>1) {
      Log.i("miposicion1", "lat1:" + latLngList.get(0).latitude + " lon1:" + latLngList.get(0).longitude);
      Log.i("miposicion2", "lat2:" + latLngList.get(1).latitude + " lon2:" + latLngList.get(1).longitude);
    }
  }

  public void Decode(View view) {
    DecodePolyline("bj~lBh}xkLYBWNKX?Ro@e@u@w@yBsC}CaEuAyBwA{Be@@aBF_@Dq@Ju@Ps@TiEiIgDcGwC|By@h@");
  }
  Polyline polyline2 = null;
  public void DecodePolyline(String encodepolyline){
    List<LatLng> list = PolyUtil.decode(encodepolyline);
    if (polyline2 != null) polyline2.remove();
    PolylineOptions polylineOptions = new PolylineOptions()
        .addAll(list).clickable(true);
    polyline2 = gMap.addPolyline(polylineOptions);
    polyline2.setColor(Color.BLUE);
  }
}
