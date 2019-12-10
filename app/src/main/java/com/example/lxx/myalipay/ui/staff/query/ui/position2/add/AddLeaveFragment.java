package com.example.lxx.myalipay.ui.staff.query.ui.position2.add;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.LeaveFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.bean.LeaveNewBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * created by lxx at 2019/12/3 11:36
 * 描述:请假申请单
 */
public class AddLeaveFragment extends BaseBackFragment implements TextWatcher {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.tv_mudidi)
    TextView tvMudidi;
    @BindView(R.id.iv_delete_mudidi)
    ImageView ivDeleteMudidi;
    @BindView(R.id.ll_mudidi)
    LinearLayout llMudidi;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.iv_delete_type)
    ImageView ivDeleteType;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.iv_delete_start)
    ImageView ivDeleteStart;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.iv_delete_end)
    ImageView ivDeleteEnd;
    @BindView(R.id.tv_auto_hour)
    EditText tvAutoHour;
    @BindView(R.id.iv_delete_auto_hour)
    ImageView ivDeleteAutoHour;
    @BindView(R.id.et_case)
    EditText etCase;
    @BindView(R.id.apply_normal)
    Button applyNormal;
    @BindView(R.id.apply_succeed)
    Button applySucceed;
    @BindView(R.id.apply_relative)
    RelativeLayout applyRelative;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private TimePickerView pvTime1, pvTime2;
    private String titles;
    private String session;
    private String type, start, end, tvHour, stCase, sign, types, index;
    private LeaveNewBean bean;

    private String[] ArrType = {"事假", "病假", "产假", "婚假", "调休", "年假", "其他", "工伤", "通宵", "半通宵", "休假"};

    Message message = new Message();
    Bundle bundle = new Bundle();
    
    public static AddLeaveFragment newInstance(String title, LeaveNewBean bean, String sign, String type) {
        AddLeaveFragment fragment = new AddLeaveFragment();
        fragment.titles = title;
        fragment.bean = bean;
        fragment.sign = sign;
        fragment.types = type;
        return fragment;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            type = bundle.getString("type");
            start = bundle.getString("start");
            end = bundle.getString("end");
            tvHour = bundle.getString("tvHour");
            stCase = bundle.getString("etCase");
            LatteLogger.d("time",type+"\n"+ start + "\n" + end + "\n" + tvHour + "\n" + stCase);
            if (type != "" && start != "" && end != ""&&tvHour!=""&&stCase!=""
                    &&!TextUtils.isEmpty(type)&&!TextUtils.isEmpty(start)
                    &&!TextUtils.isEmpty(end)&&!TextUtils.isEmpty(tvHour)&&!TextUtils.isEmpty(stCase)) {
                applySucceed.setVisibility(View.VISIBLE);
                applyNormal.setVisibility(View.GONE);
            }else{
                applySucceed.setVisibility(View.GONE);
                applyNormal.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.add_leave;
    }

    @Override
    public void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("申请" + titles);
        selectedTime(tvStartTime, tvEndTime);
        session = (String) MyApplication.get(_mActivity, Session, "");
        tvType.addTextChangedListener(this);
        tvStartTime.addTextChangedListener(this);
        tvEndTime.addTextChangedListener(this);
        tvAutoHour.addTextChangedListener(this);
        etCase.addTextChangedListener(this);
        if (sign == "2") {
            tvStartTime.setText(bean.getStartTime());
            tvEndTime.setText(bean.getEndTime());
            etCase.setText(bean.getReason());
            int t = (new Double(bean.getLongTime())).intValue();
            tvAutoHour.setText(String.valueOf(t));
            if (bean.getMorning() != null) {
                index = bean.getMorning();
            } else if (bean.getAfter() != null) {
                index = bean.getAfter();
            } else {
                index = bean.getDinner();
            }
            tvType.setText(index);
            bundle.putString("start", tvStartTime.getText().toString());
            bundle.putString("end", tvEndTime.getText().toString());
            bundle.putString("tvHour", tvAutoHour.getText().toString());
            bundle.putString("etCase", etCase.getText().toString());
            bundle.putString("type", tvType.getText().toString());
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
        }
    }

    private void RequestDataList() {
        final NormalListDialog dialog = new NormalListDialog(_mActivity, ArrType);
        dialog.title("请假类型")
                .titleTextSize_SP(14)
                .itemTextSize(14)
                .layoutAnimation(null)
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvType.setText(ArrType[position]);
                dialog.dismiss();
            }
        });
    }

    private void bigsmall(String start, String end) {
        try {
            if (start != null && end != null) {
                Date date1 = sf.parse(start);
                Date date2 = sf.parse(end);
                if (date1.getTime() > date2.getTime() || date1.getTime() == date2.getTime()) {
                    final NormalDialog dialog = new NormalDialog(_mActivity);
                    dialog.content("结束时间不能小于或等于开始时间")
                            .style(NormalDialog.STYLE_ONE)
                            .show();
                    dialog.setOnBtnClickL(new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();

                        }
                    }, new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    });
                    tvEndTime.setText("");
                } else {
                    //  tvAutoHour.setText(DateUtils.CountAddMinus(sf, tvStartTime.getText().toString(), tvEndTime.getText().toString(),1));
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_type, R.id.tv_startTime, R.id.tv_endTime, R.id.apply_succeed, R.id.iv_delete_type, R.id.iv_delete_start, R.id.iv_delete_end, R.id.iv_delete_auto_hour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_type:
                RequestDataList();
                break;
            case R.id.tv_startTime:
                pvTime1.show();
                break;
            case R.id.tv_endTime:
                pvTime2.show();
                break;
            case R.id.apply_normal:
                if (start == null) {
                    ToastUtils.getInstance().showToast("开始时间不能为null");
                }
                if (end == null) {
                    ToastUtils.getInstance().showToast( "结束时间不能为null");
                }
                if (tvHour == null) {
                    ToastUtils.getInstance().showToast("时长不能为null");
                }
                if (type == null) {
                    ToastUtils.getInstance().showToast("类型不能为null");
                }
                if (stCase == null) {
                    ToastUtils.getInstance().showToast("请假原因不能为null");
                }
                break;
            case R.id.apply_succeed:
                //type,start,end,tvHour,stCase
                if (type != null && start != null && end != null && tvHour != null && stCase != null) {
                    if (sign == "2") {
                        submitData2(bean.get_id(), start,
                                end,
                                tvHour,
                                type,
                                stCase);
                    } else {
                        submitData(start,
                                end,
                                tvHour,
                                type,
                                stCase);
                    }
                }
                break;
            case R.id.iv_delete_type:
                tvType.setText("");
                break;
            case R.id.iv_delete_start:
                tvStartTime.setText("");
                break;
            case R.id.iv_delete_end:
                tvEndTime.setText("");
                break;
            case R.id.iv_delete_auto_hour:
                tvAutoHour.setText("");
                break;
        }
    }

    /**
     * 提交请假
     */
    private void submitData2(String _id, final String start, String end, String hour, final String type, String etcase) {
        EasyHttp.post(Constants.REQUEST_OA_MOB + session)
                .params("Id", _id)
                .params("StartTime", start)
                .params("EndTime", end)
                .params("LeaveTime", hour)
                .params("Type", type)
                .params("Reason", etcase)
                .params("Ltype", types)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("submit", s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int status = jsonObject.getInt("Status");
                            if (status == 0) {
                                ToastUtils.getInstance().showToast("提交成功");
                                Bundle bundle = new Bundle();
                                bundle.putString(LeaveFragment.KEY_RESULT_TYPE, type);
                                setFragmentResult(RESULT_OK, bundle);
                                _mActivity.onBackPressed();
                            } else {
                                ToastUtils.getInstance().showToast(jsonObject.getString("Msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 提交请假
     */
    private void submitData(String start, String end, String hour, final String type, String etcase) {
        EasyHttp.post(Constants.SUBMIT_LEAVE + session)
                .params("StartTime", start)
                .params("EndTime", end)
                .params("LeaveTime", hour)
                .params("Type", type)
                .params("Reason", etcase)
                .params("Ltype", types)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int status = jsonObject.getInt("Status");
                            if (jsonObject.getString("Msg").equals(sessionE)){
                                start(LoginFragment.newInstance());
                                ToastUtils.getInstance().showToast(sessionPastDue);
                            }else {
                                if (status == 0) {
                                    ToastUtils.getInstance().showToast("提交成功");
                                    Bundle bundle = new Bundle();
                                    bundle.putString(LeaveFragment.KEY_RESULT_TYPE, type);
                                    setFragmentResult(RESULT_OK, bundle);
                                    _mActivity.onBackPressed();
                                } else {
                                    ToastUtils.getInstance().showToast(jsonObject.getString("Msg"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void selectedTime(final TextView start, final TextView end) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        pvTime1 = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                start.setText(getTime(date));

            }
        }).setDate(selectedDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("开始时间")
                .setSubCalSize(14)
                .setTitleSize(14)
                .setContentTextSize(14)
                .isDialog(true)
                .build();
        pvTime2 = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                end.setText(getTime(date));

            }
        }).setDate(selectedDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("结束时间")
                .setSubCalSize(14)
                .setTitleSize(14)
                .setContentTextSize(14)
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
                dialogWindow.setGravity(Gravity.CENTER);//改成Bottom，底部显示
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
                dialogWindow.setGravity(Gravity.CENTER);//改成Bottom，底部显示
            }
        }
    }

    private String getTime(Date date) {
        return sf.format(date);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        type = tvType.getText().toString();
        start = tvStartTime.getText().toString();
        end = tvEndTime.getText().toString();
        tvHour = tvAutoHour.getText().toString();
        stCase = etCase.getText().toString();
        bigsmall(start, end);

        if (!TextUtils.isEmpty(type)) {
            bundle.putString("type", type);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
            ivDeleteType.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            bundle.putString("type", type);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
            ivDeleteType.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(start)) {
            bundle.putString("start", start);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
            ivDeleteStart.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            bundle.putString("start", start);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
            ivDeleteStart.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(end)) {
            bundle.putString("end", end);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
            ivDeleteEnd.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            bundle.putString("end", end);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
            ivDeleteEnd.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(tvHour)) {
            bundle.putString("tvHour", tvHour);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
            ivDeleteAutoHour.setVisibility(View.VISIBLE);
            ivDeleteAutoHour.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteAutoHour.setVisibility(View.GONE);
            bundle.putString("tvHour", tvHour);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
        }
        if (!TextUtils.isEmpty(stCase)) {
            bundle.putString("etCase", stCase);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
        }else{
            bundle.putString("etCase", stCase);
            message = mHandler.obtainMessage();
            message.setData(bundle);
            message.sendToTarget();
        }
    }
}
