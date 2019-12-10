package com.example.lxx.myalipay.widget.font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @package: com.example.lxx.myalipay.Widget
 * @date: 2018/4/27 12:34
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe:
 */
public class FontTextView5 extends AppCompatTextView {

    public FontTextView5(Context context) {
        super(context);
        init(context);
    }

    public FontTextView5(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FontTextView5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        //设置字体图标
        Typeface font = Typeface.createFromAsset(context.getAssets(), "iconfont5.ttf");
        this.setTypeface(font);
    }
}
