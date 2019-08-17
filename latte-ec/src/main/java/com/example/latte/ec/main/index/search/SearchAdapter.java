package com.example.latte.ec.main.index.search;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.example.latte.ec.R;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * 作者：贪欢
 * 时间：2019/8/11
 * 描述：
 */
public class SearchAdapter extends MultipleRecyclerAdapter {


    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (entity.getItemType()){
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;

                default:
                    break;
        }
    }
}
