package com.samsung.bookm.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.bookm.Model.IndexStatistic;
import com.samsung.bookm.R;


import java.util.ArrayList;

public class IndexStatisticAdapter extends RecyclerView.Adapter<IndexStatisticAdapter.IndexStatisViewHolder> {
    ArrayList<IndexStatistic> mArrIndex;
    Context mContext;

    public IndexStatisticAdapter(ArrayList<IndexStatistic> mArrIndex, Context mContext) {
        this.mArrIndex = mArrIndex;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public IndexStatisViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_index_statistic, viewGroup, false);
        return new IndexStatisViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexStatisViewHolder indexStatisViewHolder, final int i) {
        IndexStatistic indexStatistic = mArrIndex.get(i);
        indexStatisViewHolder.tvIndex.setText(String.valueOf(indexStatistic.getIndex()));
        indexStatisViewHolder.tvTitle.setText(indexStatistic.getTitle());
    }

    @Override
    public int getItemCount() {
        return mArrIndex.size();
    }

    public class IndexStatisViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndex, tvTitle;

        public IndexStatisViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
