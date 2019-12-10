package com.example.lxx.myalipay.ui.staff.query.ui.position10.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * created by lxx at 2019/12/3 17:42
 * 描述:
 */
public class CarSendCheckBean {


    private int Status;
    private String Msg;
    private List<DataBean> Data;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * SendCarNo : CL180910
         * DeptName : 打磨部
         * GroupName : 五金组
         * UserName : 李星
         * OrderTime : 2018-01-27 11:45
         * UseCarTime : 2018-01-27 11:43
         * Entourage :
         * Items :
         * Destination : 公司氧化厂回公司
         * Reason : 拿货  滴滴补单
         * CarNo : 滴滴打车
         * Driver : 滴滴司机
         * ManagerConfirm :
         * ConfirmTime :
         * AdmChecked :
         * CheckTime :
         * SetOutTime :
         * RetTime :
         * RetMileage : 0
         * Mileage : 0
         * Remarks :
         * Tel :
         * Inspectedby :
         * SetOutGuardby :
         * RetGuardby :
         * SenCarby : 卢波
         * SenCarTime : 2018-01-27 11:50
         * CancelStatus : 0
         * UserId : FXX
         * DeptId : DM
         * GroupId : WJ
         * UserCarDept : 打磨部
         * UserCarGroup : 五金组
         * Status : 0
         * "CarStatus": "使用中",
         * "CarName": "吉利帝豪小型车",
         * "PicturesUrl": "http://api.honpe.com:8079/Car/粤B-Q7J66.jpg",
         * 01-27 12:16:15.876 7222-7222/com.example.administrator.honpe D/PRETTY_LOGGER-response: │ SetOutGuardby : 肖小海
         */
        public static final int DATE = 1;
        public static final int TEXT = 2;
        public static final int TEXT_CONTENT = 3;
        public static final int TIMELINE = 4;
        private int itemType;
        private String date_flag;

        private List<String> Opts;
        private String SendCarNo;
        private String DeptName;
        private String GroupName;
        private String UserName;
        private String OrderTime;
        private String UseCarTime;
        private String Entourage;
        private String Items;
        private String Destination;
        private String Reason;
        private String CarNo;
        private String Driver;
        private String ManagerConfirm;
        private String ConfirmTime;
        private String AdmChecked;
        private String CheckTime;
        private String SetOutTime;
        private String RetTime;
        private int RetMileage;
        private int Mileage;
        private String Remarks;
        private String Tel;
        private String Inspectedby;
        private String SetOutGuardby;
        private String RetGuardby;
        private String SenCarby;
        private String SenCarTime;
        private int CancelStatus;
        private String UserId;
        private String DeptId;
        private String GroupId;
        private String UserCarDept;
        private String UserCarGroup;
        private int Status;
        public boolean isSelected = false;
        private String CarStatus;
        private String CarName;
        private String PicturesUrl;

        public String getCarStatus() {
            return CarStatus;
        }

        public void setCarStatus(String carStatus) {
            CarStatus = carStatus;
        }

        public String getCarName() {
            return CarName;
        }

        public void setCarName(String carName) {
            CarName = carName;
        }

        public String getPicturesUrl() {
            return PicturesUrl;
        }

        public void setPicturesUrl(String picturesUrl) {
            PicturesUrl = picturesUrl;
        }

        public String getDate_flag() {
            return date_flag;
        }

        public void setDate_flag(String date_flag) {
            this.date_flag = date_flag;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public List<String> getOpts() {
            return Opts;
        }

        public void setOpts(List<String> opts) {
            Opts = opts;
        }

        public String getSendCarNo() {
            return SendCarNo;
        }

        public void setSendCarNo(String sendCarNo) {
            SendCarNo = sendCarNo;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String deptName) {
            DeptName = deptName;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String groupName) {
            GroupName = groupName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getOrderTime() {
            return OrderTime;
        }

        public void setOrderTime(String orderTime) {
            OrderTime = orderTime;
        }

        public String getUseCarTime() {
            return UseCarTime;
        }

        public void setUseCarTime(String useCarTime) {
            UseCarTime = useCarTime;
        }

        public String getEntourage() {
            return Entourage;
        }

        public void setEntourage(String entourage) {
            Entourage = entourage;
        }

        public String getItems() {
            return Items;
        }

        public void setItems(String items) {
            Items = items;
        }

        public String getDestination() {
            return Destination;
        }

        public void setDestination(String destination) {
            Destination = destination;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String reason) {
            Reason = reason;
        }

        public String getCarNo() {
            return CarNo;
        }

        public void setCarNo(String carNo) {
            CarNo = carNo;
        }

        public String getDriver() {
            return Driver;
        }

        public void setDriver(String driver) {
            Driver = driver;
        }

        public String getManagerConfirm() {
            return ManagerConfirm;
        }

        public void setManagerConfirm(String managerConfirm) {
            ManagerConfirm = managerConfirm;
        }

        public String getConfirmTime() {
            return ConfirmTime;
        }

        public void setConfirmTime(String confirmTime) {
            ConfirmTime = confirmTime;
        }

        public String getAdmChecked() {
            return AdmChecked;
        }

        public void setAdmChecked(String admChecked) {
            AdmChecked = admChecked;
        }

        public String getCheckTime() {
            return CheckTime;
        }

        public void setCheckTime(String checkTime) {
            CheckTime = checkTime;
        }

        public String getSetOutTime() {
            return SetOutTime;
        }

        public void setSetOutTime(String setOutTime) {
            SetOutTime = setOutTime;
        }

        public String getRetTime() {
            return RetTime;
        }

        public void setRetTime(String retTime) {
            RetTime = retTime;
        }

        public int getRetMileage() {
            return RetMileage;
        }

        public void setRetMileage(int retMileage) {
            RetMileage = retMileage;
        }

        public int getMileage() {
            return Mileage;
        }

        public void setMileage(int mileage) {
            Mileage = mileage;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String remarks) {
            Remarks = remarks;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public String getInspectedby() {
            return Inspectedby;
        }

        public void setInspectedby(String inspectedby) {
            Inspectedby = inspectedby;
        }

        public String getSetOutGuardby() {
            return SetOutGuardby;
        }

        public void setSetOutGuardby(String setOutGuardby) {
            SetOutGuardby = setOutGuardby;
        }

        public String getRetGuardby() {
            return RetGuardby;
        }

        public void setRetGuardby(String retGuardby) {
            RetGuardby = retGuardby;
        }

        public String getSenCarby() {
            return SenCarby;
        }

        public void setSenCarby(String senCarby) {
            SenCarby = senCarby;
        }

        public String getSenCarTime() {
            return SenCarTime;
        }

        public void setSenCarTime(String senCarTime) {
            SenCarTime = senCarTime;
        }

        public int getCancelStatus() {
            return CancelStatus;
        }

        public void setCancelStatus(int cancelStatus) {
            CancelStatus = cancelStatus;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getDeptId() {
            return DeptId;
        }

        public void setDeptId(String deptId) {
            DeptId = deptId;
        }

        public String getGroupId() {
            return GroupId;
        }

        public void setGroupId(String groupId) {
            GroupId = groupId;
        }

        public String getUserCarDept() {
            return UserCarDept;
        }

        public void setUserCarDept(String userCarDept) {
            UserCarDept = userCarDept;
        }

        public String getUserCarGroup() {
            return UserCarGroup;
        }

        public void setUserCarGroup(String userCarGroup) {
            UserCarGroup = userCarGroup;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }


}

