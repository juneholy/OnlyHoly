package com.test.holy.onlyholy;

import android.app.Application;

import com.test.holy.onlyholy.crash.CrashHandler;

/**
 * Created by houlin.jiang on 2017/3/9.
 */

public class MyApplication extends Application {
    private static MyApplication sInstance;

    public static MyApplication getsInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        CrashHandler.getsInstance().init(this);
    }
}
