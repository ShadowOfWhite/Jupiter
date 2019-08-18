package com.example.asus.startproject;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.latte.ec.launcher.LauncherFragment;
import com.example.latte.ec.main.EcBottomFragment;
import com.example.latte.ec.sign.ISignListener;
import com.example.latte.ec.sign.SignInFragment;
import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.app.Latte;
import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.ui.launcher.ILauncherListener;
import com.example.latte_core.ui.launcher.OnLauncherFinishTag;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
        ISignListener ,ILauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        //状态栏透明
        StatusBarCompat.translucentStatusBar(this,true);

    }

    @Override
    public LatteFragment setRootDelegate() {
        return WebExampleFragment.create(Config.INDEX_URL);
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
//                Toast.makeText(this,"启动结束，用户登录了",Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new EcBottomFragment());
                break;
            case NOT_SIGNED:

                Toast.makeText(this,"启动结束，用户没登录",Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new SignInFragment());
                break;
                default:
                    break;
        }
    }


}

