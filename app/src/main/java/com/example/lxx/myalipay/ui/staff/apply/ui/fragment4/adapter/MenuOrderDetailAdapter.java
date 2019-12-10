package com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;

import java.util.List;

/**
 * created by lxx at 2019/11/29 10:38
 * 描述:
 */
public class MenuOrderDetailAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public MenuOrderDetailAdapter(@Nullable List<String> data) {
        super(R.layout.css_text_1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_record_text, (helper.getLayoutPosition() + 1) + "." + item);
    }
}