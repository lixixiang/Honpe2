package com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.bumptech.glide.Glide;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarMapInfoBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarReturnBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.CarlonLatBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.map.ShowAllMapActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.map.ShowAllMapProgressActivity;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.PointsUtil;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.BaseListView;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.api.FinalClass.Tokey;
import static com.example.lxx.myalipay.api.FinalClass.UserId;


/**
 * @author lxx
 * @date 2019/11/28
 * 派车详情页设计
 */
public class DetailListActivity extends BaseActivity implements AMap.OnMapLoadedListener {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.car_icon)
    ImageView carIcon;
    @BindView(R.id.iv_car_status)
    ImageView ivCarStatus;
    @BindView(R.id.carId)
    TextView carId;
    @BindView(R.id.plateNumber)
    TextView plateNumber;
    @BindView(R.id.car_name)
    TextView carName;
    @BindView(R.id.tv_driver_name)
    TextView tvDriverName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.carStatus)
    TextView carStatus;
    @BindView(R.id.apply_man)
    TextView applyMan;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.apply_time)
    TextView applyTime;
    @BindView(R.id.OrderTime)
    TextView OrderTime;
    @BindView(R.id.applyCarNo)
    TextView applyCarNo;
    @BindView(R.id.depart)
    TextView depart;
    @BindView(R.id.team)
    TextView team;
    @BindView(R.id.goLong)
    TextView goLong;
    @BindView(R.id.items)
    TextView items;
    @BindView(R.id.event)
    TextView event;
    @BindView(R.id.EstimatedRTime)
    TextView EstimatedRTime;
    @BindView(R.id.outTime)
    TextView outTime;
    @BindView(R.id.backTime)
    TextView backTime;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.backM)
    TextView backM;
    @BindView(R.id.goM)
    TextView goM;
    @BindView(R.id.listView)
    BaseListView progressListView;
    @BindView(R.id.scroll)
    ScrollView scroll;
    @BindView(R.id.bottom_line)
    View bottomLine;
    @BindView(R.id.re_map)
    RelativeLayout reMap;

    private MapView mapView;
    private AMap aMap;

    private String session, driverPhone, PicUrl, CarNo, CarType, CarStatus, Driver, DriverTel, OrderSeq, SendCarNo, DeptName, GroupName,
            UserName, strOrderTime, Entourage, Items, Reason, RetTime, Remarks, Tel, SenCarby, SenCarTime, UserCarDept,
            UserCarGroup, OrderStatus, SetOutTime, Destination, strEstimatedRTime, EstimatedUseTime, param, params = null;
    private int RetMileage, CancelStatus, Status, Mileage, userId, oldPosition;
    private String token, mName, curTime, curOutTime, curRetTime;

    private SimpleDateFormat YearMonthDayHourMin = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat YearMonthDayHourMinSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private List<String> array;
    private InnerDetailAdapter adapter;
    private List<InnerDetailBean> mProgressList = new ArrayList<>();
    private String NewCurOutTime, NewCurRetTime;
    private ArrayList<Double> mDouble = new ArrayList<Double>();
    private SmoothMoveMarker smoothMarker;

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_list;
    }

    @Override
    public void initView() {
        iniIntentData();
        session = (String) MyApplication.get(_mActivity, Session, "");
        token = (String) MyApplication.get(_mActivity, Tokey, "");
        if (token == "" || TextUtils.isEmpty(token)) {
            start(LoginFragment.newInstance());
        }
        userId = (int) MyApplication.get(_mActivity, UserId, 0);
        carId.setVisibility(View.GONE);
        initToolbarNav(llBack);
        tvTitle.setText("派车详情");
        plateNumber.setText(CarNo);
        carName.setText(CarType);
        tvDriverName.setText(Driver);
        tvPhone.setText(driverPhone);
        applyCarNo.setText(SendCarNo);
        OrderTime.setText(strOrderTime);
        //具体事项
        carStatus.setText(CarStatus);
        applyMan.setText(UserName);
        applyTime.setText(SenCarTime);
        outTime.setText(SetOutTime);
        backTime.setText(RetTime);
        depart.setText(DeptName);
        team.setText(GroupName);
        goLong.setText(Entourage);
        items.setText(Items);
        event.setText(Reason);
        address.setText(Destination);
        phone.setText(Tel);
        EstimatedRTime.setText(strEstimatedRTime);
        Glide.with(this).load(PicUrl).into(carIcon);
        //如果没有返厂，设置为当前时间
        curTime = YearMonthDayHourMinSS.format(new Date());
        if (Mileage != 0 && RetMileage != 0) {
            goM.setText(Mileage + "公里");
            backM.setText(RetMileage + "公里");
        } else if (Mileage != 0) {
            goM.setText(Mileage + "公里");
        } else if (RetMileage != 0) {
            backM.setText(RetMileage + "公里");
        } else {
            goM.setVisibility(View.INVISIBLE);
            backM.setVisibility(View.INVISIBLE);
        }
        remark.setText(Remarks);
        setData();//设置进度布局

        reMap.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(RetTime) && !TextUtils.isEmpty(SetOutTime)) {
            curRetTime = YearMonthDayHourMinSS.format(DateUtils.setDate(YearMonthDayHourMin, curTime));
            curOutTime = YearMonthDayHourMinSS.format(DateUtils.setDate(YearMonthDayHourMin, SetOutTime));
            NewCurOutTime = DateUtils.addDateMinut(YearMonthDayHourMinSS, curOutTime, -8);
            NewCurRetTime = DateUtils.addDateMinut(YearMonthDayHourMinSS, curRetTime, -8);
        } else if (!TextUtils.isEmpty(RetTime) && !TextUtils.isEmpty(SetOutTime)) {
            curRetTime = YearMonthDayHourMinSS.format(DateUtils.setDate(YearMonthDayHourMin, RetTime)); //返厂时间
            curOutTime = YearMonthDayHourMinSS.format(DateUtils.setDate(YearMonthDayHourMin, SetOutTime));//出厂时间
            NewCurOutTime = DateUtils.addDateMinut(YearMonthDayHourMinSS, curOutTime, -8);
            NewCurRetTime = DateUtils.addDateMinut(YearMonthDayHourMinSS, curRetTime, -8);
        } else {
            reMap.setVisibility(View.GONE);//地图的相对布局
            bottomLine.setVisibility(View.GONE); //地图与审批之间的线
        }
        initMapRequest(NewCurOutTime, NewCurRetTime);
    }

    /**
     * 重新请求数据
     */
    private void initMapRequest(String strSetOutTime, String strReTime) {
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

                        for (CarMapInfoBean.DataBean dataBean : bean.getData()) {
                            String str = dataBean.getMachineName();
                            String[][] object = {new String[]{"-", ""}};
                            String mCarNo = StringUtils.replace(str, object);
                            if (CarNo.equals("粤BQ7J66")) {
                                ToastUtils.getInstance().showToast("此车未装有GPS");
                                mapView.setVisibility(View.GONE);
                                bottomLine.setVisibility(View.GONE);
                            } else {
                                if (mCarNo.equals(CarNo)) {
                                    LatteLogger.d("mCarNo", mCarNo);
                                    item.setCarId(dataBean.getCarId());
                                    requestMap(item, strSetOutTime, strReTime);
                                    mapView.setVisibility(View.VISIBLE);
                                    bottomLine.setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                    }
                });
    }

    private void requestMap(CarReturnBean item, String strSetOutTime, String strReTime) {
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
                            reMap.setVisibility(View.GONE);
                        } else if (beans.getRet() == 1 && beans.getData().size() > 1) {
                            for (int i = 0; i < beans.getData().size(); i++) {
                                mDouble.add(beans.getData().get(i).getLonc());
                                mDouble.add(beans.getData().get(i).getLatc());
                            }
                            initMoveMarker(mDouble);
                        } else {
                            reMap.setVisibility(View.GONE);
                        }
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
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(points.get((points.size() - 2)), 16));
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
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) { //双击

                } else { //单击
                    if (TextUtils.isEmpty(RetTime)) {
                        Intent intent1 = new Intent(mContext, ShowAllMapActivity.class);
                        intent1.putExtra("carNo", CarNo);
                        intent1.putExtra("NewCurOutTime", NewCurOutTime);
                        intent1.putExtra("NewCurRetTime", NewCurRetTime);
                        startActivity(intent1);
                    } else {
                        String NewCurOutTime = DateUtils.addDateMinut(YearMonthDayHourMin,
                                SetOutTime, -8);
                        String NewCurRetTime = DateUtils.addDateMinut(YearMonthDayHourMin,
                                RetTime, -8);
                        Intent intent = new Intent(mContext, ShowAllMapProgressActivity.class);
                        intent.putExtra("SetOutTime", NewCurOutTime);
                        intent.putExtra("RetTime", NewCurRetTime);
                        intent.putExtra("CarNo", CarNo);
                        intent.putExtra("Destination", Destination);
                        startActivity(intent);
                    }
                }
            }
        });
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

    private void setData() {
        adapter = new InnerDetailAdapter(_mActivity, getJsonData());
        progressListView.setAdapter(adapter);
    }

    private List<InnerDetailBean> getJsonData() {
        if (TextUtils.isEmpty(SenCarby) || "".equals(SenCarby)) {
            InnerDetailBean item = new InnerDetailBean();
            item.setName(UserName);
            item.setSenStatus("发起人:");
            item.setContent(Reason);
            item.setDate(strOrderTime);
            item.setCarStatus(OrderStatus);
            item.setArray(array);
            mProgressList.add(item);
        } else {
            String[] fMan = {"发起人:", "派车人:"};
            String[] sMan = {UserName, SenCarby};
            String[] sDate = {strOrderTime, SenCarTime};
            String[] sContent = {Reason, Remarks};
            String[] sStatus = {OrderStatus, OrderStatus};
            for (int i = 0; i < sMan.length; i++) {
                InnerDetailBean item = new InnerDetailBean();
                item.setSenStatus(fMan[i]);
                item.setName(sMan[i]);
                item.setContent(sContent[i]);
                item.setDate(sDate[i]);
                item.setCarStatus(sStatus[i]);
                mProgressList.add(item);
            }
        }
        return mProgressList;
    }

    private void iniIntentData() {
        driverPhone = getIntent().getStringExtra("driverPhone");
        PicUrl = getIntent().getStringExtra("PicUrl");//图片
        CarNo = getIntent().getStringExtra("CarNo");//车牌号
        CarType = getIntent().getStringExtra("CarType"); //车名
        CarStatus = getIntent().getStringExtra("CarStatus");//车辆状态
        Driver = getIntent().getStringExtra("Driver"); //司机
        DriverTel = getIntent().getStringExtra("DriverTel");// 司机电话
        OrderSeq = getIntent().getStringExtra("OrderSeq"); //用车时间
        SendCarNo = getIntent().getStringExtra("SendCarNo");//申请单号
        DeptName = getIntent().getStringExtra("DeptName");//部门
        GroupName = getIntent().getStringExtra("GroupName"); //组别
        UserName = getIntent().getStringExtra("UserName");//申请人
        strOrderTime = getIntent().getStringExtra("OrderTime"); //申请时间
        Entourage = getIntent().getStringExtra("Entourage"); //随行人员
        Items = getIntent().getStringExtra("Items"); //物品
        Reason = getIntent().getStringExtra("Reason"); //原因
        RetTime = getIntent().getStringExtra("RetTime"); //回厂时间
        RetMileage = getIntent().getIntExtra("RetMileage", 0);//返回公里
        Mileage = getIntent().getIntExtra("Mileage", 0); //里程
        Remarks = getIntent().getStringExtra("Remarks");//备注
        Tel = getIntent().getStringExtra("Tel"); ///电话
        SenCarby = getIntent().getStringExtra("SenCarby"); //派车人
        SenCarTime = getIntent().getStringExtra("SenCarTime");   //用车时间
        CancelStatus = getIntent().getIntExtra("CancelStatus", 0); //取消状态
        UserCarDept = getIntent().getStringExtra("UserCarDept");//用车部门
        UserCarGroup = getIntent().getStringExtra("UserCarGroup");//用车组别
        Status = getIntent().getIntExtra("Status", -1);//当前状态
        OrderStatus = getIntent().getStringExtra("OrderStatus");//派车状态
        SetOutTime = getIntent().getStringExtra("SetOutTime"); //出厂时间
        Destination = getIntent().getStringExtra("Destination");//目的地
        strEstimatedRTime = getIntent().getStringExtra("EstimatedRTime");  //预计返回时间
        EstimatedUseTime = getIntent().getStringExtra("EstimatedUseTime");
        array = getIntent().getStringArrayListExtra("opts");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = findViewById(R.id.TextureMapView);
        mapView.onCreate(savedInstanceState);
        initMap();
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.getUiSettings().setZoomControlsEnabled(false);//设为置右下角放大缩小图标
        }
    }


    @Override
    protected void onResume() { //方法必须重写
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() { //方法必须重写
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMapLoaded() {

    }
}






















