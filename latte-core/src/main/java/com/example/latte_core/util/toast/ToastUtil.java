package com.example.latte_core.util.toast;

import android.widget.Toast;

import com.example.latte_core.app.Latte;

/**
 * 作者：贪欢
 * 时间：2019/6/20
 * 描述：
 */
public class ToastUtil {

    // TODO: 2019/7/27 这里可以加上线程判断，子线程显示方式不一样
    public static void showText(String text){
        Toast.makeText(Latte.getApplication(),text,Toast.LENGTH_SHORT).show();
    }
}
