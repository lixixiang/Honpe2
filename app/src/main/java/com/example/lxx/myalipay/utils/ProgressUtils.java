package com.example.lxx.myalipay.utils;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * created by lxx at 2019/11/12 14:31
 * 描述:IOS进度工具
 */
public class ProgressUtils {
    public static KProgressHUD hud;

    /**
     * 显示加载中
     * status 0 表示
     * **/
    public static void disLoadView(Context context, int status){
        if(hud != null && status == 0){
            hud.dismiss();
        }else if(status ==1){
            hud = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setLabel("正在加载...");
            try{
                hud.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
