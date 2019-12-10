package com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean.RepairBean;

import java.util.List;

/**
 * created by lxx at 2019/11/28 17:33
 * 描述:维修单适配器
 */
public class RepairAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<RepairBean> data;

    public RepairAdapter(Context context, List<RepairBean> data) {
        this.context = context;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getDetailListBeans().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getDetailListBeans().get(childPosition);
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
            convertView = inflater.inflate(R.layout.item_repair, parent, false);
            parentViewHolder = new ParentViewHolder(convertView);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }
        RepairBean bean = data.get(groupPosition);
        parentViewHolder.tvDepart.setText(bean.getDepart());
        parentViewHolder.userName.setText(bean.getUserName());
        parentViewHolder.tvOrderNo.setText(bean.getOrderId());
        parentViewHolder.tvOrderCause.setText(bean.getCause());
        parentViewHolder.tvTime.setText(bean.getApplyDate());
        parentViewHolder.iconIndex.setImageResource(isExpanded ? R.drawable.ic_top : R.drawable.ic_bottom);
        //   parentViewHolder.view0.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_repair_list_detail, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        RepairBean.DetailListBean bean =
                data.get(groupPosition).getDetailListBeans().get(childPosition);
        childViewHolder.tvItem.setText("("+bean.getRepairItem()+")");
        childViewHolder.tvPoint.setText(bean.getPoint());
        childViewHolder.tvHours.setText(bean.getTime());
        childViewHolder.tvType.setText(bean.getType());
        childViewHolder.tvFinishTime.setText(bean.getFinishTime());
        childViewHolder.tvUserName.setText(bean.getRepairMan());
        childViewHolder.tvApplydepartConfirm.setText(bean.isConfirm()?"完成":"未完成");
        childViewHolder.tvCause.setText(bean.getCause());
        childViewHolder.tvChecked.setText(bean.getDianGonConfirm());
        childViewHolder.tvResult.setText(bean.getResultCommit());
        childViewHolder.btnChangeState.setText("去完成");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ParentViewHolder {
        private TextView tvDepart, userName, tvOrderNo, tvOrderCause, tvStatus, tvTime;
        private ImageView iconIndex;
        //  private View view0;

        private ParentViewHolder(View view) {
            tvDepart = view.findViewById(R.id.tv_depart);
            userName = view.findViewById(R.id.tv_applyPerson);
            tvOrderNo = view.findViewById(R.id.tv_order_no);
            tvOrderCause = view.findViewById(R.id.tv_orderCause);
            tvStatus = view.findViewById(R.id.tv_status);
            tvTime = view.findViewById(R.id.tv_time);
            iconIndex = view.findViewById(R.id.icon_index);
            // view0 = view.findViewById(R.id.view0);
        }
    }
    class ChildViewHolder{
        private TextView tvItem,tvPoint,tvHours,tvType,tvFinishTime,tvUserName,tvApplydepartConfirm,tvCause,
                tvChecked,tvResult;
        private Button btnChangeState;
        private ChildViewHolder(View view){
            tvItem = view.findViewById(R.id.tv_item);   //维修项目
            tvPoint = view.findViewById(R.id.tv_point);  //位置
            tvHours = view.findViewById(R.id.tv_hours); //工时
            tvType = view.findViewById(R.id.tv_type);   //类型
            tvFinishTime = view.findViewById(R.id.tv_finish_time);//完成时间
            tvUserName = view.findViewById(R.id.tv_userName); //维修人
            tvApplydepartConfirm = view.findViewById(R.id.tv_apply_depart_confirm);//申请部门确认
            tvCause = view.findViewById(R.id.tv_cause); //原因
            tvChecked = view.findViewById(R.id.tv_checked); //电工鉴定
            tvResult = view.findViewById(R.id.tv_result);  //鉴定结果
            btnChangeState = view.findViewById(R.id.apply_succeed);//完成按钮
        }
    }
}

