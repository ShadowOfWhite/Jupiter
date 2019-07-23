package com.example.latte.ec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.latte.ec.main.personal.list.ListBean;
import com.example.latte_core.delegates.LatteDelegate;

/**
 * 作者：贪欢
 * 时间：2019/7/21
 * 描述：
 */
public class PersonalClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public PersonalClickListener(LatteDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getmId();
        switch (id) {
            case 1:

                DELEGATE.getparentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;

            case 2:
                DELEGATE.getparentDelegate().getSupportDelegate().start(bean.getmDelegate());
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
