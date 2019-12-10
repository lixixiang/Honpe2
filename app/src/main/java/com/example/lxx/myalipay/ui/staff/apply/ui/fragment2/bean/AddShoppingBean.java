package com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean;

import java.util.List;

/**
 * created by lxx at 2019/12/1 19:02
 * 描述:
 */
public class AddShoppingBean {
    private String shoppingEvent;
    private String shoppingType;
    private String shoppingPayDate;
    private String shoppingPayWay;
    private String shoppingRemark;
    private List<Data> DataList;
    private List<ImageData> DataImageList;

    public List<ImageData> getDataImageList() {
        return DataImageList;
    }

    public void setDataImageList(List<ImageData> dataImageList) {
        DataImageList = dataImageList;
    }

    public String getShoppingEvent() {
        return shoppingEvent;
    }

    public void setShoppingEvent(String shoppingEvent) {
        this.shoppingEvent = shoppingEvent;
    }

    public String getShoppingType() {
        return shoppingType;
    }

    public void setShoppingType(String shoppingType) {
        this.shoppingType = shoppingType;
    }

    public String getShoppingPayDate() {
        return shoppingPayDate;
    }

    public void setShoppingPayDate(String shoppingPayDate) {
        this.shoppingPayDate = shoppingPayDate;
    }

    public String getShoppingPayWay() {
        return shoppingPayWay;
    }

    public void setShoppingPayWay(String shoppingPayWay) {
        this.shoppingPayWay = shoppingPayWay;
    }

    public String getShoppingRemark() {
        return shoppingRemark;
    }

    public void setShoppingRemark(String shoppingRemark) {
        this.shoppingRemark = shoppingRemark;
    }


    public List<Data> getDataList() {
        return DataList;
    }

    public void setDataList(List<Data> dataList) {
        DataList = dataList;
    }

    public static class ImageData{
        public String ImageName;       //图片的名字
        public String ImagePath;       //图片的路径

        public String getImageName() {
            return ImageName;
        }

        public void setImageName(String imageName) {
            ImageName = imageName;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String imagePath) {
            ImagePath = imagePath;
        }
    }

    public static class Data{
        private String name;
        private String size;
        private String num;
        private String unit;
        private String price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}

