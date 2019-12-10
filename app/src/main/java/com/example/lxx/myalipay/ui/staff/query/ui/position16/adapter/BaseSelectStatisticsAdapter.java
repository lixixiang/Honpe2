package com.example.lxx.myalipay.ui.staff.query.ui.position16.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean2;
import com.example.lxx.myalipay.widget.BaseGridView;


import java.util.List;

/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.adapter
 * @date: 2018/8/21 16:16
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：item_total_query  shape_rectangle_white_gray
 */
public class BaseSelectStatisticsAdapter extends BaseQuickAdapter<StatisticsBean2.DataBean, BaseViewHolder> {


    public BaseSelectStatisticsAdapter(List<StatisticsBean2.DataBean> data) {
        super(R.layout.item_title_arraw, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatisticsBean2.DataBean item) {
      //  helper.setIsRecyclable(false);//禁止复用 true-可以复用

      //  if (item.getDishesDetails().size()>0) {
//            helper.setVisible(R.id.re_layout, true);
            helper.setText(R.id.tv_title,item.getMealTimes());
            ItemChildGridAdapter adapter = new ItemChildGridAdapter(mContext, item.getDishesDetails());
            ((BaseGridView)helper.getView(R.id.gridView_1)).setAdapter(adapter);

//        }else{
//            helper.setText(R.id.tv_title,"");
//            helper.setVisible(R.id.re_layout, false);
//        }
    }
}
