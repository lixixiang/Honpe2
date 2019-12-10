package com.example.lxx.myalipay.widget.check_session;

import android.content.Context;


import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

/**
 * created by lxx at 2019/11/21 11:26
 * 描述:
 */
public class CheckSession {

    public static void getInstance(Context context, String session) {
        EasyHttp.post(Constants.ADD_MOBE_DELETER + session)
                .params("ModuleId", "D01")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(context, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(context, 0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                        ProgressUtils.disLoadView(context, 0);
                    }

                    @Override
                    public void onSuccess(String result) {
                        PermissionBean bean = Convert.fromJson(result, PermissionBean.class);
                        final String cs2 = "Invalid Session.";

                    }
                });
    }

    public class PermissionBean{
        /**
         * Status : 0
         * Msg : 成功!
         * Data : ["查询","新增","修改","确认","审批","派车","出入","作废"]
         */

        private int Status;
        private String Msg;
        private Object Data;

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

        public Object getData() {
            return Data;
        }

        public void setData(Object Data) {
            this.Data = Data;
        }
    }
}
