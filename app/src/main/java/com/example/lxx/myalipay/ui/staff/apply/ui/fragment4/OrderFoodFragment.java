package com.example.lxx.myalipay.ui.staff.apply.ui.fragment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.adapter.ApplyOrderMenuAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.add_order.ApplyOrderActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.bean.ApplyChild;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.bean.ApplyChildBean4;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.CustomMenuDialog;
import com.example.lxx.myalipay.widget.font.FontTextView;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import jp.shts.android.library.TriangleLabelView;

import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.utils.DateUtils.dateToWeek;

/**
 * created by lxx at 2019/11/27 16:11
 * 描述:订餐界面
 */
public class OrderFoodFragment extends BaseBackFragment {

    @BindView(R.id.tv_Year_Month)
    TextView tvYearMonth;
    @BindView(R.id.tv_font_date)
    FontTextView tvFontDate;
    @BindView(R.id.tv_time1)
    FontTextView tvTime1;
    @BindView(R.id.lab_time1)
    TriangleLabelView labTime1;
    @BindView(R.id.tv_person_num)
    FontTextView tvPersonNum;
    @BindView(R.id.lab_person_num)
    TriangleLabelView labPersonNum;
    @BindView(R.id.tv_time2)
    FontTextView tvTime2;
    @BindView(R.id.lab_time2)
    TriangleLabelView labTime2;
    @BindView(R.id.tv_person_num2)
    FontTextView tvPersonNum2;
    @BindView(R.id.lab_person_num2)
    TriangleLabelView labPersonNum2;
    @BindView(R.id.ll_table)
    LinearLayout llTable;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<ApplyChild> mList = new ArrayList<>();

    SimpleDateFormat YearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat YearMonth = new SimpleDateFormat("yy年MM月");
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    private String firstDay, lastDay, curDay, today, msg, session;
    private ApplyOrderMenuAdapter adapter;

    private LinearLayoutManager linearLayoutManager;
    public static final int ORDER_FOOD = 0xF1;


    public static OrderFoodFragment newInstance() {
        OrderFoodFragment fragment = new OrderFoodFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_order_food;
    }


    @Override
    protected void initView() {
        firstDay = DateUtils.getFristDayOfMonth(YearMonthDay);
        lastDay = DateUtils.getLastDayOfMonth(YearMonthDay);
        today = YearMonthDay.format(new Date());
        tvYearMonth.setText(YearMonth.format(new Date()));
        session = (String) MyApplication.get(_mActivity, Session, "");
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        initWidget();
    }

    private void initWidget() {
        tvFontDate.setBackgroundResource(R.color.grey_table2);
        tvTime1.setBackgroundResource(R.color.grey_table2);
        tvTime2.setBackgroundResource(R.color.grey_table2);
        tvPersonNum.setBackgroundResource(R.color.grey_table2);
        tvPersonNum2.setBackgroundResource(R.color.grey_table2);
        tvFontDate.setText("日期");
        tvTime1.setText("时间1");
        tvTime2.setText("时间2");
        tvPersonNum.setText("人数");
        tvPersonNum2.setText("人数");
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
                getJsonData();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getJsonData();
    }

    private void getJsonData() {
        LatteLogger.d("dateTime", firstDay + "  " + lastDay);
        EasyHttp.post(Constants.GETCUSTOMORDERLIST + session)
                .params("StartTime", firstDay) //日期
                .params("EndTime", lastDay)  //晚餐
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        final ApplyChildBean4 bean = Convert.fromJson(s, ApplyChildBean4.class);
                        LatteLogger.d("result", s);
                        if (bean.getStatus() == -1) {
                            ToastUtils.getInstance().showToast(bean.getMsg());
                        } else {
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapter = new ApplyOrderMenuAdapter(getNewJson(bean.getData()));
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                                    MenuDialog(adapter, position, bean, true);
                                }
                            });
                        }
                    }
                });
    }

    private void MenuDialog(final BaseQuickAdapter adapter, final int position, ApplyChildBean4 bean, boolean b) {
        CustomMenuDialog dialog = new CustomMenuDialog(_mActivity, bean.getData().get(position).getDetails(), session, bean.getData().get(position).getOrderNo());
        dialog.show();
        dialog.setCanceledOnTouchOutside(b);
        dialog.setOnDeleteListener(() -> {
            EventBusUtil.sendEvent(new Event(FinalClass.D));
        });
        dialog.setOnMobListener(() -> {
            ApplyChildBean4.DataBean bean1 = new ApplyChildBean4.DataBean();
            bean1.setOrderNo(bean.getData().get(position).getOrderNo());
            bean1.setMealsDate(bean.getData().get(position).getMealsDate());
            bean1.setMealsNumber(bean.getData().get(position).getMealsNumber());
            bean1.setNotes(bean.getData().get(position).getNotes());
            bean1.setFoodAmount(bean.getData().get(position).getFoodAmount());
            bean1.setDetails(bean.getData().get(position).getDetails());
            bean1.setMealType(bean.getData().get(position).getMealType());
            bean1.setMealTimer(bean.getData().get(position).getMealTimer());

            Intent intent = new Intent(_mActivity, ApplyOrderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("style", "2");
            bundle.putSerializable("dataObject", bean1);
            intent.putExtras(bundle);
            startActivityForResult(intent, ORDER_FOOD);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ORDER_FOOD && resultCode == RESULT_OK && data != null) {
            getJsonData();
            adapter.notifyDataSetChanged();
        }
    }


    private List<ApplyChild> getNewJson(List<ApplyChildBean4.DataBean> data) {
        try {
            mList.clear();
            for (int i = 0; i < data.size(); i++) {
                ApplyChild item = new ApplyChild();
                Date start = DateUtils.setDate(data.get(i).getMealsDate());
                curDay = myDay.format(start);
                item.setDate(curDay + "/" + dateToWeek(data.get(i).getMealsDate()));
                item.setType(data.get(i).getMealType());
                if (item.getType().equals("午餐")) {
                    item.setTime1(data.get(i).getMealTimer());
                    item.setNum1(data.get(i).getMealsNumber() + "");
                } else if (item.getType().equals("晚餐")) {
                    item.setTime2(data.get(i).getMealTimer());
                    item.setNum2(data.get(i).getMealsNumber() + "");
                }
                mList.add(item);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mList;
    }
}


