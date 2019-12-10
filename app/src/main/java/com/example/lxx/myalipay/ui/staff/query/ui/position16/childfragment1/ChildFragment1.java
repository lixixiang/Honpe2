package com.example.lxx.myalipay.ui.staff.query.ui.position16.childfragment1;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean1;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.childfragment1.adapter.MyListView;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child
 * @date: 2018/10/15 10:35
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：员工点餐1  fragment_meal1
 */
public class ChildFragment1 extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.recyclerView)
    ListView mListView;
    @BindView(R.id.ck_breakfast)
    CheckBox ckBreakfast;
    @BindView(R.id.ck_lunch)
    CheckBox ckLunch;
    @BindView(R.id.ck_after)
    CheckBox ckAfter;
    @BindView(R.id.ck_night)
    CheckBox ckNight;
    @BindView(R.id.ck_all)
    CheckBox ckAll;

    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    List<StatisticsBean1.DataBean> mList = new ArrayList<>();
    List<String> mDateList = new ArrayList<>();
    String session, UserID, firstDay, lastDay, today;
    MyListView adapter;

    @Override
    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.ck_breakfast:
                if (isChecked) {
                    adapter.selectVertical();
                } else {
                    adapter.cancelVertical();
                }
                break;
            case R.id.ck_lunch:
                if (isChecked) {
                    adapter.selectVertical2();
                } else {
                    adapter.cancelVertical2();
                }
                break;
            case R.id.ck_after:
                if (isChecked) {
                    adapter.selectVertical3();
                } else {
                    adapter.cancelVertical3();
                }
                break;
            case R.id.ck_night:
                if (isChecked) {
                    adapter.selectVertical4();
                } else {
                    adapter.cancelVertical4();
                }
                break;
            case R.id.ck_all:
                if (isChecked) {
                    ckBreakfast.setChecked(true);
                    ckLunch.setChecked(true);
                    ckAfter.setChecked(true);
                    ckNight.setChecked(true);
                    adapter.selectAll();

                } else {
                    ckBreakfast.setChecked(false);
                    ckLunch.setChecked(false);
                    ckAfter.setChecked(false);
                    ckNight.setChecked(false);
                    adapter.cancelAll();
                }

                break;
            default:
                break;
        }
    }

    public static ChildFragment1 newInstance() {
        ChildFragment1 fragment = new ChildFragment1();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.include_tab_checkbox_head;
    }

    @Override
    protected void initView() {
        getAllDate();
        getJsonData();
        //全选的点击监听
        ckBreakfast.setOnCheckedChangeListener(this);
        ckLunch.setOnCheckedChangeListener(this);
        ckAfter.setOnCheckedChangeListener(this);
        ckNight.setOnCheckedChangeListener(this);
        ckAll.setOnCheckedChangeListener(this);
    }



    private void getJsonData() {
        EasyHttp.post(Constants.YGREPORTMEETLIST + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
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
                        LatteLogger.d("result", s);
                        final StatisticsBean1 bean = Convert.fromJson(s, StatisticsBean1.class);
                        adapter = new MyListView(_mActivity, getData(bean.getData()));
                        mListView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 获取本月所有日期
     *
     * @return
     */
    private List<StatisticsBean1.DataBean> getData(List<StatisticsBean1.DataBean> data) {
        mList.clear(); //防止叠加
        Map<String, List<StatisticsBean1.DataBean>> maps = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            String day = data.get(i).getMealDate();
            List<StatisticsBean1.DataBean> listDay = maps.get(day);
            if (listDay == null) {
                listDay = new ArrayList<>();
            }
            StatisticsBean1.DataBean item = new StatisticsBean1.DataBean();
            item.setHasBreakFast(data.get(i).isHasBreakFast());
            item.setHasLunch(data.get(i).isHasLunch());
            item.setHasDinner(data.get(i).isHasDinner());
            item.setHasMidnight(data.get(i).isHasMidnight());
            listDay.add(item);
            maps.put(day, listDay);
        }

        for (int i = 0; i < mDateList.size(); i++) {
            StatisticsBean1.DataBean item = new StatisticsBean1.DataBean();
            item.setMealDate(mDateList.get(i));
            item.setUserID("");
            item.setWeek(DateUtils.getWeek(mDateList.get(i)));
            for (String day : maps.keySet()) {
                if (day.equals(mDateList.get(i))) {
                    List<StatisticsBean1.DataBean> listDatas = maps.get(day);
                    for (int j = 0; j < listDatas.size(); j++) {
                        StatisticsBean1.DataBean isSelector = listDatas.get(j);
                        item.setHasBreakFast(isSelector.isHasBreakFast());
                        item.setHasLunch(isSelector.isHasLunch());
                        item.setHasDinner(isSelector.isHasDinner());
                        item.setHasMidnight(isSelector.isHasMidnight());
                    }
                }
            }
            mList.add(item);
        }
        return mList;
    }

    @Override
    public void onResume() {
        super.onResume();
        ProgressUtils.disLoadView(_mActivity,0);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.REPORT_STAFF:
                StatisticsBean1.DataBean data = (StatisticsBean1.DataBean) event.getData();
                if (data != null)
                    iObjectToActivity.submit1(mList);
                break;
        }
    }

    private void getAllDate() {
        session = (String) MyApplication.get(_mActivity, Session, "");
        UserID = (String) MyApplication.get(_mActivity, FinalClass.UserCode, "");

        firstDay = sdf1.format(new Date());
        lastDay = DateUtils.getBackDayOfMonth(sdf1);
        mDateList = DateUtils.getDays(firstDay, lastDay);
    }

    IObjectToActivity iObjectToActivity = null;

    public interface IObjectToActivity {
        void submit1(List<StatisticsBean1.DataBean> list);
    }

    @Override
    public void onAttach(Activity activity) {
        iObjectToActivity = (IObjectToActivity) activity;
        super.onAttach(activity);
    }
}


