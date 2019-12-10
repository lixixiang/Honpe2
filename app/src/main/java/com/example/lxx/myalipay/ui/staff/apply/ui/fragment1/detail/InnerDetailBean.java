package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.detail;

import java.util.List;

/**
 * created by lxx at 2019/11/28 14:03
 * 描述:
 */
public class InnerDetailBean {
    public static final int TYPE_TOP = 0x0000;
    public static final int TYPE_NORMAL = 0x0001;
    private int itemType;
    private String name;
    private String content;
    private String date;
    private int status;
    private String senStatus;
    private int currentStatus;
    private String CarStatus;
    public boolean isSelected = false;
    private List<String> array;

    public List<String> getArray() {
        return array;
    }

    public void setArray(List<String> array) {
        this.array = array;
    }

    public String getCarStatus() {
        return CarStatus;
    }

    public void setCarStatus(String carStatus) {
        CarStatus = carStatus;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getSenStatus() {
        return senStatus;
    }

    public void setSenStatus(String senStatus) {
        this.senStatus = senStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

