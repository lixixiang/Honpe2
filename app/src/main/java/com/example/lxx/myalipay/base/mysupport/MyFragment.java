package com.example.lxx.myalipay.base.mysupport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @package: com.example.lxx.myalipay.base
 * @date: 2018/9/27 14:32
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：
 */
public abstract class MyFragment extends MySupportFragment  {
    private static final String TAG = "Fragmentation";
    private Unbinder mUnbinder;
    private View rootView;
    private boolean isFragmentVisible;
    public boolean isFirst;
    protected String fragmentTitle;             //fragment标题
    private InputMethodManager imm;
    protected OnFragmentOpenDrawerListener mOpenDraweListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            mOpenDraweListener = (OnFragmentOpenDrawerListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentOpenDrawerListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(TAG);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(TAG, isHidden());
    }

    protected void initToolbarNav(final View homeBack) {
        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            _mActivity.finish();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(getLayoutResource(), container, false);
            mUnbinder = ButterKnife.bind(this, rootView);
            initView();
            //可见，但是并没有加载过
            if(isFragmentVisible && !isFirst){
                onFragmentVisibleChange(true);
            }
        }
        return rootView;
    }

    //获取布局文件
    protected abstract int getLayoutResource();
    //初始化view
    protected abstract void initView();
    protected boolean isRegisterEventBus(){
        return false;
    }
    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作.
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible){

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            isFragmentVisible = true;
        }
        if (rootView == null){
            return;
        }
        //可见，并且没有加载过
        if (!isFirst && isFragmentVisible){
            onFragmentVisibleChange(true);
            return;
        }
        //由可见——>不可见 已经加载过
        if (isFragmentVisible){
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    /**
     * 含有Bundle通过Class跳转界面
     */
    public void startActivity(Class<?> cls,Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle !=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    /**
     * 含有Bundle通过Class跳转界面
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode){
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle !=null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        this.imm = null;

    }

    public void showSoftInput(Context context, View view) {
        this.imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public void hideSoftInput(Context context, View view) {
        this.imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    public boolean isShowSoftInput(Context context) {
        this.imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //获取状态信息
        return imm.isActive();//true 打开
    }

    public void finish() {
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = _mActivity.getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) _mActivity.getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    public String getTitle() {
        return TextUtils.isEmpty(fragmentTitle) ? "" : fragmentTitle;
    }

    public void setTitle(String title) {
        fragmentTitle = title;
    }
    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOpenDraweListener = null;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发的粘性事件
     * @param event
     */
    protected void receiveStickyEvent(Event event){

    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event){

    }
}


