package com.example.moneymaster;

import android.content.Intent;
import android.content.SharedPreferences;
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
                if(appLocked()){
                    Intent mainIntent = new Intent(SplashScreenActivity.this, LockScreenActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
                else {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        },splashTime);
    }


    //check the app is locked or unlocked
    public boolean appLocked()
    {
        SharedPreferences settings = getSharedPreferences("password",0);
        String password = settings.getString("hashPassword","error");

        if(password.equalsIgnoreCase("error") || password.equals("noPassword"))
        {
            return false;
        }
        return true;
    }
}
