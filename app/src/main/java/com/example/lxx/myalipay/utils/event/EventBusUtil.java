package com.example.lxx.myalipay.utils.event;

import org.greenrobot.eventbus.EventBus;

/**
 * created by lxx at 2019/11/11 9:01
 * 描述:事件分发工具
 */
public class EventBusUtil {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
