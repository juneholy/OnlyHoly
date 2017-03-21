package com.test.holy.onlyholy.crash;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.test.holy.onlyholy.MainActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by houlin.jiang on 2017/3/9.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    public static final String LOG_PATH = Environment.getExternalStorageDirectory().getPath() + "/Android/CrashLog/";

    private static CrashHandler sInstance = new CrashHandler();
    private UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    public CrashHandler() {
    }

    public static CrashHandler getsInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e(TAG,"=======================start=============================");
        dumpExceptionToSDCard(e);
        e.printStackTrace();
        restartApp();
        mDefaultCrashHandler.uncaughtException(t,e);


    }

    private void restartApp() {
        //使用Toast来显示异常信息
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext.getApplicationContext(), "系统出错,即将重启... ...",
                        Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Log.e(TAG, "error : ", e);
        }
        Intent intent = new Intent(mContext.getApplicationContext(), MainActivity.class);
        PendingIntent restartIntent = PendingIntent.getActivity(
                mContext.getApplicationContext(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        //退出程序
        AlarmManager mgr = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                restartIntent); // 1秒钟后重启应用

    }

    private void dumpExceptionToSDCard(Throwable e) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

        }

        File dir =new File(LOG_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(LOG_PATH + "crash" + time);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            e.printStackTrace(pw);
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void dumpPhoneInfo(PrintWriter pw) {
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
            pw.println("APP Version :" + pi.versionName + "_" + pi.versionCode);
            pw.println("OS Version :" + VERSION.RELEASE + "_" + VERSION.SDK_INT);
            pw.println("Vendor :" + Build.MANUFACTURER);
            pw.println("Model :" + Build.MODEL);
            pw.println("CPU ABI :" + Build.CPU_ABI);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
