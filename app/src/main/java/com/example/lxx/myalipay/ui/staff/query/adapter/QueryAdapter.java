package com.example.lxx.myalipay.ui.staff.query.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.bean.VipBean;
import com.example.lxx.myalipay.widget.font.FontTextView;

import java.util.List;

import static com.example.lxx.myalipay.api.FinalClass.TEXT;
import static com.example.lxx.myalipay.api.FinalClass.TEXT_IMAGE;


/**
 * created by lxx at 2019/12/3 9:25
 * 描述:
 */
public class QueryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VipBean> results;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public QueryAdapter(Context context, List<VipBean> results) {
        this.results = results;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder mViewHolder;
        if (viewType == TEXT) {
            view = inflater.inflate(R.layout.css_title_small_depart, parent, false);
            mViewHolder = new MyViewHolder1(view);
        } else {
            view = inflater.inflate(R.layout.css_font_text_vertical, parent, false);
            mViewHolder = new MyViewHolder2(view);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case TEXT:
                final MyViewHolder1 holder1 = (MyViewHolder1) holder;
                holder1.mTheme.setText(results.get(position).getTheme());
                break;
            case TEXT_IMAGE:
                final MyViewHolder2 holder2 = (MyViewHolder2) holder;
                holder2.tv_content.setText(results.get(position).getTitle());
                holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                holder2.iv_vip_icon.setTextColor(context.getResources().getColor(results.get(position).getIconFontColor()));
//                if (position == 2) {
//                    holder2.tv_content.setText(results.get(position).getTitle());
//                    holder2.iv_vip_icon.setText(results.get(position).getIconFont());
//                } else if (position == 10) {
//                    holder2.tv_content.setText(results.get(position).getTitle());
//                    holder2.iv_vip_icon.setText(results.get(position).getIconFont());
//                } else if (position == 11) {
//                    holder2.tv_content.setText(results.get(position).getTitle());
//                    holder2.iv_vip_icon.setText(results.get(position).getIconFont());
//                } else if (position == 12) {
//                    holder2.tv_content.setText(results.get(position).getTitle());
//                    holder2.iv_vip_icon.setText(results.get(position).getIconFont());
//                } else if (position == 13) {
//                    holder2.tv_content.setText(results.get(position).getTitle());
//                    holder2.iv_vip_icon.setText(results.get(position).getIconFont());
//                } else if (position == 14) {
//                    holder2.tv_content.setText(results.get(position).getTitle());
//                    holder2.iv_vip_icon.setText(results.get(position).getIconFont());
//                } else {
//                    holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
//                    holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
//                }

                switch (position) {
                    case 0:
                        break;
                    case 1://考勤
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 2://请假
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 3://出差
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 4://外出
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 5://加班
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.color_select_date_dialog_edit_text_bg_focus));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.color_select_date_dialog_edit_text_bg_focus));
                        break;
                    case 6://补卡
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 7://住宿
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 8://培训
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 9:
                        break;
                    case 10://派车
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 11://常规采购
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 12://维修
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 13://订餐
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 14://委外加工
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 15://物品领用
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 16://员工点餐
                        holder2.tv_content.setText(results.get(position).getTitle());
                        holder2.iv_vip_icon.setText(results.get(position).getIconFont());
                        break;
                    case 17:
                        break;
                    case 18:
                        break;
                    case 19://报销
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 20://收款
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 21://付款
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 22://备用金
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 23:
                        break;
                    case 24://客户管理
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 25://客户添加
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 26:
                        break;
                    case 27:
                        break;
                    case 28:
                        break;
                    case 29://总监
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 30://主管
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 31://业务部
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    case 32://事业部
                        holder2.iv_vip_icon.setTextColor(context.getResources().getColor(R.color.grey_home));
                        holder2.tv_content.setTextColor(context.getResources().getColor(R.color.grey_home));
                        break;
                    default:
                        break;
                }

                if (mOnItemClickListener != null) {
                    holder2.item_gride.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.onItemClick(holder2.itemView, position, TEXT_IMAGE);
                        }
                    });
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return results.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        public TextView mTheme;

        public MyViewHolder1(View itemView) {
            super(itemView);
            mTheme = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        FontTextView iv_vip_icon;
        TextView tv_content;
        //  TextView tv_msg_tips;
        LinearLayout item_gride;

        public MyViewHolder2(View itemView) {
            super(itemView);
            //   tv_msg_tips = (TextView) itemView.findViewById(R.id.tv_msg_tips);
            iv_vip_icon = itemView.findViewById(R.id.font_icon);
            tv_content = itemView.findViewById(R.id.tv_content);
            item_gride = itemView.findViewById(R.id.item_gride);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, int type);
        void onItemLongClick(View view, int position);
    }
}
