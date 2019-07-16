package com.example.latte.ec.pay;

/**
 * 作者：贪欢
 * 时间：2019/7/10
 * 描述：
 */
public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
