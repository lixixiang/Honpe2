package com.example.lxx.myalipay.ui.staff.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.bean.InnerManagerBean;

import java.util.List;

/**
 * created by lxx at 2019/11/27 13:35
 * 描述:内部员工首页适配器
 */
public class InnerAdapter extends BaseQuickAdapter<InnerManagerBean, BaseViewHolder> {

    public InnerAdapter(List<InnerManagerBean> data) {
        super(R.layout.item_inner_icon_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InnerManagerBean item) {
        helper.setText(R.id.tv_content, item.getTitle())
                .setText(R.id.font_icon, item.getContent());

        helper.setTextColor(R.id.font_icon, mContext.getResources().getColor(item.getFontColor()));
        switch (helper.getLayoutPosition()) {
            case 3:
                helper.setTextColor(R.id.font_icon, mContext.getResources().getColor(R.color.grey_home));
                helper.setTextColor(R.id.tv_content, mContext.getResources().getColor(R.color.grey_home));
                break;
            case 4:
                helper.setTextColor(R.id.font_icon, mContext.getResources().getColor(R.color.grey_home));
                helper.setTextColor(R.id.tv_content, mContext.getResources().getColor(R.color.grey_home));
                break;
            default:
                break;
        }
    }
}