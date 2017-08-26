package com.example.zhangfan.booklisting.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Harold on 2017/8/23.
 */

public class OpenBookJsonUtils {

    public static ArrayList<Book> getBookListFromJson(String bookJsonStr)
            throws JSONException {

        final String BOOK_LIST = "books";
        final String BOOK_TITLE = "title";
        final String BOOK_AUTHOR = "author";

        //book list result max 20 items
        ArrayList<Book> bookList = new ArrayList<>(20);

        JSONObject rootJson = new JSONObject(bookJsonStr);

        // get all books by key books
        JSONArray bookArrayJson = rootJson.getJSONArray(BOOK_LIST);

        if (bookArrayJson != null) {
            Book book = null;
            String title = "";
            ArrayList<String> authors = null;
            for (int i = 0; i < bookArrayJson.length(); i++) {
                JSONObject bookJson = bookArrayJson.getJSONObject(i);
                title = bookJson.getString(BOOK_TITLE);
                authors = new ArrayList<>(10);
                JSONArray authorArrayJson = bookJson.getJSONArray(BOOK_AUTHOR);
                if (authorArrayJson != null && authorArrayJson.length() > 0) {
                    for (int j = 0; j < authorArrayJson.length(); j++) {
                        authors.add(authorArrayJson.getString(j));
                    }
                }
                book = new Book(title, authors);
                bookList.add(book);
            }
        }

        return bookList;
    }

}
