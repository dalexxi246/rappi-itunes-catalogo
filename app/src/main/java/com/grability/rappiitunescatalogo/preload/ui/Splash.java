package com.grability.rappiitunescatalogo.preload.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.grability.rappiitunescatalogo.R;
import com.grability.rappiitunescatalogo.preload.ui.view.SplashView;

public class Splash extends AppCompatActivity implements SplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
