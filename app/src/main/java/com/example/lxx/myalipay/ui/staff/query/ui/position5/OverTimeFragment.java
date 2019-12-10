package com.example.lxx.myalipay.ui.staff.query.ui.position5;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.bean.LeaveNewBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.bean.LeaveShowListBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position5.adapter.OverTimeAdapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position5.add.AddOverTimeFragment;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.font.FontTextView4;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * created by lxx at 2019/12/3 14:24
 * 描述:
 */
public class OverTimeFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {

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
    @BindView(R.id.tv_font_date)
    FontTextView4 tvFontDate;
    @BindView(R.id.tv_startTime)
    FontTextView4 tvStartTime;
    @BindView(R.id.tv_overTime)
    FontTextView4 tvOverTime;
    @BindView(R.id.tv_reason)
    FontTextView4 tvReason;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_background)
    LinearLayout llBackground;
    private Activity activity;
    private SimpleDateFormat sfYearMonth = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat YearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    private LinearLayoutManager linearLayoutManager;
    private String titles, firstDay, lastDay, curMonth;
    private OverTimeAdapter adapter;

    private String[] bottomTitles = {"修改加班", "取消加班"};

    public static OverTimeFragment newInstance(String title) {
        OverTimeFragment fragment = new OverTimeFragment();
        fragment.titles = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.over_time;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(titles);
        curMonth = sfYearMonth.format(new Date());
        tvDate.setText(curMonth);
        tvEnd.setVisibility(View.VISIBLE);
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        firstDay = DateUtils.getFristDayOfMonth(YearMonthDay);
        lastDay = DateUtils.getLastDayOfMonth(YearMonthDay);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecyclerView();
        ProgressUtils.disLoadView(_mActivity, 0);
    }

    private void initRecyclerView() {
        EasyHttp.post(Constants.LEAVEAPPLYLIST + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
                .params("Ltype", "3")
                .params("IsCancel", "0")
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

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("DormFragment", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                start(LoginFragment.newInstance());
                                ToastUtils.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                final LeaveShowListBean bean = Convert.fromJson(s, LeaveShowListBean.class);
                                if (bean.getStatus() == 0) {
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    llBackground.setBackgroundResource(R.color.grey_line_table);
                                    adapter = new OverTimeAdapter(bean.getData());
                                    recyclerView.setAdapter(adapter);
                                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                        @Override
                                        public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                                            final LeaveNewBean itemBean = new LeaveNewBean();
                                            itemBean.setUserName(bean.getData().get(position).getUserName());
                                            itemBean.set_id(bean.getData().get(position).getId());
                                            itemBean.setReason(bean.getData().get(position).getReason());
                                            itemBean.setStartTime(bean.getData().get(position).getStartTime());
                                            itemBean.setEndTime(bean.getData().get(position).getEndTime());
                                            itemBean.setLongTime(bean.getData().get(position).getLeaveTime());
                                            final ActionSheetDialog dialog = new ActionSheetDialog(_mActivity, bottomTitles, null);
                                            dialog.isTitleShow(false)
                                                    .layoutAnimation(null)
                                                    .itemTextSize(14)
                                                    .itemTextColor(Color.BLACK)
                                                    .lvBgColor(Color.WHITE)
                                                    .cancelText(_mActivity.getResources().getColor(R.color.grey_aeaeae))
                                                    .cancelTextSize(14)
                                                    .show();
                                            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                                @Override
                                                public void onOperItemClick(AdapterView<?> parent, View view, int position0, long id) {
                                                    switch (position0) {
                                                        case 0:
                                                            startForResult(AddOverTimeFragment.newInstance(titles, itemBean, "2", "3"), 444);
                                                            break;
                                                        case 1:
                                                            delete(adapter, position, bean.getData().get(position).getId());
                                                            break;
                                                    }
                                                    dialog.dismiss();
                                                }
                                            });
                                        }
                                    });

                                } else {
                                    ToastUtils.getInstance().showToast(bean.getMsg());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void delete(final BaseQuickAdapter adapters, final int position, String _id) {
        EasyHttp.post(Constants.REQUEST_OA_DELETE + session)
                .params("Id", _id)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", "删除成功！");
                        ToastUtils.getInstance().showToast("删除成功！");
                        initRecyclerView();
                        adapters.notifyItemRemoved(position);
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
        firstDay = DateUtils.getFristDayOfMonth(YearMonthDay, oneDay);
        lastDay = DateUtils.getLastDayOfMonth(YearMonthDay, oneDay);
        LatteLogger.d("monday", firstDay + " 某月第一天 " + lastDay + "某月最后一天");
        tvDate.setText(DateUtils.getLastMonth(oneDay));
        initRecyclerView();

    }

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                startForResult(AddOverTimeFragment.newInstance(titles, null, "1", "3"), 444);
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

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        initRecyclerView();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initRecyclerView();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
