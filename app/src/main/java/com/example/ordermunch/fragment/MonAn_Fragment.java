package com.example.ordermunch.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordermunch.R;
import com.example.ordermunch.Utils;
import com.example.ordermunch.activity.ShowDL_MonAn_Activity;
import com.example.ordermunch.adapter.MonAnAdapter;
import com.example.ordermunch.itf.ICallBack;
import com.example.ordermunch.itf.IFragmentParent;
import com.example.ordermunch.model.MonAnModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MonAn_Fragment extends Fragment implements IFragmentParent {

    private MonAnAdapter monAnAdapter;
    private ArrayList<MonAnModel> dataModels = new ArrayList<>();
    private RecyclerView rvMonAn;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mon_an, container, false);

        rvMonAn = view.findViewById(R.id.rcv_monan);
        setupUI();
        initData();

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initData() {
        SQLiteDatabase database = requireActivity().openOrCreateDatabase("sqlite91.db", Context.MODE_PRIVATE, null);
        Cursor c = database.query("dishes_v2", null, null, null, null, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            MonAnModel dataModel = new MonAnModel();
            dataModel.setName(c.getString(1));
            dataModel.setImage(c.getString(2).replace("https://cdn.cet.edu.vn", "https://cet.edu.vn"));
            dataModel.setDescription(c.getString(4).replace("https://cdn.cet.edu.vn", "https://cet.edu.vn"));
            dataModels.add(dataModel);
            c.moveToNext();
        }
        c.close();

        if (monAnAdapter != null) {
            monAnAdapter.notifyDataSetChanged();
        }
    }

    private void setupUI() {
        Utils.setupVerticalRecyclerView(getContext(), rvMonAn);
        monAnAdapter = new MonAnAdapter(dataModels, new ICallBack() {
            @Override
            public void onClickImage(String title, String path, String des) {
                Intent pIntent = new Intent(getContext(), ShowDL_MonAn_Activity.class);
                Bundle extras = new Bundle();
                extras.putString(ShowDL_MonAn_Activity.PATH_TITLE, title);
                Log.d("TAG", "onClickImage:" + title);
                extras.putString(ShowDL_MonAn_Activity.PATH_IMAGE, path);
                extras.putString(ShowDL_MonAn_Activity.PATH_DES, des);
                pIntent.putExtras(extras);
                startActivity(pIntent);
            }
        });
        rvMonAn.setAdapter(monAnAdapter);
    }


    @Override
    public List<File> getDataFiles() {
        return null;
    }

    @Override
    public void updateListSort(ArrayList<File> listSort) {

    }
}