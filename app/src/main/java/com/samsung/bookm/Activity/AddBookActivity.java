package com.jmctvs.addbook;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jmctvs.addbook.model.AppDatabase;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener{

    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    Button mBtnPickFile, mBtnAddBook;
    TextView mTvBookName, mTvAuthor, mTvBookUri;
    Spinner mSpCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        mBtnPickFile = findViewById(R.id.btn_pickfile);
        mBtnPickFile.setOnClickListener(this);
        mTvBookUri = findViewById(R.id.tv_bookuri);

        mBtnAddBook = findViewById(R.id.btn_addbook);
        mBtnAddBook.setOnClickListener(this);

        mSpCategory = findViewById(R.id.sp_category);
        String[] genreList = {"Ngon tinh", "Trinh tham", "Tho"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genreList);
        mSpCategory.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pickfile:
                pickFile();
                break;
            case R.id.btn_addbook:
                addBook();
                break;
        }
    }

    void pickFile() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{READ_EXTERNAL_STORAGE},
                    PERMISSION_CODE
            );
            return;
        }
        launchPicker();
    }

    void launchPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            //alert user that file manager not working
            Toast.makeText(this, "Can not open file!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Uri uri = data.getData();
                if(getFileName(uri) != null){
                    mTvBookUri.setText(getFileName(uri));
                }
                Log.d("SVMC", uri.toString());
            }
        }
    }

    void addBook(){
        AppDatabase.getInstance(this).insertGenre("Tiểu thuyết");
        AppDatabase.getInstance(this).insertGenre("Trinh thám");
        AppDatabase.getInstance(this).insertGenre("Ngôn tình");
        AppDatabase.getInstance(this).insertGenre("Thơ");
    }
}
