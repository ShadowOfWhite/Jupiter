package com.example.latte_core.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * 作者：贪欢
 * 时间：2019/7/18
 * 描述：请求码存储
 */

public class RequestCode {

    public static final int TAKE_PHOTO = 4;
    public static final int PICK_PHOTO = 5;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
    public static final int SCAN = 8;
}
