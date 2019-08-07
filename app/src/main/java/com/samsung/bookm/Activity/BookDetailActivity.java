package com.samsung.bookm.Activity;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.samsung.bookm.Model.AppDatabase;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;

import java.io.File;
import java.util.ArrayList;

public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE = 41;
    Button mBtnChangeInfo, mBtnSaveInfo, mBtnRead, mBtnDelete;
    TextView mTvBookName, mTvAuthor, mTvCategory, mTvNbPage;
    Spinner mSpCategory;
    ImageView mIvBookCover;
    EditText mEdBookName, mEdAuthor, mEdDescription;
    Book mBook;
    Context mContext;
    ArrayList<String> listGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        mContext = this;

        listGenre = AppDatabase.getInstance(this).getAllGenre();

        mBook = AppDatabase.getInstance(this).getBookById(getIntent().getIntExtra("bookId", 0));
        Log.d("svmc", "onClick: " + mBook.getAuthor());

        mBtnRead = findViewById(R.id.btn_read);
        mBtnChangeInfo = (Button) findViewById(R.id.btn_change_info);
        mTvBookName = findViewById(R.id.tv_book_name);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvCategory = findViewById(R.id.tv_category);
        mTvNbPage = findViewById(R.id.tv_nbpage);
        mIvBookCover = findViewById(R.id.img_cover);
        mBtnDelete = findViewById(R.id.btn_delete_book);

        mBtnSaveInfo = findViewById(R.id.btn_save_info);
        mEdBookName = findViewById(R.id.ed_book_name);
        mEdAuthor = findViewById(R.id.ed_author);
        mSpCategory = findViewById(R.id.sp_category);
        mEdDescription = findViewById(R.id.ed_description);

        mTvBookName.setText(mBook.getName());
        mTvAuthor.setText(mBook.getAuthor());
        mTvCategory.setText(listGenre.get(mBook.getGenreId()-1));
        mTvNbPage.setText(mBook.getNumPage() + "");
        if(mBook.getImgPath() != null){
            Uri imgUri = Uri.fromFile(new File(mBook.getImgPath()));
            mIvBookCover.setImageURI(imgUri);
        } else {
            mIvBookCover.setImageResource(R.drawable.defbookcover);
        }


        mBtnSaveInfo.setVisibility(View.GONE);
        mEdBookName.setVisibility(View.GONE);
        mEdAuthor.setVisibility(View.GONE);
        mSpCategory.setVisibility(View.GONE);
        mEdDescription.setVisibility(View.GONE);

        ArrayAdapter genreAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listGenre);
        mSpCategory.setAdapter(genreAdapter);

        mBtnChangeInfo.setOnClickListener(this);
        mBtnSaveInfo.setOnClickListener(this);
        mBtnRead.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_change_info:

                mBtnSaveInfo.setVisibility(View.VISIBLE);
                mEdBookName.setVisibility(View.VISIBLE);
                mEdBookName.setText(mTvBookName.getText().toString());
                mEdAuthor.setVisibility(View.VISIBLE);
                mEdAuthor.setText(mTvAuthor.getText().toString());
                mSpCategory.setVisibility(View.VISIBLE);

                mBtnChangeInfo.setVisibility(View.GONE);
                mTvBookName.setVisibility(View.GONE);
                mTvAuthor.setVisibility(View.GONE);
                mTvCategory.setVisibility(View.GONE);
                mBtnRead.setVisibility(View.INVISIBLE);
                mBtnDelete.setVisibility(View.GONE);

                break;
            case R.id.btn_save_info:

                mBtnChangeInfo.setVisibility(View.VISIBLE);
                mTvBookName.setVisibility(View.VISIBLE);
                mTvBookName.setText(mEdBookName.getText().toString());
                mBook.setName(mEdBookName.getText().toString());
                mTvAuthor.setVisibility(View.VISIBLE);
                mTvAuthor.setText(mEdAuthor.getText().toString());
                mBook.setAuthor(mEdAuthor.getText().toString());
                mTvCategory.setVisibility(View.VISIBLE);
                mBook.setGenreId(mSpCategory.getSelectedItemPosition()+1);
                mTvCategory.setText(listGenre.get(mBook.getGenreId()-1));

                Log.d("svmc", "onClick: " + mBook.getAuthor());

                AppDatabase.getInstance(this).updateBookInfo(mBook);

                mBtnSaveInfo.setVisibility(View.GONE);
                mEdBookName.setVisibility(View.GONE);
                mEdAuthor.setVisibility(View.GONE);
                mSpCategory.setVisibility(View.GONE);
                mBtnRead.setVisibility(View.VISIBLE);
                mBtnDelete.setVisibility(View.VISIBLE);

                break;

            case R.id.btn_read:
                Intent i = new Intent(this, ReadActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("exercise", mBook);
                i.putExtra("READ_BOOK", bundle);
                startActivity(i);
                break;

            case R.id.btn_delete_book:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to remove this book?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDatabase.getInstance(mContext).deleteBook(mBook.getId());
                        Toast.makeText(mContext, "Book has been removed!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        if(requestCode == 41){
//            mTvBookName.setText(data.getStringExtra("book_name"));
//            mTvAuthor.setText(data.getStringExtra( "author"));
//        }
//    }

}
