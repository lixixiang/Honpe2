package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean;

import java.util.List;

/**
 * created by lxx at 2019/11/28 14:50
 * 描述:
 */
public class CarMapInfoBean {

    /**
     * data : [{"activeTime":"2019-01-25","carId":102856,"carNO":"WhatsGPS-203901","carType":3,"driverName":"","driverTel":"","imei":"780218020203901","joinTime":"2018-05-18","machineName":"WhatsGPS-203901","machineType":5,"password":"1398759FDE5B78EDC4701C4745D53531","platformTime":"2020-02-01","remark":"","serviceState":0,"serviceTime":"2020-02-01","simNO":"1440066841298","updateTime":"2019-01-25","userId":1345},{"activeTime":"2019-01-25","carId":102892,"carNO":"WhatsGPS-205393","carType":3,"driverName":"","driverTel":"","imei":"780218020205393","joinTime":"2018-05-18","machineName":"粤B-8J46X","machineType":5,"password":"E76523DC4EAF4D9AA678F147D0783490","platformTime":"2020-02-01","remark":"","serviceState":0,"serviceTime":"2020-02-01","simNO":"1440066840308","updateTime":"2019-01-25","userId":1345},{"activeTime":"2019-01-25","carId":102911,"carNO":"WhatsGPS-205989","carType":3,"driverName":"1440066841477","driverTel":"","imei":"780218020205989","joinTime":"2018-05-18","machineName":"WhatsGPS-205989","machineType":5,"password":"2EDA5A88FA5D97B1161E6E7B1BBE28DD","platformTime":"2020-02-01","remark":"","serviceState":0,"serviceTime":"2020-02-01","simNO":"","updateTime":"2019-01-25","userId":1345},{"activeTime":"2019-01-25","carId":102925,"carNO":"WhatsGPS-206656","carType":3,"driverName":"","driverTel":"","imei":"780218020206656","joinTime":"2018-05-18","machineName":"WhatsGPS-206656","machineType":5,"password":"B8D8177768E5CFA0FDD83FF41CA866B0","platformTime":"2020-02-01","remark":"","serviceState":0,"serviceTime":"2020-02-01","simNO":"1440066841132","updateTime":"2019-01-25","userId":1345},{"activeTime":"2019-01-25","carId":102932,"carNO":"WhatsGPS-207688","carType":3,"driverName":"","driverTel":"","imei":"780218020207688","joinTime":"2018-05-18","machineName":"WhatsGPS-207688","machineType":5,"password":"BF88657B07871FEE3D0E4326BF483B8E","platformTime":"2020-02-01","remark":"","serviceState":0,"serviceTime":"2020-02-01","simNO":"1440066840061","updateTime":"2019-01-25","userId":1345},{"activeTime":"2019-01-25","carId":102960,"carNO":"WhatsGPS-209494","carType":3,"driverName":"","driverTel":"","imei":"780218020209494","joinTime":"2018-05-18","machineName":"WhatsGPS-209494","machineType":5,"password":"4B7E9090493EEC64C542212065D00BAC","platformTime":"2020-02-01","remark":"","serviceState":0,"serviceTime":"2020-02-01","simNO":"1440066840700","updateTime":"2019-01-25","userId":1345},{"activeTime":"2019-01-25","carId":103028,"carNO":"WhatsGPS-213181","carType":3,"driverName":"","driverTel":"","imei":"780218020213181","joinTime":"2018-05-18","machineName":"WhatsGPS-213181","machineType":5,"password":"17E26B241187DCBFE0115B119AFF76B6","platformTime":"2020-02-01","remark":"","serviceState":0,"serviceTime":"2020-02-01","simNO":"1440066841852","updateTime":"2019-01-25","userId":1345},{"activeTime":"2019-01-25","carId":103092,"carNO":"WhatsGPS-214395","carType":3,"driverName":"","driverTel":"","imei":"780218020214395","joinTime":"2018-05-18","machineName":"WhatsGPS-214395","machineType":5,"password":"ED80C5716D534376000B1FC615F4518F","platformTime":"2020-02-01","remark":"","serviceState":0,"serviceTime":"2020-02-01","simNO":"1440066840051","updateTime":"2019-01-25","userId":1345}]
     * ret : 1
     */

    private int ret;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * activeTime : 2019-01-25
         * carId : 102856
         * carNO : WhatsGPS-203901
         * carType : 3
         * driverName :
         * driverTel :
         * imei : 780218020203901
         * joinTime : 2018-05-18
         * machineName : WhatsGPS-203901
         * machineType : 5
         * password : 1398759FDE5B78EDC4701C4745D53531
         * platformTime : 2020-02-01
         * remark :
         * serviceState : 0
         * serviceTime : 2020-02-01
         * simNO : 1440066841298
         * updateTime : 2019-01-25
         * userId : 1345
         */

        private String activeTime;
        private int carId;
        private String carNO;
        private int carType;
        private String driverName;
        private String driverTel;
        private String imei;
        private String joinTime;
        private String machineName;
        private int machineType;
        private String password;
        private String platformTime;
        private String remark;
        private int serviceState;
        private String serviceTime;
        private String simNO;
        private String updateTime;
        private int userId;

        public String getActiveTime() {
            return activeTime;
        }

        public void setActiveTime(String activeTime) {
            this.activeTime = activeTime;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getCarNO() {
            return carNO;
        }

        public void setCarNO(String carNO) {
            this.carNO = carNO;
        }

        public int getCarType() {
            return carType;
        }

        public void setCarType(int carType) {
            this.carType = carType;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverTel() {
            return driverTel;
        }

        public void setDriverTel(String driverTel) {
            this.driverTel = driverTel;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }

        public String getMachineName() {
            return machineName;
        }

        public void setMachineName(String machineName) {
            this.machineName = machineName;
        }

        public int getMachineType() {
            return machineType;
        }

        public void setMachineType(int machineType) {
            this.machineType = machineType;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPlatformTime() {
            return platformTime;
        }

        public void setPlatformTime(String platformTime) {
            this.platformTime = platformTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getServiceState() {
            return serviceState;
        }

        public void setServiceState(int serviceState) {
            this.serviceState = serviceState;
        }

        public String getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }

        public String getSimNO() {
            return simNO;
        }

        public void setSimNO(String simNO) {
            this.simNO = simNO;
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
    }
}

