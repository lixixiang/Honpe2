package com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.add_order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.adapter.BaseRepairListAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean.RepairApplyListBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lxx
 * @date 2019/12/1
 * 维修申请单
 */
public class AddRepairListActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.ll_add_work)
    LinearLayout llAddWork;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_car_search)
    TextView tvCarSearch;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.tv_car_modification)
    TextView tvCarModification;
    @BindView(R.id.bottomBar)
    LinearLayout bottomBar;

    public static int REPAIR_APPLY = 1;
    List<RepairApplyListBean> list;
    private String character, type, editItem, editPoint, editTimes, editCause;
    BaseRepairListAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    private static int count = 0;
    @Subscribe
    public void Event(RepairApplyListBean bean){
        list = new ArrayList<>();
        adapter.addData(0,bean);
        list.add(bean);
        count++;
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_repair_list;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("维修申请");
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new BaseRepairListAdapter(list);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REPAIR_APPLY && resultCode == RESULT_OK && data != null) {
            character = data.getExtras().getString("character");
            type = data.getExtras().getString("type");
            editItem = data.getExtras().getString("editItem");
            editPoint = data.getExtras().getString("editPoint");
            editTimes = data.getExtras().getString("editTimes");
            editCause = data.getExtras().getString("editCause");
            EventBus.getDefault().post(new RepairApplyListBean("cL10015854","胡立伟",character,"2018-11-17",
                    type,editItem,editPoint,editTimes,"",editCause));
        }
    }

    @OnClick(R.id.ll_add_work)
    public void onViewClicked() {
        startActivityForResult(new Intent(_mActivity,RepairApplyActivity.class), REPAIR_APPLY);
    }
}
