package com.example.lxx.myalipay.api;

/**
 * created by lxx at 2019/11/11 11:33
 * 描述:
 */
public class FinalClass  {
    public static final int FIRST = 0;//首页碎片
    public static final int SECOND = 1;//我的碎片

    public static final int START_CAMER_RESULT = 0x2;//扫描
    public static final int RC_CAMERA = 0X01;
    public static final int Address = 0x02;
    public static final int EDIT_ADDRESS = 0x03;
    public static final int ADD_ADDRESS = 0x04;
    public static final int REQUEST_IMAGE_GET = 1;
    public static final int REQUEST_PHOTO_CUT = 2;
    //保存登录信息
    public static final String IsSession = "IsSession";
    public static final String Status = "Status";
    public static final String Session = "KeyValue";
    public static final String UserName = "UserName";
    public static final String UserCode = "UserCode";
    public static final String PassWord = "PassWord";
    public static final String HeadIcon = "Ico";
    public static final String CircleIcon = "InSpeechBg";
    public static final String UserType = "UserType"; //判断登陆时显示 内部员工首页图标
    //////////////////////////////////Event//////////////////////////////////////
    public static final int ME_info = 4;
    public static final int UPDATA_NONEED = 0;//不需要更新     //软件更新
    public static final int UPDATA_CLIENT = 1;
    public static final int GET_UNDATAINFO_ERROR = 2;
    //安装APK
    public static final int REQUEST_CODE_APP_INSTALL = 0x111;
    //退出APP
    public static final int EXIT_APP = 5;
    //上传头像返回到我的
    public static final int UPDATA_TO_ME = 6;
    //打开GPS权限
    public static final int OPEN_GPS = 5002;
    /**
     * viewpager
     */
    public static final int video_status = 0xff0;

    /**
     * 正方形二维码宽度
     */
    public static final int CODE_WIDTH = 440;
    /**
     * LOGO宽度值,最大不能大于二维码20%宽度值,大于可能会导致二维码信息失效
     */
    public static final int LOGO_WIDTH_MAX = CODE_WIDTH / 5;
    /**
     *LOGO宽度值,最小不能小鱼二维码10%宽度值,小于影响Logo与二维码的整体搭配
     */
    public static final int LOGO_WIDTH_MIN = CODE_WIDTH / 10;
    //新增访客单
    public static final int ADD_APPOINT = 0x11;
    public static final int REQ_MODIFY_FRAGMENT = 100;
    //访客新增内
    public static final int A = 0x111111;
    public static final int B = 0x222222;
    public static final int C = 0x333333;
    public static final int D = 0x444444;
    public static final int E = 0x555555;
    public static final int F = 0x666666;
    public static final int G = 0x777777;
    public static final int H = 0x888888;
    public static final int I = 0x999999;
    public static final int J = 0x2;
    public static final int K = 0x22;
    public static final int L = 0x222;
    //行车轨迹
    public static final String Tokey = "Tokey";
    public static final String UserId = "UserId";

    //复杂布局常量
    public static final int TEXT = 1;
    public static final int TEXT_IMAGE = 2;
    public static final int DEPART = 3;
    public static final int YEAR_MONTH_DAY_TIME_MINUTE = 4;
    public static final int EDIT = 5;
    public static final int REMARKS = 6;
    public static final int REPORT_STAFF = 0x1;//员工点餐1
}
