package com.aiceking.citynetapiloader.shanghai;

import android.content.Context;

import com.aiceking.netapiloader.citynetapiloader.BaseCityNetApiLoader;
import com.aiceking.netapiloader.libraryinit.NetApiLoader;
import com.aiceking.netapiloader.util.classloadutil.annotation.CityAnnotation;

@CityAnnotation(city = "上海")
public class ShanghaiNetApiLoader implements BaseCityNetApiLoader {
    @Override
    public NetApiLoader getNetApiLoader(Context context) {
        NetApiLoader<ShanghaiNetApi> netApiNetApiLoader=new NetApiLoader<>();

        return netApiNetApiLoader;
    }
}
