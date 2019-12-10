package com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.bean.AppointBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by lxx at 2019/11/25 9:27
 * 描述:
 */
public class AppointChildListAdapter extends BaseAdapter {
    Context context;
    List<AppointBean.DataBean> lists;
    LayoutInflater inflater;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private int userType;
    private String mDate;
    private String[] selector = {"更改预约", "取消预约"};
    private String[] selector2 = {"通过", "不通过"};
    private String session;
    private LinearLayout linearLayout;
    private TextView tvHeadStatus;

    public AppointChildListAdapter(Context context, List<AppointBean.DataBean> lists, int userType, String session, LinearLayout line, TextView tvHeadStatus) {
        this.context = context;
        this.lists = lists;
        inflater = LayoutInflater.from(context);
        this.userType = userType;
        this.session = session;
        linearLayout = line;
        this.tvHeadStatus = tvHeadStatus;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.css_order_event_time_status, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AppointBean.DataBean bean = lists.get(position);
        holder.tvId.setText(String.valueOf(position + 1) + ".");
        holder.tvReason.setText(bean.getReason());
        String[] strDate = bean.getVisitDate().split(" ");

        Date date = DateUtils.setDate(sdf, strDate[0]);
        mDate = sf.format(date);
        holder.tvTimeBound.setText(mDate + "   " + bean.getVisitTime());


        if (userType == 1) {
            if (bean.getVState() == 1) {
                holder.tvStatus.setText("已成功");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_blue_radius10);
                tvHeadStatus.setText("已成功");
            } else if (bean.getVState() == 0) {
                holder.tvStatus.setText("不通过");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_red_radius10);
                tvHeadStatus.setVisibility(View.GONE);
            } else {
                holder.tvStatus.setText("修改");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_green_radius10);
                tvHeadStatus.setText("预约中...");
            }
        } else if (userType == 0) {
            if (bean.getVState() == 1) {
                holder.tvStatus.setText("已成功");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_grey_radius10);
            } else if (bean.getVState() == 0) {
                holder.tvStatus.setText("不通过");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_red_radius10);
            } else {
                holder.tvStatus.setText("待审批");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_blue_radius10);
            }
        }

        final ViewHolder finalHolder = holder;
        holder.tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType == 1) {
                    final ActionSheetDialog dialog = new ActionSheetDialog(context, selector, null);
                    dialog.isTitleShow(false)
                            .layoutAnimation(null)
                            .lvBgColor(Color.WHITE)
                            .itemTextSize(14)
                            .itemTextColor(Color.BLACK)
                            .cancelTextSize(14)
                            .cancelText(Color.RED)
                            .show();
                    dialog.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position1, long id) {
                            switch (position1) {
                                case 0: //更改
                                    Event<AppointBean.DataBean> event = new Event<AppointBean.DataBean>(FinalClass.ADD_APPOINT, bean);
                                    EventBusUtil.sendEvent(event);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    break;
                                case 1:
                                    bean.setVState(2);
                                    getInterface(bean.getID(), "2", finalHolder.tvStatus, position);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    });
                } else {
                    final ActionSheetDialog dialog = new ActionSheetDialog(context, selector2, null);
                    dialog.isTitleShow(false)
                            .layoutAnimation(null)
                            .lvBgColor(Color.WHITE)
                            .itemTextSize(14)
                            .itemTextColor(Color.BLACK)
                            .cancelTextSize(14)
                            .cancelText(Color.RED)
                            .show();
                    dialog.setOnOperItemClickL((parent1, view, position1, id) -> {
                        switch (position1) {
                            case 0:
                                bean.setVState(1);
                                getInterface2(bean.getID(), "1", finalHolder.tvStatus);
                                notifyDataSetChanged();
                                dialog.dismiss();
                                break;
                            case 1:
                                bean.setVState(0);
                                getInterface2(bean.getID(), "0", finalHolder.tvStatus);
                                notifyDataSetChanged();
                                dialog.dismiss();
                                break;
                        }
                    });
                }
            }
        });
        if (userType == 1) {
            if (bean.getVState() == 0) {
                holder.tvStatus.setClickable(false);
            } else {
                holder.tvStatus.setClickable(true);
            }
        } else if (userType == 0) {
            if (bean.getVState() == 0) {
                holder.tvStatus.setClickable(false);
            } else {
                holder.tvStatus.setClickable(true);
            }
        }
        if (!TextUtils.isEmpty(bean.getReachTime()) || !"".equals(bean.getReachTime())) {//进厂时间
            holder.tvStatus.setClickable(false);
            holder.tvStatus.setText("已进厂");
            holder.tvStatus.setBackgroundResource(R.drawable.shape_grey_radius10);
        }
        if (!TextUtils.isEmpty(bean.getDepartureTime()) || !"".equals(bean.getDepartureTime())) {
            holder.tvStatus.setVisibility(View.GONE);
        }
        return convertView;
    }

    private void getInterface2(String orderId, final String selector, final TextView status) {
        EasyHttp.post(Constants.ADMINISTRATOR + session)
                .params("ID", orderId)
                .params("VState", selector)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("dddddddd", s);
                        try {
                            JSONObject object = new JSONObject(s);
                            int state = object.getInt("Status");
                            if (state == 0) {
                                ToastUtils.getInstance().showToast(object.getString("Msg"));
                                if (selector.equals("1")) {
                                    status.setText("已成功");
                                    status.setBackgroundResource(R.drawable.shape_grey_radius10);
                                } else {
                                    status.setText("不通过");
                                    status.setBackgroundResource(R.drawable.shape_red_radius10);
                                }
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getInterface(String orderId, final String selector, final TextView status, final int position) {
        EasyHttp.post(Constants.ADMINISTRATOR + session)
                .params("ID", orderId)
                .params("VState", selector)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("dddddddd", s);
                        try {
                            JSONObject object = new JSONObject(s);
                            int state = object.getInt("Status");
                            if (state == 0) {
                                ToastUtils.getInstance().showToast(object.getString("Msg"));
                                if (selector.equals("2")) {
                                    lists.remove(position);
                                    linearLayout.setVisibility(View.GONE);
                                    notifyDataSetChanged();
                                } else {
//                                    status.setText("不通过");
//                                    status.setBackgroundResource(R.drawable.shape_red_10);
                                }
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    class ViewHolder {
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_Reason)
        TextView tvReason;
        @BindView(R.id.tv_time_bound)
        TextView tvTimeBound;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

