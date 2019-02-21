package com.aiceking.netapiloader.http.retrofit;

import com.aiceking.netapiloader.http.gsonhelp.GsonHelp;
import com.aiceking.netapiloader.http.gsonhelp.MyGsonConverterFactory;
import com.aiceking.netapiloader.http.httpsCerHelp.HttpsCerHelp;
import com.aiceking.netapiloader.libraryinit.NetApiLoader;
import com.aiceking.netapiloader.util.logutil.LogUtil;
import com.aiceking.netapiloader.util.urlutil.UrlUtil;

import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by radio on 2017/9/20.
 */

public class RetrofitHelp {
    public static final int CONNECT_TIME_OUT = 30;//连接超时时长x秒
    public static final int READ_TIME_OUT = 30;//读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 30;//写数据接超时时长x秒
    private NetApiLoader netApiLoader;
    public Retrofit getRetrofit() {
        return retrofit;
    }
    private Retrofit retrofit;
    public RetrofitHelp(NetApiLoader netApiLoader){
        this.netApiLoader=netApiLoader;
        initRetrofitAndNetApi();
    }
    public void initRetrofitAndNetApi() {
         retrofit = new Retrofit.Builder()
                .client(initOkHttpClient())
                .baseUrl(UrlUtil.getBaseUrl(netApiLoader))
                .addConverterFactory(new MyGsonConverterFactory(GsonHelp.getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder mBuilder ;
        if (netApiLoader.isHasCer()) {
            //  设置https证书
            mBuilder = HttpsCerHelp.getClientBuilderByCer(netApiLoader.getContext(), netApiLoader.getCerNames());
        }else{
            mBuilder = HttpsCerHelp.getClientBuilderByCer(netApiLoader.getContext(), new String[]{""});
        }
        //开启Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.showLog("okHttp：",message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        /**RetrofitUrlManager用于随时切换BaseUrl*/
        OkHttpClient client= RetrofitUrlManager.getInstance().with(mBuilder)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
        return client;
    }

    public  void  changeBaseUrl(String tag, String url){
        RetrofitUrlManager.getInstance().putDomain(tag, url);
    }
}
