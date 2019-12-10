package com.example.lxx.myalipay.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.parkingwang.keyboard.KeyboardInputController;
import com.parkingwang.keyboard.OnInputChangedListener;
import com.parkingwang.keyboard.PopupKeyboard;
import com.parkingwang.keyboard.view.InputView;


/**
 * created by lxx at 2019/11/25 14:04
 * 描述:车牌号输入
 */
public class CarNoInputDialog extends DialogFragment {

    public static CarNoInputDialog newInstance() {
        CarNoInputDialog fragment = new CarNoInputDialog();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_car_no_input, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final InputView mInputView = view.findViewById(R.id.input_view);
        final Button lockTypeButton = view.findViewById(R.id.lock_type);
        final CheckBox verifyCheckButton = view.findViewById(R.id.verify_checkbutton);

        // 创建弹出键盘
        final PopupKeyboard mPopupKeyboard = new PopupKeyboard(getContext());
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(mInputView, getDialog());

        // 隐藏确定按钮
        mPopupKeyboard.getKeyboardEngine().setHideOKKey(false);

        verifyCheckButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPopupKeyboard.getController().setSwitchVerify(isChecked);
            }
        });


        // KeyboardInputController提供一个默认实现的新能源车牌锁定按钮
        mPopupKeyboard.getController()
                .setDebugEnabled(true)
                .setSwitchVerify(verifyCheckButton.isChecked())
                .bindLockTypeProxy(new KeyboardInputController.ButtonProxyImpl(lockTypeButton) {
                    @Override
                    public void onNumberTypeChanged(boolean isNewEnergyType) {
                        super.onNumberTypeChanged(isNewEnergyType);
                        if (isNewEnergyType) {
                            lockTypeButton.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                        } else {
                            lockTypeButton.setTextColor(getResources().getColor(android.R.color.black));
                        }
                    }
                });
        mPopupKeyboard.getController().addOnInputChangedListener(new OnInputChangedListener() {
            @Override
            public void onChanged(String number, boolean isCompleted) {
                if (isCompleted) {
                    mPopupKeyboard.dismiss(getDialog().getWindow());
                }
            }

            @Override
            public void onCompleted(String number, boolean isAutoCompleted) {
                mPopupKeyboard.dismiss(getDialog().getWindow());
            }
        });


        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatteLogger.d("myNumber", mInputView.getNumber());
                onCarNoListener.setCarNo(mInputView.getNumber());
                dismiss();
            }
        });

    }

    public void show(FragmentManager manager) {
        super.show(manager, getTag());
    }

    private OnCarNoListener onCarNoListener;

    public void setListener(OnCarNoListener listener) {
        onCarNoListener = listener;
    }

    public interface OnCarNoListener {
        void setCarNo(String carNo);
    }
}

