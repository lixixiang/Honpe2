package com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lxx.myalipay.R;

import java.util.List;

/**
 * created by lxx at 2019/12/1 19:02
 * 描述:
 */
public class BaseAddImageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private List<String> mList;
    public static final String IMG_LIST = "img_list"; //第几张图片
    public static final String POSITION = "position"; //第几张图片
    public static final String PIC_PATH = "pic_path"; //图片路径
    public static final int MAX_SELECT_PIC_NUM = 5; // 最多上传5张图片
    public static final int REQUEST_CODE_MAIN = 10; //请求码
    public static final int RESULT_CODE_VIEW_IMG = 11; //查看大图页面的结果码
    public BaseAddImageAdapter(@Nullable List<String> data) {
        super(R.layout.item_add_delete_image, data);
        mList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getLayoutPosition();

        if (position < mList.size()) {
            //代表+号之前的需要正常显示图片
            String picUrl = mList.get(position);
            Glide.with(mContext).load(picUrl).into((ImageView) helper.getView(R.id.iv_image));
            helper.setVisible(R.id.bt_del,true);
            helper.addOnClickListener(R.id.bt_del);
        }else{
            helper.setImageResource(R.id.iv_image,R.drawable.icon_add_pic);
            helper.setVisible(R.id.bt_del,false);
        }
    }

    @Override
    public int getItemCount() {
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > MAX_SELECT_PIC_NUM) {
            return mList.size();
        } else {
            return count;
        }
    }
}
