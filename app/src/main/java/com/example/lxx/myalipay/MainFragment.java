package com.example.lxx.myalipay;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lxx.myalipay.base.BaseMainFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.HomeFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.MeFragment;
import com.example.lxx.myalipay.ui.view.BottomBar;
import com.example.lxx.myalipay.ui.view.BottomBarTab;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.Utils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusActivityScope;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.utils.event.TabSelectedEvent;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

import static com.example.lxx.myalipay.api.FinalClass.FIRST;
import static com.example.lxx.myalipay.api.FinalClass.SECOND;
import static com.example.lxx.myalipay.api.FinalClass.video_status;


/**
 * created by lxx at 2019/11/11 9:48
 * 描述:
 */
public class MainFragment extends BaseMainFragment {
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    private SupportFragment[] mFragments = new SupportFragment[2];
    private float bottombarHeight;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView() {

        bottomBar.addItem(new BottomBarTab(_mActivity, R.mipmap.bottom1, getString(R.string.text_home)))
                .addItem(new BottomBarTab(_mActivity, R.mipmap.bottom2, getString(R.string.text_me)));

        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
                bottomBar.setCurrentItem(position);
                Event<Integer> event2 = new Event<Integer>(video_status,0);
                EventBusUtil.sendEvent(event2);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
                EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });

        LatteLogger.d("bottomBarHeight",Utils.getRealHeight(bottomBar));
        bottombarHeight = Utils.getRealHeight(bottomBar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance(bottombarHeight);
            mFragments[SECOND] = MeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_main_fragment_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(MeFragment.class);
        }
    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}
















