package com.example.lxx.myalipay.ui.fragment.b_package.setting.feedBack;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.utils.StringUtils;

import butterknife.BindView;

/**
 * created by lxx at 2019/11/23 17:21
 * 描述:
 */
public class FeedBackFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.et_describe)
    EditText etDescribe;
    @BindView(R.id.btn_up_icon_5)
    Button btnUpIcon5;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_phone)
    EditText etPhone;

    private String title;
    public static FeedBackFragment newInstance(String title) {
        FeedBackFragment fragment = new FeedBackFragment();
        fragment.title = title;
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(title);
        StringUtils.HintUtil(etDescribe,getString(R.string.hint_feed_back));
        StringUtils.HintUtil(etEmail,getString(R.string.feedback_email));
        StringUtils.HintUtil(etPhone,getString(R.string.feedback_phone_no_hint));
        etDescribe.setHintTextColor(getResources().getColor(R.color.light_blue));
        etEmail.setHintTextColor(getResources().getColor(R.color.light_blue));
        etPhone.setHintTextColor(getResources().getColor(R.color.light_blue));
    }
}






















