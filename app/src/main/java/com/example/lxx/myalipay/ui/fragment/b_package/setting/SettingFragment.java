package com.example.lxx.myalipay.ui.fragment.b_package.setting;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.entity.UpdateBean;
import com.example.lxx.myalipay.ui.fragment.b_package.setting.feedBack.FeedBackFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.setting.mob_pass.MobPassFragment;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.Utils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.utils.un_connection_wifi.ConnNetworkState;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.UPDATA_CLIENT;
import static com.example.lxx.myalipay.api.FinalClass.UPDATA_NONEED;


/**
 * created by lxx at 2019/11/22 17:12
 * 描述:设置页面
 */
public class SettingFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv)
    ListView lv;

    private SimpleAdapter dapter;
    private String[] titles = {"意见反馈", "检测新版本", "修改密码", "语言转文字", "退出登录"};
    private String mTitle;
    //自动更新
    private UpdateBean updateBean;

    public static SettingFragment newInstance(String title) {
        SettingFragment fragment = new SettingFragment();
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView() {
        tvTitle.setText(mTitle);
        initToolbarNav(ivLeft);
        List<Map<String, String>> imageList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("title", titles[i]);
            imageList.add(map);
        }
        //创建适配器
        dapter = new SimpleAdapter(_mActivity, imageList,
                R.layout.css_text_1, new String[]{"title"}, new int[]{R.id.tv_record_text});
        lv.setAdapter(dapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        start(FeedBackFragment.newInstance(titles[position]));
                        break;
                    case 1:
                        jsonData();
                        break;
                    case 2:
                        start(MobPassFragment.newInstance());
                        break;
                    case 3:
                        break;
                    case 4:
                        NormalDialog dialog = new NormalDialog(_mActivity);
                        dialog.isTitleShow(false)
                                .bgColor(Color.WHITE)
                                .cornerRadius(5)
                                .content("是否确定退出程序?")//
                                .contentGravity(Gravity.CENTER)//
                                .contentTextColor(Color.BLACK)//
                                .dividerColor(Color.parseColor("#222222"))//
                                .btnTextSize(15.5f, 15.5f)
                                .btnTextColor(getResources().getColor(R.color.google_red), getResources().getColor(R.color.google_blue))//
                                .btnPressColor(getResources().getColor(R.color.background_e))//
                                .widthScale(0.85f)//
                                .show();
                        dialog.setOnBtnClickL(new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                dialog.dismiss();
                            }
                        }, new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                MyApplication.clear(_mActivity);
                                EventBusUtil.sendEvent(new Event(FinalClass.EXIT_APP));
                                dialog.dismiss();
                            }
                        });
                        break;

                }
            }
        });
    }

    /**
     * 检测版本
     */
    private void jsonData() {
        EasyHttp.post(Constants.APPDownload)
                .params("app_ver", String.valueOf(Utils.getVersionCode(_mActivity)))
                .params("app_desc", String.valueOf(Utils.getVersionCode(_mActivity)))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (e.getCode() == 1009) {
                            new ConnNetworkState(_mActivity).checkNetworkState();
                        } else {
                            ToastUtils.getInstance().showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("progress", result);
                        updateBean = Convert.fromJson(result, UpdateBean.class);
                        LatteLogger.d("ddddddd", GsonBuildUtil.GsonBuilder(updateBean));
                        if (updateBean.getStatus() == 0) {
                            final int num = Integer.valueOf(updateBean.getData().getVer());
                            if (num == Utils.getVersionCode(_mActivity)) {
                                Log.i("version", "版本一致");
                                EventBusUtil.sendEvent(new Event(UPDATA_NONEED));
                            } else if (Utils.getVersionCode(_mActivity) < num) {
                                Log.i("version", "版本不一致");
                                EventBusUtil.sendEvent(new Event(UPDATA_CLIENT));
                            }
                        } else {
                            ToastUtils.getInstance().showToast(updateBean.getMsg());
                        }
                    }
                });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case UPDATA_NONEED:
                ToastUtils.getInstance().showToast("已经更新到最新版本：" + Utils.getVersionDes(_mActivity));
                break;
        }
    }
}


























