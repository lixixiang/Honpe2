package com.example.lxx.myalipay.ui.staff.query.ui.position7.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lxx.myalipay.ui.staff.query.ui.position7.fragment.VpStayFragment;


public class BedFragmentAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private String session;

    public BedFragmentAdapter(FragmentManager fm, String session, String... titles) {
        super(fm);
        this.session = session;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return VpStayFragment.newInstance(mTitles[position], session);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
