package com.example.lxx.myalipay.ui.staff.query.ui.position1.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.bean.CheckInBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.widget.ToastUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.adapter
 * @date: 2018/8/21 16:16
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：考勤打卡
 */
public class CheckInAdapter extends BaseQuickAdapter<CheckInBean.DataBean, BaseViewHolder> {
    private String curDay, weekday;
    SimpleDateFormat myDay = new SimpleDateFormat("dd");

    public CheckInAdapter(@Nullable List<CheckInBean.DataBean> data) {
        super(R.layout.css_table_1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CheckInBean.DataBean item) {
        String[][] object = {new String[]{"星期", ""}};
        String[][] object2 = {new String[]{"T", " "}};

        String ymdDate = StringUtils.replace(item.getRecordDate(), object2);

        try {
            Date start = DateUtils.setDate(ymdDate);
            curDay = myDay.format(start);
            LatteLogger.d("dddddddd", curDay);
            weekday = curDay + "/" + StringUtils.replace(item.getWeekName(), object);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        helper.setText(R.id.tv_time_head, weekday);

        helper.setBackgroundRes(R.id.time1, R.color.white);
        helper.setBackgroundRes(R.id.time2, R.color.white);
        helper.setBackgroundRes(R.id.time3, R.color.white);
        helper.setBackgroundRes(R.id.time4, R.color.white);
        helper.setBackgroundRes(R.id.time5, R.color.white);
        helper.setBackgroundRes(R.id.time6, R.color.white);

        helper.setTextColor(R.id.time1, mContext.getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time2, mContext.getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time3, mContext.getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time4, mContext.getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time5, mContext.getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time6, mContext.getResources().getColor(R.color.black));

        if (item.getS1() > 0) {
            helper.setBackgroundRes(R.id.time1, R.color.google_red);
            helper.setTextColor(R.id.time1, mContext.getResources().getColor(R.color.white));
            helper.getView(R.id.time1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.getInstance().showToast("上班迟到" + item.getS1()+"分钟");
                }
            });
        }else {

        }
        if (item.getS2() > 0) {
            helper.setBackgroundRes(R.id.time2, R.color.google_red);
            helper.setTextColor(R.id.time2, mContext.getResources().getColor(R.color.white));
            helper.getView(R.id.time2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.getInstance().showToast("下班早退" + item.getS2()+"分钟");
                }
            });
        }else {

        }
        if (item.getS3() > 0) {
            helper.setBackgroundRes(R.id.time3, R.color.google_red);
            helper.setTextColor(R.id.time3, mContext.getResources().getColor(R.color.white));
            helper.getView(R.id.time3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.getInstance().showToast("上班迟到" + item.getS3()+"分钟");
                }
            });
        }else {

        }
        if (item.getS4() > 0) {
            helper.setBackgroundRes(R.id.time4, R.color.google_red);
            helper.setTextColor(R.id.time4, mContext.getResources().getColor(R.color.white));
            helper.getView(R.id.time4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.getInstance().showToast("下班早退" + item.getS4()+"分钟");
                }
            });
        }else {

        }
        if (item.getS5() > 0) {
            helper.setBackgroundRes(R.id.time5, R.color.google_red);
            helper.setTextColor(R.id.time5, mContext.getResources().getColor(R.color.white));
            helper.getView(R.id.time5).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.getInstance().showToast("上班迟到" + item.getS5()+"分钟");
                }
            });
        }else {

        }
        if (item.getS6() > 0) {
            helper.setBackgroundRes(R.id.time6, R.color.google_red);
            helper.setTextColor(R.id.time6, mContext.getResources().getColor(R.color.white));
            helper.getView(R.id.time6).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.getInstance().showToast("下班早退" + item.getS6()+"分钟");
                }
            });
        }else {

        }

        if (("漏卡").equals(item.getTimer1())) {
            helper.setBackgroundRes(R.id.time1, R.color.google_yellow);
            helper.setTextColor(R.id.time1, mContext.getResources().getColor(R.color.white));
        }
        if (("漏卡").equals(item.getTimer2())) {
            helper.setBackgroundRes(R.id.time2, R.color.google_yellow);
            helper.setTextColor(R.id.time2, mContext.getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer3())) {
            helper.setBackgroundRes(R.id.time3, R.color.google_yellow);
            helper.setTextColor(R.id.time3, mContext.getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer4())) {
            helper.setBackgroundRes(R.id.time4, R.color.google_yellow);
            helper.setTextColor(R.id.time4, mContext.getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer5())) {
            helper.setBackgroundRes(R.id.time5, R.color.google_yellow);
            helper.setTextColor(R.id.time5, mContext.getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer6())) {
            helper.setBackgroundRes(R.id.time6, R.color.google_yellow);
            helper.setTextColor(R.id.time6, mContext.getResources().getColor(R.color.white));
        }

        helper.setText(R.id.time1, item.getTimer1());
        helper.setText(R.id.time2, item.getTimer2());
        helper.setText(R.id.time3, item.getTimer3());
        helper.setText(R.id.time4, item.getTimer4());
        helper.setText(R.id.time5, item.getTimer5());
        helper.setText(R.id.time6, item.getTimer6());

        if (item.getAbnormalRec().contains("记录")) {
            helper.setGone(R.id.ll_table, false);
            helper.setGone(R.id.ll_content, true);
            helper.setText(R.id.tv_time_head2, weekday);
            helper.setText(R.id.tv_content, item.getAbnormalRec());
            helper.setTextColor(R.id.tv_content, mContext.getResources().getColor(R.color.google_yellow));
            helper.setTextColor(R.id.tv_time_head2, mContext.getResources().getColor(R.color.google_yellow));
        } else if (item.getAbnormalRec().contains("休息")) {
            helper.setGone(R.id.ll_table, false);
            helper.setGone(R.id.ll_content, true);
            helper.setText(R.id.tv_time_head2, weekday);
            helper.setText(R.id.tv_content, item.getAbnormalRec());
            helper.setTextColor(R.id.tv_content, mContext.getResources().getColor(R.color.google_green));
            helper.setTextColor(R.id.tv_time_head2, mContext.getResources().getColor(R.color.google_green));
        } else {
            helper.setGone(R.id.ll_table, true);
            helper.setGone(R.id.ll_content, false);
        }
    }
}
























