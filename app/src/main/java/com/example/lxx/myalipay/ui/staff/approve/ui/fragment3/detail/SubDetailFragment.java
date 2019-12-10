package com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.detail;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.bean.SubContractDetailBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.BaseListView;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.BottomPopupOption;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by lxx at 2019/12/2 11:39
 * 描述:委外加工单明细
 */
public class SubDetailFragment extends BaseBackFragment {
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_delivery_time)
    TextView tvDeLiveryTime;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_price)
    TextView tvAmount;
    @BindView(R.id.tv_apply)
    TextView tvApply;
    @BindView(R.id.tv_flower)
    TextView tvFlower;
    @BindView(R.id.tv_classify)
    TextView tvClassify;
    @BindView(R.id.ll_table)
    LinearLayout llTable;
    @BindView(R.id.listView)
    BaseListView listView;
    @BindView(R.id.listView2)
    BaseListView listView2;
    @BindView(R.id.remark)
    TextView tvRemark;
    @BindView(R.id.item_ww)
    LinearLayout itemWW;
    @BindView(R.id.btn_approval_status)
    Button btnStatus;

    SimpleDateFormat sf = new SimpleDateFormat("MM月dd");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String ApplyNo;
    private BottomPopupOption bottomPopupOption;
    private int curPos;

    public static SubDetailFragment newInstance(String ApplyNo,int pos) {
        SubDetailFragment fragment = new SubDetailFragment();
        fragment.ApplyNo = ApplyNo;
        fragment.curPos = pos;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sub_detail;
    }

    @Override
    protected void initView() {
        title.setText("委外加工单明细");
        bottomPopupOption = new BottomPopupOption(_mActivity);
        bottomPopupOption.setItemText("同意", "不同意");
        bottomPopupOption.setColors(getResources().getColor(R.color.green), getResources().getColor(R.color.red));

        initGetJsonData();
    }

    private void initGetJsonData() {
        EasyHttp.post(Constants.MANAGER_OUTSIDE_APPLYLIST_DETAIL + session)
                .params("ApplyNo", ApplyNo)
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
                    public void onSuccess(String s) {
                        SubContractDetailBean bean = Convert.fromJson(s, SubContractDetailBean.class);
                        Log.d("initGetJsonData", GsonBuildUtil.GsonBuilder(bean));
                        itemWW.setVisibility(View.VISIBLE);
                        tvOrderNo.setText(bean.getData().getApplyNo());
                        if (!"".equals(bean.getData().getConfirmResult())) {
                            btnStatus.setText(bean.getData().getConfirmResult());
                            btnStatus.setTextColor(getResources().getColor(R.color.grey_aeaeae));
                            btnStatus.setBackgroundResource(R.drawable.shape_rectangle_white_grey_5_15);
                        }
                        bottomPopupOption.setItemClickListener(position -> {
                            switch (position){
                                case 0:
                                    bean.getData().setConfirmResult("同意");
                                    btnStatus.setText(bean.getData().getConfirmResult());
                                    btnStatus.setTextColor(getResources().getColor(R.color.grey_aeaeae));
                                    btnStatus.setBackgroundResource(R.drawable.shape_rectangle_white_grey_5_15);
                                    btnStatus.setClickable(false);
                                    bottomPopupOption.dismiss();
                                    break;
                                case 1:
                                    bean.getData().setConfirmResult("不同意");
                                    btnStatus.setText(bean.getData().getConfirmResult());
                                    btnStatus.setTextColor(getResources().getColor(R.color.grey_aeaeae));
                                    btnStatus.setBackgroundResource(R.drawable.shape_rectangle_white_grey_5_15);
                                    btnStatus.setClickable(false);
                                    bottomPopupOption.dismiss();
                                    break;
                            }
                            subResult(bean);
                            EventBusUtil.sendEvent(new Event(FinalClass.D));
                        });

                        try {
                            Date mStartDate = DateUtils.setDate(sdf, bean.getData().getApplyTimer());
                            Date mEndDate = DateUtils.setDate(bean.getData().getOrderDeliveryDate());
                            String strStartDate = sf.format(mStartDate);
                            String strEndDate = sf.format(mEndDate);
                            tvStartDate.setText("申请:" + strStartDate);
                            tvEndDate.setText("交货:" + strEndDate);
                            String date = DateUtils.CountAddMinus(sf, strStartDate, strEndDate, 2);
                            if ("0".equals(date)) {
                                tvDeLiveryTime.setText("当天交货");
                            } else {
                                tvDeLiveryTime.setTextColor(_mActivity.getResources().getColor(R.color.alpha_green));
                                tvDeLiveryTime.setText(date + "天交货");
                            }
                            if ("".equals(bean.getData().getSupplier())) {
                                tvCompany.setText("无供应商");
                            } else {
                                tvCompany.setText(bean.getData().getSupplier());
                            }
                            if (bean.getData().getAmount() != 0.0) {
                                tvAmount.setVisibility(View.VISIBLE);
                                DecimalFormat df = new DecimalFormat("###.####");
                                tvAmount.setText("报价:" + df.format(bean.getData().getAmount()));
                            }

                            tvApply.setText("申请人:" + bean.getData().getApplyUserName());
                            tvFlower.setText("跟单员:" + bean.getData().getOrderFollower());
                            tvClassify.setText("加工分类:" + bean.getData().getOutWorkType());

                            if (bean.getData().getDetails().size() > 0) {
                                llTable.setVisibility(View.VISIBLE);
                                listView.setAdapter(new TableAdapter(bean.getData().getDetails()));
                            }

                            if (!"".equals(bean.getData().getApplyNotes())) {
                                tvRemark.setVisibility(View.VISIBLE);
                                tvRemark.setText("备注:" + bean.getData().getApplyNotes());
                            }

                            LatteLogger.d("gson===",GsonBuildUtil.GsonBuilder(bean.getData().getConfirmdetails()));

                            if (bean.getData().getConfirmdetails().size() > 0) {
                                listView2.setVisibility(View.VISIBLE);

                                Collections.sort(bean.getData().getConfirmdetails(), new Comparator<SubContractDetailBean.DataBean.ConfirmdetailsBean>() {
                                    @Override
                                    public int compare(SubContractDetailBean.DataBean.ConfirmdetailsBean o1, SubContractDetailBean.DataBean.ConfirmdetailsBean o2) {
                                        if (o1.getConfirmSeq() < o2.getConfirmSeq()) {
                                            return 1;
                                        }
                                        if (o1.getConfirmSeq() == o2.getConfirmSeq()) {
                                            return 0;
                                        }
                                        return -1;
                                    }
                                });

                                listView2.setAdapter(new ApproveAdapter(bean.getData().getConfirmdetails()));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
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
            case FinalClass.D:
                break;
        }
    }

    private void subResult(SubContractDetailBean bean) {
        LatteLogger.d("SubContractDetailBean", bean.getData().getConfirmdetails().get(curPos).getConfirmLevel()+"  "+
                bean.getData().getConfirmdetails().get(curPos).getConfirmResult()+"   "+session);


        EasyHttp.post(Constants.MANAGER_APPLY_CONFIRM + session)
                .params("ApplyNo", bean.getData().getApplyNo())
                .params("ConfirmLevel", bean.getData().getConfirmdetails().get(curPos).getConfirmLevel())
                .params("ConfirmResult", bean.getData().getConfirmdetails().get(curPos).getConfirmResult())
                .params("ConfirmNotes", bean.getData().getConfirmdetails().get(curPos).getConfirmNotes())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("subResult",s);
                        ToastUtils.getInstance().showToast(bean.getData().getConfirmdetails().get(curPos).getConfirmResult() + "审批");
                    }
                });
    }

    @OnClick({R.id.ll_back,R.id.btn_approval_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                _mActivity.onBackPressed();
                break;
            case R.id.btn_approval_status:
                bottomPopupOption.showPopupWindow();
                break;
        }
    }

    public class TableAdapter extends BaseAdapter {
        LayoutInflater inflater;
        List<SubContractDetailBean.DataBean.DetailsBean> mList;

        public TableAdapter(List<SubContractDetailBean.DataBean.DetailsBean> mList) {
            this.mList = mList;
            inflater = LayoutInflater.from(_mActivity);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_table, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            SubContractDetailBean.DataBean.DetailsBean bean = mList.get(position);
            viewHolder.tvName.setText(bean.getItemName());
            viewHolder.tvMaterial.setText(bean.getMaterial());
            viewHolder.tvNum.setText(bean.getItemNumber() + "");
            viewHolder.tvPrice.setText(bean.getItemAmount() + "");

            if (position % 2 == 0) {
                viewHolder.llTable2.setBackgroundResource(R.color.white);
            } else {
                viewHolder.llTable2.setBackgroundResource(R.color.green_thin);
            }

            return convertView;
        }

        public class ViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_material)
            TextView tvMaterial;
            @BindView(R.id.tv_num)
            TextView tvNum;
            @BindView(R.id.tv_price)
            TextView tvPrice;
            @BindView(R.id.ll_table_2)
            LinearLayout llTable2;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public class ApproveAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<SubContractDetailBean.DataBean.ConfirmdetailsBean> list;

        public ApproveAdapter(List<SubContractDetailBean.DataBean.ConfirmdetailsBean> list) {
            this.list = list;
            inflater = LayoutInflater.from(_mActivity);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder2 holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_approve_list, parent, false);
                holder = new ViewHolder2(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder2) convertView.getTag();
            }
            SubContractDetailBean.DataBean.ConfirmdetailsBean bean = list.get(position);
            holder.tvIsAgree.setText(bean.getConfirmLevel() + ":" + bean.getConfirmBy() + "  " + bean.getConfirmResult());
            holder.tvDate.setText(bean.getConfirmTimer());
            holder.ivStatus.setImageResource(R.drawable.shape_circle_grey_txt);
            holder.tvLevel.setText(bean.getConfirmSeq() + "");
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(DensityUtil.dp2px(20), DensityUtil.dp2px(20));
            holder.ivStatus.setLayoutParams(params);
            if (position == 0) {
                holder.tvIsAgree.setTextColor(_mActivity.getResources().getColor(R.color.google_green));
                holder.tvDate.setTextColor(_mActivity.getResources().getColor(R.color.google_green));
                holder.ivStatus.setImageResource(R.drawable.shape_circle_green_txt);
                params = new RelativeLayout.LayoutParams(DensityUtil.dp2px(25), DensityUtil.dp2px(25));
                holder.ivStatus.setLayoutParams(params);
            }
            return convertView;
        }

        class ViewHolder2 {
            @BindView(R.id.tvTopLine)
            View tvTopLine;
            @BindView(R.id.iv_status)
            ImageView ivStatus;
            @BindView(R.id.ivBottomLine)
            View ivBottomLine;
            @BindView(R.id.tv_is_agree)
            TextView tvIsAgree;
            @BindView(R.id.tv_date)
            TextView tvDate;
            @BindView(R.id.tx_action)
            LinearLayout txAction;
            @BindView(R.id.tv_level)
            TextView tvLevel;
            @BindView(R.id.re_status)
            RelativeLayout reStatus;

            ViewHolder2(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}



