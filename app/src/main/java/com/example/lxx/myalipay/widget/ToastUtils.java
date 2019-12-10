package com.example.lxx.myalipay.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;


/**
 * created by lxx at 2019/11/12 10:25
 * 描述:自定义Toast
 */
public class ToastUtils {
    private volatile static ToastUtils newInstance = null;

    private ToastUtils() {
    }

    private Toast toast;
    private TextView mTvToast;

    public static ToastUtils getInstance() {
        if (newInstance == null) {
            synchronized (ToastUtils.class) {
                if (newInstance == null) {
                    newInstance = new ToastUtils();
                }
            }
        }
        return newInstance;
    }

    public void showToast(String text) {
        if (toast == null) {
            toast = new Toast(MyApplication.getContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            View root = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.layout_custom_toast, null);
            mTvToast = root.findViewById(R.id.toast_text);
            mTvToast.setText(text);
            toast.setView(root);
        }
        mTvToast.setText(text);
        toast.show();
    }

    public void showToast(int stringId){
        showToast(stringId+"");
    }
}

