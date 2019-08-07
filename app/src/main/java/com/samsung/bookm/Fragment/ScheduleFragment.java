package com.samsung.bookm.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.samsung.bookm.Activity.AddReminderActivity;
import com.samsung.bookm.Activity.SearchActivity;
import com.samsung.bookm.Adapter.ReminderAdapter;
import com.samsung.bookm.Data.ReminderDatabase;
import com.samsung.bookm.Model.Reminder;
import com.samsung.bookm.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    Toolbar toolbar;
    RecyclerView rv_schedule;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        toolbar = v.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.schedule_menu);
        toolbar.setTitle(R.string.title_schedule_activity);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_schedule = view.findViewById(R.id.rv_schedule);
        LinearLayoutManager ll = new LinearLayoutManager(getContext());
        ll.setOrientation(RecyclerView.VERTICAL);
        rv_schedule.setLayoutManager(ll);
        ReminderAdapter adapter = new ReminderAdapter(getData(), getContext());
        rv_schedule.setAdapter(adapter);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_add:
                        Intent intent = new Intent(getActivity(), AddReminderActivity.class);
                        startActivity(intent);
                        return true;
                    case  R.id.action_more:
                        return true;
                    default:
                        return ScheduleFragment.super.onOptionsItemSelected(item);
                }
            }
        });
    }

    public ArrayList<Reminder> getData(){

        ArrayList<Reminder> arr = (ArrayList<Reminder>) ReminderDatabase.getInstance(getContext()).getAllReminders();
        return arr;
    }

    @Override
    public void onResume() {
        super.onResume();
        ReminderAdapter adapter = new ReminderAdapter(getData(), getContext());
        rv_schedule.setAdapter(adapter);
    }
}
