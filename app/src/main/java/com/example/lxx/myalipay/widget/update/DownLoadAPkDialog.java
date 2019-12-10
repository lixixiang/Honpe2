package com.example.lxx.myalipay.widget.update;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.example.lxx.myalipay.widget.ToastUtils;
import com.flyco.dialog.widget.base.BaseDialog;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.utils.HttpLog;

import java.math.BigDecimal;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lxx.myalipay.api.FinalClass.REQUEST_CODE_APP_INSTALL;


/**
 * created by lxx at 2019/11/13 10:03
 * 描述:
 */
public class DownLoadAPkDialog extends BaseDialog<DownLoadAPkDialog> {
    @BindView(R.id.number_progress_bar)
    NumberProgressBar numberProgressBar;
    @BindView(R.id.tv_progress_number)
    TextView tvProgressNumBer;
    @BindView(R.id.tv_progress_percent)
    TextView tvProgressPercent;
    private String apk;
    private String mProgressNumberFormat;
    private NumberFormat mProgressPercentFormat;
    private long oldBytesRead;

    public DownLoadAPkDialog(Context context, String apkUrl) {
        super(context);
        this.apk = apkUrl;
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        View inflater = View.inflate(mContext, R.layout.download_apk, null);
        ButterKnife.bind(this, inflater);
        downLoad();

        return inflater;
    }

    private void initFormats() {
        mProgressNumberFormat = "%1.2fM/%2.2fM";
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
    }

    @Override
    public void setUiBeforShow() {

    }

    private void downLoad() {
        EasyHttp.downLoad(apk)
                .savePath(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/")
                .saveName("honpe.apk")
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void onStart() {
                        HttpLog.i("======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.getInstance().showToast(e.getMessage());
//                        dismiss();
                        HttpLog.i("======" + e.getMessage());
                    }

                    @Override
                    public void update(final long bytesRead, final long contentLength, final boolean done) {
                        final int progress = (int) (bytesRead * 100 / contentLength);
                        double dProgress = (double) bytesRead / (double) (1024 * 1024);
                        double dMax = (double) contentLength / (double) (1024 * 1024);
                        initFormats();
                        if (mProgressNumberFormat != null) {
                            String format = mProgressNumberFormat;
                            tvProgressNumBer.setText(String.format(format, dProgress, dMax));
                        } else {
                            tvProgressNumBer.setText("");
                        }
                        if (oldBytesRead != 0) {
                            long NetWorkSpeek = bytesRead - oldBytesRead;
                            double newSpeek = (double) NetWorkSpeek / (double) (1024);
                            BigDecimal bg = new BigDecimal(newSpeek);
                            newSpeek = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            LatteLogger.d("NetWorkSpeek", NetWorkSpeek + "  " + bytesRead + "  " + oldBytesRead + "  " + newSpeek);
                            tvProgressPercent.setText(String.valueOf(newSpeek) + "KB/s");
                        }
                        oldBytesRead = bytesRead;

                        if (done) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // dismiss();
                                    ToastUtils.getInstance().showToast("下载完成");
                                }
                            }, 3000);
                        } else {
                            LatteLogger.d("progress", progress + "    " + String.valueOf(contentLength));
                            numberProgressBar.setProgress((int) progress);
                        }
                    }

                    @Override
                    public void onComplete(String path) {
                        LatteLogger.d("path", path);
                        Event<String> event = new Event<String>(REQUEST_CODE_APP_INSTALL, path);
                        EventBusUtil.sendEvent(event);
                    }
                });
    }
    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}






