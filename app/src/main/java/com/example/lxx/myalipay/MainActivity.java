package com.example.lxx.myalipay;


import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.FloorManagerBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.example.lxx.myalipay.ui.staff.apply.ApplyActivity.CHANGE;
import static com.example.lxx.myalipay.ui.staff.apply.ApplyActivity.ORDER;
import static com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.adapter.CarBaseAdapter2.REQ_MOB;
import static com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.adapter.CarBaseAdapter2.REQ_SEND_CAR_FRAGMENT;
import static com.example.lxx.myalipay.ui.staff.query.ui.position7.adapter.BedGridViewAdapter.ADD_STAY;

public class MainActivity extends BaseActivity {
    private String msg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        if (isImmersionBarEnabled())
            initImmersionBar();
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_main_fragment_container, MainFragment.newInstance());
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //派车
        switch (requestCode) {
            case REQ_SEND_CAR_FRAGMENT:
//                String sendCarNo = data.getStringExtra("sendCarNo");
//                String carNo = data.getStringExtra("carNo");
//                String driver = data.getStringExtra("driver");
//                String reasion = data.getStringExtra("reasion");
//                LatteLogger.d("REQ_SEND_CAR_FRAGMENT", sendCarNo + "     " + carNo + "     " +
//                        driver + "     " + reasion);
                break;
            case REQ_MOB:
              LatteLogger.d("REQ_MOB","REQ_MOBREQ_MOBREQ_MOBREQ_MOBREQ_MOB");
                EventBusUtil.sendEvent(new Event(FinalClass.A));

//                String orderId = data.getStringExtra("orderId");
//                String carTime = data.getStringExtra("time");
//                String event = data.getStringExtra("event");
//                String carMudidi = data.getStringExtra("mudidi");
//                String applyName = data.getStringExtra("applyName");
//                String goAlong = data.getStringExtra("goAlong");
//                String Tel = data.getStringExtra("Tel");
//                String Items = data.getStringExtra("Items");
//                String Team = data.getStringExtra("Team");
//                String depart = data.getStringExtra("Depart");
//                LatteLogger.d("REQ_MOB", orderId + "  " +
//                        carTime + "  " + event + "  " + carMudidi + "  " + applyName + "  " + goAlong + "  " + Tel + "  " +
//                        Items + "  " + Team + "  " + depart);
                break;

        }
        if (requestCode == REQ_SEND_CAR_FRAGMENT && resultCode == 100 && data != null) {//请求申请单
            msg = data.getStringExtra("msg");
            Event<String> event = new Event<>(FinalClass.A, msg);
            EventBusUtil.sendEvent(event);
            //  EventBusUtil.sendEvent(new Event(FinalClass.A)); //两种方式一种传值，一种不传
        } else if (requestCode == REQ_MOB && resultCode == 150 && data != null) { //派车修改
            msg = data.getStringExtra("result");
            LatteLogger.d("zzzz", msg);
            EventBusUtil.sendEvent(new Event(FinalClass.B));
        } else if (requestCode == ADD_STAY && resultCode == 200 && data != null) {
            FloorManagerBean bean = (FloorManagerBean) data.getSerializableExtra("floor");
            Event<FloorManagerBean> event = new Event<FloorManagerBean>(FinalClass.A, bean);
            EventBusUtil.sendEvent(event);
        }

        if (requestCode == CHANGE && resultCode == RESULT_OK && data != null) {
            msg = data.getStringExtra("result");
            EventBusUtil.sendEvent(new Event(FinalClass.A));//派车申请单成功发送到子fragment中
        } else if (requestCode == ORDER && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("result");
            LatteLogger.d("ddd", result);
            EventBusUtil.sendEvent(new Event(FinalClass.A));
        } else if (resultCode == 100) {
            LatteLogger.d("myMsg===", data.getStringExtra("msg"));
            EventBusUtil.sendEvent(new Event(FinalClass.A));
        }
    }
}



























