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

    void insertBook(Book book) {
        SQLiteDatabase sqLiteDatabase = instance.mySQLiteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteOpenHelper.BOOK_NAME, book.getName());
        contentValues.put(MySQLiteOpenHelper.BOOK_GENRE_ID, book.getGenre());
        contentValues.put(MySQLiteOpenHelper.BOOK_AUTHOR_ID, book.getAuthor());
        contentValues.put(MySQLiteOpenHelper.BOOK_NUM_PAGE, book.getNumPage());
        contentValues.put(MySQLiteOpenHelper.BOOK_LAST_RECENT_PAGE, book.getLastRecentPage());
        contentValues.put(MySQLiteOpenHelper.BOOK_IMG_PATH, book.getImgPath());
        contentValues.put(MySQLiteOpenHelper.BOOK_FILE_PATH, book.getBookPath());

        sqLiteDatabase.insert(MySQLiteOpenHelper.BOOK_TABLE, null, contentValues);
    }

    List<String> getAllGenre() {

    };

    Book getBookById() {};


    ArrayList<Book> getAllBook() {
        ArrayList<Book>  retArray = new ArrayList<>();
        SQLiteDatabase db = instance.mySQLiteOpenHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + MySQLiteOpenHelper.BOOK_TABLE + "," + MySQLiteOpenHelper.AUTHOR_TABLE + "," + MySQLiteOpenHelper.GENRE_TABLE + " " +
                "WHERE " + MySQLiteOpenHelper.GENRE_TABLE + ".id = " + MySQLiteOpenHelper.BOOK_TABLE + "." + MySQLiteOpenHelper.BOOK_GENRE_ID + "  AND " +
                MySQLiteOpenHelper.AUTHOR_ID + ".id = " + MySQLiteOpenHelper.BOOK_TABLE + "." + MySQLiteOpenHelper.BOOK_AUTHOR_ID + ';';

        //String[] projection = {MySQLiteOpenHelper.BOOK_ID, MySQLiteOpenHelper.BOOK_NAME, MySQLiteOpenHelper.BOOK_GENRE, MySQLiteOpenHelper.BOOK_IMG_PATH, MySQLiteOpenHelper.BOOK_FILE_PATH, MySQLiteOpenHelper.BOOK_LAST_RECENT_PAGE, MySQLiteOpenHelper.BOOK_NUM_PAGE };
        //Cursor cursor = db.query(MySQLiteOpenHelper.BOOK_TABLE, projection, null, null, null, null, null );
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setGenre(cursor.getString(2));
            book.setImgPath(cursor.getString(3));
            book.setBookPath(cursor.getString(4));
            book.setLastRecentPage(cursor.getInt(5));
            book.setNumPage(cursor.getInt(6));
            retArray.add(book);
        }

        cursor.close();
        return retArray;
    }
}
