package com.example.moneymaster;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //case the database
        myDBHelper = new DBHelper(this);
        deleteDatabase();

    }

    // clean the database
    public void deleteDatabase()
    {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        myDBHelper.onUpgrade(db,1,2);
    }

    //Go to the Login Screen
    public void GotoLoginActivity(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    //Go to the Register Screen
    public void GotoRegisterActivity(View view) {
        startActivity(new Intent(this,Register1Activity.class));
    }

}


