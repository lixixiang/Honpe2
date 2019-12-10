package com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.bean.ApplyChild;
import com.example.lxx.myalipay.widget.font.FontTextView4;

import java.util.List;

/**
 * created by lxx at 2019/11/29 10:25
 * 描述:
 */
public class ApplyOrderMenuAdapter extends BaseQuickAdapter<ApplyChild,BaseViewHolder> {

    FontTextView4 tvFontDate,tvTime1,tvTime2,tvNum1,tvNum2;
    public ApplyOrderMenuAdapter( @Nullable List<ApplyChild> data) {
        super(R.layout.item_apply_order_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyChild item) {
        helper.setText(R.id.tv_font_date, item.getDate());
        helper.setText(R.id.tv_time1, item.getTime1());
        helper.setText(R.id.tv_time2, item.getTime2());
        helper.setText(R.id.tv_person_num, item.getNum1());
        helper.setText(R.id.tv_person_num2, item.getNum2());

        tvFontDate = helper.getView(R.id.tv_font_date);
        tvTime1 = helper.getView(R.id.tv_time1);
        tvTime2 = helper.getView(R.id.tv_time2);
        tvNum1 = helper.getView(R.id.tv_person_num);
        tvNum2 = helper.getView(R.id.tv_person_num2);

        if (item.getTime1() == null) {
            tvTime1.setClickable(false);
            tvNum1.setClickable(false);
        }else{
            tvTime1.setClickable(true);
            tvNum1.setClickable(true);
            helper.addOnClickListener(R.id.tv_time1);
            helper.addOnClickListener(R.id.tv_person_num);
        }
        if (item.getTime2() == null) {
            tvTime2.setClickable(false);
            tvTime2.setClickable(false);
        }else {
            tvTime2.setClickable(false);
            tvTime2.setClickable(false);
            helper.addOnClickListener(R.id.tv_time2);
            helper.addOnClickListener(R.id.tv_person_num2);
        }
    }
}

