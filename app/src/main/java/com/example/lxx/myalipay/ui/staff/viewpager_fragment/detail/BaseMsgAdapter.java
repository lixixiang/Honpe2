package com.example.lxx.myalipay.ui.staff.viewpager_fragment.detail;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.utils.StringUtils;

import java.util.List;

/**
 * created by lxx at 2019/11/27 14:36
 * 描述:
 */
public class BaseMsgAdapter extends BaseQuickAdapter<MsgDetailBean.DataBean.CommentsDetailsBean,BaseViewHolder> {
    public BaseMsgAdapter(@Nullable List<MsgDetailBean.DataBean.CommentsDetailsBean> data) {
        super(R.layout.item_msg_recy, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgDetailBean.DataBean.CommentsDetailsBean item) {
        String str = item.getCommentTime();
        String[][] object = {new String[]{"T", " "}};

        helper.setText(R.id.tv_userName, item.getCritics()+":")
                .setText(R.id.tv_content, item.getCommentContent())
                .setText(R.id.tv_date, StringUtils.replace(str, object));
    }
}

