package com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean;

import java.util.List;

/**
 * created by lxx at 2019/12/1 15:36
 * 描述:选择车辆
 */
public class SelectCarBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"Status":"使用中","CarNo":"粤B3GU68"},{"Status":"使用中","CarNo":"粤B9B75L"},{"Status":"使用中","CarNo":"粤B7KJ58"},{"Status":"使用中","CarNo":"粤BQ7J66"},{"Status":"闲置中","CarNo":"粤BC76M6"},{"Status":"闲置中","CarNo":"粤B7449Q"},{"Status":"闲置中","CarNo":"粤B2KF21"},{"Status":"闲置中","CarNo":"粤B8D521"},{"Status":"闲置中","CarNo":"滴滴打车"},{"Status":"闲置中","CarNo":"拼车"},{"Status":"闲置中","CarNo":"粤B8J46X"},{"Status":"闲置中","CarNo":"货拉拉"},{"Status":"闲置中","CarNo":"粤B73T70"},{"Status":"检修中","CarNo":"京NHP133"}]
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
         * Status : 使用中
         * CarNo : 粤B3GU68
         */

        private String Status;
        private String CarNo;

        public DataBean(String status, String carNo) {
            Status = status;
            CarNo = carNo;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getCarNo() {
            return CarNo;
        }

        public void setCarNo(String CarNo) {
            this.CarNo = CarNo;
        }
    }
}


