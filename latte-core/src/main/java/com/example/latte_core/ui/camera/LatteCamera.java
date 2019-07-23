package com.example.latte_core.ui.camera;

import android.net.Uri;

import com.example.latte_core.delegates.PermissionCheckDelegate;
import com.example.latte_core.util.file.FileUtil;

/**
 * 作者：贪欢
 * 时间：2019/7/18
 * 描述：照相机调用类
 */


public class LatteCamera {
    public static Uri createCropFile(){
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
