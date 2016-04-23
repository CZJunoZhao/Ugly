package com.codey.ugly;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.umeng.comm.core.sdkmanager.LocationSDKManager;
import com.umeng.community.location.DefaultLocationImpl;

import cn.smssdk.SMSSDK;


/**
 * Created by Mr.Codey on 2016/4/3.
 */
public class MyApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        // 确保不要重复注入同一类型的对象,建议在Application类的onCreate中执行该代码。
        LocationSDKManager.getInstance().addAndUse(new DefaultLocationImpl()) ;
        //umeng

        //sms
       SMSSDK.initSDK(this, "11654608618c2", "1f56bd1e0399f6d4187a935aca829ba4");
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
