package com.samsung.bookm.Adapter;

import android.content.Context;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;

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
        Book book = arrBook.get(i);
        tickedListViewHolder.imgTickedBook.setImageURI(Uri.parse(book.getImgPath()));
        tickedListViewHolder.tvTickedName.setText(book.getName());
        tickedListViewHolder.tvPageNumber.setText(book.getLastRecentPage());
//        Log.d("ds",String.valueOf(tickedListViewHolder.tvName.getText()));
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
