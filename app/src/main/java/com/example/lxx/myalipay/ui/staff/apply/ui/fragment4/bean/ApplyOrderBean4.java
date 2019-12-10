package com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.bean;

import java.util.List;

/**
 * created by lxx at 2019/11/29 11:25
 * 描述:
 */
public class ApplyOrderBean4 {
    /**
     * MealsDate : 2019-01-20
     * MealType : 晚餐
     * MealTimer : 17:30-18:00
     * MealsNumber : 3
     * Notes : 3个广东客户吃饭
     * FoodAmount : 2
     * details : [{"FoodStyle":"潮州菜","FoodName":"香炸芋泥卷 ","FoodNum":1},{"FoodStyle":"潮州菜","FoodName":"蚝仔烙","FoodNum":1}]
     */
    private String OrderNo;
    private String MealsDate;
    private String MealType;
    private String MealTimer;
    private String MealsNumber;
    private String Notes;
    private int FoodAmount;
    private List<DetailsBean> details;

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getMealsDate() {
        return MealsDate;
    }

    public void setMealsDate(String MealsDate) {
        this.MealsDate = MealsDate;
    }

    public String getMealType() {
        return MealType;
    }

    public void setMealType(String MealType) {
        this.MealType = MealType;
    }

    public String getMealTimer() {
        return MealTimer;
    }

    public void setMealTimer(String MealTimer) {
        this.MealTimer = MealTimer;
    }

    public String getMealsNumber() {
        return MealsNumber;
    }

    public void setMealsNumber(String MealsNumber) {
        this.MealsNumber = MealsNumber;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public int getFoodAmount() {
        return FoodAmount;
    }

    public void setFoodAmount(int FoodAmount) {
        this.FoodAmount = FoodAmount;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * FoodStyle : 潮州菜
         * FoodName : 香炸芋泥卷
         * FoodNum : 1
         */

        private String FoodStyle;
        private String FoodName;
        private int FoodNum;
        private String FoodCode;
        private int CanteenConfirm;

        public DetailsBean() {
        }

        public DetailsBean(String foodStyle, String foodName, int foodNum) {
            FoodStyle = foodStyle;
            FoodName = foodName;
            FoodNum = foodNum;
        }

        public DetailsBean(String foodStyle, String foodName, int foodNum, String foodCode, int canteenConfirm) {
            FoodStyle = foodStyle;
            FoodName = foodName;
            FoodNum = foodNum;
            FoodCode = foodCode;
            CanteenConfirm = canteenConfirm;
        }

        public String getFoodCode() {
            return FoodCode;
        }

        public void setFoodCode(String foodCode) {
            FoodCode = foodCode;
        }

        public int getCanteenConfirm() {
            return CanteenConfirm;
        }

        public void setCanteenConfirm(int canteenConfirm) {
            CanteenConfirm = canteenConfirm;
        }

        public String getFoodStyle() {
            return FoodStyle;
        }

        public void setFoodStyle(String FoodStyle) {
            this.FoodStyle = FoodStyle;
        }

        public String getFoodName() {
            return FoodName;
        }

        public void setFoodName(String FoodName) {
            this.FoodName = FoodName;
        }

        public int getFoodNum() {
            return FoodNum;
        }

        public void setFoodNum(int FoodNum) {
            this.FoodNum = FoodNum;
        }


    }
}

