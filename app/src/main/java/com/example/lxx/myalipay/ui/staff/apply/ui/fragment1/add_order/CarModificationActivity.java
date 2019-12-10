package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.add_order;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.Session;

/**
 * 修改派车
 */
public class CarModificationActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
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

    private TimePickerView pvTime;
    public final static int REQUEST_CODE = 100; //返回的结果码
    public final static int REQUEST_TEAM = 200; //返回组
    private String depart, team, time, iniTime, session, des, phone, mMessage, times, go_along_name, mygoods;
    //修改
    private String orderId, Mtime, event, mudidi, applyName, goAlong,Tel,Items,Team,Depart;
    private String orderId2, Mtime2, event2, mudidi2, applyName2, goAlong2,Tel2,Items2,Team2,Depart2;
    private Message message;
    public final static int TO = 1;
    public final static int PHONE = 2;
    public final static int TEAM = 3;
    public final static int MESSAGE = 4;
    public final static int DEPART = 5;
    public final static int TIME = 6;
    public final static int GO_ALONG = 7;
    public final static int GOODS = 8;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    List<String> Lists = new ArrayList<>();
    private String[] teams;
    private Bundle bundle = new Bundle();
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
                    iniTime = bundle.getString("iniTime");
                    if (iniTime == null) {
                        tvTime.setText(time);
                    } else {
                        tvTime.setText(iniTime);
                    }
                    times = null;
                    if (iniTime != null) {
                        times = iniTime;
                    } else {
                        times = time;
                    }
                }
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

            if (des != null && phone != null && time != null && depart != null && team != null && mMessage != null) {
                apply_now.setVisibility(View.GONE);
                apply_now_true.setVisibility(View.VISIBLE);

            } else if (orderId != null && Mtime != null && event != null && Depart != null && Team != null && mudidi!=null){
                apply_now_true.setVisibility(View.VISIBLE);
                apply_now.setVisibility(View.GONE);

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
        session = (String) MyApplication.get(mContext, Session, "");
        tvTitle.setText("修改申请单");
        initToolbarNav(llBack);
        tvGo.setText("公司");
        apply_now.setText("马上修改");
        apply_now_true.setText("马上修改");

        Date date = new Date();
        iniTime = sf.format(date);
        message = handler.obtainMessage(TIME);
        Bundle bundle = new Bundle();
        bundle.putString("iniTime", iniTime);
        message.setData(bundle);
        message.sendToTarget();
        //修改的传值
        Bundle bundle4 = getIntent().getExtras();
        orderId = bundle4.getString("orderId");
        Mtime = bundle4.getString("time");
        event = bundle4.getString("event");
        mudidi = bundle4.getString("mudidi");
        applyName = bundle4.getString("applyName");
        goAlong = bundle4.getString("goAlong");
        Tel = bundle4.getString("Tel");
        Items = bundle4.getString("Items");
        Team = bundle4.getString("Team");
        Depart = bundle4.getString("Depart");
        orderId2 = orderId;
        Mtime2 = Mtime;
        event2 = event;
        mudidi2 = mudidi;
        applyName2 = applyName;
        goAlong2 = goAlong;
        Tel2 = Tel;
        Items2 = Items;
        Team2 = Team;
        Depart2 = Depart;
        if (mudidi !=null){
            tvTo.setText(mudidi);
            tvTo.setTextColor(getResources().getColor(R.color.black));
        }
        if (Tel !=null){
            mPhone.setText(Tel);
            mPhone.setTextColor(getResources().getColor(R.color.black));
        }
        if (goAlong !=null){
            mNumPerson.setText(goAlong);
            mNumPerson.setTextColor(getResources().getColor(R.color.black));
        }
        if (Items !=null){
            tv_accessory_items.setText(Items);
            tv_accessory_items.setTextColor(getResources().getColor(R.color.black));
        }
        if (Mtime !=null){
            tvTime.setText(Mtime);
            Message m = handler.obtainMessage(TIME);
            Bundle b = new Bundle();
            b.putString("initTime", Mtime);
            m.setData(b);
            m.sendToTarget();
            tvTime.setTextColor(getResources().getColor(R.color.black));
        }else{
            tvTime.setText(sf.format(date));
            tvTime.setTextColor(getApplicationContext().getResources().getColor(R.color.black));
        }
        if (Depart !=null){
            tvDepart.setText(Depart);
            tvDepart.setTextColor(getResources().getColor(R.color.black));
        }

        if (Team !=null){
            tvTeam.setText(Team);
            tvTeam.setTextColor(getResources().getColor(R.color.black));
        }
        if (event !=null){
            tvMessage.setText(event);
            tvMessage.setTextColor(getResources().getColor(R.color.black));
        }

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
                    go_along_name = mNumPerson.getText().toString();
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
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(root.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void selectedDate() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
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
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {

            }
        }).setType(new boolean[]{true, true, true, true, true, false})
                .isDialog(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM
            );
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom，底部显示
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
            R.id.text_selected_team, R.id.icon_message_delete, R.id.apply_normal, R.id.apply_succeed,R.id.text_arrive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_arrive:
                AddressDialog dialog = new AddressDialog(_mActivity,des);
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
                break;
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
            case R.id.text_date:
                //出行时间
                pvTime.show();//弹出条件选择器
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
                                dialog.title("选择房号")
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
                                        bundle.putString("team", tvTeam.getText().toString());
                                        message.setData(bundle);
                                        message.sendToTarget();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
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
        String mSteam = null;
        String mDepart = null;
        String mEvent = null;
        String mPhones = null;
        String mMudidi = null;
        String Itimes = null;
        String personNum = null;
        String dates = null;

        if (team !=null){
            mSteam = team;
        }else if (Team.equals(Team2)){
            mSteam = Team;
        }
        if (depart !=null){
            mDepart = depart;
        }else if (Depart.equals(Depart2)){
            mDepart = Depart;
        }
        if (mMessage !=null){
            mEvent = mMessage;
        }else if (event.equals(event2)){
            mEvent = event;
        }
        if (phone !=null){
            mPhones = phone;
        }else if (Tel.equals(Tel2)){
            mPhones =Tel;
        }
        if (des !=null){
            mMudidi = des;
        }else if (mudidi.equals(mudidi2)){
            mMudidi = mudidi;
        }
        if (mygoods !=null){
            Itimes = mygoods;
        }else if (Items.equals(Items2)){
            Itimes = Items;
        }else{
            Itimes = "";
        }
        if (go_along_name !=null){
            personNum = go_along_name;
        }else if (goAlong.equals(goAlong2)){
            personNum = goAlong;
        }else{
            personNum = "";
        }
        if (time !=null){
            dates = time;
        }else if (Mtime.equals(Mtime2)){
            dates = Mtime;
        }

        EasyHttp.post(Constants.MOBELORDER + session)
                .params("SendCarNo", orderId) //派车单号
                .params("UserCarGroup", mSteam) //用车组别
                .params("UserCarDept", mDepart) //用车部门
                .params("Reason", mEvent)  //派车事由
                .params("Tel", mPhones)  //电话
                .params("Destination", mMudidi) //目的地
                .params("Entourage", personNum)  //随车人员 可为空
                .params("Items", Itimes)  //随车物品  可为空
                .params("UseCarTime", dates) //用车时间
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("getJsonData",result);
                        CommonOrderStatusBean bean = Convert.fromJson(result, CommonOrderStatusBean.class);
                        Intent intent = new Intent();
                        setResult(150, intent);
                        ToastUtils.getInstance().showToast(bean.getMsg());
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = new Bundle();
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            depart = data.getExtras().getString("result");
            message = handler.obtainMessage(DEPART);
            bundle.putString("depart", depart);
            message.setData(bundle);
            message.sendToTarget();
//            tvDepart.setText(depart);
            tvDepart.setTextColor(getResources().getColor(R.color.black));
        }
        if (requestCode == REQUEST_TEAM && resultCode == RESULT_OK && data != null) {
            team = data.getExtras().getString("resultTeam");
            message = handler.obtainMessage(TEAM);
            bundle.putString("team", team);
            message.setData(bundle);
            message.sendToTarget();
            //    tvTeam.setText(team);
            tvTeam.setTextColor(getResources().getColor(R.color.black));
        }
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


