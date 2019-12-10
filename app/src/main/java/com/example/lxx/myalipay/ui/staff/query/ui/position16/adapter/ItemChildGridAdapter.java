package com.example.lxx.myalipay.ui.staff.query.ui.position16.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean2;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.adapter
 * 作者: lxx
 * 日期: 2019/1/17 11:33
 * 描述:
 */
public class ItemChildGridAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<StatisticsBean2.DataBean.DishesDetailsBean> mList;

    public ItemChildGridAdapter(Context context, List<StatisticsBean2.DataBean.DishesDetailsBean> list) {
        this.context = context;
        this.mList = list;
        inflater = LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.css_checkbox, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
           viewHolder = (ViewHolder) convertView.getTag();
        }
        final StatisticsBean2.DataBean.DishesDetailsBean bean = mList.get(position);

        if (bean.getIsSelected() == 0) {
            viewHolder.itemCheck.setChecked(false);
        }else{
            viewHolder.itemCheck.setChecked(true);
        }
        viewHolder.itemCheck.setText(bean.getDishes());

        viewHolder.itemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bean.setIsSelected(1);
                }else{
                    bean.setIsSelected(0);
                }
                viewHolder.itemCheck.setText(buttonView.getText().toString());
                EventBus.getDefault().post(new StatisticsBean2.DataBean.DishesDetailsBean(bean.getDishes(),bean.getIsSelected()));
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.item_check)
        CheckBox itemCheck;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
