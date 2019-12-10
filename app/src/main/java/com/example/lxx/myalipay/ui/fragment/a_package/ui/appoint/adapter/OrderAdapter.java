package com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.bean.AppointBean;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.detail.AppointDetailActivity;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.widget.BaseListView;


import java.util.List;


/**
 * created by lxx at 2019/11/25 9:16
 * 描述:
 */
public class OrderAdapter extends BaseQuickAdapter<AppointBean,BaseViewHolder> {

    private int userType;
    AppointChildListAdapter adapter;
    private String session;
    public OrderAdapter(@Nullable List<AppointBean> data, int userType, String session) {
        super(R.layout.item_appoint_head, data);
        this.userType = userType;
        this.session = session;
    }

    @Override
    protected void convert(BaseViewHolder helper, AppointBean item) {
        String[] strs = item.getMsg().split(" ");
        TextView tvStatus = helper.getView(R.id.tv_status);
        helper.setText(R.id.tv_title, strs[0])
                .setText(R.id.tv_com, "(" + strs[1] + ")");
        LatteLogger.d("ddddddd", strs[1]);
        if (userType == 1) {
            tvStatus.setVisibility(View.VISIBLE);
        } else {
            tvStatus.setVisibility(View.GONE);
        }
        BaseListView listView = helper.getView(R.id.listView);
        LinearLayout linearLayout = helper.getView(R.id.ll_appointment_header);
        adapter = new AppointChildListAdapter(mContext, item.getData(), userType, session,linearLayout,tvStatus);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, AppointDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("AppointBean", item.getData().get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }
}
