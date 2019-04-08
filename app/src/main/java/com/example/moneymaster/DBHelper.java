package com.example.moneymaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //---------SQL Database for saving the income and expense----------------
    public  static  final String TABLE_NAME = "details";
    public  static  final String COLUMN_DATE = "date";
    public  static  final String COLUMN_AMOUNT = "amount";
    public  static  final String COLUMN_TYPE = "type";
    public  static  final String COLUMN_CATEGORY = "category";
    private  static final String DATABASE_NAME = "details.db";
    private  static final int DATABASE_VERSION = 1;

    private  static  final  String DATABASE_CREATE = "create table " + TABLE_NAME +
            "(" + COLUMN_DATE + " text not null," + COLUMN_AMOUNT + " integer not null, "
            + COLUMN_TYPE + " text not null, " + COLUMN_CATEGORY + " text not null); ";

    Context context;

    public  DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
