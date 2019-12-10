package com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * created by lxx at 2019/12/2 11:41
 * 描述:
 */
public class SubContractDetailBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : {"OrderNo":"H191013001","ApplyNo":"WF19100758","ApplyUserID":"CTJ","ApplyUserName":"陈桃娇","DeptName":"打磨部","GroupName":"数码组","ApplyTimer":"2019-10-17 17:14","OrderTimer":"2019-10-13 09:08","OrderGroup":"国内业务部","OrderGroupID":null,"ZzGroup":null,"ZzGroupName":null,"OrderDeliveryDate":"2019-10-17","OrderFollower":"李小凤","OutWorkType":"离子镀","ApplyNotes":"外发离子镀，供应商：致远，离子镀黑色，先打样，试效果，后面还要镀产品，一炉800元","Supplier":"临时加工商","Amount":832,"DeliveryDate":"2019-10-17","PurchaseNotes":"","PuConfirm":"1","PuConfirmTimer":"2019-10-17 17:15","ConfirmResult":"","ConfirmNotes":"","CompletedFlow":null,"details":[{"ApplyNo":"WF19100758","ItemName":"18NF17","Specs":"","Material":"不锈钢","Unit":"pcs","ItemNumber":1,"PostTreatment":"","ItemNotes":"2019-10-16 12:00","PartCode":"068411b4-d0d5-4bca-9332-1b8c1eaf932a","QuotationNo":"94b39d89-39b3-493a-ad29-f3a3b23a4230","ItemAmount":832,"UnitPrice":832,"Rate":0,"InboundNo":"HPWW191024598","MaterialCode":"W.H191013001-001"}],"Confirmdetails":[{"ApplyNo":"WF19100758","UserId":"QJQ","ConfirmLevel":"部门主管审核","ConfirmBy":"全剑强","ConfirmTimer":"2019-10-17 17:14","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":1},{"ApplyNo":"WF19100758","UserId":"QJQ","ConfirmLevel":"部门总监审核","ConfirmBy":"全剑强","ConfirmTimer":"2019-10-17 17:14","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":2},{"ApplyNo":"WF19100758","UserId":"NRQ","ConfirmLevel":"询价发布","ConfirmBy":"农瑞琼","ConfirmTimer":"2019-10-17 17:16","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":3},{"ApplyNo":"WF19100758","UserId":"NRQ","ConfirmLevel":"采购确认","ConfirmBy":"农瑞琼","ConfirmTimer":"2019-10-17 17:17","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":4},{"ApplyNo":"WF19100758","UserId":"QJQ","ConfirmLevel":"部门经理确认","ConfirmBy":"全剑强","ConfirmTimer":"2019-10-17 17:20","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":5},{"ApplyNo":"WF19100758","UserId":"QJQ","ConfirmLevel":"部门总监确认","ConfirmBy":"全剑强","ConfirmTimer":"2019-10-17 17:20","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":6},{"ApplyNo":"WF19100758","UserId":"GFF","ConfirmLevel":"业务确认","ConfirmBy":"李小凤","ConfirmTimer":"2019-10-17 19:28","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":7},{"ApplyNo":"WF19100758","UserId":"LXF","ConfirmLevel":"业务经理审批","ConfirmBy":"李小凤","ConfirmTimer":"2019-10-18 12:04","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":8},{"ApplyNo":"WF19100758","UserId":"LBY","ConfirmLevel":"财务确认","ConfirmBy":"梁波雅","ConfirmTimer":"2019-10-18 18:53","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":9},{"ApplyNo":"WF19100758","UserId":"CYL","ConfirmLevel":"总经办审批","ConfirmBy":"陈奕龙","ConfirmTimer":"2019-10-19 20:04","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":10}]}
     */

    private int Status;
    private String Msg;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * OrderNo : H191013001
         * ApplyNo : WF19100758
         * ApplyUserID : CTJ
         * ApplyUserName : 陈桃娇
         * DeptName : 打磨部
         * GroupName : 数码组
         * ApplyTimer : 2019-10-17 17:14
         * OrderTimer : 2019-10-13 09:08
         * OrderGroup : 国内业务部
         * OrderGroupID : null
         * ZzGroup : null
         * ZzGroupName : null
         * OrderDeliveryDate : 2019-10-17
         * OrderFollower : 李小凤
         * OutWorkType : 离子镀
         * ApplyNotes : 外发离子镀，供应商：致远，离子镀黑色，先打样，试效果，后面还要镀产品，一炉800元
         * Supplier : 临时加工商
         * Amount : 832.0
         * DeliveryDate : 2019-10-17
         * PurchaseNotes :
         * PuConfirm : 1
         * PuConfirmTimer : 2019-10-17 17:15
         * ConfirmResult :
         * ConfirmNotes :
         * CompletedFlow : null
         * details : [{"ApplyNo":"WF19100758","ItemName":"18NF17","Specs":"","Material":"不锈钢","Unit":"pcs","ItemNumber":1,"PostTreatment":"","ItemNotes":"2019-10-16 12:00","PartCode":"068411b4-d0d5-4bca-9332-1b8c1eaf932a","QuotationNo":"94b39d89-39b3-493a-ad29-f3a3b23a4230","ItemAmount":832,"UnitPrice":832,"Rate":0,"InboundNo":"HPWW191024598","MaterialCode":"W.H191013001-001"}]
         * Confirmdetails : [{"ApplyNo":"WF19100758","UserId":"QJQ","ConfirmLevel":"部门主管审核","ConfirmBy":"全剑强","ConfirmTimer":"2019-10-17 17:14","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":1},{"ApplyNo":"WF19100758","UserId":"QJQ","ConfirmLevel":"部门总监审核","ConfirmBy":"全剑强","ConfirmTimer":"2019-10-17 17:14","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":2},{"ApplyNo":"WF19100758","UserId":"NRQ","ConfirmLevel":"询价发布","ConfirmBy":"农瑞琼","ConfirmTimer":"2019-10-17 17:16","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":3},{"ApplyNo":"WF19100758","UserId":"NRQ","ConfirmLevel":"采购确认","ConfirmBy":"农瑞琼","ConfirmTimer":"2019-10-17 17:17","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":4},{"ApplyNo":"WF19100758","UserId":"QJQ","ConfirmLevel":"部门经理确认","ConfirmBy":"全剑强","ConfirmTimer":"2019-10-17 17:20","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":5},{"ApplyNo":"WF19100758","UserId":"QJQ","ConfirmLevel":"部门总监确认","ConfirmBy":"全剑强","ConfirmTimer":"2019-10-17 17:20","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":6},{"ApplyNo":"WF19100758","UserId":"GFF","ConfirmLevel":"业务确认","ConfirmBy":"李小凤","ConfirmTimer":"2019-10-17 19:28","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":7},{"ApplyNo":"WF19100758","UserId":"LXF","ConfirmLevel":"业务经理审批","ConfirmBy":"李小凤","ConfirmTimer":"2019-10-18 12:04","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":8},{"ApplyNo":"WF19100758","UserId":"LBY","ConfirmLevel":"财务确认","ConfirmBy":"梁波雅","ConfirmTimer":"2019-10-18 18:53","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":9},{"ApplyNo":"WF19100758","UserId":"CYL","ConfirmLevel":"总经办审批","ConfirmBy":"陈奕龙","ConfirmTimer":"2019-10-19 20:04","ConfirmResult":"同意","ConfirmNotes":"","ConfirmSeq":10}]
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
        private Object OrderGroupID;
        private Object ZzGroup;
        private Object ZzGroupName;
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
        private Object CompletedFlow;
        private List<DetailsBean> details;
        private List<ConfirmdetailsBean> Confirmdetails;

        public static final int CSS1 = 1;
        public static final int CSS2 = 2;
        public static final int CSS3 = 3;

        private int itemType;

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

        public Object getOrderGroupID() {
            return OrderGroupID;
        }

        public void setOrderGroupID(Object OrderGroupID) {
            this.OrderGroupID = OrderGroupID;
        }

        public Object getZzGroup() {
            return ZzGroup;
        }

        public void setZzGroup(Object ZzGroup) {
            this.ZzGroup = ZzGroup;
        }

        public Object getZzGroupName() {
            return ZzGroupName;
        }

        public void setZzGroupName(Object ZzGroupName) {
            this.ZzGroupName = ZzGroupName;
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

        public Object getCompletedFlow() {
            return CompletedFlow;
        }

        public void setCompletedFlow(Object CompletedFlow) {
            this.CompletedFlow = CompletedFlow;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public List<ConfirmdetailsBean> getConfirmdetails() {
            return Confirmdetails;
        }

        public void setConfirmdetails(List<ConfirmdetailsBean> Confirmdetails) {
            this.Confirmdetails = Confirmdetails;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public static class DetailsBean {
            /**
             * ApplyNo : WF19100758
             * ItemName : 18NF17
             * Specs :
             * Material : 不锈钢
             * Unit : pcs
             * ItemNumber : 1
             * PostTreatment :
             * ItemNotes : 2019-10-16 12:00
             * PartCode : 068411b4-d0d5-4bca-9332-1b8c1eaf932a
             * QuotationNo : 94b39d89-39b3-493a-ad29-f3a3b23a4230
             * ItemAmount : 832.0
             * UnitPrice : 832.0
             * Rate : 0.0
             * InboundNo : HPWW191024598
             * MaterialCode : W.H191013001-001
             */

            private String ApplyNo;
            private String ItemName;
            private String Specs;
            private String Material;
            private String Unit;
            private int ItemNumber;
            private String PostTreatment;
            private String ItemNotes;
            private String PartCode;
            private String QuotationNo;
            private double ItemAmount;
            private double UnitPrice;
            private double Rate;
            private String InboundNo;
            private String MaterialCode;

            public String getApplyNo() {
                return ApplyNo;
            }

            public void setApplyNo(String ApplyNo) {
                this.ApplyNo = ApplyNo;
            }

            public String getItemName() {
                return ItemName;
            }

            public void setItemName(String ItemName) {
                this.ItemName = ItemName;
            }

            public String getSpecs() {
                return Specs;
            }

            public void setSpecs(String Specs) {
                this.Specs = Specs;
            }

            public String getMaterial() {
                return Material;
            }

            public void setMaterial(String Material) {
                this.Material = Material;
            }

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public int getItemNumber() {
                return ItemNumber;
            }

            public void setItemNumber(int ItemNumber) {
                this.ItemNumber = ItemNumber;
            }

            public String getPostTreatment() {
                return PostTreatment;
            }

            public void setPostTreatment(String PostTreatment) {
                this.PostTreatment = PostTreatment;
            }

            public String getItemNotes() {
                return ItemNotes;
            }

            public void setItemNotes(String ItemNotes) {
                this.ItemNotes = ItemNotes;
            }

            public String getPartCode() {
                return PartCode;
            }

            public void setPartCode(String PartCode) {
                this.PartCode = PartCode;
            }

            public String getQuotationNo() {
                return QuotationNo;
            }

            public void setQuotationNo(String QuotationNo) {
                this.QuotationNo = QuotationNo;
            }

            public double getItemAmount() {
                return ItemAmount;
            }

            public void setItemAmount(double ItemAmount) {
                this.ItemAmount = ItemAmount;
            }

            public double getUnitPrice() {
                return UnitPrice;
            }

            public void setUnitPrice(double UnitPrice) {
                this.UnitPrice = UnitPrice;
            }

            public double getRate() {
                return Rate;
            }

            public void setRate(double Rate) {
                this.Rate = Rate;
            }

            public String getInboundNo() {
                return InboundNo;
            }

            public void setInboundNo(String InboundNo) {
                this.InboundNo = InboundNo;
            }

            public String getMaterialCode() {
                return MaterialCode;
            }

            public void setMaterialCode(String MaterialCode) {
                this.MaterialCode = MaterialCode;
            }
        }

        public static class ConfirmdetailsBean {
            /**
             * ApplyNo : WF19100758
             * UserId : QJQ
             * ConfirmLevel : 部门主管审核
             * ConfirmBy : 全剑强
             * ConfirmTimer : 2019-10-17 17:14
             * ConfirmResult : 同意
             * ConfirmNotes :
             * ConfirmSeq : 1
             */

            private String ApplyNo;
            private String UserId;
            private String ConfirmLevel;
            private String ConfirmBy;
            private String ConfirmTimer;
            private String ConfirmResult;
            private String ConfirmNotes;
            private int ConfirmSeq;

            public String getApplyNo() {
                return ApplyNo;
            }

            public void setApplyNo(String ApplyNo) {
                this.ApplyNo = ApplyNo;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getConfirmLevel() {
                return ConfirmLevel;
            }

            public void setConfirmLevel(String ConfirmLevel) {
                this.ConfirmLevel = ConfirmLevel;
            }

            public String getConfirmBy() {
                return ConfirmBy;
            }

            public void setConfirmBy(String ConfirmBy) {
                this.ConfirmBy = ConfirmBy;
            }

            public String getConfirmTimer() {
                return ConfirmTimer;
            }

            public void setConfirmTimer(String ConfirmTimer) {
                this.ConfirmTimer = ConfirmTimer;
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

            public int getConfirmSeq() {
                return ConfirmSeq;
            }

            public void setConfirmSeq(int ConfirmSeq) {
                this.ConfirmSeq = ConfirmSeq;
            }
        }
    }
}

