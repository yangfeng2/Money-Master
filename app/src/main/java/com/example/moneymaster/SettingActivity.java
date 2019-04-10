package com.example.moneymaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
    }

    //go to the lock screen
    public void showLockScreen(View view) {startActivity(new Intent(this,LockActivity.class));}

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
}
