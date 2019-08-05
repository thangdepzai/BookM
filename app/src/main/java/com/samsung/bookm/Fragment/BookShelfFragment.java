package com.samsung.bookm.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.bookm.Adapter.BookAdapter;
import com.samsung.bookm.Activity.SearchActivity;
import com.samsung.bookm.R;
import com.samsung.bookm.model.Book;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookShelfFragment extends Fragment {

    Toolbar toolbar;
    RecyclerView mBookRecycler;
    ArrayList<Book> listBooks;
    BookAdapter adapter;

    public BookShelfFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBooks = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_book_shelf, container, false);
        setHasOptionsMenu(true);
        toolbar = v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.title_book_shelf_activity);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        toolbar.inflateMenu(R.menu.book_shelf_menu);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        return true;
                    case  R.id.action_more:
                        return true;
                    case R.id.action_search:
                        Intent intent = new Intent(getActivity(), SearchActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return BookShelfFragment.super.onOptionsItemSelected(item);
                }
            }
        });

        mBookRecycler = (RecyclerView) view.findViewById(R.id.book_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mBookRecycler.setLayoutManager(gridLayoutManager);
        listBooks = new ArrayList<Book>();
        getData();
        adapter = new BookAdapter(getActivity(), listBooks);
        mBookRecycler.setAdapter(adapter);
    }

    void getData() {

    }

}
