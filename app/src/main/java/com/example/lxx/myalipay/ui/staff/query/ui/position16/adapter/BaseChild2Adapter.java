package com.example.lxx.myalipay.ui.staff.query.ui.position16.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean2;
import com.example.lxx.myalipay.widget.BaseGridView;

import java.util.List;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.adapter
 * 作者: lxx
 * 日期: 2019/1/17 8:43
 * 描述:
 */
public class BaseChild2Adapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<StatisticsBean2> data;

    public BaseChild2Adapter(Context context, List<StatisticsBean2> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getData().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getData().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentViewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_title_arraw, parent, false);
            parentViewHolder = new ParentViewHolder(convertView);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }
        StatisticsBean2 bean = data.get(groupPosition);
        if (bean.getData().get(groupPosition).getMealTimes() != null) {
            parentViewHolder.tvTitle.setText(bean.getData().get(groupPosition).getMealDate());
        }else{
            parentViewHolder.tvTitle.setVisibility(View.GONE);
        }
        parentViewHolder.arrowIcon.setImageResource(isExpanded ? R.mipmap.icon_top : R.mipmap.icon_bottom);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_child2_child, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        StatisticsBean2.DataBean childBean = data.get(groupPosition).getData().get(childPosition);
        if (childBean.getMealTimes() != null) {
            childViewHolder.tvMeal.setText(childBean.getMealTimes());
        }else{
            childViewHolder.tvMeal.setVisibility(View.GONE);
        }
        ItemChildGridAdapter adapter = new ItemChildGridAdapter(context,childBean.getDishesDetails());
        childViewHolder.gridView.setAdapter(adapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ParentViewHolder {
        private TextView tvTitle;
        private ImageView arrowIcon;

        private ParentViewHolder(View view) {
            tvTitle = view.findViewById(R.id.tv_title);
            arrowIcon = view.findViewById(R.id.iv_icon);
        }
    }

    class ChildViewHolder {
        private TextView tvMeal;
        private BaseGridView gridView;

        private ChildViewHolder(View view) {
            tvMeal = view.findViewById(R.id.tv_meal);
            gridView = view.findViewById(R.id.gridView_1);
        }
    }
}
