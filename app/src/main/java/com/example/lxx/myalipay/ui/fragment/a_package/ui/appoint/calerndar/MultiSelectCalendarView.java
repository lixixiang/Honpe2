package com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.calerndar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.adapter.CalendarAdapter;
import com.example.mcalendar.Calendar;
import com.example.mcalendar.CalendarView;


import java.text.SimpleDateFormat;

import butterknife.BindView;

/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child
 * @date: 2018/10/17 9:54
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：员工点餐复选日历
 */
public class MultiSelectCalendarView extends BaseBackFragment implements CalendarView.OnCalendarSelectListener {
    @BindView(R.id.iv_homeBack)
    ImageView ivHomeBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_lunar)
    TextView tvLunar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private String StartTime;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    public static MultiSelectCalendarView newInstance() {
        MultiSelectCalendarView fragment = new MultiSelectCalendarView();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_multi_select_calendar;
    }


    @Override
    protected void initView() {
        ivHomeBack.setColorFilter(getResources().getColor(R.color.black));

        ivHomeBack.setOnClickListener(v -> _mActivity.onBackPressed());

        tvTitle.setText(calendarView.getCurYear() + "年" + calendarView.getCurMonth() + "月");
        calendarView.setOnCalendarSelectListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setAdapter(new CalendarAdapter(_mActivity));
        fab.setOnClickListener(new View.OnClickListener() { //返回到当天
            @Override
            public void onClick(View v) {
                calendarView.scrollToCurrent(true);
            }
        });
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {
    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        tvTitle.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        tvYear.setText(calendar.getYear() + "年");
        tvLunar.setText(calendar.getLunar());
        if (isClick) {
            StartTime = calendar.getYear()+"-" + calendar.getMonth() + "-" + calendar.getDay();
            Bundle bundle = new Bundle();
            bundle.putString("result",StartTime);
            setFragmentResult(RESULT_OK, bundle);
            _mActivity.onBackPressed();
        }
    }
}

