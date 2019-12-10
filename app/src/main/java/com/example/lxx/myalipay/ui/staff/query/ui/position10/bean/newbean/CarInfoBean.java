package com.example.lxx.myalipay.ui.staff.query.ui.position10.bean.newbean;

import java.util.List;


/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position10.bean
 * 作者: lxx
 * 日期: 2019/3/21 15:42
 * 描述:
 */
public class CarInfoBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"CarSeq":"1","CarStatus":"闲置中","CarNo":"粤BQ7J66","CarType":"吉利帝豪","Seats":0,"Driver":"葛廷武","DriverTel":"666375","PicUrl":"http://api.honpe.com:8079/Car/粤BQ7J66.jpg","CarRemarks":"","OrderList":[{"CarSeq":"1","CarNo":"粤BQ7J66","CarType":"吉利帝豪","CarStatus":"","Driver":"葛廷武","DriverTel":"666375","OrderSeq":"0","SendCarNo":"","DeptName":"","GroupName":"","UserName":"","OrderTime":"","UseCarTime":"","Entourage":"","Items":"","Reason":"","RetTime":"","RetMileage":0,"Remarks":"","Tel":"","SenCarby":"","SenCarTime":"","CancelStatus":0,"UserId":"","DeptId":"","GroupId":"","UserCarDept":"","UserCarGroup":"","Status":0,"OrderStatus":"待派单","Mileage":0,"SetOutTime":"","Destination":"","EstimatedRTime":"","EstimatedUseTime":"","Opts":[""]}],"OrderCount":0}]
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
         * CarSeq : 1
         * CarStatus : 闲置中
         * CarNo : 粤BQ7J66
         * CarType : 吉利帝豪
         * Seats : 0
         * Driver : 葛廷武
         * DriverTel : 666375
         * PicUrl : http://api.honpe.com:8079/Car/粤BQ7J66.jpg
         * CarRemarks :
         * OrderList : [{"CarSeq":"1","CarNo":"粤BQ7J66","CarType":"吉利帝豪","CarStatus":"","Driver":"葛廷武","DriverTel":"666375","OrderSeq":"0","SendCarNo":"","DeptName":"","GroupName":"","UserName":"","OrderTime":"","UseCarTime":"","Entourage":"","Items":"","Reason":"","RetTime":"","RetMileage":0,"Remarks":"","Tel":"","SenCarby":"","SenCarTime":"","CancelStatus":0,"UserId":"","DeptId":"","GroupId":"","UserCarDept":"","UserCarGroup":"","Status":0,"OrderStatus":"待派单","Mileage":0,"SetOutTime":"","Destination":"","EstimatedRTime":"","EstimatedUseTime":"","Opts":[""]}]
         * OrderCount : 0
         */
        private String CarSeq;
        private String CarStatus;
        private String CarNo;
        private String CarType;
        private int Seats;
        private String Driver;
        private String DriverTel;
        private String PicUrl;
        private String CarRemarks;
        private int OrderCount;
        private List<OrderListBean> OrderList;


        public String getCarSeq() {
            return CarSeq;
        }

        public void setCarSeq(String CarSeq) {
            this.CarSeq = CarSeq;
        }

        public String getCarStatus() {
            return CarStatus;
        }

        public void setCarStatus(String CarStatus) {
            this.CarStatus = CarStatus;
        }

        public String getCarNo() {
            return CarNo;
        }

        public void setCarNo(String CarNo) {
            this.CarNo = CarNo;
        }

        public String getCarType() {
            return CarType;
        }

        public void setCarType(String CarType) {
            this.CarType = CarType;
        }

        public int getSeats() {
            return Seats;
        }

        public void setSeats(int Seats) {
            this.Seats = Seats;
        }

        public String getDriver() {
            return Driver;
        }

        public void setDriver(String Driver) {
            this.Driver = Driver;
        }

        public String getDriverTel() {
            return DriverTel;
        }

        public void setDriverTel(String DriverTel) {
            this.DriverTel = DriverTel;
        }

        public String getPicUrl() {
            return PicUrl;
        }

        public void setPicUrl(String PicUrl) {
            this.PicUrl = PicUrl;
        }

        public String getCarRemarks() {
            return CarRemarks;
        }

        public void setCarRemarks(String CarRemarks) {
            this.CarRemarks = CarRemarks;
        }

        public int getOrderCount() {
            return OrderCount;
        }

        public void setOrderCount(int OrderCount) {
            this.OrderCount = OrderCount;
        }

        public List<OrderListBean> getOrderList() {
            return OrderList;
        }

        public void setOrderList(List<OrderListBean> OrderList) {
            this.OrderList = OrderList;
        }


        public static class OrderListBean {
            /**
             * CarSeq : 1
             * CarNo : 粤BQ7J66
             * CarType : 吉利帝豪
             * CarStatus :
             * Driver : 葛廷武
             * DriverTel : 666375
             * OrderSeq : 0
             * SendCarNo :
             * DeptName :
             * GroupName :
             * UserName :
             * OrderTime :
             * UseCarTime :
             * Entourage :
             * Items :
             * Reason :
             * RetTime :
             * RetMileage : 0
             * Remarks :
             * Tel :
             * SenCarby :
             * SenCarTime :
             * CancelStatus : 0
             * UserId :
             * DeptId :
             * GroupId :
             * UserCarDept :
             * UserCarGroup :
             * Status : 0
             * OrderStatus : 待派单
             * Mileage : 0
             * SetOutTime :
             * Destination :
             * EstimatedRTime :
             * EstimatedUseTime :
             * Opts : [""]
             */

            private String CarSeq;
            private String CarNo;
            private String CarType;
            private String CarStatus;
            private String Driver;
            private String DriverTel;
            private String OrderSeq;
            private String SendCarNo;
            private String DeptName;
            private String GroupName;
            private String UserName;
            private String OrderTime;
            private String UseCarTime;
            private String Entourage;
            private String Items;
            private String Reason;
            private String RetTime;
            private int RetMileage;
            private String Remarks;
            private String Tel;
            private String SenCarby;
            private String SenCarTime;
            private int CancelStatus;
            private String UserId;
            private String DeptId;
            private String GroupId;
            private String UserCarDept;
            private String UserCarGroup;
            private int Status;
            private String OrderStatus;
            private int Mileage;
            private String SetOutTime;
            private String Destination;
            private String EstimatedRTime;
            private String EstimatedUseTime;
            private List<String> Opts;

            public String getCarSeq() {
                return CarSeq;
            }

            public void setCarSeq(String CarSeq) {
                this.CarSeq = CarSeq;
            }

            public String getCarNo() {
                return CarNo;
            }

            public void setCarNo(String CarNo) {
                this.CarNo = CarNo;
            }

            public String getCarType() {
                return CarType;
            }

            public void setCarType(String CarType) {
                this.CarType = CarType;
            }

            public String getCarStatus() {
                return CarStatus;
            }

            public void setCarStatus(String CarStatus) {
                this.CarStatus = CarStatus;
            }

            public String getDriver() {
                return Driver;
            }

            public void setDriver(String Driver) {
                this.Driver = Driver;
            }

            public String getDriverTel() {
                return DriverTel;
            }

            public void setDriverTel(String DriverTel) {
                this.DriverTel = DriverTel;
            }

            public String getOrderSeq() {
                return OrderSeq;
            }

            public void setOrderSeq(String OrderSeq) {
                this.OrderSeq = OrderSeq;
            }

            public String getSendCarNo() {
                return SendCarNo;
            }

            public void setSendCarNo(String SendCarNo) {
                this.SendCarNo = SendCarNo;
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

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getOrderTime() {
                return OrderTime;
            }

            public void setOrderTime(String OrderTime) {
                this.OrderTime = OrderTime;
            }

            public String getUseCarTime() {
                return UseCarTime;
            }

            public void setUseCarTime(String UseCarTime) {
                this.UseCarTime = UseCarTime;
            }

            public String getEntourage() {
                return Entourage;
            }

            public void setEntourage(String Entourage) {
                this.Entourage = Entourage;
            }

            public String getItems() {
                return Items;
            }

            public void setItems(String Items) {
                this.Items = Items;
            }

            public String getReason() {
                return Reason;
            }

            public void setReason(String Reason) {
                this.Reason = Reason;
            }

            public String getRetTime() {
                return RetTime;
            }

            public void setRetTime(String RetTime) {
                this.RetTime = RetTime;
            }

            public int getRetMileage() {
                return RetMileage;
            }

            public void setRetMileage(int RetMileage) {
                this.RetMileage = RetMileage;
            }

            public String getRemarks() {
                return Remarks;
            }

            public void setRemarks(String Remarks) {
                this.Remarks = Remarks;
            }

            public String getTel() {
                return Tel;
            }

            public void setTel(String Tel) {
                this.Tel = Tel;
            }

            public String getSenCarby() {
                return SenCarby;
            }

            public void setSenCarby(String SenCarby) {
                this.SenCarby = SenCarby;
            }

            public String getSenCarTime() {
                return SenCarTime;
            }

            public void setSenCarTime(String SenCarTime) {
                this.SenCarTime = SenCarTime;
            }

            public int getCancelStatus() {
                return CancelStatus;
            }

            public void setCancelStatus(int CancelStatus) {
                this.CancelStatus = CancelStatus;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getDeptId() {
                return DeptId;
            }

            public void setDeptId(String DeptId) {
                this.DeptId = DeptId;
            }

            public String getGroupId() {
                return GroupId;
            }

            public void setGroupId(String GroupId) {
                this.GroupId = GroupId;
            }

            public String getUserCarDept() {
                return UserCarDept;
            }

            public void setUserCarDept(String UserCarDept) {
                this.UserCarDept = UserCarDept;
            }

            public String getUserCarGroup() {
                return UserCarGroup;
            }

            public void setUserCarGroup(String UserCarGroup) {
                this.UserCarGroup = UserCarGroup;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public String getOrderStatus() {
                return OrderStatus;
            }

            public void setOrderStatus(String OrderStatus) {
                this.OrderStatus = OrderStatus;
            }

            public int getMileage() {
                return Mileage;
            }

            public void setMileage(int Mileage) {
                this.Mileage = Mileage;
            }

            public String getSetOutTime() {
                return SetOutTime;
            }

            public void setSetOutTime(String SetOutTime) {
                this.SetOutTime = SetOutTime;
            }

            public String getDestination() {
                return Destination;
            }

            public void setDestination(String Destination) {
                this.Destination = Destination;
            }

            public String getEstimatedRTime() {
                return EstimatedRTime;
            }

            public void setEstimatedRTime(String EstimatedRTime) {
                this.EstimatedRTime = EstimatedRTime;
            }

            public String getEstimatedUseTime() {
                return EstimatedUseTime;
            }

            public void setEstimatedUseTime(String EstimatedUseTime) {
                this.EstimatedUseTime = EstimatedUseTime;
            }

            public List<String> getOpts() {
                return Opts;
            }

            public void setOpts(List<String> Opts) {
                this.Opts = Opts;
            }

        }
    }
}
