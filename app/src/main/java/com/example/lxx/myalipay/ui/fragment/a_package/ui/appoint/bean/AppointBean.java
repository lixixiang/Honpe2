package com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.bean;

import java.io.Serializable;
import java.util.List;

/**
 * created by lxx at 2019/11/25 9:14
 * 描述:
 */
public class AppointBean implements Serializable {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"ID":"FK19040010","Visitor":"李熙祥","Company":"fcxxxx","CardIdNum":"fffff","Tel":"255 5555","Reason":"dbxhxhxh","VisitDate":"2019/4/16 0:00:00","VisitTime":"14:30-15:30","CarNo":"","EntourageNum":5,"StaffDept":"国内业务部","StaffName":"ddhxhxh","StaffTel":"","ReachTime":"","DepartureTime":"","UserCode":"LIXIXIANG1982","CodeType":0,"Guid":"774b20cb-d823-4f73-9bf1-3485a62d77ce","VDays":5,"ApplyTime":"2019-04-16T18:19:30","AuditorId":"","AuditorName":"","AuditTime":"","VState":-1,"PicUrl":""},{"ID":"FK19040009","Visitor":"李熙祥","Company":"dhxhxhxh","CardIdNum":"467676 7676 7976 7767","Tel":"164 6454 4875","Reason":"dbdhzhdhdh","VisitDate":"2019/4/16 0:00:00","VisitTime":"13:30-14:30","CarNo":"zbhzhxhhxh","EntourageNum":4,"StaffDept":"ID设计组","StaffName":"dhxhxhhx","StaffTel":"","ReachTime":"","DepartureTime":"","UserCode":"LIXIXIANG1982","CodeType":0,"Guid":"59cebe70-8da0-4c8c-92ac-9402a8bb0b3f","VDays":6,"ApplyTime":"2019-04-16T17:29:33.133","AuditorId":"","AuditorName":"","AuditTime":"","VState":-1,"PicUrl":""}]
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


    public static class DataBean implements Serializable{
        /**
         * ID : FK19040010
         * Visitor : 李熙祥
         * Company : fcxxxx
         * CardIdNum : fffff
         * Tel : 255 5555
         * Reason : dbxhxhxh
         * VisitDate : 2019/4/16 0:00:00
         * VisitTime : 14:30-15:30
         * CarNo :
         * EntourageNum : 5
         * StaffDept : 国内业务部
         * StaffName : ddhxhxh
         * StaffTel :
         * ReachTime :
         * DepartureTime :
         * UserCode : LIXIXIANG1982
         * CodeType : 0
         * Guid : 774b20cb-d823-4f73-9bf1-3485a62d77ce
         * VDays : 5
         * ApplyTime : 2019-04-16T18:19:30
         * AuditorId :
         * AuditorName :
         * AuditTime :
         * VState : -1
         * PicUrl :
         */

        private String ID;
        private String Visitor;
        private String Company;
        private String CardIdNum;
        private String Tel;
        private String Reason;
        private String VisitDate;
        private String VisitTime;
        private String CarNo;
        private int EntourageNum;
        private String StaffDept;
        private String StaffName;
        private String StaffTel;
        private String ReachTime;
        private String DepartureTime;
        private String UserCode;
        private int CodeType;
        private String Guid;
        private int VDays;
        private String ApplyTime;
        private String AuditorId;
        private String AuditorName;
        private String AuditTime;
        private int VState;
        private String PicUrl;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getVisitor() {
            return Visitor;
        }

        public void setVisitor(String Visitor) {
            this.Visitor = Visitor;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getCardIdNum() {
            return CardIdNum;
        }

        public void setCardIdNum(String CardIdNum) {
            this.CardIdNum = CardIdNum;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getVisitDate() {
            return VisitDate;
        }

        public void setVisitDate(String VisitDate) {
            this.VisitDate = VisitDate;
        }

        public String getVisitTime() {
            return VisitTime;
        }

        public void setVisitTime(String VisitTime) {
            this.VisitTime = VisitTime;
        }

        public String getCarNo() {
            return CarNo;
        }

        public void setCarNo(String CarNo) {
            this.CarNo = CarNo;
        }

        public int getEntourageNum() {
            return EntourageNum;
        }

        public void setEntourageNum(int EntourageNum) {
            this.EntourageNum = EntourageNum;
        }

        public String getStaffDept() {
            return StaffDept;
        }

        public void setStaffDept(String StaffDept) {
            this.StaffDept = StaffDept;
        }

        public String getStaffName() {
            return StaffName;
        }

        public void setStaffName(String StaffName) {
            this.StaffName = StaffName;
        }

        public String getStaffTel() {
            return StaffTel;
        }

        public void setStaffTel(String StaffTel) {
            this.StaffTel = StaffTel;
        }

        public String getReachTime() {
            return ReachTime;
        }

        public void setReachTime(String ReachTime) {
            this.ReachTime = ReachTime;
        }

        public String getDepartureTime() {
            return DepartureTime;
        }

        public void setDepartureTime(String DepartureTime) {
            this.DepartureTime = DepartureTime;
        }

        public String getUserCode() {
            return UserCode;
        }

        public void setUserCode(String UserCode) {
            this.UserCode = UserCode;
        }

        public int getCodeType() {
            return CodeType;
        }

        public void setCodeType(int CodeType) {
            this.CodeType = CodeType;
        }

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public int getVDays() {
            return VDays;
        }

        public void setVDays(int VDays) {
            this.VDays = VDays;
        }

        public String getApplyTime() {
            return ApplyTime;
        }

        public void setApplyTime(String ApplyTime) {
            this.ApplyTime = ApplyTime;
        }

        public String getAuditorId() {
            return AuditorId;
        }

        public void setAuditorId(String AuditorId) {
            this.AuditorId = AuditorId;
        }

        public String getAuditorName() {
            return AuditorName;
        }

        public void setAuditorName(String AuditorName) {
            this.AuditorName = AuditorName;
        }

        public String getAuditTime() {
            return AuditTime;
        }

        public void setAuditTime(String AuditTime) {
            this.AuditTime = AuditTime;
        }

        public int getVState() {
            return VState;
        }

        public void setVState(int VState) {
            this.VState = VState;
        }

        public String getPicUrl() {
            return PicUrl;
        }

        public void setPicUrl(String PicUrl) {
            this.PicUrl = PicUrl;
        }

    }
}

