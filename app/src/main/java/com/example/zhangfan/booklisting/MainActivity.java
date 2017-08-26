package com.example.zhangfan.booklisting;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zhangfan.booklisting.utilities.Book;
import com.example.zhangfan.booklisting.utilities.NetworkUtils;
import com.example.zhangfan.booklisting.utilities.OpenBookJsonUtils;

import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    private RecyclerView mRecyclerView;
    private BookAdapter mBookAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    private static final int BOOK_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.book_list);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mBookAdapter = new BookAdapter();
        mRecyclerView.setAdapter(mBookAdapter);

        getSupportLoaderManager().initLoader(BOOK_LOADER_ID, null, this);
    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<ArrayList<Book>>(this) {

            @Override
            protected void onStartLoading() {
                mLoadingIndicator.setVisibility(View.VISIBLE);
                forceLoad();
            }

            @Override
            public ArrayList<Book> loadInBackground() {
                URL bookURL = NetworkUtils.buildUrl("Android编程");

                try {
                    String jsonResult = NetworkUtils.getResponseFromHttpUrl(bookURL);
                    ArrayList<Book> books = OpenBookJsonUtils.getBookListFromJson(jsonResult);
                    return books;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mBookAdapter.setBookData(data);
        if (null == data) {
            showErrorMessage();
        } else {
            showBookDataView();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {

    }

    private void showBookDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }
}
