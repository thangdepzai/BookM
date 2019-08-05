package com.samsung.bookm.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder> {
    List<Book>bookList=new ArrayList<>();

    public StatisticAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public StatisticAdapter.StatisticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.statistic_book, parent, false);
        return new StatisticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticAdapter.StatisticViewHolder holder, final int position) {
        holder.mTextView.setText(bookList.get(position).getName());
        holder.mImageView.setBackgroundResource(R.drawable.ic_book_black_24dp);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class StatisticViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        public StatisticViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=(ImageView) itemView.findViewById(R.id.imageView);
            mTextView=(TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
