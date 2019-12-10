package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.map;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
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
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarMapInfoBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarReturnBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarlonLatBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.PointsUtil;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.api.FinalClass.Tokey;
import static com.example.lxx.myalipay.api.FinalClass.UserId;


/**
 * @author lxx
 * @date 2019/11/28
 * 全图
 */

public class ShowAllMapActivity extends BaseActivity implements AMap.OnMapLoadedListener {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.move_start_button)
    Button moveStartButton;
    @BindView(R.id.activity_main)
    RelativeLayout mMapReLayout;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.ll_tips)
    LinearLayout llTips;

    MapView mapView;
    private AMap aMap;
    private SmoothMoveMarker smoothMarker;

    ArrayList<Double> mDouble = new ArrayList<>();
    private String token, CarNo, NewCurOutTime, NewCurRetTime,session;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.show_all_map;
    }

    @Override
    public void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("全图显示");
        moveStartButton.setVisibility(View.GONE);
        session = (String) MyApplication.get(_mActivity, Session, "");
        token = (String) MyApplication.get(_mActivity, Tokey, "");
        userId = (int) MyApplication.get(_mActivity, UserId, 0);

        CarNo = getIntent().getStringExtra("carNo");
        NewCurOutTime = getIntent().getStringExtra("NewCurOutTime");
        NewCurRetTime = getIntent().getStringExtra("NewCurRetTime");
        initMapRequest(NewCurOutTime,NewCurRetTime);
    }

    @Override
    public void onMapLoaded() {
        List<LatLng> points = readLatLngs(mDouble);
        LatLng drivePoint = points.get((points.size() - 1));//测试得出
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(drivePoint));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        AMapUtils.calculateLineDistance(points.get((points.size() - 2)),points.get((points.size() - 1)));//获取两点之前距离
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 重新请求数据
     */
    private void initMapRequest(final String strSetOutTime, final String strReTime){
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
                        final CarReturnBean item = new CarReturnBean();
                        for (int i = 0; i < bean.getData().size(); i++) {
                            String str = bean.getData().get(i).getMachineName();
                            String[][] object = {new String[]{"-", ""}};
                            String mCarNo = StringUtils.replace(str, object);
                            if (mCarNo.equals(CarNo)) {
                                LatteLogger.d("mCarNo", mCarNo);
                                item.setCarId(bean.getData().get(i).getCarId());
                                break;
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

                                            initMoveMarker(mDouble);

                                        } else {
                                            mMapReLayout.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    }
                });
    }

    private void initMoveMarker(ArrayList<Double> mDouble) {
        addPolylineInPlayGround();
        // 获取轨迹坐标点
        List<LatLng> points = readLatLngs(mDouble);
        LatLngBounds.Builder b = LatLngBounds.builder();

        for (int i = 0; i < points.size(); i++) {
            b.include(points.get(i));
        }
        LatLngBounds bounds = b.build();
        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
        smoothMarker = new SmoothMoveMarker(aMap);
        smoothMarker.setDescriptor(BitmapDescriptorFactory.fromView(_mActivity.getLayoutInflater().inflate(R.layout.map_car_size, null)));  // 设置滑动的图标
        LatLng drivePoint = points.get((points.size() - 2));//测试得出
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(points.get((points.size() - 2)),16));
        //设置起点位置
        Marker StartMarker = aMap.addMarker(new MarkerOptions().position(points.get(0)).icon(
                BitmapDescriptorFactory.fromResource(R.mipmap.start)));
        StartMarker.setVisible(true);

        Pair<Integer, LatLng> pair = PointsUtil.calShortestDistancePoint(points, drivePoint);
        points.set(pair.first, drivePoint);
        List<LatLng> subList = points.subList(pair.first, points.size());
        smoothMarker.setPoints(subList);//设置平滑移动的轨迹list
        smoothMarker.setTotalDuration(40);//设置平滑移动的总时间
        smoothMarker.startSmoothMove();  //开始滑动
    }

    private void addPolylineInPlayGround() {
        aMap.setOnMapLoadedListener(this);
        List<LatLng> list = readLatLngs(mDouble);
        List<Integer> colorList = new ArrayList<Integer>();
        aMap.addPolyline(new PolylineOptions()
                .setCustomTexture(BitmapDescriptorFactory.fromResource(R.mipmap.custtexture)) //setCustomTextureList(bitmapDescriptors)
                .addAll(list)
                .useGradient(true)
                .width(25));
    }

    private List<LatLng> readLatLngs(ArrayList<Double> mDouble) {
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < mDouble.size(); i += 2) {
            points.add(new LatLng(mDouble.get(i + 1), mDouble.get(i)));
        }
        return points;
    }
}

















