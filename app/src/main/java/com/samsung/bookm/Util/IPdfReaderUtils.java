package com.samsung.bookm.Util;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
public interface IPdfReaderUtils extends OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener {
    public void displayFromUri(Uri uri);

    public String getFileName(Uri uri);


    @Override
    public void loadComplete(int nbPages);

    @Override
    public void onPageChanged(int page, int pageCount);

    @Override
    public void onPageError(int page, Throwable t);

}
