package com.example.lxx.myalipay.ui.staff.query.ui.position16.Fragment3Priase.child;



import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.adapter.ListAdapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean3;
import com.example.lxx.myalipay.widget.BaseListView;

import java.util.List;

import butterknife.BindView;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.Fragment3Priase.child
 * 作者: lxx
 * 日期: 2019/1/22 9:47
 * 描述:
 */
public class FragmentChild3 extends BaseFragment {
    @BindView(R.id.listView)
    BaseListView listView;
    ListAdapter adapter;

    List<StatisticsBean3.DataBean.DetailsBean> mList;

    public static FragmentChild3 newInstance(List<StatisticsBean3.DataBean.DetailsBean> list) {
        FragmentChild3 fragmentChild = new FragmentChild3();
        fragmentChild.mList = list;
        return fragmentChild;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_child;
    }

    @Override
    protected void initView() {
        adapter = new ListAdapter(_mActivity, mList);
        listView.setAdapter(adapter);
    }
}
