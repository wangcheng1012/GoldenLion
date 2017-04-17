package com.rxmvp.http.exception;

/**
 * 约定异常
 */

public interface ErrorType {
    /**
     * 正常
     */
    int SUCCESS = 1;
    /**
     * 未知错误
     */
    int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    int HTTP_ERROR = 1003;

    /**
     * 重新登录
     */
    int RE_LOGIN = 121;
}
