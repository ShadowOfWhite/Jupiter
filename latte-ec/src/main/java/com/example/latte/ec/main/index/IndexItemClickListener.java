package com.example.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.latte.ec.detail.GoodsDetailFragment;
import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class IndexItemClickListener  extends SimpleClickListener {

    private final LatteFragment DELEGATE;

    private IndexItemClickListener(LatteFragment delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(LatteFragment delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        int goodsId = entity.getField(MultipleFields.ID);
        GoodsDetailFragment delegate = GoodsDetailFragment.create(goodsId);
        DELEGATE.start(delegate);
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
