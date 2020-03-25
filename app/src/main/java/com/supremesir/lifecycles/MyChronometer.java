package com.supremesir.lifecycles;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author HaoFan Fang
 * @date 2020/3/25 10:38
 */

public class MyChronometer extends Chronometer implements LifecycleObserver {

    private long elapsedTime;
    // TODO: 将elapsedTime和ViewModel结合起来

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void pauseMeter() {
        elapsedTime = SystemClock.elapsedRealtime() - getBase();
        stop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void resumeMeter() {
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }
}
