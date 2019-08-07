package com.samsung.bookm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.bookm.Activity.ReadActivity;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;


import java.io.File;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context mContext;
    ArrayList<Book> bookArr;

    public BookAdapter(Context mContext, ArrayList<Book> bookArr) {
        this.mContext = mContext;
        this.bookArr = bookArr;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(itemView);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int position) {
        final Book book = bookArr.get(position);

        if(book.getImgPath() != null) {
            Uri bookCover = Uri.fromFile(new File(book.getImgPath()));
            Log.d("SVMC", "onBindViewHolder: " + book.getImgPath());
            bookViewHolder.imBookCover.setImageURI(bookCover);
        } else {
            bookViewHolder.imBookCover.setImageResource(R.mipmap.defbookcover);

        }
        bookViewHolder.tvBookName.setText(book.getName());
        int progress = book.getNumPage() != 0 ? (book.getLastRecentPage() + 1) * 100 / book.getNumPage() : 0;
        bookViewHolder.pbReadProgress.setProgress(progress);
        // short click
        bookViewHolder.imBookCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ReadActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("exercise", book);
                i.putExtra("READ_BOOK", bundle);
                mContext.startActivity(i);
            }
        });

        // long click
        // TODO
        bookViewHolder.imBookCover.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArr.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imBookCover;
        TextView tvBookName;
        ProgressBar pbReadProgress;

        public BookViewHolder(View itemView) {
            super(itemView);
            imBookCover = (ImageView) itemView.findViewById(R.id.book_cover);
            tvBookName = (TextView) itemView.findViewById(R.id.book_name);
            pbReadProgress = (ProgressBar) itemView.findViewById(R.id.pg_book_progress);
        }
    }
}