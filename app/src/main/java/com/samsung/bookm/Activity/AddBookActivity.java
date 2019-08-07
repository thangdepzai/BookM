package com.samsung.bookm.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;


import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.samsung.bookm.Fragment.BookShelfFragment;
import com.samsung.bookm.Model.AppDatabase;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;
import com.samsung.bookm.Util.Utils;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final int PERMISSION_CODE = 42042;

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
        btnAddBook.setClickable(false);
        btnChoseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, PICK_IMAGE);
            }
        });

        btnChoseBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddBook.setClickable(false);
                btnAddBook.setBackgroundColor(getResources().getColor(R.color.button_add_book_not_press));
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_BOOK);
            }
        });



    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            if(resultCode == RESULT_OK && data != null) {
                Uri selectedImg = data.getData();
                if(selectedImg != null) {
                    btnChoseImage.setImageURI(selectedImg);
                    String imgPath = Utils.getPathFromUri(mContext, selectedImg);
                    newBook.setImgPath(imgPath);
                }

            }

        }
        else if (requestCode == PICK_BOOK) {
            if(resultCode == RESULT_OK &&data != null) {
                Uri selectedFile = data.getData();
                if(selectedFile != null) {
                    newBook.setBookPath(selectedFile+"");
                    btnChoseBook.setText(selectedFile.toString());
                    btnAddBook.setClickable(true);
                    btnAddBook.setBackgroundColor(getResources().getColor(R.color.button_add_book_press));
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
                                newBook.setLastRecentPage(0);
                                newBook.setLastRecentPage(10);
                                AppDatabase.getInstance(mContext).insertBook(newBook);
                                Intent i = new Intent(getApplicationContext(), BookShelfFragment.class);
                                startActivity(i);
                                finish();

                            }
                        }
                    });

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
