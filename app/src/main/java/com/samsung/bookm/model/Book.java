package com.samsung.bookm.Model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String name;
    private int genreId;
    private String genre;
    private String author;
    private String bookPath;
    private String imgPath;
    private int numPage;
    private int lastRecentPage;

    public int getTotalReadTime() {
        return totalReadTime;
    }

    public void setTotalReadTime(int totalReadTime) {
        this.totalReadTime = totalReadTime;
    }

    public int getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(int lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    private int totalReadTime;
    private int lastReadTime;
    private  int idImage;

    public Book() {}

    public Book(String name, int genreId, String author, String bookPath, String imgPath, int numPage, int lastRecentPage) {
        this.name = name;
        this.genreId = genreId;
        this.author = author;
        this.bookPath = bookPath;
        this.imgPath = imgPath;
        this.numPage = numPage;
        this.lastRecentPage = lastRecentPage;
    }

    public Book(int id, String name, int idImage) {
        this.id = id;
        this.name = name;
        this.idImage = idImage;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }

    public int getLastRecentPage() {
        return lastRecentPage;
    }

    public void setLastRecentPage(int lastRecentPage) {
        this.lastRecentPage = lastRecentPage;
    }


}
