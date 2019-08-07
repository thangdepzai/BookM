package com.samsung.bookm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.bookm.Activity.AddBookActivity;
import com.samsung.bookm.Adapter.ViewPagerAdapter;
import com.samsung.bookm.Data.SettingPreference;
import com.samsung.bookm.Fragment.BookShelfFragment;
import com.samsung.bookm.Fragment.ScheduleFragment;
import com.samsung.bookm.Fragment.SettingFragment;
import com.samsung.bookm.Fragment.StatisticFragment;
import com.samsung.bookm.Interface.ISettingCallBack;


public class MainActivity extends AppCompatActivity implements ISettingCallBack {
    ViewPagerAdapter adapter;
    private static final String TAG = "MainActivity";
    public static final int PERMISSION_CODE = 42042;
    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    MenuItem prevMenuItem=null;
    BookShelfFragment bookShelfFragment;
    ScheduleFragment scheduleFragment;
    SettingFragment settingFragment;
    StatisticFragment statisticFragment;
    SettingPreference mSettingPreference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettingPreference = SettingPreference.getInstance(this);
        initNightMode();


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        invalidateFragmentMenus(0);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_book:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.nav_shedule:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.nav_statistic:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.nav_setting:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                invalidateFragmentMenus(position);
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("SVMC", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//         //Disable ViewPager Swipe
//       viewPager.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                return true;
//            }
//        });



    }
    @Override
    public void initNightMode() {
        int nightMode = mSettingPreference.getCurrNightMode();
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }
    @Override
    public int getCurrentNightMode() {
        return getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
    }

    @Override
    public void alternateNightMode(int currentNightMode) {
        int newNightMode;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            newNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else {
            newNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        }
        mSettingPreference.changeNightMode(newNightMode);
        recreate();
    }

    private void setupViewPager(ViewPager viewPager) {
         adapter = new ViewPagerAdapter(getSupportFragmentManager());

        bookShelfFragment = new BookShelfFragment();
        scheduleFragment = new ScheduleFragment();
        settingFragment = new SettingFragment();
        statisticFragment = new StatisticFragment();

        adapter.addFragment(bookShelfFragment);
        adapter.addFragment(scheduleFragment);
        adapter.addFragment(statisticFragment);
        adapter.addFragment(settingFragment);

        viewPager.setAdapter(adapter);
    }

    private void invalidateFragmentMenus(int position){
        for(int i = 0; i < adapter.getCount(); i++){
            adapter.getItem(i).setHasOptionsMenu(i == position);
        }
        invalidateOptionsMenu(); //or respectively its support method.
    }



}