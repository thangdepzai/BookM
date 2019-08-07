package com.example.administrator.bookdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE = 41;
    Button mBtnChangeInfo;
    TextView mTvBookName, mTvAuthor, mTvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        mBtnChangeInfo = (Button) findViewById(R.id.btn_change_info);
        mBtnChangeInfo.setOnClickListener(this);

        mTvBookName = findViewById(R.id.tv_book_name);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvDescription = findViewById(R.id.tv_description);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_change_info:
                Intent intent = new Intent(this, ChangeBookInfoActivity.class);
                intent.putExtra("book_name", mTvBookName.getText().toString());
                intent.putExtra("author", mTvAuthor.getText().toString());
                intent.putExtra("description", mTvDescription.getText().toString());
                startActivityForResult(intent, 41);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 41){
            mTvBookName.setText(data.getStringExtra("book_name"));
            mTvAuthor.setText(data.getStringExtra( "author"));
        }
    }

}
