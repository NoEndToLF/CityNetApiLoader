package com.aiceking.netapiloader.util.urlutil;


import com.aiceking.netapiloader.libraryinit.NetApiLoader;

/**
 * Created by radio on 2017/9/20.
 */

public class UrlUtil {
    public static String getBaseUrl(NetApiLoader netApiLoader){
        if (netApiLoader.isDebug()){
            return netApiLoader.getDebugUrl();
        }else {
            return netApiLoader.getReleaseUrl();
        }
    }
}
