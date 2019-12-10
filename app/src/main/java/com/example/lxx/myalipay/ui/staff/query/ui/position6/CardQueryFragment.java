package com.example.lxx.myalipay.ui.staff.query.ui.position6;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean.RepairBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.adapter.BaseChild04Adapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.add.CardReissueAddFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.bean.CardQueryBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position6
 * 作者: lxx
 * 日期: 2019/4/26 17:17
 * 描述: 补卡
 */
public class CardQueryFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_end)
    TextView ivAdd;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private String strTitle, curMonth, firstDay, lastDay, session;
    private List<CardQueryBean> CardList = new ArrayList<>();
    private List<RepairBean.DataBean> list1 = new ArrayList<>();

    private BaseChild04Adapter adapter;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static CardQueryFragment newInstance(String title) {
        CardQueryFragment fragment = new CardQueryFragment();
        fragment.strTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_total_query;
    }

    @Override
    protected void initView() {
        initToolbarNav(homeBack);
        title.setText(strTitle);
        session = (String) MyApplication.get(_mActivity, Session, "");
        firstDay = DateUtils.getFristDayOfMonth(format);
        lastDay = DateUtils.getLastDayOfMonth(format);
        curMonth = sf.format(new Date());

        getMastRequestData();
        tvDate.setText(curMonth);
        ivAdd.setVisibility(View.VISIBLE);
        ivAdd.setText(getString(R.string.apply));

        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        refreshLayout.setOnRefreshListener(this);
    }

    private void getMastRequestData() {
        EasyHttp.post(Constants.MASTED_CARD + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("manager", s);
                        CardQueryBean bean = Convert.fromJson(s, CardQueryBean.class);
                        CardList.clear();
                        if (bean.getStatus() == 0) {
                            if (bean.getData().size() > 0) {
                                recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                                adapter = new BaseChild04Adapter(getData(bean.getData()));
                                recyclerView.setAdapter(adapter);
                            } else {
                                getRequestJsonData();
                            }
                        } else {
                            ToastUtils.getInstance().showToast(bean.getMsg());
                        }
                    }
                });
    }

    private void getRequestJsonData() {
        EasyHttp.post(Constants.FULL_CARD_LIST + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("person", s);
                        CardQueryBean bean = Convert.fromJson(s, CardQueryBean.class);
                        if (bean.getStatus() == 0) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                            adapter = new BaseChild04Adapter(getData(bean.getData()));
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    private List<CardQueryBean> getData(List<CardQueryBean.DataBean> data) {
        Map<String, List<CardQueryBean.DataBean>> maps = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {
            if (TextUtils.isEmpty(data.get(i).getPicUrl()) || "".equals(data.get(i).getPicUrl())) {
                data.get(i).setPicUrl("dd");
            }
            String myName = data.get(i).getF_CreateUserName() + " " + data.get(i).getPicUrl();

            List<CardQueryBean.DataBean> listBean = maps.get(myName);
            if (listBean == null) {
                listBean = new ArrayList<>();
            }
            CardQueryBean.DataBean bean = data.get(i);
            listBean.add(bean);
            maps.put(myName, listBean);
            LatteLogger.d("GsonBuildUtil", GsonBuildUtil.GsonBuilder(myName));
        }

        Set<String> keySet = maps.keySet();
        for (String key : keySet) {
            CardQueryBean bean = new CardQueryBean();
            bean.setMsg(key);
            List<CardQueryBean.DataBean> listDatas = maps.get(key);
            List<CardQueryBean.DataBean> listDatas2 = new ArrayList<>();

            for (int i = 0; i < listDatas.size(); i++) {
                CardQueryBean.DataBean bean1 = new CardQueryBean.DataBean();
                bean1.setF_AuditTime(listDatas.get(i).getF_AuditTime());
                bean1.setF_CreateUserName(listDatas.get(i).getF_CreateUserName());
                bean1.setF_Reason(listDatas.get(i).getF_Reason());
                bean1.setPicUrl(listDatas.get(i).getPicUrl());
                listDatas2.add(bean1);
                bean.setData(listDatas2);
            }
            bean.setData(listDatas);
            CardList.add(bean);
        }
        LatteLogger.d(GsonBuildUtil.GsonBuilder(CardList));
        return CardList;
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
        tvDate.setText(DateUtils.getLastMonth(oneDay));
        LatteLogger.d("myDay", "firstDay==" + firstDay + "==lastDay==" + lastDay);
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        getMastRequestData();
    }

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                startForResult(CardReissueAddFragment.newInstance(), 111);
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
        if (requestCode == 111 && resultCode == RESULT_OK && data != null) {
            getMastRequestData();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    getMastRequestData();
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }
}
