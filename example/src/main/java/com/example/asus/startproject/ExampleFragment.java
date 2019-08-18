package com.example.asus.startproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.ISuccess;

/**
 * Created by 杨淋 on 2018/6/1.
 * Describe：
 */

public class ExampleFragment extends LatteFragment {

    private static final String TAG = "ExampleDelegate";
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        Log.i(TAG, "onBindView: ----------");
//        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("https://news.baidu.com/")//https://news.baidu.com/
//                .params("","")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Log.i(TAG, "onSuccess: 成功了");
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(String msg) {
                        Log.e(TAG, "onFailure: 失败了");

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.i(TAG, "onError: 错误了");

                    }
                })
                .build()
                .get();


    }


}
