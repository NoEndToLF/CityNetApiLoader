package com.aiceking.netapiloader.http.function;


import com.aiceking.netapiloader.http.exception.ServerException;
import com.aiceking.netapiloader.http.gsonhelp.GsonHelp;
import com.aiceking.netapiloader.response.BaseModel;
import com.aiceking.netapiloader.util.logutil.LogUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by radio on 2017/9/20.
 */

public class ServerReponseFunction implements Function<BaseModel, Object>{

    @Override
    public Object apply(@NonNull BaseModel baseModel) throws Exception {
        if (!baseModel.isSuccess()){
            throw new ServerException(baseModel.getStatus(), baseModel.getMessage().toString());
        }
        LogUtil.showJsonLog("Json：", GsonHelp.getGson().toJson(baseModel));
        return baseModel;
    }
}
