package com.example.lxx.myalipay.widget.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.flyco.dialog.widget.base.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by lxx at 2019/11/29 11:04
 * 描述:客户点餐
 */
public class CustomBaseDialog extends BaseDialog<CustomBaseDialog> {

    @BindView(R.id.tv_Time)
    TextView tvTime;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_exit)
    TextView tvExit;

    private String[] titlesAM = {"11:00-11:30", "11:30-12:00", "12:00-12:30", "12:30-13:00"};
    private String[] titlesPM = {"17:00-17:30", "17:30-18:00", "18:00-18:30", "18:30-19:00"};
    private String[] titles2 = {"1", "2", "3", "4", "5", "6"};
    private Context context;
    private NormalListDialog dialog1, dialog2, dialog3;
    public OnDialogButtonClickListener onClickListener;
    private String type;

    public void setOnButtonClickListener(OnDialogButtonClickListener listener) {
        this.onClickListener = listener;
    }

    public CustomBaseDialog(Context context, String type) {
        super(context);
        this.context = context;
        this.type = type;
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        View inflate = View.inflate(mContext, R.layout.dialog_custom_base, null);
        ButterKnife.bind(this, inflate);
        tvExit.setText("提交");

        StringUtils.HintUtil(tvTime, "预约时间区间");
        StringUtils.HintUtil(tvNum, "预约人数");
        dialog1 = new NormalListDialog(context, titlesAM);
        dialog2 = new NormalListDialog(context, titlesPM);
        dialog3 = new NormalListDialog(context, titles2);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTime.setText("");
                tvNum.setText("");
                dismiss();
            }
        });
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvTime.getText().toString()) || TextUtils.isEmpty(tvNum.getText().toString())) {
                    ToastUtils.getInstance().showToast("数据不能为空哦~~");
                } else {
                    onClickListener.ButtonClick(tvTime.getText().toString(), tvNum.getText().toString());
                }
                dismiss();
            }
        });
    }


    @OnClick({R.id.ll_time, R.id.ll_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time:
                if (type.equals("午餐")) {
                    dialog1.title("请选择时间区间")
                            .layoutAnimation(null)
                            .show();
                    dialog1.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tvTime.setText(titlesAM[position]);
                            dialog1.dismiss();
                        }
                    });
                } else {
                    dialog2.title("请选择时间区间")
                            .layoutAnimation(null)
                            .show();
                    dialog2.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tvTime.setText(titlesPM[position]);
                            dialog2.dismiss();
                        }
                    });
                }
                break;
            case R.id.ll_num:
                if (type.equals("午餐")) {
                    dialog3.title("请选择人数")
                            .layoutAnimation(null)
                            .show();
                    dialog3.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tvNum.setText(titles2[position]);
                            dialog3.dismiss();
                        }
                    });
                } else {
                    dialog3.title("请选择人数")
                            .layoutAnimation(null)
                            .show();
                    dialog3.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tvNum.setText(titles2[position]);
                            dialog3.dismiss();
                        }
                    });
                }
                break;
        }
    }

    public interface OnDialogButtonClickListener {
        public void ButtonClick(String time, String num);
    }
}

