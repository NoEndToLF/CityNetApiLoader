package com.aiceking.citynetapiloader.application;

import android.app.Application;

import com.aiceking.citynetapiloader.BuildConfig;
import com.aiceking.netapiloader.citynetapiloader.CityNetApiLoader;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CityNetApiLoader.getInstance().BindAllCityNetApiLoader(this, BuildConfig.isLog);
    }
}
