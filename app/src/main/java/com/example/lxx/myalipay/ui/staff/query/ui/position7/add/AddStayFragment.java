package com.example.lxx.myalipay.ui.staff.query.ui.position7.add;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.ItemDepartBean;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.bean.ItemTeamBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.FloorManagerBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.GridObjectBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.StayBean;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * created by lxx at 2019/12/3 16:06
 * 描述:
 */
public class AddStayFragment extends BaseActivity implements TextWatcher {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_delete_name)
    ImageView ivDeleteName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.iv_delete_sex)
    ImageView ivDeleteSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_depart)
    TextView tvDepart;
    @BindView(R.id.iv_delete_depart)
    ImageView ivDeleteDepart;
    @BindView(R.id.ll_depart)
    LinearLayout llDepart;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.iv_delete_team)
    ImageView ivDeleteTeam;
    @BindView(R.id.ll_team)
    LinearLayout llTeam;
    @BindView(R.id.tv_Build_num)
    TextView tvBuildNum;
    @BindView(R.id.iv_delete_build)
    ImageView ivDeleteBuild;
    @BindView(R.id.tv_floor_num)
    TextView tvFloorNum;
    @BindView(R.id.iv_delete_floor)
    ImageView ivDeleteFloor;
    @BindView(R.id.tv_room_num)
    TextView tvRoomNum;
    @BindView(R.id.iv_delete_room)
    ImageView ivDeleteRoom;
    @BindView(R.id.tv_bunk)
    TextView tvBunk;
    @BindView(R.id.iv_delete_bunk)
    ImageView ivDeleteBunk;
    @BindView(R.id.apply_normal)
    Button applyNormal;
    @BindView(R.id.apply_succeed)
    Button applySucceed;
    @BindView(R.id.apply_relative)
    RelativeLayout applyRelative;

    private String session;
    List<String> Lists = new ArrayList<>();

    private String[] sexs = {"男", "女"};
    private String[] teams;
    private String[] BuildNum = {"D栋", "小别墅"};
    private String[] FloorNum = {"2层", "3层", "4层", "5层"};
    private String[] FloorNum2 = {"2层", "3层"};
    private String strName, strSex, strDepart, strTeam, strBuild, strFloor, strRoom, strBunk, ParRecid, rowid,strBunks;
    private Bundle bundle = new Bundle();
    private Message message;
    private int totalBed,freeBed;
    private int position;
    private GridObjectBean gridObjectBean;
    private StayBean.DataBean roomBeans = new StayBean.DataBean();

    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            strName = bundle.getString("name");
            strSex = bundle.getString("sex");
            strDepart = bundle.getString("depart");
            strTeam = bundle.getString("team");
            strBuild = bundle.getString("build");
            strFloor = bundle.getString("floor");
            strBunk = bundle.getString("bunk");

            if (strName != "" && strSex != "" && strDepart != "" && strTeam != "" && strBuild != "" && strFloor != "" && strBunk != ""
                    && !TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strSex) && !TextUtils.isEmpty(strDepart) && !TextUtils.isEmpty(strTeam)
                    && !TextUtils.isEmpty(strBuild) && !TextUtils.isEmpty(strFloor) && !TextUtils.isEmpty(strBunk)) {
                applySucceed.setVisibility(View.VISIBLE);
                applyNormal.setVisibility(View.GONE);
            } else {
                applySucceed.setVisibility(View.GONE);
                applyNormal.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_floor_room;
    }

    @Override
    public void initView() {
        initToolbarNav(llBack);
        session = (String) MyApplication.get(_mActivity, Session, "");
        etName.addTextChangedListener(this);
        tvSex.addTextChangedListener(this);
        tvDepart.addTextChangedListener(this);
        tvTeam.addTextChangedListener(this);
        tvBuildNum.addTextChangedListener(this);
        tvFloorNum.addTextChangedListener(this);
        tvRoomNum.addTextChangedListener(this);
        tvBunk.addTextChangedListener(this);

        gridObjectBean = (GridObjectBean) getIntent().getSerializableExtra("obj");
        for (int i = 0; i < gridObjectBean.getDetailBeans().size(); i++) {
            if ("".equals(gridObjectBean.getDetailBeans().get(i).getSex())) {
                freeBed++;
            }
        }
        if (gridObjectBean.getRowid() != null&&!"".equals(gridObjectBean.getRowid())) { //修改
            tvTitle.setText("换宿舍床位");
            etName.setText(gridObjectBean.getUserName());
            tvSex.setText(gridObjectBean.getSex());
            tvDepart.setText(gridObjectBean.getDepart());
            tvTeam.setText(gridObjectBean.getTeam());
            tvBuildNum.setText(gridObjectBean.getBuild());
            tvFloorNum.setText(gridObjectBean.getFloor());
            tvRoomNum.setText(gridObjectBean.getRoom());
            position = gridObjectBean.getPosition();
            ParRecid = gridObjectBean.getParRecid();
            rowid = gridObjectBean.getRowid();
            totalBed = gridObjectBean.getTotalBedNum();

            LatteLogger.d(tvDepart.getText().toString()+" "+tvTeam.getText().toString());

            etName.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
            tvSex.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
            tvDepart.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
            tvTeam.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));

            tvBuildNum.setTextColor(_mActivity.getResources().getColor(R.color.black));
            tvFloorNum.setTextColor(_mActivity.getResources().getColor(R.color.black));
            tvRoomNum.setTextColor(_mActivity.getResources().getColor(R.color.black));
            tvBunk.setTextColor(_mActivity.getResources().getColor(R.color.black));


            etName.setEnabled(false);
            tvSex.setEnabled(false);
            tvDepart.setEnabled(false);
            tvTeam.setEnabled(false);

            tvBuildNum.setEnabled(true);
            tvFloorNum.setEnabled(true);
            tvRoomNum.setEnabled(true);
            tvBunk.setEnabled(true);

            ivDeleteName.setVisibility(View.GONE);
            ivDeleteSex.setVisibility(View.GONE);
            ivDeleteDepart.setVisibility(View.GONE);
            ivDeleteTeam.setVisibility(View.GONE);
        } else { //新增
            tvTitle.setText("添加员工住宿");
            etName.setText(gridObjectBean.getUserName());
            tvSex.setText(gridObjectBean.getSex());
            tvDepart.setText(gridObjectBean.getDepart());
            tvTeam.setText(gridObjectBean.getTeam());
            tvBuildNum.setText(gridObjectBean.getBuild());
            tvFloorNum.setText(gridObjectBean.getFloor());
            tvRoomNum.setText(gridObjectBean.getRoom());
            position = gridObjectBean.getPosition();
            ParRecid = gridObjectBean.getParRecid();
            totalBed = gridObjectBean.getTotalBedNum();
            etName.setEnabled(true);
            tvSex.setEnabled(true);
            tvDepart.setEnabled(true);
            tvTeam.setEnabled(true);

            tvBuildNum.setEnabled(false);
            tvFloorNum.setEnabled(false);
            tvRoomNum.setEnabled(false);
            tvBunk.setEnabled(false);

            etName.setTextColor(_mActivity.getResources().getColor(R.color.black));
            tvSex.setTextColor(_mActivity.getResources().getColor(R.color.black));
            tvDepart.setTextColor(_mActivity.getResources().getColor(R.color.black));
            tvTeam.setTextColor(_mActivity.getResources().getColor(R.color.black));

            tvBuildNum.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
            tvFloorNum.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
            tvRoomNum.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
            tvBunk.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
            ivDeleteBuild.setVisibility(View.GONE);
            ivDeleteFloor.setVisibility(View.GONE);
            ivDeleteRoom.setVisibility(View.GONE);
            ivDeleteBunk.setVisibility(View.GONE);
        }

        int bed = Integer.parseInt(gridObjectBean.getBedNum());
        if (bed % 2 == 0) {
            tvBunk.setText("床位" + bed + "(下)");
        } else {
            tvBunk.setText("床位" + bed + "(上)");
        }
    }

    List<String> bedList = new LinkedList<>();

    @OnClick({R.id.tv_sex, R.id.tv_depart, R.id.tv_team, R.id.tv_Build_num, R.id.tv_floor_num, R.id.tv_room_num,
            R.id.tv_bunk, R.id.apply_normal, R.id.apply_succeed,
            R.id.iv_delete_name, R.id.iv_delete_sex, R.id.iv_delete_depart, R.id.iv_delete_team,
            R.id.iv_delete_build, R.id.iv_delete_floor, R.id.iv_delete_room, R.id.iv_delete_bunk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sex:
                final NormalListDialog dialog = new NormalListDialog(_mActivity, sexs);
                dialog.title("选择性别")
                        .layoutAnimation(null)
                        .show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tvSex.setText(sexs[position]);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.tv_depart:
                getDepartData();
                break;
            case R.id.tv_team:
                getTeamData();
                break;
            case R.id.tv_Build_num:
                final NormalListDialog dialog1 = new NormalListDialog(_mActivity, BuildNum);
                dialog1.title("选择楼号")
                        .titleTextSize_SP(12)
                        .itemTextSize(12)
                        .layoutAnimation(null)
                        .show();
                dialog1.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tvBuildNum.setText(BuildNum[position]);
                        dialog1.dismiss();
                    }
                });
                break;
            case R.id.tv_floor_num:
                if (tvBuildNum.getText().toString().equals("D栋")) {
                    final NormalListDialog dialog2 = new NormalListDialog(_mActivity, FloorNum);
                    dialog2.title("选择楼层")
                            .itemTextSize(12)
                            .titleTextSize_SP(12)
                            .layoutAnimation(null)
                            .show();
                    dialog2.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tvFloorNum.setText(FloorNum[position]);
                            dialog2.dismiss();
                        }
                    });
                } else if (tvBuildNum.getText().toString().equals("小别墅")) {
                    final NormalListDialog dialog2 = new NormalListDialog(_mActivity, FloorNum2);
                    dialog2.title("选择楼层")
                            .itemTextSize(12)
                            .titleTextSize_SP(12)
                            .layoutAnimation(null)
                            .show();
                    dialog2.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tvFloorNum.setText(FloorNum[position]);
                            dialog2.dismiss();
                        }
                    });
                } else {
                    ToastUtils.getInstance().showToast("请先选择完楼号再来点击楼层");
                }
                break;
            case R.id.tv_room_num:
                if (StringUtils.findStr(FloorNum, tvFloorNum.getText().toString())) {
                    getRoomNum(tvBuildNum.getText().toString(), tvFloorNum.getText().toString());
                } else {
                    ToastUtils.getInstance().showToast("请先选择完楼层再来点击房层");
                }
                break;
            case R.id.tv_bunk:
                String strFloorNum = tvFloorNum.getText().toString();
                bedList.clear();
                if (gridObjectBean.getDetailBeans() != null) {
                    String[][] object ={new String[]{"空床"," "}};
                    LatteLogger.d("totalBed",gridObjectBean.getTotalBedNum());
                    for (int i = 0; i < gridObjectBean.getDetailBeans().size(); i++) {
                        strBunks = gridObjectBean.getDetailBeans().get(i).getBeNum()
                                + "   "+ StringUtils.replace(gridObjectBean.getDetailBeans().get(i).getResidentsName(),object);
                        bedList.add(strBunks);
                    }
                }else{
                    for (int i = 0; i < totalBed; i++) {
                        if (i % 2 == 0) {
                            strBunks = "床位" + (i + 1) + "(上)";
                        }else {
                            strBunks = "床位" + (i + 1) + "(下)";
                        }
                        for (int j = 0; j < roomBeans.getDetails().size(); j++) {
                            if (strBunks.equals(roomBeans.getDetails().get(j).getBedNum())) {
                                strBunks = strBunks+"   "+roomBeans.getDetails().get(j).getResidentsName();
                            }
                        }
                        bedList.add(strBunks);
                    }
                }

                LatteLogger.d("getBedNum",GsonBuildUtil.GsonBuilder(bedList));
                final String[] D_BunkType = bedList.toArray(new String[bedList.size()]);
                if (!TextUtils.isEmpty(strFloorNum)) {
                    final NormalListDialog dialog3 = new NormalListDialog(_mActivity, D_BunkType);
                    dialog3.title("选择房号")
                            .itemTextSize(12)
                            .titleTextSize_SP(12)
                            .layoutAnimation(null)
                            .show();
                    dialog3.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String[] bunkTypes = D_BunkType[position].split("   ");
//                            if (bunkTypes[1] != null) {
//                                final NormalDialog dialog = new NormalDialog(mContext);
//                                dialog.content("确定要替换掉  "+bunkTypes[1]+"?")
//                                        .isTitleShow(false)
//                                        .titleTextSize(14f)
//                                        .contentTextSize(12f)
//                                        .contentGravity(Gravity.CENTER)
//                                        .btnTextSize(12f,12f)
//                                        .btnTextColor(Color.RED, Color.BLUE)
//                                        .btnPressColor(Color.parseColor("#2B2B2B"))
//                                        .widthScale(0.85f)
//                                        .show();
//                                dialog.setOnBtnClickL(new OnBtnClickL() {
//                                    @Override
//                                    public void onBtnClick() {
//                                        dialog.dismiss();
//                                    }
//                                }, new OnBtnClickL() {
//                                    @Override
//                                    public void onBtnClick() {
//                                        dialog.dismiss();
//                                    }
//                                });
//                            }
                            tvBunk.setText(bunkTypes[0]);
                            dialog3.dismiss();
                        }
                    });
                } else {
                    ToastUtils.getInstance().showToast("请先选择完房号再来点击床位");
                }
                break;
            case R.id.iv_delete_name:
                etName.setText("");
                break;
            case R.id.iv_delete_sex:
                tvSex.setText("");
                break;
            case R.id.iv_delete_depart:
                tvDepart.setText("");
                break;
            case R.id.iv_delete_team:
                tvTeam.setText("");
                break;
            case R.id.iv_delete_build:
                tvBuildNum.setText("");
                break;
            case R.id.iv_delete_floor:
                tvFloorNum.setText("");
                break;
            case R.id.iv_delete_room:
                tvRoomNum.setText("");
                break;
            case R.id.iv_delete_bunk:
                tvBunk.setText("");
                break;
            case R.id.apply_normal:

                break;
            case R.id.apply_succeed:
                if (!TextUtils.isEmpty(rowid)) {
                    SubmitNewOrderData(rowid);
                } else {
                    rowid = "";
                    SubmitNewOrderData(rowid);
                }
                break;
        }
    }

    //新增
    private void SubmitNewOrderData(final String rowid) {
        EasyHttp.post(Constants.SUBMIT_ADD_MOB_DORM + session)
                .params("rowid", rowid)
                .params("ParRecid", ParRecid)
                .params("DormitoryNum", strRoom)
                .params("BedNum", strBunk)
                .params("ResidentsName", strName)
                .params("Sex", strSex)
                .params("DeptName", strDepart)
                .params("GroupName", strTeam)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("SubmitNewOrderData", s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("Status") == 0) {
                                ToastUtils.getInstance().showToast("提交成功!");
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                FloorManagerBean floorBean = new FloorManagerBean();
                                floorBean.setFloorNum(tvFloorNum.getText().toString());
                                floorBean.setBuild(tvBuildNum.getText().toString());
                                List<FloorManagerBean.FloorDataBean> floorDataBeans = new LinkedList<>();
                                FloorManagerBean.FloorDataBean floorDataBean = new FloorManagerBean.FloorDataBean();
                                floorDataBean.setParRecid(ParRecid);
                                floorDataBean.setDormitoryNum(tvRoomNum.getText().toString());
                                floorDataBean.setDeptName(tvDepart.getText().toString());
                                floorDataBean.setGroupName(tvTeam.getText().toString());
                                floorDataBean.setBedQty(totalBed);
                                floorDataBean.setFreeBedQty(freeBed-1);
                                LatteLogger.d("gridObjectBean",GsonBuildUtil.GsonBuilder(gridObjectBean.getDetailBeans()));
                                List<FloorManagerBean.FloorDataBean.FloorDetailBean> floorDetailBeans = new LinkedList<>();
                                FloorManagerBean.FloorDataBean.FloorDetailBean detailBean = new FloorManagerBean.FloorDataBean.FloorDetailBean();
                                detailBean.setResidentsName(strName);
                                detailBean.setSex(strSex);
                                detailBean.setBeNum(strBunk);
                                detailBean.setDepartName(strDepart);
                                detailBean.setTeam(strTeam);
                                detailBean.setRowid(rowid);
                                floorDetailBeans.add(detailBean);
                                floorDataBean.setDetailBeans(floorDetailBeans);
                                floorDataBeans.add(floorDataBean);
                                floorBean.setFloorDataBeanList(floorDataBeans);

                                bundle.putSerializable("floor", floorBean);
                                intent.putExtras(bundle);
                                setResult(200, intent);
                                finish();
                            } else {
                                ToastUtils.getInstance().showToast("提交失败!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    private void getRoomNum(String StrBuildNum, String StrFloorNum) {
        String[] floorNum = StrFloorNum.split("层");
        EasyHttp.post(Constants.REQUEST_DORM + session)
                .params("BuildingNum", StrBuildNum)
                .params("FloorNum", floorNum[0])
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
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        final StayBean bean = Convert.fromJson(s, StayBean.class);
                        List<String> roomNums = new ArrayList<>();
                        for (int i = 0; i < bean.getData().size(); i++) {
                            roomNums.add(bean.getData().get(i).getDormitoryNum());
                        }
                        teams = roomNums.toArray(new String[roomNums.size()]);
                        final NormalListDialog dialog2 = new NormalListDialog(_mActivity, teams);
                        dialog2.title("选择房号")
                                .itemTextSize(12)
                                .titleTextSize_SP(12)
                                .layoutAnimation(null)
                                .show();
                        dialog2.setOnOperItemClickL(new OnOperItemClickL() {
                            @Override
                            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                tvRoomNum.setText(teams[position]);

                                for (int i = 0; i < bean.getData().size(); i++) {
                                    if (bean.getData().get(i).getDormitoryNum().equals(teams[position])) {
                                        ParRecid = bean.getData().get(i).getRecId();
                                        totalBed = bean.getData().get(i).getBedQty();
                                        roomBeans.setDetails(bean.getData().get(i).getDetails());
                                        break;
                                    }
                                }
                                LatteLogger.d("roomNum", GsonBuildUtil.GsonBuilder(roomBeans));
                                dialog2.dismiss();
                            }
                        });
                    }
                });
    }

    private void getTeamData() {
        EasyHttp.post(Constants.MANAGER_TEAM + session)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException result) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        final ItemTeamBean bean = Convert.fromJson(result, ItemTeamBean.class);
                        Lists.clear();
                        for (int i = 0; i < bean.getData().size(); i++) {
                            Lists.add(bean.getData().get(i).getGroupName());
                        }
                        teams = Lists.toArray(new String[Lists.size()]);

                        final NormalListDialog dialog = new NormalListDialog(_mActivity, teams);
                        dialog.title("选择组别")
                                .titleTextSize_SP(12)
                                .itemTextSize(12)
                                .layoutAnimation(null)
                                .show();
                        dialog.setOnOperItemClickL(new OnOperItemClickL() {
                            @Override
                            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                tvTeam.setText(teams[position]);
                                dialog.dismiss();
                            }
                        });
                    }
                });
    }

    private void getDepartData() {
        EasyHttp.post(Constants.MANAGER_DEPART + session)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("result", result);
                        ItemDepartBean bean = Convert.fromJson(result, ItemDepartBean.class);
                        Lists.clear();
                        for (int i = 0; i < bean.getData().size(); i++) {
                            Lists.add(bean.getData().get(i).getDeptName());
                        }

                        teams = Lists.toArray(new String[Lists.size()]);
                        final NormalListDialog dialog = new NormalListDialog(_mActivity, teams);
                        dialog.title("选择部门")
                                .titleTextSize_SP(12)
                                .itemTextSize(12)
                                .cornerRadius(5)
                                .heightScale(0.7f)
                                .layoutAnimation(null)
                                .show();
                        dialog.setOnOperItemClickL(new OnOperItemClickL() {
                            @Override
                            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                tvDepart.setText(teams[position]);
                                dialog.dismiss();
                            }
                        });
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        strName = etName.getText().toString();
        strSex = tvSex.getText().toString();
        strDepart = tvDepart.getText().toString();
        strTeam = tvTeam.getText().toString();
        strBuild = tvBuildNum.getText().toString();
        strFloor = tvFloorNum.getText().toString();
        strRoom = tvRoomNum.getText().toString();
        strBunk = tvBunk.getText().toString();

        bundle.putString("name", strName);
        bundle.putString("sex", strSex);
        bundle.putString("depart", strDepart);
        bundle.putString("team", strTeam);
        bundle.putString("build", strBuild);
        bundle.putString("floor", strRoom);
        bundle.putString("bunk", strBunk);

        message = mHandler.obtainMessage();
        message.setData(bundle);
        message.sendToTarget();

        if (!TextUtils.isEmpty(strName)&&gridObjectBean.getRowid() !=null) {
            ivDeleteName.setVisibility(View.GONE);
        } else  if (!TextUtils.isEmpty(strName)) {
            ivDeleteName.setVisibility(View.VISIBLE);
            ivDeleteName.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteName.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(strSex)) {
            ivDeleteSex.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteSex.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(strDepart)) {
            ivDeleteDepart.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteDepart.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(strTeam)) {
            ivDeleteTeam.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteTeam.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(strBuild)) {
            ivDeleteBuild.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteBuild.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(strFloor)) {
            ivDeleteFloor.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteFloor.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(strRoom)) {
            ivDeleteRoom.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteRoom.setImageResource(R.mipmap.ic_right_grey);
        }
        if (!TextUtils.isEmpty(strBunk)) {
            ivDeleteBunk.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            ivDeleteBunk.setImageResource(R.mipmap.ic_right_grey);
        }
    }
}
























