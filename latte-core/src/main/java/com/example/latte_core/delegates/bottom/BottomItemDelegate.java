package com.example.latte_core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;


import com.example.latte_core.R;
import com.example.latte_core.app.Latte;
import com.example.latte_core.delegates.LatteDelegate;

/**
 * 作者：贪欢
 * 时间：2019/6/4
 * 描述：每一个页面
 */
public abstract class BottomItemDelegate extends LatteDelegate {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplication().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }




/*    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis() - mExitTime > EXIT_TIME){
                Toast.makeText(getContext(),"再次点击退出"+getString(R.string.app_name),Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }else {
                _mActivity.finish();
                if (mExitTime != 0){
                    mExitTime = 0;
                }
            }
            return true;
        }
        return false;
    }*/
}
