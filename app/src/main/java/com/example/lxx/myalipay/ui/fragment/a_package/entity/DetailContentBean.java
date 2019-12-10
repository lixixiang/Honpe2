package com.example.lxx.myalipay.ui.fragment.a_package.entity;

/**
 * created by lxx at 2019/11/12 14:54
 * 描述:
 */
public class DetailContentBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : {"Id":919,"Title":"VR","Content":"&nbsp;<img src=\"/uploadfile/image/20180203/20180203094940_88870.gif\" alt=\"\" />&nbsp;<img src=\"/uploadfile/image/20180203/20180203094941_94188.gif\" alt=\"\" />&nbsp;<img src=\"/uploadfile/image/20180203/20180203094942_59218.gif\" alt=\"\" />&nbsp;<img src=\"/uploadfile/image/20180203/20180203094942_73954.gif\" alt=\"\" />&nbsp;<img src=\"/uploadfile/image/20180203/20180203094943_91048.gif\" alt=\"\" />&nbsp;<img src=\"/uploadfile/image/20180203/20180203094943_62454.gif\" alt=\"\" />&nbsp;","PicUrl":"1517622599.gif","VideoUrl":"","AddTime":"2018-02-03T09:47:18"}
     * Total : 0
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
         * Id : 919
         * Title : VR
         * Content : &nbsp;<img src="/uploadfile/image/20180203/20180203094940_88870.gif" alt="" />&nbsp;<img src="/uploadfile/image/20180203/20180203094941_94188.gif" alt="" />&nbsp;<img src="/uploadfile/image/20180203/20180203094942_59218.gif" alt="" />&nbsp;<img src="/uploadfile/image/20180203/20180203094942_73954.gif" alt="" />&nbsp;<img src="/uploadfile/image/20180203/20180203094943_91048.gif" alt="" />&nbsp;<img src="/uploadfile/image/20180203/20180203094943_62454.gif" alt="" />&nbsp;
         * PicUrl : 1517622599.gif
         * VideoUrl :
         * AddTime : 2018-02-03T09:47:18
         */

        private int Id;
        private String Title;
        private String Content;
        private String PicUrl;
        private String VideoUrl;
        private String AddTime;
        private String Www;

        public String getWww() {
            return Www;
        }

        public void setWww(String www) {
            Www = www;
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

