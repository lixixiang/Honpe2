package com.example.lxx.myalipay.ui.fragment.b_package.address.add;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.address.bean.AddressBean;
import com.example.lxx.myalipay.ui.fragment.b_package.entity.ReceiverAddressBean;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.widget.AreaPickerView;
import com.example.lxx.myalipay.widget.font.FontTextView;
import com.example.lxx.myalipay.widget.switchButton.SwitchButton;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 添加新地址
 */

public class AddAddressFragment extends BaseBackFragment implements TextWatcher {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_end)
    TextView tvFinish;
    @BindView(R.id.et_consignee)
    EditText etConsignee;
    @BindView(R.id.et_consignee_phone)
    EditText etConsigneePhone;
    @BindView(R.id.re_area)
    RelativeLayout reArea;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.cb_switch_button)
    SwitchButton cbSwitchButton;
    @BindView(R.id.tv_area_tips)
    TextView tvAreaTips;
    @BindView(R.id.ic_delete_consignee)
    ImageView icDeleteConsignee;
    @BindView(R.id.ic_delete_consignee_phone)
    ImageView icDeleteConsigneePhone;
    @BindView(R.id.ic_delete_address_content)
    ImageView icDeleteAddressContent;
    @BindView(R.id.font_talk)
    FontTextView fontTalk;

    private String strConsignee, strPhone, strArea, strAddress;
    private AreaPickerView areaPickerView;
    private List<AddressBean> addressBeans;
    private ReceiverAddressBean bean;
    private boolean isClickAble = false;
    private int position = -1;


    public static AddAddressFragment newInstance(ReceiverAddressBean bean, int position) {
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.bean = bean;
        fragment.position = position;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_add_address;
    }

    @Override
    protected void initView() {
        initToolbarNav(homeBack);
        title.setText("新增收货地址");
        tvFinish.setVisibility(View.VISIBLE);
        tvFinish.setText("保存");

        if (bean != null) { //编缉
            LatteLogger.d("GsonBuildUtil", GsonBuildUtil.GsonBuilder(bean));
            strConsignee = bean.getUserName();
            strPhone = bean.getPhone();
            strArea = bean.getArea();
            strAddress = bean.getAddress();
            etConsignee.setText(bean.getUserName());
            etConsigneePhone.setText(bean.getPhone());
            tvAreaTips.setText(bean.getArea());
            etAddress.setText(bean.getAddress());
            isClickAble = bean.isClick();
            cbSwitchButton.setChecked(isClickAble);
        }

        hint();
        setPickerView();
        setSwitchButton();
        etConsignee.addTextChangedListener(this);
        etConsigneePhone.addTextChangedListener(this);
        etAddress.addTextChangedListener(this);
        tvAreaTips.addTextChangedListener(this);
    }

    private void setSwitchButton() {
        cbSwitchButton.setChecked(isClickAble);
        cbSwitchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isClickAble = isChecked;
            }
        });
    }

    private void hint() {
        StringUtils.HintUtil(etConsignee, "请填写收货人姓名");
        StringUtils.HintUtil(etConsigneePhone, "请填写收货人手机号");
        StringUtils.HintUtil(tvAreaTips, "省、市、区");
        StringUtils.HintUtil(etAddress, "如道路、门牌号、小区、楼栋号、单元室等");
    }

    int[] i;

    private void setPickerView() {
        Gson gson = new Gson();
        addressBeans = gson.fromJson(getCityJson(), new TypeToken<List<AddressBean>>() {
        }.getType());
        areaPickerView = new AreaPickerView(_mActivity, R.style.Dialog, addressBeans);
        areaPickerView.setAreaPickerViewCallback(new AreaPickerView.AreaPickerViewCallback() {
            @Override
            public void callback(int... value) {
                i = value;
                if (value.length == 3)
                    tvAreaTips.setText(addressBeans.get(value[0]).getLabel() + "省" + addressBeans.get(value[0]).getChildren().get(value[1]).getLabel() + "市" + addressBeans.get(value[0]).getChildren().get(value[1]).getChildren().get(value[2]).getLabel());
                else
                    tvAreaTips.setText(addressBeans.get(value[0]).getLabel() + "省" + addressBeans.get(value[0]).getChildren().get(value[1]).getLabel());
            }
        });
    }

    private String getCityJson() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            AssetManager assetManager = _mActivity.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("region.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        strConsignee = etConsignee.getText().toString();
        strPhone = etConsigneePhone.getText().toString();
        strArea = tvAreaTips.getText().toString();
        strAddress = etAddress.getText().toString();
        if (strConsignee.length() > 0) {
            icDeleteConsignee.setVisibility(View.VISIBLE);
        } else {
            icDeleteConsignee.setVisibility(View.INVISIBLE);
        }
        if (strPhone.length() > 0) {
            icDeleteConsigneePhone.setVisibility(View.VISIBLE);
        } else {
            icDeleteConsigneePhone.setVisibility(View.INVISIBLE);
        }
        if (strAddress.length() > 0) {
            icDeleteAddressContent.setVisibility(View.VISIBLE);
        } else {
            icDeleteAddressContent.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.Address:
                ReceiverAddressBean bean = (ReceiverAddressBean) event.getData();
                LatteLogger.d("GsonBuildUtil", GsonBuildUtil.GsonBuilder(bean));
                final NormalDialog dialog = new NormalDialog(_mActivity);
                if ("".equals(bean.getUserName()) || TextUtils.isEmpty(bean.getUserName())) {
                    dialog.content("收货人姓名长度需要在2-25个字符之间")
                            .contentTextColor(Color.BLACK)
                            .contentTextSize(14)
                            .isTitleShow(false)
                            .btnNum(1)
                            .btnText("好的")
                            .btnTextSize(14)
                            .btnTextColor(_mActivity.getResources().getColor(R.color.style_red))
                            .contentGravity(Gravity.CENTER)
                            .showAnim(null)
                            .show();
                } else if ("".equals(bean.getPhone()) || TextUtils.isEmpty(bean.getPhone())) {
                    // final NormalDialog dialog1 = new NormalDialog(_mActivity);
                    dialog.content("请填写正确的手机号码")
                            .contentTextColor(Color.BLACK)
                            .contentTextSize(14)
                            .btnTextSize(14)
                            .isTitleShow(false)
                            .btnNum(1)
                            .btnText("好的")
                            .btnTextColor(_mActivity.getResources().getColor(R.color.style_red))
                            .contentGravity(Gravity.CENTER)
                            .showAnim(null)
                            .show();
                    dialog.setOnBtnClickL(new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    });
                } else if ("".equals(bean.getArea())) {
                    dialog.content("所在区域一定要填哦~~")
                            .contentTextColor(Color.BLACK)
                            .contentTextSize(14)
                            .btnTextSize(14)
                            .isTitleShow(false)
                            .btnNum(1)
                            .btnText("好的")
                            .btnTextColor(_mActivity.getResources().getColor(R.color.style_red))
                            .contentGravity(Gravity.CENTER)
                            .showAnim(null)
                            .show();
                    dialog.setOnBtnClickL(new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    });
                } else if ("".equals(bean.getAddress())) {
                    dialog.content("详细地址不填是无法收到货物的~~")
                            .contentTextColor(Color.BLACK)
                            .contentTextSize(14)
                            .btnTextSize(14)
                            .isTitleShow(false)
                            .btnNum(1)
                            .btnText("好的")
                            .btnTextColor(_mActivity.getResources().getColor(R.color.style_red))
                            .contentGravity(Gravity.CENTER)
                            .showAnim(null)
                            .show();
                    dialog.setOnBtnClickL(new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    });
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("address", bean);
                    setFragmentResult(RESULT_OK, bundle);
                    _mActivity.onBackPressed();
                    hideSoftKeyBoard();
                }
                break;
        }
    }

    int ret = 0; // 函数调用返回值

    @OnClick({R.id.tv_end, R.id.re_area, R.id.ic_delete_consignee, R.id.ic_delete_consignee_phone, R.id.ic_delete_address_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                ReceiverAddressBean bean = new ReceiverAddressBean(strConsignee, strPhone, strArea, strAddress, isClickAble, position);
                Event<ReceiverAddressBean> event = new Event<ReceiverAddressBean>(FinalClass.Address, bean);
                EventBusUtil.sendEvent(event);
                break;
            case R.id.re_area:
                areaPickerView.setSelect(i);
                areaPickerView.show();
                break;
            case R.id.ic_delete_consignee:
                etConsignee.setText("");
                icDeleteConsignee.setVisibility(View.GONE);
                break;
            case R.id.ic_delete_consignee_phone:
                etConsigneePhone.setText("");
                icDeleteConsigneePhone.setVisibility(View.GONE);
                break;
            case R.id.ic_delete_address_content:
                etAddress.setText("");
                icDeleteAddressContent.setVisibility(View.GONE);
                break;
        }
    }
}







