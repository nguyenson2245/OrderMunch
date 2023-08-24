package com.example.ordermunch.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ordermunch.DatabaseHelper;
import com.example.ordermunch.R;
import com.example.ordermunch.adapter.PageStateAdapter;
import com.example.ordermunch.itf.IFragmentParent;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    PageStateAdapter pageStateAdapter;
    private IFragmentParent currentFragment = null;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();
        intiTabLayout();
        return view;
    }

    private void initView() {
        tabLayout = view.findViewById(R.id.tab_layout_home);
        viewPager2 = view.findViewById(R.id.viewpager2_home);
    }

    private void intiTabLayout() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> screens = new ArrayList<>();

        screens.add(new MonAn_Fragment());
        screens.add(new DoUong_Fragment());

        pageStateAdapter = new PageStateAdapter(fragmentManager, getLifecycle());
        pageStateAdapter.addScreen(screens);

        currentFragment = pageStateAdapter.getFragmentByPosition(0);

        viewPager2.setAdapter(pageStateAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Món Ăn"));
        tabLayout.addTab(tabLayout.newTab().setText("Đồ Uống"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                currentFragment = pageStateAdapter.getFragmentByPosition(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

}