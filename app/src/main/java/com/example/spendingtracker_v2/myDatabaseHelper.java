package com.example.spendingtracker_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class myDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "spendingRecordDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "spendingSummaryTable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "_description";
    private static final String COLUMN_VALUE = "_value";
    private static final String COLUMN_DATE = "_date";

    public myDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME +" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DESCRIPTION + " TEXT," + COLUMN_VALUE + " REAL, "
                + COLUMN_DATE + " TEXT);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addItemToDatabase(String description, double value, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_VALUE, value);
        cv.put(COLUMN_DATE, date);

        long reslut = db.insert(TABLE_NAME, null, cv);

        if(reslut == -1){
            Toast.makeText(context, "Insert to DB failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Data inserted to DB.", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db != null){
            db.rawQuery(query, null);
            }
        return cursor;
    }
}
