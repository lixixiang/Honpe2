package com.example.lxx.myalipay.ui.staff.query.ui.position13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.CustomMenuDialog;
import com.example.lxx.myalipay.widget.font.FontTextView;
import com.example.lxx.myalipay.widget.font.FontTextView4;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.shts.android.library.TriangleLabelView;

import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.utils.DateUtils.dateToWeek;


/**
 * created by lxx at 2019/12/3 21:37
 * 描述:客户订餐
 */
public class CustomCookerOrderFragment extends BaseBackFragment {


    List<ApplyChild> mList = new ArrayList<>();
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.font_icon_right)
    FontTextView4 fontIconRight;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.ll_click)
    LinearLayout llClick;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
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
    @BindView(R.id.elv_collocation)
    ExpandableListView elvCollocation;
    @BindView(R.id.ll_custom_head)
    LinearLayout llCustomHead;
    @BindView(R.id.ll_background)
    LinearLayout llBackground;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat YearMonth = new SimpleDateFormat("yy年MM月");
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    private String firstDay, lastDay, curDay, curMonth, today, msg, session;
    private ApplyOrderMenuAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Activity activity;


    public static CustomCookerOrderFragment newInstance() {
        CustomCookerOrderFragment fragment = new CustomCookerOrderFragment();
        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search_shopping;
    }


    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("客户订餐");
        llCustomHead.setVisibility(View.VISIBLE);
        tvEnd.setVisibility(View.VISIBLE);
        curMonth = sf.format(new Date());
        tvDate.setText(curMonth);
        firstDay = DateUtils.getFristDayOfMonth(format);
        lastDay = DateUtils.getLastDayOfMonth(format);
        today = format.format(new Date());
        session = (String) MyApplication.get(activity, Session, "");
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        initWidget();

    }

    public int oneDay = 0; //这个初始日子

    public int getOneDay() {
        String countStr = tvDate.getText().toString().trim();
        if (countStr != null) {
            oneDay = Integer.valueOf(countStr);
        }
        return oneDay;
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
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getJsonData();
    }

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtils.getFristDayOfMonth(format, oneDay);
        lastDay = DateUtils.getLastDayOfMonth(format, oneDay);
        tvDate.setText(DateUtils.getLastMonth(oneDay));
        today = format.format(new Date());
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

    private void MenuDialog(final BaseQuickAdapter adapter, final int position, final ApplyChildBean4 bean, boolean b) {
        CustomMenuDialog dialog = new CustomMenuDialog(_mActivity, bean.getData().get(position).getDetails(), session, bean.getData().get(position).getOrderNo());
        dialog.show();
        dialog.setCanceledOnTouchOutside(b);
        dialog.setOnDeleteListener(new CustomMenuDialog.onDeleteListener() {
            @Override
            public void onClickDelete() {
                //  EventBusUtil.sendEvent(new Event(FinalClass.D));
                getJsonData();
                adapter.notifyItemRemoved(position);
            }
        });
        dialog.setOnMobListener(new CustomMenuDialog.onMobClassListener() {
            @Override
            public void onClickMobClass() {
                LatteLogger.d(bean.getData().get(position).getOrderNo());
                ApplyChildBean4.DataBean bean1 = new ApplyChildBean4.DataBean();
                bean1.setOrderNo(bean.getData().get(position).getOrderNo());
                bean1.setMealsDate(bean.getData().get(position).getMealsDate());
                bean1.setMealsNumber(bean.getData().get(position).getMealsNumber());
                bean1.setNotes(bean.getData().get(position).getNotes());
                bean1.setFoodAmount(bean.getData().get(position).getFoodAmount());
                bean1.setDetails(bean.getData().get(position).getDetails());
                bean1.setMealType(bean.getData().get(position).getMealType());
                bean1.setMealTimer(bean.getData().get(position).getMealTimer());
                getMob(bean1);
            }
        });
    }

    private void getMob(ApplyChildBean4.DataBean data) {
        Intent intent = new Intent(_mActivity, ApplyOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("style", "2");
        bundle.putSerializable("dataObject", data);
        intent.putExtras(bundle);
        startActivityForResult(intent, 111);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK && data != null) {
            getJsonData();
            adapter.notifyDataSetChanged();
        }
//        else if (requestCode == ORDER && resultCode == RESULT_OK && data != null) {
//            getJsonData();
//            adapter.notifyDataSetChanged();
//        }
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

    /**
     * 做头部按钮切换日期
     */
    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager, R.id.tv_end,  R.id.ll_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager://取当前日期的前一天.
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager://取当前日期的后一天.
                oneDay++;
                setOneMonth(oneDay);
                break;
            case R.id.ll_click://点击日期栏
                // startForResult(MultiSelectCalendarView.newInstance(), REQ_MODIFY_FRAGMENT);
                break;
            case R.id.tv_end:
                Intent intent = new Intent(_mActivity, ApplyOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("style", "1");
                intent.putExtras(bundle);
                //   startActivityForResult(intent, ORDER);
                break;
        }
    }
}

