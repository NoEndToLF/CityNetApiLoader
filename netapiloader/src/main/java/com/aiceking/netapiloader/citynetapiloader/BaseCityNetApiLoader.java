package com.aiceking.netapiloader.citynetapiloader;

import android.content.Context;

import com.aiceking.netapiloader.libraryinit.NetApiLoader;

public interface BaseCityNetApiLoader {
    NetApiLoader getNetApiLoader(Context context);
}
