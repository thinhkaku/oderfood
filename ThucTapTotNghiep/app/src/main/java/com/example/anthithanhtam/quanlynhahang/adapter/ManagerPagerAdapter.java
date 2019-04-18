package com.example.anthithanhtam.quanlynhahang.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

public class ManagerPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment>listFragment= new ArrayList<>();
    private ArrayList<Integer>arrIcon=new ArrayList<>();
    public ManagerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, Integer icon){
        listFragment.add(fragment);
        arrIcon.add(icon);
    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

}
