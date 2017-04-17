package com.rxmvp.api;

import com.rxmvp.bean.HttpResult;
import com.rxmvp.http.exception.ApiException;

import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getStatus() != 1) {
            throw new ApiException(httpResult.getStatus(), httpResult.getMessage());
        }

        return httpResult.getData();

    }
}