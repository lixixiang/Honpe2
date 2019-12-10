package com.example.lxx.myalipay.ui.staff.query.ui.position7.bean;

import java.io.Serializable;
import java.util.List;

/**
 * created by lxx at 2019/12/3 15:38
 * 描述:
 */
public class FloorManagerBean implements Serializable{
    private int position;
    private String BuildNum;    //哪栋
    private String FloorNum; //楼层
    private String TotalRoom;   //共多少间用字段的长段
    private String EmptyRoom;   //空房间
    private String FreeRoom;     //剩余多少间用长度
    private String FullRoom;    //满房间
    private List<FloorDataBean> floorDataBeanList;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getBuild() {
        return BuildNum;
    }

    public void setBuild(String build) {
        BuildNum = build;
    }

    public String getFloorNum() {
        return FloorNum;
    }

    public void setFloorNum(String floorNum) {
        FloorNum = floorNum;
    }

    public String getTotalRoom() {
        return TotalRoom;
    }

    public void setTotalRoom(String totalRoom) {
        TotalRoom = totalRoom;
    }

    public String getEmptyRoom() {
        return EmptyRoom;
    }

    public void setEmptyRoom(String emptyRoom) {
        EmptyRoom = emptyRoom;
    }

    public String getFreeRoom() {
        return FreeRoom;
    }

    public void setFreeRoom(String freeRoom) {
        FreeRoom = freeRoom;
    }

    public String getFullRoom() {
        return FullRoom;
    }

    public void setFullRoom(String fullRoom) {
        FullRoom = fullRoom;
    }

    public List<FloorDataBean> getFloorDataBeanList() {
        return floorDataBeanList;
    }

    public void setFloorDataBeanList(List<FloorDataBean> floorDataBeanList) {
        this.floorDataBeanList = floorDataBeanList;
    }

    public static class FloorDataBean implements Serializable{
        private String ParRecid;
        private String DormitoryNum; //宿舍编号308
        private int BedQty;       //宿舍总床位
        private int FreeBedQty;   //剩余床位
        private String DeptName;     //所属部门
        private String GroupName;    //所属组别
        private List<FloorDetailBean> detailBeans;

        public List<FloorDetailBean> getDetailBeans() {
            return detailBeans;
        }

        public String getParRecid() {
            return ParRecid;
        }

        public void setParRecid(String parRecid) {
            ParRecid = parRecid;
        }

        public void setDetailBeans(List<FloorDetailBean> detailBeans) {
            this.detailBeans = detailBeans;
        }

        public String getDormitoryNum() {
            return DormitoryNum;
        }

        public void setDormitoryNum(String dormitoryNum) {
            DormitoryNum = dormitoryNum;
        }

        public int getBedQty() {
            return BedQty;
        }

        public void setBedQty(int bedQty) {
            BedQty = bedQty;
        }

        public int getFreeBedQty() {
            return FreeBedQty;
        }

        public void setFreeBedQty(int freeBedQty) {
            FreeBedQty = freeBedQty;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String deptName) {
            DeptName = deptName;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String groupName) {
            GroupName = groupName;
        }



        public static class FloorDetailBean implements Serializable {
            private String rowid;
            private String ResidentsName; //姓名 赵晨光
            private String Sex;           //性别
            private String BeNum;         //床位
            private String Rooms;         //房间
            private String departName;    //部门
            private String team;          //组别

            public String getDepartName() {
                return departName;
            }

            public void setDepartName(String departName) {
                this.departName = departName;
            }

            public String getTeam() {
                return team;
            }

            public void setTeam(String team) {
                this.team = team;
            }

            public String getRowid() {
                return rowid;
            }

            public void setRowid(String rowid) {
                this.rowid = rowid;
            }

            public String getRooms() {
                return Rooms;
            }

            public void setRooms(String rooms) {
                Rooms = rooms;
            }

            public String getResidentsName() {
                return ResidentsName;
            }

            public void setResidentsName(String residentsName) {
                ResidentsName = residentsName;
            }

            public String getSex() {
                return Sex;
            }

            public void setSex(String sex) {
                Sex = sex;
            }

            public String getBeNum() {
                return BeNum;
            }

            public void setBeNum(String beNum) {
                BeNum = beNum;
            }
        }
    }
}



