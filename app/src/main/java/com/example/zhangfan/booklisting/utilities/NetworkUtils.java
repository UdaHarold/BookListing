package com.example.zhangfan.booklisting.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by Harold on 2017/8/22.
 */

public class NetworkUtils {

    private static final String BOOK_URL = "https://api.douban.com/v2/book/search";
    private static final String QUERY_PARAM = "q";
    private static final String FIELDS = "fields";


    public static URL buildUrl(String bookName) {
        Uri builtUri = Uri.parse(BOOK_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, bookName)
                .appendQueryParameter(FIELDS, "title,author")
                .build();

        URL url = null;
        try {
            String afterDecode = URLDecoder.decode(builtUri.toString(), "UTF-8");
            url = new URL(afterDecode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
