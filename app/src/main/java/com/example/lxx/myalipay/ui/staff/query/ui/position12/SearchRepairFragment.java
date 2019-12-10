package com.example.lxx.myalipay.ui.staff.query.ui.position12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.adapter.RepairAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean.RepairBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by lxx at 2019/12/3 21:35
 * 描述:维修查询
 */
public class SearchRepairFragment extends BaseBackFragment {
    @BindView(R.id.elv_collocation)
    ExpandableListView mListView;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;

    private List<RepairBean> repairBean;
    List<RepairBean.DetailListBean> goodsList_1 = new ArrayList<>();
    List<RepairBean.DetailListBean> goodsList_2 = new ArrayList<>();


    private String session, userName, CarNo, CarUserName, StartTime, param, params;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    public static SearchRepairFragment newInstance() {
        SearchRepairFragment fragment = new SearchRepairFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search_repair;
    }

    @Override
    public void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("维修");
        repairBean = new ArrayList<>();
        RepairBean repairBean_1 = new RepairBean();
        RepairBean repairBean_2 = new RepairBean();

        repairBean_1.setOrderId("CL1818412");
        repairBean_1.setDepart("操机部-");
        repairBean_1.setUserName("李鹏");
        repairBean_1.setApplyDate("2018-12-05 12:00");
        repairBean_1.setCause("" + "(" + "5项" + ")" + "机台破损，电脑黑屏，办公维修");
        goodsList_1.add(new RepairBean.DetailListBean("灯具电路", "A栋一楼后座32位", "2天", "2018-11-14 14:20", "李鹏", true, "电线老化", "老鼠咬断电线", "电线已经换成新线，线路更换完成", "普通维修"));
        goodsList_1.add(new RepairBean.DetailListBean("机台更换油管", "B栋三楼机壳科", "1天", "2018-11-15 10:20", "李鹏", false, "电线老化", "", "", "普通维修"));
        goodsList_1.add(new RepairBean.DetailListBean("灯管更换", "C栋二楼", "2天", "2018-11-14 14:20", "李鹏", true, "灯丝烧断", "", "灯管更换完成", "紧急维修"));
        goodsList_1.add(new RepairBean.DetailListBean("安装监控", "A栋一楼后座32位", "2", "2018-11-14 14:20", "李鹏", false, "安装前排监控", "安装80%", "", "紧急维修"));
        repairBean_1.setDetailListBeans(goodsList_1);

        repairBean_2.setOrderId("CL1818412");
        repairBean_2.setDepart("操机部-");
        repairBean_2.setUserName("李鹏");
        repairBean_2.setCause("" + "(" + "2项" + ")" + "机台破损，电脑黑屏");
        repairBean_2.setApplyDate("2018-12-05 12:00");

        goodsList_2.add(new RepairBean.DetailListBean("灯具电路", "A栋一楼后座32位", "2天", "2018-11-14 14:20", "李鹏", true, "电线老化", "老鼠咬断电线", "电线已经换成新线，线路更换完成", "紧急维修"));
        goodsList_2.add(new RepairBean.DetailListBean("机台更换油管", "B栋三楼机壳科", "1天", "2018-11-15 10:20", "李鹏", false, "电线老化", "", "", "紧急维修"));
        repairBean_2.setDetailListBeans(goodsList_2);
        repairBean.add(repairBean_1);
        repairBean.add(repairBean_2);
        mListView.setAdapter(new RepairAdapter(_mActivity, repairBean));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

