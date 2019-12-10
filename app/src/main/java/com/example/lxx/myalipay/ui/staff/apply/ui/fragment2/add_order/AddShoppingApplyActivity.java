package com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.add_order;

import android.app.Dialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.adapter.BaseAddImageAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.bean.AddShoppingBean;
import com.example.lxx.myalipay.utils.DateUtils;
import com.example.lxx.myalipay.widget.BaseListView;
import com.example.lxx.myalipay.widget.edit.CursorEditText;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lxx
 * @date 2019/12/1
 * 采购申请单
 */
public class AddShoppingApplyActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.et_apply_event)
    CursorEditText etApplyEvent;
    @BindView(R.id.tv_buy_type)
    TextView tvBuyType;
    @BindView(R.id.tv_expected_delivery_date)
    TextView tvExpectedDeliveryDate;
    @BindView(R.id.listView)
    BaseListView listView;
    @BindView(R.id.ll_add_detail)
    LinearLayout llAddDetail;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.apply_normal)
    Button applyNormal;
    @BindView(R.id.apply_succeed)
    Button applySucceed;
    @BindView(R.id.apply_relative)
    RelativeLayout applyRelative;
    @BindView(R.id.scroll)
    ScrollView scroll;

    private String[] buyType = {"办公用品", "生产原料", "其他"};
    private String[] payWay = {"现金", "银行汇款", "微信支付", "支付宝"};
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private TimePickerView pvTime1;
    private BaseShoppingAdapter adapter;
    private BaseAddImageAdapter adapter2;

    List<AddShoppingBean.Data> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_shopping_apply;
    }

    @Override
    public void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("采购申请");
        selectedTime();
        adapter = new BaseShoppingAdapter(getDetail());
        adapter.setData(mList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    private List<AddShoppingBean.Data> getDetail() {
        AddShoppingBean.Data item = new AddShoppingBean.Data();
        mList.add(item);
        return mList;
    }


    private void selectedTime() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        pvTime1 = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvExpectedDeliveryDate.setText(DateUtils.getTime(sf,date));
            }
        }).setDate(selectedDate)
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        tvExpectedDeliveryDate.setText(DateUtils.getTime(sf,date));
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true)
                .build();
        Dialog mDialog = pvTime1.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.picker_view_slide_anim);
                dialogWindow.setGravity(Gravity.CENTER);
            }
        }
    }

    @OnClick({R.id.tv_buy_type, R.id.tv_expected_delivery_date, R.id.tv_pay_way, R.id.apply_normal, R.id.apply_succeed, R.id.ll_add_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_buy_type:
                final NormalListDialog dialog = new NormalListDialog(_mActivity, buyType);
                dialog.title("请选择")
                        .isTitleShow(false)
                        .layoutAnimation(null)
                        .itemPressColor(Color.GRAY)
                        .itemTextColor(Color.parseColor("#303030"))
                        .show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tvBuyType.setText(buyType[position]);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.ll_add_detail:
                adapter.addData(new AddShoppingBean.Data());
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_expected_delivery_date:
                pvTime1.show();
                break;
            case R.id.tv_pay_way:
                final NormalListDialog dialog1 = new NormalListDialog(_mActivity, payWay);
                dialog1.isTitleShow(false)
                        .itemTextColor(Color.GRAY)
                        .layoutAnimation(null)
                        .itemTextColor(Color.parseColor("#303030"))
                        .show();
                dialog1.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tvPayWay.setText(payWay[position]);
                        dialog1.dismiss();
                    }
                });
                break;
            case R.id.apply_normal:
                break;
            case R.id.apply_succeed:
                hideSoftInput(_mActivity,view);
                break;
        }
    }

    class BaseShoppingAdapter extends BaseAdapter {

        List<AddShoppingBean.Data> mList;

        public BaseShoppingAdapter(List<AddShoppingBean.Data> mList) {
            this.mList = mList;
        }

        //设置数据
        public void setData(List<AddShoppingBean.Data> data) {
            this.mList = data;
        }

        //加入数据
        public void addData(AddShoppingBean.Data bean) {
            mList.add(0, bean);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            ViewHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(_mActivity).inflate(R.layout.item_add_caigou, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.name.setText(holder.name.getText().toString());
            holder.size.setText(holder.size.getText().toString());
            holder.num.setText(holder.num.getText().toString());
            holder.unit.setText(holder.unit.getText().toString());
            holder.price.setText(holder.price.getText().toString());
            holder.tvDetailTitle.setText(String.format(getString(R.string.txt_change_purchase), position + 1));

            if (mList.size() > 1) {//删除按钮
                holder.tvDelete.setVisibility(View.VISIBLE);
            } else {
                holder.tvDelete.setVisibility(View.GONE);
            }
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
            return view;
        }

        class ViewHolder {
            @BindView(R.id.name)
            CursorEditText name;
            @BindView(R.id.size)
            CursorEditText size;
            @BindView(R.id.num)
            CursorEditText num;
            @BindView(R.id.unit)
            CursorEditText unit;
            @BindView(R.id.price)
            CursorEditText price;
            @BindView(R.id.tv_detail_title)
            TextView tvDetailTitle;
            @BindView(R.id.tv_delete)
            TextView tvDelete;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}





