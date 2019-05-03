package com.example.moneymaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    //--------- variables ----------
    DBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        myDBHelper = new DBHelper(this);

        //check the theme seleceted
        checkTheme();
    }

    //check the theme is dark or light
    public void checkTheme()
    {
        View view = findViewById(R.id.settingActivity);

        String currentTheme = currentTheme();
        //dark theme found
        if (currentTheme.equals("darkTheme")){
            view.setBackgroundColor(Color.parseColor("#000000"));
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

    //go to the log screen
    public void showLogScreen(View view) {startActivity(new Intent(this,LogActivity.class));}

    //go to the lock screen
    public void showLockScreen(View view) {startActivity(new Intent(this,LockActivity.class));}

    //go to about screen
    public void showAboutScreen(View view) {startActivity(new Intent(this,AboutActivity.class));}

    //delete the password and unlock the app
    public void deletePassword(View view)
    {
        String password = "noPassword";

        SharedPreferences settings = getSharedPreferences("password", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("hashPassword",password);

        editor.apply();

        Toast.makeText(getApplicationContext(),"Unlock Successful !",Toast.LENGTH_LONG).show();
        startActivity(new Intent(SettingActivity.this,HomeActivity.class));
    }

    // delete of the data of the database
    public void deleteDatabase(View view)
    {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        myDBHelper.onUpgrade(db,1,2);
        startActivity(new Intent(SettingActivity.this, HomeActivity.class));
    }

    // change the theme to dark theme
    public void darkTheme (View view)
    {
        String theme = "darkTheme";

        SharedPreferences settings = getSharedPreferences("themes",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("typeTheme",theme);
        editor.apply();

        startActivity(new Intent(this,HomeActivity.class));

        Toast.makeText(getApplicationContext(),"Changed to the Dark Theme. ",Toast.LENGTH_LONG).show();
    }

    //change the theme to light theme (default theme)
    public void lightTheme (View view)
    {
        String theme = "lightTheme";

        SharedPreferences settings = getSharedPreferences("themes",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("typeTheme",theme);
        editor.apply();

        startActivity(new Intent(this,HomeActivity.class));
        Toast.makeText(getApplicationContext(),"Changed to the Light Theme. ",Toast.LENGTH_LONG).show();
    }

}
