package com.example.lxx.myalipay.widget.dialog;

import java.util.List;

/**
 * created by lxx at 2019/11/29 10:41
 * 描述:
 */
public class TestMenuShowBean {
    private String MenuType;
    private List<Details> details;

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public String getMenuType() {
        return MenuType;
    }

    public void setMenuType(String menuType) {
        MenuType = menuType;
    }

    public static class Details  {
        private String foodName;

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }


    }
}
