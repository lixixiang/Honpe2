package com.example.lxx.myalipay.ui.staff.query.ui.position1.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * created by lxx at 2019/10/22 14:29
 * 描述:
 */
public class CheckInBean {
    /**
     * Status : 0
     * Msg : 成功！
     * Data : [{"EmpNo":"2495","UserName":null,"DptName":null,"DepCode":null,"WeekName":"星期二","RecordDate":"2019-10-01T00:00:00","T1":null,"Timer1":null,"S1":0,"T2":null,"Timer2":null,"S2":0,"T3":null,"Timer3":null,"S3":0,"T4":null,"Timer4":null,"S4":0,"T5":null,"Timer5":null,"S5":0,"T6":null,"Timer6":null,"S6":0,"Lock":0,"RecordCount":0,"HistoryRec":"","AbnormalRec":"法定假日休息"}]
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

    public static class DataBean implements Serializable,MultiItemEntity{
        /**
         *             "EmpNo":"660",   //工号
         *             "UserName":"覃勇鹏",  //姓名
         *             "DptName":"软件组",  //部门
         *             "DepCode":"10101030101", //部门编号
         *             "WeekName":"星期二",  //星期
         *             "RecordDate":"2019-10-08 00:00:00", //考勤日期
         *             "T1":"2019-10-08 08:30:00",  //打卡时间1（全）
         *             "Timer1":"08:30",  //打卡时间1（时分）
         *             "S1":0,   //打卡时间1迟到早退分钟数
         *             "T2":"2019-10-08 12:30:00",
         *             "Timer2":"12:30",
         *             "S2":0,
         *             "T3":"2019-10-08 12:50:00",
         *             "Timer3":"12:50",
         *             "S3":0,
         *             "T4":"2019-10-08 18:11:00",
         *             "Timer4":"18:11",
         *             "S4":0,
         *             "T5":null,
         *             "Timer5":"",
         *             "S5":0,
         *             "T6":null,
         *             "Timer6":"",
         *             "S6":0,
         *             "Lock":0,  //考勤锁定
         *             "RecordCount":4, //打卡次数
         *             "HistoryRec":"08:30:00;12:30:00;12:50:00;18:11:00", //打卡记录
         *             "AbnormalRec":""    //考勤说明
         */
        private String EmpNo;
        private String UserName;
        private String DptName;
        private String DepCode;
        private String WeekName;
        private String RecordDate;
        private String T1;
        private String Timer1;
        private int S1;
        private String T2;
        private String Timer2;
        private int S2;
        private String T3;
        private String Timer3;
        private int S3;
        private String T4;
        private String Timer4;
        private int S4;
        private String T5;
        private String Timer5;
        private int S5;
        private String T6;
        private String Timer6;
        private int S6;
        private int Lock;
        private int RecordCount;
        private String HistoryRec;
        private String AbnormalRec;

        private int itemType;
        public static final int TABLE = 1;
        public static final int TEXT = 2;


        public String getEmpNo() {
            return EmpNo;
        }

        public void setEmpNo(String EmpNo) {
            this.EmpNo = EmpNo;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getDptName() {
            return DptName;
        }

        public void setDptName(String DptName) {
            this.DptName = DptName;
        }

        public String getDepCode() {
            return DepCode;
        }

        public void setDepCode(String DepCode) {
            this.DepCode = DepCode;
        }

        public String getWeekName() {
            return WeekName;
        }

        public void setWeekName(String WeekName) {
            this.WeekName = WeekName;
        }

        public String getRecordDate() {
            return RecordDate;
        }

        public void setRecordDate(String RecordDate) {
            this.RecordDate = RecordDate;
        }

        public String getT1() {
            return T1;
        }

        public void setT1(String T1) {
            this.T1 = T1;
        }

        public String getTimer1() {
            return Timer1;
        }

        public void setTimer1(String Timer1) {
            this.Timer1 = Timer1;
        }

        public int getS1() {
            return S1;
        }

        public void setS1(int S1) {
            this.S1 = S1;
        }

        public String getT2() {
            return T2;
        }

        public void setT2(String T2) {
            this.T2 = T2;
        }

        public String getTimer2() {
            return Timer2;
        }

        public void setTimer2(String Timer2) {
            this.Timer2 = Timer2;
        }

        public int getS2() {
            return S2;
        }

        public void setS2(int S2) {
            this.S2 = S2;
        }

        public String getT3() {
            return T3;
        }

        public void setT3(String T3) {
            this.T3 = T3;
        }

        public String getTimer3() {
            return Timer3;
        }

        public void setTimer3(String Timer3) {
            this.Timer3 = Timer3;
        }

        public int getS3() {
            return S3;
        }

        public void setS3(int S3) {
            this.S3 = S3;
        }

        public String getT4() {
            return T4;
        }

        public void setT4(String T4) {
            this.T4 = T4;
        }

        public String getTimer4() {
            return Timer4;
        }

        public void setTimer4(String Timer4) {
            this.Timer4 = Timer4;
        }

        public int getS4() {
            return S4;
        }

        public void setS4(int S4) {
            this.S4 = S4;
        }

        public String getT5() {
            return T5;
        }

        public void setT5(String T5) {
            this.T5 = T5;
        }

        public String getTimer5() {
            return Timer5;
        }

        public void setTimer5(String Timer5) {
            this.Timer5 = Timer5;
        }

        public int getS5() {
            return S5;
        }

        public void setS5(int S5) {
            this.S5 = S5;
        }

        public String getT6() {
            return T6;
        }

        public void setT6(String T6) {
            this.T6 = T6;
        }

        public String getTimer6() {
            return Timer6;
        }

        public void setTimer6(String Timer6) {
            this.Timer6 = Timer6;
        }

        public int getS6() {
            return S6;
        }

        public void setS6(int S6) {
            this.S6 = S6;
        }

        public int getLock() {
            return Lock;
        }

        public void setLock(int Lock) {
            this.Lock = Lock;
        }

        public int getRecordCount() {
            return RecordCount;
        }

        public void setRecordCount(int RecordCount) {
            this.RecordCount = RecordCount;
        }

        public String getHistoryRec() {
            return HistoryRec;
        }

        public void setHistoryRec(String HistoryRec) {
            this.HistoryRec = HistoryRec;
        }

        public String getAbnormalRec() {
            return AbnormalRec;
        }

        public void setAbnormalRec(String AbnormalRec) {
            this.AbnormalRec = AbnormalRec;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }
}
