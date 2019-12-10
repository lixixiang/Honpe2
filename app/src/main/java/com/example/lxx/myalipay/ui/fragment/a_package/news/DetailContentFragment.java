package com.example.lxx.myalipay.ui.fragment.a_package.news;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.Constants;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.ui.fragment.a_package.entity.DetailContentBean;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.ProgressUtils;
import com.example.lxx.myalipay.utils.StringUtils;
import com.example.lxx.myalipay.utils.gson.Convert;
import com.google.gson.Gson;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.IWebLayout;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * created by lxx at 2019/11/12 11:05
 * 描述:打开的是一个网页 用于详情页面原生
 */
public class DetailContentFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView titles;
    @BindView(R.id.tv_content)
    WebView contentWebView;
    @BindView(R.id.text_models_webViews)
    ImageView imgControl;
    @BindView(R.id.container)
    LinearLayout linContainer;
    @BindView(R.id.fragment_content)
    LinearLayout linearLayout;
    @BindView(R.id.toolbar_title)
    TextView mTitleTextView;
    @BindView(R.id.toolbars)
    Toolbar toolbar;
    @BindView(R.id.iv_back)
    ImageView mBackImageView;
    @BindView(R.id.view_line)
    View mLineView;
    @BindView(R.id.iv_finish)
    ImageView mFinishImageView;
    @BindView(R.id.iv_more)
    ImageView mMoreImageView;

    private String title, id, data, vip, detailUrl, net;
    //数据集合
    private String[] imageUrls;
    protected AgentWeb mAgentWeb;
    private PopupMenu mPopupMenu;
    private Disposable disposable;

    public static DetailContentFragment newInstance(String title, String id, String vip) {
        DetailContentFragment fragment = new DetailContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("id", id);
        bundle.putString("vip", vip);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.detail_content_fragment;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        id = bundle.getString("id");
        vip = bundle.getString("vip");
        if (vip.equals("")) {
            detailUrl = Constants.HtmlURL;
        } else {
            detailUrl = Constants.DETAIL_URL;
        }
        titles.setText(title);
        initToolbarNav(homeBack);
    }


    @Override
    public void onResume() {
        super.onResume();
        getCheckedData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EasyHttp.cancelSubscription(disposable);
    }


    private void getCheckedData() {
        disposable =  EasyHttp.post(detailUrl)
                .params("id", id)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity,1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onSuccess(String s) {
                        ProgressUtils.disLoadView(_mActivity,0);
                        dealData(s);
                    }
                });
    }

    private void dealData(String result) {
        DetailContentBean bean = Convert.fromJson(result, DetailContentBean.class);
        data = bean.getData().getContent();
        net = bean.getData().getWww();
        LatteLogger.d("webbbbb", "data========" + data + "====net=====" + net);
        linearLayout.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.GONE);
        contentWebView.setVisibility(View.VISIBLE);
        imgControl.setVisibility(View.GONE);
        //step1.
        if ("".equals(net) && !"".equals(data) || !"".equals(net) && !"".equals(data)) {
            if (data.contains("http://www.honpe.com") || data.contains("<img src=\"http:")) {
                imageUrls = StringUtils.returnImageUrlsFromHtml2(data);
            } else {
                imageUrls = StringUtils.returnImageUrlsFromHtml(data);
            }
        } else if ("".equals(data) && !"".equals(net)) {
            linearLayout.setVisibility(View.GONE);
            toolbar.setVisibility(View.VISIBLE);
            mAgentWeb = AgentWeb.with(_mActivity)
                    .setAgentWebParent(linContainer, new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .setWebChromeClient(mWebChromeClient)
                    .setWebViewClient(mWebViewClient)
                    .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                    .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                    .setWebLayout(new WebLayout(_mActivity))
                    .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                    .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                    .createAgentWeb()
                    .ready()
                    .go(net);

        }
        //step2.WebView开启支持JavaScript
        contentWebView.getSettings().setJavaScriptEnabled(true);
        contentWebView.getSettings().setAppCacheEnabled(true);
        contentWebView.getSettings().setDatabaseEnabled(true);
        contentWebView.getSettings().setDomStorageEnabled(true);
        contentWebView.loadDataWithBaseURL(Constants.path, getNewContent(data), "text/html", "utf-8", null);
        contentWebView.addJavascriptInterface(new MJavascriptInterface(_mActivity, imageUrls), "imagelistener");
        contentWebView.setWebViewClient(new MyWebViewClient());

    }

    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        Log.d("VACK", doc.toString());
        return doc.toString();
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
//            Log.i("Info","onProgress:"+newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mTitleTextView != null) {
                mTitleTextView.setText(title);
            }
        }
    };
    private WebViewClient mWebViewClient = new WebViewClient() {
        private HashMap<String, Long> timer = new HashMap<>();

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl() + "");
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        //
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {

            Log.i(TAG, "view:" + new Gson().toJson(view.getHitTestResult()));
            Log.i(TAG, "mWebViewClient shouldOverrideUrlLoading:" + url);
            //intent:// scheme的处理 如果返回false ， 则交给 DefaultWebClient 处理 ， 默认会打开该Activity  ， 如果Activity不存在则跳到应用市场上去.  true 表示拦截
            //例如优酷视频播放 ，intent://play?...package=com.youku.phone;end;
            //优酷想唤起自己应用播放该视频 ， 下面拦截地址返回 true  则会在应用内 H5 播放 ，禁止优酷唤起播放该视频， 如果返回 false ， DefaultWebClient  会根据intent 协议处理 该地址 ， 首先匹配该应用存不存在 ，如果存在 ， 唤起该应用播放 ， 如果不存在 ， 则跳到应用市场下载该应用 .
            if (url.startsWith("intent://") && url.contains("com.youku.phone")) {
                return true;
            }
			/*else if (isAlipay(view, mUrl))   //1.2.5开始不用调用该方法了 ，只要引入支付宝sdk即可 ， DefaultWebClient 默认会处理相应url调起支付宝
			    return true;*/


            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            Log.i(TAG, "mUrl:" + url + " onPageStarted  target:" + net);
            timer.put(url, System.currentTimeMillis());
            if (url.equals(net)) {
                pageNavigator(View.GONE);
            } else {
                pageNavigator(View.VISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if (timer.get(url) != null) {
                long overTime = System.currentTimeMillis();
                Long startTime = timer.get(url);
                Log.i(TAG, "  page mUrl:" + url + "  used time:" + (overTime - startTime));
            }

        }
    };

    private void pageNavigator(int tag) {
        mBackImageView.setVisibility(tag);
        mLineView.setVisibility(tag);
    }


    @OnClick({R.id.iv_back,  R.id.iv_finish, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (!mAgentWeb.back()) {
                    _mActivity.onBackPressed();
                }
                break;
            case R.id.iv_finish:
                _mActivity.onBackPressed();
                break;
            case R.id.iv_more:
                showPoPup(view);
                break;
        }
    }

    /**
     * 显示更多菜单
     *
     * @param view 菜单依附在该View下面
     */
    private void showPoPup(View view) {
        if (mPopupMenu == null) {
            mPopupMenu = new PopupMenu(this.getActivity(), view);
            mPopupMenu.inflate(R.menu.toolbar_menu);
            mPopupMenu.setOnMenuItemClickListener(mOnMenuItemClickListener);
        }
        mPopupMenu.show();
    }
    /**
     * 菜单事件
     */
    private PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {

                case R.id.refresh:
                    if (mAgentWeb != null) {
                        mAgentWeb.getUrlLoader().reload(); // 刷新
                    }
                    return true;

                case R.id.copy:
                    if (mAgentWeb != null) {
                        toCopy(getContext(), mAgentWeb.getWebCreator().getWebView().getUrl());
                    }
                    return true;
                case R.id.default_browser:
                    if (mAgentWeb != null) {
                        openBrowser(mAgentWeb.getWebCreator().getWebView().getUrl());
                    }
                    return true;
                case R.id.default_clean:
                    toCleanWebCache();
                    return true;
                case R.id.error_website:
                    loadErrorWebSite();
                    return true;
                default:
                    return false;
            }

        }
    };

    /**
     * 复制字符串
     *
     * @param context
     * @param text
     */
    private void toCopy(Context context, String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    private void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            Toast.makeText(this.getContext(), targetUrl + " 该链接无法使用浏览器打开。", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri mUri = Uri.parse(targetUrl);
        intent.setData(mUri);
        startActivity(intent);
    }

    /**
     * 测试错误页的显示
     */
    private void loadErrorWebSite() {
        if (mAgentWeb != null) {
            mAgentWeb.getUrlLoader().loadUrl("http://www.unkownwebsiteblog.me");
        }
    }

    /**
     * 清除 WebView 缓存
     */
    private void toCleanWebCache() {
        if (this.mAgentWeb != null) {
            //清理所有跟WebView相关的缓存 ，数据库， 历史记录 等。
            this.mAgentWeb.clearWebCache();
            Toast.makeText(getActivity(), "已清理缓存", Toast.LENGTH_SHORT).show();
            //清空所有 AgentWeb 硬盘缓存，包括 WebView 的缓存 , AgentWeb 下载的图片 ，视频 ，apk 等文件。
//            AgentWebConfig.clearDiskCache(this.getContext());
        }
    }


    public class WebLayout implements IWebLayout {
        private Activity mActivity;
        private final TwinklingRefreshLayout mTwinklingRefreshLayout;
        private WebView mWebView = null;

        public WebLayout(Activity activity) {
            this.mActivity = activity;
            mTwinklingRefreshLayout = (TwinklingRefreshLayout) LayoutInflater.from(activity).inflate(R.layout.fragment_twk_web, null);
            mTwinklingRefreshLayout.setPureScrollModeOn();
            mWebView = (WebView) mTwinklingRefreshLayout.findViewById(R.id.webView);
        }

        @NonNull
        @Override
        public ViewGroup getLayout() {
            return mTwinklingRefreshLayout;
        }

        @Nullable
        @Override
        public WebView getWebView() {
            return mWebView;
        }

    }

    public class MJavascriptInterface {
        private Context context;
        private String[] imageUrls;

        public MJavascriptInterface(Context context, String[] imageUrls) {
            this.context = context;
            this.imageUrls = imageUrls;
        }

        @android.webkit.JavascriptInterface
        public void openImage(String img){
            Intent intent = new Intent();
            intent.putExtra("imageUrls",imageUrls);
            intent.putExtra("curImageUrl", img);
         //   intent.setClass(context, PhotoBrowserActivity.class);
            LatteLogger.d("imageUrls",imageUrls);
            context.startActivity(intent);
            Log.i("curImage:",img.toString());
            for (int i = 0; i < imageUrls.length; i++) {
                Log.e("imageUrls"+i,imageUrls[i].toString());
            }
        }
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            addImageClickListener(view);//待网页加载完全后设置图片点击的监听方法
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageStarted(view, url, favicon);
        }

        private void addImageClickListener(WebView webView) {
            webView.loadUrl("javascript:(function(){" +
                    "var objs = document.getElementsByTagName(\"img\"); " +
                    "for(var i=0;i<objs.length;i++)  " +
                    "{"
                    + "    objs[i].onclick=function()  " +
                    "    {  "
                    + "        window.imagelistener.openImage(this.src);  " +//通过js代码找到标签为img的代码块，设置点击的监听方法与本地的openImage方法进行连接
                    "    }  " +
                    "}" +
                    "})()");
        }
    }
}

















