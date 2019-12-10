package com.example.lxx.myalipay.widget.edit;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * created by lxx at 2019/12/1 18:52
 * 描述:更新说明
 */
public class CursorEditText extends AppCompatEditText {
    private CharSequence mHint;

    private Paint mHintPaint;
    private int mCurHintTextColor;

    public CursorEditText(Context context) {
        this(context, null);
    }

    public CursorEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle);
    }

    public CursorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mHint = getHint();
        setHint("");
        mHintPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mHintPaint.setTextSize(getTextSize());
        mHintPaint.setTextAlign(Paint.Align.RIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(mHint) || !TextUtils.isEmpty(getText())) {
            return;
        }
        canvas.save();
        ColorStateList hintTextColors = getHintTextColors();
        if (hintTextColors != null) {
            int color = hintTextColors.getColorForState(getDrawableState(), 0);
            if (color != mCurHintTextColor) {
                mCurHintTextColor = color;
                mHintPaint.setColor(color);
            }
        }

        Paint.FontMetricsInt fontMetrics = mHintPaint.getFontMetricsInt();
        int baseline = (getHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(mHint, 0, mHint.length(),
                getWidth() - getPaddingRight() + getScrollX(),
                baseline, mHintPaint);
        canvas.restore();
    }

//    @Override
//    protected void onSelectionChanged(int selStart, int selEnd) {
//        super.onSelectionChanged(selStart, selEnd);
//        if (selStart == selEnd) { // 防止不能多选
//            setSelection(getText().length()); // 保证光标始终在最后面
//        }
//
//    }
}
