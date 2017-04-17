package com.rxmvp.api.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 返回参数 []-> null ,{} -> null
 */
public class ResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //返回参数 []-> null ,{} -> null
        Response response = chain.proceed(request);

        ResponseBody body = response.body();
        String bodystring = body.string();
        if (bodystring.contains("[]")) {
            bodystring = bodystring.replace("[]","null");
        }
        if (bodystring.contains("{}")) {
            bodystring = bodystring.replace("{}","null");
        }
        Response newResponse = response.newBuilder().body(ResponseBody.create(MediaType.parse(body.contentType().toString()), bodystring)).build(); //重组

        return newResponse;
    }
    //end


}
