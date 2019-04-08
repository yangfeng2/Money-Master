package com.example.moneymaster;

        import android.app.Activity;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.text.DateFormat;
        import java.util.Calendar;
        import java.util.Date;

public class IncomeActivity extends Activity {

    //------------ variables -------------
    public static final String TABLE_NAME = "details";
    DBHelper myDBHelper;
    Button addButton;
    EditText amountText;
    EditText categoryText;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_activity);

        myDBHelper = new DBHelper(this);
        addButton = findViewById(R.id.addButton);
        amountText = findViewById(R.id.amountText);
        categoryText = findViewById(R.id.categoryText);

        //get the current date
        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData(currentDate,amountText.getText().toString(),"income",categoryText.getText().toString().toUpperCase());
            }
        });

    }

    //the function of insert data into database
    public  void insertData(String date, String amount, String type, String category)
    {
        ContentValues values = new ContentValues();
        values.put("date",date);
        int amountInt = Integer.parseInt(amount);
        values.put("amount",amountInt);
        values.put("type",type);
        values.put("category",category);

        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        db.insert(TABLE_NAME, null,values);
        startActivity(new Intent(this, HomeActivity.class));
    }
}
