package com.samsung.bookm.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;


public class SettingPreference {
    private static SettingPreference instance = null;
    private Context mContext;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor mEditor;

    private static final String PREFS_NAME = "setting";
    private static final String NIGHT_MODE = "night_mode";

    private SettingPreference(Context context) {
        mContext = context;
        mPreference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mEditor = mPreference.edit();
    }

    public static SettingPreference getInstance(Context mContext)
    {
        if (instance == null)
            instance = new SettingPreference(mContext);

        return instance;
    }



    public int getCurrNightMode(){
        return mPreference.getInt(NIGHT_MODE, AppCompatDelegate.MODE_NIGHT_NO);
    }
    public  void changeNightMode(int mode){
        mEditor.putInt(NIGHT_MODE, mode);
        mEditor.commit();
    }
}
