package com.example.ordermunch.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ordermunch.DatabaseHelper;
import com.example.ordermunch.R;
import com.example.ordermunch.fragment.CuaHangFragment;
import com.example.ordermunch.fragment.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DatabaseHelper databaseHelper;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNav();
        databaseHelper_m();

        bottomNavigationView.setSelectedItemId(R.id.trangChu);

    }

    private void databaseHelper_m() {
        databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.createDatabase();
            databaseHelper.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseHelper.close();
    }

    private void BottomNav() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.trangChu) {
                    FragmentHome fragmentHome = new FragmentHome();
                    replaceFragment(fragmentHome);
                } else if (id == R.id.cuaHang) {
                    CuaHangFragment cuaHangFragment = new CuaHangFragment();
                    replaceFragment(cuaHangFragment);
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment newFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}