package com.example.moneymaster;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private static int splashTime = 4000; //splash screen time out is 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        /*
        Splash Screen Code by Sabith Pkc Mnr
        Source Code: https://github.com/SabithPkcMnr/SplashScreen
        */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        }, splashTime);
    }
}