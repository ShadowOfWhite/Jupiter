package com.example.latte.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte_core.delegates.BaseDelegate;
import com.example.latte_core.delegates.buttom.ButtomItemDelegate;

/**
 * 作者：贪欢
 * 时间：2019/6/5
 * 描述：
 */
public class SortDelegate extends ButtomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
