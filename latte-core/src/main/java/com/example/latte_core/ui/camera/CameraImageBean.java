package com.example.latte_core.ui.camera;

import android.net.Uri;

/**
 * 作者：贪欢
 * 时间：2019/7/18
 * 描述：存储一些中间值
 */
public class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
