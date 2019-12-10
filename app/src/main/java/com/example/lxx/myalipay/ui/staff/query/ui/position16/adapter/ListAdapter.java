package com.example.lxx.myalipay.ui.staff.query.ui.position16.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean3;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lxx.myalipay.utils.DateUtils.getTimeRange;


/**
 * @ProjectName: Honpe2
 * @Package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.adapter
 * @ClassName: ListAdapter
 * @Author: lxx
 * @CreateDate: 2018/12/6 17:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/6 17:43
 * @UpdateRemark: 更新说明
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<StatisticsBean3.DataBean.DetailsBean> mList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public ListAdapter(Context context, List<StatisticsBean3.DataBean.DetailsBean> list) {
        this.context = context;
        mList = list;
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_theme3, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        StatisticsBean3.DataBean.DetailsBean bean = mList.get(position);
        holder.tvUserName.setText(bean.getUserName());
        holder.tvContent.setText(bean.getContent());
        holder.level.setText(bean.getScore());
        if (bean.getScore().equals("优")) {
            holder.level.setText("非常好");
        } else if (bean.getScore().equals("良")) {
            holder.level.setText("还可以");
        }else{
            holder.level.setText("很难吃");
        }

        holder.tvDate.setText(getTimeRange(sdf,bean.getCommentTime()));
        String str = bean.getRcode();
        String str1 = str.substring(0, str.length() - 1); //去掉字符串最后一位字符
        String str2 = str.substring(str.length() - 1, str.length());//获得最后一位字符
        if (str2.equals("1")) {
            holder.tvSwitchType.setText("早餐");
        } else if (str2.equals("2")) {
            holder.tvSwitchType.setText("中餐");
        } else if (str2.equals("3")) {
            holder.tvSwitchType.setText("晚餐");
        } else if (str2.equals("4")) {
            holder.tvSwitchType.setText("夜宵");
        }
        return view;
    }


    class ViewHolder {
        @BindView(R.id.tv_userName)
        TextView tvUserName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_switch_type)
        TextView tvSwitchType;
        @BindView(R.id.level)
        TextView level;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
