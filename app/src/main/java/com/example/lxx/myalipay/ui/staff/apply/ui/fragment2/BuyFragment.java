package com.example.lxx.myalipay.ui.staff.apply.ui.fragment2;

import android.widget.ExpandableListView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.adapter.ShoppingAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean.ShoppingApplyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * created by lxx at 2019/11/27 16:11
 * 描述:采购列表
 */
public class BuyFragment extends BaseBackFragment {

    @BindView(R.id.elv_collocation)
    ExpandableListView mListView;

    private List<ShoppingApplyBean> collocationList;
    List<ShoppingApplyBean.CollocationDetail> goodsList_1 = new ArrayList<>();
    List<ShoppingApplyBean.CollocationDetail> goodsList_2 = new ArrayList<>();


    public static BuyFragment newInstance() {
        BuyFragment fragment = new BuyFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_expandable_listview;
    }

    protected void initView() {
        getData();
    }

    private void getData() {
        collocationList = new ArrayList<>();
        ShoppingApplyBean collocation_1 = new ShoppingApplyBean();
        ShoppingApplyBean collocation_2 = new ShoppingApplyBean();

        collocation_1.setTotal(14897);
        collocation_1.setOrderNo("CL1818412");
        collocation_1.setDepart("研发部-");
        collocation_1.setUserName("李熙祥");
        collocation_1.setDate("2018-12-05 12:00");
        collocation_1.setOrderCause("办公用品");
        goodsList_1.add(new ShoppingApplyBean.CollocationDetail(1,"电脑","外星人","台",1,1000.15));
        goodsList_1.add(new ShoppingApplyBean.CollocationDetail(2,"电脑","苹果","台",1,900.15));
        collocation_1.setCollcationDetail(goodsList_1);
        for (int i = 0; i < goodsList_1.size(); i++) {
            ShoppingApplyBean.CollocationDetail bean1 = new ShoppingApplyBean.CollocationDetail();
            bean1.setTotal(bean1.getNum()*bean1.getPrice());
        }

        collocation_2.setTotal(5000);
        collocation_2.setOrderNo("CL1818413");
        collocation_2.setDepart("研发部-");
        collocation_2.setUserName("李熙祥");
        collocation_2.setDate("2018-12-05 13:00");
        collocation_2.setOrderCause("办公用品");
        goodsList_2.add(new ShoppingApplyBean.CollocationDetail(1,"笔","水质","支",50,12.1));
        goodsList_2.add(new ShoppingApplyBean.CollocationDetail(2,"纸","打印","张",20,15.2));
        goodsList_2.add(new ShoppingApplyBean.CollocationDetail(3,"油墨","xL","瓶",50,10));
        goodsList_2.add(new ShoppingApplyBean.CollocationDetail(4,"键盘","惠普","件",50,25));
        goodsList_2.add(new ShoppingApplyBean.CollocationDetail(5,"椅子","??","张",50,20));
        collocation_2.setCollcationDetail(goodsList_2);
        for (int i = 0; i < goodsList_2.size(); i++) {
            ShoppingApplyBean.CollocationDetail bean2 = new ShoppingApplyBean.CollocationDetail();
            bean2.setTotal(bean2.getNum()*bean2.getPrice());
        }
        collocationList.add(collocation_1);
        collocationList.add(collocation_2);
        mListView.setAdapter(new ShoppingAdapter(_mActivity,mListView,collocationList));
        // mListView.expandGroup(0); 默认打开第一个
    }
}
