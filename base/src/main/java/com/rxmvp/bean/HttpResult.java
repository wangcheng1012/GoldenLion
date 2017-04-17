package com.rxmvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.trinea.android.common.annotation.NotProguard;

/*
Http服务返回一个固定格式的数据
{
    "status": 1,
    "message": "已发送",
    "data": []
}
 */
@NotProguard
public class HttpResult<T> implements Parcelable {
    private int status;
    private String message;
    private T data;


    protected HttpResult(Parcel in) {
        status = in.readInt();
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HttpResult> CREATOR = new Creator<HttpResult>() {
        @Override
        public HttpResult createFromParcel(Parcel in) {
            return new HttpResult(in);
        }

        @Override
        public HttpResult[] newArray(int size) {
            return new HttpResult[size];
        }
    };

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "status:" + getStatus()+"message:"+getMessage()+"data:"+getData();
    }
}