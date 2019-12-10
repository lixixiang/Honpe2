package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean;

import java.util.List;

/**
 * created by lxx at 2019/11/28 10:25
 * 描述:
 */
public class ItemTeamBean {
    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"GroupId":"FM","GroupName":"复模组","GroupType":"生产","GroupGmId":"WWC"},{"GroupId":"GN","GroupName":"国内业务部","GroupType":"营业","GroupGmId":"LSP"},{"GroupId":"JK","GroupName":"机壳组","GroupType":"生产","GroupGmId":"LBB,LKW,HHQ,WWC"},{"GroupId":"MJ","GroupName":"模具组","GroupType":"生产","GroupGmId":"PJD"},{"GroupId":"QT","GroupName":"其他","GroupType":"营业","GroupGmId":""},{"GroupId":"RY","GroupName":"日本业务部","GroupType":"营业","GroupGmId":"CYH"},{"GroupId":"SM","GroupName":"数码组","GroupType":"生产","GroupGmId":"SSIAISK,CYL,QJQ"},{"GroupId":"TEST","GroupName":"测试组","GroupType":"营业","GroupGmId":""},{"GroupId":"WJ","GroupName":"五金组","GroupType":"生产","GroupGmId":"WING"},{"GroupId":"YF","GroupName":"研发组","GroupType":"生产","GroupGmId":"PWD"},{"GroupId":"YY","GroupName":"国际业务部","GroupType":"营业","GroupGmId":"LXM"}]
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
         * GroupId : FM
         * GroupName : 复模组
         * GroupType : 生产
         * GroupGmId : WWC
         */

        private String GroupId;
        private String GroupName;
        private String GroupType;
        private String GroupGmId;

        public String getGroupId() {
            return GroupId;
        }

        public void setGroupId(String GroupId) {
            this.GroupId = GroupId;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String GroupName) {
            this.GroupName = GroupName;
        }

        public String getGroupType() {
            return GroupType;
        }

        public void setGroupType(String GroupType) {
            this.GroupType = GroupType;
        }

        public String getGroupGmId() {
            return GroupGmId;
        }

        public void setGroupGmId(String GroupGmId) {
            this.GroupGmId = GroupGmId;
        }
    }
}
