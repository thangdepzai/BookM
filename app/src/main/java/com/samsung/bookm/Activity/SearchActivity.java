package com.samsung.bookm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.bookm.R;
import com.samsung.bookm.Util.InputMethodUtils;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivArrowBack;
    private ImageView ivActionSearch;
    private ImageView ivActionClear;
    private EditText etSearch;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // anh xa view
        findView();
        // dang ki lang nghe su kien
        registerListener();

    }

    private void registerListener() {
        ivActionClear.setOnClickListener(this);
        ivActionSearch.setOnClickListener(this);
        ivActionClear.setOnClickListener(this);
        ivArrowBack.setOnClickListener(this);


        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    goSearchResult();
                    return true;
                }
                return false;
            }
        });

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus && etSearch.getText().length() > 0) {
                    ivActionClear.setVisibility(View.VISIBLE);
                } else {
                    ivActionClear.setVisibility(View.GONE);
                }
            }
        });


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
                if (count > 0 && etSearch.isFocusable()) {
                    ivActionClear.setVisibility(View.VISIBLE);
                } else {
                    ivActionClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etSearch.post(new Runnable() {
            @Override
            public void run() {
                // bat ban phim ao
                InputMethodUtils.showSoftInput(etSearch);
            }
        });




    }


    private void findView() {
        ivArrowBack = (ImageView) findViewById(R.id.iv_arrow_back);
        ivActionSearch = (ImageView) findViewById(R.id.iv_action_search);
        ivActionClear = findViewById(R.id.iv_action_clear);
        etSearch = (EditText) findViewById(R.id.et_search);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_arrow_back:
                onBackPressed();
                InputMethodUtils.hideSoftInput(this);

                // an ban phim ao

                break;
            case R.id.iv_action_search:
                goSearchResult();
                break;
            case R.id.iv_action_clear:
                etSearch.setText("");
                break;
        }
    }

    private void goSearchResult() {
        Editable etSearchText = etSearch.getText();
        if (etSearchText != null) {
            String trim = etSearchText.toString().trim();
            if (trim.length() > 0) {
                // tim tu khoa
 //               DBManger.getInstance().saveSearchKeyword(trim);
//                AppRouter.showSearchResultActivity(this, trim);
//                AppManager.getInstance().finishActivity(getClass());
            }
        }
    }

}
