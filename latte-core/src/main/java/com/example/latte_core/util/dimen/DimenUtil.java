package com.example.latte_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.latte_core.app.Latte;

/**
 * Created by 杨淋 on 2018/10/18.
 * Describe：用来测量的工具方法
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
