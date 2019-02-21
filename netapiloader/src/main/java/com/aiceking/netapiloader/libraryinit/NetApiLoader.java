package com.aiceking.netapiloader.libraryinit;

import android.content.Context;

import com.aiceking.netapiloader.http.retrofit.RetrofitHelp;
import com.aiceking.netapiloader.http.uploadimghelp.UpLoadImgService;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by radio on 2017/9/13.
 */

public class NetApiLoader<T> {
    private Context context;
    private boolean isDebug;

    private String debugUrl;
    private String releaseUrl;
    public String[] getCerNames() {
        return cerNames;
    }
    public void setCerNames(String[] cerNames) {
        this.cerNames = cerNames;
    }
    private String[]cerNames;
    private T netService;
    private boolean isHasCer;
    private RetrofitHelp retrofitHelp;
    public UpLoadImgService getUpLoadImgService() {
        return upLoadImgService;
    }
    public void setUpLoadImgService() {
        upLoadImgService= retrofitHelp.getRetrofit().create(UpLoadImgService.class);
    }
    private UpLoadImgService upLoadImgService;
    public NetApiLoader(){
        retrofitHelp=new RetrofitHelp(this);
    }

    public String getDebugUrl() {
        return
                debugUrl;
    }
    public String getReleaseUrl() {
        return releaseUrl;
    }
    public T getNetService() {
        return netService;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public Context getContext() {
        return context;
    }
    public boolean isDebug() {
        return isDebug;
    }
    public void setDebug(boolean debug) {
        isDebug = debug;
    }
    public void changeDebug(boolean debug,final Class<T> service){
        isDebug = debug;
        retrofitHelp.initRetrofitAndNetApi();
        netService=  retrofitHelp.getRetrofit().create(service);
        setUpLoadImgService();
    }

    public void setApiService(final Class<T> service){
        netService=  retrofitHelp.getRetrofit().create(service);
    }
    public void setBaseUrl(String debugUrl, String releaseUrl){
        this.debugUrl=debugUrl;
        this.releaseUrl=releaseUrl;
    }
    public void setHttpsCer(boolean isHasCer){
        this.isHasCer=isHasCer;
    }
    public boolean isHasCer() {
        return isHasCer;
    }
}
