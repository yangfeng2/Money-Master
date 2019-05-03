package com.example.moneymaster;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LogActivity extends AppCompatActivity {

    //-------------------- variables -----------------------
    ListView exceptionList;
    List<String> values = new ArrayList<>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        exceptionList = findViewById(R.id.exceptionList);
        textView = findViewById(R.id.exceptionText);

        getMessage();
        displayMessage();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //retrieve the message from the exception file
    public void getMessage()
    {
        SharedPreferences settings = getSharedPreferences("LogFile",0);
        Set<String> logSet = settings.getStringSet("LogMessage",null);
        if(logSet != null)
        {
            values.addAll(logSet);
        }
    }

    //show the message with an array adapter which use simple_list_item_1 layout
    public void displayMessage()
    {
        if(values != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, values);
            exceptionList.setAdapter(adapter);
        }
    }

}
