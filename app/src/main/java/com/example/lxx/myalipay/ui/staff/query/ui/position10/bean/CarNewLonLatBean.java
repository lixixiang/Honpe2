package com.example.lxx.myalipay.ui.staff.query.ui.position10.bean;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position10.bean
 * 作者: lxx
 * 日期: 2019/2/16 12:03
 * 描述:
 */
public class CarNewLonLatBean {
    private double lon; //经度
    private double lat; //纬度
    private double latc;//偏移量纬度
    private double lonc;//偏移量经度

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLatc() {
        return latc;
    }

    public void setLatc(double latc) {
        this.latc = latc;
    }

    public double getLonc() {
        return lonc;
    }

    public void setLonc(double lonc) {
        this.lonc = lonc;
    }
}
