package com.example.lxx.myalipay.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * created by lxx at 2019/11/12 11:50
 * 描述:
 */
public class GsonBuildUtil {
    public static String  GsonBuilder(Object obj) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String result = gson.toJson(obj);
        return result;
    }
}
