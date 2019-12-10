package com.example.lxx.myalipay.ui.fragment.a_package.entity;

import java.util.List;

/**
 * created by lxx at 2019/11/12 10:38
 * 描述:
 */
public class NewsBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : {"total":7,"rows":[{"Id":452,"Title":"香港某创业基地来访红品","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-06-27T09:25:31"},{"Id":451,"Title":"红品职员英语培训","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-06-23T09:11:55"},{"Id":438,"Title":"红品晶英研发团队参加3D打印协同创新设计","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-06-06T15:54:44"},{"Id":449,"Title":"欧洲各国客户代表团来访红品考察","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-04-28T11:04:12"},{"Id":335,"Title":"红品模型2016年春节放假通知","Content":"","PicUrl":"http://www.honpe.com/uploadfile/1464339453.png","VideoUrl":"","AddTime":"2016-01-18T19:35:49"},{"Id":378,"Title":"红品模型为员工组织户外拓展提升培训","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-01-10T18:34:19"},{"Id":384,"Title":"热烈祝贺红品模型2015年年终庆典圆满成功","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-01-02T20:38:36"}]}
     * Total : 7
     */

    private int Status;
    private String Msg;
    private DataBean Data;
    private int Total;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public static class DataBean {
        /**
         * total : 7
         * rows : [{"Id":452,"Title":"香港某创业基地来访红品","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-06-27T09:25:31"},{"Id":451,"Title":"红品职员英语培训","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-06-23T09:11:55"},{"Id":438,"Title":"红品晶英研发团队参加3D打印协同创新设计","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-06-06T15:54:44"},{"Id":449,"Title":"欧洲各国客户代表团来访红品考察","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-04-28T11:04:12"},{"Id":335,"Title":"红品模型2016年春节放假通知","Content":"","PicUrl":"http://www.honpe.com/uploadfile/1464339453.png","VideoUrl":"","AddTime":"2016-01-18T19:35:49"},{"Id":378,"Title":"红品模型为员工组织户外拓展提升培训","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-01-10T18:34:19"},{"Id":384,"Title":"热烈祝贺红品模型2015年年终庆典圆满成功","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2016-01-02T20:38:36"}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * Id : 452
             * Title : 香港某创业基地来访红品
             * Content :
             * PicUrl :
             * VideoUrl :
             * AddTime : 2016-06-27T09:25:31
             */

            private int Id;
            private String Title;
            private String Content;
            private String PicUrl;
            private String VideoUrl;
            private String AddTime;

            public RowsBean(String title,String addTime,String picUrl) {
                Title = title;
                PicUrl = picUrl;
                AddTime = addTime;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }

            public String getPicUrl() {
                return PicUrl;
            }

            public void setPicUrl(String PicUrl) {
                this.PicUrl = PicUrl;
            }

            public String getVideoUrl() {
                return VideoUrl;
            }

            public void setVideoUrl(String VideoUrl) {
                this.VideoUrl = VideoUrl;
            }

            public String getAddTime() {
                return AddTime;
            }

            public void setAddTime(String AddTime) {
                this.AddTime = AddTime;
            }
        }
    }
}

