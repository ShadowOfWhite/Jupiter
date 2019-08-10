package com.example.latte_core.util.callback;

import android.support.annotation.NonNull;

/**
 * 作者：贪欢
 * 时间：2019/7/21
 * 描述：
 */
public interface IGlobalCallback<T> {

    void executeCallback(@NonNull T args);
}
