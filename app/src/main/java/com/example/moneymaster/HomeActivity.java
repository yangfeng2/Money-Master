package com.example.moneymaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    //variables
    FloatingActionButton fab, fab1, fab2;
    boolean isFABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.text_date);
        textViewDate.setText(currentDate);

        //Hide the floating button
        fab2 = findViewById(R.id.fab2);
        fab1 = findViewById(R.id.fab1);




        fab2.hide();
        fab1.hide();
    }

    //show or hide the income button and expense button
    public void showFABMenu(View view){
        if(isFABOpen == false)
        {
            fab2.show();
            fab1.show();
            isFABOpen = true;
        }
        else
        {
            fab2.hide();
            fab1.hide();
            isFABOpen = false;
        }
    }

    //Go to the income screen
    public void showIncome(View view){
        startActivity(new Intent(this, IncomeActivity.class));
    }
}
