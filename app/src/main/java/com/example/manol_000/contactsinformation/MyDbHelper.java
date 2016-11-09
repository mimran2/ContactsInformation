package com.example.manol_000.contactsinformation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by white on 10/9/2015.
 */
public class MyDbHelper extends SQLiteOpenHelper {
   private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "people";
    public static final String COL_NAME = "Name";
    public static final String COL_INFO = "Info";
    private static final String STRING_CREATE =
            "CREATE TABLE " +TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, " + COL_INFO + " TEXT);";
    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STRING_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


}
