package com.samsung.bookm.model;

public class Book {
    private String name;
    private String bookPath;
    private String imgPath;

    public Book(String name, String bookPath, String imgPath) {
        this.name = name;
        this.bookPath = bookPath;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}