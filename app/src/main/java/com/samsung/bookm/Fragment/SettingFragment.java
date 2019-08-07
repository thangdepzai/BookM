package com.samsung.bookm.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.Switch;
import android.widget.Toast;

import com.samsung.bookm.Activity.InformationActivity;
import com.samsung.bookm.Activity.LanguageActivity;
import com.samsung.bookm.Interface.ISettingCallBack;
import com.samsung.bookm.MainActivity;
import com.samsung.bookm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    Context mContext;
    LinearLayout mLanguage, mInformation;
    Switch mSwitch;
    MainActivity mMainActivity;
    public SettingFragment() {
        // Required empty public constructor
    }
    public SettingFragment(Context context) {
        mContext = context;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_setting, container, false);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_setting_activity);

        mLanguage = v.findViewById(R.id.language);
        mInformation = v.findViewById(R.id.app_information);
        mSwitch = v.findViewById(R.id.switch_nightmode);

        mLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LanguageActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        mInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InformationActivity.class);
                startActivity(intent);
            }
        });

        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                mSwitch.setChecked(false);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                mSwitch.setChecked(true);
                break;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                // We don't know what mode we're in, assume notnight
                break;
        }

//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
//            mSwitch.setChecked(true);
//        }
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        return v;
    }

}
