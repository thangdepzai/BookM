package com.samsung.bookm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.bookm.Interface.ITransferData;
import com.samsung.bookm.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    ArrayList<String> bookNames;
    ArrayList<Integer> bookImages;
    Context mContext;
    ITransferData mITranferData;

    public BookAdapter(Context mContext, ArrayList<Integer> bookImages, ArrayList<String> bookNames, ITransferData mITranferData) {
        this.bookImages = bookImages;
        this.bookNames = bookNames;
        this.mContext = mContext;
        this.mITranferData = mITranferData;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.rowlayout, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, final int position) {
        String name = bookNames.get(position);
        holder.tv_book.setText(name);
        int id_img = bookImages.get(position);
        holder.image_view_book.setImageResource(id_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(mContext, Main2Activity.class);
              //  mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookNames.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView image_view_book;
        TextView tv_book;

        public BookViewHolder(View itemView) {
            super(itemView);

            image_view_book = (ImageView) itemView.findViewById(R.id.image_view_book);
            tv_book = (TextView) itemView.findViewById(R.id.tv_book);

        }
    }
}