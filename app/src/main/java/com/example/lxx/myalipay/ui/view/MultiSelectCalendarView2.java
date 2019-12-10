package com.example.lxx.myalipay.ui.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.adapter.CalendarAdapter;
import com.example.mcalendar.Calendar;
import com.example.mcalendar.CalendarView;

import butterknife.BindView;

public class MultiSelectCalendarView2 extends BaseActivity implements CalendarView.OnCalendarSelectListener {
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
    String StartTime;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_multi_select_calendar;
    }
    @Override
    public void initView() {
        ivHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

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
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay());
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}

