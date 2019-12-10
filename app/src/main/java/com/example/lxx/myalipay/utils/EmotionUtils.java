package com.example.lxx.myalipay.utils;

import java.util.LinkedHashMap;

/**
 * created by lxx at 2019/11/12 10:59
 * 描述:表情加载类,可自己添加多种表情，分别建立不同的map存放和不同的标志符即可
 */
public class EmotionUtils {
    /**
     * key-表情文字;
     * value-表情图片资源
     */
    public static LinkedHashMap<String, Integer> EMPTY_GIF_MAP;
    public static LinkedHashMap<String, Integer> EMOTION_STATIC_MAP;


    static {
        EMPTY_GIF_MAP = new LinkedHashMap<>();

    }
}
