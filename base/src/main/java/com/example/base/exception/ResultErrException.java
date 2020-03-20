package com.example.base.exception;

import com.example.base.response.ResponseCode;

public class ResultErrException extends RuntimeException {

    private int errCode;
    private String errMsg;
    private boolean localeMess;
    private boolean useCode;

    public boolean isUseCode() {
        return useCode;
    }

    public ResultErrException(ResponseCode errCode, String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
        this.errCode = errCode.getCode();
    }

    public ResultErrException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.errMsg = responseCode.getMsg();
        this.errCode = responseCode.getCode();
    }

    public ResultErrException(int errCode, String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
        this.errCode = errCode;
    }
    public ResultErrException(int errCode, String errMsg,boolean useCode) {
        super(errMsg);
        this.errMsg = errMsg;
        this.errCode = errCode;
        this.useCode = useCode;
    }

    public ResultErrException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
        this.errCode = ResponseCode.FAIL.getCode();
    }
    public ResultErrException(String errMsg,boolean localeMess) {
        this(errMsg);
        this.errMsg = errMsg;
        this.localeMess = localeMess;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public boolean isLocaleMess() {
        return localeMess;
    }
}
