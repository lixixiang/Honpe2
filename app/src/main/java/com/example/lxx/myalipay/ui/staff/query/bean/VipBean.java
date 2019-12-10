package com.example.lxx.myalipay.ui.staff.query.bean;

/**
 * created by lxx at 2019/12/3 9:18
 * 描述:
 */
public class VipBean {
    private int id;
    private String theme;  //主题
    private String title; //标题
    private Integer url; //跳转的url参数
    private String iconFont;     //字体图标
    private int iconFontColor; //设置颜色

    private String depart;   //部门
    private String date_year_month_day; //年月日
    private String date_time_minute; //时分
    private String carNo; //车牌号
    private String remark; //备注

    private int icon;//图片
    private int type;//何种类型

    public int getIconFontColor() {
        return iconFontColor;
    }

    public void setIconFontColor(int iconFontColor) {
        this.iconFontColor = iconFontColor;
    }

    public void setUrl(Integer url) {
        this.url = url;
    }

    public String getIconFont() {
        return iconFont;
    }

    public void setIconFont(String iconFont) {
        this.iconFont = iconFont;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDate_year_month_day() {
        return date_year_month_day;
    }

    public void setDate_year_month_day(String date_year_month_day) {
        this.date_year_month_day = date_year_month_day;
    }

    public String getDate_time_minute() {
        return date_time_minute;
    }

    public void setDate_time_minute(String date_time_minute) {
        this.date_time_minute = date_time_minute;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public VipBean() {
    }
}

