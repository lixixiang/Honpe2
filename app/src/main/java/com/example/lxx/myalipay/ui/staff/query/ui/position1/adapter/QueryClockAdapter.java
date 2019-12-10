package com.example.lxx.myalipay.ui.staff.query.ui.position1.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.bean.TotalQueyBean;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.List;


/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position1.adapter
 * 作者: lxx
 * 日期: 2019/1/28 11:57
 * 描述:
 */
public class QueryClockAdapter extends BaseQuickAdapter<TotalQueyBean.DataBean,BaseViewHolder> {


    public QueryClockAdapter(@Nullable List<TotalQueyBean.DataBean> data) {
        super(R.layout.item_query_clock_in, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TotalQueyBean.DataBean item) {
        helper.setText(R.id.tv_job_number, item.getRecorderTime());
        helper.setText(R.id.tv_type, item.getStatus());
        unSelector_circle_size(helper);
    }

    private void unSelector_circle_size(BaseViewHolder helper) {
        helper.setBackgroundRes(R.id.iv_status, R.drawable.shape_circle_logistics_gray);
        ViewGroup.LayoutParams para0;
        para0 = helper.getView(R.id.iv_status).getLayoutParams();
        para0.height = DensityUtil.dp2px(10);
        para0.width = DensityUtil.dp2px(10);
        helper.getView(R.id.iv_status).setLayoutParams(para0);
    }
}
