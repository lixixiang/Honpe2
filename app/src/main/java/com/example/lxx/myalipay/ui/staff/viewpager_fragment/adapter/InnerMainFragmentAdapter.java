package com.example.lxx.myalipay.ui.staff.viewpager_fragment.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.viewpager_fragment.bean.MsgListBean;
import com.example.lxx.myalipay.utils.StringUtils;


import java.util.List;

/**
 * created by lxx at 2019/11/27 14:15
 * 描述:
 */
public class InnerMainFragmentAdapter extends BaseQuickAdapter<MsgListBean.DataBean.RowsBean, BaseViewHolder> {

    public InnerMainFragmentAdapter(@Nullable List<MsgListBean.DataBean.RowsBean> data) {
        super(R.layout.item_icon_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgListBean.DataBean.RowsBean item) {
        String str = item.getAddTime();
        String[][] object = {new String[]{"T", " "}};
        ((TextView) helper.getView(R.id.tv_msg_num)).setVisibility(View.VISIBLE);
        helper.setText(R.id.tv_date, StringUtils.replace(str, object)).setText(R.id.tv_content, item.getTitle())
                .setText(R.id.tv_msg_num, "评论(" + item.getComments() + ")");

    }
}