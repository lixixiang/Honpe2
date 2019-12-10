package com.example.lxx.myalipay.ui.staff.query.ui.position16.Fragment3Priase.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.Fragment3Priase.child.FragmentChild;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.Fragment3Priase.child.FragmentChild2;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.Fragment3Priase.child.FragmentChild3;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean3;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.callback.CallBackChildFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.Fragment3Priase
 * 作者: lxx
 * 日期: 2019/1/20 22:37
 * 描述:
 */
public class FragmentPraise extends BaseFragment {
    @BindView(R.id.rb_good)
    RadioButton rbGood;
    @BindView(R.id.rb_normal)
    RadioButton rbNormal;
    @BindView(R.id.rb_bad)
    RadioButton rbBad;
    @BindView(R.id.group)
    RadioGroup group;

    StatisticsBean3.DataBean bean = new StatisticsBean3.DataBean();
    @BindView(R.id.et_appraise)
    EditText etAppraise;
    @BindView(R.id.tv_comment_time)
    TextView tvCommentTime;
    @BindView(R.id.tv_excellence_rate)
    TextView tvExcellenceRate;
    CallBackChildFragment commitCallback;

    private String[] praise = {"优","良","差"};

    @Override
    public void onAttach(Activity activity) {
        commitCallback = (CallBackChildFragment) activity;
        super.onAttach(activity);
    }

    private int pos;

    public static FragmentPraise newInstance(StatisticsBean3.DataBean bean) {
        FragmentPraise fragmentPriase = new FragmentPraise();
        fragmentPriase.bean = bean;
        return fragmentPriase;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(CommitMeatBean event) {
        if (event != null) {
            commitCallback.callBack(event);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_priase;
    }


    @Override
    protected void initView() {
        rbGood.setText("很好");
        rbNormal.setText("一般");
        rbBad.setText("很差");
        tvCommentTime.setText(bean.getCommentSum() + "");
        tvExcellenceRate.setText(bean.getExcellenceRate() + "%");

        //initFragment();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int index = 0; index < group.getChildCount(); index++) {
                    RadioButton rb = (RadioButton) group.getChildAt(index);
                    if (rb.isChecked()) {
                        pos = index;
                        setIndexSelected(index);
                        break;
                    }
                }
            }
        });

        etAppraise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (pos == 0) {
                        EventBus.getDefault().post(new CommitMeatBean(bean.getMealDate(), bean.getMealTimes(), "优", etAppraise.getText().toString()));
                    } else if (pos == 1) {
                        EventBus.getDefault().post(new CommitMeatBean(bean.getMealDate(), bean.getMealTimes(), "良", etAppraise.getText().toString()));
                    } else {
                        EventBus.getDefault().post(new CommitMeatBean(bean.getMealDate(), bean.getMealTimes(), "差", etAppraise.getText().toString()));
                    }
                }
            }
        });
    }

    //方法二,默认第一fragment
    private void setIndexSelected(int index) {
        switch (index) {
            case 0:
                changeFragment(FragmentChild.newInstance(bean.getDetails()));
                break;
            case 1:
                changeFragment(FragmentChild2.newInstance(bean.getDetails()));
                break;
            case 2:
                changeFragment(FragmentChild3.newInstance(bean.getDetails()));
                break;
        }
    }

    private void changeFragment(Fragment fm) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.child_fragment, fm);
        ft.commit();
    }

    public static class CommitMeatBean {
        private String MealDate;
        private String MealTime;
        private String Score;
        private String Content;

        public CommitMeatBean() {
        }

        public CommitMeatBean(String mealDate, String mealTime, String score, String content) {
            MealDate = mealDate;
            MealTime = mealTime;
            Score = score;
            Content = content;
        }


        public String getMealDate() {
            return MealDate;
        }

        public void setMealDate(String mealDate) {
            MealDate = mealDate;
        }

        public String getMealTime() {
            return MealTime;
        }

        public void setMealTime(String mealTime) {
            MealTime = mealTime;
        }

        public String getScore() {
            return Score;
        }

        public void setScore(String score) {
            Score = score;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }
    }
}
