package com.example.lxx.myalipay.ui.fragment.b_package.mobi_icon;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.login.LoginFragment;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.Utils;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.soundcloud.android.crop.Crop;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.lxx.myalipay.api.FinalClass.UPDATA_TO_ME;


/**
 * created by lxx at 2019/11/23 9:08
 * 描述:修改头像
 */
public class MobIconFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.iv_circle)
    CircleImageView ivCircle;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    private String headIcon, userName;
    private Uri source, uricropFile;
    private String cropTempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cropHeadIcon.jpg";


    public static MobIconFragment newInstance(String session, String headIcon, String userName) {
        MobIconFragment fragment = new MobIconFragment();
        fragment.session = session;
        fragment.headIcon = headIcon;
        fragment.userName = userName;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = _mActivity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        Log.i("zBarLibary", "version:1.4.1  buildDate:2019年11月05日 ");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mob_icon;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("个人资料");
        tvUserName.setText(userName);
        LatteLogger.d("ddddddddddd",headIcon);

        if (!"".equals(headIcon)) {
            Glide.with(_mActivity).load(headIcon).into(ivCircle);
        }else {
            ivCircle.setImageResource(R.drawable.selector_men);
        }
    }


    @OnClick({R.id.iv_circle, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_circle:
                Crop.pickImage(getContext(), findFragment(MobIconFragment.class));
                break;
            case R.id.btn_sure:
                LatteLogger.d("source================>" + source);
                if (source != null) {
                    getUploadFile(source);
                } else {
                    _mActivity.onBackPressed();
                }
                break;
        }
    }

    private void getUploadFile(Uri source) {
        File file = Utils.UriToFile(source);
        String url = Constants.UploadFile + session;
        EasyHttp.post(url)
                .params("uploadtype", 1 + "")
                .params("image", file, file.getName(), null)
                .accessToken(true)
                .timeStamp(true)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity, 0);
                        ToastUtils.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("uploadImage", s);
                        if (s.contains("Invalid Session.")||s.contains(sessionE)) {
                            start(LoginFragment.newInstance());
                            ToastUtils.getInstance().showToast(sessionPastDue);
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if (jsonObject.getInt("Status") == 0) {
                                    JSONArray data = jsonObject.getJSONArray("Data");
                                    ToastUtils.getInstance().showToast(jsonObject.getString("Msg"));
                                    LatteLogger.d("datadata===============" + data.get(0));
                                    Event<Object> event = new Event<Object>(UPDATA_TO_ME, data.get(0));
                                    EventBusUtil.sendEvent(event);
                                }
                                _mActivity.onBackPressed();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            source = result.getData();
            beginCrop(source);
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        LatteLogger.d("beginCrop=============" + source);
        uricropFile = Uri.parse("file://" + cropTempPath);
        Crop.of(source, uricropFile).asSquare().start(getContext(), findFragment(MobIconFragment.class));
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            source = Crop.getOutput(result);
            LatteLogger.d("handleCrop=============" + source);
            ivCircle.setImageURI(source);
        } else if (resultCode == Crop.RESULT_ERROR) {
            ToastUtils.getInstance().showToast(Crop.getError(result).getMessage());
        }
    }
}






















