package com.example.latte_core.util.timer;

import java.util.TimerTask;

/**
 * 作者：贪欢
 * 时间：2019/5/23
 * 描述：
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null){
            mITimerListener.onTimer();
        }

    }
}
