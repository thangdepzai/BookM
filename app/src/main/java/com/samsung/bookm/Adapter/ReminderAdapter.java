package com.samsung.bookm.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.samsung.bookm.Activity.EditReminderActivity;
import com.samsung.bookm.Data.ReminderDatabase;
import com.samsung.bookm.Model.AppDatabase;
import com.samsung.bookm.Model.Book;
import com.samsung.bookm.Model.Reminder;
import com.samsung.bookm.R;
import com.samsung.bookm.Receiver.AlarmReceiver;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderHolder> {
    ArrayList<Reminder>  arr;
    Context context;

    public ReminderAdapter(ArrayList<Reminder> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public ReminderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_schedule, parent, false);
        return new ReminderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReminderHolder holder, final int position) {
        holder.txt_title.setText(arr.get(position).getmTitle());
        holder.txt_date.setText(arr.get(position).getmDate()+" "+arr.get(position).getmTime());

        if(arr.get(position).getmRepeat().equals("true")){
            holder.txt_mode_repeat.setText("Every " + arr.get(position).getmRepeatNo() + " " + arr.get(position).getmRepeatType() + "(s)");
        }else if (arr.get(position).getmRepeat().equals("false")) {
            holder.txt_mode_repeat.setText("Repeat Off");
        }

        Book book = AppDatabase.getInstance(context).getBookById(arr.get(position).getmBookId());
        holder.img_book_cover.setImageURI(Uri.parse(book.getImgPath()));
        holder.progress_bar.setProgress((int)book.getLastRecentPage()/book.getNumPage());
        if(arr.get(position).getmActive().equals("true")){
            holder.fab_notifi_on.setVisibility(View.GONE);
            holder.fab_notifi_off.setVisibility(View.VISIBLE);
        }else{
            holder.fab_notifi_off.setVisibility(View.GONE);
            holder.fab_notifi_on.setVisibility(View.VISIBLE);
        }

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditReminderActivity.class);
                i.putExtra(EditReminderActivity.EXTRA_REMINDER_ID, arr.get(position).getmID());
                context.startActivity(i);
            }
        });
        holder.cv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                // xoa database
                                ReminderDatabase db = new ReminderDatabase(context);
                                db.deleteReminder(arr.get(position));
                                //cap nhat giao dien
                                arr.remove(position);
                                holder.alarmReceiver.cancelAlarm(context, arr.get(position).getmID());
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, arr.size());
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure to delete ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return arr!=null?arr.size():0;
    }


    public class ReminderHolder extends RecyclerView.ViewHolder {
        ImageView img_book_cover;
        TextView txt_title, txt_on_page, txt_date, txt_mode_repeat;
        ProgressBar progress_bar;
        FloatingActionButton fab_notifi_on,fab_notifi_off ;
        CardView cv;
        AlarmReceiver  alarmReceiver;
        public ReminderHolder(@NonNull View itemView) {
            super(itemView);
            img_book_cover = itemView.findViewById(R.id.img_book_cover);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_on_page = itemView.findViewById(R.id.txt_on_page);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_mode_repeat = itemView.findViewById(R.id.txt_mode_repeat);
            progress_bar = itemView.findViewById(R.id.progess_bar);
            fab_notifi_on = itemView.findViewById(R.id.fab_notifi_on);
            fab_notifi_off = itemView.findViewById(R.id.fab_notifi_on);
            cv = itemView.findViewById(R.id.cv);
            alarmReceiver = new AlarmReceiver();
        }
    }
}
