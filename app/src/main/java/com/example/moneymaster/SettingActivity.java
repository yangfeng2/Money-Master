package com.example.moneymaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
    }

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

}
