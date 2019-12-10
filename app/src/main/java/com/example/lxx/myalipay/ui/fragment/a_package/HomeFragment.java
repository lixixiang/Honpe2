package com.example.lxx.myalipay.ui.fragment.a_package;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.lxx.myalipay.MainFragment;
import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseMainFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.adapter.CommFragmentPagerAdapter;
import com.example.lxx.myalipay.ui.fragment.a_package.adapter.HomeGridViewAdapter;
import com.example.lxx.myalipay.ui.fragment.a_package.entity.UpdateBean;
import com.example.lxx.myalipay.ui.fragment.a_package.entity.UserCenterBean;
import com.example.lxx.myalipay.ui.fragment.a_package.news.DetailContentFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.news.NewsFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.news.VideoFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.appoint.AppointHomeFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.ui.logistics.LogisticsCheckFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.login.bean.LoginBean;
import com.example.lxx.myalipay.ui.staff.StaffHomeFragment;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.TabEntity;
import com.example.lxx.myalipay.utils.Utils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.utils.un_connection_wifi.ConnNetworkState;
import com.example.lxx.myalipay.widget.BaseGridView;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.scan.ScanManager;
import com.example.lxx.myalipay.widget.update.DownLoadAPkDialog;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.barlibrary.ImmersionBar;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import me.jessyan.autosize.utils.ScreenUtils;
import pub.devrel.easypermissions.EasyPermissions;

import static com.example.lxx.myalipay.api.FinalClass.EXIT_APP;
import static com.example.lxx.myalipay.api.FinalClass.ME_info;
import static com.example.lxx.myalipay.api.FinalClass.REQUEST_CODE_APP_INSTALL;
import static com.example.lxx.myalipay.api.FinalClass.UPDATA_CLIENT;
import static com.example.lxx.myalipay.api.FinalClass.UPDATA_NONEED;
import static com.example.lxx.myalipay.api.FinalClass.video_status;
import static com.example.lxx.myalipay.utils.Utils.dp2px;
import static com.lcodecore.tkrefreshlayout.utils.DensityUtil.px2dp;


/**
 * created by lxx at 2019/11/11 11:27
 * 描述:
 */
public class HomeFragment extends BaseMainFragment implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.abl_bar)
    AppBarLayout abl_bar;
    @BindView(R.id.include_toolbar_small)
    View include_toolbar_small;
    @BindView(R.id.v_title_big_mask)
    View v_title_big_mask;
    @BindView(R.id.v_toolbar_small_mask)
    View v_toolbar_small_mask;
    @BindView(R.id.gridView_1)
    BaseGridView mGridView;
    @BindView(R.id.tab)
    CommonTabLayout commonTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;


    private int[] fontColor = {
            R.color.google_coffee, R.color.google_blue,
            R.color.google_yellow, R.color.thin_red, R.color.green};
    private String[] fontIcon;
    private String[] Apptext;
    private int UserType;
    private List<Fragment> mFragment;
    private HomeGridViewAdapter mAdapter;
    private CommFragmentPagerAdapter Adapter; //新闻

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    String[] vpContent;

    String[] StrScans;
    int[] unSelectedColor = {R.color.background_e, R.color.background_e, R.color.background_e};
    int[] selectedColor = {R.color.blue_dark, R.color.blue_dark, R.color.blue_dark};

    private String apkFile;

    //第二次进入主页
    private boolean isExit; //登录与退出
    //自动更新
    private UpdateBean updateBean;

    private float topHeight, tabHeight, bottombarHeight, AllHeight;

    public static HomeFragment newInstance(float bottombarHeight) {
        HomeFragment fragment = new HomeFragment();
        fragment.bottombarHeight = bottombarHeight;
        return fragment;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarColor(R.color.blue_dark)
                .init();
    }

    @Override
    public void onResume() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.blue_dark)
                .init();
        super.onResume();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case UPDATA_NONEED:
                LatteLogger.d("不需要更新");
                break;
            case UPDATA_CLIENT:
                //弹出对话框
                showUpdateDialog(updateBean.getData().getDescription(), Utils.getVersionCode(_mActivity));
                break;
            case REQUEST_CODE_APP_INSTALL:
                apkFile = (String) event.getData();
                installApk();
                break;
            case ME_info: //登录回来得到新的session
                LoginBean loginBean = (LoginBean) event.getData();
                session = loginBean.getLogonUser().getKeyValue();
                UserType = loginBean.getLogonUser().getUserType();
                LatteLogger.d("session=======", session);
                showGridView(UserType);
                mAdapter.notifyDataSetChanged();
                break;
            case EXIT_APP:
                UserType = 3;
                showGridView(UserType);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void installApk() {
        File apk = new File(apkFile);
        LatteLogger.d("apkFile", apk + "");
        Uri uri = Uri.fromFile(apk);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(_mActivity, getContext().getPackageName() + ".fileProvider", apk);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        startActivity(intent);
        Process.killProcess(Process.myPid());
    }

    private void showUpdateDialog(String description, int versionCode) {
        String strTips = getString(R.string.new_version_detected);
        String content = getString(R.string.find_the_latest_version) + description + getString(R.string.Download_update_or_not);
        final NormalDialog dialog = new NormalDialog(_mActivity);
        dialog.title(strTips)
                .titleTextSize(15.5f)
                .bgColor(Color.WHITE)
                .cornerRadius(5)
                .content(content)
                .contentGravity(Gravity.CENTER)
                .contentTextColor(Color.BLACK)
                .btnTextColor(getResources().getColor(R.color.grey_home), getResources().getColor(R.color.gray))
                .btnTextSize(15.5f, 15.5f)
                .btnText("稍候再试", "马上更新")
                .btnPressColor(getResources().getColor(R.color.background))
                .widthScale(0.85f)
                .titleTextSize(20)
                .style(NormalDialog.STYLE_TWO)
                .show();
        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                downLoadAPK();
                dialog.dismiss();
            }
        });
    }

    private void downLoadAPK() {
        final DownLoadAPkDialog pd = new DownLoadAPkDialog(_mActivity, Constants.VERSION);
        pd.setCanceledOnTouchOutside(false);
        pd.show();

    }

    @Override
    protected void initView() {
        //检测版本是否需要更新
        jsonData();
        Apptext = getResources().getStringArray(R.array.home_text);
        fontIcon = getResources().getStringArray(R.array.home_font_icon);
        vpContent = getResources().getStringArray(R.array.news);
        StrScans = getResources().getStringArray(R.array.scan);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));//GridView选中时去除背景色
        UserType = (int) MyApplication.get(_mActivity, FinalClass.UserType, 3);
        showGridView(UserType);
        setViewPager();//设置界面

    }

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

    private void showGridView(int UserType) {
        LatteLogger.d("showGridView", UserType);
        mAdapter = new HomeGridViewAdapter(_mActivity, getData(), UserType);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (UserType == 3) {//第一次进来时，登录入口字样
                            ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                        } else if (UserType == 0) { //员工
                            getStaffLevel();
                        }
                        break;
                    case 1:
                        break;
                    case 2:
                        long nowTime = System.currentTimeMillis();
                        if (nowTime - TOUCH_TIME > WAIT_TIME) {
                            ((MainFragment) getParentFragment()).startBrotherFragment(DetailContentFragment.newInstance("企业简介", "509", ""));
                            TOUCH_TIME = nowTime;
                        } else {
                            //  ToastUtils.getInstance().showToast("不要重复点击，手机会爆的呢");
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 员工级别
     */
    private void getStaffLevel() {
        EasyHttp.post(Constants.ADD_MOBE_DELETER + session)
                .params("ModuleId", "D01")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("dddddaaa", result);
                        Event<Integer> event2 = new Event<Integer>(video_status, 0);
                        EventBusUtil.sendEvent(event2);
                        if (result.contains(sessionE) || result.contains(sessionI)) {
                            long nowTime = System.currentTimeMillis();
                            if (nowTime - TOUCH_TIME > WAIT_TIME) {
                                ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                                TOUCH_TIME = nowTime;
                            } else {
                                //  ToastUtils.getInstance().showToast("不要重复点击，手机会爆的呢");
                            }
                            ToastUtils.getInstance().showToast(sessionPastDue);
                        } else {
                            try {
                                /**
                                 * Status : 0
                                 * Msg : 成功!
                                 * Data : ["查询","新增","修改","确认","审批","派车","出入","作废"]
                                 */
                                long nowTime = System.currentTimeMillis();
                                if (nowTime - TOUCH_TIME > WAIT_TIME) {
                                    JSONObject jsonObject = new JSONObject(result);
                                    LatteLogger.d(jsonObject.getJSONArray("Data"));
                                    ((MainFragment) getParentFragment()).startBrotherFragment(StaffHomeFragment.newInstance());
                                    TOUCH_TIME = nowTime;
                                } else {
                                    //  ToastUtils.getInstance().showToast("不要重复点击，手机会爆的呢");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    private void setViewPager() {
        topHeight = Utils.getRealHeight(abl_bar);
        tabHeight = Utils.getRealHeight2(commonTabLayout);
        AllHeight = Utils.getWindowHeight();
        LatteLogger.d("height", "topHeight==" + topHeight + "====tabHeight===" +
                tabHeight + "===botombarHeight===" + bottombarHeight+"==AllHeight=="
                +AllHeight+"========="+Utils.getPhoneSize(_mActivity)+"====="
                +ScreenUtils.getStatusBarHeight()+"====="+ScreenUtils.getHeightOfNavigationBar(_mActivity));

        float elseHeight = AllHeight - (topHeight + tabHeight + bottombarHeight+ScreenUtils.getStatusBarHeight());
        LatteLogger.d("elseHeight",px2dp(_mActivity,elseHeight));

        mFragment = new ArrayList<>();
        mFragment.add(NewsFragment.newInstance("868", "0"));
        mFragment.add(NewsFragment.newInstance("867", "0"));
        mFragment.add(VideoFragment.newInstance(elseHeight));

        for (int i = 0; i < vpContent.length; i++) {
            mTabEntities.add(new TabEntity(vpContent[i], selectedColor[i], unSelectedColor[i]));
        }

        Adapter = new CommFragmentPagerAdapter(getChildFragmentManager(), mFragment, vpContent);
        viewPager.setAdapter(Adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);//表示缓存2、3两个界面。如此便避免了界面3被销毁。

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
                Event<Integer> event = new Event<Integer>(video_status, position);
                EventBusUtil.sendEvent(event);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LatteLogger.d("state", state);
            }
        });
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private List<UserCenterBean> getData() {
        List<UserCenterBean> mList = new ArrayList<>();
        for (int i = 0; i < fontColor.length; i++) {
            UserCenterBean bean = new UserCenterBean();
            bean.setTitle(Apptext[i]);
            bean.setFontIcon(fontIcon[i]);
            bean.setFontColor(fontColor[i]);
            mList.add(bean);
        }
        return mList;
    }


    @OnClick({R.id.ll_scan, R.id.ll_order, R.id.ll_logistics, R.id.ll_way})
    public void onViewClicked(View view) {
        Event<Integer> event2 = new Event<Integer>(video_status, 0);
        EventBusUtil.sendEvent(event2);
        switch (view.getId()) {
            case R.id.ll_scan:
                //扫一扫
                NormalListDialog normalListDialog = new NormalListDialog(_mActivity, StrScans);
                normalListDialog.title(getString(R.string.scan) + getString(R.string.style))
                        .layoutAnimation(null)
                        .show();
                normalListDialog.setOnOperItemClickL((parent, view1, position, id) -> {
                    switch (position) {
                        case 0:
                            ScanManager.startScan(_mActivity, QrConfig.TYPE_QRCODE, QrConfig.SCANVIEW_TYPE_QRCODE);
                            break;
                        case 1:
                            ScanManager.startScan(_mActivity, QrConfig.TYPE_BARCODE, QrConfig.SCANVIEW_TYPE_BARCODE);
                            break;
                    }
                    normalListDialog.dismiss();
                });
                break;
            case R.id.ll_order:
                //访客预约
                ((MainFragment) getParentFragment()).startBrotherFragment(AppointHomeFragment.newInstance(getString(R.string.order)));
                break;
            case R.id.ll_logistics:
                //物流查询
                ((MainFragment) getParentFragment()).startBrotherFragment(LogisticsCheckFragment.newInstance(getString(R.string.Logistics)));
                break;
            case R.id.ll_way:
                //联系方式
                ((MainFragment) getParentFragment()).startBrotherFragment(DetailContentFragment.newInstance("联系方式", "508", ""));
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}






