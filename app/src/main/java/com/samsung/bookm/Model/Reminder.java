package com.samsung.bookm.Model;

public class Reminder {
    private int mID; // id
    private String mTitle; // title
    private String mDate; // Ngay doc
    private String mTime; // thoi gian doc 9 9:30...
    private String mRepeat; // che do lap
    private String mRepeatNo; // so lan lap
    private String mRepeatType; // kieu lap theo ngay / gio /.....
    private String mActive; // lap hay khong

    public Reminder(int mID, String mTitle, String mDate, String mTime, String mRepeat, String mRepeatNo, String mRepeatType, String mActive) {
        this.mID = mID;
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mRepeat = mRepeat;
        this.mRepeatNo = mRepeatNo;
        this.mRepeatType = mRepeatType;
        this.mActive = mActive;
    }

    public Reminder(String mTitle, String mDate, String mTime, String mRepeat, String mRepeatNo, String mRepeatType, String mActive) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mRepeat = mRepeat;
        this.mRepeatNo = mRepeatNo;
        this.mRepeatType = mRepeatType;
        this.mActive = mActive;
    }

    public Reminder() {
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmRepeat() {
        return mRepeat;
    }

    public void setmRepeat(String mRepeat) {
        this.mRepeat = mRepeat;
    }

    public String getmRepeatNo() {
        return mRepeatNo;
    }

    public void setmRepeatNo(String mRepeatNo) {
        this.mRepeatNo = mRepeatNo;
    }

    public String getmRepeatType() {
        return mRepeatType;
    }

    public void setmRepeatType(String mRepeatType) {
        this.mRepeatType = mRepeatType;
    }

    public String getmActive() {
        return mActive;
    }

    public void setmActive(String mActive) {
        this.mActive = mActive;
    }
}
