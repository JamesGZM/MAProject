package com.ma.comics;

import android.app.Application;

import com.gutils.GActivityManager;
import com.gutils.GUtils;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class ComicsApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        GUtils.initialize(this);
        GUtils.setDebug(BuildConfig.DEBUG, "ma");
        registerActivityLifecycleCallbacks(GActivityManager.getActivityLifecycleCallbacks());
    }


}
