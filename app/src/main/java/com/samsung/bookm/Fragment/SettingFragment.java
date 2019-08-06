package com.samsung.bookm.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.QuickContactBadge;
import android.widget.Switch;

import com.samsung.bookm.Interface.ISettingCallBack;
import com.samsung.bookm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    ISettingCallBack iSettingCallBack;
    public SettingFragment() {
        // Required empty public constructor
    }
    public SettingFragment(ISettingCallBack context) {
        iSettingCallBack = context;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_setting, container, false);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_setting_activity);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button sw_night_mode = view.findViewById(R.id.sw_night_mode);
        sw_night_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iSettingCallBack==null){
                    iSettingCallBack = (ISettingCallBack) getContext();
                }
                iSettingCallBack.alternateNightMode(iSettingCallBack.getCurrentNightMode());
            }
        });
//        Switch mDarkMode=view.findViewById(R.id.sw_night_mode);
//        mDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(iSettingCallBack==null){
//                    iSettingCallBack = (ISettingCallBack) getContext();
//                }
//                Log.d("abcd",String.valueOf(iSettingCallBack.getCurrentNightMode()));
//                if(isChecked){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                }
//
//            }
//        });
    }
}
