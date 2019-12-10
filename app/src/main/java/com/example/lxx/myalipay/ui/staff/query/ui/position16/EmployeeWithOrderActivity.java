package com.example.lxx.myalipay.ui.staff.query.ui.position16;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.adapter.MyPagerAdapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.Fragment3Priase.detail.FragmentPraise;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean1;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean2;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.callback.CallBackChildFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.childfragment1.ChildFragment1;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.childfragment2.ChildFragment2;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.childfragment3.ChildFragment3;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.TabEntity;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.font.FontTextView4;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.Session;


public class EmployeeWithOrderActivity extends BaseActivity implements ChildFragment1.IObjectToActivity, ChildFragment2.IObjectToActivity, CallBackChildFragment {
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
    @BindView(R.id.tab)
    SlidingTabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;

    private MyPagerAdapter adapter;

    private final String[] mTitles = {"报餐", "选材统计", "菜品评价"};
    private final String[] finishes = {"确定", "确定", "提交"};
    private ArrayList<Fragment> mFragments;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    String session;
    String jsonString;

    @Override
    public int getLayoutId() {
        return R.layout.activity_employee_with_order;
    }

    /**
     * 提交餐评接口
     */
    private void getReportMeet3() {
        EasyHttp.post(Constants.GETCOMMENT + session)
                .params("MealDate", mainBean.getMealDate())
                .params("MealTimes", mainBean.getMealTime())
                .params("Score", mainBean.getScore())
                .params("Content", mainBean.getContent())
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
                        LatteLogger.d(e.getCode());
                        if (e.getMessage().equals("未知错误")) {
                            ProgressUtils.disLoadView(_mActivity, 0);
                        }
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d(s);
                        getInfo info = Convert.fromJson(s, getInfo.class);
                        if (info.getStatus() == 0) {
                            pullToRefresh();
                            ToastUtils.getInstance().showToast("提交成功");
                        } else {
                            ToastUtils.getInstance().showToast(info.getMsg());
                        }
                    }
                });
    }

    /**
     * 提交员工点餐菜品接口
     */
    private void getReportMeet2() {
        EasyHttp.post(Constants.YGCOMMITMIUE + session)
                .params("Data", jsonString)
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
                        LatteLogger.d(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d(s);
                        getInfo info = Convert.fromJson(s, getInfo.class);
                        if (info.getStatus() == 0) {
                            ToastUtils.getInstance().showToast("提交成功");
                        } else {
                            ToastUtils.getInstance().showToast(info.getMsg());
                        }
                    }
                });
    }

    /**
     * 报餐提交
     */
    private void getReportMeet() {
        if (jsonString != null) {
            try {
                JSONObject object = new JSONObject();
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(jsonString);
                object.put("Data", jsonArray);
                String params = String.valueOf(object);
                LatteLogger.d("param", params);

                EasyHttp.post(Constants.YGREPORTMEET + session)
                        .upJson(params)
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
                                ToastUtils.getInstance().showToast(e.getMessage());
                            }

                            @Override
                            public void onSuccess(String result) {
                                LatteLogger.d("tag", result);
                                ToastUtils.getInstance().showToast("提交成功！");
                            }
                        });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initView() {
        tvEnd.setVisibility(View.VISIBLE);
        session = (String) MyApplication.get(_mActivity, Session, "");
        initToolbarNav(llBack);
        iniTopTitle();
    }

    public void pullToRefresh() {
        mFragments.clear();
        mFragments.add(ChildFragment1.newInstance());
        mFragments.add(ChildFragment2.newInstance());
        mFragments.add(ChildFragment3.newInstance());
        adapter.notifyDataSetChanged();
    }

    private void iniTopTitle() {
        mFragments = new ArrayList<>();
        mFragments.add(ChildFragment1.newInstance());
        mFragments.add(ChildFragment2.newInstance());
        mFragments.add(ChildFragment3.newInstance());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }

        adapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vp.setAdapter(adapter);
        // tabLayout.setTabData(mTabEntities);
        tabLayout.setViewPager(vp);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(final int position) {
                vp.setCurrentItem(position);
                tvEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == 0) {
                            getReportMeet();
                        } else if (position == 1) {
                            getReportMeet2();
                        } else {
                            getReportMeet3();
                        }

                    }
                });
                tvTitle.setText(mTitles[position]);
                tvEnd.setText(finishes[position]);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                tabLayout.setCurrentTab(position);
                tvEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == 0) {
                            getReportMeet();
                        } else if (position == 1) {
                            getReportMeet2();
                        } else {
                            getReportMeet3();
                        }

                    }
                });
                tvTitle.setText(mTitles[position]);
                tvEnd.setText(finishes[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp.setCurrentItem(0);
        tvTitle.setText(mTitles[0]);
        tvEnd.setText(finishes[0]);
        //按钮
        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReportMeet();
            }
        });
    }


    @Override
    public void submit2(List<StatisticsBean2.DataBean> list) {
        List<StatisticsBean2.DataBean> mListDataBean = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            StatisticsBean2.DataBean bean = new StatisticsBean2.DataBean();
            bean.setMealDate(list.get(i).getMealDate());
            bean.setMealTimes(list.get(i).getMealTimes());
            bean.setDishesDetails(getDataDetail(list.get(i).getDishesDetails()));
            mListDataBean.add(bean);
        }

        //界面2
        Gson gson = new Gson();
        jsonString = gson.toJson(mListDataBean);
        LatteLogger.d("jsonString2", jsonString);
    }

    private List<StatisticsBean2.DataBean.DishesDetailsBean> getDataDetail(List<StatisticsBean2.DataBean.DishesDetailsBean> list2) {
        List<StatisticsBean2.DataBean.DishesDetailsBean> list = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {
            StatisticsBean2.DataBean.DishesDetailsBean bean = new StatisticsBean2.DataBean.DishesDetailsBean();
            bean.setIsSelected(list2.get(i).getIsSelected());
            if (bean.getIsSelected() == 1) {
                bean.setDishes(list2.get(i).getDishes());
                bean.setIsSelected(list2.get(i).getIsSelected());
                list.add(bean);
            }
        }
        return list;
    }

    FragmentPraise.CommitMeatBean mainBean = new FragmentPraise.CommitMeatBean();

    @Override
    public void callBack(FragmentPraise.CommitMeatBean bean) {
        if (bean != null) {
            mainBean = bean;
        }

        Gson gson = new Gson();
        String json = gson.toJson(mainBean);
        LatteLogger.d("json", json);
    }

    @Override
    public void submit1(List<StatisticsBean1.DataBean> list) {
        //界面1
        Gson gson = new Gson();
        jsonString = gson.toJson(list);
        LatteLogger.d("jsonString1", jsonString);

    }

    public class getInfo {

        /**
         * Status : 0
         * Msg : 成功!
         * Data : null
         */

        private int Status;
        private String Msg;
        private Object Data;

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }

        public Object getData() {
            return Data;
        }

        public void setData(Object Data) {
            this.Data = Data;
        }
    }
}
