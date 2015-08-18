package com.myprey.savepanda;

/**
 * Created by Ramanan on 7/3/2015.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    Context cont;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "savepandas.db";
    public static final String TABLE_PRODUCTS = "scores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE="score";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        cont=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SCORE + " INTEGER DEFAULT -1 " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addName(Name name){
        int score=-1;
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name.get_name());
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)).equals(name.get_name())) {
                score=c.getInt(c.getColumnIndex(COLUMN_SCORE));
                break;
            }
            c.moveToNext();
        }
        if(score==-1) {
            db.insert(TABLE_PRODUCTS, null, values);
            updateScore(name.get_name(),name.get_score());
        }
        else if(score<name.get_score())updateScore(name.get_name(),name.get_score());
        db.close();
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1 ORDER BY "+COLUMN_SCORE+" DESC;";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_NAME))+":";
                dbString+=c.getInt(c.getColumnIndex(COLUMN_SCORE));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public void updateScore(String name,int score){
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_PRODUCTS + "\nSET " + COLUMN_SCORE + "=\"" + score + "\"" + "\nWHERE " +
                COLUMN_NAME + "=\"" + name + "\";";
        db.execSQL(query);
        db.close();
    }

    public void Upgrade(){
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db,1,1);
    }
}
