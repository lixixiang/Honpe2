package com.example.lxx.myalipay.ui.staff.query.ui.position7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.FloorManagerBean;
import com.example.lxx.myalipay.widget.BaseGridView;

import java.util.List;

/**
 * created by lxx at 2019/12/3 15:50
 * 描述:
 */
public class BedExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<FloorManagerBean.FloorDataBean> parentList;
    private LayoutInflater inflater;
    private String build,floor;


    public BedExpandAdapter(Context context, List<FloorManagerBean.FloorDataBean> parentList,String build,String floor) {
        this.context = context;
        this.parentList = parentList;
        this.build = build;
        this.floor =floor;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentList.get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final RoomViewHolder roomViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context,R.layout.css_build2,null);
            roomViewHolder = new RoomViewHolder(convertView);
            convertView.setTag(roomViewHolder);
        } else {
            roomViewHolder = (RoomViewHolder) convertView.getTag();
        }
        FloorManagerBean.FloorDataBean bean = parentList.get(groupPosition);
        roomViewHolder.tvRoomNum.setText(bean.getDormitoryNum() + "");
        roomViewHolder.tvTotalBedNum.setText(bean.getBedQty()+"");
        roomViewHolder.tvFreeRoom.setText(bean.getFreeBedQty() + "");
        roomViewHolder.ivRoomOrientation.setImageResource(isExpanded ?  R.drawable.ic_bottom:R.drawable.fragmentation_ic_right);
        if (bean.getFreeBedQty() == 0) {
            roomViewHolder.tvRoomStatus.setVisibility(View.VISIBLE);
            roomViewHolder.tvRoomStatus.setBackgroundResource(R.color.thin_red);
            roomViewHolder.tvRoomStatus.setTextColor(context.getResources().getColor(R.color.white));
            roomViewHolder.tvRoomStatus.setText("满房");
        }else if (bean.getFreeBedQty() < bean.getBedQty()){
            roomViewHolder.tvRoomStatus.setVisibility(View.VISIBLE);
            roomViewHolder.tvRoomStatus.setBackgroundResource(R.color.blue);
            roomViewHolder.tvRoomStatus.setTextColor(context.getResources().getColor(R.color.white));
            roomViewHolder.tvRoomStatus.setText("未满");
        } else {
            roomViewHolder.tvRoomStatus.setVisibility(View.VISIBLE);
            roomViewHolder.tvRoomStatus.setBackgroundResource(R.color.background2);
            roomViewHolder.tvRoomStatus.setTextColor(context.getResources().getColor(R.color.list_bottom));
            roomViewHolder.tvRoomStatus.setText("空房");
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        BedViewHolder bedViewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context,R.layout.item_child_grid,null);
            bedViewHolder = new BedViewHolder(convertView);
            convertView.setTag(bedViewHolder);
        } else {
            bedViewHolder = (BedViewHolder) convertView.getTag();
        }
        bedViewHolder.gridView.setAdapter(new BedGridViewAdapter(context,parentList.get(groupPosition).getDetailBeans(),parentList.get(groupPosition),build,floor));
        bedViewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(context, "GridView - " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class RoomViewHolder {
        private TextView tvRoomNum, tvTotalBedNum, tvFreeRoom,tvRoomStatus;
        private ImageView ivRoomOrientation;

        private RoomViewHolder(View view) {
            tvRoomNum = view.findViewById(R.id.tv_room_num);
            tvTotalBedNum = view.findViewById(R.id.tv_total_bed_num);
            tvFreeRoom = view.findViewById(R.id.tv_freeRoom);
            ivRoomOrientation = view.findViewById(R.id.iv_room_orientation);
            tvRoomStatus = view.findViewById(R.id.tv_room_status);
        }
    }

    class BedViewHolder {
        BaseGridView gridView;

        private BedViewHolder(View view) {
            gridView = view.findViewById(R.id.base_grid);
        }
    }
}
