package com.example.lxx.myalipay.ui.staff.query.ui.position7.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.FloorManagerBean;
import com.example.lxx.myalipay.widget.CustomExpandableListView;

import java.util.List;

/**
 * created by lxx at 2019/12/3 15:37
 * 描述:
 */
public class VpStayListAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<FloorManagerBean> data;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            notifyDataSetChanged();//更新数据
            super.handleMessage(msg);
        }
    };

    /*供外界更新数据的方法*/
    public void refresh(ExpandableListView expandableListView,int groupPosition){
        handler.sendMessage(new Message());
        //必须重新伸缩之后才能更新数据
        expandableListView.collapseGroup(groupPosition);
        expandableListView.expandGroup(groupPosition);
    }

    public VpStayListAdapter(Context context, List<FloorManagerBean> data) {
        this.context = context;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<FloorManagerBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    // 取得分组数
    @Override
    public int getGroupCount() {
        return data.size();
    }

    // 取得指定分组的子元素数。
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    // 取得与给定分组关联的数据
    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    // 取得与指定分组、指定子项目关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getFloorDataBeanList().get(childPosition);
    }

    // 取得指定分组的ID。该组ID必须在组中是唯一的。组合的ID （参见getCombinedGroupId(long)）
    // 必须不同于其他所有ID（分组及子项目的ID）。
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // 取得给定分组中给定子视图的ID。 该组ID必须在组中是唯一的。组合的ID （参见getCombinedGroupId(long)）
    // 必须不同于其他所有ID（分组及子项目的ID）。
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    // 是否指定分组视图及其子视图的ID对应的后台数据改变也会保持该ID。
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        FloorViewHolder parentViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.css_build, parent, false);
            parentViewHolder = new FloorViewHolder(convertView);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (FloorViewHolder) convertView.getTag();
        }
        FloorManagerBean bean = data.get(groupPosition);
        parentViewHolder.tvFloor.setText(bean.getFloorNum());
        parentViewHolder.tvTotalRoom.setText(bean.getTotalRoom() + "间");
        parentViewHolder.tvEmptyRoom.setText(bean.getEmptyRoom() + "间");
        parentViewHolder.tvFreeRoom.setText(bean.getFreeRoom() + "间");
        parentViewHolder.tvFullRoom.setText(bean.getFullRoom() + "间");
        parentViewHolder.ivOrientation.setImageResource(isExpanded ? R.drawable.ic_bottom : R.drawable.fragmentation_ic_right);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, final boolean isLastChild, View convertView, ViewGroup parent) {
        ExpandViewHolder holder = null;
        if (convertView == null) {
            holder = new ExpandViewHolder();
            convertView = inflater.inflate(R.layout.item_expand_group_child, parent, false);
            holder.childExpandLv = convertView.findViewById(R.id.expand_group_item);
            convertView.setTag(holder);
        } else {
            holder = (ExpandViewHolder) convertView.getTag();
        }
        final BedExpandAdapter bedAdapter = new BedExpandAdapter(context, data.get(groupPosition).getFloorDataBeanList()
                , data.get(groupPosition).getBuild(), data.get(groupPosition).getFloorNum());
        holder.childExpandLv.setAdapter(bedAdapter);
        final ExpandViewHolder finalHolder = holder;
        holder.childExpandLv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < bedAdapter.getGroupCount(); i++) {
                    if (i != groupPosition) {
                        finalHolder.childExpandLv.collapseGroup(i);
                    }
                }
            }
        });
        return convertView;
    }

    // 指定位置的子视图是否可选择。
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ExpandViewHolder {
        CustomExpandableListView childExpandLv;
    }

    class FloorViewHolder {
        private TextView tvFloor, tvTotalRoom, tvEmptyRoom, tvFreeRoom, tvFullRoom;
        private ImageView ivOrientation;
        private RelativeLayout reFloor;

        private FloorViewHolder(View view) {
            tvFloor = view.findViewById(R.id.txt_floor);
            tvTotalRoom = view.findViewById(R.id.tv_totalRoom);
            tvEmptyRoom = view.findViewById(R.id.tv_emptyRoom);
            tvFreeRoom = view.findViewById(R.id.tv_freeRoom);
            tvFullRoom = view.findViewById(R.id.tv_fullRoom);
            ivOrientation = view.findViewById(R.id.iv_orientation);
            reFloor = view.findViewById(R.id.re_floor_build);
        }
    }
}

