package com.example.lxx.myalipay.ui.fragment.a_package.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.ui.fragment.a_package.entity.UserCenterBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.widget.font.FontTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by lxx at 2019/11/11 17:26
 * 描述:
 */
public class HomeGridViewAdapter extends BaseAdapter {
    private Context context = null;
    private LayoutInflater inflater;
    private List<UserCenterBean> mList;
    private int myType;

    public HomeGridViewAdapter(Context context, List<UserCenterBean> list, int UserType) {//UserType 1为客户，2为员工
        this.context = context;
        this.mList = list;
        inflater = LayoutInflater.from(context);
        this.myType = UserType;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grider_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LatteLogger.d("UserType", "UserType=======" + myType);
        UserCenterBean bean = mList.get(position);
        holder.tvTitle.setText(bean.getTitle());
        holder.tvFont.setText(bean.getFontIcon());
        holder.tvFont.setTextColor(context.getResources().getColor(bean.getFontColor()));
        if (myType == 1) {
            switch (position) {
                case 0:
                    holder.tvTitle.setText(context.getResources().getString(R.string.login_custom));
                    break;
                case 1:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
                case 2:

                    break;
                case 3:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
                case 4:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
            }
        } else if (myType == 0) {
            switch (position) {
                case 0:
                    holder.tvTitle.setText(context.getString(R.string.login_staff));
                    break;
                case 1:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
                case 2:

                    break;
                case 3:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
                case 4:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
            }
        }else {
            switch (position) {
                case 0:
                    holder.tvTitle.setText(context.getResources().getString(R.string.login_in));
                    break;
                case 1:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
                case 2:

                    break;
                case 3:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
                case 4:
                    holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
                    holder.tvFont.setTextColor(context.getResources().getColor(R.color.grey_home));
                    break;
            }
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.grid_image)
        ImageView gridImage;
        @BindView(R.id.tv_font)
        FontTextView tvFont;
        @BindView(R.id.text_lunch)
        TextView tvTitle;
        @BindView(R.id.item_gride)
        LinearLayout itemGride;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}



















