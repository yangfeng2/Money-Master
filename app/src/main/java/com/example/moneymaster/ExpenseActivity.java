package com.example.moneymaster;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class ExpenseActivity extends Activity {

    //---------------- variables ---------------
    public static final String TABLE_NAME = "details";
    DBHelper myDBHelper;
    Button addButton;
    EditText amountText;
    EditText categoryText;
    String currentDate;
    ArrayList<String> logMsg = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_activity);

        myDBHelper = new DBHelper(this);
        addButton = findViewById(R.id.addButton);
        amountText = findViewById(R.id.amountText);
        categoryText = findViewById(R.id.categoryText);

        //get the current date
        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());


        //the data will be added to the database when user click add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData(currentDate,amountText.getText().toString(),"expense",categoryText.getText().toString().toUpperCase());
            }
        });

        //check the theme selected
        checkTheme();

    }

    //check the theme is dark or light
    public void checkTheme()
    {
        View view = findViewById(R.id.expenseActivity);
        TextView amountText = findViewById(R.id.amountViewText);
        TextView categoryText = findViewById(R.id.categoryViewText);
        EditText editCategory = findViewById(R.id.categoryText);

        String currentTheme = currentTheme();
        //dark theme found
        if (currentTheme.equals("darkTheme")){
            view.setBackgroundColor(Color.parseColor("#000000"));
            amountText.setTextColor(Color.parseColor("#ffffff"));
            categoryText.setTextColor(Color.parseColor("#ffffff"));
            editCategory.setTextColor(Color.parseColor("#ffffff"));
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

    /*--------------- the function of insert data into database ----------------------
        1.Check the category is correct or incorrect
        -Insert into SQL database if it is correct
        -Show exception in log page if it is incorrect
     */
    public  void insertData(String date, String amount, String type, String category)
    {
        boolean checkText = true;

        ContentValues values = new ContentValues();
        values.put("date",date);
        //int amountInt = Integer.parseInt(amount);
        if (amount.equals(""))
        {
            //display a message to user
            Toast.makeText(getApplicationContext(),"Please enter a correct amount ! ",Toast.LENGTH_LONG).show();

            //send log details to the log page with sharedpreference
            Date currentTime = Calendar.getInstance().getTime();
            String value = "Amount is incorrect. Occurred at "+currentTime;
            logMsg.add(value);

            checkText = false;
        }
        else {
            values.put("amount",amount);
        }
        values.put("type",type);
        if(stringContainsNumber(category))
        {
            //display a message to user
            Toast.makeText(getApplicationContext(),"Please enter a correct category ! ",Toast.LENGTH_LONG).show();

            //send log details to the log page with sharedpreference
            Date currentTime = Calendar.getInstance().getTime();
            String value = category +" is a wrong category format. Occurred at "+currentTime;
            logMsg.add(value);

            checkText = false;
        }
        else {
            values.put("category",category);
        }

        if (checkText == true)
        {
            SQLiteDatabase db = myDBHelper.getWritableDatabase();
            db.insert(TABLE_NAME, null,values);
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    /*   ------ Method of check a string ------------

        1. Return true when the string contains number.
        2. Return false when the string doesn't contains a number
    */
    public boolean stringContainsNumber(String s)
    {
        return Pattern.compile("[0-9]").matcher(s).find();
    }
}
