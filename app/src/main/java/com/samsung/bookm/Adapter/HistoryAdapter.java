package com.samsung.bookm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.bookm.Activity.SearchActivity;
import com.samsung.bookm.Data.HistoryDatabase;
import com.samsung.bookm.Model.SearchHistory;
import com.samsung.bookm.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    ArrayList<SearchHistory> arr;
    Context context;

    public HistoryAdapter(ArrayList<SearchHistory> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, final int position) {
        final SearchHistory s = arr.get(position);
        holder.txt.setText(s.getText());
        holder.btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryDatabase.getInstance(context).deleteHistory(s.getId());
                arr.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView btn_clear;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_his);
            btn_clear = itemView.findViewById(R.id.btn_clear);
        }
    }
}
