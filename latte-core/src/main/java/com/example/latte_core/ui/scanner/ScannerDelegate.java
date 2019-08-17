package com.example.latte_core.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte_core.delegates.LatteDelegate;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * 作者：贪欢
 * 时间：2019/8/6
 * 描述：
 */
public class ScannerDelegate extends LatteDelegate implements ZBarScannerView.ResultHandler {

    private ScanView scanView = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (scanView == null){
            scanView = new ScanView(getContext());
        }
        scanView.setAutoFocus(true);//自动对焦
        scanView.setResultHandler(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (scanView != null){
            scanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (scanView != null){
            scanView.stopCameraPreview();
            scanView.stopCamera();
        }
    }

    @Override
    public Object setLayout() {
        return scanView;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void handleResult(Result rawResult) {
        final Bundle bundle = new Bundle();
        bundle.putString("SCAN_RESULT",rawResult.getContents());
        setFragmentResult(1000,bundle);
        getSupportDelegate().pop();
    }
}
