package com.example.lxx.myalipay.ui.staff.approve.ui.fragment3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.bean.SubContractBean;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.detail.SubDetailFragment;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.BottomPopupOption;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * created by lxx at 2019/12/2 10:34
 * 描述:
 */
public class ApproveSubContractFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    ApproveSubContractAdapter adapter;

    SubContractBean linkedData;
    private BottomPopupOption bottomPopupOption;
    private Button btnStatus;
    private int currentPos;

    public static ApproveSubContractFragment newInstance(SubContractBean linkedData) {
        ApproveSubContractFragment fragment = new ApproveSubContractFragment();
        fragment.linkedData = linkedData;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_refresh_recyclerview;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.C: //刷新数据
                break;
        }
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(linkedData.getDepart() + "_委外加工审批");

        LatteLogger.d("linkedData",GsonBuildUtil.GsonBuilder(linkedData));

        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue_ocean);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mSwipeRefreshLayout.setEnabled(false);

        adapter = new ApproveSubContractAdapter(linkedData.getListBean());
        recyclerView.setAdapter(adapter);

        bottomPopupOption = new BottomPopupOption(_mActivity);
        bottomPopupOption.setItemText("同意", "不同意");
        bottomPopupOption.setColors(getResources().getColor(R.color.green), getResources().getColor(R.color.red));

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(SubDetailFragment.newInstance(linkedData.getListBean().get(position).getApplyNo(),position));
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                btnStatus = view.findViewById(R.id.btn_approval_status);
                currentPos = position;

                bottomPopupOption.showPopupWindow();
            }
        });

        bottomPopupOption.setItemClickListener(new BottomPopupOption.onPopupWindowItemClickListener() {
            @Override
            public void onItemClick(int bottomPosition) {
                switch (bottomPosition) {
                    case 0:
                        LatteLogger.d("currentPos" + linkedData.getListBean().get(currentPos).getConfirmResult()+"  "+currentPos);
                        linkedData.getListBean().get(currentPos).setConfirmResult("同意");
                        btnStatus.setText(linkedData.getListBean().get(currentPos).getConfirmResult());
                        btnStatus.setTextColor(getResources().getColor(R.color.grey_aeaeae));
                        btnStatus.setBackgroundResource(R.drawable.shape_rectangle_white_grey_5_15);
                        bottomPopupOption.dismiss();
                        break;
                    case 1:
                        linkedData.getListBean().get(currentPos).setConfirmResult("不同意");
                        btnStatus.setText(linkedData.getListBean().get(currentPos).getConfirmResult());
                        btnStatus.setTextColor(getResources().getColor(R.color.grey_aeaeae));
                        btnStatus.setBackgroundResource(R.drawable.shape_rectangle_white_grey_5_15);
                        bottomPopupOption.dismiss();
                        break;
                }
                EventBusUtil.sendEvent(new Event(FinalClass.D));
            }
        });
        initAdapter();
    }

    private void initAdapter() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ApproveSubContractAdapter(linkedData.getListBean());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void subResult() {
        LatteLogger.d("subResult","ApplyNo==="+linkedData.getListBean().get(currentPos).getApplyNo()+
        "===ConfirmLevel=="+linkedData.getListBean().get(currentPos).getCompletedFlow()+
        "==ConfirmResult=="+linkedData.getListBean().get(currentPos).getConfirmResult()+
        "==ConfirmNotes==="+linkedData.getListBean().get(currentPos).getConfirmNotes());
        EasyHttp.post(Constants.MANAGER_APPLY_CONFIRM + session)
                .params("ApplyNo", linkedData.getListBean().get(currentPos).getApplyNo())
                .params("ConfirmLevel", linkedData.getListBean().get(currentPos).getCompletedFlow())
                .params("ConfirmResult", linkedData.getListBean().get(currentPos).getConfirmResult())
                .params("ConfirmNotes", linkedData.getListBean().get(currentPos).getConfirmNotes())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("subResult",s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("Status")== 0) {
                                ToastUtils.getInstance().showToast(linkedData.getListBean().get(currentPos).getConfirmResult() + "审批");
                            }else {
                                ToastUtils.getInstance().showToast(linkedData.getListBean().get(currentPos).getConfirmResult() + "审批");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    @SuppressLint("SimpleDateFormat")
    public class ApproveSubContractAdapter extends BaseQuickAdapter<SubContractBean.DataBean, BaseViewHolder> {

        public ApproveSubContractAdapter(@Nullable List<SubContractBean.DataBean> data) {
            super(R.layout.item_approve_sub_contract, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final SubContractBean.DataBean item) {

            SimpleDateFormat sf = new SimpleDateFormat("MM月dd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            if (item != null) {
                helper.setGone(R.id.item_ww, true);
            }
            try {
                Date mStartDate = DateUtils.setDate(sdf, item.getApplyTimer());
                Date mEndDate = DateUtils.setDate(item.getOrderDeliveryDate());
                String strStartDate = sf.format(mStartDate);
                String strEndDate = sf.format(mEndDate);
                helper.setText(R.id.tv_start_date, "申请:" + strStartDate);
                helper.setText(R.id.tv_end_date, "交货:" + strEndDate);

                helper.setTextColor(R.id.tv_delivery_time, _mActivity.getResources().getColor(R.color.orange));
                String date = DateUtils.CountAddMinus(sf, strStartDate, strEndDate, 2);
                if ("0".equals(date)) {
                    helper.setText(R.id.tv_delivery_time, "当天交货");
                } else {
                    helper.setTextColor(R.id.tv_delivery_time, _mActivity.getResources().getColor(R.color.alpha_green));
                    helper.setText(R.id.tv_delivery_time, date + "天交货");
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            helper.setText(R.id.tv_order_no, item.getOrderNo());
            helper.setText(R.id.tv_apply, "申请人:" + item.getApplyUserName());
            helper.setText(R.id.tv_flower, "跟单员:" + item.getOrderFollower());
            helper.setText(R.id.tv_classify, "加工分类:" + item.getOutWorkType());
            helper.setGone(R.id.tv_price, false);

            if ("".equals(item.getSupplier())) {
                helper.setText(R.id.tv_company, "无供应商");
            } else {
                helper.setText(R.id.tv_company, item.getSupplier());
            }
            if (item.getAmount() != 0.0) {
                helper.setGone(R.id.tv_price, true);
                DecimalFormat df = new DecimalFormat("###.####");
                helper.setText(R.id.tv_price, "报价:" + df.format(item.getAmount()) + "元");
            }

            helper.addOnClickListener(R.id.btn_approval_status);

            if ("同意".equals(item.getConfirmResult())) {
                helper.setEnabled(R.id.btn_approval_status, false);
                helper.setText(R.id.btn_approval_status, item.getConfirmResult());
                helper.setBackgroundRes(R.id.btn_approval_status, R.drawable.shape_rectangle_white_grey_5_15);
                helper.setTextColor(R.id.btn_approval_status, _mActivity.getResources().getColor(R.color.white_a5));
            } else if ("不同意".equals(item.getConfirmResult())) {
                helper.setEnabled(R.id.btn_approval_status, false);
                helper.setText(R.id.btn_approval_status, "不同意");
                helper.setBackgroundRes(R.id.btn_approval_status, R.drawable.shape_rectangle_white_grey_5_15);
                helper.setTextColor(R.id.btn_approval_status, _mActivity.getResources().getColor(R.color.white_a5));
            } else {
                helper.setEnabled(R.id.btn_approval_status, true);
                helper.setText(R.id.btn_approval_status, "待审批");
                helper.setBackgroundRes(R.id.btn_approval_status, R.drawable.shape_blue_radius5);
                helper.setTextColor(R.id.btn_approval_status, _mActivity.getResources().getColor(R.color.white));
            }
        }
    }
}
