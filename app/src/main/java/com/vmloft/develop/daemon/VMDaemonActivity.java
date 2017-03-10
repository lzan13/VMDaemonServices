package com.vmloft.develop.daemon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by lzan13 on 2017/3/9.
 */

public class VMDaemonActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lzan13", "VMDaemonActivity -> onCreate");

        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 1;
        lp.height = 1;

        window.setAttributes(lp);

        VMDaemonManager.getInstance().setDaemonActivity(this);
    }
}
