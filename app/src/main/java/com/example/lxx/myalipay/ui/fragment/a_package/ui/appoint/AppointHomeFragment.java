package com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint;

import android.os.Bundle;
import android.se.omapi.Session;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.adapter.OrderAdapter;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.add.AppointFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.bean.AppointBean;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.login.bean.LoginBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.UserType;


/**
 * created by lxx at 2019/11/20 16:26
 * 描述:访客预约
 */
public class AppointHomeFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int userType;
    private String title, result, mStartTime, mEndTime, strUrl;
    private OrderAdapter adapter;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    AppointBean.DataBean bean;

    public static AppointHomeFragment newInstance(String title) {
        AppointHomeFragment fragment = new AppointHomeFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {
        tvTitle.setText(title);
        initToolbarNav(llBack);
        tvEnd.setVisibility(View.VISIBLE);
        userType = (int) MyApplication.get(_mActivity, UserType, -1);
        mStartTime = DateUtils.advanceToDelayed(sdf, -30);
        mEndTime = DateUtils.advanceToDelayed(sdf, 30);
        tvEnd.setText(getString(R.string.apply));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (userType == 0) { //管理者
            strUrl = Constants.MANAGER_LIST;
        } else if (userType == 1) { //本人
            strUrl = Constants.SHOW_APPOINT_LIST;
        }
        if (!"".equals(session)) {
            getRequestData(session);
        } else {
            start(LoginFragment.newInstance());
            ToastUtils.getInstance().showToast("进入此界面，需要登录！");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        ProgressUtils.disLoadView(_mActivity, 0);
    }

    private void getRequestData(String session) {
        EasyHttp.post(strUrl + session)
                .params("StartTime", mStartTime)
                .params("EndTime", mEndTime)
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
                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                start(LoginFragment.newInstance());
//                                startActivity(new Intent(_mActivity, LoginFragment.class));
//                                bun.putString("type", TagerIndex);
//                                LoginInterceptor.interceptor(_mActivity, "com.example.lxx.myalipay.MainActivity", bun);
                                ToastUtils.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                final AppointBean bean = Convert.fromJson(s, AppointBean.class);
                                LatteLogger.d("AppointHome", s);
                                mList.clear();
                                if (bean.getStatus() == 0) {
                                    adapter = new OrderAdapter(getData(bean.getData()), userType, session);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                                    recyclerView.setAdapter(adapter);
                                } else {
                                    ToastUtils.getInstance().showToast(bean.getMsg());
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    List<AppointBean> mList = new ArrayList<>();
    List<AppointBean.DataBean> listData = new ArrayList<>();

    private List<AppointBean> getData(List<AppointBean.DataBean> dataBeans) {
        Map<String, List<AppointBean.DataBean>> maps = new LinkedHashMap<>();
        for (int i = 0; i < dataBeans.size(); i++) {
            String key = dataBeans.get(i).getStaffName() + " "
                    + dataBeans.get(i).getStaffDept();

            listData = maps.get(key);
            if (listData == null) {
                listData = new ArrayList<>();
            }
            AppointBean.DataBean bean = dataBeans.get(i);
            listData.add(bean);
            maps.put(key, listData);
        }
        for (String keys : maps.keySet()) {
            AppointBean bean = new AppointBean();
            bean.setMsg(keys);
            listData = maps.get(keys);
            bean.setData(listData);
            mList.add(bean);
        }
        LatteLogger.d(GsonBuildUtil.GsonBuilder(mList));
        return mList;
    }

    @Override
    public boolean onBackPressedSupport() {
        ProgressUtils.disLoadView(_mActivity, 0);
        return super.onBackPressedSupport();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.ME_info:
                LoginBean bean = (LoginBean) event.getData();
                session = bean.getLogonUser().getKeyValue();
                getRequestData(session);
                break;
        }
    }

    @OnClick(R.id.tv_end)
    public void onViewClicked() {
        //访客预约
        startForResult(AppointFragment.newInstance(bean), 110);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        session = (String) MyApplication.get(_mActivity, FinalClass.Session, "");
        if (requestCode == 110 && resultCode == RESULT_OK && data != null) {
            result = data.getString("result");
            LatteLogger.d("end", result);
            getRequestData(session);
        } else if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            result = data.getString("result");
            String start = sf.format(DateUtils.setDate(sf, result));
            mStartTime = start;
            LatteLogger.d("start", result);
            getRequestData(session);
        } else if (requestCode == 102 && resultCode == RESULT_OK && data != null) {
            result = data.getString("result");
            String end = sf.format(DateUtils.setDate(sf, result));
            mEndTime = end;
            getRequestData(session);
        } else if (requestCode == 1111 && resultCode == RESULT_OK && data != null) {
            getRequestData(session);
        }
    }
}



























