package com.example.lxx.myalipay.ui.fragment.a_package.news;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseBackFragment;
import com.example.lxx.myalipay.widget.HackyViewPager;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.OnClick;

public class PhotoBrowserFragment extends BaseBackFragment {

    @BindView(R.id.pager)
    HackyViewPager mPager;
    @BindView(R.id.crossIv)
    AppCompatImageView crossIv;
    @BindView(R.id.photoOrderTv)
    TextView photoOrderTv;
    @BindView(R.id.saveTv)
    TextView saveTv;
    @BindView(R.id.centerIv)
    ProgressBar progressBar;

    private String curImageUrl = "";
    private String[] imageUrls = new String[]{};

    private int curPosition = -1;
    private int[] initialedPositions = null;

    public static PhotoBrowserFragment newInstance(String[] imageUrls, String img) {
        PhotoBrowserFragment fragment = new PhotoBrowserFragment();
        fragment.imageUrls = imageUrls;
        fragment.curImageUrl = img;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo_browser;
    }

    @Override
    protected void initView() {
        if (curImageUrl != null && imageUrls == null) {
            imageUrls = new String[]{curImageUrl};
        }
        initialedPositions = new int[imageUrls.length];
        initInitialedPositions();
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageUrls.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                if (imageUrls[position] != null && !"".equals(imageUrls[position])) {
                    final PhotoView view = new PhotoView(_mActivity);
                    view.isEnabled();
                    view.setScaleType(ImageView.ScaleType.FIT_CENTER);

                    container.addView(view);
                    return view;
                }
                return null;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                releaseOnePosition(position);
                container.removeView((View) object);
            }
        });

        curPosition = returnClickedPosition() == -1 ? 0 : returnClickedPosition();
        mPager.setCurrentItem(curPosition);
        mPager.setTag(curPosition);
        if (initialedPositions[curPosition] != curPosition) {//如果当前页面未加载完毕，则显示加载动画，反之相反；
            showLoadingProgressBar();
        }
        photoOrderTv.setText((curPosition + 1) + "/" + imageUrls.length);//设置页面的编号

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (initialedPositions[position] != position) {//如果当前页面未加载完毕，则显示加载动画，反之相反；
                    showLoadingProgressBar();
                } else {
                    hideLoadingAnimation();
                }
                curPosition = position;
                photoOrderTv.setText((position + 1) + "/" + imageUrls.length);//设置页面的编号
                mPager.setTag(position);//为当前view设置tag
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void hideLoadingAnimation() {
        releaseResource();
        progressBar.setVisibility(View.GONE);
    }

    private void showLoadingProgressBar() {
//       centerIv.setVisibility(View.VISIBLE);
//       centerIv.setImageResource(R.drawable.ProgressUtils);
//       ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(centerIv, "rotation", 0f, 360);
//       objectAnimator.setDuration(500);
//       objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//       objectAnimator.start();
        progressBar.setVisibility(View.VISIBLE);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(layoutParams);

    }

    private int returnClickedPosition() {
        if (imageUrls == null || curImageUrl == null) {
            return -1;
        }
        for (int i = 0; i < imageUrls.length; i++) {
            if (curImageUrl.equals(imageUrls[i])) {
                return i;
            }
        }
        return -1;
    }

    private void initInitialedPositions() {
        for (int i = 0; i < initialedPositions.length; i++) {
            initialedPositions[i] = -1;
        }
    }

    private void releaseOnePosition(int position) {
        initialedPositions[position] = -1;
    }
    private void savePhotoToLocal() {
        ViewGroup containerTemp = (ViewGroup) mPager.findViewWithTag(mPager.getCurrentItem());
        if (containerTemp == null) {
            return;
        }
        PhotoView photoViewTemp = (PhotoView) containerTemp.getChildAt(0);

    }

    private void releaseResource() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.pager, R.id.crossIv, R.id.photoOrderTv, R.id.saveTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pager:
               _mActivity.onBackPressed();
                break;
            case R.id.crossIv:
                break;
            case R.id.photoOrderTv:
                break;
            case R.id.saveTv:
                savePhotoToLocal();
                break;
        }
    }

    @Override
    public void onDestroy() {
        releaseResource();
        super.onDestroy();
    }
}
