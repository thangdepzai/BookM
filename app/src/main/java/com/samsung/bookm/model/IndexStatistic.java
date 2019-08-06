package com.samsung.bookm.Model;

public class IndexStatistic {
    double index;
    String title;

    public IndexStatistic(double index, String title) {
        this.index = index;
        this.title = title;
    }

    public double getIndex() {
        return index;
    }

    public void setIndex(double index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
