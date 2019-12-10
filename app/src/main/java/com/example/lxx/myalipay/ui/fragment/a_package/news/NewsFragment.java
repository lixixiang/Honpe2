package com.example.lxx.myalipay.ui.fragment.a_package.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.example.lxx.myalipay.MainFragment;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.adapter.NewsAdapter;
import com.example.lxx.myalipay.ui.fragment.a_package.entity.NewsBean;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * created by lxx at 2019/11/12 9:01
 * 描述:
 */
public class NewsFragment extends BaseFragment {
    @BindView(R.id.recy)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.ll_net_fail)
    LinearLayout llNewFail;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView title;
    private String _id, language, top;
    private NewsAdapter adapter;

    public static NewsFragment newInstance(String id,String top){
        NewsFragment fragment = new NewsFragment();
        fragment._id = id;
        fragment.top = top;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_toolbar_recyclerview_un_network;
    }

    @Override
    protected void initView() {
        LatteLogger.d("aaaaaa",_id+"  "+top);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!"0".equals(top)) {
            toolbar.setVisibility(View.VISIBLE);
            initToolbarNav(llBack);
            title.setText("企业简介");
        }else {
            toolbar.setVisibility(View.GONE);
        }
    }


    private void initGson(){
        if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
            language = "CN";
        } else if (getResources().getConfiguration().locale.getCountry().equals("US")) {
            language = "en";
        } else {
            language = "jp";
        }

        EasyHttp.post(Constants.NEWS_URL)
                .params("top",top)
                .params("newstype",_id)
                .params("language",language)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity,1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity,0);
                        LatteLogger.d("ApiException", e.getCode());
                        if (e.getCode() == 1009) {
                            recyclerView.setVisibility(View.GONE);
                            llNewFail.setVisibility(View.VISIBLE);
                            ToastUtils.getInstance().showToast("网络连接失败，请连接网络再试...");
                        } else {
                            recyclerView.setVisibility(View.VISIBLE);
                            llNewFail.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onSuccess(String s) {
                        final NewsBean bean = Convert.fromJson(s, NewsBean.class);

                        if (bean.getStatus() == 0) {
                            LatteLogger.d("dddddddddd", GsonBuildUtil.GsonBuilder(bean));
                            recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                            adapter = new NewsAdapter(bean.getData().getRows());
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    // 通知MainFragment跳转至NewFeatureFragment
                                    String id = bean.getData().getRows().get(position).getId() + "";
                                    if (TextUtils.isEmpty(id)) {
                                        ToastUtils.getInstance().showToast( "id为空");
                                    } else {
                                        ((MainFragment) getParentFragment().getParentFragment()).startBrotherFragment
                                                (DetailContentFragment.newInstance(getString(R.string.news_details), id, ""));
                                    }
                                }
                            });
                        } else {
                            ToastUtils.getInstance().showToast(bean.getMsg());
                        }
                    }
                });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initGson();
    }


    @OnClick(R.id.btn_return)
    public void onViewClicked() {
        initGson();
        recyclerView.setVisibility(View.VISIBLE);
        llNewFail.setVisibility(View.GONE);
    }
}




