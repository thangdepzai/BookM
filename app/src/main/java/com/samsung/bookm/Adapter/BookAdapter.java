package com.samsung.bookm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.bookm.R;
import com.samsung.bookm.model.Book;

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
        bookViewHolder.imBookCover.setImageResource(R.mipmap.defbookcover);
        bookViewHolder.tvBookName.setText(book.getName());
        bookViewHolder.imBookCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        public BookViewHolder(View itemView) {
            super(itemView);
            imBookCover = (ImageView) itemView.findViewById(R.id.book_cover);
            tvBookName = (TextView) itemView.findViewById(R.id.book_name);
        }
    }
}
