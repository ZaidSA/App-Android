package com.kovid19track.kovid19track.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.kovid19track.kovid19track.R;
import com.kovid19track.kovid19track.ui.onboarding.OnboardingActivity;
import com.kovid19track.kovid19track.utils.Constants;

public class Splash extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash.this, OnboardingActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, Constants.SPLASH_DISPLAY_LENGTH);
//
    }
}
