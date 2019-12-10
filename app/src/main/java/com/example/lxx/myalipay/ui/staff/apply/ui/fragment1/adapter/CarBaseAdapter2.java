package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lxx.myalipay.MainActivity;
import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.ui.staff.apply.ApplyActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.add_order.CarModificationActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarInfoBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.send_car_list.SendCarListActivity;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.utils.StringUtils.useList;


/**
 * created by lxx at 2019/11/27 17:29
 * 描述:
 */
public class CarBaseAdapter2 extends BaseAdapter {
    private Context context;
    private List<CarInfoBean.DataBean.OrderListBean> listBeans;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
    private String[] title1 = {"修改申请", "修改派车", "作废"};
    private String[] title2 = {"修改申请", "修改派车", "作废"};
    private String[] title3 = {"修改申请", "作废"};
    private String[] title0 = {"待派车", "修改申请", "作废"};
    private String[] title01 = {"待派车", "作废"};
    public static final int REQ_SEND_CAR_FRAGMENT = 100;
    public static final int REQ_MOB = 200;

    public CarBaseAdapter2(Context context, List<CarInfoBean.DataBean.OrderListBean> listBeans) {
        this.listBeans = listBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hor_title_right2, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CarInfoBean.DataBean.OrderListBean item = listBeans.get(position);
        String userName = (String) MyApplication.get(context, FinalClass.UserName, "");
        final String session = (String) MyApplication.get(context, Session, "");
        List<String> opts = item.getOpts();
        final String[] array = opts.toArray(new String[opts.size()]);

        if (item.getUserName().equals(userName)) { //自己申请的单
            if ("待派单".equals(item.getOrderStatus())) {
                holder.tvItem1.setText("");
            } else {
                holder.tvItem1.setText((position + 1) + ".");
            }
            holder.tvItem1.setTextColor(context.getResources().getColor(R.color.green));
            holder.tvItem2.setText("自己");
            holder.tvItem2.setTextColor(context.getResources().getColor(R.color.green));

            if (!TextUtils.isEmpty(item.getUseCarTime())) {
                holder.tvItem3.setText(sf.format(DateUtils.setDate(sdf, item.getUseCarTime())));
            }

            holder.tvItem3.setTextColor(context.getResources().getColor(R.color.green));
            holder.tvItem4.setText(item.getReason());
            holder.tvItem4.setTextColor(context.getResources().getColor(R.color.green));

            simpledata(holder,item.getReason(), item.getOrderStatus(), R.drawable.shape_rectangle_white_green_5_lr15_tb5, R.color.green);

            holder.tvItem5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getOrderStatus().equals("待派车")) {
                        if (useList(array, "修改") && useList(array, "作废") && useList(array, "派车")) { //在待派车状态下有权限对话框的显示
                            final ActionSheetDialog dialog = new ActionSheetDialog(context, title0, null);
                            dialog.isTitleShow(false)
                                    .layoutAnimation(null)
                                    .lvBgColor(Color.WHITE)
                                    .titleTextColor(Color.BLACK)
                                    .cancelText(Color.RED)
                                    .show();
                            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                @Override
                                public void onOperItemClick(AdapterView<?> parent, View view, int position0, long id) {
                                    switch (position0) {
                                        case 0: //待派车
                                            IntentSendCarActivity(item);
                                            break;
                                        case 1: //修改申请
                                            mobApplyMethod(item);
                                            break;
                                        case 2: //作废
                                            deleteOrderDialog(item, session, position);
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            });
                        } else { //无权限
                            NoAccess(item, session, position);
                        }
                    } else if (item.getOrderStatus().equals("已派车")) {
                        if (useList(array, "修改") && useList(array, "作废") && useList(array, "派车")) {//自己的单并且有派车权限
                            final ActionSheetDialog dialog = new ActionSheetDialog(context, title1, null);
                            dialog.isTitleShow(false)
                                    .layoutAnimation(null)
                                    .lvBgColor(Color.WHITE)
                                    .titleTextColor(Color.BLACK)
                                    .cancelText(Color.RED)
                                    .show();
                            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                @Override
                                public void onOperItemClick(AdapterView<?> parent, View view, int position0, long id) {
                                    switch (position0) {
                                        case 0: //修改申请
                                            mobApplyMethod(item);
                                            break;
                                        case 1: //修改派车
                                            IntentSendCarActivity(item, item.getCarNo(), item.getDriver(), item.getReason());
                                            break;
                                        case 2: //删除
                                            deleteOrderDialog(item, session, position);
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            });
                        } else { //没有派车权限的普通老百姓
                            NoAccess(item, session, position);
                        }
                    }
                }
            });
        } else { //别人的单
            if ("待派单".equals(item.getOrderStatus())) {
                holder.tvItem1.setText("");
            } else {
                holder.tvItem1.setText((position + 1) + ".");
            }
            holder.tvItem1.setTextColor(context.getResources().getColor(R.color.black));
            holder.tvItem2.setText(item.getUserName());
            holder.tvItem2.setTextColor(context.getResources().getColor(R.color.black));
            if (!TextUtils.isEmpty(item.getUseCarTime())) {
                holder.tvItem3.setText(sf.format(DateUtils.setDate(sdf, item.getUseCarTime())));
            }
            holder.tvItem3.setTextColor(context.getResources().getColor(R.color.black));
            holder.tvItem4.setText(item.getReason());
            holder.tvItem4.setTextColor(context.getResources().getColor(R.color.black));
            if (useList(array, "派车")) { //自己看别人的单
                simpledata(holder,item.getReason(), item.getOrderStatus(), R.drawable.shape_rectangle_white_green_5_lr15_tb5, R.color.white);
            } else {
                simpledata(holder,item.getReason(), item.getOrderStatus(), R.drawable.shape_rectangle_grey_5_lr15_tb5, R.color.white);
            }

            holder.tvItem5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getOrderStatus().equals("待派车")) { //同样有权限的人
                        if (useList(array, "派车")) { //有权限的人按别人的单按钮
                            final ActionSheetDialog dialog = new ActionSheetDialog(context, title01, null);
                            dialog.isTitleShow(false)
                                    .layoutAnimation(null)
                                    .lvBgColor(Color.WHITE)
                                    .titleTextColor(Color.BLACK)
                                    .cancelText(Color.RED)
                                    .show();

                            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                @Override
                                public void onOperItemClick(AdapterView<?> parent, View view, int position01, long id) {
                                    switch (position01) {
                                        case 0:
                                            IntentSendCarActivity(item);
                                            break;
                                        case 1:
                                            deleteOrderDialog(item, session, position);
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            });
                        }
                    } else if (item.getOrderStatus().equals("已派车")) {
                        if (useList(array, "派车")) { //有权限的人按别人的单按钮
                            final ActionSheetDialog dialog = new ActionSheetDialog(context, title2, null);
                            dialog.isTitleShow(false)
                                    .layoutAnimation(null)
                                    .lvBgColor(Color.WHITE)
                                    .titleTextColor(Color.BLACK)
                                    .cancelText(Color.RED)
                                    .show();
                            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                @Override
                                public void onOperItemClick(AdapterView<?> parent, View view, int position1, long id) {
                                    switch (position1) {
                                        case 0: //修改申请
                                            mobApplyMethod(item);
                                            break;
                                        case 1: //修改派车
                                            IntentSendCarActivity(item, item.getCarNo(), item.getDriver(), item.getReason());
                                            break;
                                        case 2: //删除操作
                                            deleteOrderDialog(item, session, position);
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                }
            });
        }

        return convertView;
    }

    private void deleteOrderDialog(final CarInfoBean.DataBean.OrderListBean item, final String session, final int position) {
        final NormalDialog dialog1 = new NormalDialog(context);
        dialog1.isTitleShow(false)
                .content("是否要删除" + "\"" + item.getUserName() + "\"" + "的订单?")
                .cornerRadius(5)//
                .contentGravity(Gravity.CENTER)
                .btnTextColor(Color.RED, Color.BLUE)
                .btnPressColor(Color.LTGRAY)
                .show();
        dialog1.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() { //left
                dialog1.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {//right
                deleteOrder(session, item, position);
                dialog1.dismiss();
            }
        });
    }

    private void NoAccess(final CarInfoBean.DataBean.OrderListBean item, final String session, final int position) {
        final ActionSheetDialog dialog = new ActionSheetDialog(context, title3, null);
        dialog.isTitleShow(false)
                .layoutAnimation(null)
                .lvBgColor(Color.WHITE)
                .titleTextColor(Color.BLACK)
                .cancelText(Color.RED)
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position3, long id) {
                switch (position3) {
                    case 0:
                        mobApplyMethod(item);
                        break;
                    case 1:
                        deleteOrderDialog(item, session, position);
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    private void deleteOrder(String session, CarInfoBean.DataBean.OrderListBean item, final int pos) {
        EasyHttp.post(Constants.MANAGER_CONFIRM + session)
                .params("SendCarNo", item.getSendCarNo())
                .params("Status", 5 + "")
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        ToastUtils.getInstance().showToast("派车单已经删除！");
                        listBeans.remove(pos);
                        notifyDataSetChanged();
                        // EventBusUtil.sendEvent(new Event(FinalClass.C));
                    }
                });
    }

    private void IntentSendCarActivity(CarInfoBean.DataBean.OrderListBean item) {
        Intent intent1 = new Intent(context, SendCarListActivity.class);
        intent1.putExtra("sendCarNo", item.getSendCarNo());
        ((MainActivity) context).startActivityForResult(intent1, REQ_SEND_CAR_FRAGMENT);
    }

    private void IntentSendCarActivity(CarInfoBean.DataBean.OrderListBean item, String carNo, String driver, String reason) {
        Intent intent1 = new Intent(context, SendCarListActivity.class);
        intent1.putExtra("sendCarNo", item.getSendCarNo());
        intent1.putExtra("carNo", carNo);
        intent1.putExtra("driver", driver);
        intent1.putExtra("reasion", reason);
        ((MainActivity) context).startActivityForResult(intent1, REQ_SEND_CAR_FRAGMENT);
    }

    private void simpledata(ViewHolder holder,String resource, String str, int statusColorBg, int textColor) {
        if (TextUtils.isEmpty(resource)) {
            holder.tvItem5.setVisibility(View.GONE);
        }else {
            holder.tvItem5.setVisibility(View.VISIBLE);
        }
        holder.tvItem5.setText(str);
        holder.tvItem5.setBackgroundResource(statusColorBg);
        holder.tvItem5.setTextColor(context.getResources().getColor(textColor));
    }


    class ViewHolder {
        @BindView(R.id.tv_item1)
        TextView tvItem1;
        @BindView(R.id.tv_item2)
        TextView tvItem2;
        @BindView(R.id.tv_item3)
        TextView tvItem3;
        @BindView(R.id.tv_item4)
        TextView tvItem4;
        @BindView(R.id.tv_item5)
        TextView tvItem5;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private void mobApplyMethod(CarInfoBean.DataBean.OrderListBean item) {
        String carMudidi = item.getDestination(); //目的地
        String Tel = item.getTel();            //电话
        String go_along = item.getEntourage(); //随车人员
        String Items = item.getItems();  //随车物品
        String carTime = item.getUseCarTime();//时间
        String Team = item.getGroupName(); //组别
        String depart = item.getDeptName(); //部门
        String carEvent = item.getReason(); //事由
        String orderId = item.getSendCarNo(); //汽车单号
        String ApplyMen = item.getUserName(); //用户名

        LatteLogger.d("mobApplyMethod",Team+"   "+depart);

        Intent intent = new Intent(context, CarModificationActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("orderId", orderId);
        bundle.putString("time", carTime);
        bundle.putString("event", carEvent);
        bundle.putString("mudidi", carMudidi);
        bundle.putString("applyName", ApplyMen);
        bundle.putString("goAlong", go_along);
        bundle.putString("Tel", Tel);
        bundle.putString("Items", Items);
        bundle.putString("Team", Team);
        bundle.putString("Depart", depart);
        intent.putExtras(bundle);
        ((MainActivity) context).startActivityForResult(intent, REQ_MOB);
    }
}