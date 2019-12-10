package com.example.lxx.myalipay.ui.fragment.b_package.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.fragment.b_package.entity.ReceiverAddressBean;

import java.util.List;


/**
 * created by lxx at 2019/11/21 15:34
 * 描述:
 */
public class AddressAdapter extends BaseQuickAdapter<ReceiverAddressBean, BaseViewHolder> {


    public AddressAdapter(@Nullable List<ReceiverAddressBean> data) {
        super(R.layout.item_address_show, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReceiverAddressBean item) {
        helper.setText(R.id.consignee_name,item.getUserName());
        helper.setText(R.id.consignee_phone, item.getPhone());
        helper.setText(R.id.consignee_address,item.getArea() + item.getAddress());
        final CheckBox isCheck = helper.getView(R.id.radio_selected);
        showPhone(item.getPhone());
        isCheck.setClickable(false);
        isCheck.setChecked(item.isClick());
        TextView checkText = helper.getView(R.id.radio_selectedText);
        checkText.setText(item.isClick() ? "默认地址" : "设为默认");
        helper.addOnClickListener(R.id.radio_selectedText);
        helper.addOnClickListener(R.id.ll_edite_address);
        helper.addOnClickListener(R.id.ll_delete_address);

    }

    private String showPhone(String phone) {
        return phone.substring(0, 3) + "*****" + phone.substring(phone.length() - 3);
    }
}

