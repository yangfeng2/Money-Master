package com.example.moneymaster;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Register1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1_activity);
    }

    public void GotoHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
