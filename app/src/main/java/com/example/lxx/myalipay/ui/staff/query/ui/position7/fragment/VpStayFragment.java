package com.example.lxx.myalipay.ui.staff.query.ui.position7.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.adapter.VpStayListAdapter;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.FloorManagerBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.StayBean;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * created by lxx at 2019/12/3 15:35
 * 描述:
 */
public class VpStayFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.elv_collocation)
    ExpandableListView expandableListView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    String titles,session;
    private VpStayListAdapter adapter;
    List<FloorManagerBean> floorList = new ArrayList<>();
    List<FloorManagerBean.FloorDataBean> roomList = new ArrayList<>();
    List<FloorManagerBean.FloorDataBean> roomList2 = new ArrayList<>();
    List<FloorManagerBean.FloorDataBean> roomList3 = new ArrayList<>();
    List<FloorManagerBean.FloorDataBean> roomList4 = new ArrayList<>();
    private String[] BuildNum = {"D栋", "小别墅"};
    String[] DRoom2 = {"201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "218"};
    String[] DRoom3 = {"301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312", "313", "314", "315", "316", "317", "318"};
    String[] DRoom4 = {"401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "414", "415", "416", "417", "418"};
    String[] DRoom5 = {"501", "502", "503", "504", "505", "506", "507", "508", "509", "510", "511", "512", "513", "514", "515", "516", "517", "518"};
    String[] DRoomA1 = {"201A", "202A", "203A", "204A", "205A", "206A", "207A"};
    String[] DRoomA2 = {"301A", "302A", "303A", "304A", "305A", "306A", "307A"};

    int empty = 0;
    int free = 0;
    int full = 0;
    int index;

    public static VpStayFragment newInstance(String title, String session) {
        VpStayFragment fragment = new VpStayFragment();
        fragment.titles = title;
        fragment.session = session;
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_expandable_listview;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
//                FloorManagerBean objectBean = (FloorManagerBean) event.getData();
//                LatteLogger.d("dddd", GsonBuildUtil.GsonBuilder(objectBean));
//                floorList.add(objectBean);
//                adapter.setData(floorList);
//                adapter.refresh(expandableListView,index);
                getNetData();
                break;
                case FinalClass.ME_info:
                    getNetData();
                    break;
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getNetData();
    }

    @Override
    protected void initView() {
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                index = groupPosition;
                if (expandableListView.isGroupExpanded(groupPosition)) {
                    expandableListView.collapseGroup(groupPosition);
                } else {
                    expandableListView.expandGroup(groupPosition);
                }
                return true;
            }
        });

        refreshLayout.setOnRefreshListener(this);
        // 被展开组的监听
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                // 保证每次只有一个组是展开状态
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    if (i != groupPosition) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });
    }

    private void getNetData() {
        LatteLogger.d("titles", titles);
        EasyHttp.post(Constants.REQUEST_DORM + session)
                .params("BuildingNum", titles)
                .params("FloorNum", "")
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                start(LoginFragment.newInstance());
                                ToastUtils.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                StayBean bean = Convert.fromJson(s, StayBean.class);
                                LatteLogger.d("onSuccess", GsonBuildUtil.GsonBuilder(bean));
                                floorList.clear();
                                roomList.clear();
                                roomList2.clear();
                                roomList3.clear();
                                roomList4.clear();
                                adapter = new VpStayListAdapter(_mActivity, getJsonData(bean.getData()));
                                expandableListView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private String strBedNum;

    private List<FloorManagerBean> getJsonData(List<StayBean.DataBean> data) {
        Map<String, FloorManagerBean.FloorDataBean> maps = new LinkedHashMap<>();
        List<String> build = new ArrayList<>();
        List<String> floor = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            build.add(data.get(i).getBuildingNum());
            floor.add(data.get(i).getFloorNum());
            String room = data.get(i).getDormitoryNum();
            FloorManagerBean.FloorDataBean ListRoom = maps.get(room);
            if (ListRoom == null) {
                ListRoom = new FloorManagerBean.FloorDataBean();
            }
            ListRoom.setBedQty(data.get(i).getBedQty());
            ListRoom.setDeptName(data.get(i).getDeptName());
            ListRoom.setDormitoryNum(data.get(i).getDormitoryNum());
            ListRoom.setFreeBedQty(data.get(i).getFreeBedQty());
            ListRoom.setGroupName(data.get(i).getGroupName());
            ListRoom.setParRecid(data.get(i).getRecId());

            List<FloorManagerBean.FloorDataBean.FloorDetailBean> listDetail = new ArrayList<>();
            for (int d = 0; d < ListRoom.getBedQty(); d++) {
                FloorManagerBean.FloorDataBean.FloorDetailBean detailBean =
                        new FloorManagerBean.FloorDataBean.FloorDetailBean();
                if ((d + 1) % 2 == 0) {
                    strBedNum = "床位" + (d + 1) + "(下)";
                } else {
                    strBedNum = "床位" + (d + 1) + "(上)";
                }
                detailBean.setBeNum(strBedNum);
                detailBean.setResidentsName("空床");
                detailBean.setSex("");
                for (int z = 0; z < data.get(i).getDetails().size(); z++) {
                    StayBean.DataBean.DetailsBean detailBean2 = data.get(i).getDetails().get(z);
                    if (detailBean.getBeNum().equals(detailBean2.getBedNum())) {
                        detailBean.setBeNum(detailBean2.getBedNum());
                        detailBean.setResidentsName(detailBean2.getResidentsName());
                        detailBean.setSex(detailBean2.getSex());
                        detailBean.setRowid(detailBean2.getRowid());
                        detailBean.setDepartName(detailBean2.getDeptName());
                        detailBean.setTeam(detailBean2.getGroupName());
                    }
                }
                listDetail.add(detailBean);
            }
            ListRoom.setDetailBeans(listDetail);
            maps.put(room, ListRoom);
        }
        StringUtils.DeleteArrayRepeatStr(build);
        StringUtils.DeleteArrayRepeatStr(floor);

        if (titles.equals(BuildNum[0])) {
            free = 0;
            empty = 0;
            full = 0;
            for (int i = 0; i < floor.size(); i++) {
                FloorManagerBean FloorBean = new FloorManagerBean();
                if (i == 0) {
                    for (int j = 0; j < DRoom2.length; j++) {
                        for (String key : maps.keySet()) {
                            if (key.equals(DRoom2[j])) {
                                FloorManagerBean.FloorDataBean RoomBean = new FloorManagerBean.FloorDataBean();
                                FloorManagerBean.FloorDataBean listData = maps.get(key);
                                RoomBean.setDormitoryNum(listData.getDormitoryNum());
                                RoomBean.setBedQty(listData.getBedQty());
                                RoomBean.setFreeBedQty(listData.getFreeBedQty());
                                RoomBean.setDeptName(listData.getDeptName());
                                RoomBean.setGroupName(listData.getGroupName());
                                RoomBean.setParRecid(listData.getParRecid());
                                RoomBean.setDetailBeans(listData.getDetailBeans());

                                if (RoomBean.getBedQty() > RoomBean.getFreeBedQty() && RoomBean.getFreeBedQty() != 0) {
                                    free++;
                                }
                                if (RoomBean.getBedQty() == RoomBean.getFreeBedQty()) {
                                    empty++;
                                }
                                if (RoomBean.getFreeBedQty() == 0) {
                                    full++;
                                }
                                roomList.add(RoomBean);
                            }
                        }
                    }
                    FloorBean.setBuild(titles);
                    FloorBean.setFloorNum(floor.get(i) + "层");
                    FloorBean.setFloorDataBeanList(roomList);
                    FloorBean.setTotalRoom(DRoom2.length + "");
                    FloorBean.setFreeRoom(free + "");
                    FloorBean.setEmptyRoom(empty + "");
                    FloorBean.setFullRoom(full + "");
                    floorList.add(FloorBean);
                } else if (i == 1) {
                    free = 0;
                    empty = 0;
                    full = 0;
                    for (int j = 0; j < DRoom3.length; j++) {
                        for (String key : maps.keySet()) {
                            if (key.equals(DRoom3[j])) {
                                FloorManagerBean.FloorDataBean RoomBean = new FloorManagerBean.FloorDataBean();
                                FloorManagerBean.FloorDataBean listData = maps.get(key);
                                RoomBean.setDormitoryNum(listData.getDormitoryNum());
                                RoomBean.setBedQty(listData.getBedQty());
                                RoomBean.setFreeBedQty(listData.getFreeBedQty());
                                RoomBean.setDeptName(listData.getDeptName());
                                RoomBean.setGroupName(listData.getGroupName());
                                RoomBean.setParRecid(listData.getParRecid());
                                RoomBean.setDetailBeans(listData.getDetailBeans());

                                if (RoomBean.getBedQty() > RoomBean.getFreeBedQty()) {
                                    free++;
                                }
                                if (RoomBean.getBedQty() == RoomBean.getFreeBedQty()) {
                                    empty++;
                                }
                                if (RoomBean.getFreeBedQty() == 0) {
                                    full++;
                                }
                                roomList2.add(RoomBean);
                            }
                        }
                    }
                    FloorBean.setBuild(titles);
                    FloorBean.setFloorNum(floor.get(i) + "层");
                    FloorBean.setFloorDataBeanList(roomList2);
                    FloorBean.setTotalRoom(DRoom3.length + "");
                    FloorBean.setFreeRoom(free + "");
                    FloorBean.setEmptyRoom(empty + "");
                    FloorBean.setFullRoom(full + "");
                    floorList.add(FloorBean);
                } else if (i == 2) {
                    free = 0;
                    empty = 0;
                    full = 0;
                    for (int j = 0; j < DRoom4.length; j++) {
                        for (String key : maps.keySet()) {
                            if (key.equals(DRoom4[j])) {
                                FloorManagerBean.FloorDataBean RoomBean = new FloorManagerBean.FloorDataBean();
                                FloorManagerBean.FloorDataBean listData = maps.get(key);
                                RoomBean.setDormitoryNum(listData.getDormitoryNum());
                                RoomBean.setBedQty(listData.getBedQty());
                                RoomBean.setFreeBedQty(listData.getFreeBedQty());
                                RoomBean.setDeptName(listData.getDeptName());
                                RoomBean.setGroupName(listData.getGroupName());
                                RoomBean.setParRecid(listData.getParRecid());
                                RoomBean.setDetailBeans(listData.getDetailBeans());

                                if (RoomBean.getBedQty() > RoomBean.getFreeBedQty()) {
                                    free++;
                                }
                                if (RoomBean.getBedQty() == RoomBean.getFreeBedQty()) {
                                    empty++;
                                }
                                if (RoomBean.getFreeBedQty() == 0) {
                                    full++;
                                }
                                roomList3.add(RoomBean);
                            }
                        }
                    }
                    FloorBean.setBuild(titles);
                    FloorBean.setFloorNum(floor.get(i) + "层");
                    FloorBean.setFloorDataBeanList(roomList3);
                    FloorBean.setTotalRoom(DRoom4.length + "");
                    FloorBean.setFreeRoom(free + "");
                    FloorBean.setEmptyRoom(empty + "");
                    FloorBean.setFullRoom(full + "");
                    floorList.add(FloorBean);
                } else if (i == 3) {
                    free = 0;
                    empty = 0;
                    full = 0;
                    for (int j = 0; j < DRoom5.length; j++) {
                        for (String key : maps.keySet()) {
                            if (key.equals(DRoom5[j])) {
                                FloorManagerBean.FloorDataBean RoomBean = new FloorManagerBean.FloorDataBean();
                                FloorManagerBean.FloorDataBean listData = maps.get(key);
                                RoomBean.setDormitoryNum(listData.getDormitoryNum());
                                RoomBean.setBedQty(listData.getBedQty());
                                RoomBean.setFreeBedQty(listData.getFreeBedQty());
                                RoomBean.setDeptName(listData.getDeptName());
                                RoomBean.setGroupName(listData.getGroupName());
                                RoomBean.setParRecid(listData.getParRecid());
                                RoomBean.setDetailBeans(listData.getDetailBeans());

                                if (RoomBean.getBedQty() > RoomBean.getFreeBedQty()) {
                                    free++;
                                }
                                if (RoomBean.getBedQty() == RoomBean.getFreeBedQty()) {
                                    empty++;
                                }
                                if (RoomBean.getFreeBedQty() == 0) {
                                    full++;
                                }
                                roomList4.add(RoomBean);
                            }
                        }
                    }
                    FloorBean.setBuild(titles);
                    FloorBean.setFloorNum(floor.get(i) + "层");
                    FloorBean.setFloorDataBeanList(roomList4);
                    FloorBean.setTotalRoom(DRoom5.length + "");
                    FloorBean.setFreeRoom(free + "");
                    FloorBean.setEmptyRoom(empty + "");
                    FloorBean.setFullRoom(full + "");
                    floorList.add(FloorBean);
                }
            }
        } else {
            free = 0;
            empty = 0;
            full = 0;
            for (int i = 0; i < floor.size(); i++) {
                FloorManagerBean FloorBean = new FloorManagerBean();
                if (i == 0) {
                    for (int j = 0; j < DRoomA1.length; j++) {
                        for (String key : maps.keySet()) {
                            if (key.equals(DRoomA1[j])) {
                                FloorManagerBean.FloorDataBean RoomBean = new FloorManagerBean.FloorDataBean();
                                FloorManagerBean.FloorDataBean listData = maps.get(key);
                                RoomBean.setDormitoryNum(listData.getDormitoryNum());
                                RoomBean.setBedQty(listData.getBedQty());
                                RoomBean.setFreeBedQty(listData.getFreeBedQty());
                                RoomBean.setDeptName(listData.getDeptName());
                                RoomBean.setGroupName(listData.getGroupName());
                                RoomBean.setParRecid(listData.getParRecid());
                                RoomBean.setDetailBeans(listData.getDetailBeans());

                                if (RoomBean.getBedQty() > RoomBean.getFreeBedQty()) {
                                    free++;
                                }
                                if (RoomBean.getBedQty() == RoomBean.getFreeBedQty()) {
                                    empty++;
                                }
                                if (RoomBean.getFreeBedQty() == 0) {
                                    full++;
                                }
                                roomList.add(RoomBean);
                            }
                        }
                    }
                    FloorBean.setBuild(titles);
                    FloorBean.setFloorNum(floor.get(i) + "层");
                    FloorBean.setFloorDataBeanList(roomList);
                    FloorBean.setTotalRoom(DRoomA1.length + "");
                    FloorBean.setFreeRoom(free + "");
                    FloorBean.setEmptyRoom(empty + "");
                    FloorBean.setFullRoom(full + "");
                    floorList.add(FloorBean);
                } else if (i == 1) {
                    free = 0;
                    empty = 0;
                    full = 0;
                    for (int j = 0; j < DRoomA2.length; j++) {
                        for (String key : maps.keySet()) {
                            if (key.equals(DRoomA2[j])) {
                                FloorManagerBean.FloorDataBean RoomBean = new FloorManagerBean.FloorDataBean();
                                FloorManagerBean.FloorDataBean listData = maps.get(key);
                                RoomBean.setDormitoryNum(listData.getDormitoryNum());
                                RoomBean.setBedQty(listData.getBedQty());
                                RoomBean.setFreeBedQty(listData.getFreeBedQty());
                                RoomBean.setDeptName(listData.getDeptName());
                                RoomBean.setGroupName(listData.getGroupName());
                                RoomBean.setParRecid(listData.getParRecid());
                                RoomBean.setDetailBeans(listData.getDetailBeans());

                                if (RoomBean.getBedQty() > RoomBean.getFreeBedQty()) {
                                    free++;
                                }
                                if (RoomBean.getBedQty() == RoomBean.getFreeBedQty()) {
                                    empty++;
                                }
                                if (RoomBean.getFreeBedQty() == 0) {
                                    full++;
                                }
                                roomList2.add(RoomBean);
                            }
                        }
                    }
                    FloorBean.setBuild(titles);
                    FloorBean.setFloorNum(floor.get(i) + "层");
                    FloorBean.setFloorDataBeanList(roomList2);
                    FloorBean.setTotalRoom(DRoomA2.length + "");
                    FloorBean.setFreeRoom(free + "");
                    FloorBean.setEmptyRoom(empty + "");
                    FloorBean.setFullRoom(full + "");
                    floorList.add(FloorBean);
                }
            }
        }

        LatteLogger.d("floorList", GsonBuildUtil.GsonBuilder(floorList));
        return floorList;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    getNetData();
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }
}




