package com.example.lxx.myalipay.ui.staff.viewpager_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.viewpager_fragment.adapter.InnerMainFragmentAdapter;
import com.example.lxx.myalipay.ui.staff.viewpager_fragment.bean.MsgListBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * created by lxx at 2019/11/27 14:03
 * 描述:行政通知
 */
public class InnerMainFragment extends BaseBackFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private String mTitle, url;
    private int position;
    InnerMainFragmentAdapter adapter;

    public static InnerMainFragment newInstance(String title, int position) {
        InnerMainFragment fragment = new InnerMainFragment();
        fragment.mTitle = title;
        fragment.position = position;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void initView() {
        setNetRequest(position);
    }


    private void setNetRequest(final int pos) {
        String session = (String) MyApplication.get(_mActivity, Session, "");
        LatteLogger.d(session);
        EasyHttp.post(Constants.YGMESSAGELIST + session)
                .params("top", "0")
                .params("hascontent", false+"")
                .params("newstype", pos + 1+"")
                .execute(new SimpleCallBack<String>() {


                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("result", result);
                        final MsgListBean bean = Convert.fromJson(result, MsgListBean.class);
                        if (bean.getStatus() == 0) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                            adapter = new InnerMainFragmentAdapter(bean.getData().getRows());
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    if (bean.getStatus() == 0) {
                                        Intent intent = new Intent(_mActivity,DetailMsgActivity.class);
                                        intent.putExtra("id", bean.getData().getRows().get(position).getId());
                                        intent.putExtra("newstype",pos + 1);
                                        startActivity(intent);
                                    }else{
                                        ToastUtils.getInstance().showToast(bean.getMsg());
                                    }
                                }
                            });
                        }else{
                            ToastUtils.getInstance().showToast(bean.getMsg());
                        }
                    }
                });
    }


}
