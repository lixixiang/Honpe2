package com.example.lxx.myalipay.ui.staff.approve.ui.fragment3;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.approve.ApproveFragment;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.adapter.SubContractAdapter;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.bean.ProcureSureBean;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.bean.SubContractBean;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.BottomPopupOption;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.D;
import static com.example.lxx.myalipay.api.FinalClass.ME_info;


/**
 * created by lxx at 2019/12/2 10:05
 * 描述:委外加工
 */
public class SubContractFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.iv_no_order)
    ImageView ivNoOrder;

    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private String StartTime, OverTime;
    private BottomPopupOption bottomPopupOption;
    private SubContractAdapter adapter;
    private ProcureSureBean bean;
    private List<SubContractBean> mList = new ArrayList<>();
    private int[] icons = {
            R.mipmap.sub_contract01, R.mipmap.sub_contract02, R.mipmap.sub_contract03, R.mipmap.sub_contract04,
            R.mipmap.sub_contract05, R.mipmap.sub_contract06, R.mipmap.sub_contract07, R.mipmap.sub_contract08,
            R.mipmap.sub_contract09, R.mipmap.sub_contract10, R.mipmap.sub_contract11, R.mipmap.sub_contract12,
            R.mipmap.sub_contract13, R.mipmap.sub_contract14, R.mipmap.sub_contract15, R.mipmap.sub_contract16};


    public static SubContractFragment newInstance() {
        SubContractFragment fragment = new SubContractFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_refresh_recyclerview;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue_ocean);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, -50);
        StartTime = sf.format(calendar.getTime());
        OverTime = sf.format(new Date());
        calendar.clear();
        LatteLogger.d("时间", "开始时间===" + StartTime + "====结束时间===" + OverTime);
        bottomPopupOption = new BottomPopupOption(_mActivity);
        bottomPopupOption.setItemText("同意", "不同意");

        initAdapter();
    }

    private void initAdapter() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        netRequest();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
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
            case ME_info:
                netRequest();
                break;
            case D:
                netRequest();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        netRequest();
    }

    private void netRequest() {
        EasyHttp.post(Constants.MANAGER_OUTSIDE_APPLYLIST + session)
                .params("StartTime", StartTime)
                .params("EndTime", OverTime)
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
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("netRequest", result);
                        try {
                            JSONObject obj = new JSONObject(result);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                start(LoginFragment.newInstance());
                            } else {
                                bean = Convert.fromJson(result, ProcureSureBean.class);
                                LatteLogger.d("dddd", GsonBuildUtil.GsonBuilder(bean));
                                if (bean.getStatus() == -1) {
                                    ToastUtils.getInstance().showToast(bean.getMsg());
                                } else {
                                    adapter = new SubContractAdapter(getJsonDatas(bean.getData()),icons);
                                    recyclerView.setAdapter(adapter);
                                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                                            ((ApproveFragment) getParentFragment()).startBrotherFragment(ApproveSubContractFragment.newInstance(mList.get(position)));
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    List<ProcureSureBean.DataBean> linkedData;

    private List<SubContractBean> getJsonDatas(List<ProcureSureBean.DataBean> data) {
        mList.clear();
        LinkedHashMap<String, List<ProcureSureBean.DataBean>> maps = new LinkedHashMap<>();

        for (int i = 0; i < data.size(); i++) {
            String strDepart = data.get(i).getDeptName();
            List<ProcureSureBean.DataBean> listDay = maps.get(strDepart);
            if (listDay == null) {
                listDay = new ArrayList<>();
            }
            ProcureSureBean.DataBean item = new ProcureSureBean.DataBean();
            item.setOrderNo(data.get(i).getOrderNo());
            item.setApplyNo(data.get(i).getApplyNo());
            item.setApplyUserID(data.get(i).getApplyUserID());
            item.setApplyUserName(data.get(i).getApplyUserName());
            item.setDeptName(data.get(i).getDeptName());
            item.setGroupName(data.get(i).getGroupName());
            item.setApplyTimer(data.get(i).getApplyTimer());
            item.setOrderTimer(data.get(i).getOrderTimer());
            item.setOrderGroup(data.get(i).getOrderGroup());
            item.setOrderDeliveryDate(data.get(i).getOrderDeliveryDate());
            item.setOrderFollower(data.get(i).getOrderFollower());
            item.setOutWorkType(data.get(i).getOutWorkType());
            item.setApplyNotes(data.get(i).getApplyNotes());
            item.setSupplier(data.get(i).getSupplier());
            item.setAmount(data.get(i).getAmount());
            item.setDeliveryDate(data.get(i).getDeliveryDate());
            item.setPuConfirm(data.get(i).getPuConfirm());
            item.setPuConfirmTimer(data.get(i).getPuConfirmTimer());
            item.setConfirmResult(data.get(i).getConfirmResult());
            item.setConfirmNotes(data.get(i).getConfirmNotes());
            item.setCompletedFlow(data.get(i).getCompletedFlow());
            listDay.add(item);
            maps.put(strDepart, listDay);
        }

        for (String depart : maps.keySet()) {
            SubContractBean bean = new SubContractBean();
            bean.setDepart(depart);
            linkedData = maps.get(depart);
            bean.setTime(linkedData.get(linkedData.size() - 1).getApplyTimer());
            bean.setTips(linkedData.size() + 1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            if (bean.getDepart().equals(depart)) {
                List<SubContractBean.DataBean> subData = new ArrayList<>();
                for (int i = 0; i < linkedData.size(); i++) {
                    SubContractBean.DataBean item = new SubContractBean.DataBean();
                    item.setOrderNo(linkedData.get(i).getOrderNo());
                    item.setApplyNo(linkedData.get(i).getApplyNo());
                    item.setApplyUserID(linkedData.get(i).getApplyUserID());
                    item.setApplyUserName(linkedData.get(i).getApplyUserName());
                    item.setDeptName(linkedData.get(i).getDeptName());
                    item.setGroupName(linkedData.get(i).getGroupName());
                    item.setApplyTimer(linkedData.get(i).getApplyTimer());
                    item.setOrderTimer(linkedData.get(i).getOrderTimer());
                    item.setOrderGroup(linkedData.get(i).getOrderGroup());
                    item.setOrderDeliveryDate(linkedData.get(i).getOrderDeliveryDate());
                    item.setOrderFollower(linkedData.get(i).getOrderFollower());
                    item.setOutWorkType(linkedData.get(i).getOutWorkType());
                    item.setApplyNotes(linkedData.get(i).getApplyNotes());
                    item.setSupplier(linkedData.get(i).getSupplier());
                    item.setAmount(linkedData.get(i).getAmount());
                    item.setDeliveryDate(linkedData.get(i).getDeliveryDate());
                    item.setPuConfirm(linkedData.get(i).getPuConfirm());
                    item.setPuConfirmTimer(linkedData.get(i).getPuConfirmTimer());
                    item.setConfirmResult(linkedData.get(i).getConfirmResult());
                    item.setConfirmNotes(linkedData.get(i).getConfirmNotes());
                    item.setCompletedFlow(linkedData.get(i).getCompletedFlow());
                    subData.add(item);
                }
                bean.setListBean(subData);
            }
            mList.add(bean);
        }
        LatteLogger.d("ProcureSureBean", GsonBuildUtil.GsonBuilder(mList));
        return mList;
    }


}
