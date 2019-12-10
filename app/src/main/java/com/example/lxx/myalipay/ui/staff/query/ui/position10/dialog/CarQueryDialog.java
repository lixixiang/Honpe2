package com.example.lxx.myalipay.ui.staff.query.ui.position10.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.staff.query.ui.position10.dialog.adapter.DriverTagAdapter;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.tabutils.FlowTagLayout;
import com.example.lxx.myalipay.utils.tabutils.OnTagSelectListener;
import com.example.lxx.myalipay.utils.tabutils.bean.TagInfo;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position10.dialog
 * 作者: lxx
 * 日期: 2019/4/27 13:52
 * 描述: 派车历史记录多项查询
 */
public class CarQueryDialog implements CcommodityPresenterInf, View.OnClickListener {

    public MyDialog mBottomSheetDialog;
    private Activity mActivity;
    private View contentView;

    private TextView tvStartTime, tvEndTime;
    private TimePickerView pvTime1, pvTime2;
    private SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
    private FlowTagLayout rlDriver, rlCarNO;
    private List<TagInfo> DriverList;
    private List<TagInfo> CarNOList;
    private String[] carNos;
    private String[] DriverNames;
    private DriverTagAdapter driverTagAdapter;

    private Button btnQuery;
    private String strDriver, strCarNO, StrStartTime, strStart = "", strEnd = "";
    private int driverPosition,CarNoPosition;

    public CarQueryDialog(Activity mActivity, String[] DriverNames, String[] carNos, String StrStartTime, String oldST, String oldET) {
        this.mActivity = mActivity;
        this.DriverNames = DriverNames;
        this.carNos = carNos;
        this.StrStartTime = StrStartTime;
        this.strStart = oldST;
        this.strEnd = oldET;
    }

    @Override
    public void showDialog() {
        //----------------弹出窗口
        mBottomSheetDialog = new MyDialog(mActivity, R.style.GoodDialog);
        //设置退出速度
        mBottomSheetDialog.outDuration(100);
        mBottomSheetDialog.inDuration(100);
        //设置铺满
        mBottomSheetDialog.heightParam(ViewGroup.LayoutParams.WRAP_CONTENT);
        //解析视图
        contentView = LayoutInflater.from(mActivity).inflate(R.layout.car_query, null);
        //设置视图
        mBottomSheetDialog.setContentView(contentView);

        //取消按钮
        ImageView ivClose = (ImageView) contentView.findViewById(R.id.iv_close);
        //开始时间和结束时间
        tvStartTime = contentView.findViewById(R.id.tv_startTime);
        tvEndTime = contentView.findViewById(R.id.tv_endTime);
        rlDriver = contentView.findViewById(R.id.rl_driver_name);
        rlCarNO = contentView.findViewById(R.id.rl_car_no);
        btnQuery = contentView.findViewById(R.id.btn_buy_input_message);

        if (!TextUtils.isEmpty(strStart))
            tvStartTime.setText(strStart);
        if (!TextUtils.isEmpty(strEnd))
            tvEndTime.setText(strEnd);


        //设置监听
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        mBottomSheetDialog.show();

        selectedTime();
        initDriverName();
        initCarNo();


    }

    /**
     * 初始化司机姓名
     */
    private void initDriverName() {

        DriverList = new ArrayList<>();
        for (int i = 0; i < DriverNames.length; i++) {
            TagInfo item = new TagInfo(false, DriverNames[i]);
            DriverList.add(item);
        }



        driverTagAdapter = new DriverTagAdapter(mActivity, DriverList);
        rlDriver.setAdapter(driverTagAdapter);
        driverTagAdapter.notifyDataSetChanged();
        rlDriver.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);

        rlDriver.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                driverPosition = selectedList.get(0);
                strDriver = DriverList.get(driverPosition).getText();
            }
        });
    }


    /**
     * 初始化车牌号
     */
    private void initCarNo() {
        CarNOList = new ArrayList<>();
        for (int i = 0; i < carNos.length; i++) {
            TagInfo item = new TagInfo(false, carNos[i]);
            CarNOList.add(item);
        }

        if (CarNoPosition != -1) {
            TagInfo item = new TagInfo();
            item.setText(carNos[CarNoPosition]);
            item.setChecked(true);
            CarNOList.set(CarNoPosition,item);
        }

        driverTagAdapter = new DriverTagAdapter(mActivity, CarNOList);
        rlCarNO.setAdapter(driverTagAdapter);
        driverTagAdapter.notifyDataSetChanged();
        rlCarNO.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);


        rlCarNO.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                CarNoPosition = selectedList.get(0);
                strCarNO = CarNOList.get(CarNoPosition).getText();
            }
        });
    }

    private void selectedTime() {
        Calendar selectedDate = Calendar.getInstance();
        pvTime1 = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvStartTime.setText(getTime(date));
            }
        }).setDate(selectedDate)
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        tvStartTime.setText(getTime(date));
                    }
                }).setType(new boolean[]{false, false, false, true, true, false})
                .isDialog(true)
                .build();
        pvTime2 = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvEndTime.setText(getTime(date));
            }
        }).setDate(selectedDate)
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        tvEndTime.setText(getTime(date));
                    }
                }).setType(new boolean[]{false, false, false, true, true, false})
                .isDialog(true)
                .build();
        Dialog mDialog = pvTime1.getDialog();
        Dialog mDialog2 = pvTime2.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setGravity(Gravity.CENTER);
            }
        }
        if (mDialog2 != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime2.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog2.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setGravity(Gravity.CENTER);
            }
        }
    }

    private String getTime(Date date) {
        return sf.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_startTime:
                pvTime1.show();
                break;
            case R.id.tv_endTime:
                pvTime2.show();
                break;
            case R.id.iv_close://关闭弹窗
                mBottomSheetDialog.dismiss();
                break;
            case R.id.btn_buy_input_message: //查询按钮
                if (inputMessage != null) {
                    if (tvStartTime.getText().toString().equals("开始时间") || tvEndTime.getText().toString().equals("结束时间")) {
                        tvStartTime.setText("00:00");
                        tvEndTime.setText("23:59");
                    }
                    inputMessage.getDialogData(StrStartTime + " " + tvStartTime.getText().toString(),
                            StrStartTime + " " + tvEndTime.getText().toString(), tvStartTime.getText().toString(), tvEndTime.getText().toString(), strDriver, strCarNO);
                }
                mBottomSheetDialog.dismiss();
                break;
        }
    }

    public BtnBuyInputMessage inputMessage;

    public void setBtnMessage(BtnBuyInputMessage listener) {
        inputMessage = listener;
    }

    public interface BtnBuyInputMessage {
        void getDialogData(String startTime, String endTime, String oldStartTime, String oldEndTime, String driver, String carNo);
    }
}