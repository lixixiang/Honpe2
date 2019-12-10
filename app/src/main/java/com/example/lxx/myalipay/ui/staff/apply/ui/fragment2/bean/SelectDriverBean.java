package com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean;

import java.util.List;

/**
 * created by lxx at 2019/12/1 15:37
 * 描述:
 */
public class SelectDriverBean {
    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"Seq":1,"DriverName":"杨雨薛"},{"Seq":2,"DriverName":"官海锋"},{"Seq":3,"DriverName":"刘洋"},{"Seq":4,"DriverName":"刘勇"},{"Seq":5,"DriverName":"卢波"},{"Seq":6,"DriverName":"贺智辉"},{"Seq":7,"DriverName":"吴成忠"},{"Seq":8,"DriverName":"韦苏娜"},{"Seq":9,"DriverName":"钟德邦"},{"Seq":10,"DriverName":"陈敏"},{"Seq":11,"DriverName":"刘宏亮"},{"Seq":12,"DriverName":"员工驾驶"},{"Seq":13,"DriverName":"王晓如"},{"Seq":14,"DriverName":"杜林"},{"Seq":15,"DriverName":"俞继铭"},{"Seq":16,"DriverName":"邹恒星"},{"Seq":17,"DriverName":"潘家俊"},{"Seq":18,"DriverName":"杜耿峰"},{"Seq":19,"DriverName":"肖卫"},{"Seq":20,"DriverName":"葛廷武"}]
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

    public static class DataBean  {
        /**
         * Seq : 1
         * DriverName : 杨雨薛
         */

        private int Seq;
        private String DriverName;

        public DataBean(int seq, String driverName) {
            Seq = seq;
            DriverName = driverName;
        }

        public int getSeq() {
            return Seq;
        }

        public void setSeq(int Seq) {
            this.Seq = Seq;
        }

        public String getDriverName() {
            return DriverName;
        }

        public void setDriverName(String DriverName) {
            this.DriverName = DriverName;
        }


    }
}


