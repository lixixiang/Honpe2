package com.example.lxx.myalipay.ui.staff.query.ui.position6.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.bean.CardQueryBean;
import com.example.lxx.myalipay.widget.BaseListView;

import java.util.List;

/**
 * created by lxx at 2019/12/3 14:44
 * 描述:
 */
public class BaseChild04Adapter extends BaseQuickAdapter<CardQueryBean, BaseViewHolder> {
    /**
     * 标记展开的item
     */
    public BaseChild04Adapter(@Nullable List<CardQueryBean> data) {
        super(R.layout.item_send_fragment04, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CardQueryBean item) {
        String[] strHead = item.getMsg().split(" ");
        helper.setText(R.id.tv_name, strHead[0]);

        if (strHead[1] != null&&!TextUtils.isEmpty(strHead[1])) {
            Glide.with(mContext).load(strHead[1]).into((ImageView) helper.getView(R.id.headerIcon));
        }else if ("dd".equals(strHead[1])){
            helper.setImageResource(R.id.headerIcon,R.mipmap.ic_normal_bg);
        }

        final BaseListViewAdapter adapter = new BaseListViewAdapter(mContext,item.getData());
        ((BaseListView) helper.getView(R.id.list_time)).setAdapter(adapter);
        final ImageView iv = ((ImageView)helper.getView(R.id.iv));
        if (item.getData().size() > 1) {
            iv.setVisibility(View.VISIBLE);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapter.getCount() == 1) {
                        adapter.addItemNum(item.getData().size());
                        iv.setImageResource(R.drawable.ic_top);
                        adapter.notifyDataSetChanged();
                    }else{
                        adapter.addItemNum(1);
                        iv.setImageResource(R.drawable.ic_bottom);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }else{
            iv.setVisibility(View.GONE);
        }
    }
}
