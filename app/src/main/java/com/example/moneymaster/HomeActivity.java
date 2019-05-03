package com.example.moneymaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

public class HomeActivity extends AppCompatActivity {

    //----------------- variables ----------------------
    FloatingActionButton fab1, fab2, fab3;
    boolean isFABOpen = false;
    public static final String TABLE_NAME = "details";
    DBHelper myDBHelper;
    ListView listView;
    String[] allColumns = new String[] {"date", "amount", "type", "category"};
    List<String> listDate = new ArrayList<>();
    List<Integer> listAmount = new ArrayList<>();
    List<String> listType = new ArrayList<>();
    List<String> listCategory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        //find the floating buttons
        fab3 = findViewById(R.id.fab3);
        fab2 = findViewById(R.id.fab2);
        fab1 = findViewById(R.id.fab1);
        //hide the floating buttons
        fab3.hide();
        fab2.hide();
        fab1.hide();
        //cast the database
        myDBHelper = new DBHelper(this);
        listView = findViewById(R.id.listView);
        displayDetails();
        showTotal();
        //check the theme selected
        checkTheme();
    }

    //check the theme is dark or light
    public void checkTheme()
    {
        View view = findViewById(R.id.homeActivity);
        TextView overviewText = findViewById(R.id.overviewText);

        String currentTheme = currentTheme();
        //dark theme found
        if (currentTheme.equals("darkTheme")){
            view.setBackgroundColor(Color.parseColor("#000000"));
            overviewText.setTextColor(Color.parseColor("#ffffff"));
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

    /*
        ----- Show The Total of Income and Expense -------

        1.Get the amount of income and expense from the list.
        2.Add them together.
        3.Show the total to user.


     */
    public void showTotal() {
        TextView sumIncome = findViewById(R.id.totalIncome);
        TextView sumExpense = findViewById(R.id.totalExpense);
        int totalIncome = 0;
        int totalExpense = 0;
        int sumTotal = 0;

        for (int i = 0; i < listAmount.size(); i++) {
            if (listType.get(i).equals("income")) {
                totalIncome += listAmount.get(i);
            } else {
                totalExpense += listAmount.get(i);
            }
        }

        sumIncome.setText("AU$ " + String.valueOf(totalIncome));
        sumExpense.setText("AU$ " + String.valueOf(totalExpense));
    }

    // getting the date, category, type, category and amount from the database
    public void getAllDetails()
    {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, allColumns, null,null,null,null,null);

        //keep processing until the next entry is empty
        while (cursor.moveToNext())
        {
            listDate.add(cursor.getString(cursor.getColumnIndex("date")));
            listAmount.add(cursor.getInt(cursor.getColumnIndex("amount")));
            listType.add(cursor.getString(cursor.getColumnIndex("type")));
            listCategory.add(cursor.getString(cursor.getColumnIndex("category")));
        }
    }

    // display the date, category and amount
    public void displayDetails()
    {
        getAllDetails();
        CustomAdapter adapter = new CustomAdapter();
        listView.setAdapter(adapter);
    }

    // a custom adapter to show the details with details_layout file
    private class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return listDate.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            View view1 = getLayoutInflater().inflate(R.layout.details_layout,null);

            TextView dateView = view1.findViewById(R.id.dateText);
            TextView amountView = view1.findViewById(R.id.amountText);
            TextView categoryView = view1.findViewById(R.id.categoryText);

            //show the data from database
            dateView.setText(listDate.get(i));
            amountView.setText("AU$ "+listAmount.get(i));
            categoryView.setText(listCategory.get(i));

            //check the type of theme
            if (currentTheme().equals("darkTheme")){
                dateView.setTextColor(Color.parseColor("#ffffff"));
                categoryView.setTextColor(Color.parseColor("#ffffff"));
            }


            //change the color of amount
            String detailsType = listType.get(i);
            if (detailsType.equals("income"))
            {
                amountView.setTextColor(Color.parseColor("#2DB652"));
            }
            else
            {
                amountView.setTextColor(Color.parseColor("#BF1333"));
            }




            return view1;
        }
    }

    //show or hide the income button and expense button
    public void showFABMenu(View view){
        if(isFABOpen == false)
        {
            fab3.show();
            fab2.show();
            fab1.show();
            isFABOpen = true;
        }
        else
        {
            fab3.hide();
            fab2.hide();
            fab1.hide();
            isFABOpen = false;
        }
    }

    //Go to the overview screen
    public void showOverview(View view) {startActivity(new Intent(this,OverviewActivity.class));}

    //Go to the income screen
    public void showIncome(View view){
        startActivity(new Intent(this, IncomeActivity.class));
    }

    //Go to the expense screen
    public void showExpense(View view){
        startActivity(new Intent(this, ExpenseActivity.class));
    }

    //Go to the setting screen
    public void showSetting(View view) {startActivity(new Intent(this,SettingActivity.class));}
}
