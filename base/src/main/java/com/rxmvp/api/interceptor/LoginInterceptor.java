package com.rxmvp.api.interceptor;

import android.os.Bundle;

import com.rxmvp.http.exception.ErrorType;
import com.wlj.base.util.AppConfig;
import com.wlj.base.util.AppManager;
import com.wlj.base.util.GoToHelp;
import com.wlj.base.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * 登录
 */
public class LoginInterceptor implements Interceptor {
    public static final String interceptorLogin = "InterceptorLogin";
    public static final String error_code = "error_code";
    /*
         {
            "status": 0,
            "message": "请登录",
            "data": {"error_code": "00001"}
         }
          */
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //返回参数 []-> null ,{} -> null
        Response response = chain.proceed(request);

        ResponseBody body = response.body();
        String bodystring = body.string();

        try {
            JSONObject jsonObject = new JSONObject(bodystring);
            int status = jsonObject.optInt("status");
            if(status == 0){
                JSONObject data = jsonObject.optJSONObject("data");
                if(data != null){
                    String errorCode = data.optString("error_code");
                    if("00001".equals(errorCode)){
                        String key = AppConfig.getAppConfig().get(AppConfig.CONF_KEY);
                        if(!StringUtils.isEmpty(key)) {
//                            jsonObject.put("message", "你的账号在其他地方登录\n如不是本人操作请及时修改密码");
                            jsonObject.put("status",  ErrorType.RE_LOGIN);
                        }
                        //登录
                        try {
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(interceptorLogin,true);
//                            bundle.putString("error_code",errorCode);
                            GoToHelp.go(AppManager.getAppManager().currentActivity(), Class.forName("com.dw.applebuy.ui.loginreg.LoginActivity"),bundle);

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            bodystring = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response newResponse = response.newBuilder().body(ResponseBody.create(MediaType.parse(body.contentType().toString()), bodystring)).build(); //重组

        return newResponse;
    }
    //end


}
