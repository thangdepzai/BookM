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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.samsung.bookm.Activity.ReadActivity;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;

import java.io.File;
import java.util.ArrayList;

public class TickedListAdapter extends RecyclerView.Adapter<TickedListAdapter.TickedListViewHolder> {
    ArrayList<Book> arrBook = new ArrayList<>();
    Context mContext;

    public TickedListAdapter(ArrayList<Book> arrBook, Context mContext) {
        this.arrBook = arrBook;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public TickedListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(mContext);
        View itemView=inflater.inflate(R.layout.ticked_book_recycle_item,viewGroup,false);
        return new TickedListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TickedListViewHolder tickedListViewHolder, final int i) {

        final Book tickedBook = arrBook.get(i);

        if(tickedBook.getImgPath() != null) {
            Uri bookCover = Uri.fromFile(new File(tickedBook.getImgPath()));
            Log.d("SVMC", "onBindViewHolder: " + tickedBook.getImgPath());
            tickedListViewHolder.imgTickedBook.setImageURI(bookCover);
        } else {
            tickedListViewHolder.imgTickedBook.setImageResource(R.mipmap.defbookcover);

        }
        tickedListViewHolder.tvTickedName.setText(tickedBook.getName());
        Log.d("name",tickedBook.getName());
        Log.d("name",String .valueOf(tickedBook.getLastRecentPage()));
        tickedListViewHolder.tvPageNumber.setText("Trang "+String .valueOf(tickedBook.getLastRecentPage()));
//        Log.d("ds",String.valueOf(tickedListViewHolder.tvName.getText()));

        tickedListViewHolder.imgTickedBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, ReadActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("exercise", tickedBook);
                i.putExtra("READ_BOOK", bundle);
                mContext.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrBook.size();
    }

    public class TickedListViewHolder extends RecyclerView.ViewHolder {
        TextView tvTickedName;
        ImageView imgTickedBook;
        TextView tvPageNumber;
        public TickedListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPageNumber=(TextView)itemView.findViewById(R.id.tv_page_number) ;
            tvTickedName=(TextView)itemView.findViewById(R.id.tv_ticked_name);
            imgTickedBook=(ImageView)itemView.findViewById(R.id.image_ticked_recycler);
        }
    }
}
