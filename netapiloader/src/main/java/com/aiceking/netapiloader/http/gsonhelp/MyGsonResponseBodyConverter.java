package com.aiceking.netapiloader.http.gsonhelp;

import com.aiceking.netapiloader.http.exception.ServerException;
import com.aiceking.netapiloader.response.BaseStatusModel;
import com.aiceking.netapiloader.util.logutil.LogUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by static on 2018/3/28/028.
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            LogUtil.showLog("response",response);
            //ResultResponse 只解析result字段
            BaseStatusModel resultResponse = gson.fromJson(response, BaseStatusModel.class);
            if (resultResponse.isSuccess() ){
                //result==0表示成功返回，继续用本来的Model类解析
                return gson.fromJson(response, type);
            } else {
                //ErrResponse 将msg解析为异常消息文本
                throw new ServerException(resultResponse.getStatus(), resultResponse.getMessage());
            }
        } finally {
            value.close();
        }
    }
}
