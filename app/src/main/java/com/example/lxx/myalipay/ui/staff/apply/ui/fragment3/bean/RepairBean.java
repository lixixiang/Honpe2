package com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * created by lxx at 2019/11/28 17:24
 * 描述:维修单
 */
public class RepairBean {
    private String orderId;//维修单号
    private String depart;//申请部门
    private String userName;//申请人:
    private String applyDate;//申请日期
    private String cause;//报修原因
    private String headIcon;//头像
    private List<DataBean> databeans;

    public RepairBean() {
    }

    public RepairBean(String userName) {
        this.userName = userName;
    }

    public List<DataBean> getDatabeans() {
        return databeans;
    }

    public void setDatabeans(List<DataBean> databeans) {
        this.databeans = databeans;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    private List<DetailListBean> detailListBeans;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<DetailListBean> getDetailListBeans() {
        return detailListBeans;
    }

    public void setDetailListBeans(List<DetailListBean> detailListBeans) {
        this.detailListBeans = detailListBeans;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }


    public static class DataBean extends MenuBean.DataBean implements MultiItemEntity {

        public static final int TITLE = 1;
        public static final int CONTENT = 2;

        private int itemType;
        private String userName;
        private String time;
        private String reason;
        private String id;

        private int type;//时间1
        private String strPic;

        private String Type;
        private String FoodName;
        private String Unit;
        private String Taste;
        private String FoodCode;
        private int Status;//状态
        private String menuType;

        public DataBean(String type, String foodName, String unit, String taste, String foodCode, int status) {
            Type = type;
            FoodName = foodName;
            Unit = unit;
            Taste = taste;
            FoodCode = foodCode;
            Status = status;
        }

        public DataBean(String foodName, int status,String menuType) {
            FoodName = foodName;
            Status = status;
            this.menuType = menuType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public String getMenuType() {
            return menuType;
        }

        public void setMenuType(String menuType) {
            this.menuType = menuType;
        }

        public String getFoodCode() {
            return FoodCode;
        }

        public void setFoodCode(String foodCode) {
            FoodCode = foodCode;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getFoodName() {
            return FoodName;
        }

        public void setFoodName(String foodName) {
            FoodName = foodName;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String unit) {
            Unit = unit;
        }

        public String getTaste() {
            return Taste;
        }

        public void setTaste(String taste) {
            Taste = taste;
        }

        public String getStrPic() {
            return strPic;
        }

        public void setStrPic(String strPic) {
            this.strPic = strPic;
        }

        public DataBean() {
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }


        public void setType(int type) {
            this.type = type;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public DataBean(String time) {
            this.time = time;
        }


        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }

    public static class DetailListBean{
        private String repairItem; //维修项目
        private String point;      //位置
        private String time;       //要求时限
        private String finishTime; //完成时间
        private String repairMan;  //维修人
        private String type;       //紧急类型
        private boolean isConfirm; //申请部门确认（维修效果）
        private String cause; //报修原因
        private String dianGonConfirm;//电工鉴定
        private String ResultCommit; //鉴定结果处理意见

        public DetailListBean(String repairItem, String point, String time, String finishTime, String repairMan, boolean isConfirm, String cause, String dianGonConfirm, String resultCommit, String type) {
            this.repairItem = repairItem;
            this.point = point;
            this.time = time;
            this.finishTime = finishTime;
            this.repairMan = repairMan;
            this.isConfirm = isConfirm;
            this.cause = cause;
            this.dianGonConfirm = dianGonConfirm;
            this.type = type;
            ResultCommit = resultCommit;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRepairItem() {
            return repairItem;
        }

        public void setRepairItem(String repairItem) {
            this.repairItem = repairItem;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getRepairMan() {
            return repairMan;
        }

        public void setRepairMan(String repairMan) {
            this.repairMan = repairMan;
        }

        public boolean isConfirm() {
            return isConfirm;
        }

        public void setConfirm(boolean confirm) {
            isConfirm = confirm;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public String getDianGonConfirm() {
            return dianGonConfirm;
        }

        public void setDianGonConfirm(String dianGonConfirm) {
            this.dianGonConfirm = dianGonConfirm;
        }

        public String getResultCommit() {
            return ResultCommit;
        }

        public void setResultCommit(String resultCommit) {
            ResultCommit = resultCommit;
        }
    }
}


