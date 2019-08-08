package com.samsung.bookm.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.samsung.bookm.Model.SearchHistory;

import java.util.ArrayList;
import java.util.List;

public class HistoryDatabase extends SQLiteOpenHelper {
    private static HistoryDatabase historyDatabase =null;
    private static final String TABLE_NAME ="history";
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "HistoryDatabase";
    private static final String KEY_ID = "id";
    private static final String KEY_WORD = "word";
    private static final String KEY_TIME = "time";

    public HistoryDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  static HistoryDatabase getInstance(Context context){
        if(historyDatabase==null){
            historyDatabase = new HistoryDatabase(context);
        }
        return  historyDatabase;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_NAME +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_WORD + " TEXT,"
                + KEY_TIME + " INTEGER )";
        sqLiteDatabase.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
    // Getting all Reminders
    public List<SearchHistory> getAllHistory(){
        List<SearchHistory> historyList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                SearchHistory history = new SearchHistory();
                history.setId(Integer.parseInt(cursor.getString(0)));
                history.setText(cursor.getString(1));
                history.setTime(cursor.getLong(2));

                // Adding Reminders to list
                historyList.add(history);
            } while (cursor.moveToNext());
        }
        return historyList;
    }
    // Adding new Reminder
    public int addHistory(SearchHistory history){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_WORD , history.getText());
        values.put(KEY_TIME , history.getTime());
        // Inserting Row
        int id =(int)  db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void deleteHistory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = KEY_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(TABLE_NAME, whereClause, whereArgs);
    }

}
