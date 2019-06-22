package com.example.latte_core.util.toast;

import android.widget.Toast;

import com.example.latte_core.app.Latte;

/**
 * 作者：贪欢
 * 时间：2019/6/20
 * 描述：
 */
public class ToastUtil {

    public static void showText(String text){
        Toast.makeText(Latte.getApplication(),text,Toast.LENGTH_SHORT).show();
    }
}
