package com.example.lxx.myalipay.ui.staff.approve.ui.fragment4;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;

import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.adapter.BaseChild04Adapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.bean.CardQueryBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.google.gson.Gson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.Session;

/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.fragment.search
 * @date: 2018/7/4 14:07
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：R.layout.c_fragment_third  app_test_pic
 */
public class SendCarChildFragment04 extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    BaseChild04Adapter adapter;
    private List<CardQueryBean> CardList = new ArrayList<>();
    private String firstDay,lastDay, session;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static SendCarChildFragment04 newInstance() {
        SendCarChildFragment04 fragment = new SendCarChildFragment04();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_recyclerview;
    }

    protected void initView() {
        firstDay = DateUtils.getFristDayOfMonth(format);
        lastDay = DateUtils.getLastDayOfMonth(format);
        session = (String) MyApplication.get(_mActivity, Session, "");
        getMastRequestData();
    }

    private void getMastRequestData() {
        LatteLogger.d("first2lastDay",firstDay+"  "+lastDay);
        EasyHttp.post(Constants.MASTED_CARD + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("getMastRequestData", s);
                        CardQueryBean bean = Convert.fromJson(s, CardQueryBean.class);
                        if (bean.getStatus() == 0) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                           adapter = new BaseChild04Adapter(getData(bean.getData()));
                            recyclerView.setAdapter(adapter);
                        } else {
                            if (bean.getData().size() <= 0) {
                                getRequestJsonData();
                            }
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
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d(s);
                        CardQueryBean bean = Convert.fromJson(s, CardQueryBean.class);
                        if (bean.getStatus() == 0) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                            adapter = new BaseChild04Adapter(getData(bean.getData()));
                            recyclerView.setAdapter(adapter);
                        } else {

                        }
                    }
                });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.C:
                getMastRequestData();
                break;
        }
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
        }
        Set<String> keySet = maps.keySet();

        for (String key : keySet) {
            CardQueryBean bean = new CardQueryBean();
            bean.setMsg(key);
            LatteLogger.d("key", key);
            List<CardQueryBean.DataBean> listDatas = maps.get(key);
            String strData = new Gson().toJson(listDatas);
            LatteLogger.d("strData", strData);
            bean.setData(listDatas);
            CardList.add(bean);
        }
        LatteLogger.d(GsonBuildUtil.GsonBuilder(CardList));
        return CardList;
    }
}

