package com.supremesir.lifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    private long elapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.meter);
        // UNIX时间，从1970-1-1到现在经过的毫秒数
//        chronometer.setBase(System.currentTimeMillis());
        // 手机从上次启动到现在经过的毫秒数，一般用作时间段的统计
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        chronometer.setBase(SystemClock.elapsedRealtime() - elapsedTime);
    }

    // 在视图层面的stop和start只是暂停和启动了chronometer的刷新效果
    // chronometer的实际功能并未停止
//    @Override
//    protected void onPause() {
//        super.onPause();
//        chronometer.stop();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        chronometer.start();
//    }
}
