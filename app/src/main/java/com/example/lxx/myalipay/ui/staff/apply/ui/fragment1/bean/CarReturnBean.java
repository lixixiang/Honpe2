package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean;

/**
 * created by lxx at 2019/11/28 14:51
 * 描述:往返
 */
public class CarReturnBean {
    private String CarNo;
    private String StartTime;
    private String EndTime;
    private int CarId;


    public int getCarId() {
        return CarId;
    }

    public void setCarId(int carId) {
        CarId = carId;
    }

    public String getCarNo() {
        return CarNo;
    }

    public void setCarNo(String carNo) {
        CarNo = carNo;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}
