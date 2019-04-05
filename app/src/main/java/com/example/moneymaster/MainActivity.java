package com.example.moneymaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        view= this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.WhiteP);
        /*-----------*/


    }

    public void createaccount(View view)
    {
        String button_text;
        button_text = ((Button) view).getText().toString();
        if (button_text.equals("Create account" ))
        {
            Intent intent = new Intent (this,Register1Activity.class);
            startActivity(intent);
        }

        else if (button_text.equals("Login"))
        {
            Intent intent = new Intent (this,LoginActivity.class);
            startActivity(intent);
        }

    }
    public void login(View view)
    {
        String button_text;
        button_text = ((Button) view).getText().toString();
        if (button_text.equals("Login"))
        {
            Intent intent = new Intent (this,LoginActivity.class);
            startActivity(intent);
        }
    }

    /*--Login button Can not turn into Login activity page--*/
   /*-----
    public void login(View view)
    {

        String button_text;
        button_text = ((Button) view).getText().toString();
        if (button_text.equals("Login" ))
        {
            Intent intent = new Intent (this,LoginActivity.class);
            startActivity(intent);
        }
        /*-----
        else if (button_text.equals("Login"))
        {
            Intent intent = new Intent (this,LoginActivity.class);
            startActivity(intent);
        }
        ------*/
}


