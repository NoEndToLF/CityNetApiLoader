package com.aiceking.netapiloader.citynetapiloader;

import android.content.Context;

import com.aiceking.netapiloader.libraryinit.NetApiLoader;
import com.aiceking.netapiloader.util.classloadutil.ClassLoadManager;
import com.aiceking.netapiloader.util.classloadutil.annotation.CityAnnotation;
import com.aiceking.netapiloader.util.logutil.LogUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class CityNetApiLoader {
    private String Tag="CityNetApiLoader";
    private static CityNetApiLoader cityNetApiLoader;
    private HashMap<String, NetApiLoader> netApiLoaderHashMap;
    private boolean isLog;
    public CityNetApiLoader(){
        netApiLoaderHashMap=new HashMap<>();
    }
    public boolean isLog() {
        return isLog;
    }
/**加载所有城市的NetApiLoader*/
    public void BindAllCityNetApiLoader(Context context,boolean isLog) {
        this.isLog=isLog;
        Logger.addLogAdapter(new AndroidLogAdapter());
        try {
            ArrayList<Class> baseCityNetApiLoaderArrayList= ClassLoadManager.getInstance()
                    .getAllClassByInterface(BaseCityNetApiLoader.class);
            for (Class cla:baseCityNetApiLoaderArrayList){
                CityAnnotation cityAnnotation=(CityAnnotation)cla.getAnnotation(CityAnnotation.class);
                if (!netApiLoaderHashMap.containsKey(cityAnnotation.city())){
                    netApiLoaderHashMap.put(cityAnnotation.city(),((BaseCityNetApiLoader)(cla.newInstance())).getNetApiLoader(context));
                }
            }
        }catch (Exception e){

        }

    }

    public NetApiLoader getCityNetApiLoader(String city){
        NetApiLoader netApiLoader = null;
        if (netApiLoaderHashMap.containsKey(city)){
            if (netApiLoaderHashMap.get(city)!=null){
                netApiLoader= netApiLoaderHashMap.get(city);
            }else {
                LogUtil.showLog(Tag,"未找到"+city+"对应的NetApiLoader");
            }
        }else {
            LogUtil.showLog(Tag,"未找到"+city+"对应的NetApiLoader");
        }
        return netApiLoader;
    }
    public static CityNetApiLoader getInstance(){
        if (cityNetApiLoader==null){
            synchronized (CityNetApiLoader.class){
                if (cityNetApiLoader==null){
                    cityNetApiLoader=new CityNetApiLoader();
                }
            }
        }
        return cityNetApiLoader;
    }

}
