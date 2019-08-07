package com.example.administrator.bookdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeBookInfoActivity extends AppCompatActivity {

    EditText mEdBookName, mEdAuthor;

    Button mBtnSaveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_book_info);

        Intent intent = getIntent();

        mEdBookName = findViewById(R.id.ed_bookname);
        mEdBookName.setText(intent.getStringExtra("book_name"));
        mEdAuthor = findViewById(R.id.ed_author);
        mEdAuthor.setText(intent.getStringExtra("author"));

        mBtnSaveInfo = findViewById(R.id.btn_save_info);
        mBtnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.putExtra("book_name", mEdBookName.getText().toString());
                intent1.putExtra("author", mEdAuthor.getText().toString());
                setResult(41, intent1);
                finish();
            }
        });


    }
}
