package com.samsung.bookm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.samsung.bookm.Model.AppDatabase;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    Context mContext;
    EditText edtBookName;
    EditText edtAuthor;
    Button btnAddBook;
    Button btnChoseBook;
    ImageButton btnChoseImage;
    Spinner spnGenre;
    Book newBook;

    public static final int PICK_IMAGE = 1403;
    public static final int PICK_BOOK = 1406;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        mContext = this;

        newBook = new Book();
        edtAuthor = findViewById(R.id.et_author);
        edtBookName = findViewById(R.id.et_book_name);
        btnAddBook = findViewById(R.id.bt_add_book);
        btnChoseImage = findViewById(R.id.ib_chose_image);
        btnChoseBook = findViewById(R.id.bt_chose_book_file);
        setupGenreSpiner();

        btnChoseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        btnChoseBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Book"), PICK_BOOK);
            }
        });

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = edtBookName.getText().toString();
                if(bookName.equals("")) {
                    Toast.makeText(mContext, "Please insert book's name", Toast.LENGTH_LONG).show();
                } else {
                    newBook.setName(bookName);
                    newBook.setAuthor(edtAuthor.getText().toString());
                    newBook.setGenreId(spnGenre.getSelectedItemPosition() + 1);
                    AppDatabase.getInstance(mContext).insertBook(newBook);
                    Toast.makeText(mContext, newBook.getName() + " has been added to library", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            if(data != null) {
                Uri selectedImg = data.getData();
                if(selectedImg != null) {
                    btnChoseImage.setImageURI(selectedImg);
                    File file = new File(selectedImg.toString());
                    Log.d("SVMC", "onActivityResult: " + selectedImg.toString());
                    newBook.setImgPath(file.getPath());
                }

            }
        }
        if (requestCode == PICK_BOOK) {
            if(data != null) {
                Uri selectedFile = data.getData();
                if(selectedFile != null) {
                    newBook.setBookPath(selectedFile.getPath());
                    btnChoseBook.setText(selectedFile.getPath().substring(selectedFile.getPath().lastIndexOf("/")+1));
                }
            }
        }
    }

    void setupGenreSpiner() {
        spnGenre = findViewById(R.id.spn_genre);
        List<String> listGenre = AppDatabase.getInstance(mContext).getAllGenre();
        ArrayAdapter genreAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listGenre);
        spnGenre.setAdapter(genreAdapter);
    }
}
