package com.example.lxx.myalipay.ui.login.forget_pass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.font.FontTextView4;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.lxx.myalipay.api.FinalClass.UserName;

/**
 * created by lxx at 2019/12/6 12:29
 * 描述:
 */
public class ForgetPassword extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.font_icon_right)
    FontTextView4 fontIconRight;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.tv_account)
    EditText tvAccount;
    @BindView(R.id.ll_tab_account)
    LinearLayout llTabAccount;
    @BindView(R.id.ll_success_submit)
    LinearLayout llSuccessSubmit;
    @BindView(R.id.apply_normal)
    Button applyNormal;
    @BindView(R.id.apply_succeed)
    Button applySucceed;
    @BindView(R.id.apply_relative)
    RelativeLayout applyRelative;

    private String userAccount="";
    private SharedPreferences sp;
    public static ForgetPassword newInstance() {
        ForgetPassword fragment = new ForgetPassword();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_find_pass;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("找回密码");
        applySucceed.setVisibility(View.VISIBLE);
        applyNormal.setVisibility(View.GONE);
        applySucceed.setText("提交帐号");
        sp = _mActivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userAccount = sp.getString("USER_CODE2", "");
        tvAccount.setText(userAccount);
    }

    @OnClick({R.id.apply_succeed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apply_succeed:
                LatteLogger.d("dddddddddddd",userAccount);
                if (userAccount.equals("HonpeErp_"+tvAccount.getText().toString())) {
                    if (userAccount.contains("HonpeErp_")) {
                        userAccount = userAccount+tvAccount.getText().toString();
                    }else {
                        userAccount = tvAccount.getText().toString();
                    }
                    LatteLogger.d("ddd",userAccount);
                    getJsonData(userAccount);
                }
                break;
        }
    }

    private void getJsonData(String account) {
        LatteLogger.d("account",account);
        EasyHttp.post(Constants.FIND_PASS)
                .params("usercode", account)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("dddddddddddd",result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getInt("Status") == 0) {
                                ToastUtils.getInstance().showToast(jsonObject.getInt("Msg"));
                            }else {
                                ToastUtils.getInstance().showToast(jsonObject.getInt("Msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        _mActivity.onBackPressed();

                    }
                });
    }
}









