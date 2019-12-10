package com.example.lxx.myalipay.ui.staff.query.ui.position1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.adapter.CheckInAdapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.adapter.TotalQueryAdapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.bean.CheckInBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.bean.EventBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.RxDialogEditSureCancel;
import com.example.lxx.myalipay.widget.font.FontTextView4;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * created by lxx at 2019/12/3 9:54
 * 描述:
 */
public class TotalQueryFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.ll_click)
    LinearLayout llClick;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;


    private TotalQueryAdapter adapter;
    private CheckInAdapter checkInAdapter;
    private String session, firstDay, lastDay, curMonth, curDay, today;
    List<String> mDateList = new ArrayList<>();
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    private String InputUserNos = "";
    private LinearLayoutManager linearLayoutManager;
    private int mIndex;

    public static TotalQueryFragment newInstance() {
        TotalQueryFragment fragment = new TotalQueryFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_total_query;
    }

    @Override
    protected void initView() {
        llDate.setVisibility(View.VISIBLE);
        initToolbarNav(llBack);
        tvTitle.setText("考勤");
        tvEnd.setText("查找");
        tvEnd.setVisibility(View.VISIBLE);
        session = (String) MyApplication.get(_mActivity, FinalClass.Session, "");
        linearLayoutManager = new LinearLayoutManager(_mActivity);

        firstDay = DateUtils.getFristDayOfMonth(format);
        lastDay = DateUtils.getLastDayOfMonth(format);
        mDateList = DateUtils.queryData(format, firstDay, lastDay);
        LatteLogger.d("day", firstDay + "  " + lastDay);
        today = format.format(new Date());
        curMonth = sf.format(new Date());
        tvDate.setText(curMonth);
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setTextColor(getResources().getColor(R.color.grey_home));
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setTextColor(getResources().getColor(R.color.white));
            btnNextPager.setClickable(true);
        }
        refreshLayout.setOnRefreshListener(this);
        for (int i = 0; i < mDateList.size(); i++) {
            if (today.equals(mDateList.get(i))) {
                mIndex = i;
                break;
            }
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getTestNote(InputUserNos, firstDay, lastDay);
    }

    private void getTestNote(String myNo, String firstDay, String lastDay) {//"2495"
        EasyHttp.post(Constants.PERSON_CHECK + session)
                .params("EmpNo", myNo)
                .params("StarDay", firstDay)
                .params("EndDay", lastDay)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity, 0);
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                start(LoginFragment.newInstance());
                                ToastUtils.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                final CheckInBean checkInBean = Convert.fromJson(s, CheckInBean.class);
                                LatteLogger.d("getTestNote", GsonBuildUtil.GsonBuilder(checkInBean));
                                if (checkInBean.getStatus() == 0) {
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    recyclerView.scrollToPosition(mIndex);
                                    checkInAdapter = new CheckInAdapter(checkInBean.getData());
                                    recyclerView.setAdapter(checkInAdapter);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public int oneDay = 0; //这个初始日子

    public int getOneDay() {
        String countStr = tvDate.getText().toString().trim();
        if (countStr != null) {
            oneDay = Integer.valueOf(countStr);
        }
        return oneDay;
    }

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtils.getFristDayOfMonth(format, oneDay);
        lastDay = DateUtils.getLastDayOfMonth(format, oneDay);
        mDateList = DateUtils.queryData(format, firstDay, lastDay);
        LatteLogger.d("monday", firstDay + " 某月第一天 " + lastDay + "某月最后一天");
        tvDate.setText(DateUtils.getLastMonth(oneDay));
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setTextColor(getResources().getColor(R.color.grey_home));
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setTextColor(getResources().getColor(R.color.white));
            btnNextPager.setClickable(true);
        }
        getTestNote(InputUserNos, firstDay, lastDay);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
                EventBean InputUserNo = (EventBean) event.getData();
                InputUserNos =InputUserNo.getContent();
                getTestNote(InputUserNo.getContent(), firstDay, lastDay);
                break;
            case FinalClass.ME_info:
                getTestNote(InputUserNos, firstDay, lastDay);
                break;
        }
    }

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                final RxDialogEditSureCancel editDialog = new RxDialogEditSureCancel(_mActivity,InputUserNos, "考勤查询", "输入员工工号:", "请输入");
                editDialog.setCanceledOnTouchOutside(true);
                editDialog.show();
                break;
            case R.id.btn_up_pager:
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneMonth(oneDay);
                break;
        }
    }


    /**
     * 做一个刷新
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    getTestNote(InputUserNos, firstDay, lastDay);
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }
}






