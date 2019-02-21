package com.aiceking.netapiloader.util.classloadutil;

import android.content.Context;
import android.util.Log;


import com.aiceking.netapiloader.util.classloadutil.annotation.CityAnnotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import dalvik.system.DexFile;

public class ClassLoadManager {
    private String Tag="ClassLoadManager：";
    private static ClassLoadManager classLoadManager;

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;
    public static ClassLoadManager getInstance(){
        if (classLoadManager==null){
            synchronized (ClassLoadManager.class){
                if (classLoadManager==null){
                    classLoadManager=new ClassLoadManager();
                }
            }
        }
        return classLoadManager;
    }
    public Class getCityClassByInterFace(String city,Class clazz){
        Class cla = null;
        if (clazz.isInterface()) {
            try {
                ArrayList<Class> allClass = getAllClass(context.getPackageCodePath(), context.getPackageName());
                for (int i = 0; i < allClass.size(); i++) {
                    if (clazz.isAssignableFrom(allClass.get(i))) {
                        if (!clazz.equals(allClass.get(i))) {
                            CityAnnotation cityAnnotation=(CityAnnotation)allClass.get(i).getAnnotation(CityAnnotation.class);
                            if (cityAnnotation.city().equals(city)){
                                cla=allClass.get(i);
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(Tag,e.getMessage());
            }
        }
        return cla;
    }
    public  ArrayList<Class> getAllClassByInterface(Class clazz) {
        ArrayList<Class> list = new ArrayList<>();
        if (clazz.isInterface()) {
            try {
                ArrayList<Class> allClass = getAllClass(context.getPackageCodePath(), context.getPackageName());
                for (int i = 0; i < allClass.size(); i++) {
                    if (clazz.isAssignableFrom(allClass.get(i))) {
                        if (!clazz.equals(allClass.get(i))) {
                            list.add(allClass.get(i));
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(Tag,e.getMessage());
            }
        }
        return list;
    }

    /**
     * 从一个指定路径下查找所有的类
     *
     */
    private  ArrayList<Class> getAllClass(String packagePath, String packageName) {
        ArrayList<Class> list = new ArrayList<>();
        try {
            DexFile df = new DexFile(packagePath);
            Enumeration<String> enumeration = df.entries();
            while (enumeration.hasMoreElements()) {
                String className = enumeration.nextElement();
                if (className.contains(packageName)) {
                    Class clazz = Class.forName(className);
                    list.add(clazz);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        return list;
    }


}
