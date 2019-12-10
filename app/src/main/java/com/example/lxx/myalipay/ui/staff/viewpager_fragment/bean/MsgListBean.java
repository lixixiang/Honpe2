package com.example.lxx.myalipay.ui.staff.viewpager_fragment.bean;

import java.util.List;

/**
 * created by lxx at 2019/11/27 14:11
 * 描述:内部员工首页viewPager 显示内容
 */
public class MsgListBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : {"total":140,"rows":[{"Id":"4be82b80-4e49-4c77-b3f4-4a7c1fd56230","Title":"通知--今天（1/23）下午有重要客户去参观生产车间，请所有部门积极配合，做好相应准备，做好保密工作。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2018-01-23T10:54:49","Comments":0,"CommentsDetails":null},{"Id":"95478907-e06a-4a0d-bec3-c4e5bdafeb5f","Title":"通知--今天（1/16）有1批重要客户去参观生产车间，请生产各部门配合！","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2018-01-16T09:04:01","Comments":0,"CommentsDetails":null},{"Id":"535239fe-ae9c-478f-bb7e-5ec0f630641a","Title":"12/18--重要客户考核工厂，请各部门全力以赴做好相关准备！！","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-12-16T14:02:54","Comments":0,"CommentsDetails":null},{"Id":"a8682718-3492-459e-b8cf-1c5e23cd688b","Title":"通知--明天早上8点10分左右有重要客户来参观工厂，请各生产部门配合！！","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-12-12T15:53:03","Comments":0,"CommentsDetails":null},{"Id":"6efe790e-048e-4300-80de-8bc0c110e36f","Title":"通知-今天下午15:00以后，日语业务组有重要客户来参观生产车间，请各部门做好相应准备。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-11-28T08:54:10","Comments":0,"CommentsDetails":null},{"Id":"6e5167f3-b8a3-46f4-968a-f56b7ca15622","Title":"关于2018年元旦晚会节目报名及征集通知","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-11-14T09:11:34","Comments":2,"CommentsDetails":null},{"Id":"35aa7c8b-a035-4424-b837-4a659e8dc3b4","Title":"通知--今天下午英语组有重要客户去参观生产车间，每个部门都会去参观，请各部门维持好平时7S现场卫生，做好保密工作。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-10-17T08:33:11","Comments":0,"CommentsDetails":null},{"Id":"064fd582-5d84-4a24-ab6b-295c279b2f25","Title":"通知-今天英语组有重要客户去参观生产车间，每个部门都会去参观，请各部门维持好平时7S现场卫生，做好保密工作。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-20T10:09:14","Comments":0,"CommentsDetails":null},{"Id":"9c63dc16-85c5-4cf5-9141-4eec5f0bb9a1","Title":"关于国庆节和中秋节放假的通知","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-19T11:26:01","Comments":0,"CommentsDetails":null},{"Id":"ca19ea1a-f945-4f4d-811b-8dd4ce807be7","Title":"停电温馨提示","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-16T14:22:57","Comments":0,"CommentsDetails":null},{"Id":"51bb4dfd-5070-4eaf-8019-3e0b1cbbcf63","Title":"通知--今天上午10:00左右，日语组有重要客户去参观生产车间，请各部门维持好平时7S现场卫生，做好保密工作。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-05T09:48:53","Comments":0,"CommentsDetails":null},{"Id":"87e08106-9799-402d-9300-7b1f15d121e7","Title":"通知--国内业务的客户要借用大会议室一星期，请各部门人员知悉！","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-05T09:46:20","Comments":0,"CommentsDetails":null},{"Id":"3eb82144-c7f3-4212-9a44-7543adba7fc3","Title":"通知--今天下午大概2点左右，有市领导会来公司进行参观，考察，请各部门做好各种相关准备。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-08-30T10:52:56","Comments":0,"CommentsDetails":null},{"Id":"4f12befc-24d0-4b38-974f-a1715980459d","Title":"关于拔河比赛事宜的通知","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-08-18T14:28:37","Comments":0,"CommentsDetails":null},{"Id":"dfcdfabe-0481-4110-b503-1f5869d78138","Title":"关于台球比赛事宜的通知","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-08-18T14:25:59","Comments":3,"CommentsDetails":null}]}
     * Total : 140
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
         * total : 140
         * rows : [{"Id":"4be82b80-4e49-4c77-b3f4-4a7c1fd56230","Title":"通知--今天（1/23）下午有重要客户去参观生产车间，请所有部门积极配合，做好相应准备，做好保密工作。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2018-01-23T10:54:49","Comments":0,"CommentsDetails":null},{"Id":"95478907-e06a-4a0d-bec3-c4e5bdafeb5f","Title":"通知--今天（1/16）有1批重要客户去参观生产车间，请生产各部门配合！","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2018-01-16T09:04:01","Comments":0,"CommentsDetails":null},{"Id":"535239fe-ae9c-478f-bb7e-5ec0f630641a","Title":"12/18--重要客户考核工厂，请各部门全力以赴做好相关准备！！","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-12-16T14:02:54","Comments":0,"CommentsDetails":null},{"Id":"a8682718-3492-459e-b8cf-1c5e23cd688b","Title":"通知--明天早上8点10分左右有重要客户来参观工厂，请各生产部门配合！！","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-12-12T15:53:03","Comments":0,"CommentsDetails":null},{"Id":"6efe790e-048e-4300-80de-8bc0c110e36f","Title":"通知-今天下午15:00以后，日语业务组有重要客户来参观生产车间，请各部门做好相应准备。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-11-28T08:54:10","Comments":0,"CommentsDetails":null},{"Id":"6e5167f3-b8a3-46f4-968a-f56b7ca15622","Title":"关于2018年元旦晚会节目报名及征集通知","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-11-14T09:11:34","Comments":2,"CommentsDetails":null},{"Id":"35aa7c8b-a035-4424-b837-4a659e8dc3b4","Title":"通知--今天下午英语组有重要客户去参观生产车间，每个部门都会去参观，请各部门维持好平时7S现场卫生，做好保密工作。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-10-17T08:33:11","Comments":0,"CommentsDetails":null},{"Id":"064fd582-5d84-4a24-ab6b-295c279b2f25","Title":"通知-今天英语组有重要客户去参观生产车间，每个部门都会去参观，请各部门维持好平时7S现场卫生，做好保密工作。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-20T10:09:14","Comments":0,"CommentsDetails":null},{"Id":"9c63dc16-85c5-4cf5-9141-4eec5f0bb9a1","Title":"关于国庆节和中秋节放假的通知","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-19T11:26:01","Comments":0,"CommentsDetails":null},{"Id":"ca19ea1a-f945-4f4d-811b-8dd4ce807be7","Title":"停电温馨提示","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-16T14:22:57","Comments":0,"CommentsDetails":null},{"Id":"51bb4dfd-5070-4eaf-8019-3e0b1cbbcf63","Title":"通知--今天上午10:00左右，日语组有重要客户去参观生产车间，请各部门维持好平时7S现场卫生，做好保密工作。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-05T09:48:53","Comments":0,"CommentsDetails":null},{"Id":"87e08106-9799-402d-9300-7b1f15d121e7","Title":"通知--国内业务的客户要借用大会议室一星期，请各部门人员知悉！","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-09-05T09:46:20","Comments":0,"CommentsDetails":null},{"Id":"3eb82144-c7f3-4212-9a44-7543adba7fc3","Title":"通知--今天下午大概2点左右，有市领导会来公司进行参观，考察，请各部门做好各种相关准备。","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-08-30T10:52:56","Comments":0,"CommentsDetails":null},{"Id":"4f12befc-24d0-4b38-974f-a1715980459d","Title":"关于拔河比赛事宜的通知","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-08-18T14:28:37","Comments":0,"CommentsDetails":null},{"Id":"dfcdfabe-0481-4110-b503-1f5869d78138","Title":"关于台球比赛事宜的通知","Content":"","PicUrl":"","VideoUrl":"","AddTime":"2017-08-18T14:25:59","Comments":3,"CommentsDetails":null}]
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
             * Id : 4be82b80-4e49-4c77-b3f4-4a7c1fd56230
             * Title : 通知--今天（1/23）下午有重要客户去参观生产车间，请所有部门积极配合，做好相应准备，做好保密工作。
             * Content :
             * PicUrl :
             * VideoUrl :
             * AddTime : 2018-01-23T10:54:49
             * Comments : 0
             * CommentsDetails : null
             */

            private String Id;
            private String Title;
            private String Content;
            private String PicUrl;
            private String VideoUrl;
            private String AddTime;
            private int Comments;
            private Object CommentsDetails;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
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

            public int getComments() {
                return Comments;
            }

            public void setComments(int Comments) {
                this.Comments = Comments;
            }

            public Object getCommentsDetails() {
                return CommentsDetails;
            }

            public void setCommentsDetails(Object CommentsDetails) {
                this.CommentsDetails = CommentsDetails;
            }
        }
    }
}

