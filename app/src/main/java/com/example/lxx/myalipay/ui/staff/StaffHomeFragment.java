package com.example.lxx.myalipay.ui.staff;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.adapter.CommFragmentPagerAdapter;
import com.example.lxx.myalipay.ui.fragment.a_package.news.DetailContentFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.AppointHomeFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.logistics.LogisticsCheckFragment;
import com.example.lxx.myalipay.ui.staff.adapter.InnerAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ApplyActivity;
import com.example.lxx.myalipay.ui.staff.approve.ApproveMainActivity;
import com.example.lxx.myalipay.ui.staff.bean.InnerManagerBean;
import com.example.lxx.myalipay.ui.staff.query.QueryFragment;
import com.example.lxx.myalipay.ui.staff.viewpager_fragment.InnerMainFragment;
import com.example.lxx.myalipay.utils.TabEntity;
import com.example.lxx.myalipay.widget.SpaceItemDecoration;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.scan.ScanManager;
import com.flyco.dialog.widget.NormalListDialog;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bertsir.zbar.QrConfig;

/**
 * created by lxx at 2019/11/25 17:51
 * 描述:
 */
public class StaffHomeFragment extends BaseBackFragment {
    @BindView(R.id.ll_scan)
    LinearLayout llScan;
    @BindView(R.id.ll_order)
    LinearLayout llOrder;
    @BindView(R.id.ll_logistics)
    LinearLayout llLogistics;
    @BindView(R.id.ll_way)
    LinearLayout llWay;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.abl_bar)
    AppBarLayout ablBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.frameLay)
    FrameLayout frameLay;
    @BindView(R.id.tab)
    CommonTabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    Unbinder unbinder;

    //个人查询icon
    private String[] icon1 = {"\ue5b4", "\ue636", "\ue677", "\ue634", "\ue7b6"};
    private String[] title1 = {"申请", "审批", "查询", "工作记录", "工作计划"};
    private int[] iconColor1 = {R.color.green, R.color.yellow_gold, R.color.blue_dark, R.color.google_red, R.color.violet_7B1FA2};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] vpContent = {"公司通告", "员工讨论"};
    private int[] unSelectedColor = {R.color.background_e, R.color.background_e, R.color.background_e};
    private int[] selectedColor = {R.color.blue_dark, R.color.blue_dark, R.color.blue_dark};
    String[] StrScans,Apptext;

    private InnerAdapter adapter;
    private List<InnerManagerBean> data;
    private List<Fragment> fragments = new ArrayList<>();

    public static StaffHomeFragment newInstance() {
        StaffHomeFragment fragment = new StaffHomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_staff_home_fragment;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("个人首页");
        GridLayoutManager gManager = new GridLayoutManager(_mActivity, 4);
        StrScans = getResources().getStringArray(R.array.scan);
        Apptext = getResources().getStringArray(R.array.home_text);

        gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        int space = getResources().getDimensionPixelSize(R.dimen._2dp);
        recyclerView.addItemDecoration(new SpaceItemDecoration(space));
        recyclerView.setLayoutManager(gManager);
        adapter = new InnerAdapter(getData());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0: //我的申请
                        startActivity(new Intent(_mActivity, ApplyActivity.class));
                        break;
                    case 1: //我的审批
                        startActivity(new Intent(_mActivity,ApproveMainActivity.class));
                        break;
                    case 2: //我的查询
                        start(QueryFragment.newInstance());
                        break;
                    case 3: //工作记录
                        //  start(MyReportFragment.newInstance(title1[position]));
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 4: //工作计划
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                }
            }
        });
        setViewPager();//设置界面
        tl(); //通知消息
    }

    private void setViewPager() {
        for (int i = 0; i < vpContent.length; i++) {
            mTabEntities.add(new TabEntity(vpContent[i], selectedColor[i], unSelectedColor[i]));
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void tl() {
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        for (int i = 0; i < vpContent.length; i++) {
            fragments.add(InnerMainFragment.newInstance(vpContent[i], i));
        }

        viewPager.setAdapter(new CommFragmentPagerAdapter(getChildFragmentManager(), fragments, vpContent));
        tab.setTabData(mTabEntities);
    }

    private List<InnerManagerBean> getData() {
        data = new ArrayList<>();
        for (int i = 0; i < title1.length; i++) {
            InnerManagerBean item = new InnerManagerBean();
            item.setTitle(title1[i]);
            item.setContent(icon1[i]);
            item.setFontColor(iconColor1[i]);
            item.setMsgNum(title1.length);
            data.add(item);
        }
        return data;
    }

    @OnClick({R.id.ll_scan, R.id.ll_order, R.id.ll_logistics, R.id.ll_way})
    public void onViewClicked(View view) {
        switch (view.getId()) {
                 case R.id.ll_scan:
                //扫一扫
                NormalListDialog normalListDialog = new NormalListDialog(_mActivity, StrScans);
                normalListDialog.title(getString(R.string.scan) + getString(R.string.style))
                        .layoutAnimation(null)
                        .show();
                normalListDialog.setOnOperItemClickL((parent, view1, position, id) -> {
                    switch (position) {
                        case 0:
                            ScanManager.startScan(_mActivity, QrConfig.TYPE_QRCODE, QrConfig.SCANVIEW_TYPE_QRCODE);
                            break;
                        case 1:
                            ScanManager.startScan(_mActivity, QrConfig.TYPE_BARCODE, QrConfig.SCANVIEW_TYPE_BARCODE);
                            break;
                    }
                    normalListDialog.dismiss();
                });
                break;
            case R.id.ll_order:
                //访客预约
               start(AppointHomeFragment.newInstance(Apptext[1]));
                break;
            case R.id.ll_logistics:
                //物流查询
                start(LogisticsCheckFragment.newInstance(getString(R.string.Logistics)));
                break;
            case R.id.ll_way:
                //联系方式
                start(DetailContentFragment.newInstance("联系方式", "508", ""));
                break;
        }
    }
}
