package com.example.lxx.myalipay.ui.staff.query.ui.position16.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.adapter
 * @date: 2018/10/15 11:03
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：BaseQuickAdapter<EmployeeOrderBean,MyBaseViewHolder>
 */
public class BaseEmployeeOrderAdapter extends BaseQuickAdapter<StatisticsBean1.DataBean, BaseViewHolder> {

    private CheckBox ckBreakfast, ckLunch, ckAfter, ckNight, ckAll;
    int i;
    final Map<Integer, Boolean> mapList = new HashMap<Integer, Boolean>();
    private Map<Integer, Map<Integer, Boolean>> map2 = new HashMap<Integer, Map<Integer, Boolean>>();
    public BaseEmployeeOrderAdapter(@Nullable List<StatisticsBean1.DataBean> data) {
        super(R.layout.item_text_text_button, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StatisticsBean1.DataBean item) {
        helper.setText(R.id.tv_date, item.getMealDate());
        helper.setText(R.id.tv_week, String.valueOf(item.getWeek()));

        //子类监听防止重绘
        ckBreakfast = helper.getView(R.id.ck_child_breakfast);
        ckLunch = helper.getView(R.id.ck_child_lunch);
        ckAfter = helper.getView(R.id.ck_child_night);
        ckNight = helper.getView(R.id.ck_child_night_snack);
        ckAll = helper.getView(R.id.ck_child_all);
        int position = helper.getLayoutPosition();
        helper.setTag(R.id.ck_child_breakfast, position);
        helper.setTag(R.id.ck_child_lunch, position);
        helper.setTag(R.id.ck_child_night, position);
        helper.setTag(R.id.ck_child_night_snack, position);

        ckBreakfast.setChecked(item.isHasBreakFast());
        ckLunch.setChecked(item.isHasLunch());
        ckAfter.setChecked(item.isHasDinner());
        ckNight.setChecked(item.isHasMidnight());

        ckBreakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                i = (int) buttonView.getTag();
                item.setHasBreakFast(isChecked);
                mapList.put(1, item.isHasBreakFast());
                map2.put(i, mapList);
            }
        });

       ckLunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               i = (int) buttonView.getTag();
               item.setHasLunch(isChecked);
               mapList.put(2, item.isHasLunch());
               map2.put(i, mapList);
           }
       });

       ckAfter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               i = (int) buttonView.getTag();
               item.setHasDinner(isChecked);
               mapList.put(3, item.isHasDinner());
               map2.put(i, mapList);
           }
       });

        ckNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                i = (int) buttonView.getTag();
                item.setHasMidnight(isChecked);
                mapList.put(4, item.isHasMidnight());
                map2.put(i, mapList);
            }
        });

        Map<Integer, Boolean> map3 = map2.get(position);

        if (map3 != null) {
            if (map3.get(1) != null) {
                ckBreakfast.setChecked(map3.get(1));
            }else{
                ckBreakfast.setChecked(false);
            }
            if (map3.get(2) != null) {
                ckLunch.setChecked(map3.get(2));
            }else{
                ckLunch.setChecked(false);
            }
            if (map3.get(3) != null) {
                ckAfter.setChecked(map3.get(3));
            }else{
                ckAfter.setChecked(false);
            }
            if (map3.get(4) != null) {
                ckNight.setChecked(map3.get(4));
            }else{
                ckNight.setChecked(false);
            }
        }
        else{
            ckBreakfast.setChecked(item.isHasBreakFast());
            ckLunch.setChecked(item.isHasLunch());
            ckAfter.setChecked(item.isHasDinner());
            ckNight.setChecked(item.isHasMidnight());
        }
    }
}


