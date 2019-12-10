package com.example.lxx.myalipay.ui.login.register;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.Utils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.font.FontTextView;
import com.example.lxx.myalipay.widget.font.FontTextView4;
import com.google.gson.JsonObject;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * created by lxx at 2019/12/6 10:32
 * 描述:
 */
public class RegisterFragment extends BaseBackFragment implements TextWatcher {

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
    @BindView(R.id.et_count)
    EditText etCount;
    @BindView(R.id.icon_font_delete1)
    FontTextView iconFontDelete1;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.icon_font_delete2)
    FontTextView iconFontDelete2;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.icon_font_delete3)
    FontTextView iconFontDelete3;
    @BindView(R.id.et_passagain)
    EditText etPassagain;
    @BindView(R.id.icon_font_delete4)
    FontTextView iconFontDelete4;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.icon_font_delete5)
    FontTextView iconFontDelete5;
    @BindView(R.id.et_youxian)
    EditText etYouxian;
    @BindView(R.id.icon_font_delete6)
    FontTextView iconFontDelete6;
    @BindView(R.id.et_comply)
    EditText etComply;
    @BindView(R.id.icon_font_delete7)
    FontTextView iconFontDelete7;
    @BindView(R.id.et_area)
    EditText etArea;
    @BindView(R.id.icon_font_delete8)
    FontTextView iconFontDelete8;
    @BindView(R.id.radio_zh)
    RadioButton radioZh;
    @BindView(R.id.radio_jp)
    RadioButton radioJp;
    @BindView(R.id.radio_else)
    RadioButton radioElse;
    @BindView(R.id.country_group)
    RadioGroup countryGroup;
    @BindView(R.id.ll14)
    LinearLayout ll14;
    @BindView(R.id.radio_male)
    RadioButton radioMale;
    @BindView(R.id.radio_female)
    RadioButton radioFemale;
    @BindView(R.id.radio_female2)
    RadioButton radioFemale2;
    @BindView(R.id.sex)
    RadioGroup sex;
    @BindView(R.id.ll15)
    LinearLayout ll15;
    @BindView(R.id.apply_normal)
    Button applyNormal;
    @BindView(R.id.apply_succeed)
    Button applySucceed;
    @BindView(R.id.apply_relative)
    RelativeLayout applyRelative;
    @BindView(R.id.scr)
    ScrollView scr;
    @BindView(R.id.register_progress)
    LinearLayout registerProgress;

    private boolean usernameCursor = true;// 判读用户名输入框是失去光标还是获得光标
    private boolean repasswordCursor = true;// 判读重复密码输入框是失去光标还是获得光标

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        _mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tvTitle.setText("注册");
        initToolbarNav(llBack);
        initListener();
        Utils.setEditTextHint(etCount, 16, "用户帐号");
        Utils.setEditTextHint(etName, 16, "昵称/用户名");
        Utils.setEditTextHint(etPass, 16, "密码");
        Utils.setEditTextHint(etPassagain, 16, "密码确认");
        Utils.setEditTextHint(etPhone, 16, "电话");
        Utils.setEditTextHint(etYouxian, 16, "邮箱");
        Utils.setEditTextHint(etComply, 16, "公司名");
        Utils.setEditTextHint(etArea, 16, "所在区域");

        etCount.addTextChangedListener(this);
        etName.addTextChangedListener(this);
        etPass.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
        etYouxian.addTextChangedListener(this);
        etComply.addTextChangedListener(this);
        etArea.addTextChangedListener(this);

        country = radioZh.getText().toString();
        countryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_zh:
                        country = radioZh.getText().toString();
                        break;
                    case R.id.radio_jp:
                        country = radioJp.getText().toString();
                        break;
                    case R.id.radio_else:
                        country = radioElse.getText().toString();
                        break;
                }
                LatteLogger.d("select===", country);
            }
        });
      sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
              switch (checkedId) {
                  case R.id.radio_male:
                      strSex = radioMale.getText().toString();
                      break;
                  case R.id.radio_female:
                      strSex = radioFemale.getText().toString();
                  break;
              }
          }
      });
    }

    String userCoder, userName, passWord, rePassword, Telephone, Email, Area, complay,country,strSex;
    @OnClick({R.id.apply_succeed,R.id.icon_font_delete1, R.id.icon_font_delete2, R.id.icon_font_delete3, R.id.icon_font_delete4, R.id.icon_font_delete5, R.id.icon_font_delete6, R.id.icon_font_delete7, R.id.icon_font_delete8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_font_delete1:
                iconFontDelete1.setVisibility(View.GONE);
                etCount.setText("");
                break;
            case R.id.icon_font_delete2:
                iconFontDelete2.setVisibility(View.GONE);
                etName.setText("");
                break;
            case R.id.icon_font_delete3:
                iconFontDelete3.setVisibility(View.GONE);
                etPass.setText("");
                break;
            case R.id.icon_font_delete4:
                iconFontDelete4.setVisibility(View.GONE);
                etPassagain.setText("");
                break;
            case R.id.icon_font_delete5:
                iconFontDelete5.setVisibility(View.GONE);
                etPhone.setText("");
                break;
            case R.id.icon_font_delete6:
                iconFontDelete6.setVisibility(View.GONE);
                etYouxian.setText("");
                break;
            case R.id.icon_font_delete7:
                iconFontDelete7.setVisibility(View.GONE);
                etComply.setText("");
                break;
            case R.id.icon_font_delete8:
                iconFontDelete8.setVisibility(View.GONE);
                etArea.setText("");
                break;
            case R.id.apply_succeed:
                userCoder = etCount.getText().toString();
                userName = etName.getText().toString();
                passWord = etPass.getText().toString();
                rePassword = etPassagain.getText().toString();
                Telephone = etPhone.getText().toString();
                Email = etYouxian.getText().toString();
                Area = etArea.getText().toString();
                complay = etComply.getText().toString();

                if (!TextUtils.isEmpty(userCoder)
                        && !TextUtils.isEmpty(userName)
                        && !TextUtils.isEmpty(passWord)
                        && !TextUtils.isEmpty(rePassword)
                        && !TextUtils.isEmpty(Email)
                        && !TextUtils.isEmpty(Telephone)
                        && !TextUtils.isEmpty(Area)) {
                    if (passWord.equals(rePassword)) {
                        if (StringUtils.isEmail(Email)) {
                            DealData(userCoder, userName, passWord, rePassword, Telephone, Email, Area, complay);
                        } else {
                            ToastUtils.getInstance().showToast("输入邮箱有误！");
                        }
                    } else {
                        ToastUtils.getInstance().showToast("二次密码输入不一致，请重新输入！");
                    }
                } else {
                    ToastUtils.getInstance().showToast("请填写完整信息！");
                }
                break;
        }
    }

    private void DealData(final String userCoder, final String userName, final String passWord,
                          String rePassword, String phone, String email, String area, String complay) {
        String language = null;
        if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
            language = "en";
        } else if (getResources().getConfiguration().locale.getCountry().equals("US")) {
            language = "US";
        } else {
            language = "jp";
        }

        //三个国家我只选一个返回
      //  RadioButton selecteCountry = (RadioButton) findViewById(countryGroup.getCheckedRadioButtonId());
        LatteLogger.d("select===", country);
        EasyHttp.post(Constants.REGISTERINFO)
                .params("usercode", userCoder)
                .params("username", userName)
                .params("password", passWord)
                .params("confirmpassword", rePassword)
                .params("telephoneno", phone)
                .params("language", language)
                .params("country", country)
                .params("sex",strSex)
                .params("area", area)
                .params("email", email)
                .params("companyname", complay)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("ddddddddw",result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getInt("Status") == 0) {
                                ToastUtils.getInstance().showToast(jsonObject.getString("Msg"));
                                _mActivity.onBackPressed();
                            }else {
                                ToastUtils.getInstance().showToast(jsonObject.getString("Msg"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initListener() {
        etCount.setOnFocusChangeListener(new CheckUsernameListener());
        etPassagain.setOnFocusChangeListener(new RePasswordListener());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        userCoder = etCount.getText().toString();
        userName = etName.getText().toString();
        passWord = etPass.getText().toString();
        rePassword = etPassagain.getText().toString();
        Telephone = etPhone.getText().toString();
        Email = etYouxian.getText().toString();
        Area = etArea.getText().toString();
        complay = etComply.getText().toString();
        if (s.length() > 0) {
            if (!TextUtils.isEmpty(userCoder)) {
                iconFontDelete1.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.A, userCoder);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(userName)) {
                iconFontDelete2.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.B, userName);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(passWord)) {
                iconFontDelete3.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.C, passWord);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(rePassword)) {
                iconFontDelete4.setVisibility(View.VISIBLE);

            }
            if (!TextUtils.isEmpty(Telephone)) {
                iconFontDelete5.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.D, Telephone);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(Email)) {
                iconFontDelete6.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.E, Email);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(Area)) {
                iconFontDelete8.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.F, Area);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(complay)) {
                iconFontDelete7.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.G, complay);
                EventBusUtil.sendEvent(event);
            }

        } else {
            if (!TextUtils.isEmpty(userCoder)) {
                iconFontDelete1.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.A, userCoder);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(userName)) {
                iconFontDelete2.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.B, userName);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(passWord)) {
                iconFontDelete3.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.C, passWord);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(rePassword)) {
                iconFontDelete4.setVisibility(View.GONE);

            }
            if (!TextUtils.isEmpty(Telephone)) {
                iconFontDelete5.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.D, Telephone);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(Email)) {
                iconFontDelete6.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.E, Email);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(Area)) {
                iconFontDelete8.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.F, Area);
                EventBusUtil.sendEvent(event);
            }
            if (!TextUtils.isEmpty(complay)) {
                iconFontDelete7.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.G, complay);
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
                userCoder = (String) event.getData();
                break;
            case FinalClass.B:
                userName = (String) event.getData();
                break;
            case FinalClass.C:
                passWord = (String) event.getData();
                break;
            case FinalClass.D:
                Telephone = (String) event.getData();
                break;
            case FinalClass.E:
                Email = (String) event.getData();
                break;
            case FinalClass.F:
                Area = (String) event.getData();
                break;
            case FinalClass.G:
                complay = (String) event.getData();
                break;
        }
        LatteLogger.d("dddddddd", userCoder + "   " + userName + "   " +
                passWord + "   " + Telephone + "   " + Email + "   " + Area + "   " + complay);
        if ("".equals(userCoder) || "".equals(userName) || "".equals(passWord) || "".equals(Telephone)
                || "".equals(Email) || "".equals(Area) || "".equals(complay) && userCoder == null || userName == null || passWord == null || Telephone == null
                || Email == null || Area == null || complay == null) {
            applyNormal.setVisibility(View.VISIBLE);
            applySucceed.setVisibility(View.GONE);
        } else {
            applyNormal.setVisibility(View.GONE);
            applySucceed.setVisibility(View.VISIBLE);
        }

    }


    /**
     * CheckUsernameListener
     *
     * @author renzhongfeng
     * 当输入完用户名，输入框失去光标后,�?��该用户名在数据库中是否存�?
     */
    private class CheckUsernameListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            // TODO Auto-generated method stub
            String myUsername = etCount.getText().toString();
            if (!usernameCursor) {
                if (isUsernameExisted(myUsername)) {
                    ToastUtils.getInstance().showToast( "该用户名已经存在，请更改用户名");
                }
            }
        }

        /**
         * 用于检测用户输入的用户名是否已经注册
         * 将用户输入的用户名传动到服务器，在用户数据库中查找该用户名，若能够查找到则返回true，否则返回false
         *
         * @param username 用户输入的用户名
         * @return 标记该用户名是否已经存在，存在为true，不存在为false
         */
        private boolean isUsernameExisted(String username) {

            boolean flag = false;
            return flag;
        }
    }

    /**
     * RePasswordListener
     *
     * @author renzhongfeng
     * 重复输入密码失去光标的监听类 2014/07/27
     */
    private class RePasswordListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (repasswordCursor = !repasswordCursor) {
                if (!checkPassword(etPass.getText().toString(), etPassagain.getText().toString())) {
                    etPassagain.setText("");
                    //rePassword.requestFocus();
                    ToastUtils.getInstance().showToast("两次密码不一样，请重新输入");
                }
            }
            if (!StringUtils.isPass(etPass.getText().toString())) {
                etPass.setText("");
                ToastUtils.getInstance().showToast("密码为6-18位数字和字母组合");
            }
        }

        /**
         * 比较两次输入的密码是否一致
         * rePassword输入完成后，光标改变监听，和password进行比较，如果不一致，会有提示，并且两次密码密码清空
         *
         * @param psw1 密码框中输入的密码
         * @param psw2 重复密码框中输入的密码
         * @return 两次密码一致返回true，否则返回false
         */
        public boolean checkPassword(String psw1, String psw2) {
            if (psw1.equals(psw2))
                return true;
            else
                return false;
        }
    }
}
























