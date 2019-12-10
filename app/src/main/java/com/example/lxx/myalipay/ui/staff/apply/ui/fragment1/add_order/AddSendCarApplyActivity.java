package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.add_order;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CommonOrderStatusBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.ItemDepartBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.ItemTeamBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.mob.GetMessageUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.AddressDialog;
import com.example.lxx.myalipay.widget.font.FontTextView2;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.Session;

/**
 * 派车申请单
 */
public class AddSendCarApplyActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_start_point)
    EditText tvGo;
    @BindView(R.id.text_arrive)
    TextView tvTo;
    @BindView(R.id.edit_manage_phone)
    EditText mPhone;
    @BindView(R.id.text_num_people)
    EditText mNumPerson;
    @BindView(R.id.text_accessory_items)
    EditText tv_accessory_items;
    @BindView(R.id.text_selected_message)
    EditText tvMessage;
    @BindView(R.id.icon_from_delete)
    FontTextView2 icon_from_delete;
    @BindView(R.id.icon_to_delete)
    FontTextView2 icon_to_delete;
    @BindView(R.id.icon_phone_delete)
    FontTextView2 icon_phone_delete;
    @BindView(R.id.icon_num_people_delete)
    FontTextView2 icon_num_people_delete;
    @BindView(R.id.icon_accessory_items_delete)
    FontTextView2 icon_accessory_items_delete;
    @BindView(R.id.icon_message_delete)
    FontTextView2 icon_message_delete;
    @BindView(R.id.icon_date_delete)
    FontTextView2 iconDateDelete;
    @BindView(R.id.icon_depart_delete)
    FontTextView2 iconDepartDelete;
    @BindView(R.id.icon_team_delete)
    FontTextView2 iconTeamDelete;
    @BindView(R.id.text_return_date)
    TextView textReturnDate;
    @BindView(R.id.icon_date_delete77)
    FontTextView2 iconReturnDate;
    @BindView(R.id.text_date)
    TextView tvTime;
    @BindView(R.id.text_selected_depart)
    TextView tvDepart;
    @BindView(R.id.text_selected_team)
    TextView tvTeam;
    @BindView(R.id.apply_normal)
    Button apply_now;
    @BindView(R.id.apply_succeed)
    Button apply_now_true;
    @BindView(R.id.svr)
    ScrollView scrollView;
    @BindView(R.id.root)
    LinearLayout root;
    List<String> Lists = new ArrayList<>();

    private TimePickerView pvTime, pvTime2;
    public final static int REQUEST_CODE = 100; //返回的结果码
    public final static int REQUEST_TEAM = 200; //返回组
    private String depart, team, time, returnTime, session,
            des, phone, mMessage, go_along_name, mygoods;
    private Message message;
    public final static int TO = 1;
    public final static int PHONE = 2;
    public final static int TEAM = 3;
    public final static int MESSAGE = 4;
    public final static int DEPART = 5;
    public final static int TIME = 6;
    public final static int GO_ALONG = 7;
    public final static int GOODS = 8;
    public final static int RETURN_TIME = 9;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String[] teams;
    Bundle bundle = new Bundle();
    public static AddSendCarApplyActivity newInstance() {
        AddSendCarApplyActivity fragment = new AddSendCarApplyActivity();
        return fragment;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            switch (msg.what) {
                case TO: {
                    des = bundle.getString("des");
                }
                break;
                case PHONE: {
                    phone = bundle.getString("phone");
                }
                break;
                case TEAM: {
                    team = bundle.getString("team");
                    tvTeam.setText(team);
                }
                break;
                case MESSAGE: {
                    mMessage = bundle.getString("mMessage");
                }
                break;
                case DEPART: {
                    depart = bundle.getString("depart");
                    tvDepart.setText(depart);
                }
                break;
                case TIME: {
                    time = bundle.getString("time");
                }
                break;
                case RETURN_TIME:
                    returnTime = bundle.getString("returnTime");
                    break;
                case GO_ALONG: {
                    go_along_name = bundle.getString("go_along_name");
                }
                break;
                case GOODS: {
                    if (mygoods != null) {
                        mygoods = bundle.getString("mygoods");
                    } else {
                        mygoods = "nulls";
                    }
                }
                break;
            }

            if (des != null && phone != null && time != null && depart != null && team != null && mMessage != null && returnTime != null
                    && des != "" && phone != "" && time != "" && depart != "" && team != "" && mMessage != "" && returnTime != "") {
                apply_now.setVisibility(View.GONE);
                apply_now_true.setVisibility(View.VISIBLE);
            } else {
                apply_now.setVisibility(View.VISIBLE);
                apply_now_true.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_apply_mobification;
    }

    @Override
    public void initView() {
        session = (String) MyApplication.get(_mActivity, Session, "");
        tvTitle.setText("派车申请");
        initToolbarNav(homeBack);
        tvGo.setText("公司");

        root.addOnLayoutChangeListener(mListener);
        mEtMsgOnKeyListener();

        selectedDate();
    }


    private void mEtMsgOnKeyListener() {
        hideSoftKeyboard();
        final Bundle bundle = new Bundle();
        tvTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    des = s.toString();
                    message = handler.obtainMessage(TO);
                    bundle.putString("des", des);
                    message.setData(bundle);
                    message.sendToTarget();
                    icon_to_delete.setVisibility(View.VISIBLE);
                } else {
                    icon_to_delete.setVisibility(View.GONE);
                    apply_now.setVisibility(View.VISIBLE);
                    apply_now_true.setVisibility(View.GONE);
                }
            }
        });
        mPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    phone = s.toString();
                    message = handler.obtainMessage(PHONE);
                    bundle.putString("phone", phone);
                    message.setData(bundle);
                    message.sendToTarget();
                    icon_phone_delete.setVisibility(View.VISIBLE);
                } else {
                    icon_phone_delete.setVisibility(View.GONE);
                    apply_now.setVisibility(View.VISIBLE);
                    apply_now_true.setVisibility(View.GONE);
                }
            }
        });
        tvMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mMessage = s.toString();
                    message = handler.obtainMessage(MESSAGE);
                    bundle.putString("mMessage", mMessage);
                    message.setData(bundle);
                    message.sendToTarget();
                    icon_message_delete.setVisibility(View.VISIBLE);
                } else {
                    icon_message_delete.setVisibility(View.GONE);
                    apply_now.setVisibility(View.VISIBLE);
                    apply_now_true.setVisibility(View.GONE);
                }
            }
        });
        mNumPerson.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    go_along_name = mNumPerson.getText().toString();
                    icon_num_people_delete.setVisibility(View.VISIBLE);
                } else {
                    icon_num_people_delete.setVisibility(View.GONE);
                }
                message = handler.obtainMessage(GO_ALONG);
                bundle.putString("go_along_name", go_along_name);
                message.setData(bundle);
                message.sendToTarget();
            }
        });
        tv_accessory_items.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mygoods = tv_accessory_items.getText().toString();
                    icon_accessory_items_delete.setVisibility(View.VISIBLE);
                } else {
                    mygoods = tv_accessory_items.getText().toString();
                    icon_accessory_items_delete.setVisibility(View.GONE);
                }
                message = handler.obtainMessage(GOODS);
                bundle.putString("mygoods", mygoods);
                message.setData(bundle);
                message.sendToTarget();
            }
        });
        GetMessageUtil util = new GetMessageUtil();
        util.editChanged(tvGo, icon_from_delete);

        tvTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    iconDateDelete.setVisibility(View.VISIBLE);
                    time = tvTime.getText().toString();
                    message = handler.obtainMessage(GOODS);
                    bundle.putString("time", mygoods);
                    message.setData(bundle);
                    message.sendToTarget();
                } else {
                    iconDateDelete.setVisibility(View.GONE);
                    apply_now.setVisibility(View.VISIBLE);
                    apply_now_true.setVisibility(View.GONE);
                }
            }
        });
        textReturnDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    iconReturnDate.setVisibility(View.VISIBLE);
                    returnTime = textReturnDate.getText().toString();
                    message = handler.obtainMessage(GOODS);
                    bundle.putString("returnTime", mygoods);
                    message.setData(bundle);
                    message.sendToTarget();
                } else {
                    iconReturnDate.setVisibility(View.GONE);
                    apply_now.setVisibility(View.VISIBLE);
                    apply_now_true.setVisibility(View.GONE);
                }

            }
        });
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                _mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(root.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void selectedDate() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        pvTime2 = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textReturnDate.setText(getTime(date));
                returnTime = getTime(date);
                message = handler.obtainMessage(RETURN_TIME);
                Bundle bundle = new Bundle();
                bundle.putString("returnTime", returnTime);
                message.setData(bundle);
                message.sendToTarget();
            }
        }).setDate(selectedDate).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {

            }
        }).setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("预计返回时间")
                .setTitleSize(14)
                .setContentTextSize(14)
                .setSubCalSize(14)
                .isDialog(true)
                .build();


        pvTime = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvTime.setText(getTime(date));
                time = getTime(date);
                message = handler.obtainMessage(TIME);
                Bundle bundle = new Bundle();
                bundle.putString("time", time);
                message.setData(bundle);
                message.sendToTarget();

            }
        }).setDate(selectedDate)
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
                }).setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("出行时间")
                .setTitleSize(14)
                .setContentTextSize(14)
                .setSubCalSize(14)
                .isDialog(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        Dialog mDialog2 = pvTime2.getDialog();
        if (mDialog2 != null) {
            FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
            );
            params2.leftMargin = 20;
            params2.rightMargin = 20;
            pvTime2.getDialogContainerLayout().setLayoutParams(params2);

            Window dialogWindow = mDialog2.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setGravity(Gravity.CENTER);
            }
        }
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
            );
            params.leftMargin = 20;
            params.rightMargin = 20;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setGravity(Gravity.CENTER);//改成Bottom，底部显示
            }
        }
    }

    /**
     * 选择日期
     */
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        return sf.format(date);
    }

    public View.OnLayoutChangeListener mListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            Log.i("OnLayoutChangeListener", oldLeft + "\t" + oldRight + "\t" + oldTop + "\t" + oldBottom);
            Log.i("OnLayoutChangeListener", left + "\t" + right + "\t" + top + "\t" + bottom);
            if (oldBottom != 0 && (oldBottom - bottom) > 150) {
                srcoll(oldBottom - bottom);
            }
        }
    };

    public void srcoll(int y) {
        int scrollY = scrollView.getScrollY();
        if (scrollY == 0) {
            scrollView.scrollBy(0, 9999);
        }
    }

    @OnClick({R.id.icon_from_delete, R.id.icon_to_delete, R.id.icon_phone_delete, R.id.icon_num_people_delete,
            R.id.icon_accessory_items_delete, R.id.text_date, R.id.text_selected_depart,
            R.id.text_selected_team, R.id.icon_message_delete, R.id.apply_normal, R.id.apply_succeed,
            R.id.icon_date_delete, R.id.icon_date_delete77, R.id.text_return_date, R.id.text_arrive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_from_delete:
                tvGo.setText("");
                break;
            case R.id.icon_to_delete:
                tvTo.setText("");
                break;
            case R.id.icon_phone_delete:
                mPhone.setText("");
                break;
            case R.id.icon_num_people_delete:
                mNumPerson.setText("");
                break;
            case R.id.icon_accessory_items_delete:
                tv_accessory_items.setText("");
                break;
            case R.id.icon_date_delete:
                tvTime.setText("");
                break;
            case R.id.text_date:
                //出行时间
                pvTime.show();//弹出条件选择器
                break;
            case R.id.text_return_date:
                pvTime2.show();
                break;
            case R.id.icon_date_delete77:
                textReturnDate.setText("");
                break;
            case R.id.text_selected_depart:
                EasyHttp.post(Constants.MANAGER_DEPART + session)
                        .execute(new SimpleCallBack<String>() {
                            @Override
                            public void onError(ApiException e) {

                            }

                            @Override
                            public void onSuccess(String result) {
                                LatteLogger.d("result", result);
                                ItemDepartBean bean = Convert.fromJson(result, ItemDepartBean.class);
                                Lists.clear();
                                for (int i = 0; i < bean.getData().size(); i++) {
                                    Lists.add(bean.getData().get(i).getDeptName());
                                }

                                teams = Lists.toArray(new String[Lists.size()]);
                                final NormalListDialog dialog = new NormalListDialog(_mActivity, teams);
                                dialog.title("选择部门")
                                        .titleTextSize_SP(12)
                                        .itemTextSize(12)
                                        .cornerRadius(5)
                                        .heightScale(0.7f)
                                        .layoutAnimation(null)
                                        .show();
                                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                    @Override
                                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        tvDepart.setText(teams[position]);
                                        message = handler.obtainMessage(DEPART);
                                        bundle.putString("depart", tvDepart.getText().toString());
                                        message.setData(bundle);
                                        message.sendToTarget();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                break;
            case R.id.text_selected_team:
                EasyHttp.post(Constants.MANAGER_TEAM + session)
                        .execute(new SimpleCallBack<String>() {
                            @Override
                            public void onError(ApiException result) {

                            }

                            @Override
                            public void onSuccess(String result) {
                                final ItemTeamBean bean = Convert.fromJson(result, ItemTeamBean.class);
                                Lists.clear();
                                for (int i = 0; i < bean.getData().size(); i++) {
                                    Lists.add(bean.getData().get(i).getGroupName());
                                }
                                teams = Lists.toArray(new String[Lists.size()]);

                                final NormalListDialog dialog = new NormalListDialog(_mActivity, teams);
                                dialog.title("选择组别")
                                        .titleTextSize_SP(12)
                                        .itemTextSize(12)
                                        .layoutAnimation(null)
                                        .show();
                                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                    @Override
                                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        tvTeam.setText(teams[position]);
                                        message = handler.obtainMessage(TEAM);
                                        bundle.putString("team",tvTeam.getText().toString());
                                        message.setData(bundle);
                                        message.sendToTarget();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                break;
            case R.id.text_arrive:
                AddressDialog dialog = new AddressDialog(_mActivity,des);
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
                break;
            case R.id.icon_message_delete:
                tvMessage.setText("");
                break;
            case R.id.apply_normal:
                ToastUtils.getInstance().showToast("你好像还有内容没填噢!@——@");
                break;
            case R.id.apply_succeed:
                //提交按钮
                getJsonData();
                break;
        }
    }


    private void getJsonData() {
        String along = null;
        String mGoods = null;
        if (go_along_name != null) {
            along = go_along_name;
        } else {
            along = "";
        }
        if (mygoods != null) {
            mGoods = mygoods;
        } else {
            mGoods = "";
        }
        Date date = new Date();
        String initDa = sf.format(date);
        EasyHttp.post(Constants.NEWADDORDER + session)
                .params("Entourage", along)
                .params("Destination", des)
                .params("Tel", phone)
                .params("Items", mGoods)
                .params("UseCarTime", time)
                .params("UserCarDept", depart)
                .params("UserCarGroup", team)
                .params("Reason", mMessage)
                .params("ExpectReTime", returnTime)
                .params("OrderTime", initDa)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage().toString());
                    }

                    @Override
                    public void onSuccess(String s) {
                        CommonOrderStatusBean bean = Convert.fromJson(s, CommonOrderStatusBean.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("result", bean.getMsg());
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                        ToastUtils.getInstance().showToast( bean.getMsg());
                    }
                });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onStickyEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
                String msg = (String) event.getData();
                LatteLogger.d("FinalClass", msg);
                tvTo.setText(msg);
                break;
        }
    }
}

