package com.example.lxx.myalipay.ui.staff.query.ui.position16.Fragment3Priase;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.Fragment3Priase
 * 作者: lxx
 * 日期: 2019/1/21 18:07
 * 描述:
 */
public class LikeBean {
    private int switchLike;
    private String strPosition;
    public LikeBean(int switchLike,String pos) {
        this.switchLike = switchLike;
        this.strPosition = pos;
    }


    public String getStrPosition() {
        return strPosition;
    }

    public void setStrPosition(String strPosition) {
        this.strPosition = strPosition;
    }

    public int getSwitchLike() {
        return switchLike;
    }

    public void setSwitchLike(int switchLike) {
        this.switchLike = switchLike;
    }
}
