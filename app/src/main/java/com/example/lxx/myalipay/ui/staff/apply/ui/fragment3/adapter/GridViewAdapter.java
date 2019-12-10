package com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean.MenuBean;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;


import java.util.List;

/**
 * created by lxx at 2019/11/28 17:26
 * 描述:
 */
public class GridViewAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_PERSON = 1;
    public String menuStyle;

    public GridViewAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.css_title_small_depart);
        addItemType(TYPE_PERSON, R.layout.css_checkbox);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                MenuBean lv1 = (MenuBean) item;
                helper.setText(R.id.tv_title, lv1.getMsg());
                menuStyle = lv1.getMsg();
                helper.setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.green));
                break;
            case TYPE_PERSON:
                final MenuBean.DataBean lv2 = (MenuBean.DataBean) item;
                final CheckBox checkBox = helper.getView(R.id.item_check);
                checkBox.setText(lv2.getFoodName());
                helper.addOnClickListener(R.id.item_check);

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            lv2.setStatus(1);
                            lv2.setMenuCheck(true);
                        } else {
                            lv2.setStatus(0);
                            lv2.setMenuCheck(false);
                        }
                        Event<MenuBean.DataBean> event = new Event<MenuBean.DataBean>
                                (FinalClass.A, new MenuBean.DataBean(
                                        lv2.getFoodStyle(), lv2.getFoodName(), lv2.getUnit(), lv2.getFoodCode(), lv2.getStatus()
                                ));
                        EventBusUtil.sendEvent(event);
                    }
                });

                if (lv2.getStatus() >0) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
                break;
        }
    }
}
