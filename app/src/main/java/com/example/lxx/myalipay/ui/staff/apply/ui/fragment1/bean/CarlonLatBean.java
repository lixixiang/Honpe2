package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean;

import java.util.List;

/**
 * created by lxx at 2019/11/28 14:57
 * 描述:
 */
public class CarlonLatBean {

    /**
     * data : [{"alarm":"","altitude":0,"dir":300,"exData":"s=0;st=1552356288","imei":"780218020205989","isStop":true,"lat":22.688856,"latc":22.691814,"lon":113.840472,"lonc":113.851983,"mileage":123,"pointDt":"2019-04-02 06:10:28","pointType":1,"remark":"","signalMile":123,"speed":8,"status":"","stopTime":1215},{"alarm":"","altitude":0,"dir":218,"exData":"s=0;st=1552356288","imei":"780218020205989","isStop":false,"lat":22.688308,"latc":22.691258,"lon":113.839937,"lonc":113.851451,"mileage":204,"pointDt":"2019-04-02 06:10:43","pointType":1,"remark":"","signalMile":81,"speed":22,"status":"","stopTime":15}]
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
         * alarm :
         * altitude : 0
         * dir : 300
         * exData : s=0;st=1552356288
         * imei : 780218020205989
         * isStop : true
         * lat : 22.688856
         * latc : 22.691814
         * lon : 113.840472
         * lonc : 113.851983
         * mileage : 123
         * pointDt : 2019-04-02 06:10:28
         * pointType : 1
         * remark :
         * signalMile : 123
         * speed : 8
         * status :
         * stopTime : 1215
         */

        private String alarm;
        private int altitude;
        private int dir;
        private String exData;
        private String imei;
        private boolean isStop;
        private double lat;
        private double latc;
        private double lon;
        private double lonc;
        private int mileage;
        private String pointDt;
        private int pointType;
        private String remark;
        private int signalMile;
        private int speed;
        private String status;
        private int stopTime;

        public String getAlarm() {
            return alarm;
        }

        public void setAlarm(String alarm) {
            this.alarm = alarm;
        }

        public int getAltitude() {
            return altitude;
        }

        public void setAltitude(int altitude) {
            this.altitude = altitude;
        }

        public int getDir() {
            return dir;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }

        public String getExData() {
            return exData;
        }

        public void setExData(String exData) {
            this.exData = exData;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public boolean isIsStop() {
            return isStop;
        }

        public void setIsStop(boolean isStop) {
            this.isStop = isStop;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLatc() {
            return latc;
        }

        public void setLatc(double latc) {
            this.latc = latc;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLonc() {
            return lonc;
        }

        public void setLonc(double lonc) {
            this.lonc = lonc;
        }

        public int getMileage() {
            return mileage;
        }

        public void setMileage(int mileage) {
            this.mileage = mileage;
        }

        public String getPointDt() {
            return pointDt;
        }

        public void setPointDt(String pointDt) {
            this.pointDt = pointDt;
        }

        public int getPointType() {
            return pointType;
        }

        public void setPointType(int pointType) {
            this.pointType = pointType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSignalMile() {
            return signalMile;
        }

        public void setSignalMile(int signalMile) {
            this.signalMile = signalMile;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStopTime() {
            return stopTime;
        }

        public void setStopTime(int stopTime) {
            this.stopTime = stopTime;
        }
    }
}

