package com.example.moneymaster;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //check the current theme
        checkTheme();
    }

    //check the theme is dark or light
    public void checkTheme()
    {
        View view = findViewById(R.id.aboutActivity);
        TextView logoName = findViewById(R.id.moneyMaster);
        TextView licenseText = findViewById(R.id.licenseText);

        String currentTheme = currentTheme();
        //dark theme found
        if (currentTheme.equals("darkTheme")){
            view.setBackgroundColor(Color.parseColor("#000000"));
            logoName.setTextColor(Color.parseColor("#ffffff"));
            licenseText.setTextColor(Color.parseColor("#ffffff"));
        }
        //light theme found
        else {
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    //retrieve the data from typeTheme file
    public String currentTheme()
    {
        SharedPreferences settings = getSharedPreferences("themes",0);
        String currentTheme = settings.getString("typeTheme","error");
        return  currentTheme;
    }
}
