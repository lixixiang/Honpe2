package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.send_car_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CommonOrderStatusBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean.SelectCarBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean.SelectDriverBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.Utils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.font.FontTextView;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * 按钮待派车界面
 */
public class SendCarListActivity extends BaseActivity implements TextWatcher {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.view0)
    TextView view0;
    @BindView(R.id.ll_view0)
    LinearLayout llView0;
    @BindView(R.id.et_startTime)
    TextView etCarNO;
    @BindView(R.id.icon_font_delete0)
    FontTextView iconFontDelete0;
    @BindView(R.id.view1)
    TextView view1;
    @BindView(R.id.ll_view1)
    LinearLayout llView1;
    @BindView(R.id.et_overTime)
    TextView etDriver;
    @BindView(R.id.icon_font_delete1)
    FontTextView iconFontDelete1;
    @BindView(R.id.view3)
    TextView view3;
    @BindView(R.id.ll_view2)
    LinearLayout llView2;
    @BindView(R.id.edit_car_no)
    TextView editCarNo;
    @BindView(R.id.icon_font_delete3)
    FontTextView iconFontDelete3;
    @BindView(R.id.re_car_no)
    RelativeLayout reCarNo;
    @BindView(R.id.view4)
    TextView view4;
    @BindView(R.id.ll_view3)
    LinearLayout llView3;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.icon_font_delete4)
    FontTextView iconFontDelete4;
    @BindView(R.id.re_remark)
    RelativeLayout reRemark;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.btn_sure)
    Button btnSure;

    private String session, send_car, mobCarNo, mobDriver, mobReason;
    private List<SelectCarBean.DataBean> optionsItem1s = new ArrayList<>();
    private List<SelectDriverBean.DataBean> optionsItem2s = new ArrayList<>();
    private List<String> carNos = new ArrayList<>();
    private List<String> drivers = new ArrayList<>();
    String[] strCarNOs, strDrivers;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_car;
    }

    @Override
    public void initView() {
        mobCarNo = getIntent().getStringExtra("carNo");
        mobDriver = getIntent().getStringExtra("driver");
        mobReason = getIntent().getStringExtra("reason");
        if (mobCarNo != null) {
            etCarNO.setText(mobCarNo);
            Event<String> event = new Event<String>(FinalClass.A, mobCarNo);
            EventBusUtil.sendEvent(event);
        }
        if (mobDriver != null) {
            etDriver.setText(mobDriver);
            Event<String> event = new Event<String>(FinalClass.B, mobDriver);
            EventBusUtil.sendEvent(event);
        }
        if (mobReason != null) {
            etRemark.setText(mobReason);
            Event<String> event = new Event<String>(FinalClass.C, mobReason);
            EventBusUtil.sendEvent(event);
        }

        session = (String) MyApplication.get(mContext, Session, "");


        reCarNo.setVisibility(View.GONE);
        reRemark.setVisibility(View.VISIBLE);
        initToolbarNav(llBack);

        tvTitle.setText("派车");
        btnSure.setText("确认派车");
        view0.setText("选择车辆");
        view1.setText("选择司机");
        view4.setText("派车备注");

        String cs = "点击选择车辆";
        String cs2 = "点击选择司机";
        String cs3 = "派车备注内容(非必填)";
        StringUtils.HintUtil(etCarNO, cs);
        StringUtils.HintUtil(etDriver, cs2);
        StringUtils.HintUtil(etRemark, cs3);

        mEtMsgOnKeyListener();
    }

    private void mEtMsgOnKeyListener() {
        etCarNO.addTextChangedListener(this);
        etDriver.addTextChangedListener(this);
        etRemark.addTextChangedListener(this);
    }

    private void getDriverData() {
        EasyHttp.post(Constants.MANAGER_DIVERS + session)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("DIVERS", result);
                        SelectDriverBean bean = Convert.fromJson(result, SelectDriverBean.class);
                        drivers.clear();
                        for (int i = 0; i < bean.getData().size(); i++) {
                            optionsItem2s.add(new SelectDriverBean.DataBean(bean.getData().get(i).getSeq(),
                                    bean.getData().get(i).getDriverName()));
                            drivers.add(bean.getData().get(i).getDriverName());
                        }
                        strDrivers = Utils.ListToString(drivers);
                        NormalListDialog dialog = new NormalListDialog(_mActivity, strDrivers);
                        dialog.title("选择部门")
                                .heightScale(0.7f)
                                .titleTextSize_SP(12)
                                .cornerRadius(5)
                                .itemTextSize(12)
                                .layoutAnimation(null)
                                .show();
                        dialog.setOnOperItemClickL(new OnOperItemClickL() {
                            @Override
                            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                etDriver.setText(strDrivers[position]);
                                dialog.dismiss();
                            }
                        });
                    }
                });
    }

    private void getCarNoData() {
        EasyHttp.post(Constants.MANAGER_CAR + session)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        SelectCarBean bean = Convert.fromJson(result, SelectCarBean.class);
                        Log.d("MANAGER_CAR", result);
                        carNos.clear();
                        if (bean.getStatus() == 0) {
                            for (int i = 0; i < bean.getData().size(); i++) {
                                optionsItem1s.add(new SelectCarBean.DataBean(bean.getData().get(i).getStatus(), bean.getData().get(i).getCarNo()));
                                carNos.add(bean.getData().get(i).getCarNo());
                            }
                            strCarNOs = Utils.ListToString(carNos);
                            NormalListDialog dialog = new NormalListDialog(_mActivity,new BaseSelector());
                            dialog.title("选择车牌号")
                                    .heightScale(0.7f)
                                    .titleTextSize_SP(12)
                                    .cornerRadius(5)
                                    .itemTextSize(12)
                                    .layoutAnimation(null)
                                    .show();
                            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                @Override
                                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    etCarNO.setText(strCarNOs[position]);
                                    dialog.dismiss();
                                }
                            });
                        } else {
                        }

                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            if (etCarNO.getText().toString().length() > 0) {
                mobCarNo = etCarNO.getText().toString();
                iconFontDelete0.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.A, mobCarNo);
                EventBusUtil.sendEvent(event);
            }
            if (etDriver.getText().toString().length() > 0) {
                mobDriver = etDriver.getText().toString();
                iconFontDelete1.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.B, mobDriver);
                EventBusUtil.sendEvent(event);
            }
            if (etRemark.getText().toString().length() > 0) {
                mobReason = etRemark.getText().toString();
                iconFontDelete4.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.C, mobReason);
                EventBusUtil.sendEvent(event);
            }
        } else {
            if (etCarNO.getText().toString().length() == 0) {
                mobCarNo = "";
                iconFontDelete0.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.A, mobCarNo);
                EventBusUtil.sendEvent(event);
            }
            if (etDriver.getText().toString().length() == 0) {
                mobDriver = "";
                iconFontDelete1.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.B, mobDriver);
                EventBusUtil.sendEvent(event);
            }
            if (etRemark.getText().toString().length() == 0) {
                mobReason = "";
                iconFontDelete4.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.C, mobReason);
                EventBusUtil.sendEvent(event);
            }
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
                mobCarNo = (String) event.getData();
                break;
            case FinalClass.B:
                mobDriver = (String) event.getData();
                break;
            case FinalClass.C:
                mobReason = (String) event.getData();
                break;
        }

        LatteLogger.d("dddddddd", mobCarNo + "   " + mobDriver + "   " +
                mobReason);

        if ("".equals(mobCarNo) || "".equals(mobDriver)) {
            btnSure.setClickable(false);
            btnSure.setBackgroundResource(R.color.grey_home);
        } else {
            btnSure.setClickable(true);
            btnSure.setBackgroundResource(R.drawable.selector_green_darkgreen);
        }
    }

    @OnClick({R.id.et_startTime, R.id.icon_font_delete0, R.id.et_overTime, R.id.icon_font_delete1, R.id.icon_font_delete4, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_startTime:
                getCarNoData();


                break;
            case R.id.icon_font_delete0:
                etCarNO.setText("");
                iconFontDelete0.setVisibility(View.GONE);
                break;
            case R.id.et_overTime:
                getDriverData();

                break;
            case R.id.icon_font_delete1:
                etDriver.setText("");
                iconFontDelete1.setVisibility(View.GONE);
                break;
            case R.id.icon_font_delete4:
                etRemark.setText("");
                iconFontDelete4.setVisibility(View.GONE);
                break;
            case R.id.btn_sure:
                getOptionData();
                break;
        }
    }

    private void getOptionData() {
        send_car = getIntent().getStringExtra("sendCarNo");//CL1903133
        LatteLogger.d("sendCar", send_car);
        EasyHttp.post(Constants.MANAGER_SEND_CAR + session)
                .params("SendCarNo", send_car)
                .params("CarNo", etCarNO.getText().toString())
                .params("Driver", etDriver.getText().toString())
                .params("Remarks", etRemark.getText().toString().trim())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage().toString());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("getOptionData",result);
                        CommonOrderStatusBean bean = Convert.fromJson(result, CommonOrderStatusBean.class);
                        if (bean.getStatus() == 0) {
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("msg", bean.getMsg());
                            intent.putExtras(bundle);
                            setResult(100, intent);
                            finish();
                            ToastUtils.getInstance().showToast(bean.getMsg());
                        } else {
                            ToastUtils.getInstance().showToast(bean.getMsg());
                        }
                    }
                });
    }

    public class BaseSelector extends BaseAdapter {

        @Override
        public int getCount() {
            return optionsItem1s.size();
        }

        @Override
        public Object getItem(int position) {
            return optionsItem1s.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(_mActivity).inflate(R.layout.css_carno_carstatus, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvTitle.setText(optionsItem1s.get(position).getCarNo());
            holder.tvStatus.setText(optionsItem1s.get(position).getStatus());

            return convertView;
        }

      public class ViewHolder {
            @BindView(R.id.tv_title)
            TextView tvTitle;
            @BindView(R.id.tv_status)
            TextView tvStatus;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


}

