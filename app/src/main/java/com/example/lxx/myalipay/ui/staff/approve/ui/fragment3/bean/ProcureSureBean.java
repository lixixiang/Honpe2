package com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.bean;

import java.io.Serializable;
import java.util.List;

/**
 * created by lxx at 2019/12/2 10:25
 * 描述:
 */
public class ProcureSureBean {
    private int Status;
    private String Msg;
    private List<DataBean> Data;
    private boolean isTop;

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

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

    public static class DataBean implements Serializable {
        /**
         * OrderNo : H180330052
         * ApplyNo : WF18030549
         * ApplyUserID : SXC
         * ApplyUserName : 苏小婵
         * DeptName : 工程部
         * GroupName : 五金组
         * ApplyTimer : 2018-03-31 08:51
         * OrderTimer : 2018-03-30 19:59
         * OrderGroup : 日本业务部
         * OrderDeliveryDate : 2018-04-05
         * OrderFollower : 周敏
         * OutWorkType : CNC
         * ApplyNotes : 因目前急单太多，无法排上机，为避免影响交期，申请外协。
         * Supplier : 东莞市铠邦模具有限公司
         * Amount : 23000.0
         * DeliveryDate : 2018-04-08
         * PurchaseNotes :
         * PuConfirm : 1
         * PuConfirmTimer : 2018-04-02 09:31
         * ConfirmResult :
         * ConfirmNotes :
         * details : null
         */

        private String OrderNo;
        private String ApplyNo;
        private String ApplyUserID;
        private String ApplyUserName;
        private String DeptName;
        private String GroupName;
        private String ApplyTimer;
        private String OrderTimer;
        private String OrderGroup;
        private String OrderDeliveryDate;
        private String OrderFollower;
        private String OutWorkType;
        private String ApplyNotes;
        private String Supplier;
        private double Amount;
        private String DeliveryDate;
        private String PurchaseNotes;
        private String PuConfirm;
        private String PuConfirmTimer;
        private String ConfirmResult;
        private String ConfirmNotes;
        private Object details;
        private String CompletedFlow;



        public String getCompletedFlow() {
            return CompletedFlow;
        }

        public void setCompletedFlow(String completedFlow) {
            CompletedFlow = completedFlow;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public String getApplyNo() {
            return ApplyNo;
        }

        public void setApplyNo(String ApplyNo) {
            this.ApplyNo = ApplyNo;
        }

        public String getApplyUserID() {
            return ApplyUserID;
        }

        public void setApplyUserID(String ApplyUserID) {
            this.ApplyUserID = ApplyUserID;
        }

        public String getApplyUserName() {
            return ApplyUserName;
        }

        public void setApplyUserName(String ApplyUserName) {
            this.ApplyUserName = ApplyUserName;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String GroupName) {
            this.GroupName = GroupName;
        }

        public String getApplyTimer() {
            return ApplyTimer;
        }

        public void setApplyTimer(String ApplyTimer) {
            this.ApplyTimer = ApplyTimer;
        }

        public String getOrderTimer() {
            return OrderTimer;
        }

        public void setOrderTimer(String OrderTimer) {
            this.OrderTimer = OrderTimer;
        }

        public String getOrderGroup() {
            return OrderGroup;
        }

        public void setOrderGroup(String OrderGroup) {
            this.OrderGroup = OrderGroup;
        }

        public String getOrderDeliveryDate() {
            return OrderDeliveryDate;
        }

        public void setOrderDeliveryDate(String OrderDeliveryDate) {
            this.OrderDeliveryDate = OrderDeliveryDate;
        }

        public String getOrderFollower() {
            return OrderFollower;
        }

        public void setOrderFollower(String OrderFollower) {
            this.OrderFollower = OrderFollower;
        }

        public String getOutWorkType() {
            return OutWorkType;
        }

        public void setOutWorkType(String OutWorkType) {
            this.OutWorkType = OutWorkType;
        }

        public String getApplyNotes() {
            return ApplyNotes;
        }

        public void setApplyNotes(String ApplyNotes) {
            this.ApplyNotes = ApplyNotes;
        }

        public String getSupplier() {
            return Supplier;
        }

        public void setSupplier(String Supplier) {
            this.Supplier = Supplier;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }

        public String getDeliveryDate() {
            return DeliveryDate;
        }

        public void setDeliveryDate(String DeliveryDate) {
            this.DeliveryDate = DeliveryDate;
        }

        public String getPurchaseNotes() {
            return PurchaseNotes;
        }

        public void setPurchaseNotes(String PurchaseNotes) {
            this.PurchaseNotes = PurchaseNotes;
        }

        public String getPuConfirm() {
            return PuConfirm;
        }

        public void setPuConfirm(String PuConfirm) {
            this.PuConfirm = PuConfirm;
        }

        public String getPuConfirmTimer() {
            return PuConfirmTimer;
        }

        public void setPuConfirmTimer(String PuConfirmTimer) {
            this.PuConfirmTimer = PuConfirmTimer;
        }

        public String getConfirmResult() {
            return ConfirmResult;
        }

        public void setConfirmResult(String ConfirmResult) {
            this.ConfirmResult = ConfirmResult;
        }

        public String getConfirmNotes() {
            return ConfirmNotes;
        }

        public void setConfirmNotes(String ConfirmNotes) {
            this.ConfirmNotes = ConfirmNotes;
        }

        public Object getDetails() {
            return details;
        }

        public void setDetails(Object details) {
            this.details = details;
        }
    }
}


