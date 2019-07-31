package com.samsung.bookm;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.bookm.Adapter.ViewPagerAdapter;
import com.samsung.bookm.Fragment.BookShelfFragment;
import com.samsung.bookm.Fragment.ScheduleFragment;
import com.samsung.bookm.Fragment.SettingFragment;
import com.samsung.bookm.Fragment.StatisticFragment;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    MenuItem prevMenuItem=null;
    BookShelfFragment bookShelfFragment;
    ScheduleFragment scheduleFragment;
    SettingFragment settingFragment;
    StatisticFragment statisticFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
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
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

         //Disable ViewPager Swipe
       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });


        setupViewPager(viewPager);
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        bookShelfFragment=new BookShelfFragment();
        scheduleFragment = new ScheduleFragment();
        settingFragment = new SettingFragment();
        statisticFragment = new StatisticFragment();

        adapter.addFragment(bookShelfFragment);
        adapter.addFragment(scheduleFragment);
        adapter.addFragment(statisticFragment);
        adapter.addFragment(settingFragment);

        viewPager.setAdapter(adapter);
    }


}