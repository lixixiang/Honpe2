package com.example.lxx.myalipay.ui.fragment.a_package.entity;

/**
 * created by lxx at 2019/11/11 17:28
 * 描述:
 */
public class UserCenterBean {
    private String FontIcon;
    private String title;
    private String content;
    private int FontColor;
    private int icon;



    public UserCenterBean() {
    }

    public int getFontColor() {
        return FontColor;
    }

    public void setFontColor(int fontColor) {
        FontColor = fontColor;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserCenterBean(String icon, String title) {
        FontIcon = icon;
        this.title = title;
    }

    public UserCenterBean(String fontIcon, String title, int fontColor) {
        FontIcon = fontIcon;
        this.title = title;
        FontColor = fontColor;
    }

    public String getFontIcon() {
        return FontIcon;
    }

    public void setFontIcon(String fontIcon) {
        FontIcon = fontIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

