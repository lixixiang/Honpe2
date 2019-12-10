package com.example.lxx.myalipay.ui.fragment.b_package.address;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.adapter.AddressAdapter;
import com.example.lxx.myalipay.ui.fragment.b_package.address.add.AddAddressFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.entity.ReceiverAddressBean;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * created by lxx at 2019/11/21 15:08
 * 描述:地址
 */
public class AddressFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_address)
    Button btnAddress;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.re_none_order)
    RelativeLayout reNoneOrder;
    private AddressAdapter adapter;
    private int num;
    String titles;
    public static AddressFragment newInstance(String title){
        AddressFragment fragment = new AddressFragment();
        fragment.titles = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_address;
    }

    @Override
    protected void initView() {
       initToolbarNav(homeBack);
        tvTitle.setText(titles);
        btnAddress.setText("添加新地址");
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new AddressAdapter(getData());
        recyclerView.setAdapter(adapter);
        num = mList.size();
        Event<Integer> event = new Event<Integer>(FinalClass.Address, num);
        EventBusUtil.sendEvent(event);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.ll_edite_address:
                    startForResult(AddAddressFragment.newInstance(mList.get(position), position), FinalClass.EDIT_ADDRESS);
                    break;
                case R.id.ll_delete_address:
                    final NormalDialog dialog1 = new NormalDialog(_mActivity);
                    dialog1.isTitleShow(false)
                            .content("是否要删除" + "\"" + mList.get(position).getUserName() + "\"" + "的订单?")
                            .widthScale(0.85f)
                            .cornerRadius(5)//
                            .contentTextSize(14)
                            .btnTextSize(14)
                            .contentGravity(Gravity.CENTER)
                            .btnTextColor(Color.RED, Color.BLUE)
                            .btnPressColor(Color.LTGRAY)
                            .show();
                    dialog1.setOnBtnClickL(new OnBtnClickL() {
                        @Override
                        public void onBtnClick() { //left
                            dialog1.dismiss();
                        }
                    }, new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {//right
                            mList.remove(position);
                            adapter.notifyItemRemoved(position);
                            num = mList.size();
                            Event<Integer> event1 = new Event<Integer>(FinalClass.Address, num);
                            EventBusUtil.sendEvent(event1);
                            dialog1.dismiss();
                        }
                    });
                    break;
            }
        });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.Address:
                num = (int) event.getData();
                LatteLogger.d("num", num);
                if (num == 0) {
                    recyclerView.setVisibility(View.GONE);
                    reNoneOrder.setVisibility(View.VISIBLE);
                    btnAddress.setVisibility(View.GONE);
                    tvAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startForResult(AddAddressFragment.newInstance(null, -1), FinalClass.ADD_ADDRESS);
                        }
                    });
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    btnAddress.setVisibility(View.VISIBLE);
                    reNoneOrder.setVisibility(View.GONE);
                }
                break;
        }
    }

    List<ReceiverAddressBean> mList = new ArrayList<>();

    private List<ReceiverAddressBean> getData() {
        ReceiverAddressBean bean = new ReceiverAddressBean("王先生", "1591426612", "广东省深圳市", "福永桥底白石厦29栋46号", true, -1);
        mList.add(bean);
        ReceiverAddressBean bean1 = new ReceiverAddressBean("李先生", "1591426612", "广东省深圳市", "福永桥底白石厦29栋46号", false, -1);
        mList.add(bean1);
        return mList;
    }

    @OnClick(R.id.btn_address)
    public void onViewClicked() {
        startForResult(AddAddressFragment.newInstance(null, -1), FinalClass.ADD_ADDRESS);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == FinalClass.ADD_ADDRESS && resultCode == RESULT_OK && data != null) {
            ReceiverAddressBean bean = (ReceiverAddressBean) data.getSerializable("address");
            if (bean.isClick()) {
                mList.add(0, bean);
            } else {
                mList.add(bean);
            }

            recyclerView.setVisibility(View.VISIBLE);
            btnAddress.setVisibility(View.VISIBLE);
            reNoneOrder.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        } else if (requestCode == FinalClass.EDIT_ADDRESS && resultCode == RESULT_OK && data != null) {
            ReceiverAddressBean bean = (ReceiverAddressBean) data.getSerializable("address");
            LatteLogger.d("BuildUtil", GsonBuildUtil.GsonBuilder(bean));
            if (bean.isClick()) {
                mList.set(bean.getPosition(), bean);
            } else {
                mList.set(bean.getPosition(), bean);
            }
            adapter.notifyDataSetChanged();
        }
        Collections.reverse(mList);
    }
}




















