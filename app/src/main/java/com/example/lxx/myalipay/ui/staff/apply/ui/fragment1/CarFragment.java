package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.adapter.CarHomeAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarInfoBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * created by lxx at 2019/11/27 16:11
 * 描述:派车列表 css_refresh_recyclerview
 */
public class CarFragment extends BaseBackFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.iv_no_order)
    ImageView ivNoOrder;
    private String  userName, StartTime;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    CarHomeAdapter adapter;
    Disposable disposable;
    public static CarFragment newInstance() {
        CarFragment fragment = new CarFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_refresh_recyclerview;
    }

    @Override
    protected void initView() {
        StartTime = sf.format(new Date());
        initAdapter();
    }
    private void initAdapter() {
        refreshLayout.setOnRefreshListener(() -> refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                refreshLayout.setRefreshing(false);
            }
        }, 2000));
    }

    private void getData(){
        /**
         * 获取派车申请单未完成列表接口
         */
         disposable =EasyHttp.post(Constants.UNSENDCAR + session)
                .params("StartTime", StartTime)
                .params("EndTime", StartTime)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        LatteLogger.d(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("carInfo", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains(sessionI)||obj.getString("Msg").contains(sessionE)) {
                                start(LoginFragment.newInstance());
                                ToastUtils.getInstance().showToast(sessionPastDue);
                            }else{
                                final CarInfoBean bean = Convert.fromJson(s, CarInfoBean.class);
                                if (bean.getStatus() == 0) {
                                    recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                                    adapter = new CarHomeAdapter(bean.getData());
                                    recyclerView.setAdapter(adapter);
                                } else {
                                    ToastUtils.getInstance().showToast(bean.getMsg());
                                }
                            }
                        } catch (JSONException o) {
                            o.printStackTrace();
                        }
                    }
                });
    }



    @Override
    protected boolean isRegisterEventBus() { //将主界面的回调传过来，更新当前界面
        return true;
    }

    @Override
    protected void receiveEvent(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
                getData();
                break;
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyHttp.clearCache();
        EasyHttp.cancelSubscription(disposable);
    }
}
