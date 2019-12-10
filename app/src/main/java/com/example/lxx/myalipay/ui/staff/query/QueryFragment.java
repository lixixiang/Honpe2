package com.example.lxx.myalipay.ui.staff.query;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.query.adapter.QueryAdapter;
import com.example.lxx.myalipay.ui.staff.query.bean.VipBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.TotalQueryFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position10.CarInfoFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position11.SearchShoppingFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position12.SearchRepairFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position13.CustomCookerOrderFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position14.SubContractFragment2;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.EmployeeWithOrderActivity;
import com.example.lxx.myalipay.ui.staff.query.ui.position17.LocationFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.LeaveFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position3.DormFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position4.OutFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position5.OverTimeFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.CardQueryFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.StayFragment;
import com.example.lxx.myalipay.widget.SpaceItemDecoration;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.font.FontTextView4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by lxx at 2019/11/27 13:49
 * 描述:
 */
public class QueryFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.activity_recycler_view)
    LinearLayout activityRecyclerView;

    private static int titleFlag = 1;
    private static int GridFlag = 2;


    private QueryAdapter adapter;
    private List<VipBean> data = new ArrayList<>();

    //人事管理
    private String[] icons0 = {"\ue63d", "\ue609", "\ue622", "\ue798", "\ue75b", "\ue649", "\ue628", "\ue603"};
    private String[] titles0 = {"考勤", "请假", "出差", "外出", "加班", "补卡", "住宿", "培训"};
    private int[] FontColor0 = {R.color.green, R.color.blue, R.color.violet_7B1FA2, R.color.color_confirm_normal, R.color.color_select_date_dialog_edit_text_bg_focus,
            R.color.google_red, R.color.blue_ocean, R.color.orange, R.color.green_38AE80};
    //行政管理
    private String[] icons1 = {"\ue63a", "\ue617", "\ue60f", "\ue6d8", "\ue60b", "\ue6c5", "\ue627", "\ue611"};
    private String[] titles1 = {"派车", "采购", "维修", "客户订餐", "委外加工", "物品领用", "员工点餐", "定位"};
    private int[] FontColor1 = {R.color.blue_dark, R.color.red, R.color.yellow_gold, R.color.green,
            R.color.color_select_date_dialog_edit_text_bg_focus, R.color.red_254, R.color.color_confirm_normal, R.color.google_coffee};

    //业务管理
    private String[] icons3 = {"\ue61e", "", "", ""};
    private String[] titles3 = {"客户管理", "", "", ""};
    private int[] FontColor3 = {R.color.google_coffee, R.color.brown3, android.R.color.transparent, android.R.color.transparent};


    public static QueryFragment newInstance() {
        QueryFragment fragment = new QueryFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("查询");
        GridLayoutManager gManager = new GridLayoutManager(_mActivity, 4);
        gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 9 || position == 18 || position == 23 || position == 28) {
                    return 4;
                } else {
                    return 1;
                }
            }
        });
        int space = getResources().getDimensionPixelSize(R.dimen._1dp);
        recyclerView.addItemDecoration(new SpaceItemDecoration(space));
        recyclerView.setLayoutManager(gManager);
        adapter = new QueryAdapter(_mActivity, getData());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new QueryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type) {
                switch (position) {
                    case 0:
                        break;
                    case 1://考勤
                        start(TotalQueryFragment.newInstance());
                        break;
                    case 2://请假
                        start(LeaveFragment.newInstance(titles0[1]));
                        break;
                    case 3://出差
                        start(DormFragment.newInstance(titles0[2]));
                        break;
                    case 4://外出
                        start(OutFragment.newInstance(titles0[3]));
                        break;
                    case 5://加班
                        start(OverTimeFragment.newInstance(titles0[4]));
                        break;
                    case 6://补卡
                        start(CardQueryFragment.newInstance(titles0[5]));
                        break;
                    case 7://住宿
                        start(StayFragment.newInstance(titles0[6]));
                        break;
                    case 8://培训
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 9:
                        break;
                    case 10://派车
                        start(CarInfoFragment.newInstance());
                        break;
                    case 11://常规采购
                        start(SearchShoppingFragment.newInstance());
                        break;
                    case 12://维修
                        start(SearchRepairFragment.newInstance());
                        break;
                    case 13://订餐
                        start(CustomCookerOrderFragment.newInstance());
                        break;
                    case 14://委外加工
                        start(SubContractFragment2.newInstance());
                        break;
                    case 15://物品领用
                        break;
                    case 16://员工点餐
                        startActivity(new Intent(_mActivity, EmployeeWithOrderActivity.class));
                        break;
                    case 17: //定位
                        start(LocationFragment.newInstance("外勤定位"));
                        break;
                    case 18:
                        break;
                    case 19://报销
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 20://收款
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 21://付款
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 22://备用金
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 23:
                        break;
                    case 24://客户管理
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 25://客户添加
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 26:
                        break;
                    case 27:
                        break;
                    case 28:
                        break;
                    case 29://总监
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 30://主管
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 31://业务部
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    case 32://事业部
                        ToastUtils.getInstance().showToast("功能正在研发中...");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private List<VipBean> getData() {
        VipBean bean = new VipBean();
        bean.setTheme("人事管理");
        bean.setType(titleFlag);
        data.add(bean);

        for (int i = 0; i < titles0.length; i++) {
            VipBean item = new VipBean();
            item.setTitle(titles0[i]);
            item.setIconFont(icons0[i]);
            item.setIconFontColor(FontColor0[i]);
            item.setType(GridFlag);
            data.add(item);
        }

        VipBean bean1 = new VipBean();
        bean1.setTheme("行政管理");
        bean1.setType(titleFlag);
        data.add(bean1);

        for (int i = 0; i < titles1.length; i++) {
            VipBean item1 = new VipBean();
            item1.setTitle(titles1[i]);
            item1.setIconFont(icons1[i]);
            item1.setIconFontColor(FontColor1[i]);
            item1.setType(GridFlag);
            data.add(item1);
        }

        VipBean bean3 = new VipBean();
        bean3.setTheme("业务管理");
        bean3.setType(titleFlag);
        data.add(bean3);

        for (int i = 0; i < titles3.length; i++) {
            VipBean item3 = new VipBean();
            item3.setTitle(titles3[i]);
            item3.setIconFont(icons3[i]);
            item3.setIconFontColor(FontColor3[i]);
            item3.setType(GridFlag);
            data.add(item3);
        }

        return data;
    }
}
