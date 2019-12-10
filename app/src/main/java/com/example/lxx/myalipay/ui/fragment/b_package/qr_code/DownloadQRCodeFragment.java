package com.example.lxx.myalipay.ui.fragment.b_package.qr_code;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.utils.Utils;

import butterknife.BindView;
import butterknife.OnLongClick;
import cn.bertsir.zbar.utils.QRUtils;

/**
 * 二维码下载APK
 */
public class DownloadQRCodeFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.tv_version_num)
    TextView tvVersionNum;
    /**
     * 生成的二维码图片存储的URI
     */
    private Uri imageFileUri;
    private Bitmap qrCode;

    public static DownloadQRCodeFragment newInstance() {
        DownloadQRCodeFragment fragment = new DownloadQRCodeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_download_qr;
    }

    @Override
    protected void initView() {
        initToolbarNav(homeBack);
        title.setText("关于红品app");

        tvVersionNum.setText("当前版本号:V" + Utils.getVersionDes(_mActivity));
        String content = "http://www.honpe.com/app";
        qrCode = QRUtils.getInstance().createQRCodeAddLogo(content, 100, 100,
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_honpe));
        ivQrCode.setImageBitmap(qrCode);
    }

    @OnLongClick(R.id.iv_qr_code)
    boolean img_code() {
        imageFileUri = Utils.BitmapToUri(getContext(), qrCode);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageFileUri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "分享到"));
        return true;
    }
}
