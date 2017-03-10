package com.vmloft.develop.daemon.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import com.vmloft.develop.daemon.MainActivity;
import com.vmloft.develop.daemon.R;

/**
 * 正常的系统前台进程，会在系统通知栏显示一个Notification通知图标，因为这个通知的关系，应用的进程优先级是比较高的，
 * 一般系统的内存回收是无法杀死的
 *
 * Created by lzan13 on 2017/3/7.
 */
public class VMForegroundService extends Service {

    private final static String TAG = VMForegroundService.class.getSimpleName();

    // 通知栏发送的通知 id
    private final static int NOTIFY_ID = 1000;

    @Override public void onCreate() {
        Log.i(TAG, "VMForegroundService->onCreate");
        super.onCreate();
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "VMForegroundService->onStartCommand");

        // 创建个通知，这样可以提高服务的优先级
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("这是一条前台通知");
        builder.setContentText("这是一条前台通知，表示当前程序有一个前台服务在运行");
        builder.setContentInfo("前台服务");
        builder.setWhen(System.currentTimeMillis());
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        // 开启前台通知
        startForeground(NOTIFY_ID, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("onBind 未实现");
    }

    @Override public void onDestroy() {
        Log.i(TAG, "VMForegroundService->onDestroy");
        super.onDestroy();
    }
}