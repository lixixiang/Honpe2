package com.example.lxx.myalipay.ui.login.bean;

/**
 * created by lxx at 2019/11/28 15:44
 * 描述:
 */
public class UserCarBean {

    /**
     * data : {"address":"","email":"","joinTime":"2018-11-07 15:12:33","lang":"zh_CN","linkMan":"","linkPhone":"","name":"honpe","parentId":1375,"password":"BD96F9AB01A1A6C1F34D1286BEC970C1","rechargeURL":"","remark":"","subAlarm":true,"timeZoneSecond":28800,"token":"7524a67f-1e94-4f28-8b67-75452fea31ab","updateTime":"2019-01-25 16:35:01","userId":1345,"userName":"honpe","userType":2}
     * ret : 1
     */

    private DataBean data;
    private int ret;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public static class DataBean {
        /**
         * address :
         * email :
         * joinTime : 2018-11-07 15:12:33
         * lang : zh_CN
         * linkMan :
         * linkPhone :
         * name : honpe
         * parentId : 1375
         * password : BD96F9AB01A1A6C1F34D1286BEC970C1
         * rechargeURL :
         * remark :
         * subAlarm : true
         * timeZoneSecond : 28800
         * token : 7524a67f-1e94-4f28-8b67-75452fea31ab
         * updateTime : 2019-01-25 16:35:01
         * userId : 1345
         * userName : honpe
         * userType : 2
         */

        private String address;
        private String email;
        private String joinTime;
        private String lang;
        private String linkMan;
        private String linkPhone;
        private String name;
        private int parentId;
        private String password;
        private String rechargeURL;
        private String remark;
        private boolean subAlarm;
        private int timeZoneSecond;
        private String token;
        private String updateTime;
        private int userId;
        private String userName;
        private int userType;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getLinkMan() {
            return linkMan;
        }

        public void setLinkMan(String linkMan) {
            this.linkMan = linkMan;
        }

        public String getLinkPhone() {
            return linkPhone;
        }

        public void setLinkPhone(String linkPhone) {
            this.linkPhone = linkPhone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRechargeURL() {
            return rechargeURL;
        }

        public void setRechargeURL(String rechargeURL) {
            this.rechargeURL = rechargeURL;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isSubAlarm() {
            return subAlarm;
        }

        public void setSubAlarm(boolean subAlarm) {
            this.subAlarm = subAlarm;
        }

        public int getTimeZoneSecond() {
            return timeZoneSecond;
        }

        public void setTimeZoneSecond(int timeZoneSecond) {
            this.timeZoneSecond = timeZoneSecond;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }
    }
}

