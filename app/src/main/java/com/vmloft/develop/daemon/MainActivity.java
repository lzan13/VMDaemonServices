package com.vmloft.develop.daemon;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.vmloft.develop.daemon.services.VMBackgroundService;
import com.vmloft.develop.daemon.services.VMCoreService;
import com.vmloft.develop.daemon.services.VMDaemonJobService;
import com.vmloft.develop.daemon.services.VMDaemonService;
import com.vmloft.develop.daemon.services.VMForegroundService;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_foreground).setOnClickListener(viewListener);
        findViewById(R.id.btn_daemon).setOnClickListener(viewListener);
        findViewById(R.id.btn_background).setOnClickListener(viewListener);
        findViewById(R.id.btn_job_service).setOnClickListener(viewListener);

        // 启动核心进程
        startCoreProcess();
    }

    /**
     * 点击事件监听
     */
    private View.OnClickListener viewListener = new View.OnClickListener() {
        @Override public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_foreground:
                    Intent foregroundIntent =
                            new Intent(getApplicationContext(), VMForegroundService.class);
                    startService(foregroundIntent);
                    break;
                case R.id.btn_daemon:
                    Intent daemonIntent =
                            new Intent(getApplicationContext(), VMDaemonService.class);
                    startService(daemonIntent);
                    break;
                case R.id.btn_background:
                    Intent backgroundIntent =
                            new Intent(getApplicationContext(), VMBackgroundService.class);
                    startService(backgroundIntent);
                    break;
                case R.id.btn_job_service:
                    startJobScheduler();
                    break;
            }
        }
    };

    /**
     * 5.x以上系统启用 JobScheduler API 进行实现守护进程的唤醒操作
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void startJobScheduler() {
        int jobId = 1;
        JobInfo.Builder jobInfo =
                new JobInfo.Builder(jobId, new ComponentName(this, VMDaemonJobService.class));
        jobInfo.setPeriodic(10000);
        jobInfo.setPersisted(true);
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo.build());
    }

    /**
     * 启动核心进程
     */
    private void startCoreProcess() {
        startService(new Intent(getApplicationContext(), VMCoreService.class));
    }
}
