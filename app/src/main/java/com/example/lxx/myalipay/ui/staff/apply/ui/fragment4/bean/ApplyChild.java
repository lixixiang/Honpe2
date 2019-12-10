package com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.bean;

/**
 * created by lxx at 2019/11/29 10:24
 * 描述:
 */
public class ApplyChild {
    private String orderNo;
    private String type;
    private String date;
    private String time1;
    private String time2;
    private String num1;
    private String num2;
    private int menu1;
    private int menu2;
    private int position;
    public ApplyChild() {
    }

    public ApplyChild(String type, String date, String time1, String time2, String num1, String num2) {
        this.type = type;
        this.date = date;
        this.time1 = time1;
        this.time2 = time2;
        this.num1 = num1;
        this.num2 = num2;
    }

    public int getMenu1() {
        return menu1;
    }

    public void setMenu1(int menu1) {
        this.menu1 = menu1;
    }

    public int getMenu2() {
        return menu2;
    }

    public void setMenu2(int menu2) {
        this.menu2 = menu2;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }


}

