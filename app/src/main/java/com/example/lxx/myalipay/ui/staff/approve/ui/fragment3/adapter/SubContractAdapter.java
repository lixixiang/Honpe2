package com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.bean.SubContractBean;
import com.example.lxx.myalipay.utils.DateUtils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * created by lxx at 2019/12/4 10:27
 * 描述:
 */
public class SubContractAdapter extends BaseQuickAdapter<SubContractBean, BaseViewHolder> {

    SimpleDateFormat sf = new SimpleDateFormat("MM月dd");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private int[] icons;

    public SubContractAdapter(@Nullable List<SubContractBean> data,int[] icon) {
        super(R.layout.item_sub_contract, data);
        this.icons = icon;
    }

    @Override
    protected void convert(BaseViewHolder helper, SubContractBean item) {

        Date mDate = DateUtils.setDate(sdf, item.getTime());
        String mD = sf.format(mDate);
        if (item.getTips() >= 99) {
            helper.setText(R.id.tv_tips, "99+");
            helper.setBackgroundRes(R.id.tv_tips, R.drawable.circle_ovl_red);
        } else {
            helper.setText(R.id.tv_tips, item.getTips() + "");
            helper.setBackgroundRes(R.id.tv_tips, R.drawable.circle_red);
        }
        item.setIcon(icons[helper.getLayoutPosition()]);
        helper.setImageResource(R.id.iv_weChat, item.getIcon());
        helper.setText(R.id.tv_depart, item.getDepart())
                .setText(R.id.tv_time, mD);
    }
}

