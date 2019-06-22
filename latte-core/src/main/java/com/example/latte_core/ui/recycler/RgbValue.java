package com.example.latte_core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * 作者：贪欢
 * 时间：2019/6/22
 * 描述：
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red,green,blue);
    }
}
