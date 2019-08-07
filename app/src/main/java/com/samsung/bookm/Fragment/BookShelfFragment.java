package com.samsung.bookm.Fragment;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.content.Context;

import android.os.Build;
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

import com.samsung.bookm.Activity.AddBookActivity;
import com.samsung.bookm.Activity.ReadActivity;
import com.samsung.bookm.Adapter.BookAdapter;
import com.samsung.bookm.Activity.SearchActivity;
import com.samsung.bookm.Model.AppDatabase;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.Interface.ITransferData;
import com.samsung.bookm.R;
import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.widget.Constraints.TAG;


import java.util.ArrayList;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookShelfFragment extends Fragment  implements ITransferData {
    public BookShelfFragment() {
    }

    Toolbar toolbar;

    private final static int REQUEST_CODE = 42;
    public static final String SAMPLE_FILE = "sample.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    RecyclerView mBookRecycler;
    ArrayList<Book> listBooks;
    BookAdapter adapter;


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
                        if(isStoragePermissionGranted()){
                            Intent it = new Intent(getActivity(), AddBookActivity.class);
                            startActivity(it);


                        }
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

        //tạo Grid với 3 cột
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        mBookRecycler.setLayoutManager(gridLayoutManager);

        listBooks = AppDatabase.getInstance(getContext()).getAllBook();
        adapter = new BookAdapter(getContext(),listBooks );
        mBookRecycler.setAdapter(adapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        listBooks = AppDatabase.getInstance(getContext()).getAllBook();
        adapter = new BookAdapter(getContext(),listBooks );
        mBookRecycler.setAdapter(adapter);
    }

    @Override
    public void delete(int position) {
        listBooks.remove(position);
        adapter.notifyDataSetChanged();
    }



    // xin quyen doc file
    void pickFile() {
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),
                READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{READ_EXTERNAL_STORAGE},
                    1
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
    ArrayList<Book> getData() {
        ArrayList<Book> arr = AppDatabase.getInstance(getContext()).getAllBook();
        // TODO get data tu dâtbase
        return arr;


    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("SVMC","Permission is granted");
                return true;
            } else {

                Log.v("SVMC","Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("SVMC","Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("SVMC","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }


}
