package com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.add_order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.widget.font.FontTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class RepairApplyActivity extends BaseActivity implements TextWatcher, RadioGroup.OnCheckedChangeListener {


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
    @BindView(R.id.radioButton_character_1)
    RadioButton radioButtonCharacter1;
    @BindView(R.id.radioButton_character_2)
    RadioButton radioButtonCharacter2;
    @BindView(R.id.radio_character)
    RadioGroup radioCharacter;
    @BindView(R.id.view1)
    TextView view1;
    @BindView(R.id.radioButton_type_1)
    RadioButton radioButtonType1;
    @BindView(R.id.radioButton_type_2)
    RadioButton radioButtonType2;
    @BindView(R.id.radio_type)
    RadioGroup radioType;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.edit_item)
    EditText editItem;
    @BindView(R.id.icon_to_delete)
    FontTextView iconToDelete;
    @BindView(R.id.view3)
    TextView view3;
    @BindView(R.id.edit_point)
    EditText editPoint;
    @BindView(R.id.icon_phone_delete)
    FontTextView iconPhoneDelete;
    @BindView(R.id.view4)
    FontTextView view4;
    @BindView(R.id.edit_times)
    EditText editTimes;
    @BindView(R.id.icon_num_people_delete)
    FontTextView iconNumPeopleDelete;
    @BindView(R.id.view9)
    FontTextView view9;
    @BindView(R.id.edit_cause)
    EditText editCause;
    @BindView(R.id.icon_message_delete)
    FontTextView iconMessageDelete;
    @BindView(R.id.apply_normal)
    Button applyNormal;
    @BindView(R.id.apply_succeed)
    Button applySucceed;
    @BindView(R.id.apply_relative)
    RelativeLayout applyRelative;
    @BindView(R.id.manage_cardView)
    LinearLayout manageCardView;
    @BindView(R.id.svr)
    ScrollView svr;

    RadioButton character, type;
    //将选中的值记录下来
    Message message;
    Bundle bundle = new Bundle();
    String characters, types, items, points, times, cause;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            bundle = msg.getData();
            characters = bundle.getString("character");
            types = bundle.getString("type");
            items = bundle.getString("editItem");
            points = bundle.getString("editPoint");
            times = bundle.getString("editTimes");
            cause = bundle.getString("editCause");
            // ToastUtil.getShortToast(_mActivity,"item===="+item+"==point===="+point+"===times==="+times+"===finishTime==="+finishTime+"===cause==="+cause);
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_repair_apply;
    }

    @Override
    public void initView() {
       initToolbarNav(llBack);
       tvTitle.setText("维修申请单");
        radioCharacter.setOnCheckedChangeListener(this);
        radioType.setOnCheckedChangeListener(this);

        character = findViewById(radioCharacter.getCheckedRadioButtonId());
        type = findViewById(radioType.getCheckedRadioButtonId());

        message = handler.obtainMessage();
        bundle.putString("character", character.getText().toString());
        bundle.putString("type", type.getText().toString());
        message.setData(bundle);
        handler.sendMessage(message);

        editItem.addTextChangedListener(this);
        editPoint.addTextChangedListener(this);
        editTimes.addTextChangedListener(this);
        editCause.addTextChangedListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        character = findViewById(radioCharacter.getCheckedRadioButtonId());
        type = findViewById(radioType.getCheckedRadioButtonId());
        message = handler.obtainMessage();
        bundle.putString("character", character.getText().toString());
        bundle.putString("type", type.getText().toString());
        message.setData(bundle);
        handler.sendMessage(message);
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

            if (!TextUtils.isEmpty(editItem.getText().toString())
                    && !TextUtils.isEmpty(editPoint.getText().toString())
                    && !TextUtils.isEmpty(editTimes.getText().toString())
                    && !TextUtils.isEmpty(editCause.getText().toString())) {

                message = handler.obtainMessage();
                bundle.putString("editItem", editItem.getText().toString());
                bundle.putString("editPoint", editPoint.getText().toString());
                bundle.putString("editTimes", editTimes.getText().toString());
                bundle.putString("editCause", editCause.getText().toString());
                message.setData(bundle);
                handler.sendMessage(message);

                applyNormal.setVisibility(View.GONE);
                applySucceed.setVisibility(View.VISIBLE);
            } else {
                applyNormal.setVisibility(View.VISIBLE);
                applySucceed.setVisibility(View.GONE);
            }

        } else {
            applyNormal.setVisibility(View.VISIBLE);
            applySucceed.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.apply_succeed)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.putExtra("character",characters);
        intent.putExtra("type", types);
        intent.putExtra("editItem",items);
        intent.putExtra("editPoint",points);
        intent.putExtra("editTimes",times);
        intent.putExtra("editCause",cause);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
