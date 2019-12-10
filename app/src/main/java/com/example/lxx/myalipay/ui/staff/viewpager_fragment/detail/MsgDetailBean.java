package com.example.lxx.myalipay.ui.staff.viewpager_fragment.detail;

import java.util.List;

/**
 * created by lxx at 2019/11/27 14:35
 * 描述:
 */
public class MsgDetailBean {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : {"Id":"b0708e92-c900-4384-ba99-f8ea605690e0","Title":"通知--今天下午（16:00-17:00）日语组有重要客户会去参观生产车间，请知悉！！","Content":"通知--今天下午（16:00-17:00）日语组有重要客户会去参观生产车间，请各同事维持好平时7S现场卫生，做好保密工作。注意个人言行举止及形象，如有正面遇到客人请面带笑容主动点头打招呼。\n\n   同时，为体现公司严谨的工作作风，请各同事注意以下几点：\n   ①进出门禁，要轻开轻关，不要使门发出太大声音，出入后要使门禁保持在关闭状态。\n   ②车间中门除了拉货倒垃圾，禁止出入，保持关闭状态。\n   ③电脑需设置三分钟锁屏，做好保密工作。\n   ④客户的产品在运输过程中需要遮盖好。\n   ⑤整理好工作区域的卫生。\n   ⑥禁止成群结队进入抽烟区抽烟。\n   ⑦严禁私自带有摄像头的设备进入车间，一经发现按窃取公司机密处理。\n\n以上，谢谢","PicUrl":"","VideoUrl":"","AddTime":"2017-07-04T11:27:04","Comments":2,"CommentsDetails":[{"Critics":"李海媚","CommentTime":"2017-07-06T11:19:00","CommentContent":"已通知，并做好7S。"},{"Critics":"苏小婵","CommentTime":"2017-07-05T08:46:00","CommentContent":"一直在进行~"}]}
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

    public static class DataBean  {
        /**
         * Id : b0708e92-c900-4384-ba99-f8ea605690e0
         * Title : 通知--今天下午（16:00-17:00）日语组有重要客户会去参观生产车间，请知悉！！
         * Content : 通知--今天下午（16:00-17:00）日语组有重要客户会去参观生产车间，请各同事维持好平时7S现场卫生，做好保密工作。注意个人言行举止及形象，如有正面遇到客人请面带笑容主动点头打招呼。

         同时，为体现公司严谨的工作作风，请各同事注意以下几点：
         ①进出门禁，要轻开轻关，不要使门发出太大声音，出入后要使门禁保持在关闭状态。
         ②车间中门除了拉货倒垃圾，禁止出入，保持关闭状态。
         ③电脑需设置三分钟锁屏，做好保密工作。
         ④客户的产品在运输过程中需要遮盖好。
         ⑤整理好工作区域的卫生。
         ⑥禁止成群结队进入抽烟区抽烟。
         ⑦严禁私自带有摄像头的设备进入车间，一经发现按窃取公司机密处理。

         以上，谢谢
         * PicUrl :
         * VideoUrl :
         * AddTime : 2017-07-04T11:27:04
         * Comments : 2
         * CommentsDetails : [{"Critics":"李海媚","CommentTime":"2017-07-06T11:19:00","CommentContent":"已通知，并做好7S。"},{"Critics":"苏小婵","CommentTime":"2017-07-05T08:46:00","CommentContent":"一直在进行~"}]
         */

        private String Id;
        private String Title;
        private String Content;
        private String PicUrl;
        private String VideoUrl;
        private String AddTime;
        private int Comments;
        private List<CommentsDetailsBean> CommentsDetails;

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

        public List<CommentsDetailsBean> getCommentsDetails() {
            return CommentsDetails;
        }

        public void setCommentsDetails(List<CommentsDetailsBean> CommentsDetails) {
            this.CommentsDetails = CommentsDetails;
        }



        public static class CommentsDetailsBean {
            /**
             * Critics : 李海媚
             * CommentTime : 2017-07-06T11:19:00
             * CommentContent : 已通知，并做好7S。
             */

            private String Critics;
            private String CommentTime;
            private String CommentContent;

            public String getCritics() {
                return Critics;
            }

            public void setCritics(String Critics) {
                this.Critics = Critics;
            }

            public String getCommentTime() {
                return CommentTime;
            }

            public void setCommentTime(String CommentTime) {
                this.CommentTime = CommentTime;
            }

            public String getCommentContent() {
                return CommentContent;
            }

            public void setCommentContent(String CommentContent) {
                this.CommentContent = CommentContent;
            }
        }
    }
}

