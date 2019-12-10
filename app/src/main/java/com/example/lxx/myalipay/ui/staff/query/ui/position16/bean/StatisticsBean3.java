package com.example.lxx.myalipay.ui.staff.query.ui.position16.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.bean
 * 作者: lxx
 * 日期: 2019/1/18 12:05
 * 描述: 获取餐评列表接口
 */
public class StatisticsBean3 {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"Rcode":"201707252","MealDate":"2017-07-25","MealTimes":"午餐","Great":1,"Good":0,"Bad":3,"ExcellenceRate":25,"CommentSum":4,"Details":[{"Rcode":"201707252","CommentTime":"2017-07-25 14:18","Score":"差","Content":"","UserName":"唐国新"},{"Rcode":"201707252","CommentTime":"2017-07-26 13:04","Score":"差","Content":"","UserName":"吴祖伦"},{"Rcode":"201707252","CommentTime":"2017-07-27 14:26","Score":"差","Content":"","UserName":"何璇"},{"Rcode":"201707252","CommentTime":"2019-01-17 11:13","Score":"优","Content":"","UserName":"管理员"}]},{"Rcode":"201707253","MealDate":"2017-07-25","MealTimes":"晚餐","Great":0,"Good":1,"Bad":0,"ExcellenceRate":100,"CommentSum":1,"Details":[{"Rcode":"201707253","CommentTime":"2019-01-17 12:05","Score":"良","Content":"jiayouba ","UserName":"管理员"}]}]
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

    public static class DataBean implements MultiItemEntity {
        /**
         * Rcode : 201707252
         * MealDate : 2017-07-25
         * MealTimes : 午餐
         * Great : 1
         * Good : 0
         * Bad : 3
         * ExcellenceRate : 25.0
         * CommentSum : 4
         * Details : [{"Rcode":"201707252","CommentTime":"2017-07-25 14:18","Score":"差","Content":"","UserName":"唐国新"},{"Rcode":"201707252","CommentTime":"2017-07-26 13:04","Score":"差","Content":"","UserName":"吴祖伦"},{"Rcode":"201707252","CommentTime":"2017-07-27 14:26","Score":"差","Content":"","UserName":"何璇"},{"Rcode":"201707252","CommentTime":"2019-01-17 11:13","Score":"优","Content":"","UserName":"管理员"}]
         */
        private int itemType;
        public static final int item1 = 0;
        public static final int item2 = 1;

        private String Rcode;
        private String MealDate;
        private String MealTimes;
        private String[] mealName;//早中晚夜
        private String[] appraiseText;//很好一般很差
        private int Great;
        private int Good;
        private int Bad;
        private double ExcellenceRate;
        private int CommentSum;
        private List<DetailsBean> Details;

        public DataBean() {
        }

        public DataBean(int itemType, String rcode, String mealDate, String mealTimes, String[] mealName, String[] appraiseText,
                        int great, int good, int bad, double excellenceRate, int commentSum, List<DetailsBean> details) {
            this.itemType = itemType;
            Rcode = rcode;
            MealDate = mealDate;
            MealTimes = mealTimes;
            this.mealName = mealName;
            this.appraiseText = appraiseText;
            Great = great;
            Good = good;
            Bad = bad;
            ExcellenceRate = excellenceRate;
            CommentSum = commentSum;
            Details = details;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public String getRcode() {
            return Rcode;
        }

        public String[] getMealName() {
            return mealName;
        }

        public void setMealName(String[] mealName) {
            this.mealName = mealName;
        }

        public String[] getAppraiseText() {
            return appraiseText;
        }

        public void setAppraiseText(String[] appraiseText) {
            this.appraiseText = appraiseText;
        }

        public void setRcode(String Rcode) {
            this.Rcode = Rcode;
        }

        public String getMealDate() {
            return MealDate;
        }

        public void setMealDate(String MealDate) {
            this.MealDate = MealDate;
        }

        public String getMealTimes() {
            return MealTimes;
        }

        public void setMealTimes(String MealTimes) {
            this.MealTimes = MealTimes;
        }

        public int getGreat() {
            return Great;
        }

        public void setGreat(int Great) {
            this.Great = Great;
        }

        public int getGood() {
            return Good;
        }

        public void setGood(int Good) {
            this.Good = Good;
        }

        public int getBad() {
            return Bad;
        }

        public void setBad(int Bad) {
            this.Bad = Bad;
        }

        public double getExcellenceRate() {
            return ExcellenceRate;
        }

        public void setExcellenceRate(double ExcellenceRate) {
            this.ExcellenceRate = ExcellenceRate;
        }

        public int getCommentSum() {
            return CommentSum;
        }

        public void setCommentSum(int CommentSum) {
            this.CommentSum = CommentSum;
        }

        public List<DetailsBean> getDetails() {
            return Details;
        }

        public void setDetails(List<DetailsBean> Details) {
            this.Details = Details;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public static class DetailsBean {
            public DetailsBean() {
            }

            public DetailsBean(String rcode, String commentTime, String score, String content, String userName) {
                Rcode = rcode;
                CommentTime = commentTime;
                Score = score;
                Content = content;
                UserName = userName;
            }

            /**
             * Rcode : 201707252
             * CommentTime : 2017-07-25 14:18
             * Score : 差
             * Content :
             * UserName : 唐国新
             */

            private String Rcode;
            private String CommentTime;
            private String Score;
            private String Content;
            private String UserName;

            public String getRcode() {
                return Rcode;
            }

            public void setRcode(String Rcode) {
                this.Rcode = Rcode;
            }

            public String getCommentTime() {
                return CommentTime;
            }

            public void setCommentTime(String CommentTime) {
                this.CommentTime = CommentTime;
            }

            public String getScore() {
                return Score;
            }

            public void setScore(String Score) {
                this.Score = Score;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }
        }
    }
}
