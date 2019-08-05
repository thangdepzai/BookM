package com.samsung.bookm.Fragment;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samsung.bookm.Activity.ReadActivity;
import com.samsung.bookm.Adapter.BookAdapter;
import com.samsung.bookm.Activity.SearchActivity;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.R;
import com.samsung.bookm.Util.IPdfReaderUtils;
import static android.app.Activity.RESULT_OK;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookShelfFragment extends Fragment {

    Toolbar toolbar;

    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;
    public static final String SAMPLE_FILE = "sample.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

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
        Log.d("SVMC", "onCreateView BOOK");
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_book_shelf, container, false);
        toolbar = v.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.book_shelf_menu);
        toolbar.setTitle(R.string.title_book_shelf_activity);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("SVMC", "onViewCreated BOOK");

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        pickFile();
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
        adapter = new BookAdapter(getContext(), listBooks);
        mBookRecycler.setAdapter(adapter);
    }


    // xin quyen doc file
    void pickFile() {
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),
                READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{READ_EXTERNAL_STORAGE},
                    PERMISSION_CODE
            );

            return;
        }

        launchPicker();
    }

    // tim kiem file
    void launchPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            //alert user that file manager not working
            Toast.makeText(getContext(), "Can not open file!!!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Uri uri = data.getData();
               Intent i = new Intent(getContext(), ReadActivity.class);
               Bundle b = new Bundle();
               i.putExtra("KEY_URI", uri+"");
               startActivity(i);

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SVMC", "DESTROY BOOK");
    }
    void getData() {

    }


}
