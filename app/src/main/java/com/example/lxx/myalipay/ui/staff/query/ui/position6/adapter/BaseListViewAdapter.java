package com.example.lxx.myalipay.ui.staff.query.ui.position6.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.bean.CardQueryBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lxx.myalipay.api.FinalClass.Session;


/**
 * created by lxx at 2019/12/3 15:02
 * 描述:打卡日期
 */
public class BaseListViewAdapter extends BaseAdapter {

    private Context context;
    private List<CardQueryBean.DataBean> mList;
    private LayoutInflater inflater;

    private int itemCount = 1;
    private String[] isAgree = {"同意", "不同意"};
    private String session;
    public BaseListViewAdapter(Context context, List<CardQueryBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (mList.size() > 1) {
            return itemCount;
        } else {
            return mList.size();
        }
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.css_title_status, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final CardQueryBean.DataBean bean = mList.get(position);
        holder.time.setText(bean.getF_BkTime() + " " + bean.getF_Reason());
        holder.tvStatus.setVisibility(View.VISIBLE);
        session = (String) MyApplication.get(context, Session, "");

        if (bean.getF_State() == -1) {
            holder.tvStatus.setBackgroundResource(R.drawable.shape_rectangle_white_green_5_lr15_tb5);
            holder.tvStatus.setText("待审批");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.white));
            holder.tvStatus.setClickable(false);
            final ViewHolder finalHolder = holder;
            holder.tvStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ActionSheetDialog dialog = new ActionSheetDialog(context, isAgree, null);
                    dialog.isTitleShow(false)
                            .layoutAnimation(null)
                            .lvBgColor(Color.WHITE)
                            .cancelText(Color.RED)
                            .show();
                    dialog.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position0, long id) {
                            switch (position0){
                                case 0: //同意
                                    bean.setF_State(1);
                                    getRequestAgree(bean.getF_ID(),1);
                                    finalHolder.tvStatus.setBackgroundResource(R.drawable.shape_grey_radius10);
                                    finalHolder.tvStatus.setText("已通过");
                                    finalHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.white));
                                    break;
                                case 1: //不同意
                                    bean.setF_State(0);
                                    getRequestAgree(bean.getF_ID(),0);
                                    finalHolder.tvStatus.setBackgroundResource(R.drawable.shape_grey_radius10);
                                    finalHolder.tvStatus.setText("未通过");
                                    finalHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.white));
                                break;
                            }
                            notifyDataSetChanged();
                            finalHolder.tvStatus.setClickable(true);
                            dialog.dismiss();
                        }
                    });
                }
            });

        }else if (bean.getF_State() == 0){
            holder.tvStatus.setBackgroundResource(R.drawable.shape_grey_radius10);
            holder.tvStatus.setText("未通过");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.white));
            holder.tvStatus.setClickable(true);
        } else{
            holder.tvStatus.setBackgroundResource(R.drawable.shape_grey_radius10);
            holder.tvStatus.setText("已通过");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.white));
            holder.tvStatus.setClickable(true);
        }

        return view;
    }

    private void getRequestAgree(String id, int status) {
        EasyHttp.post(Constants.REQUIRED_APPROVAL + session)
                .params("F_ID",id)
                .params("F_State",status+"")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(context,1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(context,0);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("getRequestAgree",s);
                        try {
                            JSONObject object = new JSONObject(s);
                            String status = (String) object.get("Msg");
                            ToastUtils.getInstance().showToast(status);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    class ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void addItemNum(int number) {
        itemCount = number;
    }
}
