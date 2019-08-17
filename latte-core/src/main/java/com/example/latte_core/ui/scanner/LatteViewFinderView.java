package com.example.latte_core.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.example.latte_core.R;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * 作者：贪欢
 * 时间：2019/8/10
 * 描述：
 */
public class LatteViewFinderView extends ViewFinderView {
    public LatteViewFinderView(Context context) {
        this(context,null);
    }

    public LatteViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}
