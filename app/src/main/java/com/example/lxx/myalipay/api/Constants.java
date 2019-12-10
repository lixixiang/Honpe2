package com.example.lxx.myalipay.api;

/**
 * created by lxx at 2019/11/12 10:20
 * 描述:
 */
public class Constants {

    public static final String URL = "http://api.honpe.com:8099/";
    public static final String URL2 = "http://api.honpe.com:8068/";

    //新闻列表
    public static final String NEWS_URL = URL + "api/Web/News/PostGetNewsList";

    //图片路径
    public static final String path = "http://old.honpe.com";

    //Html页面URL
    public static final String HtmlURL = URL + "api/Web/News/PostGetNewsContent";
    public static final String DETAIL_URL = "http://api.honpe.com:8099/api/Web/WebContent/PostGetWebContent";


    //app下载更新
    public static final String APPDownload = URL + "api/sys/app/PostRequestforAppVer";
    //地图轨迹头部
    public static final String header ="http://www.gpsnow.net/";
    //检测App是否为最新版本 红品APK
    public static final String VERSION = "http://api.honpe.com:81/honpeapp/honpe.apk";
    //获取访客预约申请列表接口（管理人员）
    public static final String MANAGER_LIST = URL + "api/erp/visitorappt/postvisitorapprovallist?sessionkey=";
    //获取本人访客预约申请列表接口
    public static final String SHOW_APPOINT_LIST = URL + "api/erp/visitorappt/postvisitorapptlist?sessionkey=";
    //Erp用户模块权限接口 如:增加，修改，作废 旧版第一个界面底部按钮
    public static final String ADD_MOBE_DELETER = URL + "api/erp/sendcar/posterpmoduleauths?sessionkey=";
    //honpe登录
    public static final String LoginURL = URL + "api/sys/account/getlogin";
    //上传头像的接口  "http://192.168.20.192:8098/api/oms/file/postuploadfilegeneral?sessionkey=""http://192.168.20.192:8098/api/oms/file/postuploadfilegeneral"
    public static final String UploadFile = URL + "api/oms/file/postuploadheadpic?sessionkey=";
    //修改密码
    public static final String MOB_PASS_WORD = URL + "api/sys/account/PostChangePassword?SessionKey=";
    //用户注册
    public static final String REGISTERINFO = URL + "api/sys/account/PostRegisterInfo";
    //找回密码
    public static final String FIND_PASS = URL+"api/sys/account/PostFindPassword";
    //访客预约审核接口（管理人员）
    public static final String ADMINISTRATOR = URL + "api/erp/visitorappt/postvisitorcheck?sessionkey=";
    //获取部门组织架构列表接口（用于申请单选择部门提交）
    public static final String departList = URL + "api/erp/crmbasedate/posterpdeptlist?sessionkey=";
    //提交访客预约申请接口
    public static final String SUBMIT_APPOINT = URL + "api/erp/visitorappt/postvisitorapptsave?sessionkey=";
    //公司通告及员工讨论列表接口
    public static final String YGMESSAGELIST = URL + "api/erp/erpnews/postgeterpnewslist?sessionkey=";
    //公司通告及员工讨论内容详情接口
    public static final String YGMESSAGEDETAIL = URL + "api/erp/erpnews/postgeterpnewscontent?sessionkey=";
    //获取派车申请单未完成列表接口：
    public static final String UNSENDCAR = URL +"api/erp/sendcar/posterpcarorderlist?sessionkey=";
    //主管确认接口 做一个测试
    public static final String MANAGER_CONFIRM = URL + "api/erp/sendcar/postconfirm?sessionkey=";
    //获取Erp部门列表接口
    public static final String MANAGER_DEPART = URL + "api/erp/erpbasedate/posterpdeptlist?sessionkey=";
    //获取Erp组别列表接口
    public static final String MANAGER_TEAM = URL + "api/erp/erpbasedate/posterpgrouplist?sessionkey=";
    //获取客户点餐列表接口
    public static final String GETCUSTOMORDERLIST = URL + "api/erp/ordering/posterporderings?sessionkey=";
    // 1.取消点餐申请
    public static final String REQUEST_MENU_DELETE = URL + "api/erp/ordering/PostCancelOrdering?sessionkey=";
    //获取客户点餐菜谱列表接口
    public static final String GETMENULIST = URL + "api/erp/ordering/posterporderingfoods?sessionkey=";
    //修改点餐申请
    public static final String MOB_MENU = URL + "api/erp/ordering/postupdateordering?sessionkey=";
    //提交客户点餐申请接口
    public static final String COMMITORDER = URL + "api/erp/ordering/postsaveordering?sessionkey=";
    //新增派车申请单
    public static final String NEWADDORDER = URL + "api/erp/sendcar/postsavenewsendcarorder?sessionkey=";
    //修改派车申请单
    public static final String MOBELORDER = URL + "api/erp/sendcar/postupdatesendcarorder?sessionkey=";
    //获取Erp车辆列表接口
    public static final String MANAGER_CAR = URL + "api/erp/erpbasedate/posterpcarinfolist?sessionkey=";
    //获取Erp司机列表接口
    public static final String MANAGER_DIVERS = URL + "api/erp/erpbasedate/posterpcardriverlist?sessionkey=";
    //指派车辆接口
    public static final String MANAGER_SEND_CAR = URL + "api/erp/sendcar/postsendcar?sessionkey=";
    //获取外发采购申请单接口
    public static final String MANAGER_OUTSIDE_APPLYLIST = URL + "api/erp/oemapply/postoemapplyorder?sessionkey=";
    //获取外发采购申请单明细接口
    public static final String MANAGER_OUTSIDE_APPLYLIST_DETAIL = URL + "api/erp/oemapply/postoemapplyorderinfo?sessionkey=";
    //获取外发采购申请审批接口
    public static final String MANAGER_APPLY_CONFIRM = URL + "api/erp/oemapply/postoemapplyconfirm?sessionkey=";
    //新个人考勤记录获取接口
    public static final String PERSON_CHECK = URL +"api/oa/attendance/getuserdayattendance?sessionkey=";
    //请假申请列表接口：
    public static final String LEAVEAPPLYLIST = URL + "api/erp/askleave/posterpaskleavelist?sessionkey=";
    // 2. 修改请假
    public static final String REQUEST_OA_MOB = URL + "api/erp/askleave/postupdateaskleave?sessionkey=";
    //提交请假申请接口：
    public static final String SUBMIT_LEAVE = URL + "api/erp/askleave/postsaveaskleave?sessionkey=";
    // 1。取消请假接口
    public static final String REQUEST_OA_DELETE = URL + "api/erp/askleave/postcancelaskleave?sessionkey=";
    //主管审批接口
    public static final String REQUIRED_APPROVAL = URL + "api/oa/attendance/postsupattendcheck?sessionkey=";
    //获取申请人补卡记录接口（有审批权限主管）
    public static final String MASTED_CARD = URL + "api/oa/attendance/postsupattendapprovallist?sessionkey=";
    //获取申请人补卡记录接口
    public static final String FULL_CARD_LIST = URL + "api/oa/attendance/postsupattendlist?sessionkey=";
    //提交补卡申请接口
    public static final String FULL_CARD = URL + "api/oa/attendance/postmakeupattendance?sessionkey=";
    //获取宿舍列表接口
    public static final String REQUEST_DORM = URL +"api/erp/dormitory/postdormitorylist?sessionkey=";
    //宿舍新增住宿人员，修改住宿人员宿舍接口：
    public static final String SUBMIT_ADD_MOB_DORM = URL + "api/erp/dormitory/postsaveresident?sessionkey=";
    //移除住宿人员接口
    public static final String DELETE_BED = URL + "api/erp/dormitory/postremoveresident?sessionkey=";
    //派车申请单查询
    public static final String CARAPPLYSEARCH = URL + "api/erp/sendcar/posterpsendcar?sessionkey=";
    //获取员工报餐列表接口
    public static final String YGREPORTMEETLIST = URL +"api/erp/staffmeal/poststaffmeallist?sessionkey=";
    //提交员工报餐接口
    public static final String YGREPORTMEET = URL + "api/erp/staffmeal/postsavestaffmeal?sessionkey=";
    //提交员工点餐菜品接口
    public static final String YGCOMMITMIUE = URL +"api/erp/staffmeal/postsavestaffmealorder?sessionkey=";
    //提交餐评接口
    public static final String GETCOMMENT = URL + "api/erp/staffmeal/postsaveevaluation?sessionkey=";
    //获取餐评列表接口
    public static final String GETORDERLIST = URL + "api/erp/staffmeal/postevaluationlist?sessionkey=";
    //获取员工点餐菜品列表接口
    public static final String YGORDERMENULIST = URL + "api/erp/staffmeal/poststaffdisheslist?sessionkey=";
    //新增外勤定位接口
    public static final String FIELD_ORIENTATION = URL + "api/oa/fieldlocation/postaddlocation?sessionkey=";



    //获取地图
    //1.登录
    public static final String LoginUrl = header + "user/login.do";
    //2.用户信息
    public static final String getCar = "http://47.93.194.90/car/getByUserId.do";
    //3 查询历史轨迹
    public static final String getHistory = header +"position/queryHistory.do";
    //4.根据车辆 id 获取车辆状态
    public static final String getLastIndex = header+"carStatus/getByCarIds.do";
}



























