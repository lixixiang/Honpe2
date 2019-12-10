package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lxx.myalipay.utils.StringUtils.useList;


/**
 * created by lxx at 2019/11/28 14:03
 * 描述:
 */
public class InnerDetailAdapter extends BaseAdapter {

    private List<InnerDetailBean> allContent;
    private Context context;
    private LayoutInflater inflater;
    private OnClickListBtn mOnClickListBtn;

    public InnerDetailAdapter(Context context, List<InnerDetailBean> allContent) {
        this.allContent = allContent;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnClickListBtn(OnClickListBtn onClick) {
        mOnClickListBtn = onClick;
    }

    @Override
    public int getCount() {
        return allContent.size();
    }

    @Override
    public Object getItem(int position) {
        return allContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_inner_approvals_status, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final InnerDetailBean bean = allContent.get(position);

        holder.tvDate.setText(bean.getDate()); //审批日期
        holder.tvContent.setText(bean.getContent()); //审批内容
        holder.tvStatus.setText(bean.getCarStatus()); //审批当前状态
        holder.tvWhoFlag.setText(bean.getSenStatus()); //当前是谁
        holder.tvName.setText(bean.getName()); //用户

        String UserName = (String) MyApplication.get(context, FinalClass.UserName, "");

        if (bean.getCarStatus().equals("待派车")) {
            if (UserName.equals(bean.getName())) {
                holder.tvName.setText("自己");
                String[] arrays = bean.getArray().toArray(new String[bean.getArray().size()]);
                for (int i = 0; i < arrays.length; i++) {
                    if (useList(arrays, "待派车")) { //有待派车权限
                        Selector_circle_size(holder, R.color.white,
                                R.drawable.shape_green_radius10,
                                R.drawable.shape_circle_logistics_green);
                        holder.tvStatus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.getInstance().showToast(position+"");
                            }
                        });
                    }else{//没有权限
                        holder.tvName.setText(bean.getName()); //用户
                        unSelector_circle_size(holder);
                    }
                }
            }else{
                holder.tvName.setText(bean.getName()); //用户
            }
            holder.line.setVisibility(View.GONE);
        } else {
            if (UserName.equals(bean.getName())) {
                holder.tvName.setText("自己");
            }else{
                holder.tvName.setText(bean.getName()); //用户
            }
            switch (position) {
                case 0:
                    unSelector_circle_size(holder);
                    holder.tvContent.setVisibility(View.VISIBLE);
                    holder.line.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    Selector_circle_size(holder, R.color.white,
                            R.drawable.shape_grey_radius10,
                            R.drawable.shape_circle_logistics_green);
                    holder.tvContent.setVisibility(View.GONE);
                    holder.line.setVisibility(View.GONE);
                    break;
            }
        }
        return convertView;
    }

    private void Selector_circle_size(ViewHolder holder, int tvColor, int tvBackground, int circleBackground) {
        holder.tvStatus.setTextColor(context.getResources().getColor(tvColor));
        holder.tvStatus.setBackgroundResource(tvBackground);
        holder.ivStatus.setBackgroundResource(circleBackground);
        ViewGroup.LayoutParams para;
        para = holder.ivStatus.getLayoutParams();
        para.height = DensityUtil.dp2px(15);
        para.width = DensityUtil.dp2px(15);
        holder.ivStatus.setLayoutParams(para);
    }

    private void unSelector_circle_size(ViewHolder holder) {
        holder.tvStatus.setTextColor(context.getResources().getColor(R.color.white));
        holder.tvStatus.setBackgroundResource(R.drawable.shape_grey_radius10);
        holder.ivStatus.setBackgroundResource(R.drawable.shape_circle_logistics_gray);
        ViewGroup.LayoutParams para0;
        para0 = holder.ivStatus.getLayoutParams();
        para0.height = DensityUtil.dp2px(10);
        para0.width = DensityUtil.dp2px(10);
        holder.ivStatus.setLayoutParams(para0);
    }


    static class ViewHolder {
        @BindView(R.id.iv_status)
        ImageView ivStatus;
        @BindView(R.id.ivBottomLine)
        View ivLine;
        @BindView(R.id.rl_left)
        RelativeLayout rlLeft;
        @BindView(R.id.tv_who_flag)
        TextView tvWhoFlag;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.re_time_line)
        LinearLayout reTimeLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickListBtn {
        void onShenPiMethod(View view, int position, InnerDetailBean bean);

        void onPaiCeMethod(View view, int position, InnerDetailBean bean);
    }
}
