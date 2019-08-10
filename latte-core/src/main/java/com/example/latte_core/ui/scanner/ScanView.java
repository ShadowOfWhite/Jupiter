package com.example.latte_core.ui.scanner;

import android.content.Context;
import android.util.AttributeSet;

import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * 作者：贪欢
 * 时间：2019/8/6
 * 描述：
 */
public class ScanView extends ZBarScannerView {

    public ScanView(Context context) {
        this(context,null);
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }




}
