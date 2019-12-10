package com.example.lxx.myalipay.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.flyco.dialog.widget.base.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by lxx at 2019/12/4 10:49
 * 描述:首页更新
 */
public class UpdateTipsDialog extends BaseDialog<UpdateTipsDialog> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private String strTips,content,leftBtn,rightBtn;
    private onSureUpdate onSureUpdate;

    public void setOnSureUpdate(onSureUpdate listener){
        this.onSureUpdate = listener;
    }

    public UpdateTipsDialog(Context context, String strTips, String content, String leftBtn, String rightBtn) {
        super(context);
        this.strTips = strTips;
        this.content = content;
        this.leftBtn = leftBtn;
        this.rightBtn = rightBtn;
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        View inflate = View.inflate(mContext, R.layout.update_tips_dialog, null);
        ButterKnife.bind(this,inflate);
        tvTitle.setText(strTips);
        tvContent.setText(content);
        tvSure.setText(leftBtn);
        tvCancel.setText(rightBtn);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSureUpdate.onClick();
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface onSureUpdate{
        void onClick();
    }
}






