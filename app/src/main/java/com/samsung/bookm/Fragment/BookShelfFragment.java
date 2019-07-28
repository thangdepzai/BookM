package com.samsung.bookm.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.bookm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookShelfFragment extends Fragment {


    public BookShelfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_shelf, container, false);
    }

}
