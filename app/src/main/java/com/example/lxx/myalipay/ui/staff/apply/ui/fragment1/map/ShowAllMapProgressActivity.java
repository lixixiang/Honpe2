package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarMapInfoBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarReturnBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarlonLatBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.Tokey;


/**
 * @author lxx
 * @date 2019/11/28
 * 显示全图
 */
public class ShowAllMapProgressActivity extends BaseActivity {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.move_start_button)
    Button mStartButton;
    @BindView(R.id.activity_main)
    RelativeLayout mMapReLayout;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.ll_tips)
    LinearLayout llTips;

    MapView mapView;
    private AMap aMap;
    private SmoothMoveMarker moveMarker;

    private String NewCurOutTime, NewCurRetTime, mudidi, token, CarNo;

    private static final int START_STATUS = 0;

    private static final int MOVE_STATUS = 1;

    private static final int PAUSE_STATUS = 2;
    private static final int FINISH_STATUS = 3;
    private int mMarkerStatus = START_STATUS;

    ArrayList<Double> mDouble = new ArrayList<>();

    private int userId;

    @Override
    public int getLayoutId() {
        return R.layout.show_all_map;
    }

    @Override
    public void initView() {
        tvTitle.setText("全图显示");
        initToolbarNav(llBack);
        NewCurOutTime = getIntent().getStringExtra("SetOutTime");
        NewCurRetTime = getIntent().getStringExtra("RetTime");
        mudidi = getIntent().getStringExtra("Destination");
        CarNo = getIntent().getStringExtra("CarNo");

        if (TextUtils.isEmpty(NewCurOutTime)) {
            llTips.setVisibility(View.VISIBLE);
            mMapReLayout.setVisibility(View.GONE);
            tvTips.setText("此车尚未出厂！");
        } else {
            mMapReLayout.setVisibility(View.VISIBLE);
            llTips.setVisibility(View.GONE);
            initMapRequest(NewCurOutTime, NewCurRetTime);
        }

    }

    /**
     * 重新请求数据
     */
    private void initMapRequest(final String strSetOutTime, final String strReTime) {
        token = (String) MyApplication.get(_mActivity, Tokey, "");
        userId = (int) MyApplication.get(_mActivity, FinalClass.UserId, 0);

        EasyHttp.post(Constants.getCar)
                .params("token", token)
                .params("userId", userId + "")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        LatteLogger.d(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("getCarInfo", s);
                        final CarMapInfoBean bean = Convert.fromJson(s, CarMapInfoBean.class);
                        CarReturnBean item = new CarReturnBean();
                        for (int i = 0; i < bean.getData().size(); i++) {
                            String str = bean.getData().get(i).getMachineName();
                            String[][] object = {new String[]{"-", ""}};
                            String mCarNo = StringUtils.replace(str, object);
                            if (mCarNo.equals(CarNo)) {
                                LatteLogger.d("mCarNo", mCarNo);
                                item.setCarId(bean.getData().get(i).getCarId());
                                llTips.setVisibility(View.GONE);
                                mMapReLayout.setVisibility(View.VISIBLE);
                                break;
                            } else {
                                llTips.setVisibility(View.VISIBLE);
                                tvTips.setText("此车尚未装有GPS!");
                                mMapReLayout.setVisibility(View.GONE);
                            }
                        }
                        LatteLogger.d("SetOutTime", "SetOutTime==" + strSetOutTime + "==curRetTime" + strReTime);

                        EasyHttp.post(Constants.getHistory)
                                .params("token", token)
                                .params("carId", item.getCarId() + "")
                                .params("mapType", "2")
                                .params("startTime", strSetOutTime)
                                .params("endTime", strReTime)
                                .execute(new SimpleCallBack<String>() {
                                    @Override
                                    public void onError(ApiException e) {
                                        LatteLogger.d("History", e.getMessage());
                                    }

                                    @Override
                                    public void onSuccess(String s) {
                                        LatteLogger.d("CarlonLatBean", s);
                                        CarlonLatBean beans = Convert.fromJson(s, CarlonLatBean.class);

                                        if (beans.getRet() == 1 && beans.getData().size() == 1) {
                                            mMapReLayout.setVisibility(View.GONE);
                                        } else if (beans.getRet() == 1 && beans.getData().size() > 1) {
                                            for (int i = 0; i < beans.getData().size(); i++) {
                                                mDouble.add(beans.getData().get(i).getLonc());
                                                mDouble.add(beans.getData().get(i).getLatc());
                                            }
                                            initMoveMarker();

                                        } else {
                                            mMapReLayout.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    }
                });
    }

    private static Handler Handler = new Handler();
    private static Runnable mRunnable = null;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 60000:
                    mMarkerStatus = FINISH_STATUS;
                    mStartButton.setText("回放");
                    break;
                default:
                    break;
            }
        }
    };

    private void initMoveMarker() {
        addPolylineInPlayGround();
        // 获取轨迹坐标点
        List<LatLng> points = readLatLngs();
        LatLngBounds.Builder b = LatLngBounds.builder();
        for (int i = 0; i < points.size(); i++) {
            b.include(points.get(i));
        }
        LatLngBounds bounds = b.build();
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        moveMarker = new SmoothMoveMarker(aMap);
        // 设置滑动的图标
        moveMarker.setDescriptor(BitmapDescriptorFactory.fromResource(R.mipmap.car));
        //设置起点,终点位置
        Marker StartMarker = aMap.addMarker(new MarkerOptions().position(points.get(0)).icon(
                BitmapDescriptorFactory.fromResource(R.mipmap.start)));
        Marker EndMarker = aMap.addMarker(new MarkerOptions().position(points.get(points.size() - 1)).icon(
                BitmapDescriptorFactory.fromResource(R.mipmap.end)));
        StartMarker.setVisible(true);
        EndMarker.setVisible(true);

        // 设置滑动的轨迹左边点
        moveMarker.setPoints(points);
        moveMarker.setTotalDuration(40);//设置平滑移动的总时间
        try {
            moveMarker.setMoveListener(new SmoothMoveMarker.MoveListener() {
                @Override
                public void move(final double distance) {
                    mRunnable = new Runnable() {
                        @Override
                        public void run() {
                            if (distance == 0) {
                                Message message = new Message();
                                message.what = 60000;
                                mHandler.sendMessage(message);
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LatteLogger.d(e.getMessage());
        }
    }

    private void addPolylineInPlayGround() {
        List<LatLng> list = readLatLngs();
        aMap.addPolyline(new PolylineOptions()
                .setCustomTexture(BitmapDescriptorFactory.fromResource(R.mipmap.custtexture)) //setCustomTextureList(bitmapDescriptors)
                .addAll(list)
                .useGradient(true)
                .width(18));
    }

    private List<LatLng> readLatLngs() {
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < mDouble.size(); i += 2) {
            points.add(new LatLng(mDouble.get(i + 1), mDouble.get(i)));
        }
        return points;
    }


    @OnClick({R.id.move_start_button, R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.move_start_button:
                if (mMarkerStatus == START_STATUS) {
                    moveMarker.startSmoothMove();
                    mMarkerStatus = MOVE_STATUS;
                    mStartButton.setText("暂停");
                } else if (mMarkerStatus == MOVE_STATUS) {
                    moveMarker.stopMove();
                    mMarkerStatus = PAUSE_STATUS;
                    mStartButton.setText("继续");
                } else if (mMarkerStatus == PAUSE_STATUS) {
                    moveMarker.startSmoothMove();
                    mMarkerStatus = MOVE_STATUS;
                    mStartButton.setText("暂停");
                } else if (mMarkerStatus == FINISH_STATUS) {
                    moveMarker.setPosition(new LatLng(mDouble.get(1), mDouble.get(0)));
                    List<LatLng> points = readLatLngs();
                    moveMarker.setPoints(points);
                    moveMarker.startSmoothMove();
                    mMarkerStatus = MOVE_STATUS;
                    mStartButton.setText("暂停");
                }
                break;
            case R.id.ll_back:
                mapView.onDestroy();
                Handler.removeCallbacks(mRunnable);
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
    }

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    @Override
    public void onBackPressedSupport() {
        mapView.onDestroy();
        Handler.removeCallbacks(mRunnable);
        super.onBackPressedSupport();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        Handler.removeCallbacks(mRunnable);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();

    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
}
