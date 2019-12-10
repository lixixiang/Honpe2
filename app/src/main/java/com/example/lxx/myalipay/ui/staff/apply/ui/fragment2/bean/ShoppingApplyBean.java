package com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean;

import java.util.List;

/**
 * created by lxx at 2019/11/28 17:17
 * 描述:采购物料
 */
public class ShoppingApplyBean {
    private String depart;     //部门
    private String userName;   //姓名
    private String orderNo;    //订单号
    private String orderCause;//原因
    private String date;     //日期
    private int total;//总金额

    private List<CollocationDetail> collocation;


    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
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

    public String getOrderCause() {
        return orderCause;
    }

    public void setOrderCause(String orderCause) {
        this.orderCause = orderCause;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CollocationDetail> getCollcationDetail() {
        return collocation;
    }

    public void setCollcationDetail(List<CollocationDetail> collocationDetail) {
        this.collocation = collocationDetail;
    }

    public static class CollocationDetail {
        private int orderOn;
        private String name;
        private String type;
        private String unit;
        private int num;
        private double price;
        private double total;

        public CollocationDetail() {
        }

        public CollocationDetail(int orderOn,String name, String type, String unit, int num, double price) {
            this.orderOn = orderOn;
            this.name = name;
            this.type = type;
            this.unit = unit;
            this.num = num;
            this.price = price;

        }

        public int getOrderOn() {
            return orderOn;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}

