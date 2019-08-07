package com.samsung.bookm.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public void updateBookNumPage(int bookId,int nbPages) {
        SQLiteDatabase sqLiteDatabase = instance.mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteOpenHelper.BOOK_NUM_PAGE, nbPages);
        String whereClause = MySQLiteOpenHelper.BOOK_ID + " = ?";
        String[] whereArg = {String.valueOf(bookId)};

        sqLiteDatabase.update(MySQLiteOpenHelper.BOOK_TABLE,contentValues, whereClause, whereArg );
    }

    public void updateLastReadPage(int bookId,int lPage) {
        SQLiteDatabase sqLiteDatabase = instance.mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteOpenHelper.BOOK_LAST_RECENT_PAGE, lPage);
        String whereClause = MySQLiteOpenHelper.BOOK_ID + " = ?";
        String[] whereArg = {String.valueOf(bookId)};

        sqLiteDatabase.update(MySQLiteOpenHelper.BOOK_TABLE,contentValues, whereClause, whereArg );
    }

    public void updateReadTime(int bookId,Long duration, Long endTime) {
        SQLiteDatabase sqLiteDatabase = instance.mySQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteOpenHelper.BOOK_LAST_READ_TIME, endTime);
        contentValues.put(MySQLiteOpenHelper.BOOK_TOTAL_READ_TIME, duration);

        String whereClause = MySQLiteOpenHelper.BOOK_ID + " = ?";
        String[] whereArg = {String.valueOf(bookId)};

        sqLiteDatabase.update(MySQLiteOpenHelper.BOOK_TABLE,contentValues, whereClause, whereArg );

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
            Log.d("SVMC", "getAllBook: " + book.getBookPath());

            retArray.add(book);
        }

        cursor.close();
        return retArray;
    }
    public Book getBookById(int id){
        SQLiteDatabase db = instance.mySQLiteOpenHelper.getReadableDatabase();
        String query = "SELECT * FROM " + MySQLiteOpenHelper.BOOK_TABLE+" WHERE "+MySQLiteOpenHelper.BOOK_ID + " = "+id;
        Cursor cursor = db.rawQuery(query, null);
        Book  book =null;
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                book = new Book();

                book.setId(cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_ID)));
                book.setName(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_NAME)));
                book.setGenreId(cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_GENRE_ID)));
                book.setImgPath(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_IMG_PATH)));
                book.setBookPath(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_FILE_PATH)));
                book.setLastRecentPage(cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_LAST_RECENT_PAGE)));
                book.setNumPage(cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_NUM_PAGE)));
                book.setTotalReadTime(cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_TOTAL_READ_TIME)));
                book.setLastReadTime(cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.BOOK_LAST_READ_TIME)));
            }
            cursor.close();
        }
        return book;
    }
}