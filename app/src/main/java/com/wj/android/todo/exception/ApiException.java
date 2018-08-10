package com.wj.android.todo.exception;

/**
 * 作者：wangwnejie on 2018/8/8 14:06
 * 邮箱：wangwenjie1303@stnts.com
 */
public class ApiException extends RuntimeException{

    private int errorCode;

    public ApiException(String message, int errorCode) {
        super(message);
        setErrorCode(errorCode);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
