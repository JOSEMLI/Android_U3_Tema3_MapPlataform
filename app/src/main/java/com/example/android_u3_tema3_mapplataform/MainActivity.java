package com.example.android_u3_tema3_mapplataform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void rutadospunto(View view) {
startActivity(new Intent(this,RutaEntreDosPuntos.class));
  }

  public void geocoding(View view) {
    startActivity(new Intent(this,MiGeocode.class));
  }
}
