package com.samsung.bookm.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.bookm.Adapter.BookAdapter;
import com.samsung.bookm.Interface.ITransferData;
import com.samsung.bookm.MainActivity;
import com.samsung.bookm.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookShelfFragment extends Fragment  implements ITransferData {

    RecyclerView recyclerView;
    ArrayList<String> bookNames = new ArrayList<>();
    ArrayList<Integer> bookImages = new ArrayList<>();
    BookAdapter adapter;
    Context mContext;

    public BookShelfFragment(Context context) {
        // Required empty public constructor
        mContext = context;
        getData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_shelf, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);

        //tạo Grid với 3 cột
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new BookAdapter(mContext, (ArrayList<Integer>) bookImages,bookNames, this);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }


    public void getData(){
        bookNames.add("B1");
        bookNames.add("B2");
        bookNames.add("B3");
        bookNames.add("B4");
        bookNames.add("B5");
        bookNames.add("B6");
        bookImages.add(R.drawable.a1);
        bookImages.add(R.drawable.a1);
        bookImages.add(R.drawable.a1);
        bookImages.add(R.drawable.a1);
        bookImages.add(R.drawable.a1);
        bookImages.add(R.drawable.a1);
    }

    @Override
    public void delete(int position) {
        bookImages.remove(position);
        adapter.notifyDataSetChanged();
    }

}
