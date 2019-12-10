package com.example.lxx.myalipay.ui.staff.bean;

/**
 * created by lxx at 2019/11/27 13:36
 * 描述:内部员工首页实体类
 */
public class InnerManagerBean {
    private int icon;
    private String title;
    private String content;
    private int fontColor;
    private int msgNum;


    public InnerManagerBean() {
    }

    public InnerManagerBean(int icon, String title, String content) {
        this.icon = icon;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "InnerManagerBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    public int getFontColor() {
        return fontColor;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

