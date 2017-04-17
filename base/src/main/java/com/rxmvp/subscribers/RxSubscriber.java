package com.rxmvp.subscribers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rxmvp.basemvp.BaseView;
import com.rxmvp.http.exception.ApiException;
import com.rxmvp.http.exception.ErrorType;
import com.rxmvp.utils.NetworkUtil;
import com.wlj.base.util.UIHelper;

/**
 *
 */
public abstract class RxSubscriber<T> extends BaseSubscriber<T> {

    private static final String TAG = RxSubscriber.class.getSimpleName();
    private BaseView mView;
//    private Context mContext;

    public RxSubscriber(BaseView mView) {

        this.mView = mView;
    }

//    public RxSubscriber(Context mContext) {
//        this.mContext = mContext;
//
//    }

    @Override
    public void onStart() {
        super.onStart();

        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable()) {
            mView.showMessage("当前无网络，请检查网络情况");
            onCompleted();

            // 无网络执行complete后取消注册防调用onError
            if (!isUnsubscribed()) {
                unsubscribe();
            }
        } else {
            mView.showLoading();
        }
    }

    @Override
    public void onCompleted() {
        if (mView != null)
            mView.hideLoading();
    }

    @Override
    protected void onError(ApiException ex) {
        if (mView != null) {
            mView.hideLoading();

            if(ex.code != ErrorType.RE_LOGIN) {
                //重新登录时不谈提示框
                mView.showMessage(ex.message);
            }
        }
        Log.e(TAG, "onError: " + ex.message + "code: " + ex.code);
    }

    @Override
    public abstract void onNext(T t);
}
