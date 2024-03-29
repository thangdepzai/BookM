package com.samsung.bookm.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase {
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    private AppDatabase(Context context) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (AppDatabase.class) {
                if(instance == null) {
                    instance = new AppDatabase(context);
                }
            }
        }
        return instance;
    }

    public void insertBook(Book book) {
        SQLiteDatabase sqLiteDatabase = instance.mySQLiteOpenHelper.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteOpenHelper.BOOK_NAME, book.getName());
        contentValues.put(MySQLiteOpenHelper.BOOK_GENRE_ID, book.getGenreId());
        contentValues.put(MySQLiteOpenHelper.BOOK_AUTHOR, book.getAuthor());
        contentValues.put(MySQLiteOpenHelper.BOOK_NUM_PAGE, book.getNumPage());
        contentValues.put(MySQLiteOpenHelper.BOOK_LAST_RECENT_PAGE, book.getLastRecentPage());
        contentValues.put(MySQLiteOpenHelper.BOOK_IMG_PATH, book.getImgPath());
        contentValues.put(MySQLiteOpenHelper.BOOK_FILE_PATH, book.getBookPath());

        sqLiteDatabase.insert(MySQLiteOpenHelper.BOOK_TABLE, null, contentValues);
    }


    public ArrayList<String> getAllGenre() {
        ArrayList<String> listGenre = new ArrayList<>();

        SQLiteDatabase db = instance.mySQLiteOpenHelper.getReadableDatabase();

        String[] projection = {MySQLiteOpenHelper.GENRE_ID, MySQLiteOpenHelper.GENRE_NAME };
        Cursor cursor = db.query(MySQLiteOpenHelper.GENRE_TABLE, projection, null, null, null, null, null );

        while (cursor.moveToNext()) {
            listGenre.add(cursor.getString(1));
        }
        cursor.close();

        return listGenre;
    };
//
//    Book getBookById() {};


    public ArrayList<Book> getAllBook() {
        ArrayList<Book>  retArray = new ArrayList<>();
        SQLiteDatabase db = instance.mySQLiteOpenHelper.getReadableDatabase();

        String[] projection = {MySQLiteOpenHelper.BOOK_ID, MySQLiteOpenHelper.BOOK_NAME, MySQLiteOpenHelper.BOOK_GENRE_ID, MySQLiteOpenHelper.BOOK_IMG_PATH,
                MySQLiteOpenHelper.BOOK_FILE_PATH, MySQLiteOpenHelper.BOOK_LAST_RECENT_PAGE, MySQLiteOpenHelper.BOOK_NUM_PAGE,
                MySQLiteOpenHelper.BOOK_TOTAL_READ_TIME, MySQLiteOpenHelper.BOOK_LAST_READ_TIME };

        Cursor cursor = db.query(MySQLiteOpenHelper.BOOK_TABLE, projection, null , null, null, null, null );

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setGenreId(cursor.getInt(2));
            book.setImgPath(cursor.getString(3));
            book.setBookPath(cursor.getString(4));
            book.setLastRecentPage(cursor.getInt(5));
            book.setNumPage(cursor.getInt(6));
            book.setTotalReadTime(cursor.getInt(7));
            book.setLastReadTime(cursor.getInt(8));

            retArray.add(book);
        }

        cursor.close();
        return retArray;
    }
}
