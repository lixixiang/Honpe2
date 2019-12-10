package com.example.lxx.myalipay.ui.login.bean;

/**
 * created by lxx at 2019/11/22 14:13
 * 描述:登录实体类
 */
public class LoginBean {
    /**
     * SessionKey : 752e5eb9a53c62fa60a2cecdb12d1a3c
     * LogonUser : {"UserName":"lxx","UserCode":"xxx","KeyValue":"752e5eb9a53c62fa60a2cecdb12d1a3c","UserPwd":"","Language":"en","IsActive":true,"EmployeeId":"","Ico":"http://api.honpe.com:8079/HeadPic/2017-08-05/20170805165355835_1.png"}
     * Status : 0
     * Msg : 登陆成功!
     */

    private String SessionKey;
    private LogonUserBean LogonUser;
    private int Status;
    private String Msg;

    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String SessionKey) {
        this.SessionKey = SessionKey;
    }

    public LogonUserBean getLogonUser() {
        return LogonUser;
    }

    public void setLogonUser(LogonUserBean LogonUser) {
        this.LogonUser = LogonUser;
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

    public static class LogonUserBean {
        /**
         * UserName : lxx
         * UserCode : xxx
         * KeyValue : 752e5eb9a53c62fa60a2cecdb12d1a3c
         * UserPwd :
         * Language : en
         * IsActive : true
         * EmployeeId :
         * Ico : http://api.honpe.com:8079/HeadPic/2017-08-05/20170805165355835_1.png
         */

        private String UserName;
        private String UserCode;
        private String KeyValue;
        private String UserPwd;
        private String Language;
        private boolean IsActive;
        private String EmployeeId;
        private String Ico;
        private String InSpeechBg;
        private int UserType;

        public LogonUserBean(String userName,
                             String keyValue,
                             String language,
                             String ico,
                             String inSpeechBg,
                             int userType) {
            UserName = userName;
            KeyValue = keyValue;
            Language = language;
            Ico = ico;
            InSpeechBg = inSpeechBg;
            UserType = userType;
        }

        public int getUserType() {
            return UserType;
        }

        public void setUserType(int userType) {
            UserType = userType;
        }

        public String getInSpeechBg() {
            return InSpeechBg;
        }

        public void setInSpeechBg(String inSpeechBg) {
            InSpeechBg = inSpeechBg;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserCode() {
            return UserCode;
        }

        public void setUserCode(String UserCode) {
            this.UserCode = UserCode;
        }

        public String getKeyValue() {
            return KeyValue;
        }

        public void setKeyValue(String KeyValue) {
            this.KeyValue = KeyValue;
        }

        public String getUserPwd() {
            return UserPwd;
        }

        public void setUserPwd(String UserPwd) {
            this.UserPwd = UserPwd;
        }

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String Language) {
            this.Language = Language;
        }

        public boolean isIsActive() {
            return IsActive;
        }

        public void setIsActive(boolean IsActive) {
            this.IsActive = IsActive;
        }

        public String getEmployeeId() {
            return EmployeeId;
        }

        public void setEmployeeId(String EmployeeId) {
            this.EmployeeId = EmployeeId;
        }

        public String getIco() {
            return Ico;
        }

        public void setIco(String Ico) {
            this.Ico = Ico;
        }
    }
}

