package com.thangtruong19.coffeemanagement.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 19/10/2018.
 */

public class OrderDbHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="order.db";

    private static final String SQL_CREATE_ENTRIES=
            "CREATE TABLE "+ OrderContract.OrderEntry.TABLE_NAME+"("
                    + OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + OrderContract.OrderEntry.COLUMN_COFFEE_NAME+ " TEXT, "
                    + OrderContract.OrderEntry.COLUMN_COFFEE_NUMBER+ " INTEGER, "
                    + OrderContract.OrderEntry.COLUMN_TEA_NAME+ " TEXT, "
                    + OrderContract.OrderEntry.COLUMN_TEA_NUMBER+ " INTEGER, "
                    + OrderContract.OrderEntry.COLUMN_MILK_NAME+ " TEXT, "
                    + OrderContract.OrderEntry.COLUMN_MILK_NUMBER+ " INTEGER, "
                    + OrderContract.OrderEntry.COLUMN_TABLE_ORDER+ " TEXT, "
                    + OrderContract.OrderEntry.COLUMN_TOTAL_MONEY+ " INTEGER );";

    private static final String SQL_DELETE_ENTRY=
            "DROP TABLE IF EXISTS"+ OrderContract.OrderEntry.TABLE_NAME;

    public OrderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRY);
        onCreate(sqLiteDatabase);
    }
}
