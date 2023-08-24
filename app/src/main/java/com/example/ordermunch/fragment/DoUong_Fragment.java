package com.example.ordermunch.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordermunch.R;
import com.example.ordermunch.Utils;
import com.example.ordermunch.activity.ShowDL_DoUong_Activity;
import com.example.ordermunch.activity.ShowDL_MonAn_Activity;
import com.example.ordermunch.adapter.DoUongAdapter;
import com.example.ordermunch.adapter.MonAnAdapter;
import com.example.ordermunch.itf.ICallBack;
import com.example.ordermunch.itf.IFragmentParent;
import com.example.ordermunch.model.DoUongModel;
import com.example.ordermunch.model.MonAnModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DoUong_Fragment extends Fragment implements IFragmentParent {

//    View view;
//
//    private static final String DB_PATH_SUFFIX = "/databases/";
//    private SQLiteDatabase database = null;
//    private static final String DATABASE_NAME = "sqlite91.db";
//
//    private ListView lv;
//    private ArrayList<String> mylist;
//    private ArrayAdapter<String> myadapter;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_do_uong, container, false);
//
//        lv = view.findViewById(R.id.lv_douong);
//        mylist = new ArrayList<>();
//        myadapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, mylist);
//        lv.setAdapter(myadapter);
//
//        processCopy();
//        database = requireActivity().openOrCreateDatabase("sqlite91.db", Context.MODE_PRIVATE, null);
//
//        Cursor c = database.query("drinks", null, null, null, null, null, null, null);
//        String data = "";
//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            data = c.getString(0) + "\n" + c.getString(1) + "\n" ;
//            mylist.add(data);
//            c.moveToNext();
//        }
//        c.close();
//        myadapter.notifyDataSetChanged();
//
//        return view;
//    }
//
//    private void processCopy() {
//        File dbFile = requireActivity().getDatabasePath(DATABASE_NAME);
//        if (!dbFile.exists()) {
//            try {
//                CopyDataBaseFromAsset();
//                Toast.makeText(requireContext(), "Copying success from Assets folder", Toast.LENGTH_LONG).show();
//            } catch (Exception e) {
//                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    private String getDatabasePath() {
//        return requireActivity().getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
//    }
//
//    private void CopyDataBaseFromAsset() {
//        try {
//            InputStream myInput = requireContext().getAssets().open(DATABASE_NAME);
//            String outFileName = getDatabasePath();
//
//            File f = new File(requireActivity().getApplicationInfo().dataDir + DB_PATH_SUFFIX);
//            if (!f.exists())
//                f.mkdir();
//
//            OutputStream myOutput = new FileOutputStream(outFileName);
//
//            int size = myInput.available();
//            byte[] buffer = new byte[size];
//            myInput.read(buffer);
//            myOutput.write(buffer);
//
//            myOutput.flush();
//            myOutput.close();
//            myInput.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//

    View view;

    private DoUongAdapter doUongAdapter;
    private ArrayList<DoUongModel> dataModels = new ArrayList<>();
    private RecyclerView rvDouong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_do_uong, container, false);

        rvDouong = view.findViewById(R.id.rcv_doUong);
        setupUI();
        initData();

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initData() {
        SQLiteDatabase database = requireActivity().openOrCreateDatabase("sqlite91.db", Context.MODE_PRIVATE, null);
        Cursor c = database.query("drinks", null, null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            DoUongModel dataModel = new DoUongModel();
            dataModel.setId(c.getString(0));
            dataModel.setName(c.getString(1));
            dataModel.setImage(c.getString(2).replace("https://cdn.cet.edu.vn", "https://cet.edu.vn"));
            dataModel.setDescription(c.getString(4).replace("https://cdn.cet.edu.vn", "https://cet.edu.vn"));
            dataModels.add(dataModel);
            c.moveToNext();
        }
        c.close();

        if (doUongAdapter != null) {
            doUongAdapter.notifyDataSetChanged();
        }
    }

    private void setupUI() {
        Utils.setupVerticalRecyclerView(getContext(), rvDouong);
        doUongAdapter = new DoUongAdapter(dataModels, new ICallBack() {
            @Override
            public void onClickImage(String title, String path, String des) {
                Intent pIntent = new Intent(getContext(), ShowDL_DoUong_Activity.class);
                Bundle extras = new Bundle();
                extras.putString(ShowDL_DoUong_Activity.PATH_TITLE_2, title);
                extras.putString(ShowDL_DoUong_Activity.PATH_IMAGE2, path);
                extras.putString(ShowDL_DoUong_Activity.PATH_DES2, des);
                pIntent.putExtras(extras);
                startActivity(pIntent);
            }
        });
        rvDouong.setAdapter(doUongAdapter);
    }

    @Override
    public List<File> getDataFiles() {
        return null;
    }

    @Override
    public void updateListSort(ArrayList<File> listSort) {

    }
}