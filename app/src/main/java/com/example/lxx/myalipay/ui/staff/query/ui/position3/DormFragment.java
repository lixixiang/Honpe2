package com.example.lxx.myalipay.ui.staff.query.ui.position3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.adapter.LeaveAdapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.bean.LeaveNewBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.bean.LeaveShowListBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position3.add.AddTripFragment;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.CustomTypeDialog;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.utils.DateUtils.dateToWeek;


/**
 * created by lxx at 2019/12/3 13:39
 * 描述:
 */
public class DormFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_background)
    LinearLayout llBackground;

    //tab
    private String session, titles, firstDay, lastDay, curMonth, curDay, type, autohour, startTime, endTime, remark, etToCity, tripMode, goLong, today;
    List<String> mDateList = new ArrayList<>(); //记录一个月日子
    private SimpleDateFormat sfYearMonth = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat YearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat Day = new SimpleDateFormat("dd");
    SimpleDateFormat yearMonthdayHourMin = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat HourMin = new SimpleDateFormat("HH:mm");
    SimpleDateFormat Hour = new SimpleDateFormat("HH");
    private LeaveAdapter adapter;
    private Activity activity;
    public static final String KEY_RESULT_TYPE = "type";
    //PopupWindow
    private PopupWindow mPopupWindow;

    private LinearLayoutManager linearLayoutManager;

    private boolean move = false;
    private int mIndex;
    private String sign,types;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public static DormFragment newInstance(String title) {
        DormFragment fragment = new DormFragment();
        fragment.titles = title;
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_leave;
    }

    @Override
    public void initView() {
        initToolbarNav(homeBack);
        title.setText(titles);
        curMonth = sfYearMonth.format(new Date());
        tvDate.setText(curMonth);
        session = (String) MyApplication.get(_mActivity, Session, "");
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        firstDay = DateUtils.getFristDayOfMonth(YearMonthDay);
        lastDay = DateUtils.getLastDayOfMonth(YearMonthDay);
        today = YearMonthDay.format(new Date());
        mDateList = DateUtils.queryData(YearMonthDay, firstDay, lastDay);
        swipeRefreshLayout.setOnRefreshListener(this);

        for (int i = 0; i < mDateList.size(); i++) {
            if (today.equals(mDateList.get(i))) {
                mIndex = i;
                break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecyclerView();
        ProgressUtils.disLoadView(_mActivity, 0);
    }

    private void initRecyclerView() {
        EasyHttp.post(Constants.LEAVEAPPLYLIST + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
                .params("Ltype", "1")
                .params("IsCancel", "0")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("DormFragment", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                start(LoginFragment.newInstance());
                                ToastUtils.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                final LeaveShowListBean bean = Convert.fromJson(s, LeaveShowListBean.class);
                                mList.clear();
                                if (bean.getStatus() == 0) {
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    recyclerView.scrollToPosition(mIndex);
                                    adapter = new LeaveAdapter(getDate(bean.getData()));
                                    recyclerView.setAdapter(adapter);
                                    llBackground.setBackgroundResource(R.color.grey_line_table);
                                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                        @Override
                                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                            sign = "1";
                                            types = "1";

                                            switch (view.getId()) {
                                                case R.id.tv_morning:
                                                    if (mList.get(position).getMorning().equals("\ue611")) {
                                                        startForResult(AddTripFragment.newInstance(titles,mList.get(position),sign,types), 333);
                                                    } else if (!TextUtils.isEmpty(mList.get(position).getMorning())) {
                                                        showPopupWindow(adapter, position,  true);
                                                    }
                                                    break;

                                                case R.id.tv_after:
                                                    if (mList.get(position).getAfter().equals("\ue611")) {
                                                        startForResult(AddTripFragment.newInstance(titles,mList.get(position),sign,types), 333);
                                                    } else if (!TextUtils.isEmpty(mList.get(position).getAfter())) {
                                                        showPopupWindow(adapter, position,  true);
                                                    }
                                                    break;
                                                case R.id.tv_dinner:
                                                    if (mList.get(position).getDinner().equals("\ue611")) {
                                                        startForResult(AddTripFragment.newInstance(titles,mList.get(position),sign,types), 333);
                                                    } else if (!TextUtils.isEmpty(mList.get(position).getDinner())) {
                                                        showPopupWindow(adapter, position,  true);
                                                    }
                                                    break;
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

    /**
     * 显示PopupWindow
     */
    private void showPopupWindow(final BaseQuickAdapter adapter, final int position, boolean b) {

        String obj = GsonBuildUtil.GsonBuilder(mList.get(position));
        LatteLogger.d("dddddddddddd", "obj===" + obj);

        CustomTypeDialog dialog = new CustomTypeDialog(_mActivity, mList.get(position), session,titles);
        dialog.show();
        dialog.setCancelable(b);
        dialog.setOnDeleteListener(new CustomTypeDialog.onDeleteListener() {
            @Override
            public void onClickDelete() {
                initRecyclerView();
                adapter.notifyItemRemoved(position);
            }
        });
        dialog.setOnMobListener(new CustomTypeDialog.onMobClassListener() {
            @Override
            public void onClickMobClass() {
                sign = "2";
                types = "1";
                startForResult(AddTripFragment.newInstance(titles,mList.get(position),sign,types), 3331);
            }
        });

    }

    private String JsonStartTime, JsonEndTime, startHour, endHour;

    private int sTime, eTime, sDay, eDay;
    private List<LeaveNewBean> mList = new ArrayList<>(); //整个一个月的数据

    private List<LeaveNewBean> getDate(List<LeaveShowListBean.DataBean> mShowList) {
        try {
            for (int i = 0; i < mDateList.size(); i++) {
                LeaveNewBean item = new LeaveNewBean();
                Date start = null;
                start = DateUtils.setDate(mDateList.get(i));
                curDay = Day.format(start);
                item.setDate(curDay + "/" + dateToWeek(mDateList.get(i)));

                Date ListDate = DateUtils.setDate(YearMonthDay, mDateList.get(i));
                Date todayDate = DateUtils.setDate(YearMonthDay, today);
                if (ListDate.getTime() >= todayDate.getTime()) {
                    if (item.getMorning() == null) {
                        item.setMorning("\ue611");
                    }
                    if (item.getAfter() == null) {
                        item.setAfter("\ue611");
                    }
                    if (item.getDinner() == null) {
                        item.setDinner("\ue611");
                    }
                } else {
                    item.setMorning("");
                    item.setAfter("");
                    item.setDinner("");
                }
                for (int j = 0; j < mShowList.size(); j++) {
                    JsonStartTime = mShowList.get(j).getStartTime();
                    JsonEndTime = mShowList.get(j).getEndTime();

                    sDay = Integer.parseInt(Day.format(DateUtils.setDate(yearMonthdayHourMin, JsonStartTime)));
                    eDay = Integer.parseInt(Day.format(DateUtils.setDate(yearMonthdayHourMin, JsonEndTime)));

                    int curMDay = Integer.parseInt(curDay);

                    Date eYearMonth = sfYearMonth.parse(sfYearMonth.format(DateUtils.setDate(yearMonthdayHourMin, JsonEndTime)));
                    Date titleYearMonth = sfYearMonth.parse(tvDate.getText().toString());

                    if (titleYearMonth.getTime() < eYearMonth.getTime() || titleYearMonth.getTime() > eYearMonth.getTime()) { //跨月的比较
                        List<String> mDateList2 = DateUtils.queryData(YearMonthDay, JsonStartTime, JsonEndTime);

                        for (int m = 0; m < mDateList2.size(); m++) {
                            String mListStartDate = sfYearMonth.format(DateUtils.setDate(YearMonthDay, mDateList2.get(m)));
                            if (tvDate.getText().toString().equals(mListStartDate)) { //同一个月的比较
                                LatteLogger.d("ddd","curMDay==="+curMDay+"==sDay=="+sDay+"==mDateList2=="+mDateList2.get(m));
                                if (curMDay == sDay) {
                                    if (sTime >= 8 && sTime < 13) {
                                        item.setMorning(mShowList.get(j).getType());
                                        item.setAfter(mShowList.get(j).getType());
                                        item.setDinner(mShowList.get(j).getType());
                                        item.setStatus(mShowList.get(j).getState());
                                        item.setStartTime(JsonStartTime);
                                        item.setEndTime(JsonEndTime);
                                        item.set_id(mShowList.get(j).getId());
                                        item.setReason(mShowList.get(j).getReason());
                                        item.setDestination(mShowList.get(j).getDestination());
                                        item.setLongTime(mShowList.get(j).getLeaveTime());
                                        item.setUserName(mShowList.get(j).getUserName());

                                    } else if (sTime >= 13 && sTime < 18) {  //开始时间 中上
                                        item.setAfter(mShowList.get(j).getType());
                                        item.setDinner(mShowList.get(j).getType());
                                        item.setStatus(mShowList.get(j).getState());
                                        item.setStartTime(JsonStartTime);
                                        item.setEndTime(JsonEndTime);
                                        item.set_id(mShowList.get(j).getId());
                                        item.setReason(mShowList.get(j).getReason());
                                        item.setDestination(mShowList.get(j).getDestination());
                                        item.setLongTime(mShowList.get(j).getLeaveTime());
                                        item.setUserName(mShowList.get(j).getUserName());

                                    } else {  //开始时间 晚上
                                        item.setDinner(mShowList.get(j).getType());
                                        item.setStatus(mShowList.get(j).getState());
                                        item.setStartTime(JsonStartTime);
                                        item.setEndTime(JsonEndTime);
                                        item.set_id(mShowList.get(j).getId());
                                        item.setReason(mShowList.get(j).getReason());
                                        item.setDestination(mShowList.get(j).getDestination());
                                        item.setLongTime(mShowList.get(j).getLeaveTime());
                                        item.setUserName(mShowList.get(j).getUserName());

                                    }
                                }
                            } else { //不同月份的比较
                                LatteLogger.d("ddd", "curDay===" + curMDay + "==sDay==" + sDay + "==mDateList2==" + mDateList2.get(m));
                            }
                        }
                    } else { //同月的比较
                        getShowDateMethod(mShowList, item, j, curMDay); //一个以内，所有的方法
                    }
                }
                mList.add(item);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mList;
    }

    private void getShowDateMethod(List<LeaveShowListBean.DataBean> mShowList, LeaveNewBean item, int j, int curMDay) throws ParseException {
        //获取小时数
        startHour = Hour.format(DateUtils.setDate(yearMonthdayHourMin, JsonStartTime));
        endHour = Hour.format(DateUtils.setDate(yearMonthdayHourMin, JsonEndTime));
        sTime = Integer.parseInt(startHour);
        eTime = Integer.parseInt(endHour);

        if (curMDay == eDay) {
            if (sDay == eDay) {//一天以内的判断
                inflateData(mShowList, item, j);//一天以内的方法
            }
        }
        if (curMDay >= sDay && curMDay <= eDay) { //跨天
            for (int e = sDay; e < eDay; e++) {
                if (curMDay == sDay) {
                    if (sTime >= 8 && sTime < 13) { //开始时间 早上
                        item.setMorning(mShowList.get(j).getType());
                        item.setAfter(mShowList.get(j).getType());
                        item.setDinner(mShowList.get(j).getType());
                        item.setStatus(mShowList.get(j).getState());
                        item.setStartTime(JsonStartTime);
                        item.setEndTime(JsonEndTime);
                        item.set_id(mShowList.get(j).getId());
                        item.setReason(mShowList.get(j).getReason());
                        item.setDestination(mShowList.get(j).getDestination());
                        item.setLongTime(mShowList.get(j).getLeaveTime());
                        item.setUserName(mShowList.get(j).getUserName());

                    } else if (sTime >= 13 && sTime < 18) {  //开始时间 中上
                        item.setAfter(mShowList.get(j).getType());
                        item.setDinner(mShowList.get(j).getType());
                        item.setStatus(mShowList.get(j).getState());
                        item.setStartTime(JsonStartTime);
                        item.setEndTime(JsonEndTime);
                        item.set_id(mShowList.get(j).getId());
                        item.setReason(mShowList.get(j).getReason());
                        item.setDestination(mShowList.get(j).getDestination());
                        item.setLongTime(mShowList.get(j).getLeaveTime());
                        item.setUserName(mShowList.get(j).getUserName());

                    } else {  //开始时间 晚上
                        item.setDinner(mShowList.get(j).getType());
                        item.setStatus(mShowList.get(j).getState());
                        item.setStartTime(JsonStartTime);
                        item.setEndTime(JsonEndTime);
                        item.set_id(mShowList.get(j).getId());
                        item.setReason(mShowList.get(j).getReason());
                        item.setDestination(mShowList.get(j).getDestination());
                        item.setLongTime(mShowList.get(j).getLeaveTime());
                        item.setUserName(mShowList.get(j).getUserName());

                    }
                } else if (curMDay == eDay) {
                    LatteLogger.d("curMDay", curMDay);
                    if (eTime >= 8 && eTime < 13) {  //结束时间 早上
                        item.setMorning(mShowList.get(j).getType());
                        item.setStatus(mShowList.get(j).getState());
                        item.setStartTime(JsonStartTime);
                        item.setEndTime(JsonEndTime);
                        item.set_id(mShowList.get(j).getId());
                        item.setReason(mShowList.get(j).getReason());
                        item.setDestination(mShowList.get(j).getDestination());
                        item.setLongTime(mShowList.get(j).getLeaveTime());
                        item.setUserName(mShowList.get(j).getUserName());

                    } else if (eTime >= 13 && eTime < 18) {  //结束时间 中午
                        item.setMorning(mShowList.get(j).getType());
                        item.setAfter(mShowList.get(j).getType());
                        item.setStatus(mShowList.get(j).getState());
                        item.setStartTime(JsonStartTime);
                        item.setEndTime(JsonEndTime);
                        item.set_id(mShowList.get(j).getId());
                        item.setReason(mShowList.get(j).getReason());
                        item.setDestination(mShowList.get(j).getDestination());
                        item.setLongTime(mShowList.get(j).getLeaveTime());
                        item.setUserName(mShowList.get(j).getUserName());

                    } else {  //结束时间 晚上
                        item.setMorning(mShowList.get(j).getType());
                        item.setAfter(mShowList.get(j).getType());
                        item.setDinner(mShowList.get(j).getType());
                        item.setStatus(mShowList.get(j).getState());
                        item.setStartTime(JsonStartTime);
                        item.setEndTime(JsonEndTime);
                        item.set_id(mShowList.get(j).getId());
                        item.setReason(mShowList.get(j).getReason());
                        item.setDestination(mShowList.get(j).getDestination());
                        item.setLongTime(mShowList.get(j).getLeaveTime());
                        item.setUserName(mShowList.get(j).getUserName());

                    }
                } else {
                    item.setMorning(mShowList.get(j).getType());
                    item.setAfter(mShowList.get(j).getType());
                    item.setDinner(mShowList.get(j).getType());
                    item.setStartTime(JsonStartTime);
                    item.setEndTime(JsonEndTime);
                    item.set_id(mShowList.get(j).getId());
                    item.setReason(mShowList.get(j).getReason());
                    item.setDestination(mShowList.get(j).getDestination());
                    item.setLongTime(mShowList.get(j).getLeaveTime());
                    item.setUserName(mShowList.get(j).getUserName());

                }
            }
        }
    }

    private void inflateData(List<LeaveShowListBean.DataBean> mShowList, LeaveNewBean item, int j) {
        if (sTime >= 8 && sTime < 13) { //上午跨度到下午
            if (eTime >= 8 && eTime < 13) { //早上
                item.setMorning(mShowList.get(j).getType());
                item.setStatus(mShowList.get(j).getState());
                item.setStartTime(JsonStartTime);
                item.setEndTime(JsonEndTime);
                item.set_id(mShowList.get(j).getId());
                item.setReason(mShowList.get(j).getReason());
                item.setDestination(mShowList.get(j).getDestination());
                item.setLongTime(mShowList.get(j).getLeaveTime());
                item.setUserName(mShowList.get(j).getUserName());

            } else { //早下
                item.setMorning(mShowList.get(j).getType());
                item.setAfter(mShowList.get(j).getType());
                item.setStatus(mShowList.get(j).getState());
                item.setStartTime(JsonStartTime);
                item.setEndTime(JsonEndTime);
                item.set_id(mShowList.get(j).getId());
                item.setReason(mShowList.get(j).getReason());
                item.setDestination(mShowList.get(j).getDestination());
                item.setLongTime(mShowList.get(j).getLeaveTime());
                item.setUserName(mShowList.get(j).getUserName());

            }
        } else if (sTime >= 13 && sTime < 18) { //下午跨度到晚上
            if (eTime >= 13 && eTime < 18) { //下午
                item.setAfter(mShowList.get(j).getType());
                item.setStatus(mShowList.get(j).getState());
                item.setStartTime(JsonStartTime);
                item.setEndTime(JsonEndTime);
                item.set_id(mShowList.get(j).getId());
                item.setReason(mShowList.get(j).getReason());
                item.setDestination(mShowList.get(j).getDestination());
                item.setLongTime(mShowList.get(j).getLeaveTime());
                item.setUserName(mShowList.get(j).getUserName());

            } else { //下午晚班
                item.setAfter(mShowList.get(j).getType());
                item.setDinner(mShowList.get(j).getType());
                item.setStatus(mShowList.get(j).getState());
                item.setStartTime(JsonStartTime);
                item.setEndTime(JsonEndTime);
                item.set_id(mShowList.get(j).getId());
                item.setReason(mShowList.get(j).getReason());
                item.setDestination(mShowList.get(j).getDestination());
                item.setLongTime(mShowList.get(j).getLeaveTime());
                item.setUserName(mShowList.get(j).getUserName());

            }
        } else if (sTime >= 18 && sTime < 24 || sTime >= 0 && sTime < 8) { //晚上跨度到早上
            if (eTime >= 18 && eTime <= 24 || eTime >= 0 && eTime < 8) { //晚班
                item.setDinner(mShowList.get(j).getType());
                item.setStatus(mShowList.get(j).getState());
                item.setStartTime(JsonStartTime);
                item.setEndTime(JsonEndTime);
                item.set_id(mShowList.get(j).getId());
                item.setReason(mShowList.get(j).getReason());
                item.setDestination(mShowList.get(j).getDestination());
                item.setLongTime(mShowList.get(j).getLeaveTime());
                item.setUserName(mShowList.get(j).getUserName());

            } else { //晚早
                item.setDinner(mShowList.get(j).getType());
                item.setMorning(mShowList.get(j).getType());
                item.setStatus(mShowList.get(j).getState());
                item.setStartTime(JsonStartTime);
                item.setEndTime(JsonEndTime);
                item.set_id(mShowList.get(j).getId());
                item.setReason(mShowList.get(j).getReason());
                item.setDestination(mShowList.get(j).getDestination());
                item.setLongTime(mShowList.get(j).getLeaveTime());
                item.setUserName(mShowList.get(j).getUserName());

            }
        } else {//一天 从早上8点到晚上18点以后
            item.setMorning(mShowList.get(j).getType());
            item.setAfter(mShowList.get(j).getType());
            item.setDinner(mShowList.get(j).getType());
            item.setStatus(mShowList.get(j).getState());
            item.setStartTime(JsonStartTime);
            item.setEndTime(JsonEndTime);
            item.set_id(mShowList.get(j).getId());
            item.setReason(mShowList.get(j).getReason());
            item.setDestination(mShowList.get(j).getDestination());
            item.setLongTime(mShowList.get(j).getLeaveTime());
            item.setUserName(mShowList.get(j).getUserName());

        }
    }

    public int oneDay = 0; //这个初始日子

    public int getOneDay() {
        String countStr = tvDate.getText().toString().trim();
        if (countStr != null) {
            oneDay = Integer.valueOf(countStr);
        }
        return oneDay;
    }

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtils.getFristDayOfMonth(YearMonthDay, oneDay);
        lastDay = DateUtils.getLastDayOfMonth(YearMonthDay, oneDay);
        mDateList = DateUtils.queryData(YearMonthDay, firstDay, lastDay);
        LatteLogger.d("monday", firstDay + " 某月第一天 " + lastDay + "某月最后一天");
        tvDate.setText(DateUtils.getLastMonth(oneDay));
        initRecyclerView();

    }

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                break;
            case R.id.btn_up_pager:
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneMonth(oneDay);
                break;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    /**
     * 接收各类分发事件
     *
     * @param event 事件
     */
    @Override
    protected void receiveEvent(Event event) {
        switch (event.getCode()) {
            case FinalClass.ME_info://请假
                initRecyclerView();
                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == 333 && resultCode == RESULT_OK && data != null) {
            String msg = data.getString("Msg");
            LatteLogger.d("出差", msg);
            initRecyclerView();
            adapter.notifyDataSetChanged();
        } else if (requestCode == 3331 && resultCode == RESULT_OK && data != null) {
            initRecyclerView();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initRecyclerView();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}

