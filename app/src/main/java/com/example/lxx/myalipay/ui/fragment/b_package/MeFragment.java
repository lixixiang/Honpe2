package com.example.lxx.myalipay.ui.fragment.b_package;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lxx.myalipay.MainFragment;
import com.example.lxx.myalipay.MyApplication;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseMainFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.about.AboutFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.adapter.MeAdapter;
import com.example.lxx.myalipay.ui.fragment.b_package.address.AddressFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.entity.IconTextDirectorBean;
import com.example.lxx.myalipay.ui.fragment.b_package.mobi_icon.MobIconFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.qr_code.DownloadQRCodeFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.setting.SettingFragment;
import com.example.lxx.myalipay.ui.fragment.b_package.setting.feedBack.FeedBackFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.ui.login.bean.LoginBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.widget.font.FontTextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.lxx.myalipay.api.FinalClass.EXIT_APP;
import static com.example.lxx.myalipay.api.FinalClass.HeadIcon;
import static com.example.lxx.myalipay.api.FinalClass.ME_info;
import static com.example.lxx.myalipay.api.FinalClass.Session;
import static com.example.lxx.myalipay.api.FinalClass.UserName;


/**
 * created by lxx at 2019/11/11 11:29
 * 描述:
 */
public class MeFragment extends BaseMainFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_un_login)
    RelativeLayout reUnLogin;
    @BindView(R.id.image_head)
    CircleImageView imageHead;
    @BindView(R.id.ll_login_with_register)
    LinearLayout llLoginWithRegister;
    @BindView(R.id.ll_is_login)
    LinearLayout llIsLogin;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_font)
    FontTextView tvFont;
    MeAdapter adapter;
    List<IconTextDirectorBean> mList = new ArrayList<>();
    private String[] icons;
    private String[] texts;
    private int[] fontIcons = {R.color.green, R.color.green, R.color.green, R.color.green, R.color.green};
    private String headIcon, userName,session;
    private LoginBean bean;
    private SharedPreferences sp;

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        llBack.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.me));

        icons = getResources().getStringArray(R.array.me_icon_list);
        texts = getResources().getStringArray(R.array.me_text_list);
        headIcon = (String) MyApplication.get(_mActivity, HeadIcon, "");
        userName = (String) MyApplication.get(_mActivity, UserName, "");
        session = (String) MyApplication.get(_mActivity, Session, "");
        sp = _mActivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        ImageOnTouchEvent();
        ChangeLoginStatus();

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        for (int i = 0; i < icons.length; i++) {
            IconTextDirectorBean item = new IconTextDirectorBean(icons[i], texts[i], fontIcons[i]);
            mList.add(item);
        }
        adapter = new MeAdapter(mList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(SettingFragment.newInstance(texts[position]));
                    }
                    break;
                case 1:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(AddressFragment.newInstance(texts[position]));
                    }
                    break;
                case 2:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(DownloadQRCodeFragment.newInstance());
                    }
                    break;
                case 3: //FeedBackFragment.newInstance(titles[position]
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(FeedBackFragment.newInstance(texts[position]));
                    }
                    break;
                case 4:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(AboutFragment.newInstance(texts[position]));
                    }
                    break;
            }
        });
    }

    private void ChangeLoginStatus() {
        if (!"".equals(session)) {
            llLoginWithRegister.setVisibility(View.GONE);
            llIsLogin.setVisibility(View.VISIBLE);
            if (headIcon != null|| !TextUtils.isEmpty(headIcon)) {
                Glide.with(_mActivity).load(headIcon).into(imageHead);
            }else {
                imageHead.setImageResource(R.drawable.selector_men);
            }
            tvUserName.setText(userName);
        } else {
            llLoginWithRegister.setVisibility(View.VISIBLE);
            llIsLogin.setVisibility(View.GONE);
            imageHead.setImageResource(R.drawable.selector_men);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void ImageOnTouchEvent() {
        imageHead.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (session == "") {
                            imageHead.setImageResource(R.drawable.health_guide_men_selected);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (session == "") {
                            imageHead.setImageResource(R.drawable.health_guide_men_selected_click);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (session == "") {
                            imageHead.setImageResource(R.drawable.health_guide_men_unselected);
                        }
                        break;
                    default:
                        if (session == "") {
                            imageHead.setImageResource(R.drawable.health_guide_men_unselected);
                        }
                        break;
                }
                return false;
            }
        });
    }


    @OnClick({R.id.image_head, R.id.re_un_login,R.id.tv_font})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_head:

                break;
            case R.id.re_un_login:
                LatteLogger.d("re_un_login", session);
                if ("".equals(session)) {
                    ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                }
                break;
            case R.id.tv_font:
                if (!"".equals(session)) {
                    ((MainFragment)getParentFragment()).startBrotherFragment(MobIconFragment.newInstance(session,headIcon,userName));
                }else {
                    ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance());
                }
                break;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }


    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case ME_info:
                bean = (LoginBean) event.getData();
                session = bean.getSessionKey();
                headIcon = bean.getLogonUser().getIco();
                userName = bean.getLogonUser().getUserName();
                if (!"".equals(bean.getSessionKey())) {
                    llLoginWithRegister.setVisibility(View.GONE);
                    llIsLogin.setVisibility(View.VISIBLE);
                    LatteLogger.d("headIcon", headIcon);
                    if (!"".equals(headIcon)|| !TextUtils.isEmpty(headIcon)) {
                        Glide.with(_mActivity).load(bean.getLogonUser().getIco()).into(imageHead);
                    }else {
                        imageHead.setImageResource(R.drawable.selector_men);
                    }
                    tvUserName.setText(bean.getLogonUser().getUserName());
                } else {
                    llLoginWithRegister.setVisibility(View.VISIBLE);
                    llIsLogin.setVisibility(View.GONE);
                    imageHead.setImageResource(R.drawable.selector_men);
                }
                break;
            case EXIT_APP:
                session = "";
                llLoginWithRegister.setVisibility(View.VISIBLE);
                llIsLogin.setVisibility(View.GONE);
                imageHead.setImageResource(R.drawable.selector_men);
                _mActivity.onBackPressed();
                break;
            case FinalClass.UPDATA_TO_ME:
                Object obImg = event.getData();
                LatteLogger.d("obImg",obImg);
                MyApplication.remove(_mActivity,HeadIcon);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("HEAD_ICON",(String) obImg);
                editor.commit();
                MyApplication.put(_mActivity,HeadIcon,obImg);
                headIcon = (String) MyApplication.get(_mActivity,HeadIcon,"");
                Glide.with(_mActivity).load(obImg).into(imageHead);
                break;
        }
    }
}























