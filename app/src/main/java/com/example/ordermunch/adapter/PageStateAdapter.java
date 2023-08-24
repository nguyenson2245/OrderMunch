package com.example.ordermunch.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ordermunch.itf.IFragmentParent;

import java.util.ArrayList;
import java.util.List;

public class PageStateAdapter extends FragmentStateAdapter {
    private final List<Fragment> screens = new ArrayList<>();

    public PageStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addScreen(List<Fragment> fragments) {
        screens.clear();
        screens.addAll(fragments);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (screens.size() > 0) {
            return screens.get(position);
        } else {
            return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return screens.size();
    }

    public IFragmentParent getFragmentByPosition(int position){
        if (!screens.isEmpty()){
            return (IFragmentParent) screens.get(position);
        } else {
            return null;
        }
    }
}
