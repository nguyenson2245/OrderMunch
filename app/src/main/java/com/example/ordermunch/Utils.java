package com.example.ordermunch;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Utils {

    public static final String PATH_ASSET =  "file:///android_asset/";
    public static void setupVerticalRecyclerView(Context context, RecyclerView mRecyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setClipToPadding(false);
        mRecyclerView.setHasFixedSize(true);
    }
}
