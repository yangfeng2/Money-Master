package com.example.moneymaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainView= this.getWindow().getDecorView();
        mainView.setBackgroundResource(R.color.WhiteP);
        /*-----------*/


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


