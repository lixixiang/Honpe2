package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.mob;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.lxx.myalipay.widget.font.FontTextView2;


/**
 * created by lxx at 2019/11/28 10:22
 * 描述:修改消息工具类
 */
public class GetMessageUtil {
    public void editChanged(EditText view, final FontTextView2 iconView) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    iconView.setVisibility(View.VISIBLE);
                } else {
                    iconView.setVisibility(View.GONE);
                }
            }
        });

    }
}
