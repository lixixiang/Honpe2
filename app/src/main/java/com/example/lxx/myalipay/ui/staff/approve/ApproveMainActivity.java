package com.example.lxx.myalipay.ui.staff.approve;

import android.os.Bundle;
import android.widget.FrameLayout;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApproveMainActivity extends BaseActivity {


    @BindView(R.id.fl_main_fragment_container)
    FrameLayout flMainFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        if (findFragment(ApproveFragment.class) == null) {
            loadRootFragment(R.id.fl_main_fragment_container, ApproveFragment.newInstance());
        }
    }
}
