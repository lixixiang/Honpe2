package com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean;

/**
 * created by lxx at 2019/12/1 19:41
 * 描述:
 */
public class RepairApplyListBean {
    private String orderNo;  //申请单号
    private String userName; //申请人
    private String Character;//性质
    private String applyTime;//申请时间
    private String type;//类型
    private String item;//事项
    private String point;//位置
    private String Times;//时限
    private String FinishTime;//完成时间
    private String Cause;//原因

    public RepairApplyListBean() {
    }

    public RepairApplyListBean(String orderNo,
                               String userName,
                               String character,
                               String applyTime,
                               String type,
                               String item,
                               String point,
                               String times,
                               String finishTime,
                               String cause) {
        this.orderNo = orderNo;
        this.userName = userName;
        Character = character;
        this.applyTime = applyTime;
        this.type = type;
        this.item = item;
        this.point = point;
        Times = times;
        FinishTime = finishTime;
        Cause = cause;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCharacter() {
        return Character;
    }

    public void setCharacter(String character) {
        Character = character;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getTimes() {
        return Times;
    }

    public void setTimes(String times) {
        Times = times;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getCause() {
        return Cause;
    }

    public void setCause(String cause) {
        Cause = cause;
    }
}

