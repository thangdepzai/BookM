package com.samsung.bookm.Adapter;

import android.content.Context;

import android.util.Log;
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


public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder> {
    ArrayList<Book> arrBook = new ArrayList<>();
    Context mContext;
    @NonNull
    @Override
    public StatisticViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(mContext);
        View itemView=inflater.inflate(R.layout.image_recycler_item,viewGroup,false);
        return new StatisticViewHolder(itemView);
    }

    public StatisticAdapter(ArrayList<Book> arrBook, Context mContext) {
        this.arrBook = arrBook;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticViewHolder statisticViewHolder,final int i) {
        Book book = arrBook.get(i);
        statisticViewHolder.imgBook.setBackgroundResource(R.drawable.bia_sach1);
        statisticViewHolder.tvName.setText(book.getName());
        Log.d("ds", String.valueOf(statisticViewHolder.tvName.getText()));
    }

    @Override
    public int getItemCount() {
        return arrBook.size();
    }
    public class StatisticViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        ImageView imgBook;
        public StatisticViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tv_name);
            imgBook=(ImageView)itemView.findViewById(R.id.image_recycler);
        }
    }
}
