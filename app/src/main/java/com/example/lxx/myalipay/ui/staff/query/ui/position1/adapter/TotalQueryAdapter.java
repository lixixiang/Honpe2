package com.example.lxx.myalipay.ui.staff.query.ui.position1.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.bean.TotalTimeQuery;

import java.util.List;

/**
 * created by lxx at 2019/12/3 10:23
 * 描述:
 */
public class TotalQueryAdapter extends BaseQuickAdapter<TotalTimeQuery, BaseViewHolder> {


    public TotalQueryAdapter(@Nullable List<TotalTimeQuery> data) {
        super(R.layout.css_table_1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TotalTimeQuery item) {
        helper.setText(R.id.tv_time_head, item.getDateTime());
        helper.setText(R.id.time1, item.getTime1());
        helper.setText(R.id.time2, item.getTime2());
        helper.setText(R.id.time3, item.getTime3());
        helper.setText(R.id.time4, item.getTime4());
        helper.setText(R.id.time5, item.getTime5());
        helper.setText(R.id.time6, item.getTime6());
    }
}