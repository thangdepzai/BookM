package com.samsung.bookm.model;

public class Book {
    private String mId;
    private String mName;
    private int mIdImage;

    public Book(String mId, String mName, int mIdImage) {
        this.mId = mId;
        this.mName = mName;
        this.mIdImage = mIdImage;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmIdImage() {
        return mIdImage;
    }

    public void setmIdImage(int mIdImage) {
        this.mIdImage = mIdImage;
    }
}
