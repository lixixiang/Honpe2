package com.example.lxx.myalipay.ui.fragment.b_package.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.fragment.b_package.entity.IconTextDirectorBean;

import java.util.List;


/**
 * created by lxx at 2019/11/21 12:22
 * 描述:
 */
public class MeAdapter extends BaseQuickAdapter<IconTextDirectorBean, BaseViewHolder> {

    public MeAdapter(@Nullable List<IconTextDirectorBean> data) {
        super(R.layout.css_font_text_director, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IconTextDirectorBean item) {
        helper.setText(R.id.font_icon, item.getFontIcon());
        helper.setText(R.id.tv_txt, item.getText());
        helper.setTextColor(R.id.font_icon, mContext.getResources().getColor(item.getFontColor()));
        helper.setImageResource(R.id.iv_director, R.mipmap.ic_right_grey);
        ((ImageView) helper.getView(R.id.iv_director)).setColorFilter(mContext.getResources().getColor(R.color.grey_aeaeae));
    }
}
