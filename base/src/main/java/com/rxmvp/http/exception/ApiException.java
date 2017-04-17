package com.rxmvp.http.exception;


public class ApiException extends RuntimeException {
    // 异常处理，为速度，不必要设置getter和setter
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }
    public ApiException(int status, String message) {
        super(message);
        this.code = status;
    }
}
