package com.example.lxx.myalipay.ui.staff.query.ui.position7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.adapter.BedFragmentAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * created by lxx at 2019/12/3 15:25
 * 描述:
 */
public class StayFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.tab)
    SegmentTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    private String titles,session;

    private String[] mTitles = {"D栋","小别墅"};
    List<Fragment> fragments = new ArrayList<>();


    public static StayFragment newInstance(String title) {
        StayFragment fragment = new StayFragment();
        fragment.titles = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_stay;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(titles);
        session = (String) MyApplication.get(_mActivity, Session, "");
        tlTab();
    }

    private void tlTab() {
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        vp.setCurrentItem(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        tab.setTabData(mTitles);
        vp.setAdapter(new BedFragmentAdapter(getChildFragmentManager(),session,mTitles));
    }
}
