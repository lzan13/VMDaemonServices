package com.vmloft.develop.daemon.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 普通的后台 Service 服务，此类只是为了展示正常服务进程的优先级
 *
 * Created by lzan13 on 2017/3/7.JobScheduler机制唤醒
 */
public class VMBackgroundService extends Service {

    private final static String TAG = VMBackgroundService.class.getSimpleName();

    @Override public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
    }

    @Override public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("onBind 未实现");
    }

    @Override public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}