package com.samsung.bookm.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.samsung.bookm.Model.Reminder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class ReminderDatabase extends SQLiteOpenHelper {
    static ReminderDatabase instance = null;
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ReminderDatabase";

    // Table name
    private static final String TABLE_REMINDERS = "ReminderTable";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_REPEAT = "repeat";
    private static final String KEY_REPEAT_NO = "repeat_no";
    private static final String KEY_REPEAT_TYPE = "repeat_type";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_BOOK_ID = "bookid";

    private ReminderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  static  ReminderDatabase getInstance(Context context){
        if(instance==null){
            instance = new ReminderDatabase(context);
        }
        return instance;

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REMINDERS_TABLE = "CREATE TABLE " + TABLE_REMINDERS +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_TITLE + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " INTEGER,"
                + KEY_REPEAT + " BOOLEAN,"
                + KEY_REPEAT_NO + " INTEGER,"
                + KEY_REPEAT_TYPE + " TEXT,"
                + KEY_ACTIVE + " BOOLEAN, "
                + KEY_BOOK_ID+ " INTEGER "+ ")";
        db.execSQL(CREATE_REMINDERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDERS);

        // Create tables again
        onCreate(db);
    }

    // Adding new Reminder
    public int addReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TITLE , reminder.getmTitle());
        values.put(KEY_DATE , reminder.getmDate());
        values.put(KEY_TIME , reminder.getmTime());
        values.put(KEY_REPEAT , reminder.getmRepeat());
        values.put(KEY_REPEAT_NO , reminder.getmRepeatNo());
        values.put(KEY_REPEAT_TYPE, reminder.getmRepeatType());
        values.put(KEY_ACTIVE, reminder.getmActive());
        values.put(KEY_BOOK_ID, reminder.getmBookId());

        // Inserting Row
        int id =(int)  db.insert(TABLE_REMINDERS, null, values);
        db.close();
        return id;

    }

    // Getting single Reminder
    public Reminder getReminder(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REMINDERS, new String[]
                        {
                                KEY_ID,
                                KEY_TITLE,
                                KEY_DATE,
                                KEY_TIME,
                                KEY_REPEAT,
                                KEY_REPEAT_NO,
                                KEY_REPEAT_TYPE,
                                KEY_ACTIVE,
                                KEY_BOOK_ID
                        }, KEY_ID + "=?",

                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Reminder reminder = new Reminder(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7), Integer.parseInt(cursor.getString(8)));

        return reminder;
    }


    // Getting all Reminders
    public List<Reminder> getAllReminders(){
        List<Reminder> reminderList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + TABLE_REMINDERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Reminder reminder = new Reminder();
                reminder.setmID(Integer.parseInt(cursor.getString(0)));
                reminder.setmTitle(cursor.getString(1));
                reminder.setmDate(cursor.getString(2));
                reminder.setmTime(cursor.getString(3));
                reminder.setmRepeat(cursor.getString(4));
                reminder.setmRepeatNo(cursor.getString(5));
                reminder.setmRepeatType(cursor.getString(6));
                reminder.setmActive(cursor.getString(7));
                reminder.setmBookId(cursor.getInt(8));

                // Adding Reminders to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
        return reminderList;
    }

    // Getting Reminders Count
    public int getRemindersCount(){
        String countQuery = "SELECT * FROM " + TABLE_REMINDERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single Reminder
    public int updateReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE , reminder.getmTitle());
        values.put(KEY_DATE , reminder.getmDate());
        values.put(KEY_TIME , reminder.getmTime());
        values.put(KEY_REPEAT , reminder.getmRepeat());
        values.put(KEY_REPEAT_NO , reminder.getmRepeatNo());
        values.put(KEY_REPEAT_TYPE, reminder.getmRepeatType());
        values.put(KEY_ACTIVE, reminder.getmActive());
        values.put(KEY_BOOK_ID, reminder.getmBookId());

        // Updating row
        return db.update(TABLE_REMINDERS, values, KEY_ID + "=?",
                new String[]{String.valueOf(reminder.getmID())});
    }

    // Deleting single Reminder
    public void deleteReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REMINDERS, KEY_ID + "=?",
                new String[]{String.valueOf(reminder.getmID())});
        db.close();
    }
    public List<Reminder> getAllRemindersInDay(String date){
        List<Reminder> reminderList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + TABLE_REMINDERS +" WHERE "+ KEY_DATE + " = '"+date +"' ORDER BY "+ KEY_TIME +" ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Reminder reminder = new Reminder();
                reminder.setmID(Integer.parseInt(cursor.getString(0)));
                reminder.setmTitle(cursor.getString(1));
                reminder.setmDate(cursor.getString(2));
                reminder.setmTime(cursor.getString(3));
                reminder.setmRepeat(cursor.getString(4));
                reminder.setmRepeatNo(cursor.getString(5));
                reminder.setmRepeatType(cursor.getString(6));
                reminder.setmActive(cursor.getString(7));
                reminder.setmBookId(Integer.parseInt(cursor.getString(8)));

                // Adding Reminders to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return reminderList;
    }


    public List<Reminder> getToday(String date){
        List<Reminder> reminderList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Calendar mCalendar= Calendar.getInstance();
        int mYear = mCalendar.get(Calendar.YEAR);
        int mMonth = mCalendar.get(Calendar.MONTH) + 1;
        int mDay = mCalendar.get(Calendar.DATE);

        String selectQuery = "SELECT * FROM " + TABLE_REMINDERS
                +" WHERE "+ KEY_DATE + " = '"+date
                +"' OR ( " + KEY_DATE + " < '"+date + "' AND "+  KEY_REPEAT +" = 'true' ) "
                +" ORDER BY "+ KEY_TIME +" ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Reminder reminder = new Reminder();
                reminder.setmID(Integer.parseInt(cursor.getString(0)));
                reminder.setmTitle(cursor.getString(1));
                reminder.setmDate(cursor.getString(2));
                reminder.setmTime(cursor.getString(3));
                reminder.setmRepeat(cursor.getString(4));
                reminder.setmRepeatNo(cursor.getString(5));
                reminder.setmRepeatType(cursor.getString(6));
                reminder.setmActive(cursor.getString(7));
                reminder.setmBookId(Integer.parseInt(cursor.getString(8)));
                // Adding Reminders to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return reminderList;

    }
    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM "+TABLE_REMINDERS;
        db.execSQL(sql);
        db.close();

    }

}

