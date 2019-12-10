package com.example.lxx.myalipay.ui.staff.query.ui.position16.childfragment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.adapter.BaseSelectStatisticsAdapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean2;
import com.example.lxx.myalipay.ui.view.MultiSelectCalendarView2;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.REQ_MODIFY_FRAGMENT;
import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.utils.DateUtils.dateToWeek;


/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child
 * @date: 2018/10/15 10:35
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：员工点餐2
 */
public class ChildFragment2 extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;

    String session, firstDay, LastDay, toDay,StartTime;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    BaseSelectStatisticsAdapter adapter;
    IObjectToActivity iObjectToActivity = null;
    @BindView(R.id.iv_no_order)
    ImageView ivNoOrder;

    public static ChildFragment2 newInstance() {
        ChildFragment2 fragment = new ChildFragment2();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        iObjectToActivity = (IObjectToActivity) activity;
        super.onAttach(activity);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_meal2;
    }

    @Override
    protected void initView() {
        initData();
        GetNetRequest();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    List<StatisticsBean2.DataBean> datas = new ArrayList<>();

    @Subscribe
    public void onEventStatisticsBean(StatisticsBean2.DataBean.DishesDetailsBean data) {
        iObjectToActivity.submit2(datas);
    }



    private void initData() {
        firstDay = DateUtils.getFirstDayOfWeek(sdf, sdf.format(new Date()));
        LastDay = DateUtils.getLastDayOfWeek(sdf, sdf.format(new Date()));
        toDay = sdf.format(new Date());
        tvDate.setText(toDay);
        tvWeek.setText(dateToWeek(sdf.format(dateAddDeletes(toDay, oneDay - 1))));
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
    }

    private void GetNetRequest() {
        session = (String) MyApplication.get(_mActivity, Session, "");
        EasyHttp.post(Constants.YGORDERMENULIST + session)
                .params("StartTime", tvDate.getText().toString())
                .params("EndTime", tvDate.getText().toString())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("fragment2", s);
                        StatisticsBean2 bean = Convert.fromJson(s, StatisticsBean2.class);

                        for (int i = 0; i < bean.getData().size(); i++) {
                            if (bean.getData().get(i).getDishesDetails().size() > 0) {
                                recyclerView.setVisibility(View.VISIBLE);
                                ivNoOrder.setVisibility(View.GONE);
                                adapter = new BaseSelectStatisticsAdapter(bean.getData());
                                recyclerView.setAdapter(adapter);
                                datas = bean.getData();
                                adapter.notifyDataSetChanged();
                            } else {
                                recyclerView.setVisibility(View.GONE);
                                ivNoOrder.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    public int oneDay = 1; //这个初始日子

    public void setOneDay(int oneDay) {
        this.oneDay = oneDay;
        tvDate.setText(sdf.format(dateAddDeletes(toDay, oneDay - 1)));
        tvWeek.setText(dateToWeek(sdf.format(dateAddDeletes(toDay, oneDay - 1))));
    }

    @OnClick({R.id.tv_font1, R.id.tv_font2,R.id.ll_selector_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_font1:
                oneDay--;
                setOneDay(oneDay);
                GetNetRequest();
                break;
            case R.id.tv_font2:
                oneDay++;
                setOneDay(oneDay);
                GetNetRequest();
                break;
            case R.id.ll_selector_date:
                Intent intent = new Intent(_mActivity,MultiSelectCalendarView2.class);
                startActivityForResult(intent, REQ_MODIFY_FRAGMENT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            tvDate.setText(DateUtils.getStringOfDay(sdf, data.getStringExtra("result")));
            LatteLogger.d("dates", data.getStringExtra("result"));
            StartTime = tvDate.getText().toString();
            tvWeek.setText(dateToWeek(StartTime));
            GetNetRequest();
        }
    }

    /**
     * 日期加减。
     *
     * @param day 基础日期
     * @param Num 增加天数(减天数则用负数)
     * @return 计算结果
     */
    public Date dateAddDeletes(String day, int Num) {
        Date nowDate = null;
        try {
            nowDate = sdf.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.DATE, Num);
        return cal.getTime();
    }


    public interface IObjectToActivity {
        void submit2(List<StatisticsBean2.DataBean> list);
    }


}





