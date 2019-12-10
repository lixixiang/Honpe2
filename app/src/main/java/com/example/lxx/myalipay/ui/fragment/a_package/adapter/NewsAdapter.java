package com.example.lxx.myalipay.ui.fragment.a_package.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.fragment.a_package.entity.NewsBean;
import com.example.lxx.myalipay.utils.StringUtils;


import java.util.List;


/**
 * created by lxx at 2019/11/12 10:40
 * 描述:
 */
public class NewsAdapter extends BaseQuickAdapter<NewsBean.DataBean.RowsBean,BaseViewHolder> {

    public NewsAdapter(@Nullable List<NewsBean.DataBean.RowsBean> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean.RowsBean item) {
        String str = item.getAddTime();
        String[][] object ={new String[]{"T"," "}};
        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_date,item.getAddTime());
        if ("".equals(item.getPicUrl())) {
            ((ImageView)helper.getView(R.id.iv_icon)).setVisibility(View.GONE);
        }else{
            Glide.with(mContext).load(item.getPicUrl()).into((ImageView)helper.getView(R.id.iv_icon));
        }
        ((TextView)helper.getView(R.id.tv_date)).setText(StringUtils.replace(str,object));
    }

}

