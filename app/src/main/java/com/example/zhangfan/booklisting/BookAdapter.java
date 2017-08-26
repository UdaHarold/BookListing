package com.example.zhangfan.booklisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangfan.booklisting.utilities.Book;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * Created by Harold on 2017/8/26.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<Book> mBookData;

    public BookAdapter() {
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.mTitleTextView.setText(mBookData.get(position).getTitle());
        ArrayList<String> authors = mBookData.get(position).getAuthor();
        if (authors != null && authors.size() > 0) {
            String authorText = "";
            for (String author : authors) {
                authorText += author;
                authorText += "\t";
            }
            holder.mAuthorTextView.setText(authorText);
        }

    }

    @Override
    public int getItemCount() {
        if (mBookData != null) return mBookData.size();
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleTextView;
        public final TextView mAuthorTextView;

        public BookViewHolder(View view) {
            super(view);
            mTitleTextView = (TextView) view.findViewById(R.id.book_title);
            mAuthorTextView = (TextView) view.findViewById(R.id.book_authors);
        }
    }

    public void setBookData(ArrayList<Book> books) {
        mBookData = books;
        notifyDataSetChanged();
    }
}
