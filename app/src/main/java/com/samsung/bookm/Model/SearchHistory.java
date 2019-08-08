package com.samsung.bookm.Model;

public class SearchHistory {
    int id;

    public SearchHistory(int id, String text, long time) {
        this.id = id;
        this.text = text;
        this.time = time;
    }

    String text;
    long time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SearchHistory() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public SearchHistory(String text, long time) {
        this.text = text;
        this.time = time;
    }


}
