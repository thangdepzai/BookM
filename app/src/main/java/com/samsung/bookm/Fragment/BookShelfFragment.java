package com.samsung.bookm.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.bookm.Adapter.BookAdapter;
import com.samsung.bookm.R;
import com.samsung.bookm.model.Book;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookShelfFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_book_shelf, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBookRecycler = (RecyclerView) view.findViewById(R.id.book_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mBookRecycler.setLayoutManager(gridLayoutManager);
        listBooks = new ArrayList<Book>();
        getData();
        adapter = new BookAdapter(getActivity(), listBooks);
        mBookRecycler.setAdapter(adapter);
    }

    void getData() {
        listBooks.add(new Book("book1", "abc0", "abc"));
        listBooks.add(new Book("book2", "abc0", "abc"));
        listBooks.add(new Book("book3", "abc0", "abc"));
        listBooks.add(new Book("book4", "abc0", "abc"));
        listBooks.add(new Book("book5", "abc0", "abc"));
        listBooks.add(new Book("book6", "abc0", "abc"));
        listBooks.add(new Book("book7", "abc0", "abc"));
        listBooks.add(new Book("book8", "abc0", "abc"));
        listBooks.add(new Book("book9", "abc0", "abc"));
        listBooks.add(new Book("book10", "abc0", "abc"));
        listBooks.add(new Book("book11", "abc0", "abc"));
    }

}
