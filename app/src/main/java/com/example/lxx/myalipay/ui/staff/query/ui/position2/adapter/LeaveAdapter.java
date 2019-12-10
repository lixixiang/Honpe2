package com.example.lxx.myalipay.ui.staff.query.ui.position2.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.bean.LeaveNewBean;

import java.util.List;

/**
 * created by lxx at 2019/12/3 11:26
 * 描述:请假适配器
 */
public class LeaveAdapter extends BaseQuickAdapter<LeaveNewBean, BaseViewHolder> {

    public LeaveAdapter(@Nullable List<LeaveNewBean> data) {
        super(R.layout.item_leave, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, LeaveNewBean item) {
        helper.setText(R.id.tv_date, item.getDate());
        helper.addOnClickListener(R.id.tv_morning);
        helper.addOnClickListener(R.id.tv_after);
        helper.addOnClickListener(R.id.tv_dinner);
        helper.addOnClickListener(R.id.tv_status);

        helper.setText(R.id.tv_morning, item.getMorning());
        helper.setText(R.id.tv_after, item.getAfter());
        helper.setText(R.id.tv_dinner, item.getDinner());


        if (item.getMorning().equals("\ue611")) {
            helper.setTextColor(R.id.tv_morning,mContext.getResources().getColor(R.color.grey_home));
            helper.setVisible(R.id.lab_morning, false);
        }else if (item.getMorning().equals("")){
            helper.setVisible(R.id.lab_morning, false);
        }else{
            helper.setTextColor(R.id.tv_morning,mContext.getResources().getColor(R.color.black));
            helper.setVisible(R.id.lab_morning, true);
        }

        if (item.getAfter().equals("\ue611")) {
            helper.setTextColor(R.id.tv_after,mContext.getResources().getColor(R.color.grey_home));
            helper.setVisible(R.id.lab_after, false);
        }else if (item.getAfter().equals("")){
            helper.setVisible(R.id.lab_after, false);
        }else {
            helper.setTextColor(R.id.tv_after,mContext.getResources().getColor(R.color.black));
            helper.setVisible(R.id.lab_after, true);
        }

        if (item.getDinner().equals("\ue611")) {
            helper.setTextColor(R.id.tv_dinner,mContext.getResources().getColor(R.color.grey_home));
            helper.setVisible(R.id.lab_dinner, false);
        }else if (item.getDinner().equals("")){
            helper.setVisible(R.id.lab_dinner, false);
        }else{
            helper.setTextColor(R.id.tv_dinner,mContext.getResources().getColor(R.color.black));
            helper.setVisible(R.id.lab_dinner, true);
        }

        if (item.getStatus() == null) { //审批按钮
            helper.setText(R.id.tv_status, "\ue636");
            helper.setTextColor(R.id.tv_status,mContext.getResources().getColor(R.color.grey_home));
        }else{
            helper.setText(R.id.tv_status, "\ue636");
            helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.blue));
        }
    }
}