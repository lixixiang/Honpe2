package com.example.lxx.myalipay.ui.staff.query.ui.position7.bean;

import java.io.Serializable;
import java.util.List;

/**
 * created by lxx at 2019/12/3 16:01
 * 描述:
 */
public class GridObjectBean implements Serializable {
    private String userName;
    private String sex;
    private int position;
    private String depart;
    private String team;
    private String build;
    private String floor;
    private String room;
    private String bedNum;
    private int totalBedNum; //总床位
    private String rowid;
    private String ParRecid;
    private List<FloorManagerBean.FloorDataBean.FloorDetailBean> detailBeans;

    public List<FloorManagerBean.FloorDataBean.FloorDetailBean> getDetailBeans() {
        return detailBeans;
    }

    public void setDetailBeans(List<FloorManagerBean.FloorDataBean.FloorDetailBean> detailBeans) {
        this.detailBeans = detailBeans;
    }

    public int getTotalBedNum() {
        return totalBedNum;
    }

    public void setTotalBedNum(int totalBedNum) {
        this.totalBedNum = totalBedNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getParRecid() {
        return ParRecid;
    }

    public void setParRecid(String parRecid) {
        ParRecid = parRecid;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }
}
