package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean;

import java.util.List;

/**
 * created by lxx at 2019/11/28 10:23
 * 描述:
 */
public class ItemDepartBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"DeptID":"BA","DeptName":"保安室","DeptGm":"","DeptGmId":""},{"DeptID":"CJ","DeptName":"操机部","DeptGm":"","DeptGmId":""},{"DeptID":"CW","DeptName":"财务部","DeptGm":"","DeptGmId":""},{"DeptID":"DM","DeptName":"打磨部","DeptGm":"","DeptGmId":""},{"DeptID":"FM","DeptName":"复模部","DeptGm":"","DeptGmId":""},{"DeptID":"FZB","DeptName":"副总办","DeptGm":"白鸿基","DeptGmId":"BHJ"},{"DeptID":"GC","DeptName":"工程部","DeptGm":"","DeptGmId":""},{"DeptID":"IT","DeptName":"研发部","DeptGm":"潘卫东","DeptGmId":"PZ"},{"DeptID":"PG","DeptName":"品管部","DeptGm":"孙效伦","DeptGmId":"SXL"},{"DeptID":"PT","DeptName":"皮套部","DeptGm":"","DeptGmId":""},{"DeptID":"PY","DeptName":"喷油部","DeptGm":"","DeptGmId":""},{"DeptID":"QT","DeptName":"其他","DeptGm":"","DeptGmId":""},{"DeptID":"SG","DeptName":"手工部","DeptGm":"","DeptGmId":""},{"DeptID":"XM","DeptName":"项目部","DeptGm":"","DeptGmId":""},{"DeptID":"XY","DeptName":"丝印部","DeptGm":"","DeptGmId":""},{"DeptID":"XZ","DeptName":"管理中心","DeptGm":"","DeptGmId":""},{"DeptID":"YY","DeptName":"营业部","DeptGm":"","DeptGmId":""},{"DeptID":"ZJB","DeptName":"总经办","DeptGm":"","DeptGmId":""}]
     */

    private int Status;
    private String Msg;
    private List<DataBean> Data;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * DeptID : BA
         * DeptName : 保安室
         * DeptGm :
         * DeptGmId :
         */

        private String DeptID;
        private String DeptName;
        private String DeptGm;
        private String DeptGmId;

        public String getDeptID() {
            return DeptID;
        }

        public void setDeptID(String DeptID) {
            this.DeptID = DeptID;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getDeptGm() {
            return DeptGm;
        }

        public void setDeptGm(String DeptGm) {
            this.DeptGm = DeptGm;
        }

        public String getDeptGmId() {
            return DeptGmId;
        }

        public void setDeptGmId(String DeptGmId) {
            this.DeptGmId = DeptGmId;
        }
    }
}
