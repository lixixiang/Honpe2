package com.example.lxx.myalipay.ui.staff.query.ui.position6.bean;

import java.util.List;

/**
 * created by lxx at 2019/12/3 14:42
 * 描述:
 */
public class CardQueryBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"F_ID":"08749062-747d-4aab-a7d7-e3ac1c7a8ed0","F_EmpNo":"11111","F_Reason":"忘记打卡","F_BkTime":"2019-04-29 18:29","F_CreateUserId":"APPCS05","F_CreateUserName":"App测试用户05","F_CreateDate":"2019-04-29T14:30:34.657","F_Type":4,"F_State":-1,"F_AuditorId":"","F_AuditorName":"","F_AuditTime":"","F_AuditRemarks":""},{"F_ID":"0acbbaf2-7acc-41f8-b876-c2afd771ecf3","F_EmpNo":"11111","F_Reason":"测试结果","F_BkTime":"2019-04-29 11:38","F_CreateUserId":"APPCS05","F_CreateUserName":"App测试用户05","F_CreateDate":"2019-04-29T11:38:47.707","F_Type":4,"F_State":-1,"F_AuditorId":"","F_AuditorName":"","F_AuditTime":"","F_AuditRemarks":""},{"F_ID":"3b08677e-2cc5-4bb5-a8d8-a08083d1ee11","F_EmpNo":"11111","F_Reason":"测试","F_BkTime":"2019-04-29 09:25","F_CreateUserId":"APPCS05","F_CreateUserName":"App测试用户05","F_CreateDate":"2019-04-29T09:26:08.437","F_Type":4,"F_State":-1,"F_AuditorId":"","F_AuditorName":"","F_AuditTime":"","F_AuditRemarks":""},{"F_ID":"8e55ac3d-ec27-45a2-9ab1-1f6bb17e260d","F_EmpNo":"11111","F_Reason":"于","F_BkTime":"2019-04-29 14:24","F_CreateUserId":"APPCS05","F_CreateUserName":"App测试用户05","F_CreateDate":"2019-04-29T14:26:39.043","F_Type":2,"F_State":-1,"F_AuditorId":"","F_AuditorName":"","F_AuditTime":"","F_AuditRemarks":""}]
     */

    private int Status;
    private String Msg;
    private String ImgUrl;
    private List<DataBean> Data;

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
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

    public static class DataBean {
        /**
         * F_ID : 08749062-747d-4aab-a7d7-e3ac1c7a8ed0
         * F_EmpNo : 11111
         * F_Reason : 忘记打卡
         * F_BkTime : 2019-04-29 18:29
         * F_CreateUserId : APPCS05
         * F_CreateUserName : App测试用户05
         * F_CreateDate : 2019-04-29T14:30:34.657
         * F_Type : 4
         * F_State : -1
         * F_AuditorId :
         * F_AuditorName :
         * F_AuditTime :
         * F_AuditRemarks :
         */

        private String F_ID;
        private String F_EmpNo;
        private String F_Reason;
        private String F_BkTime;
        private String F_CreateUserId;
        private String F_CreateUserName;
        private String F_CreateDate;
        private int F_Type;
        private int F_State;
        private String F_AuditorId;
        private String F_AuditorName;
        private String F_AuditTime;
        private String F_AuditRemarks;
        private String PicUrl;

        public String getPicUrl() {
            return PicUrl;
        }

        public void setPicUrl(String picUrl) {
            PicUrl = picUrl;
        }

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String F_ID) {
            this.F_ID = F_ID;
        }

        public String getF_EmpNo() {
            return F_EmpNo;
        }

        public void setF_EmpNo(String F_EmpNo) {
            this.F_EmpNo = F_EmpNo;
        }

        public String getF_Reason() {
            return F_Reason;
        }

        public void setF_Reason(String F_Reason) {
            this.F_Reason = F_Reason;
        }

        public String getF_BkTime() {
            return F_BkTime;
        }

        public void setF_BkTime(String F_BkTime) {
            this.F_BkTime = F_BkTime;
        }

        public String getF_CreateUserId() {
            return F_CreateUserId;
        }

        public void setF_CreateUserId(String F_CreateUserId) {
            this.F_CreateUserId = F_CreateUserId;
        }

        public String getF_CreateUserName() {
            return F_CreateUserName;
        }

        public void setF_CreateUserName(String F_CreateUserName) {
            this.F_CreateUserName = F_CreateUserName;
        }

        public String getF_CreateDate() {
            return F_CreateDate;
        }

        public void setF_CreateDate(String F_CreateDate) {
            this.F_CreateDate = F_CreateDate;
        }

        public int getF_Type() {
            return F_Type;
        }

        public void setF_Type(int F_Type) {
            this.F_Type = F_Type;
        }

        public int getF_State() {
            return F_State;
        }

        public void setF_State(int F_State) {
            this.F_State = F_State;
        }

        public String getF_AuditorId() {
            return F_AuditorId;
        }

        public void setF_AuditorId(String F_AuditorId) {
            this.F_AuditorId = F_AuditorId;
        }

        public String getF_AuditorName() {
            return F_AuditorName;
        }

        public void setF_AuditorName(String F_AuditorName) {
            this.F_AuditorName = F_AuditorName;
        }

        public String getF_AuditTime() {
            return F_AuditTime;
        }

        public void setF_AuditTime(String F_AuditTime) {
            this.F_AuditTime = F_AuditTime;
        }

        public String getF_AuditRemarks() {
            return F_AuditRemarks;
        }

        public void setF_AuditRemarks(String F_AuditRemarks) {
            this.F_AuditRemarks = F_AuditRemarks;
        }
    }
}

