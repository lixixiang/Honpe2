package com.example.lxx.myalipay.ui.staff.query.ui.position17;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean.SelectCarBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.SensorEventHelper;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.dialog.UpdateTipsDialog;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.example.lxx.myalipay.api.FinalClass.OPEN_GPS;


/**
 * created by lxx at 2019/10/28 17:12
 * 描述:外勤定位
 */
public class LocationFragment extends BaseBackFragment implements AMapLocationListener, LocationSource {
    public String TAG = LocationFragment.class.getName();
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.tv_location)
    TextView tvAddress;
    @BindView(R.id.et_remark)
    EditText etReMark;
    @BindView(R.id.tv_car_no)
    TextView tvCarNo;
    @BindView(R.id.ll_map)
    LinearLayout llMap;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    AMap aMap;

    private String mTitle;
    private AMapLocationClient locationClientContinue;
    private AMapLocationClientOption locationClientOption;
    private OnLocationChangedListener mListener;

    private Bundle savedInstanceState;
    List<String> mList = new ArrayList<>();
    private double longitude, latitude;
    //////////////////////////添加方向指示/////////////////////////////////////
    private SensorEventHelper mSensorHelper;
    private boolean mFirstFix = false;
    private int continueCount = 0;

    public static final LocationFragment newInstance(String msg) {
        LocationFragment fragment = new LocationFragment();
        fragment.mTitle = msg;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_location;
    }


    @Override
    public void initView() {
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        title.setText(mTitle);
        initToolbarNav(llBack);
        checkLocationPermission();
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }

        mSensorHelper = new SensorEventHelper(_mActivity);
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
    }

    private void setUpMap() {
        aMap.getUiSettings().setZoomControlsEnabled(true);//缩放按钮是否显示
        aMap.setLocationSource(this);//设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

    }

    @AfterPermissionGranted(OPEN_GPS)
    private void checkLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(_mActivity, perms)) {
            //没有权限，向系统申请该权限。
            LatteLogger.d("已获取定位权限");
            if (checkGpsIsOpen()) {
                LatteLogger.d("已经打开GPS了!");
            } else {
                UpdateTipsDialog dialog = new UpdateTipsDialog(_mActivity, "打开GPS", "通过定位权限来获取定位", "确定", "取消");
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
                dialog.setOnSureUpdate(new UpdateTipsDialog.onSureUpdate() {
                    @Override
                    public void onClick() {
                        //跳转到手机原生设置页面
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, OPEN_GPS);
                    }
                });
            }

        } else {
            LatteLogger.d("MY", "没有权限");
            EasyPermissions.requestPermissions(_mActivity, "检测到没打开GPS权限，是否允许！", OPEN_GPS, perms);
            if (checkGpsIsOpen()) {
                LatteLogger.d("已经打开GPS了!");
            } else {
                UpdateTipsDialog dialog = new UpdateTipsDialog(_mActivity, "打开GPS", "通过定位权限来获取定位", "确定", "取消");
                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
                dialog.setOnSureUpdate(new UpdateTipsDialog.onSureUpdate() {
                    @Override
                    public void onClick() {
                        //跳转到手机原生设置页面
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, OPEN_GPS);
                    }
                });
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_GPS) {
            checkLocationPermission();
        }
    }

    private boolean checkGpsIsOpen() {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) _mActivity.getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isOpen;
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                //定位之后的回调时间
                continueCount ++;
                long callBackTime = System.currentTimeMillis();
                StringBuffer sb = new StringBuffer();
                sb.append("持续定位完成 " + continueCount +  "\n");
                sb.append("回调时间: " + DateUtils.formatUTC(callBackTime, null) + "\n");

                tvAddress.setText(aMapLocation.getAddress());
                mListener.onLocationChanged(aMapLocation);
                longitude = aMapLocation.getLongitude();
                latitude = aMapLocation.getLatitude();
                addCircle();//添加定位精度圆
                if(null == aMapLocation){
                    sb.append("定位失败：location is null!!!!!!!");
                } else {
                    sb.append(DateUtils.getLocationStr(aMapLocation));
                }
                LatteLogger.d("持续定位完成",sb.toString());
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
                Toast.makeText(_mActivity, errText, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void addCircle() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(
                android.R.color.transparent));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
    }

    private void getSelectCarNo() {
        EasyHttp.post(Constants.MANAGER_CAR + session)
                .execute(new SimpleCallBack<String>() {
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
                    }

                    @Override
                    public void onSuccess(String result) {
                        SelectCarBean bean = Convert.fromJson(result, SelectCarBean.class);
                        LatteLogger.d(GsonBuildUtil.GsonBuilder(bean));
                        if (bean.getStatus() == 0) {
                            mList.clear();
                            for (int i = 0; i < bean.getData().size(); i++) {
                                mList.add(bean.getData().get(i).getCarNo());
                            }
                            final String[] mItem = mList.toArray(new String[mList.size()]);

                            final NormalListDialog dialog = new NormalListDialog(_mActivity, mItem);
                            dialog.title("请选择车辆")
                                    .widthScale(0.7f)
                                    .heightScale(0.7f)
                                    .titleTextSize_SP(14f)
                                    .itemTextSize(14f)
                                    .itemTextColor(Color.BLACK)
                                    .layoutAnimation(null)
                                    .show();
                            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                @Override
                                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    tvCarNo.setText(mItem[position]);
                                    dialog.dismiss();
                                }
                            });
                        } else {

                        }
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
        setUpMap();
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16f));
    }

    /**
     * 停止连续客户端
     */
    void stopContinueLocation(){
        if(null != locationClientContinue){
            locationClientContinue.stopLocation();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
            mSensorHelper.setCurrentMarker(null);
            mSensorHelper = null;
        }
        mMapView.onPause();
        deactivate();
        mFirstFix = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @OnClick({R.id.ll_back, R.id.tv_car_no, R.id.btn_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                _mActivity.onBackPressed();
                break;
            case R.id.tv_car_no:
                getSelectCarNo();
                break;
            case R.id.btn_location:
                submitLocation();
                break;
        }
    }

    private void submitLocation() { //定位接口
        EasyHttp.post(Constants.FIELD_ORIENTATION + session)
                .params("F_EmpNo", tvCarNo.getText().toString())
                .params("F_Positions", tvAddress.getText().toString())
                .params("F_Longitude", longitude + "")
                .params("F_Latitude", latitude + "")
                .params("F_Remarks", etReMark.getText().toString())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("submitLocation",s);
                        try {
                            JSONObject object = new JSONObject(s);
                            ToastUtils.getInstance().showToast(object.getString("Msg"));
                            if (object.getInt("Status") == 0) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        stopContinueLocation();
                                        _mActivity.onBackPressed();
                                    }
                                }, 3000);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }



    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (locationClientContinue == null) {
            locationClientContinue = new AMapLocationClient(_mActivity);
            // 设置定位监听
            locationClientContinue.setLocationListener(this);
            //定位参数
            locationClientOption = new AMapLocationClientOption();
            //地址信息
            locationClientOption.setNeedAddress(true);
            //设置定位间隔,单位毫秒,默认为2000ms
            locationClientOption.setInterval(2000);

            //设置为高精度定位模式
            locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置为单次定位
            locationClientOption.setOnceLocation(false);
            //设置定位参数
            locationClientContinue.setLocationOption(locationClientOption);
            locationClientContinue.startLocation();
            locationClientOption.setSensorEnable(false);
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (locationClientContinue != null) {
            locationClientContinue.stopLocation();
            locationClientContinue.onDestroy();
        }
        locationClientContinue = null;
        stopContinueLocation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != locationClientContinue) {
            locationClientContinue.onDestroy();
            locationClientContinue = null;
        }
        stopContinueLocation();
    }
}





