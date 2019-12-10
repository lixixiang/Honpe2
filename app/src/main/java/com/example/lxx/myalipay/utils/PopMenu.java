package com.example.lxx.myalipay.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.example.lxx.myalipay.R;

import java.util.ArrayList;

/**
 * created by lxx at 2019/12/1 12:23
 * 描述:用于解决企业管理上拉菜单
 */
public class PopMenu {
    private ArrayList<String> itemList;
    private Context context;
    private PopupWindow popupWindow;
    private ListView listView;
    private AdapterView.OnItemClickListener listener;
    public PopMenu(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;

        itemList = new ArrayList<String>(5);

        View view = LayoutInflater.from(context)
                .inflate(R.layout.popmenu, null);
        // 设置 listview
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(new PopAdapter());
        listView.setFocusableInTouchMode(true);
        listView.setFocusable(true);

        popupWindow = new PopupWindow(view,  context.getResources()
                .getDimensionPixelSize(R.dimen._100dp),
                WindowManager.LayoutParams.WRAP_CONTENT,true);
        //防止虚拟软键盘被弹出菜单遮住
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    // 设置菜单项点击监听器
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        // this.listener = listener;
        listView.setOnItemClickListener(listener);
    }

    // 批量添加菜单项
    public void addItems(String[] items) {
        for (String s : items)
            itemList.add(s);
    }

    // 单个添加菜单项
    public void addItem(String item) {
        itemList.add(item);
    }


    // 下拉式 弹出 pop菜单 parent 右下角
    public void showAsDropDown(View parent) {
        popupWindow.showAsDropDown(parent,
                context.getResources().getDimensionPixelSize(
                        R.dimen.popmenu_yoff),
                // 保证尺寸是根据屏幕像素密度来的 这个就是调整与你显示的editText的距离
                context.getResources().getDimensionPixelSize(
                        R.dimen.popmenu_yoff),Gravity.CENTER);

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        // 刷新状态
        popupWindow.update();
    }

    public void showAtLocation(View parent){

        parent.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int[] location = new int[2];
        int popupWidth = parent.getMeasuredWidth();
        int popupHeight =  parent.getMeasuredHeight();
        parent.getLocationOnScreen(location);
        Log.i("parent","popupWidth:"+ popupWidth+"popupHeight:"+ popupHeight);
        popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, (location[0]+parent.getWidth()/10)-popupWidth/3,
                location[1]-popupHeight/2);
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        // 刷新状态
        popupWindow.update();
    }

    // 隐藏菜单
    public void dismiss() {
        popupWindow.dismiss();
    }



    // 适配器
    private final class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.pomenu_item, parent,false);
                holder = new ViewHolder();

                convertView.setTag(holder);

                holder.groupItem = (TextView) convertView
                        .findViewById(R.id.textView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.groupItem.setText(itemList.get(position));

            return convertView;
        }

        private final class ViewHolder {
            TextView groupItem;
        }
    }
}


