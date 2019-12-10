package com.example.lxx.myalipay.widget.dialog;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.ui.staff.query.ui.position2.bean.LeaveNewBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.widget.base.BaseDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by lxx at 2019/12/3 12:02
 * 描述:
 */
public class CustomTypeDialog extends BaseDialog<CustomTypeDialog> {
    @BindView(R.id.rv_menu_list)
    RecyclerView rvMenuList;
    @BindView(R.id.tv_mob)
    TextView tvMob;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.rv_menu_list2)
    RecyclerView rvMenuList2;
    @BindView(R.id.rv_menu_list3)
    RecyclerView rvMenuList3;
    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_titleBar)
    TextView tvTitleBar;
    @BindView(R.id.font_1)
    TextView font1;
    @BindView(R.id.font_2)
    TextView font2;
    @BindView(R.id.font_3)
    TextView font3;

    private String session, title;
    public onDeleteListener listener;
    public onMobClassListener mobListener;

    private LeaveNewBean dataBean;
    private List<LeaveNewBean> leaveNewBeans = new ArrayList<>();

    public void setOnDeleteListener(onDeleteListener listener) {
        this.listener = listener;
    }

    public void setOnMobListener(onMobClassListener listener) {
        this.mobListener = listener;
    }

    public CustomTypeDialog(Context context, LeaveNewBean dataBean, String session, String title) {
        super(context);
        this.dataBean = dataBean;
        this.session = session;
        this.title = title;
    }

    @Override
    public View onCreateView() {
        widthScale(0.9f);
        View inflate = View.inflate(mContext, R.layout.popup_menu, null);
        ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        llTitleBar.setVisibility(View.GONE);
        toolbar.setVisibility(View.VISIBLE);
        tvTitleBar.setText(title + "详情");
        tvMob.setText("修改" + title);
        tvCancel.setText("取消" + title);
        rvMenuList2.setVisibility(View.GONE);
        rvMenuList3.setVisibility(View.GONE);
        rvMenuList.setLayoutManager(new LinearLayoutManager(mContext));
        leaveNewBeans.add(dataBean);
        CustomDormAdapter adapter = new CustomDormAdapter(leaveNewBeans, title);
        rvMenuList.setAdapter(adapter);
    }

    @Override
    public void setUiBeforShow() {

    }

    @OnClick({R.id.tv_mob, R.id.tv_cancel, R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mob:
                dismiss();
                mobListener.onClickMobClass();
                break;
            case R.id.tv_cancel:
                delete();
                listener.onClickDelete();
                dismiss();
                break;
            case R.id.tv_close:
                dismiss();
                break;
        }
    }

    private void delete() {
        EasyHttp.post(Constants.REQUEST_OA_DELETE + session)
                .params("Id", dataBean.get_id())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", "删除成功！");
                        ToastUtils.getInstance().showToast("删除成功！");
                    }
                });
    }

    public interface onDeleteListener {
        void onClickDelete();
    }

    public interface onMobClassListener {
        void onClickMobClass();
    }

    public class CustomDormAdapter extends BaseQuickAdapter<LeaveNewBean, BaseViewHolder> {
        private String title;

        public CustomDormAdapter(@Nullable List<LeaveNewBean> data, String title) {
            super(R.layout.css_leave2, data);
            this.title = title;
        }

        @Override
        protected void convert(BaseViewHolder helper, LeaveNewBean item) {
            helper.setText(R.id.tv_startTime, item.getStartTime());
            helper.setText(R.id.tv_endTime, item.getEndTime());
            helper.setText(R.id.tv2, item.getReason());
            int t = (new Double(item.getLongTime())).intValue();
            helper.setText(R.id.tv_time_long, String.valueOf(t));
            LatteLogger.d("title=======", title);
            if (title.equals("请假")) {
                helper.setGone(R.id.ll_destination, false);
            } else {
                helper.setText(R.id.tv_destination, item.getDestination());
            }
        }
    }
}

