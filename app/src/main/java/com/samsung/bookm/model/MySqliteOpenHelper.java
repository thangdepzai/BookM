package com.samsung.bookm.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mybooks";
    public static final String BOOK_TABLE = "books";
    public static final String BOOK_ID = "id";
    public static final String BOOK_NAME = "name";
    public static final String BOOK_GENRE_ID = "genre_id";
    public static final String BOOK_AUTHOR_ID = "author_id";
    public static final String BOOK_FILE_PATH = "file_path";
    public static final String BOOK_NUM_PAGE = "num_page";
    public static final String BOOK_LAST_RECENT_PAGE = "last_recent_page";
    public static final String BOOK_IMG_PATH = "img_src";
    public static final String BOOK_DESCRIPTION = "description";

    public static final String AUTHOR_TABLE = "author";
    public static final String AUTHOR_ID = "id";
    public static final String AUTHOR_NAME = "name";

    public static final String GENRE_TABLE = "genre";
    public static final String GENRE_ID = "id";
    public static final String GENRE_NAME = "name";


    private String CREATE_BOOK_TABLE =
            String.format("CREATE TABLE %s " +
                            "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER , %s INTEGER, %s INTEGER, %s INTEGER" +
                            "FOREIGN KEY (%s) REFERENCES %s(%s) " +
                            "FOREIGN KEY (%s) REFERENCES %s(%s));",
                    BOOK_TABLE, BOOK_ID, BOOK_NAME, BOOK_DESCRIPTION, BOOK_FILE_PATH, BOOK_IMG_PATH,
                    BOOK_AUTHOR_ID, BOOK_GENRE_ID, BOOK_NUM_PAGE, BOOK_LAST_RECENT_PAGE,
                    BOOK_AUTHOR_ID, AUTHOR_TABLE, AUTHOR_ID, BOOK_GENRE_ID, GENRE_TABLE, GENRE_ID );

    private String CREATE_AUTHOR_TABLE =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT ", AUTHOR_TABLE, AUTHOR_ID, AUTHOR_NAME);
    private String CREATE_GENRE_TABLE =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT ", GENRE_TABLE, GENRE_ID, GENRE_NAME);

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_GENRE_TABLE);
        sqLiteDatabase.execSQL(CREATE_AUTHOR_TABLE);
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_SQL = String.format("DROP TABLE if EXIST %s", BOOK_TABLE);
        sqLiteDatabase.execSQL(DROP_SQL);
    }
}
