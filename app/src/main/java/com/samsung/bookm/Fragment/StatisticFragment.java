package com.samsung.bookm.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.samsung.bookm.Adapter.IndexStatisticAdapter;
import com.samsung.bookm.Adapter.StatisticAdapter;
import com.samsung.bookm.Adapter.TickedListAdapter;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.Model.IndexStatistic;
import com.samsung.bookm.R;



import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView mTickedRecyclerView;
    RecyclerView mIndexRecyclerView;
    Context mContext;
    ArrayList<Book> book = null;
    ArrayList<Book> tickedBook = null;
    ArrayList<IndexStatistic> indexStatistics = null;
    StatisticAdapter adapter;
    TickedListAdapter mTickedListAdapter;
    IndexStatisticAdapter mIndexStatisticAdapter;

    public StatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistic, container, false);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_statistic_activity);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_product);
        mTickedRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_ticked_page);
        mIndexRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_index);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerTicked = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerIndex = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

//        layoutManager.
        mRecyclerView.setLayoutManager(layoutManager);
        mTickedRecyclerView.setLayoutManager(layoutManagerTicked);
        mIndexRecyclerView.setLayoutManager(layoutManagerIndex);
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(3000);
        AlphaAnimation animTicked = new AlphaAnimation(0.0f, 1.0f);
        animTicked.setDuration(3000);
        AlphaAnimation animIndex = new AlphaAnimation(0.0f, 1.0f);
        animIndex.setDuration(3000);
        mTickedRecyclerView.setAnimation(animTicked);
        mRecyclerView.setAnimation(anim);
        mIndexRecyclerView.setAnimation(animIndex);
        book = new ArrayList<Book>();
        tickedBook = new ArrayList<Book>();
        indexStatistics = new ArrayList<>();
        getData();
        mTickedListAdapter = new TickedListAdapter(tickedBook, getActivity());
        adapter = new StatisticAdapter(book, getActivity());
        mRecyclerView.setAdapter(adapter);
        mTickedRecyclerView.setAdapter(mTickedListAdapter);
        mIndexStatisticAdapter = new IndexStatisticAdapter(indexStatistics, getActivity());
        mIndexRecyclerView.setAdapter(mIndexStatisticAdapter);
    }
    private void getData() {
        book.add(new Book(1, "Đắc nhân tâm", 3));
        book.add(new Book(2, "Yêu trên từng ngón tay", 4));
        book.add(new Book(3, "Hạt giống tâm hồn", 5));
        book.add(new Book(4, "Bố già", 6));
        book.add(new Book(5, "Sherlock Home", 7));
        tickedBook.add(new Book(1, "Đắc nhân tâm", 3));
        tickedBook.add(new Book(2, "Yêu trên từng ngón tay", 4));
        tickedBook.add(new Book(3, "Hạt giống tâm hồn", 5));
        tickedBook.add(new Book(4, "Bố già", 6));
        tickedBook.add(new Book(5, "Sherlock Home", 7));
        indexStatistics.add(new IndexStatistic(2.1, "Số giờ đọc"));
        indexStatistics.add(new IndexStatistic(0.4, "Số giờ đọc"));
        indexStatistics.add(new IndexStatistic(1.2, "Số giờ đọc"));
        indexStatistics.add(new IndexStatistic(3.2, "Số giờ đọc"));
        indexStatistics.add(new IndexStatistic(4.1, "Số giờ đọc"));

    }
}
