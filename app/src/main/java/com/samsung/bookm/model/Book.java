package com.samsung.bookm.Model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String name;
    private int genreId;
    private int authorId;
    private String genre;
    private String author;
    private String bookPath;
    private String imgPath;
    private int numPage;
    private int lastRecentPage;

    public Book() {}

    public Book(String name, int genreId, int authorId, String bookPath, String imgPath, int numPage, int lastRecentPage) {
        this.name = name;
        this.genreId = genreId;
        this.authorId = authorId;
        this.bookPath = bookPath;
        this.imgPath = imgPath;
        this.numPage = numPage;
        this.lastRecentPage = lastRecentPage;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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
