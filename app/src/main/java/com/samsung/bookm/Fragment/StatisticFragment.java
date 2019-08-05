package com.samsung.bookm.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.bookm.Adapter.StatisticAdapter;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {
    RecyclerView mRecyclerView;
    StatisticAdapter mStatisticAdapter;
    List<Book> bookList;

    public StatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_listBook);
        bookList=new ArrayList<>();
        putData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mStatisticAdapter= new StatisticAdapter(bookList);
        mRecyclerView.setAdapter(mStatisticAdapter);
    }

    private void putData() {
        bookList.add(new Book("Đại số","dsfsdf"));
        bookList.add(new Book("Giải tích 1","dsfsdf"));
        bookList.add(new Book("Giải tích 2","dsfsdf"));
        bookList.add(new Book("Giải tích 3","dsfsdf"));
    }
}
