package com.example.lxx.myalipay.ui.staff.query.ui.position10.dialog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position10.dialog.OnInitSelectedPosition;
import com.example.lxx.myalipay.utils.tabutils.bean.TagInfo;

import java.util.List;


/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position10.dialog.adapter
 * 作者: lxx
 * 日期: 2019/4/27 16:43
 * 描述:
 */
public class DriverTagAdapter extends BaseAdapter implements OnInitSelectedPosition {
    private Context mContext;
    private List<TagInfo> mDateList;

    public DriverTagAdapter(Context mContext, List<TagInfo> mDateList) {
        this.mContext = mContext;
        this.mDateList = mDateList;
    }

    @Override
    public int getCount() {
        return mDateList == null ? 0 : mDateList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);
        TextView textView = view.findViewById(R.id.tv_tag);
        TagInfo tagInfo = mDateList.get(position);
        textView.setText(tagInfo.getText());

        view.setTag(tagInfo);
        return view;
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
