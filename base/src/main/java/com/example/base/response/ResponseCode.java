package com.example.base.response;

/**
 * 错误码文档
 */

public enum ResponseCode {

    // 公共
    API_99999(9999, "操作异常，请重新尝试"),
    API_00000(200, "操作成功"),
    API_00200(200, "操作成功"),
    API_OK(200, "操作成功"),
    SUCCESS(200, "操作成功"),
    FAIL(400, "fail"),
    NO_INFORMATION(-5, "事物不存在"),

    // ======== user ===============
    USER_10000(10000, "用户未登录"),
    USER_10001(10001, "注册失败"),
    USER_10002(10002, "用户验证码在有效期"),
    USER_10003(10003, "用户验证码无效,请重新获取"),
    USER_10004(10004, "用户未设置资金安全密码"),
    USER_10005(10005, "用户未认证"),

    USER_10011(10011, "只支持承兑商登录"),

    USER_11001(11001, "提现地址无效"),
    USER_11002(11002, "提现地址已被删除,请重新添加"),

    USER_11100(11100, "当前提现数量不能低于最小提现数量"),
    USER_11101(11101, "当前提现数量不能高于最大提现数量"),
    USER_11102(11102, "资产余额不足"),
    USER_11103(11103, "提币数量不能低于网络手续费"),
    USER_11105(11105, "提币数量小数位不可大于"),
    USER_11106(11106, "提币数量不能高于账户可提币数量"),

    USER_11104(11104, "需要谷歌验证码"),

    USER_REALAUTH(12000, "fail"),


    // ======== user end ===========
    // ======== coin    ===========
    COIN_30101(30101, "该币种已被管理员关闭提现,详细查阅公告"),
    COIN_30200(30200, "虚拟币不存在"),
    // ======== coin end ===========

    // ============ file ================
    FILE_40001(40001, "上次文件失败"),

    // =========  获取验证类型  =========
    VERIFY_CODE(50000, "NotCodeErr"),
    SUPER_LOGIN(60000, "请登录超级节点"),

    // ======== pay start ===============
    PAY_UNFDER_ORDER(70000, "下单失败，请联系管理员"),

    PAY_SIGN_ERROR(70001, "签名不正确"),
    PAY_CNY_TYPE_ERROR(70002, "CNY 只支付类型 1,2,3"),
    PAY_NOT_APPID_ERROR(70003, "应用ID 不存在"),
    PAY_AMOUNT_ERROR(70004, "交易金额参数异常"),
    PAY_PRECISION_ERROR(70005, "交易精度异常"),
    PAY_COIN_NOT_ERROR(70006, "交易币种不存在"),
    PAY_MCH_ORDER_EXIST(70007, "商户订单号已经存在"),
    PAY_ORDER_DATA_EXIST(70008, "订单数据不存在"),

    PAY_MCH_NOT_ASSET(70010, "商户账户金额不足"),
    PAY_MCH_LOCK(70011, "商户下单锁开启"),
    // ======== pay end ===============

    ;

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseCode getByCode(int code) {
        for (ResponseCode ec : ResponseCode.values()) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
