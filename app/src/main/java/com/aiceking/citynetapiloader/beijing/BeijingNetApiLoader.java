package com.aiceking.citynetapiloader.beijing;

import android.content.Context;

import com.aiceking.citynetapiloader.BuildConfig;
import com.aiceking.netapiloader.citynetapiloader.BaseCityNetApiLoader;
import com.aiceking.netapiloader.libraryinit.NetApiLoader;
import com.aiceking.netapiloader.util.classloadutil.annotation.CityAnnotation;

@CityAnnotation(city = "北京")
public class BeijingNetApiLoader implements BaseCityNetApiLoader {
    @Override
    public NetApiLoader getNetApiLoader(Context context) {
        NetApiLoader<BeijingNetApi> netApiNetApiLoader=new NetApiLoader<>();

        return netApiNetApiLoader;
    }
}
