package com.rxmvp.transformer;


import com.rxmvp.bean.HttpResult;
import com.rxmvp.http.ExceptionEngine;
import com.rxmvp.http.exception.ErrorType;
import com.rxmvp.http.exception.ServerException;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by York on 2016/7/23.
 * 加入了对错误处理，已经比较完整了
 */
public class ErrorTransformer<T> implements Observable.Transformer<HttpResult<T>, T> {

    @Override
    public Observable<T> call(Observable<HttpResult<T>> responseObservable) {
        return responseObservable.map(new Func1<HttpResult<T>, T>() {
            @Override
            public T call(HttpResult<T> httpResult) {
                // 通过对返回码进行业务判断决定是返回错误还是正常取数据
//                if (httpResult.getCode() != 200) throw new RuntimeException(httpResult.getMessage());
                HttpResultNoSuccess(httpResult);
//                Logger.e(httpResult+"  ccccc");
                return httpResult.getData();
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(Throwable throwable) {
                //ExceptionEngine为处理异常的驱动器
                throwable.printStackTrace();
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        });
    }

    public static <T> ErrorTransformer<T> create() {
        return new ErrorTransformer<>();
    }

    private static ErrorTransformer instance = null;

    private ErrorTransformer() {
    }

    /**
     * 双重校验锁单例(线程安全)
     */
    public static <T> ErrorTransformer<T> getInstance() {
        if (instance == null) {
            synchronized (ErrorTransformer.class) {
                if (instance == null) {
                    instance = new ErrorTransformer<>();
                }
            }
        }
        return instance;
    }


    public static void HttpResultNoSuccess(HttpResult httpResult) {
        if (httpResult.getStatus() != ErrorType.SUCCESS) {
            throw new ServerException(httpResult.getMessage(), httpResult.getStatus());
        }
    }

}
