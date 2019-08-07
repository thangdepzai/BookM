package com.samsung.bookm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.samsung.bookm.Model.AppDatabase;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;
import com.samsung.bookm.Util.IPdfReaderUtils;

import java.io.File;
import java.util.Objects;

public class ReadActivity extends AppCompatActivity implements IPdfReaderUtils {
    PDFView mPdfView;
    String pdfFileName;
    int pageNum=0;
    Book mBook;
    Long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        mPdfView = findViewById(R.id.pdfViewer);

        Bundle bundle = getIntent().getBundleExtra("READ_BOOK");
        mBook = (Book)bundle.getSerializable("exercise");

        if(mBook != null)
        {
            Log.d("SVMC", "onCreate: " + mBook.getBookPath());
            String uriString = mBook.getBookPath();
            Uri uri = Uri.fromFile(new File(uriString));
            displayFromUri(uri);
            //do something
        }

        String uriS = getIntent().getStringExtra("KEY_URI");
        if(uriS!=null){
            Uri uri = Uri.parse(uriS);
            displayFromUri(uri);
        }
    }

    @Override
    public void displayFromUri(Uri uri) {
        mPdfView.fromUri(uri)
                .defaultPage(mBook.getLastRecentPage())
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }

    public String getFileName(Uri uri) {
        Log.d("SVMC", "getFileName: " +uri.getLastPathSegment());
        String result = null;
        if (Objects.equals(uri.getScheme(), "content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    @Override
    public void loadComplete(int nbPages) {
        Log.d("PDF", "loadComplete: " + nbPages);
        AppDatabase.getInstance(this).updateBookNumPage(mBook.getId(), nbPages);
        startTime = System.currentTimeMillis()/1000;
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        AppDatabase.getInstance(this).updateLastReadPage(mBook.getId(), page);
    }

    @Override
    public void onPageError(int page, Throwable t) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Long endTime = System.currentTimeMillis()/1000;
        Long readTime = mBook.getTotalReadTime() + endTime - startTime;
        AppDatabase.getInstance(this).updateReadTime(mBook.getId(), readTime, endTime);
    }
}
