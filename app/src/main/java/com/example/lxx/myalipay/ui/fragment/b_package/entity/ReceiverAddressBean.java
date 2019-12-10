package com.example.lxx.myalipay.ui.fragment.b_package.entity;

import java.io.Serializable;

/**
 * created by lxx at 2019/11/21 15:34
 * 描述:
 */
public class ReceiverAddressBean implements Serializable,Comparable<ReceiverAddressBean> {
    private String userName;
    private String phone;
    private String address;
    private String area;
    private boolean isClick;
    private int position;

    public ReceiverAddressBean(String userName, String phone,String area, String address, boolean isClick,int position) {
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.area = area;
        this.isClick = isClick;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    @Override
    public int compareTo(ReceiverAddressBean o) {
        int position = this.getPosition() -o.getPosition();
        if (position == 0) {
            return this.position - o.getPosition();
        }
        return position;
    }
}

