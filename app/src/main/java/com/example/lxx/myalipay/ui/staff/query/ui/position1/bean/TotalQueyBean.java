package com.example.lxx.myalipay.ui.staff.query.ui.position1.bean;

import java.util.List;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position1.bean
 * 作者: lxx
 * 日期: 2019/1/24 14:55
 * 描述:
 */
public class TotalQueyBean {
    /**
     * Status : 0
     * Msg : 成功！
     * Data : [{"Id":"136920190123080432","EnrollNumber":"1369","RecorderTime":"08:04:32","Flag":0},{"Id":"136920190123121400","EnrollNumber":"1369","RecorderTime":"12:14:00","Flag":0},{"Id":"136920190123123037","EnrollNumber":"1369","RecorderTime":"12:30:37","Flag":0},{"Id":"136920190123190510","EnrollNumber":"1369","RecorderTime":"19:05:10","Flag":0}]
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
         * Id : 136920190123080432
         * EnrollNumber : 1369
         * RecorderTime : 08:04:32
         * Flag : 0
         */

        private String Id;
        private String EnrollNumber;
        private String RecorderTime;
        private String status;
        private int Flag;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getEnrollNumber() {
            return EnrollNumber;
        }

        public void setEnrollNumber(String EnrollNumber) {
            this.EnrollNumber = EnrollNumber;
        }

        public String getRecorderTime() {
            return RecorderTime;
        }

        public void setRecorderTime(String RecorderTime) {
            this.RecorderTime = RecorderTime;
        }

        public int getFlag() {
            return Flag;
        }

        public void setFlag(int Flag) {
            this.Flag = Flag;
        }
    }
}
