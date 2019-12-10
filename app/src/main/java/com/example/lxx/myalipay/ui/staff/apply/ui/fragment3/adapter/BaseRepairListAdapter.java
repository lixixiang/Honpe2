package com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean.RepairApplyListBean;

import java.util.List;

/**
 * created by lxx at 2019/12/1 19:43
 * 描述:
 */
public class BaseRepairListAdapter extends BaseQuickAdapter<RepairApplyListBean,BaseViewHolder> {


    public BaseRepairListAdapter(@Nullable List<RepairApplyListBean> data) {
        super(R.layout.item_base_repair_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairApplyListBean item) {
        helper.setText(R.id.tv_order_no, item.getOrderNo())  //订单号
                .setText(R.id.tv_userName, item.getUserName()) //报修人
                .setText(R.id.tv_point, item.getPoint())  //位置
                .setText(R.id.tv_cause, item.getCause()) //原因
                .setText(R.id.tv_character, item.getCharacter()) //性质
                .setText(R.id.tv_apply_list, item.getApplyTime())//申请时间
                .setText(R.id.tv_day, item.getTimes());//要求时限

        if ("一般".equals(item.getCharacter())) {
            helper.setText(R.id.font_status, "\ue796");
            helper.setTextColor(R.id.font_status,mContext.getResources().getColor(R.color.green));
        }else{
            helper.setText(R.id.font_status, "\ue797");
            helper.setTextColor(R.id.font_status, mContext.getResources().getColor(R.color.google_red));
        }
    }

}