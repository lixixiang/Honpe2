package com.example.lxx.myalipay.ui.staff.viewpager_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.viewpager_fragment.detail.BaseMsgAdapter;
import com.example.lxx.myalipay.ui.staff.viewpager_fragment.detail.MsgDetailBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.BaseRecyclerView;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.Session;

public class DetailMsgActivity extends BaseActivity {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.mTitles)
    TextView mTitles;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.recyclerView)
    BaseRecyclerView recyclerView;
    private int position;
    private String id;
    private BaseMsgAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public void initView() {
        tvTitle.setText("消息详情");
        initToolbarNav(llBack);
        id = getIntent().getStringExtra("id");
        position = getIntent().getIntExtra("newstype", -1);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        /**
         * 大概布局就是这样的，完成之后，发现一个问题，就是在滑动的不流畅，
         * 卡顿，基本就是手指移动多长，界面滑动多长距离。
         * 于是找资料，在recycleview添加这两个方法就可以了。
         */
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        setNetRequest(id, position);
    }
    private void setNetRequest(String id, int position) {
        String session = (String) MyApplication.get(_mActivity, Session, "");
        EasyHttp.post(Constants.YGMESSAGEDETAIL + session)
                .params("id", id)
                .params("newstype", position + "")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast( e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        MsgDetailBean bean = Convert.fromJson(result, MsgDetailBean.class);
                        LatteLogger.d("msg", result);
                        if (bean.getStatus() == 0) {
                            String str = bean.getData().getAddTime();
                            String[][] object = {new String[]{"T", " "}};
                            tvDate.setText(StringUtils.replace(str, object));
                            mTitles.setText(bean.getData().getTitle());
                            tvContent.setText(bean.getData().getContent());
                            if (bean.getData().getComments() == 0) {
                                tvCommit.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.GONE);
                            } else {
                                tvCommit.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            tvCommit.setText(bean.getData().getComments() + "条评论");
                            adapter = new BaseMsgAdapter(bean.getData().getCommentsDetails());
                            recyclerView.setAdapter(adapter);
                        } else {
                            ToastUtils.getInstance().showToast(bean.getMsg());
                        }
                    }
                });
    }
}
