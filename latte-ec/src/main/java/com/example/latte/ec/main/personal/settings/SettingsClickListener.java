package com.example.latte.ec.main.personal.settings;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.latte.ec.main.personal.list.ListBean;
import com.example.latte_core.fragments.LatteFragment;

/**
 * 作者：贪欢
 * 时间：2019/7/27
 * 描述：
 */
public class SettingsClickListener extends SimpleClickListener {

    private final LatteFragment DELEGATE;

    public SettingsClickListener(LatteFragment delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getmId();
        switch (id) {
            case 1:
                break;

            case 2:

                break;
            default:
                break;
        }


    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
