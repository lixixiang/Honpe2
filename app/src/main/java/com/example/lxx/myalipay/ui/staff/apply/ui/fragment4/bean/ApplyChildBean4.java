package com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * created by lxx at 2019/11/29 10:29
 * 描述:
 */
public class ApplyChildBean4 implements Serializable {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"OrderNo":"DC190522004","OrderTimer":"2019-05-22 10:40","DeptName":"研发部","GroupName":"研发组","UserName":"李熙祥","MealsNumber":4,"Notes":"","MealsDate":"2019-05-22","MealType":"午餐","MealTimer":"11:30-12:00","FoodAmount":1,"TableNumber":"","CanteenBy":"0","CanteenConfirmTimer":null,"CanteenNotes":"","ServingTimer":"","ReceiveStatus":0,"OrderStatus":"等待确认","CancelStatus":0,"details":[{"OrderNo":"DC190522004","FoodStyle":"","FoodName":"","FoodNum":0,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0}]},{"OrderNo":"DC190522005","OrderTimer":"2019-05-22 10:40","DeptName":"研发部","GroupName":"研发组","UserName":"李熙祥","MealsNumber":4,"Notes":"","MealsDate":"2019-05-22","MealType":"晚餐","MealTimer":"17:30-18:00","FoodAmount":1,"TableNumber":"","CanteenBy":"0","CanteenConfirmTimer":null,"CanteenNotes":"","ServingTimer":"","ReceiveStatus":0,"OrderStatus":"等待确认","CancelStatus":0,"details":[{"OrderNo":"DC190522005","FoodStyle":"","FoodName":"","FoodNum":0,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0}]},{"OrderNo":"DC190522007","OrderTimer":"2019-05-22 14:03","DeptName":"研发部","GroupName":"研发组","UserName":"李熙祥","MealsNumber":4,"Notes":"","MealsDate":"2019-05-23","MealType":"午餐","MealTimer":"11:30-12:00","FoodAmount":1,"TableNumber":"","CanteenBy":"0","CanteenConfirmTimer":null,"CanteenNotes":"","ServingTimer":"","ReceiveStatus":0,"OrderStatus":"等待确认","CancelStatus":0,"details":[{"OrderNo":"DC190522007","FoodStyle":"","FoodName":"","FoodNum":0,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0}]},{"OrderNo":"DC190522009","OrderTimer":"2019-05-22 14:21","DeptName":"研发部","GroupName":"研发组","UserName":"李熙祥","MealsNumber":4,"Notes":"","MealsDate":"2019-05-23","MealType":"午餐","MealTimer":"11:30-12:00","FoodAmount":1,"TableNumber":"","CanteenBy":"0","CanteenConfirmTimer":null,"CanteenNotes":"","ServingTimer":"","ReceiveStatus":0,"OrderStatus":"等待确认","CancelStatus":0,"details":[{"OrderNo":"DC190522009","FoodStyle":"","FoodName":"","FoodNum":0,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0}]},{"OrderNo":"DC190524007","OrderTimer":"2019-05-24 17:41","DeptName":"研发部","GroupName":"研发组","UserName":"李熙祥","MealsNumber":4,"Notes":"","MealsDate":"2019-05-26","MealType":"午餐","MealTimer":"11:30-12:00","FoodAmount":5,"TableNumber":"","CanteenBy":"0","CanteenConfirmTimer":null,"CanteenNotes":"","ServingTimer":"","ReceiveStatus":0,"OrderStatus":"等待确认","CancelStatus":0,"details":[{"OrderNo":"DC190524007","FoodStyle":"川菜","FoodName":"酸辣大白菜","FoodNum":1,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0},{"OrderNo":"DC190524007","FoodStyle":"川菜","FoodName":"凉拌青瓜","FoodNum":1,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0},{"OrderNo":"DC190524007","FoodStyle":"川菜","FoodName":"酸辣白菜","FoodNum":1,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0},{"OrderNo":"DC190524007","FoodStyle":"川菜","FoodName":"鱼香茄子","FoodNum":1,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0},{"OrderNo":"DC190524007","FoodStyle":"川菜","FoodName":"青椒皮蛋","FoodNum":1,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0}]}]
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


    public static class DataBean implements Serializable {
        /**
         * OrderNo : DC190522004
         * OrderTimer : 2019-05-22 10:40
         * DeptName : 研发部
         * GroupName : 研发组
         * UserName : 李熙祥
         * MealsNumber : 4
         * Notes :
         * MealsDate : 2019-05-22
         * MealType : 午餐
         * MealTimer : 11:30-12:00
         * FoodAmount : 1
         * TableNumber :
         * CanteenBy : 0
         * CanteenConfirmTimer : null
         * CanteenNotes :
         * ServingTimer :
         * ReceiveStatus : 0
         * OrderStatus : 等待确认
         * CancelStatus : 0
         * details : [{"OrderNo":"DC190522004","FoodStyle":"","FoodName":"","FoodNum":0,"Unit":"份","Taste":"","FoodCode":"                                    ","CanteenConfirm":0}]
         */

        private String OrderNo;
        private String OrderTimer;
        private String DeptName;
        private String GroupName;
        private String UserName;
        private int MealsNumber;
        private String Notes;
        private String MealsDate;
        private String MealType;
        private String MealTimer;
        private int FoodAmount;
        private String TableNumber;
        private String CanteenBy;
        private Object CanteenConfirmTimer;
        private String CanteenNotes;
        private String ServingTimer;
        private int ReceiveStatus;
        private String OrderStatus;
        private int CancelStatus;
        private List<DetailsBean> details;

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public String getOrderTimer() {
            return OrderTimer;
        }

        public void setOrderTimer(String OrderTimer) {
            this.OrderTimer = OrderTimer;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String GroupName) {
            this.GroupName = GroupName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public int getMealsNumber() {
            return MealsNumber;
        }

        public void setMealsNumber(int MealsNumber) {
            this.MealsNumber = MealsNumber;
        }

        public String getNotes() {
            return Notes;
        }

        public void setNotes(String Notes) {
            this.Notes = Notes;
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

        public int getFoodAmount() {
            return FoodAmount;
        }

        public void setFoodAmount(int FoodAmount) {
            this.FoodAmount = FoodAmount;
        }

        public String getTableNumber() {
            return TableNumber;
        }

        public void setTableNumber(String TableNumber) {
            this.TableNumber = TableNumber;
        }

        public String getCanteenBy() {
            return CanteenBy;
        }

        public void setCanteenBy(String CanteenBy) {
            this.CanteenBy = CanteenBy;
        }

        public Object getCanteenConfirmTimer() {
            return CanteenConfirmTimer;
        }

        public void setCanteenConfirmTimer(Object CanteenConfirmTimer) {
            this.CanteenConfirmTimer = CanteenConfirmTimer;
        }

        public String getCanteenNotes() {
            return CanteenNotes;
        }

        public void setCanteenNotes(String CanteenNotes) {
            this.CanteenNotes = CanteenNotes;
        }

        public String getServingTimer() {
            return ServingTimer;
        }

        public void setServingTimer(String ServingTimer) {
            this.ServingTimer = ServingTimer;
        }

        public int getReceiveStatus() {
            return ReceiveStatus;
        }

        public void setReceiveStatus(int ReceiveStatus) {
            this.ReceiveStatus = ReceiveStatus;
        }

        public String getOrderStatus() {
            return OrderStatus;
        }

        public void setOrderStatus(String OrderStatus) {
            this.OrderStatus = OrderStatus;
        }

        public int getCancelStatus() {
            return CancelStatus;
        }

        public void setCancelStatus(int CancelStatus) {
            this.CancelStatus = CancelStatus;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }


        public static class DetailsBean implements Serializable {
            /**
             * OrderNo : DC190522004
             * FoodStyle :
             * FoodName :
             * FoodNum : 0
             * Unit : 份
             * Taste :
             * FoodCode :
             * CanteenConfirm : 0
             */

            private String OrderNo;
            private String FoodStyle;
            private String FoodName;
            private int FoodNum;
            private String Unit;
            private String Taste;
            private String FoodCode;
            private int CanteenConfirm;

            public DetailsBean() {
            }

            public DetailsBean(String orderNo, String foodStyle, String foodName, int foodNum, String unit, String taste, String foodCode, int canteenConfirm) {
                OrderNo = orderNo;
                FoodStyle = foodStyle;
                FoodName = foodName;
                FoodNum = foodNum;
                Unit = unit;
                Taste = taste;
                FoodCode = foodCode;
                CanteenConfirm = canteenConfirm;
            }

            public String getOrderNo() {
                return OrderNo;
            }

            public void setOrderNo(String OrderNo) {
                this.OrderNo = OrderNo;
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

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public String getTaste() {
                return Taste;
            }

            public void setTaste(String Taste) {
                this.Taste = Taste;
            }

            public String getFoodCode() {
                return FoodCode;
            }

            public void setFoodCode(String FoodCode) {
                this.FoodCode = FoodCode;
            }

            public int getCanteenConfirm() {
                return CanteenConfirm;
            }

            public void setCanteenConfirm(int CanteenConfirm) {
                this.CanteenConfirm = CanteenConfirm;
            }
        }
    }
}

