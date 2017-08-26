package com.example.zhangfan.booklisting.utilities;

import java.util.ArrayList;

/**
 * Created by Harold on 2017/8/22.
 */

public class Book {
    private String title;
    private ArrayList<String> author;

    public Book(String bookTitle, ArrayList<String> bookAuthors) {
        title = bookTitle;
        author = bookAuthors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }


}
