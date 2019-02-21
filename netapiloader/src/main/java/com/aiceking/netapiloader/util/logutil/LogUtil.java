package com.aiceking.netapiloader.util.logutil;

import com.aiceking.netapiloader.citynetapiloader.CityNetApiLoader;
import com.aiceking.netapiloader.libraryinit.NetApiLoader;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by radio on 2017/9/21.
 */

public class LogUtil {

    public static void showLog(String tag, String message){
        if (CityNetApiLoader.getInstance().isLog()){
            Logger.t(tag).i(message);
        }
    }
    public static void showJsonLog(String tag, String message){
        if (CityNetApiLoader.getInstance().isLog()){
            Logger.t(tag).json(message);
        }
    }
    public static void showListLog(String tag, List list){
        if (CityNetApiLoader.getInstance().isLog()){
            Logger.t(tag).d(list);
        }
    }
    public static void showMapLog(String tag, Map map){
        if (CityNetApiLoader.getInstance().isLog()){
            Logger.t(tag).d(map);
        }
    }
}
