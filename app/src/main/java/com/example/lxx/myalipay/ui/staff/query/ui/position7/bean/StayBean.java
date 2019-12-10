package com.example.lxx.myalipay.ui.staff.query.ui.position7.bean;

import java.util.List;

/**
 * created by lxx at 2019/12/3 16:09
 * 描述:楼栋
 */
public class StayBean {
    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"RecId":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"201","BedQty":10,"ResidentsQty":9,"FreeBedQty":1,"DeptName":"工程部","GroupName":"五金组","Details":[{"rowid":"1","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位1(上)","ResidentsName":"侯路通","EmpNo":"","Sex":"男","DeptName":"工程部","GroupName":"五金组"},{"rowid":"2","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位2(下)","ResidentsName":"洪良玉","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"3","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位3(上)","ResidentsName":"吕增","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"4","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位4(下)","ResidentsName":"曾达","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"5","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位5(上)","ResidentsName":"陈志权","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"6","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位6(下)","ResidentsName":"邓联湘","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"7","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位7(上)","ResidentsName":"姜春林","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"8","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位8(下)","ResidentsName":"邓丁三","EmpNo":"","Sex":"男","DeptName":"工程部","GroupName":"五金组"},{"rowid":"9","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位9(上)","ResidentsName":"赵兴波","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"}]},{"RecId":"2B6469A2-4F9E-47FC-BD81-4B3180AF99F6","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"202","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"管理中心","GroupName":"其他","Details":[]},{"RecId":"93AD32A8-AFCF-422A-AD08-3D280DE1CED8","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"203","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"","GroupName":"","Details":[]},{"RecId":"4CC9CA2D-E740-42D0-8CF6-88560F0F6708","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"204","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"","GroupName":"","Details":[]},{"RecId":"A69C6E7E-B3CE-4E36-9697-F501AA41733E","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"205","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"","GroupName":"","Details":[]},{"RecId":"C2891BC3-EF6C-418E-ACC0-C4574E070CB8","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"206","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"","GroupName":"","Details":[]},{"RecId":"7B85BB4F-08FC-463A-9B5E-E04457E36406","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"207","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"","GroupName":"","Details":[]},{"RecId":"6B4E216C-5582-42B7-8C5B-18F8C13A4CB4","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"208","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"","GroupName":"","Details":[]},{"RecId":"8E873855-DFEC-41C9-A9EB-132D5F58B9CC","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"209","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"","GroupName":"","Details":[]},{"RecId":"63E30C58-AA3E-40A7-9443-A5B0CF579D3E","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"210","BedQty":10,"ResidentsQty":0,"FreeBedQty":10,"DeptName":"","GroupName":"","Details":[]},{"RecId":"868AC241-E517-4414-B5DE-DB7BD64BD75C","BuildingNum":"D栋","FloorNum":"2","DormitoryNum":"211","BedQty":8,"ResidentsQty":0,"FreeBedQty":8,"DeptName":"","GroupName":"","Details":[]}]
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

    public static class DataBean  {
        /**
         * RecId : 52E61168-ED90-4DA3-AE8A-4F5BF58572D3
         * BuildingNum : D栋
         * FloorNum : 2
         * DormitoryNum : 201
         * BedQty : 10
         * ResidentsQty : 9
         * FreeBedQty : 1
         * DeptName : 工程部
         * GroupName : 五金组
         * Details : [{"rowid":"1","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位1(上)","ResidentsName":"侯路通","EmpNo":"","Sex":"男","DeptName":"工程部","GroupName":"五金组"},{"rowid":"2","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位2(下)","ResidentsName":"洪良玉","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"3","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位3(上)","ResidentsName":"吕增","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"4","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位4(下)","ResidentsName":"曾达","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"5","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位5(上)","ResidentsName":"陈志权","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"6","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位6(下)","ResidentsName":"邓联湘","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"7","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位7(上)","ResidentsName":"姜春林","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"},{"rowid":"8","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位8(下)","ResidentsName":"邓丁三","EmpNo":"","Sex":"男","DeptName":"工程部","GroupName":"五金组"},{"rowid":"9","ParRecid":"52E61168-ED90-4DA3-AE8A-4F5BF58572D3","DormitoryNum":"201","BedNum":"床位9(上)","ResidentsName":"赵兴波","EmpNo":"","Sex":"男","DeptName":"操机部","GroupName":"五金组"}]
         */

        private String RecId;
        private String BuildingNum;
        private String FloorNum;
        private String DormitoryNum;
        private int BedQty;
        private int ResidentsQty;
        private int FreeBedQty;
        private String DeptName;
        private String GroupName;
        private List<DetailsBean> Details;

        public String getRecId() {
            return RecId;
        }

        public void setRecId(String RecId) {
            this.RecId = RecId;
        }

        public String getBuildingNum() {
            return BuildingNum;
        }

        public void setBuildingNum(String BuildingNum) {
            this.BuildingNum = BuildingNum;
        }

        public String getFloorNum() {
            return FloorNum;
        }

        public void setFloorNum(String FloorNum) {
            this.FloorNum = FloorNum;
        }

        public String getDormitoryNum() {
            return DormitoryNum;
        }

        public void setDormitoryNum(String DormitoryNum) {
            this.DormitoryNum = DormitoryNum;
        }

        public int getBedQty() {
            return BedQty;
        }

        public void setBedQty(int BedQty) {
            this.BedQty = BedQty;
        }

        public int getResidentsQty() {
            return ResidentsQty;
        }

        public void setResidentsQty(int ResidentsQty) {
            this.ResidentsQty = ResidentsQty;
        }

        public int getFreeBedQty() {
            return FreeBedQty;
        }

        public void setFreeBedQty(int FreeBedQty) {
            this.FreeBedQty = FreeBedQty;
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

        public List<DetailsBean> getDetails() {
            return Details;
        }

        public void setDetails(List<DetailsBean> Details) {
            this.Details = Details;
        }


        public static class DetailsBean {
            /**
             * rowid : 1
             * ParRecid : 52E61168-ED90-4DA3-AE8A-4F5BF58572D3
             * DormitoryNum : 201
             * BedNum : 床位1(上)
             * ResidentsName : 侯路通
             * EmpNo :
             * Sex : 男
             * DeptName : 工程部
             * GroupName : 五金组
             */

            private String rowid;
            private String ParRecid;
            private String DormitoryNum;
            private String BedNum;
            private String ResidentsName;
            private String EmpNo;
            private String Sex;
            private String DeptName;
            private String GroupName;

            public String getRowid() {
                return rowid;
            }

            public void setRowid(String rowid) {
                this.rowid = rowid;
            }

            public String getParRecid() {
                return ParRecid;
            }

            public void setParRecid(String ParRecid) {
                this.ParRecid = ParRecid;
            }

            public String getDormitoryNum() {
                return DormitoryNum;
            }

            public void setDormitoryNum(String DormitoryNum) {
                this.DormitoryNum = DormitoryNum;
            }

            public String getBedNum() {
                return BedNum;
            }

            public void setBedNum(String BedNum) {
                this.BedNum = BedNum;
            }

            public String getResidentsName() {
                return ResidentsName;
            }

            public void setResidentsName(String ResidentsName) {
                this.ResidentsName = ResidentsName;
            }

            public String getEmpNo() {
                return EmpNo;
            }

            public void setEmpNo(String EmpNo) {
                this.EmpNo = EmpNo;
            }

            public String getSex() {
                return Sex;
            }

            public void setSex(String Sex) {
                this.Sex = Sex;
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

        }
    }
}

