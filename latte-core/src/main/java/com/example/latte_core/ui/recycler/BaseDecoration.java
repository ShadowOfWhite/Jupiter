package com.example.latte_core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * 作者：贪欢
 * 时间：2019/6/22
 * 描述：
 */
public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size){
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecoration create(@ColorInt int color,int size){
        return new BaseDecoration(color,size);
    }
}
