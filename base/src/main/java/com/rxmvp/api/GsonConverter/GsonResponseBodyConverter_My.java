//package com.rxmvp.api.GsonConverter;
//
//import com.google.gson.Gson;
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.rxmvp.api.exception.ApiException;
//import com.rxmvp.bean.ResultResponse;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//import okhttp3.ResponseBody;
//import retrofit2.Converter;
//
///**
// * 适配 同一个字段 不同 状态码 的情况下返回不同的数据结构
// *
// * @param <T>
// */
//final class GsonResponseBodyConverter_My<T> implements Converter<ResponseBody, T> {
//    private final Gson gson;
//    private final TypeAdapter<T> adapter;
//    private Type type;
//
//    GsonResponseBodyConverter_My(Gson gson, TypeAdapter<T> adapter, Type type) {
//        this.gson = gson;
//        this.adapter = adapter;
//        this.type = type;
//    }
//
//    @Override
//    public T convert(ResponseBody value) throws IOException {
//
////        try {
//            JsonReader jsonReader = gson.newJsonReader(value.charStream());
////            return adapter.read(jsonReader);
////        } finally {
////            value.close();
////        }
//
//        try {
//            //添加 处理 "成功返回时是消息数据列表，失败时是异常消息文本"
//            String response = value.string();
////          Logger.d("Network"+  "response>>" + response);
//            //ResultResponse 只解析 状态码 字段
//            ResultResponse resultResponse = gson.fromJson(response, ResultResponse.class);
//            if (resultResponse.getStatus() == 1) {
//                //result==1表示成功返回，继续用本来的Model类解析
//                 return gson.fromJson(response, type);
//            } else {
//                //ResultResponse 将msg解析为异常消息文本
//                throw new ApiException(resultResponse.getStatus(), resultResponse.getMessage());
//            }
//        } finally {
//            value.close();
//        }
//
//    }
//
//}
