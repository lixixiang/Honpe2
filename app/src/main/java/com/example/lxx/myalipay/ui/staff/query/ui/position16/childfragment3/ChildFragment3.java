package com.example.lxx.myalipay.ui.staff.query.ui.position16.childfragment3;

import android.support.v4.app.Fragment;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.Fragment3Priase.detail.FragmentPraise;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean3;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.TabEntity;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child
 * @date: 2018/10/15 10:35
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：员工点餐
 */
public class ChildFragment3 extends BaseFragment {
    @BindView(R.id.tab)
    CommonTabLayout tab;
    private String[] meals = {"早餐", "午餐", "晚餐", "夜宵"};
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String toDay;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    public static ChildFragment3 newInstance() {
        ChildFragment3 fragment = new ChildFragment3();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_meal3;
    }

    @Override
    protected void initView() {
        toDay = sdf.format(new Date());
        initNetRequest();
    }

    /**
     * 做当天的数据
     */
    private void initNetRequest() {
        String session = (String) MyApplication.get(_mActivity, Session, "");
        EasyHttp.post(Constants.GETORDERLIST + session)
                .params("MealDate", toDay)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("Tag", s);
                        StatisticsBean3 result = Convert.fromJson(s, StatisticsBean3.class);
                        setViewPager(result.getData());
                    }
                });
    }

    private void setViewPager(List<StatisticsBean3.DataBean> beanList) {
        List<StatisticsBean3.DataBean> list = new ArrayList<>();
        if (fragments != null) {
            fragments.removeAll(beanList);
        }
        for (int i = 0; i < meals.length; i++) {
            mTabEntities.add(new TabEntity(meals[i]));
            fragments.add(FragmentPraise.newInstance(beanList.get(i)));
        }
        tab.setTabData(mTabEntities, _mActivity, R.id.frameLayout, fragments);
    }
}


