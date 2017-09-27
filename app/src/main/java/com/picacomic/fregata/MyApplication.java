package com.picacomic.fregata;

import android.app.Application;
import android.content.Context;

import com.gutils.GActivityManager;
import com.gutils.GUtils;
import com.ma.comics.BuildConfig;
import com.ma.comics.utils.GenerateSignature;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class MyApplication extends Application{

    private static MyApplication mInstance;
    private static Context mAppContext;
    private GenerateSignature generateSignature;


    static
    {
        System.loadLibrary("JniTest");
    }

    public native String getStringComFromNative();
    public native String getStringConFromNative(final String[] p0);
    public native String getStringFromNative();
    public native String getStringSigFromNative();

    @Override
    public void onCreate() {
        super.onCreate();
        (MyApplication.mInstance = this).setAppContext(this.getApplicationContext());
        GUtils.initialize(this);
        GUtils.setDebug(BuildConfig.DEBUG, "ma");
        registerActivityLifecycleCallbacks(GActivityManager.getActivityLifecycleCallbacks());
    }

    public void setAppContext(final Context mAppContext) {
        MyApplication.mAppContext = mAppContext;
    }

    public static Context getAppContext() {
        return MyApplication.mAppContext;
    }

    public static MyApplication getInstance() {
        return MyApplication.mInstance;
    }

    public boolean getStringCom() {
        return (this.getStringComFromNative() + "").equalsIgnoreCase("1");
    }

    public String getStringCon(final String[] array) {
        if (this.generateSignature == null) {
            this.generateSignature = new GenerateSignature();
        }
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            string = string + array[i] + ", ";
        }
        final String stringConFromNative = this.getStringConFromNative(array);
        return this.generateSignature.getSignature(stringConFromNative, this.getStringSigFromNative());
    }

}
