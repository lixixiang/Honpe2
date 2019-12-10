package com.example.lxx.myalipay.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * created by lxx at 2019/12/3 10:43
 * 描述:输入框输入范围控制
 */
public class SettingTextWatcher implements TextWatcher {
    private int editStart ;
    private int editCount ;
    private EditText mEditTextPreference;
    int minValue;//最小值
    int maxValue;//最大值
    private Context mContext;

    public SettingTextWatcher(Context context,EditText e,int min, int max) {
        mContext = context;
        mEditTextPreference = e;
        minValue = min;
        maxValue = max;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//		Log.e("demo", "onTextChanged start:"+start+" count:"+count+" before:"+before);
        editStart = start;
        editCount = count;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,int after) {
//		Log.e("demo", "beforeTextChanged start:"+start+" count:"+count+" after:"+after);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        String content = s.toString();
//		Log.e("demo", "content:"+content);
        if (isNumeric(content)) {
            int num = Integer.parseInt(content);
            if (num > maxValue || num < minValue) {
                s.delete(editStart, editStart+editCount);
                mEditTextPreference.setText(s);
                Toast.makeText(mContext, "超出有效值范围", Toast.LENGTH_SHORT).show();
            }
        }else {
            s.delete(editStart, editStart+editCount);
            mEditTextPreference.setText(s);
            Toast.makeText(mContext, "只能输入数字哦", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 正则表达式-判断是否为数字
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

};

