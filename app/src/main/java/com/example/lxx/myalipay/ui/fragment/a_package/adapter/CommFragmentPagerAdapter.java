package com.example.lxx.myalipay.ui.fragment.a_package.adapter;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;


import java.lang.annotation.Retention;
import java.util.List;

import me.jessyan.autosize.utils.LogUtils;

/**
 * created by lxx at 2019/11/12 10:00
 * 描述:
 */
public class CommFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;
    private String[] mTitles;

    public CommFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        mFragmentList = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


    @Override
    public int getItemPosition(Object object) {
        LogUtils.d("getItemPosition");
        return PagerAdapter.POSITION_NONE;
    }
}

























