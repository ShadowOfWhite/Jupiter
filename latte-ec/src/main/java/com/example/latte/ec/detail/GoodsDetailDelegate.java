package com.example.latte.ec.detail;

import android.os.Bundle;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class GoodsDetailDelegate extends LatteDelegate {

    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }


    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        //返回水平的进场动画
        return new DefaultHorizontalAnimator();
    }
}
