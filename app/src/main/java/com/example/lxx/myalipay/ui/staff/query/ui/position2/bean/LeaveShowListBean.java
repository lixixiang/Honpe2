package com.example.lxx.myalipay.ui.staff.query.ui.position2.bean;

import java.util.List;

/**
 * created by lxx at 2019/12/3 11:34
 * 描述:请假显示列表
 */
public class LeaveShowListBean {
    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"Id":"L190529005","DeptName":"研发部","GroupName":"研发组","SqTime":"2019-05-29 09:12","UserName":"李熙祥","Type":"出差","StartTime":"2019-05-29 04:09","EndTime":"2019-05-29 08:09","LeaveTime":3,"Reason":"测试","State":"未处理","ZgApv":"","ZgApvTime":"                ","ZgResult":"","ZgOpinion":"","ZjApv":"","ZjApvTime":"                ","ZjResult":"","ZjOpinion":"","GmApv":"","GmApvTime":"                ","GmResult":"","GmOpinion":"","UserId":"LIXIXIANG1982","Destination":"广东省深圳市宝安区 测试","Ltype":1,"IsCancel":0},{"Id":"L190529006","DeptName":"研发部","GroupName":"研发组","SqTime":"2019-05-29 09:35","UserName":"李熙祥","Type":"出差","StartTime":"2019-05-29 09:34","EndTime":"2019-05-29 17:34","LeaveTime":8,"Reason":"去玩","State":"未处理","ZgApv":"","ZgApvTime":"                ","ZgResult":"","ZgOpinion":"","ZjApv":"","ZjApvTime":"                ","ZjResult":"","ZjOpinion":"","GmApv":"","GmApvTime":"                ","GmResult":"","GmOpinion":"","UserId":"LIXIXIANG1982","Destination":"广东省深圳市宝安区 世界之窗","Ltype":1,"IsCancel":0},{"Id":"L190529007","DeptName":"研发部","GroupName":"研发组","SqTime":"2019-05-29 11:25","UserName":"李熙祥","Type":"出差","StartTime":"2019-05-30 08:23","EndTime":"2019-05-30 13:26","LeaveTime":4,"Reason":"测30号","State":"未处理","ZgApv":"","ZgApvTime":"                ","ZgResult":"","ZgOpinion":"","ZjApv":"","ZjApvTime":"                ","ZjResult":"","ZjOpinion":"","GmApv":"","GmApvTime":"                ","GmResult":"","GmOpinion":"","UserId":"LIXIXIANG1982","Destination":"广东省深圳市宝安区 测试30号当天数据","Ltype":1,"IsCancel":0}]
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
         * Id : L190529005
         * DeptName : 研发部
         * GroupName : 研发组
         * SqTime : 2019-05-29 09:12
         * UserName : 李熙祥
         * Type : 出差
         * StartTime : 2019-05-29 04:09
         * EndTime : 2019-05-29 08:09
         * LeaveTime : 3.0
         * Reason : 测试
         * State : 未处理
         * ZgApv :
         * ZgApvTime :
         * ZgResult :
         * ZgOpinion :
         * ZjApv :
         * ZjApvTime :
         * ZjResult :
         * ZjOpinion :
         * GmApv :
         * GmApvTime :
         * GmResult :
         * GmOpinion :
         * UserId : LIXIXIANG1982
         * Destination : 广东省深圳市宝安区 测试
         * Ltype : 1
         * IsCancel : 0
         */

        private String Id;
        private String DeptName;
        private String GroupName;
        private String SqTime;
        private String UserName;
        private String Type;
        private String StartTime;
        private String EndTime;
        private double LeaveTime;
        private String Reason;
        private String State;
        private String ZgApv;
        private String ZgApvTime;
        private String ZgResult;
        private String ZgOpinion;
        private String ZjApv;
        private String ZjApvTime;
        private String ZjResult;
        private String ZjOpinion;
        private String GmApv;
        private String GmApvTime;
        private String GmResult;
        private String GmOpinion;
        private String UserId;
        private String Destination;
        private int Ltype;
        private int IsCancel;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
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

        public String getSqTime() {
            return SqTime;
        }

        public void setSqTime(String SqTime) {
            this.SqTime = SqTime;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public double getLeaveTime() {
            return LeaveTime;
        }

        public void setLeaveTime(double LeaveTime) {
            this.LeaveTime = LeaveTime;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getZgApv() {
            return ZgApv;
        }

        public void setZgApv(String ZgApv) {
            this.ZgApv = ZgApv;
        }

        public String getZgApvTime() {
            return ZgApvTime;
        }

        public void setZgApvTime(String ZgApvTime) {
            this.ZgApvTime = ZgApvTime;
        }

        public String getZgResult() {
            return ZgResult;
        }

        public void setZgResult(String ZgResult) {
            this.ZgResult = ZgResult;
        }

        public String getZgOpinion() {
            return ZgOpinion;
        }

        public void setZgOpinion(String ZgOpinion) {
            this.ZgOpinion = ZgOpinion;
        }

        public String getZjApv() {
            return ZjApv;
        }

        public void setZjApv(String ZjApv) {
            this.ZjApv = ZjApv;
        }

        public String getZjApvTime() {
            return ZjApvTime;
        }

        public void setZjApvTime(String ZjApvTime) {
            this.ZjApvTime = ZjApvTime;
        }

        public String getZjResult() {
            return ZjResult;
        }

        public void setZjResult(String ZjResult) {
            this.ZjResult = ZjResult;
        }

        public String getZjOpinion() {
            return ZjOpinion;
        }

        public void setZjOpinion(String ZjOpinion) {
            this.ZjOpinion = ZjOpinion;
        }

        public String getGmApv() {
            return GmApv;
        }

        public void setGmApv(String GmApv) {
            this.GmApv = GmApv;
        }

        public String getGmApvTime() {
            return GmApvTime;
        }

        public void setGmApvTime(String GmApvTime) {
            this.GmApvTime = GmApvTime;
        }

        public String getGmResult() {
            return GmResult;
        }

        public void setGmResult(String GmResult) {
            this.GmResult = GmResult;
        }

        public String getGmOpinion() {
            return GmOpinion;
        }

        public void setGmOpinion(String GmOpinion) {
            this.GmOpinion = GmOpinion;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getDestination() {
            return Destination;
        }

        public void setDestination(String Destination) {
            this.Destination = Destination;
        }

        public int getLtype() {
            return Ltype;
        }

        public void setLtype(int Ltype) {
            this.Ltype = Ltype;
        }

        public int getIsCancel() {
            return IsCancel;
        }

        public void setIsCancel(int IsCancel) {
            this.IsCancel = IsCancel;
        }
    }
}

