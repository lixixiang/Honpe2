package com.example.lxx.myalipay.ui.staff.query.ui.position16.bean;

import java.util.List;

/**
 * @ProjectName: Honpe2
 * @Package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.bean
 * @ClassName: StatisticsBean2
 * @Description: java类作用描述
 * @Author: 李熙祥
 * @CreateDate: 2018/12/5 17:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/5 17:24
 * @UpdateRemark: 更新说明
 */
public class StatisticsBean1 {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"MealDate":"2018-12-20","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":true,"HasDinner":true,"HasMidnight":true},{"MealDate":"2018-12-21","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":true,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-22","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":true,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-23","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-24","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":true,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-25","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-26","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-27","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":false,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-28","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":true,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-29","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-30","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-31","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false}]
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
         * MealDate : 2018-12-20
         * UserID : APPCS05
         * Week : null
         * HasBreakFast : true
         * HasLunch : true
         * HasDinner : true
         * HasMidnight : true
         */

        private String MealDate;
        private String UserID;
        private Object Week;
        private boolean HasBreakFast;
        private boolean HasLunch;
        private boolean HasDinner;
        private boolean HasMidnight;
        private boolean HasHorizontal;
        private boolean HasVertical;

        public DataBean() {
        }

        public DataBean(String mealDate, String userID, Object week, boolean hasBreakFast, boolean hasLunch, boolean hasDinner, boolean hasMidnight) {
            MealDate = mealDate;
            UserID = userID;
            Week = week;
            HasBreakFast = hasBreakFast;
            HasLunch = hasLunch;
            HasDinner = hasDinner;
            HasMidnight = hasMidnight;
        }

        public boolean isHasHorizontal() {
            return HasHorizontal;
        }

        public void setHasHorizontal(boolean hasHorizontal) {
            HasHorizontal = hasHorizontal;
        }

        public boolean isHasVertical() {
            return HasVertical;
        }

        public void setHasVertical(boolean hasVertical) {
            HasVertical = hasVertical;
        }

        public String getMealDate() {
            return MealDate;
        }

        public void setMealDate(String MealDate) {
            this.MealDate = MealDate;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = "";
        }

        public Object getWeek() {
            return Week;
        }

        public void setWeek(Object Week) {
            this.Week = Week;
        }

        public boolean isHasBreakFast() {
            return HasBreakFast;
        }

        public void setHasBreakFast(boolean HasBreakFast) {
            this.HasBreakFast = HasBreakFast;
        }

        public boolean isHasLunch() {
            return HasLunch;
        }

        public void setHasLunch(boolean HasLunch) {
            this.HasLunch = HasLunch;
        }

        public boolean isHasDinner() {
            return HasDinner;
        }

        public void setHasDinner(boolean HasDinner) {
            this.HasDinner = HasDinner;
        }

        public boolean isHasMidnight() {
            return HasMidnight;
        }

        public void setHasMidnight(boolean HasMidnight) {
            this.HasMidnight = HasMidnight;
        }
    }
}
