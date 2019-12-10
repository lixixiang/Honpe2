package com.example.lxx.myalipay.ui.staff.query.ui.position7.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.MainActivity;
import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.add.AddStayFragment;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.FloorManagerBean;
import com.example.lxx.myalipay.ui.staff.query.ui.position7.bean.GridObjectBean;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.example.lxx.myalipay.widget.font.FontTextView4;
import com.example.lxx.myalipay.widget.font.FontTextView5;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.List;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * created by lxx at 2019/12/3 16:00
 * 描述:
 */
public class BedGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<FloorManagerBean.FloorDataBean.FloorDetailBean> detailBeans;
    private LayoutInflater inflater;
    private FloorManagerBean.FloorDataBean floorDataBean;
    private String build, floor;
    public static final int ADD_STAY = 0X3;
    public GridObjectBean objectBean = new GridObjectBean();

    public BedGridViewAdapter(Context context, List<FloorManagerBean.FloorDataBean.FloorDetailBean> detailBeans
            , FloorManagerBean.FloorDataBean floorDataBean, String build, String floor) {
        this.context = context;
        this.detailBeans = detailBeans;
        this.floorDataBean = floorDataBean;
        this.build = build;
        this.floor = floor;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return detailBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return detailBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final BedViewHolder bedViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.css_build3, parent, false);
            bedViewHolder = new BedViewHolder(convertView);
            convertView.setTag(bedViewHolder);
        } else {
            bedViewHolder = (BedViewHolder) convertView.getTag();
        }

        final FloorManagerBean.FloorDataBean.FloorDetailBean bean = detailBeans.get(position);
        bedViewHolder.tvNameSex.setText(bean.getResidentsName() + "  " + bean.getSex());
        bedViewHolder.tvBeNum.setText(bean.getBeNum());
        bedViewHolder.tvDepart.setText(bean.getDepartName());
        bedViewHolder.tvTeam.setText(bean.getTeam());

        if (bean.getResidentsName().equals("空床")) {
            bedViewHolder.llDepartTeam.setVisibility(View.VISIBLE);
            bedViewHolder.Font5.setText("\ue609");
            bedViewHolder.FontAdd.setVisibility(View.VISIBLE);
            bedViewHolder.FontMob.setVisibility(View.GONE);
            bedViewHolder.FontAdd.setText("\ue611");
            bedViewHolder.tvNameSex.setTextColor(context.getResources().getColor(R.color.list_bottom));
            bedViewHolder.FontAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //添加
                    objectBean.setUserName("");
                    objectBean.setSex("");
                    objectBean.setRowid("");
                    objectBean.setPosition(position);
                    objectBean.setBuild(build);
                    objectBean.setFloor(floor);
                    objectBean.setRoom(floorDataBean.getDormitoryNum());
                    objectBean.setBedNum(position + 1 + "");
                    objectBean.setTotalBedNum(floorDataBean.getBedQty());
                    objectBean.setDepart(bean.getDepartName());
                    objectBean.setTeam(bean.getTeam());
                    objectBean.setParRecid(floorDataBean.getParRecid());
                    objectBean.setDetailBeans(floorDataBean.getDetailBeans());
                    Intent intent = new Intent(context, AddStayFragment.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("obj", objectBean);
                    intent.putExtras(bundle);
                    ((MainActivity) context).startActivityForResult(intent, ADD_STAY);
                    notifyDataSetChanged();
                }
            });
        } else {
            bedViewHolder.llDepartTeam.setVisibility(View.VISIBLE);
            bedViewHolder.FontAdd.setVisibility(View.VISIBLE);
            bedViewHolder.FontMob.setVisibility(View.VISIBLE);
            bedViewHolder.FontAdd.setText("\ue625");
            bedViewHolder.Font5.setTextColor(context.getResources().getColor(R.color.gray));
            bedViewHolder.Font5.setText("\ue6e5");
            bedViewHolder.tvNameSex.setTextColor(context.getResources().getColor(R.color.green));
            bedViewHolder.tvBeNum.setTextColor(context.getResources().getColor(R.color.list_bottom));
            final String session = (String) MyApplication.get(context, Session, "");
            bedViewHolder.FontAdd.setOnClickListener(new View.OnClickListener() { //删除
                @Override
                public void onClick(View v) {
                    final NormalDialog dialog1 = new NormalDialog(context);
                    dialog1.isTitleShow(false)
                            .content("是否要删除" + "\"" + bean.getResidentsName() + "\"" + "的床位?")
                            .cornerRadius(5)//
                            .contentTextSize(13)
                            .btnTextSize(12f,12f)
                            .widthScale(0.7f)
                            .contentGravity(Gravity.CENTER)
                            .btnTextColor(Color.RED, Color.BLUE)
                            .btnPressColor(Color.LTGRAY)
                            .show();
                    dialog1.setOnBtnClickL(new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {//left
                            dialog1.dismiss();
                        }
                    }, new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {//right
                            EasyHttp.post(Constants.DELETE_BED + session)
                                    .params("rowid", bean.getRowid())
                                    .execute(new SimpleCallBack<String>() {
                                        @Override
                                        public void onError(ApiException e) {
                                            ToastUtils.getInstance().showToast(e.getMessage());
                                        }

                                        @Override
                                        public void onSuccess(String s) {
                                            ToastUtils.getInstance().showToast("删除成功！");
                                            FloorManagerBean.FloorDataBean.FloorDetailBean bean1
                                                    = new FloorManagerBean.FloorDataBean.FloorDetailBean();
                                            bean1.setBeNum((position + 1) + "");
                                            bean1.setResidentsName("空床");
                                            bean1.setSex("");
                                            detailBeans.set(position, bean1);
                                            notifyDataSetChanged();
                                        }
                                    });
                            dialog1.dismiss();
                        }
                    });
                }
            });
            bedViewHolder.FontMob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //修改
                    objectBean.setUserName(bean.getResidentsName());
                    objectBean.setSex(bean.getSex());
                    objectBean.setPosition(position);
                    objectBean.setBuild(build);
                    objectBean.setFloor(floor);
                    objectBean.setRoom(floorDataBean.getDormitoryNum());
                    objectBean.setTotalBedNum(floorDataBean.getBedQty());
                    objectBean.setBedNum(position + 1 + "");
                    objectBean.setDepart(bean.getDepartName());
                    objectBean.setTeam(bean.getTeam());
                    objectBean.setParRecid(floorDataBean.getParRecid());
                    objectBean.setRowid(bean.getRowid());
                    objectBean.setDetailBeans(floorDataBean.getDetailBeans());
                    Intent intent = new Intent(context, AddStayFragment.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("obj", objectBean);
                    intent.putExtras(bundle);
                    ((MainActivity) context).startActivityForResult(intent, ADD_STAY);
                }
            });
        }
        return convertView;
    }

    class BedViewHolder {
        TextView tvNameSex, tvBeNum, tvDepart, tvTeam;
        FontTextView5 Font5;
        FontTextView4 FontAdd, FontMob;
        LinearLayout llDepartTeam,llAdd,llMob;

        private BedViewHolder(View view) {
            tvNameSex = view.findViewById(R.id.tv_name_sex);
            tvBeNum = view.findViewById(R.id.tv_BeNum);
            Font5 = view.findViewById(R.id.Font5);
            FontAdd = view.findViewById(R.id.font_add);
            FontMob = view.findViewById(R.id.font_mob);
            tvDepart = view.findViewById(R.id.tv_depart);
            tvTeam = view.findViewById(R.id.tv_team);
            llDepartTeam = view.findViewById(R.id.ll_depart_team);
        }
    }
}
