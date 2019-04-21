package com.example.moneymaster;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {

    public static final String TABLE_NAME = "details";
    DBHelper myDBHelper;
    String[] allColumns = new String[] {"date", "amount", "type", "category"};
    List<String> listDate = new ArrayList<>();
    List<Integer> listAmount = new ArrayList<>();
    List<String> listType = new ArrayList<>();
    List<String> listCategory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        //cast the database
        myDBHelper = new DBHelper(this);

        //retrieve all the data from database and save into list
        getAllDetails();

        //show the total of income and expense
        showTotal();

        //show the biggest income and expense
        showBiggestIncExp();
    }

    // ---------------- Method of find biggest expense and income ------------------------
    public void showBiggestIncExp()
    {
        TextView biggestIncomeCategory = findViewById(R.id.incomeCategory);
        TextView biggestIncomeAmount = findViewById(R.id.incomeAmount);
        TextView biggestExpenseCategory = findViewById(R.id.expenseCategory);
        TextView biggestExpenseAmount = findViewById(R.id.expenseAmount);
        //initialize a element
        int maxIncome = 0;
        int maxExpense = 0;

        for(int i=0;i<listAmount.size();i++)
        {
            //find the income
            if(listType.get(i).equals("income"))
            {
                int maxIndex = 0;
                //compare every element with current element
                if (listAmount.get(i) > maxIncome)
                {
                    maxIncome = listAmount.get(i);
                    //save the index of maximum amount
                    maxIndex = i;
                }
                //show the the amount and category of biggest income
                biggestIncomeCategory.setText(listCategory.get(maxIndex));
                biggestIncomeAmount.setText("AU$ "+String.valueOf(maxIncome));
            }
            //find the expense
            else
            {
                int maxIndex = 0;
                //compare every element with current element
                if (listAmount.get(i) > maxExpense)
                {
                    maxExpense = listAmount.get(i);
                    //save the index of maximum amount
                    maxIndex = i;
                }
                //show the the amount and category of biggest expense
                biggestExpenseCategory.setText(listCategory.get(maxIndex));
                biggestExpenseAmount.setText("AU$ "+String.valueOf(maxExpense));
            }
        }



    }

    /*
        ----- Show The Total of Income and Expense -------

        1.Get the amount of income and expense from the list.
        2.Add them together.
        3.Show the total to user.
        4.Calculate the net income.


     */
    public void showTotal()
    {
        TextView sumIncome = findViewById(R.id.totalIncome);
        TextView sumExpense = findViewById(R.id.totalExpense);
        TextView allTotal = findViewById(R.id.allTotal);
        int totalIncome = 0;
        int totalExpense = 0;
        int sumTotal= 0;

        for(int i=0;i<listAmount.size();i++)
        {
            if(listType.get(i).equals("income"))
            {
                totalIncome += listAmount.get(i);
            }
            else
            {
                totalExpense += listAmount.get(i);
            }
        }

        sumIncome.setText("AU$ "+String.valueOf(totalIncome));
        sumExpense.setText("AU$ "+String.valueOf(totalExpense));

        //check the net income with income minus expense
        sumTotal = totalIncome - totalExpense;
        if (sumTotal > 0) {
            allTotal.setTextColor(Color.parseColor("#2DB652"));
            allTotal.setText("+AU$ "+String.valueOf(sumTotal));
        }
        else if (sumTotal == 0)
        {
            return;
        }
        else {
            allTotal.setTextColor(Color.parseColor("#BF1333"));
            allTotal.setText("-AU$ "+ String.valueOf(-sumTotal));
        }
    }

    // retrieve the date, category, type, category and amount from the database
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
}
