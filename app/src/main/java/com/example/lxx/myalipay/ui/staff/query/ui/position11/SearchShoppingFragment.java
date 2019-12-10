package com.example.lxx.myalipay.ui.staff.query.ui.position11;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.adapter.ShoppingAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean.ShoppingApplyBean;
import com.example.lxx.myalipay.widget.font.FontTextView;
import com.example.lxx.myalipay.widget.font.FontTextView4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.shts.android.library.TriangleLabelView;


/**
 * created by lxx at 2019/12/3 21:12
 * 描述:采购查询
 */
public class SearchShoppingFragment extends BaseBackFragment {
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
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.ll_click)
    LinearLayout llClick;

    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.tv_Year_Month)
    TextView tvYearMonth;
    @BindView(R.id.tv_font_date)
    FontTextView tvFontDate;
    @BindView(R.id.tv_time1)
    FontTextView tvTime1;
    @BindView(R.id.lab_time1)
    TriangleLabelView labTime1;
    @BindView(R.id.tv_person_num)
    FontTextView tvPersonNum;
    @BindView(R.id.lab_person_num)
    TriangleLabelView labPersonNum;
    @BindView(R.id.tv_time2)
    FontTextView tvTime2;
    @BindView(R.id.lab_time2)
    TriangleLabelView labTime2;
    @BindView(R.id.tv_person_num2)
    FontTextView tvPersonNum2;
    @BindView(R.id.lab_person_num2)
    TriangleLabelView labPersonNum2;
    @BindView(R.id.ll_table)
    LinearLayout llTable;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.elv_collocation)
    ExpandableListView mListView;
    @BindView(R.id.ll_custom_head)
    LinearLayout llCustomHead;
    @BindView(R.id.ll_background)
    LinearLayout llBackground;

    private String session, userName, CarNo, CarUserName, StartTime, param, params;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    private List<ShoppingApplyBean> collocationList;
    List<ShoppingApplyBean.CollocationDetail> goodsList_1 = new ArrayList<>();
    List<ShoppingApplyBean.CollocationDetail> goodsList_2 = new ArrayList<>();

    public static SearchShoppingFragment newInstance() {
        SearchShoppingFragment fragment = new SearchShoppingFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search_shopping;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("采购查询");
        btnUpPager.setText("前一天");
        btnNextPager.setText("后一天");
        recyclerView.setVisibility(View.GONE);
        mListView.setVisibility(View.VISIBLE);
        StartTime = sf.format(new Date());
        tvWeek.setText(dateToWeek(StartTime)); //转星期
        tvDate.setText(StartTime);

        initData();
    }
    /**
     * 初始化数据
     */
    private void initData() {
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

    /**
     * 日期转换为星期
     * @param dateTime
     * @return
     */
    public String dateToWeek(String dateTime){
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance(); //获得一个日历
        Date date = null;
        try {
            date = sf.parse(dateTime);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) -1;//指示一个星期中的某天。
        if (w < 0) w = 0;
        return weekDays[w];
    }
}
