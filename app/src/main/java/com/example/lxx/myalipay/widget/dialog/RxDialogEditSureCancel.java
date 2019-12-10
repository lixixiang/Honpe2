package com.example.lxx.myalipay.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.ui.staff.query.ui.position1.bean.EventBean;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.widget.SettingTextWatcher;
import com.flyco.dialog.widget.base.BaseDialog;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * created by lxx at 2019/12/3 11:01
 * 描述:
 */
public class RxDialogEditSureCancel<C extends BaseDialog<C>> extends Dialog {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_sub1)
    LinearLayout llSub1;
    @BindView(R.id.tv_sub2)
    TextView tvSub2;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.ll_sub2)
    LinearLayout llSub2;
    @BindView(R.id.no)
    Button no;
    @BindView(R.id.yes)
    Button yes;
    private String strSub2, strTitle, hint,InputUserNos;
    private Activity activity;

    public RxDialogEditSureCancel(Activity activity,String InputUserNos, String strTitle, String strSub2, String hint) {
        super(activity, R.style.comment_dialog);
        this.activity = activity;
        this.strSub2 = strSub2;
        this.strTitle = strTitle;
        this.hint = hint;
        this.InputUserNos = InputUserNos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_dialog);
        initWindowParams();
        ButterKnife.bind(this);
        initView();
    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        //获取屏幕宽、高用
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (display.getWidth() * 0.84); // 宽度设置为屏幕的0.65

        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        llSub1.setVisibility(View.GONE);
        tvSub2.setText(strSub2);
        tvTitle.setText(strTitle);
        etAddress.setText(InputUserNos);

        if (!"".equals(hint) && !TextUtils.isEmpty(hint)) {
            StringUtils.HintUtil(etAddress, hint);
        }
        if (strTitle == "考勤查询" || strTitle == "前端点超时" || strTitle == "后端点超时") {
            etAddress.setInputType(InputType.TYPE_CLASS_NUMBER);
            if (strTitle == "前端点超时") {
                if ("请输入".equals(hint)) {
                    etAddress.setText("5000");
                }
            } else if (strTitle == "后端点超时") {
                if ("请输入".equals(hint)) {
                    etAddress.setText("1800");
                }
            }
            etAddress.addTextChangedListener(new SettingTextWatcher(activity, etAddress, 0, 10000));
        }
    }

    @OnClick({R.id.no, R.id.yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.no:
                hideSoft(no);
                dismiss();
                break;
            case R.id.yes:
                hideSoft(yes);
                String message = etAddress.getText().toString();
                EventBean bean = new EventBean(message, strTitle);
                Event<EventBean> event = new Event<EventBean>(FinalClass.A, bean);
                EventBusUtil.sendEvent(event);
                dismiss();
                break;
        }
    }

    public void hideSoft(View view) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

