package com.vmloft.develop.daemon.services;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;
import com.vmloft.develop.daemon.VMDaemonManager;

/**
 * 5.x 以上使用 JobService 实现守护进程，这个守护进程要做的工作很简单，就是启动应用的核心进程
 * Created by lzan13 on 2017/3/8.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP) public class VMDaemonJobService extends JobService {

    private final static String TAG = VMDaemonJobService.class.getSimpleName();

    @Override public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob");
        // 这里为了演示，这里直接启动核心进程，没有做其他判断操作
        VMDaemonManager.getInstance().startCoreProcess();
        return false;
    }

    @Override public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob");
        return false;
    }
}
