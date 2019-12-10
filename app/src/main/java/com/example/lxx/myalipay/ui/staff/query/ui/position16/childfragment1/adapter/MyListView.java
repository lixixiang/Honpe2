package com.example.lxx.myalipay.ui.staff.query.ui.position16.childfragment1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.ui.staff.query.ui.position16.bean.StatisticsBean1;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.childfragment1
 * 作者: lxx
 * 日期: 2019/4/11 11:30
 * 描述:
 */
public class MyListView extends BaseAdapter {
    private Context mContext;
    List<StatisticsBean1.DataBean> mList;
    private LayoutInflater mInflater;

    SimpleDateFormat sf = new SimpleDateFormat("MM-dd");

    public MyListView(Context mContext, List<StatisticsBean1.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_text_text_button, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        StatisticsBean1.DataBean bean = mList.get(position);
        try {
            String mDate = sf.format(DateUtils.setDate(bean.getMealDate()));
            holder.tvDate.setText(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvWeek.setText(String.valueOf(bean.getWeek()));

        holder.ckChildBreakfast.setId(position);
        holder.ckChildLunch.setId(position);
        holder.ckChildNight.setId(position);
        holder.ckChildNightSnack.setId(position);
        holder.ckChildAll.setId(position);

        holder.ckChildBreakfast.setChecked(bean.isHasBreakFast());
        holder.ckChildLunch.setChecked(bean.isHasLunch());
        holder.ckChildNight.setChecked(bean.isHasDinner());
        holder.ckChildNightSnack.setChecked(bean.isHasMidnight());
        holder.ckChildAll.setChecked(bean.isHasHorizontal());

        //点击时把值修改下
        holder.ckChildBreakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasBreakFast(isChecked);
                mList.set(buttonView.getId(), dat);
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        holder.ckChildLunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasLunch(isChecked);
                mList.set(buttonView.getId(), dat);
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        holder.ckChildNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasDinner(isChecked);
                mList.set(buttonView.getId(), dat);
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        holder.ckChildNightSnack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasMidnight(isChecked);
                mList.set(buttonView.getId(), dat);
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        final ViewHolder finalHolder = holder;
        holder.ckChildAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasHorizontal(isChecked);
                mList.set(buttonView.getId(), dat);
                if (isChecked) {
                    dat.setHasBreakFast(true);
                    dat.setHasLunch(true);
                    dat.setHasDinner(true);
                    dat.setHasMidnight(true);
                } else {
                    dat.setHasBreakFast(false);
                    dat.setHasLunch(false);
                    dat.setHasDinner(false);
                    dat.setHasMidnight(false);
                }
                finalHolder.ckChildBreakfast.setChecked(dat.isHasBreakFast());
                finalHolder.ckChildLunch.setChecked(dat.isHasLunch());
                finalHolder.ckChildNight.setChecked(dat.isHasDinner());
                finalHolder.ckChildNightSnack.setChecked(dat.isHasMidnight());
                finalHolder.ckChildAll.setChecked(dat.isHasHorizontal());
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        return view;
    }

    //全选
    public void selectAll() {
        if (mList != null) {
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setHasBreakFast(true);
                mList.get(i).setHasLunch(true);
                mList.get(i).setHasDinner(true);
                mList.get(i).setHasMidnight(true);
                mList.get(i).setHasHorizontal(true);
            }
            this.notifyDataSetChanged();
        }
    }

    //取消全选
    public void cancelAll() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasBreakFast(false);
            mList.get(i).setHasLunch(false);
            mList.get(i).setHasDinner(false);
            mList.get(i).setHasMidnight(false);
            mList.get(i).setHasHorizontal(false);
        }
        this.notifyDataSetChanged();
    }

    //选择垂直 早餐
    public void selectVertical() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasBreakFast(true);
        }
        this.notifyDataSetChanged();
    }

    //取消垂直 早餐
    public void cancelVertical() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasBreakFast(false);
        }
        this.notifyDataSetChanged();
    }

    //选择垂直 午餐
    public void selectVertical2() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasLunch(true);
        }
        this.notifyDataSetChanged();
    }

    //取消垂直 午餐
    public void cancelVertical2() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasLunch(false);
        }
        this.notifyDataSetChanged();
    }

    //选择垂直 晚餐
    public void selectVertical3() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasDinner(true);
        }
        this.notifyDataSetChanged();
    }

    //取消垂直 晚餐
    public void cancelVertical3() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasDinner(false);
        }
        this.notifyDataSetChanged();
    }

    //选择垂直 夜宵
    public void selectVertical4() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasMidnight(true);
        }
        this.notifyDataSetChanged();
    }

    //取消垂直 夜宵
    public void cancelVertical4() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setHasMidnight(false);
        }
        this.notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_week)
        TextView tvWeek;
        @BindView(R.id.ck_child_breakfast)
        CheckBox ckChildBreakfast;
        @BindView(R.id.ck_child_lunch)
        CheckBox ckChildLunch;
        @BindView(R.id.ck_child_night)
        CheckBox ckChildNight;
        @BindView(R.id.ck_child_night_snack)
        CheckBox ckChildNightSnack;
        @BindView(R.id.ck_child_all)
        CheckBox ckChildAll;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

















