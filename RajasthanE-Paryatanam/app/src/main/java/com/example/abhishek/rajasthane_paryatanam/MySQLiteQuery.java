package com.example.abhishek.rajasthane_paryatanam;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ABHISHEK on 12/02/2017.
 */
public class MySQLiteQuery extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rajasthan_tourism";
    public static final int DATABASE_VERSION = 1;
    Context context;

    public MySQLiteQuery(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //String[] column = {"monument_id", "imageurl","name","description"};
    @Override
    public void onCreate(SQLiteDatabase db) {
        //-----created three table to store local infirmations

        String query = "CREATE TABLE IF NOT EXISTS e_monuments_table(monument_id int primary key, imageurl varchar(80), name varchar(80),description text);";
        String query1 = "CREATE TABLE IF NOT EXISTS h_monuments_table(monument_id int primary key, imageurl varchar(80), name varchar(80),description text);";
        String query2 = "CREATE TABLE IF NOT EXISTS j_monuments_table(monument_id int primary key, imageurl varchar(80), name varchar(80),description text);";
        try {
            db.execSQL(query);
            db.execSQL(query1);
            db.execSQL(query2);
            //Toast.makeText(context.getApplicationContext(),"table created", Toast.LENGTH_LONG).show();
        }
        catch (SQLException e) {
            //Toast.makeText(context.getApplicationContext(),"table not created "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exist e_monuments_table");
            db.execSQL("drop table if exist h_monuments_table");
            db.execSQL("drop table if exist j_monuments_table");
            onCreate(db);
        }
        catch (SQLException e) {
        }
    }

}

