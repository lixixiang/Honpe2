package com.example.lxx.myalipay.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.adapter.MenuOrderDetailAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.bean.ApplyChildBean4;
import com.example.lxx.myalipay.utils.GsonBuildUtil;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import com.flyco.dialog.widget.base.BaseDialog;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by lxx at 2019/11/29 10:30
 * 描述:客户点餐对话框
 */
public class CustomMenuDialog extends BaseDialog<CustomMenuDialog> {

    List<ApplyChildBean4.DataBean.DetailsBean> dataBean;
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
    @BindView(R.id.font_1)
    TextView font1;
    @BindView(R.id.font_2)
    TextView font2;
    @BindView(R.id.font_3)
    TextView font3;
    private String session, orderNo;
    public onDeleteListener listener;
    public onMobClassListener mobListener;
    List<String> mainMeal = new ArrayList<>();
    List<String> mainMenu = new ArrayList<>();
    List<String> mainSuge = new ArrayList<>();
    public void setOnDeleteListener(onDeleteListener listener) {
        this.listener = listener;
    }

    public void setOnMobListener(onMobClassListener listener) {
        this.mobListener = listener;
    }
    private ApplyChildBean4.DataBean bean1;

    public CustomMenuDialog(Context context,List<ApplyChildBean4.DataBean.DetailsBean> dataBean, String session, String orderNo) {
        super(context);
        this.dataBean = dataBean;
        this.session = session;
        this.orderNo = orderNo;
        this.bean1 = bean1;
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
        final MenuOrderDetailAdapter adapter = new MenuOrderDetailAdapter(getMenuDate(dataBean));
        rvMenuList.setAdapter(adapter);
        rvMenuList.setLayoutManager(new LinearLayoutManager(mContext));
        final MenuOrderDetailAdapter adapter2 = new MenuOrderDetailAdapter(getmainMenu(dataBean));
        rvMenuList2.setAdapter(adapter2);
        rvMenuList2.setLayoutManager(new LinearLayoutManager(mContext));
        final MenuOrderDetailAdapter adapter3 = new MenuOrderDetailAdapter(getmainSuge(dataBean));
        rvMenuList3.setAdapter(adapter3);
        rvMenuList3.setLayoutManager(new LinearLayoutManager(mContext));
        if (mainMeal != null) {
            font1.setText("主食"+"("+mainMeal.size()+"份)");
        }
        if (mainMenu != null) {
            font2.setText("主菜"+"("+mainMenu.size()+"份)");
        }
        if (mainSuge != null) {
            font3.setText("汤类"+"("+mainSuge.size()+"份)");
        }
    }

    private List<String> getMenuDate(List<ApplyChildBean4.DataBean.DetailsBean> dataBean) {
        Map<String, List<ApplyChildBean4.DataBean.DetailsBean>> maps = new LinkedHashMap<>();
        for (int i = 0; i < dataBean.size(); i++) {
            String FoodStyle = dataBean.get(i).getFoodStyle();
            if (FoodStyle.equals("主食")) {
                FoodStyle = "主食";
            } else if (FoodStyle.equals("汤类")) {
                FoodStyle = "汤类";
            } else {
                FoodStyle = "主菜";
            }
            List<ApplyChildBean4.DataBean.DetailsBean> listBean = maps.get(FoodStyle);
            if (listBean == null) {
                listBean = new ArrayList<>();
            }
            ApplyChildBean4.DataBean.DetailsBean bean = dataBean.get(i);
            listBean.add(bean);
            maps.put(FoodStyle, listBean);
        }
        String result = GsonBuildUtil.GsonBuilder(maps);
        LatteLogger.d("dddddddd", result);

        for (String key : maps.keySet()) {
            if (key.equals("主食")) {
                TestMenuShowBean bean = new TestMenuShowBean();
                bean.setMenuType(key);
                List<ApplyChildBean4.DataBean.DetailsBean> listDatas = maps.get(key);
                for (int i = 0; i < listDatas.size(); i++) {
                    TestMenuShowBean.Details bean1 = new TestMenuShowBean.Details();
                    bean1.setFoodName(listDatas.get(i).getFoodName());
                    String meal = bean1.getFoodName();
                    mainMeal.add(meal);
                }
            }
        }
        return mainMeal;
    }

    private List<String> getmainSuge(List<ApplyChildBean4.DataBean.DetailsBean> dataBean) {
        Map<String, List<ApplyChildBean4.DataBean.DetailsBean>> maps = new LinkedHashMap<>();
        for (int i = 0; i < dataBean.size(); i++) {
            String FoodStyle = dataBean.get(i).getFoodStyle();
            if (FoodStyle.equals("主食")) {
                FoodStyle = "主食";
            } else if (FoodStyle.equals("汤类")) {
                FoodStyle = "汤类";
            } else {
                FoodStyle = "主菜";
            }
            List<ApplyChildBean4.DataBean.DetailsBean> listBean = maps.get(FoodStyle);
            if (listBean == null) {
                listBean = new ArrayList<>();
            }
            ApplyChildBean4.DataBean.DetailsBean bean = dataBean.get(i);
            listBean.add(bean);
            maps.put(FoodStyle, listBean);
        }
        String result = GsonBuildUtil.GsonBuilder(maps);
        LatteLogger.d("dddddddd", result);

        for (String key : maps.keySet()) {
            if (key.equals("汤类")) {
                TestMenuShowBean bean = new TestMenuShowBean();
                bean.setMenuType(key);
                List<ApplyChildBean4.DataBean.DetailsBean> listDatas = maps.get(key);
                for (int i = 0; i < listDatas.size(); i++) {
                    TestMenuShowBean.Details bean1 = new TestMenuShowBean.Details();
                    bean1.setFoodName(listDatas.get(i).getFoodName());
                    String meal = bean1.getFoodName();
                    mainSuge.add(meal);
                }
            }
        }
        return mainSuge;
    }

    private List<String> getmainMenu(List<ApplyChildBean4.DataBean.DetailsBean> dataBean) {
        Map<String, List<ApplyChildBean4.DataBean.DetailsBean>> maps = new LinkedHashMap<>();
        for (int i = 0; i < dataBean.size(); i++) {
            String FoodStyle = dataBean.get(i).getFoodStyle();
            if (FoodStyle.equals("主食")) {
                FoodStyle = "主食";
            } else if (FoodStyle.equals("汤类")) {
                FoodStyle = "汤类";
            } else {
                FoodStyle = "主菜";
            }
            List<ApplyChildBean4.DataBean.DetailsBean> listBean = maps.get(FoodStyle);
            if (listBean == null) {
                listBean = new ArrayList<>();
            }
            ApplyChildBean4.DataBean.DetailsBean bean = dataBean.get(i);
            listBean.add(bean);
            maps.put(FoodStyle, listBean);
        }
        String result = GsonBuildUtil.GsonBuilder(maps);
        LatteLogger.d("dddddddd", result);

        for (String key : maps.keySet()) {
            if (key.equals("主菜")) {
                TestMenuShowBean bean = new TestMenuShowBean();
                bean.setMenuType(key);
                List<ApplyChildBean4.DataBean.DetailsBean> listDatas = maps.get(key);
                for (int i = 0; i < listDatas.size(); i++) {
                    TestMenuShowBean.Details bean1 = new TestMenuShowBean.Details();
                    bean1.setFoodName(listDatas.get(i).getFoodName());
                    String meal = bean1.getFoodName();
                    mainMenu.add(meal);
                }
            }
        }
        return mainMenu;
    }

    @Override
    public void setUiBeforShow() {

    }

    @OnClick({R.id.tv_mob, R.id.tv_cancel, R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mob:
                mobListener.onClickMobClass();
                dismiss();
                break;
            case R.id.tv_cancel:
                final NormalDialog dialog1 = new NormalDialog(mContext);
                dialog1.isTitleShow(false)
                        .content("是否要删除" + "\"" + orderNo + "\"" + "订单吗?")
                        .widthScale(0.85f)
                        .cornerRadius(5)//
                        .contentTextSize(14)
                        .btnTextSize(14)
                        .contentGravity(Gravity.CENTER)
                        .btnTextColor(Color.RED, Color.BLUE)
                        .btnPressColor(Color.LTGRAY)
                        .show();
                dialog1.setOnBtnClickL(new OnBtnClickL() {
                    @Override
                    public void onBtnClick() { //left
                        dialog1.dismiss();
                    }
                }, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {//right
                        delete();
                        listener.onClickDelete();
                        dialog1.dismiss();
                    }
                });
                dismiss();
                break;
            case R.id.tv_close:
                dismiss();
                break;
        }
    }

    private void delete() {
        EasyHttp.post(Constants.REQUEST_MENU_DELETE + session)
                .params("OrderNo", orderNo)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("Status") == 0) {
                                ToastUtils.getInstance().showToast(jsonObject.getString("Msg"));
                                EventBusUtil.sendEvent(new Event(FinalClass.A));//给orderFoodFragment 发消息刷新
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
    }

    public interface onDeleteListener {
        void onClickDelete();
    }

    public interface onMobClassListener {
        void onClickMobClass();
    }
}

