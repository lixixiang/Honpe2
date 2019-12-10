package com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.adapter.GridViewAdapter;

import java.util.List;

/**
 * created by lxx at 2019/11/28 17:25
 * 描述:
 */
public class MenuBean extends AbstractExpandableItem<MenuBean.DataBean> implements MultiItemEntity {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"FoodStyle":"粤菜","Type":"全部","FoodName":"红烧桂鱼        ","Unit":"份","Taste":"正常","FoodCode":"94963544-F67F-4D00-8E90-BCB2594BB57F","Status":0}]
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

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return GridViewAdapter.TYPE_LEVEL_0;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * OrderNo : 菜品订单号
         * FoodStyle : 粤菜
         * Type : 全部
         * FoodName : 红烧桂鱼
         * Unit : 份
         * Taste : 正常
         * FoodCode : 94963544-F67F-4D00-8E90-BCB2594BB57F
         * Status : 0
         */
        private String OrderNo;
        private String FoodStyle;
        private String Type;
        private String FoodName;
        private String Unit;
        private String Taste;
        private String FoodCode;
        private int Status;
        private boolean isMenuCheck;

        public DataBean() {
        }

        public DataBean(String foodStyle) {
            FoodStyle = foodStyle;
        }

        public DataBean(String foodStyle, String foodName, String unit, String foodCode, int status) {
            FoodStyle = foodStyle;
            FoodName = foodName;
            Unit = unit;
            FoodCode = foodCode;
            Status = status;
        }

        public DataBean(String foodName,String foodCode, int status) {
            FoodName = foodName;
            FoodCode = foodCode;
            Status = status;
        }

        public boolean isMenuCheck() {
            return isMenuCheck;
        }

        public void setMenuCheck(boolean menuCheck) {
            isMenuCheck = menuCheck;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String orderNo) {
            OrderNo = orderNo;
        }

        public String getFoodStyle() {
            return FoodStyle;
        }

        public void setFoodStyle(String FoodStyle) {
            this.FoodStyle = FoodStyle;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getFoodName() {
            return FoodName;
        }

        public void setFoodName(String FoodName) {
            this.FoodName = FoodName;
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

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        @Override
        public int getItemType() {
            return GridViewAdapter.TYPE_PERSON;
        }
    }
}

