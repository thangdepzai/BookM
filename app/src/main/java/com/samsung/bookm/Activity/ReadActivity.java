package com.samsung.bookm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
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
    public static final String KEY_URI = "KEY_URI";
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

        if (bundle != null) {
            mBook = (Book) bundle.getSerializable("exercise");
            if (mBook != null) {
                Log.d("SVMC", "onCreate: " + mBook.getBookPath());
                String uriString = mBook.getBookPath();
                Uri uri = Uri.fromFile(new File(uriString));
                pageNum = mBook.getLastRecentPage();
                displayFromUri(uri);
                //do something
            }

            String uriS = getIntent().getStringExtra("KEY_URI");
            if (uriS != null && isStoragePermissionGranted()) {
                Uri uri = Uri.fromFile(new File(uriS));
                displayFromUri(uri);
            }
        }
    }

    @Override
    public void displayFromUri(Uri uri) {
        mPdfView.fromUri(uri)
                .defaultPage(pageNum )
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }

    @Override
    public String getFileName(Uri uri) {
        return null;
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

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("SVMC","Permission is granted");
                return true;
            } else {

                Log.v("SVMC","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("SVMC","Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("SVMC","Permission: "+permissions[0]+ "was "+grantResults[0]);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Long endTime = System.currentTimeMillis()/1000;
        Long readTime = mBook.getTotalReadTime() + endTime - startTime;
        AppDatabase.getInstance(this).updateReadTime(mBook.getId(), readTime, endTime);
    }


}

