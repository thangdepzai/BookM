package com.samsung.bookm.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mybooks";
    public static final String BOOK_TABLE = "books";
    public static final String BOOK_ID = "id";
    public static final String BOOK_NAME = "name";
    public static final String BOOK_GENRE_ID = "genre_id";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_FILE_PATH = "file_path";
    public static final String BOOK_NUM_PAGE = "num_page";
    public static final String BOOK_LAST_RECENT_PAGE = "last_recent_page";
    public static final String BOOK_IMG_PATH = "img_src";
    public static final String BOOK_DESCRIPTION = "description";
    public static final String BOOK_TOTAL_READ_TIME = "total_read_time";
    public static final String BOOK_LAST_READ_TIME = "last_read_time";

    public static final String GENRE_TABLE = "genre";
    public static final String GENRE_ID = "id";
    public static final String GENRE_NAME = "name";


    private String CREATE_BOOK_TABLE =
            "CREATE TABLE " + BOOK_TABLE + " (" +
                    BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BOOK_NAME + " TEXT," +
                    BOOK_DESCRIPTION + " TEXT," +
                    BOOK_FILE_PATH + " TEXT," +
                    BOOK_IMG_PATH + " TEXT," +
                    BOOK_AUTHOR + " TEXT," +
                    BOOK_GENRE_ID + " INTEGER," +
                    BOOK_NUM_PAGE + " INTEGER," +
                    BOOK_LAST_RECENT_PAGE + " INTEGER," +
                    BOOK_LAST_READ_TIME + " INTEGER," +
                    BOOK_TOTAL_READ_TIME + " INTEGER," +
                    "FOREIGN KEY (" + BOOK_GENRE_ID + ") REFERENCES " + GENRE_TABLE + "(" + GENRE_ID + ") )";

    private String CREATE_GENRE_TABLE =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT) ", GENRE_TABLE, GENRE_ID, GENRE_NAME);


    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_GENRE_TABLE);
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);
        ContentValues contentValues = new ContentValues();
        contentValues.put(GENRE_NAME, "Action and Adventure");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Anthology");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Classic");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Comic and Graphic Novel");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Crime and Detective");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Drama");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Fan-Fiction");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Fantasy");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Horror");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Mystery");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Romance");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Science Fiction (Sci-Fi)");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
        contentValues.put(GENRE_NAME, "Suspense/Thriller");
        sqLiteDatabase.insert(GENRE_TABLE, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_SQL_BOOK = String.format("DROP TABLE if EXIST %s", BOOK_TABLE);
        String DROP_SQL_GENRE = String.format("DROP TABLE if EXIST %s", GENRE_TABLE);
        sqLiteDatabase.execSQL(DROP_SQL_BOOK);
        sqLiteDatabase.execSQL(DROP_SQL_GENRE);
    }
}
