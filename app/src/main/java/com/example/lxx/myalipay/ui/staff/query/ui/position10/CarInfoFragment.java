package com.example.lxx.myalipay.ui.staff.query.ui.position10;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.adapter.CarHomeAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.add_order.AddSendCarApplyActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarInfoBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.detail.DetailListActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.map.ShowAllMapActivity;
import com.example.lxx.myalipay.ui.staff.query.ui.position10.bean.CarSendCheckBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position10.dialog.CarQueryDialog;
import com.example.lxx.myalipay.ui.staff.query.ui.position10.history.CarHistoryFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position10.history.adapter.CarHistoryAdapter;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.font.FontTextView4;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.ui.staff.approve.ApproveFragment.CHANGE;
import static com.example.lxx.myalipay.utils.DateUtils.dateToWeek;


/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.viewpagerFragment.childFragment
 * @date: 2018/11/5 13:39
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：派车查询
 */
public class CarInfoFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_end)
    TextView tvAdd;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.tv_week)
    TextView weekDay;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.titleLayout)
    LinearLayout titleLayout;
    @BindView(R.id.font_icon_right)
    FontTextView4 font4;
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

    private String session, StartTime, EndTime;
    private String HistoryStartTime, HistoryEndTime;
    private String carNo = "";
    private String DriverName = "";
    private String oldStartT,oldEndT;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> DriverList = new ArrayList<>();
    private String[] carNos;
    private String[] DriverNames;
    private CarHomeAdapter adapter;
    private CarHistoryAdapter adapter2;
    private CarQueryDialog dialog;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat YearMonthDayHourMin = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static CarInfoFragment newInstance() {
        CarInfoFragment fragment = new CarInfoFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search_car_order;
    }

    @Override
    protected void initView() {
        initToolbarNav(homeBack);
        title.setText("派车查询");
        btnUpPager.setText("前一天");
        btnNextPager.setText("后一天");

        tvAdd.setVisibility(View.VISIBLE); //申请单添加
        tvAdd.setText(getString(R.string.apply));
        StartTime = sf.format(new Date());
        EndTime = sf.format(new Date());

        session = (String) MyApplication.get(_mActivity, Session, "");
        weekDay.setText(dateToWeek(StartTime)); //转星期
        tvDate.setText(StartTime);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        refreshLayout.setOnRefreshListener(this);

        if (StartTime.equals(tvDate.getText().toString())) {
            getRequestCheck();
            btnNextPager.setClickable(false);
            btnNextPager.setTextColor(_mActivity.getResources().getColor(R.color.background_e));
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            tvAdd.setVisibility(View.VISIBLE);
            font4.setVisibility(View.GONE);
        } else {
            getHistoryData();
            btnNextPager.setClickable(true);
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            tvAdd.setVisibility(View.GONE);
            font4.setVisibility(View.VISIBLE);
            font4.setText("\ue503");
        }
        ProgressUtils.disLoadView(_mActivity, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (StartTime.equals(tvDate.getText().toString())) {
            //  getRequestCheck();
            btnNextPager.setClickable(false);
            btnNextPager.setTextColor(_mActivity.getResources().getColor(R.color.background_e));
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            tvAdd.setVisibility(View.VISIBLE);
            font4.setVisibility(View.GONE);
        } else {
            //   getHistoryData();
            btnNextPager.setClickable(true);
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            tvAdd.setVisibility(View.GONE);
            font4.setVisibility(View.VISIBLE);
            font4.setText("\ue503");
        }
        ProgressUtils.disLoadView(_mActivity, 0);
    }

    /**
     * 获取派车申请单未完成列表接口
     */
    private void getRequestCheck() {
        EasyHttp.post(Constants.UNSENDCAR + session)
                .params("StartTime", StartTime)
                .params("EndTime", EndTime)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast("Token异常，请重新登录！获取新的Token");
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("carInfo", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                start(LoginFragment.newInstance());
                                //                                MainActivity.is_login = false;
//                                LoginInterceptor.interceptor(_mActivity, "com.example.logininterceptor.InnerActivity");
                                ToastUtils.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                final CarInfoBean bean = Convert.fromJson(s, CarInfoBean.class);
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                gsonBuilder.setPrettyPrinting();
                                Gson gson = gsonBuilder.create();
                                LatteLogger.d("json", gson.toJson(bean));
                                if (bean.getStatus() == 0) {
                                    adapter = new CarHomeAdapter(bean.getData());
                                    recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                        @Override
                                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                            String carNo = bean.getData().get(position).getCarNo();
                                            String driverTel = bean.getData().get(position).getDriverTel();
                                            if (!TextUtils.isEmpty(carNo) || !"".equals(carNo)) {
                                                LatteLogger.d("onItemChildClick", carNo);
                                                start(CarHistoryFragment.newInstance(carNo, driverTel));
                                            }
                                        }
                                    });
                                } else {
                                    ToastUtils.getInstance().showToast(bean.getMsg());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private int status = 4;

    private void getHistoryData() {
        LatteLogger.d("HistoryStartTime", "CarNo====" + carNo + "===driver===" + DriverName + "====HistoryStartTime===" + HistoryStartTime + "==HistoryEndTime==" + HistoryEndTime
                + "===status===" + status);
        EasyHttp.post(Constants.CARAPPLYSEARCH + session)
                .params("CarNo", carNo)
                .params("Driver", DriverName)
                .params("StartTime", HistoryStartTime)
                .params("EndTime", HistoryEndTime)
                .params("Status", String.valueOf(status))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();

                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();

                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("jsonData", s);
                        final CarSendCheckBean bean = Convert.fromJson(s, CarSendCheckBean.class);
                        adapter2 = new CarHistoryAdapter(bean.getData());
                        recyclerView.setAdapter(adapter2);
                        adapter2.notifyDataSetChanged();

                        for (int i = 0; i < bean.getData().size(); i++) {
                            arrayList.add(bean.getData().get(i).getCarNo());
                            DriverList.add(bean.getData().get(i).getDriver());
                        }
                        carNos = arrayList.toArray(new String[arrayList.size()]);
                        carNos = StringUtils.ifRepeart(StringUtils.removeArrayEmptyTextBackNewArray(carNos));//去除数组中空值并且去除重复数据
                        DriverNames = DriverList.toArray(new String[DriverList.size()]);
                        DriverNames = StringUtils.ifRepeart(StringUtils.removeArrayEmptyTextBackNewArray(DriverNames));//去除数组中空值并且去除重复数据

                        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                testChildData(position, bean.getData().get(position), DetailListActivity.class);
                            }
                        });

                        adapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                String NewCurOutTime = DateUtils.addDateMinut(YearMonthDayHourMin,
                                        bean.getData().get(position).getSetOutTime(), -8);
                                String NewCurRetTime = DateUtils.addDateMinut(YearMonthDayHourMin,
                                        bean.getData().get(position).getRetTime(), -8);
                                CarSendCheckBean.DataBean dataBean = bean.getData().get(position);
                                Intent intent = new Intent(_mActivity, ShowAllMapActivity.class);
                                intent.putExtra("SetOutTime", NewCurOutTime);
                                intent.putExtra("RetTime", NewCurRetTime);
                                intent.putExtra("CarNo", bean.getData().get(position).getCarNo());
                                intent.putExtra("Destination", dataBean.getDestination());
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    private void testChildData(int position, CarSendCheckBean.DataBean item, Class<DetailListActivity> cls) {
        Intent intent = new Intent(_mActivity, cls);
        intent.putExtra("PicUrl", item.getPicturesUrl());
        intent.putExtra("CarNo", item.getCarNo());
        intent.putExtra("CarType", item.getCarName());
        intent.putExtra("CarStatus", item.getCarStatus());
        intent.putExtra("Driver", item.getDriver());
        intent.putExtra("OrderSeq", "");
        intent.putExtra("SendCarNo", item.getSendCarNo());
        intent.putExtra("DeptName", item.getDeptName());
        intent.putExtra("UserName", item.getUserName());
        intent.putExtra("GroupName", item.getGroupName());
        intent.putExtra("AppName", item.getUserName());
        intent.putExtra("OrderTime", item.getOrderTime());
        intent.putExtra("UseCarTime", item.getUseCarTime());
        intent.putExtra("Entourage", item.getEntourage());
        intent.putExtra("Items", item.getItems());
        intent.putExtra("Reason", item.getReason());
        intent.putExtra("SetOutTime", item.getSetOutTime());
        intent.putExtra("RetTime", item.getRetTime());
        intent.putExtra("RetMileage", item.getRetMileage());
        intent.putExtra("Mileage", item.getMileage());
        intent.putExtra("Remarks", item.getRemarks());
        intent.putExtra("Tel", item.getTel());
        intent.putExtra("SenCarby", item.getSenCarby());
        intent.putExtra("SenCarTime", item.getSenCarTime());
        intent.putExtra("CancelStatus", item.getCancelStatus());
        intent.putExtra("UserCarDept", item.getUserCarDept());
        intent.putExtra("UserCarGroup", item.getUserCarGroup());
        intent.putExtra("Status", item.getStatus());
        intent.putExtra("OrderStatus", item.getCarStatus());
        intent.putExtra("Destination", item.getDestination());
        intent.putExtra("EstimatedRTime", item.getRetTime());
        intent.putExtra("EstimatedUseTime", item.getUseCarTime());
        ArrayList<String> opts = (ArrayList<String>) item.getOpts();
        intent.putStringArrayListExtra("opts", opts);
        _mActivity.startActivity(intent);
    }


    public int oneDay = 1; //这个初始日子

    public int getOneDay() {
        String countStr = tvDate.getText().toString().trim();
        if (countStr != null) {
            oneDay = Integer.valueOf(countStr);
        }
        return oneDay;
    }

    public void setOneDay(String startTime, int oneDay) {
        this.oneDay = oneDay;
        tvDate.setText(sf.format(dateAddDeletes(StartTime, oneDay - 1)));
        weekDay.setText(dateToWeek(sf.format(dateAddDeletes(startTime, oneDay - 1))));

        HistoryStartTime = tvDate.getText().toString();
        HistoryEndTime = tvDate.getText().toString();

        if (StartTime.equals(tvDate.getText().toString())) {
            getRequestCheck();
            btnNextPager.setClickable(false);
            btnNextPager.setTextColor(_mActivity.getResources().getColor(R.color.background_e));
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            tvAdd.setVisibility(View.VISIBLE);
            font4.setVisibility(View.GONE);
            titleLayout.setVisibility(View.VISIBLE);
        } else {
            getHistoryData();
            btnNextPager.setClickable(true);
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            tvAdd.setVisibility(View.GONE);
            titleLayout.setVisibility(View.GONE);
            font4.setVisibility(View.VISIBLE);
            font4.setText("\ue503");
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
            nowDate = sf.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.DATE, Num);
        return cal.getTime();
    }

    /**
     * 做头部按钮切换日期
     */
    @OnClick({R.id.btn_up_pager, R.id.ll_click, R.id.btn_next_pager, R.id.tv_end, R.id.font_icon_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager://取当前日期的前一天.
                oneDay--;
                setOneDay(StartTime, oneDay);
                LatteLogger.d("oneDay", oneDay);
                break;
            case R.id.btn_next_pager://取当前日期的后一天.
                oneDay++;
                setOneDay(StartTime, oneDay);
                break;
            case R.id.ll_click://点击日期栏
                // startForResult(MultiSelectCalendarView.newInstance(), REQ_MODIFY_FRAGMENT);
                break;
            case R.id.tv_end:
                Intent intent = new Intent(_mActivity, AddSendCarApplyActivity.class);
                startActivityForResult(intent, CHANGE);
                break;
            case R.id.font_icon_right:
                dialog = new CarQueryDialog(_mActivity, DriverNames, carNos, tvDate.getText().toString(),oldStartT,oldEndT);
                dialog.showDialog();

                dialog.setBtnMessage(new CarQueryDialog.BtnBuyInputMessage() {
                    @Override
                    public void getDialogData(String startTime, String endTime,String oldStartTime,String oldEndTime, String driver, String mCarNo) {
                        LatteLogger.d("testString", "startTime===" + startTime + "===endTime==="
                                + endTime + "===driver==" + driver + "==mCarNo==" + mCarNo);
                        if (mCarNo == null) {
                            mCarNo = "";
                        }
                        if (driver == null) {
                            driver = "";
                        }
                        oldStartT = oldStartTime;
                        oldEndT = oldEndTime;
                        HistoryStartTime = startTime;
                        HistoryEndTime = endTime;
                        carNo = mCarNo;
                        DriverName = driver;
                        getHistoryData();
                    }
                });
                break;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
                getRequestCheck();
                adapter.notifyDataSetChanged();
                break;
            case FinalClass.B:
                getRequestCheck();
                adapter.notifyDataSetChanged();
                break;
            case FinalClass.C:
                getRequestCheck();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE && resultCode == RESULT_OK && data != null) {
            getRequestCheck();
            ProgressUtils.disLoadView(_mActivity, 0);
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (StartTime.equals(tvDate.getText().toString())) {
                    getRequestCheck();
                } else {
                    getHistoryData();
                }
                refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}

