package com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean.ShoppingApplyBean;

import java.util.List;

/**
 * created by lxx at 2019/11/28 17:18
 * 描述:采购申请适配器
 */
public class ShoppingAdapter extends BaseExpandableListAdapter

    {
        private LayoutInflater inflater;
        private Context context;
        private ExpandableListView elv_collocation;
        private List<ShoppingApplyBean> data;

    public
        ShoppingAdapter(Context context, ExpandableListView elv_collocation, List < ShoppingApplyBean > data)
        {
            this.context = context;
            this.elv_collocation = elv_collocation;
            this.data = data;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getGroupCount () {
        return data.size();
    }

        @Override
        public int getChildrenCount ( int groupPosition){
        return data.get(groupPosition).getCollcationDetail().size();
    }

        @Override
        public Object getGroup ( int groupPosition){
        return data.get(groupPosition);
    }

        @Override
        public Object getChild ( int groupPosition, int childPosition){
        return data.get(groupPosition).getCollcationDetail().get(childPosition);
    }

        @Override
        public long getGroupId ( int groupPosition){
        return groupPosition;
    }

        @Override
        public long getChildId ( int groupPosition, int childPosition){
        return childPosition;
    }

        @Override
        public boolean hasStableIds () {
        return true;
    }

        @Override
        public View getGroupView ( int groupPosition, boolean isExpanded, View
        convertView, ViewGroup
        parent){
        ParentViewHolder parentViewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_approval, parent, false);
            parentViewHolder = new ParentViewHolder(convertView);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }
        ShoppingApplyBean bean = data.get(groupPosition);
        parentViewHolder.tvDepart.setText(bean.getDepart());
        parentViewHolder.userName.setText(bean.getUserName());
        parentViewHolder.tvOrderNo.setText(bean.getOrderNo());
        parentViewHolder.tvTime.setText(bean.getDate());
        parentViewHolder.tvTotal.setText(bean.getTotal() + "");
        parentViewHolder.tvOrderCause.setText(bean.getOrderCause());
        parentViewHolder.iconIndex.setImageResource(isExpanded ? R.drawable.ic_top : R.drawable.ic_bottom);
        parentViewHolder.ll_head_icon.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        return convertView;
    }

        @Override
        public View getChildView ( int groupPosition, int childPosition, boolean isLastChild, View
        convertView, ViewGroup parent){
        ChildViewHolder childViewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_buy_detail, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        ShoppingApplyBean.CollocationDetail childBean =
                data.get(groupPosition).getCollcationDetail().get(childPosition);

        childViewHolder.tv_order.setText(childBean.getOrderOn() + "");
        childViewHolder.tv_shopping_name.setText(childBean.getName());
        childViewHolder.tvType.setText(childBean.getType());
        childViewHolder.tvUnit.setText(childBean.getUnit());
        childViewHolder.tvNum.setText(childBean.getNum() + "");
        childViewHolder.tvPrice.setText(childBean.getPrice() + "");

        if (childPosition == data.get(groupPosition).getCollcationDetail().size() - 1) {
            childViewHolder.ll_bottom.setVisibility(View.VISIBLE);
            childViewHolder.tvTotal.setText(childBean.getTotal() + "");
        } else {
            childViewHolder.ll_bottom.setVisibility(View.GONE);
        }

        return convertView;
    }

        @Override
        public boolean isChildSelectable ( int groupPosition, int childPosition){
        return true;
    }

        class ParentViewHolder {
            private TextView tvDepart, userName, tvOrderNo, tvOrderCause, tvStatus, tvTime, tvTotal;
            private ImageView iconIndex;
            private LinearLayout ll_head_icon;
            private View view0;

            private ParentViewHolder(View view) {
                tvDepart = view.findViewById(R.id.tv_depart);
                userName = view.findViewById(R.id.tv_applyPerson);
                tvOrderNo = view.findViewById(R.id.tv_order_no);
                tvOrderCause = view.findViewById(R.id.tv_orderCause);
                tvStatus = view.findViewById(R.id.tv_status);
                tvTime = view.findViewById(R.id.tv_time);
                tvTotal = view.findViewById(R.id.tv_total);
                iconIndex = view.findViewById(R.id.icon_index);
                ll_head_icon = view.findViewById(R.id.ll_head_view);
                view0 = view.findViewById(R.id.view0);
            }
        }

        private class ChildViewHolder {
            private TextView tv_order, tv_shopping_name, tvType, tvUnit, tvNum, tvPrice, tvTotal;
            private LinearLayout ll_bottom, ll_root_view;

            private ChildViewHolder(View view) {
                tv_order = view.findViewById(R.id.tv_order_no);
                tv_shopping_name = view.findViewById(R.id.tv_shopping_name);
                tvType = view.findViewById(R.id.tv_type);
                tvUnit = view.findViewById(R.id.tv_unit);
                tvNum = view.findViewById(R.id.tv_num);
                tvPrice = view.findViewById(R.id.tv_price);
                tvTotal = view.findViewById(R.id.tv_total);
                ll_bottom = view.findViewById(R.id.ll_bottom);
                ll_root_view = view.findViewById(R.id.ll_root_view);

            }
        }

//    BaseRecyclerView recyclerView;
//    BaseBuyApplyAdapter adapter;
//    List<BuyApplyBean> mList;
//
//    public ShoppingAdapter(@Nullable List<ShoppingApplyBean> data) {
//        super(data);
//        addItemType(ShoppingApplyBean.TYPE_LEVEL_0,R.layout.item_buy_apply);
//        addItemType(ShoppingApplyBean.TYPE_LEVEL_1,R.layout.item_buy_detail);
//    }
//
//    @Override
//    protected void convert(final BaseViewHolder helper, final ShoppingApplyBean item) {
//        switch (helper.getItemViewType()) {
//            case ShoppingApplyBean.TYPE_LEVEL_0:
//                helper.setText(R.id.tv_date, item.getDate());
//                helper.setText(R.id.tv_week, item.getWeek());
//                helper.setText(R.id.tv_num_shopping, item.getNum());
//                helper.setText(R.id.font_icon,item.isIndex() ? "\ue610" : "\ue523");
//                helper.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int pos = helper.getAdapterPosition();
//                        if (item.isIndex()) {
//                            collapse(pos);//折叠已展开的可展开项。
//                        }else {
//                            expand(pos);//用动画展开可扩展的项目。
//                        }
//                    }
//                });
//                break;
//            case ShoppingApplyBean.TYPE_LEVEL_1:
//                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//                adapter = new BaseBuyApplyAdapter(getBaseData());
//                recyclerView.setAdapter(adapter);
//                break;
//        }
//    }
//
//    private List<BuyApplyBean> getBaseData() {
//        mList = new ArrayList<>();
//        for (int i = 0; i < 5;i++) {
//            BuyApplyBean mBean = new BuyApplyBean();
//            mBean.setName("水质笔");
//            mBean.setType("x9");
//            mBean.setUnit("台");
//            mBean.setNum(10);
//            mBean.setPrice(5.2);
//            mList.add(mBean);
//        }
//        return mList;
//    }
}