package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarInfoBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.detail.DetailListActivity;
import com.example.lxx.myalipay.widget.BaseListView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by lxx at 2019/11/27 17:10
 * 描述:
 */
public class CarHomeAdapter extends BaseQuickAdapter<CarInfoBean.DataBean, BaseViewHolder> {


    public CarHomeAdapter(@Nullable List<CarInfoBean.DataBean> data) {
        super(R.layout.news_fragment_car_uses, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarInfoBean.DataBean item) {
        helper.setText(R.id.carId, (helper.getLayoutPosition()) + 1 + "")
                .setText(R.id.plateNumber, item.getCarNo())
                .setText(R.id.car_name, item.getCarType())
                .setText(R.id.tv_driver_name, item.getDriver())
                .setText(R.id.tv_phone, item.getDriverTel());
        if (TextUtils.isEmpty(item.getPicUrl())) {
            helper.setImageResource(R.id.car_icon, R.mipmap.ic_normal_bg);
        }else {
            Glide.with(mContext).load(item.getPicUrl()).into(((ImageView) helper.getView(R.id.car_icon)));
        }
        ImageView ivCarStatus = helper.getView(R.id.iv_car_status);
        ivCarStatus.setVisibility(View.GONE);

        helper.addOnClickListener(R.id.car_icon);

        if ("闲置中".equals(item.getCarStatus())) {
            ivCarStatus.setImageResource(R.mipmap.iv_car_status_2);
            ivCarStatus.setVisibility(View.VISIBLE);
        } else if ("使用中".equals(item.getCarStatus())) {
            ivCarStatus.setImageResource(R.mipmap.iv_car_status_1);
            ivCarStatus.setVisibility(View.VISIBLE);
        } else if ("维修中".equals(item.getCarStatus())) {
            ivCarStatus.setImageResource(R.mipmap.iv_car_status_3);
            ivCarStatus.setVisibility(View.VISIBLE);
        } else {
            ivCarStatus.setVisibility(View.GONE);
        }

        BaseListView listView = helper.getView(R.id.child_listView);
        CarBaseAdapter2 adapter = new CarBaseAdapter2(mContext, item.getOrderList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!item.getOrderList().get(position).getUserName().equals("")) {
                    testChildData(position, item, DetailListActivity.class); //跳到详情页
                }
            }
        });
    }

    private void testChildData(int position, CarInfoBean.DataBean item, Class<?> cls) {
        CarInfoBean.DataBean.OrderListBean list = item.getOrderList().get(position);
        Intent intent = new Intent(mContext, cls);
        intent.putExtra("driverPhone", item.getDriverTel());
        intent.putExtra("PicUrl", item.getPicUrl());
        intent.putExtra("CarSeq", list.getCarSeq());
        intent.putExtra("CarNo", list.getCarNo());
        intent.putExtra("CarType", list.getCarType());
        intent.putExtra("CarStatus", list.getCarStatus());
        intent.putExtra("Driver", list.getDriver());
        intent.putExtra("DriverTel", list.getDriverTel());
        intent.putExtra("OrderSeq", list.getOrderSeq());
        intent.putExtra("SendCarNo", list.getSendCarNo());
        intent.putExtra("DeptName", list.getDeptName());
        intent.putExtra("UserName", list.getUserName());
        intent.putExtra("GroupName", list.getGroupName());
        intent.putExtra("AppName", list.getUserName());
        intent.putExtra("OrderTime", list.getOrderTime());
        intent.putExtra("UseCarTime", list.getUseCarTime());
        intent.putExtra("Entourage", list.getEntourage());
        intent.putExtra("Items", list.getItems());
        intent.putExtra("Reason", list.getReason());
        intent.putExtra("SetOutTime", list.getSetOutTime());
        intent.putExtra("RetTime", list.getRetTime());
        intent.putExtra("RetMileage", list.getRetMileage());
        intent.putExtra("Remarks", list.getRemarks());
        intent.putExtra("Tel", list.getTel());
        intent.putExtra("SenCarby", list.getSenCarby());
        intent.putExtra("SenCarTime", list.getSenCarTime());
        intent.putExtra("CancelStatus", list.getCancelStatus());
        intent.putExtra("UserCarDept", list.getUserCarDept());
        intent.putExtra("UserCarGroup", list.getUserCarGroup());
        intent.putExtra("Status", list.getStatus());
        intent.putExtra("OrderStatus", list.getOrderStatus());
        intent.putExtra("Destination", list.getDestination());
        intent.putExtra("EstimatedRTime", list.getEstimatedRTime());
        intent.putExtra("EstimatedUseTime", list.getEstimatedUseTime());

        ArrayList<String> opts = (ArrayList<String>) list.getOpts();
        intent.putStringArrayListExtra("opts", opts);
        mContext.startActivity(intent);
    }
}




















