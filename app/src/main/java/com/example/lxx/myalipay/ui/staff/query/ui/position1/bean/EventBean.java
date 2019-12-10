package com.example.lxx.myalipay.ui.staff.query.ui.position1.bean;

/**
 * created by lxx at 2019/12/3 10:32
 * 描述:事件公共类
 */
public class EventBean {
    private String Content;
    private String Tag;

    public EventBean(String content, String tag) {
        Content = content;
        Tag = tag;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }
}

